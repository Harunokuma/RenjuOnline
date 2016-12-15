package server.control;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import server.model.*;

public class EndDeal
{
	public void clientOff(int id)
	{
		HashMapData HMD = HashMapData.getInstance();

		int oppoId = HMD.getClient(id).getOppoID();

		if (oppoId != 0)
		{
			HMD.getClient(oppoId).setOppoID(0);
			try
			{
				Socket socket = HMD.getClient(oppoId).getSocket();
				PrintStream ps = new PrintStream(socket.getOutputStream());
				ps.println("OPER:QUIT:");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		HMD.removeMatchs(id);
		HMD.removeMatching(id);

		if (HMD.getGameDatas().containsKey(id)) // ����Ѿ���������Ϸ����
			HMD.getGameDatas().remove(id);

		if (HMD.getGameDatas().containsKey(oppoId)) // �������Ҳ��������Ϸ����
			HMD.getGameDatas().remove(oppoId);

		new Action().removeClient(id);
		MessageData.getInstance().addMessage("Client [" + id + "] is offline");

		HMD.removeClient(id);
	}
}
