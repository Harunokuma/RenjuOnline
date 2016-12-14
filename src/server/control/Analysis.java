package server.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

import server.model.*;

public class Analysis
{
	int id;
	Socket socket;
	OutputStream outputStream;
	String read;

	public void analysis(int _id, Socket _socket, String _read)
	{
		id = _id;
		socket = _socket;
		read = _read;

		try
		{
			outputStream = socket.getOutputStream();
			PrintStream ps = new PrintStream(outputStream);

			// 初始化信息
			if (read.substring(0, 5).equals("INIT:"))
			{
				HashMapData.getInstance().getClient(id).setName(read.substring(5));
				new Action().newClient(id);
				ps.println("INIT:" + id + "-" + read.substring(5));
			}

			// 更新列表
			if (read.substring(0, 5).equals("LIST:"))
				new Action().getClientList(socket);

			// 回复
			if (read.substring(0, 5).equals("REPL:"))
			{
				String str = read.substring(5);

				// 回复邀请
				if (str.substring(0, 5).equals("CHAL:"))
					new Action().replyInvitation(id, str.substring(5));
			}

			// 落子
			if (read.substring(0, 5).equals("PLAY:"))
			{
				String str = read.substring(5);
				int position = Integer.parseInt(str);
				new Action().playChess(id, position);
			}

			// 聊天
			if (read.substring(0, 5).equals("CHAT:"))
				new Action().sendMessage(id, read.substring(5));

			// 操作
			if (read.substring(0, 5).equals("OPER:"))
			{
				String str = read.substring(5);
				// 邀请玩家
				if (str.substring(0, 5).equals("CHAL:"))
				{
					str = str.substring(5);
					int target = Integer.parseInt(str);
					new Action().sendInvitation(id, target);
				}
				// 重新开始
				if (str.substring(0, 5).equals("REST:"))
				{
					new Action().sendRestart(id);
				}
				// 退出配对
				if (str.substring(0, 5).equals("QUIT:"))
				{
					int oppoId = Integer.parseInt(str.substring(5));
					new Action().quit(id, oppoId);
				}
			}
		} catch (IOException e)
		{
			MessageData.getInstance().addMessage("Analysis input stream error!");
			e.printStackTrace();
		}
	}
}
