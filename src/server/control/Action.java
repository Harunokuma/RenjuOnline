package server.control;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;

import server.model.*;

public class Action
{
	HashMapData HMD = HashMapData.getInstance();

	// ����¿ͻ���
	public void newClient(int id)
	{
		Iterator<ClientData> i = HMD.getClients().values().iterator();

		while (i.hasNext()) // �����еĿͻ��˷����¿ͻ��˵���Ϣ
		{
			Socket socket = (Socket) i.next().getSocket();
			try
			{
				PrintStream ps = new PrintStream(socket.getOutputStream());
				String name = HMD.getName(id);
				ps.println("ADDL:" + id + "-" + name);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	// �Ƴ��ͻ���
	public void removeClient(int id)
	{
		Iterator<ClientData> i = HMD.getClients().values().iterator();

		while (i.hasNext()) // �����еĿͻ��˷��ͱ��Ƴ��Ŀͻ��˵���Ϣ
		{
			Socket socket = (Socket) i.next().getSocket();

			try
			{
				PrintStream ps = new PrintStream(socket.getOutputStream());
				String name = HMD.getName(id);
				ps.println("DELE:" + id + "-" + name);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	// ��ȡ�ͻ����б�
	public void getClientList(Socket socket)
	{
		String list = "";
		Iterator<Integer> i = HMD.getClients().keySet().iterator();

		try
		{
			PrintStream ps = new PrintStream(socket.getOutputStream());
			while (i.hasNext())
			{
				int id = i.next();
				String name = HMD.getName(id);

				list = list + id + "-" + name + "&";
			}
			ps.println("LIST:" + list);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// ������Ϣ
	public void sendMessage(int src, String read)
	{
		// ��Ϣλ��&�ַ�֮ǰ��Ŀ��idλ��&�ַ�֮��
		String[] s = read.split("&");
		String message = s[0];
		int dst = Integer.parseInt(s[1]);
		String name = HMD.getName(src);

		Socket socket = HMD.getClient(dst).getSocket();

		try
		{
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println("CHAT:" + message + "&" + src + "-" + name);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// ���Ͷ�ս����
	public void sendInvitation(int src, int dst)
	{
		Socket socket = HMD.getClient(dst).getSocket();
		String name = HMD.getName(src);

		try
		{
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println("OPER:INVI:" + src + "-" + name);
			HashMapData.getInstance().getMatching().put(src, dst);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// �ظ���ս����
	public void replyInvitation(int src, String read)
	{

		String[] s = read.split("&");
		int dst = Integer.parseInt(s[0]);
		String answer = s[1];
		String name = HMD.getName(src);
		Socket socket = HMD.getClient(dst).getSocket();

		try
		{
			PrintStream ps = new PrintStream(socket.getOutputStream());
			if ("YES".equals(answer)) // ͬ���ս
			{
				ps.println("REPL:INVI:" + src + "-" + name + "&YES");
				HMD.getMatching().remove(dst);
				HMD.addMatchs(dst, src);

				GameData publicGameData = new GameData();
				HMD.getGameDatas().put(dst, publicGameData);
				HMD.getGameDatas().put(src, publicGameData);

				publicGameData.startPlay(src, dst);
				publicGameData.sendStartMessage();
			} else if ("NO".equals(answer))
			{
				ps.println("REPL:INVI:" + src + "-" + name + "&NO");
				HMD.getMatching().remove(dst);
			} else
			{
				System.out.println("reply error!");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// �������¿�ʼ��Ϸ
	public void sendRestart(int src)
	{
		HMD.getGameDatas().get(src).restart(src);
	}

	// ����
	public void playChess(int src, int position)
	{
		HMD.getGameDatas().get(src).sendPlay(src, position);
	}

	// �뿪������Ϸ
	public void quit(int src, int dst)
	{
		// ����ֿͻ��˷����˳���Ϣ
		Socket socket = HMD.getClient(dst).getSocket();

		try
		{
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println("OPER:QUIT:");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		if (HMD.getMatchs().containsKey(src)) // ���ʹ��src��key
			HMD.getMatchs().remove(src);
		else // �������dst��key
			HMD.getMatchs().remove(dst);

		// �Ƴ�GameData
		HMD.getGameDatas().remove(src);
		HMD.getGameDatas().remove(dst);
	}
}
