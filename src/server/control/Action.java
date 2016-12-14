package server.control;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;

import server.model.*;

public class Action
{
	HashMapData HMD = HashMapData.getInstance();

	// 添加新客户端
	public void newClient(int id)
	{
		Iterator<ClientData> i = HMD.getClients().values().iterator();

		while (i.hasNext()) // 对所有的客户端发送新客户端的信息
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

	// 移除客户端
	public void removeClient(int id)
	{
		Iterator<ClientData> i = HMD.getClients().values().iterator();

		while (i.hasNext()) // 对所有的客户端发送被移除的客户端的信息
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

	// 获取客户端列表
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

	// 发送消息
	public void sendMessage(int src, String read)
	{
		// 消息位于&字符之前，目标id位于&字符之后
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

	// 发送对战邀请
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

	// 回复对战邀请
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
			if ("YES".equals(answer)) // 同意对战
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

	// 发送重新开始游戏
	public void sendRestart(int src)
	{
		HMD.getGameDatas().get(src).restart(src);
	}

	// 下棋
	public void playChess(int src, int position)
	{
		HMD.getGameDatas().get(src).sendPlay(src, position);
	}

	// 离开本次游戏
	public void quit(int src, int dst)
	{
		// 向对手客户端发送退出消息
		Socket socket = HMD.getClient(dst).getSocket();

		try
		{
			PrintStream ps = new PrintStream(socket.getOutputStream());
			ps.println("OPER:QUIT:");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		if (HMD.getMatchs().containsKey(src)) // 如果使用src作key
			HMD.getMatchs().remove(src);
		else // 如果是用dst作key
			HMD.getMatchs().remove(dst);

		// 移除GameData
		HMD.getGameDatas().remove(src);
		HMD.getGameDatas().remove(dst);
	}
}
