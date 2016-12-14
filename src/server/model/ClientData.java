package server.model;

import java.net.Socket;

//保存客户端的信息
public class ClientData
{
	private int ID;
	private int oppoID;

	private boolean started;

	private String name;
	private Socket socket;

	GameData gameData;

	public ClientData(int ID, Socket socket)
	{
		this.ID = ID;
		this.socket = socket;
	}

	public void setOppoID(int oppoID)
	{
		this.oppoID = oppoID;
	}

	public void setStarted(boolean started)
	{
		this.started = started;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setGameData(GameData gameData)
	{
		this.gameData = gameData;
	}

	public int getID()
	{
		return ID;
	}

	public int getOppoID()
	{
		return oppoID;
	}

	public boolean isStarted()
	{
		return this.started;
	}

	public String getName()
	{
		return name;
	}

	public Socket getSocket()
	{
		return socket;
	}
}
