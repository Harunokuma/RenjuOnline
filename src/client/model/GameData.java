package client.model;

public class GameData
{

	private static GameData instance = null;

	private int id = 0;
	private int oppoId = 0;
	private String name = "";

	private int turn = 0;
	public static int BLACK = 1;
	public static int WHITE = -1;

	private int myChess = 0;
	private int oppoChess = 0;

	private boolean connected = false;
	private boolean started = false;

	private int[][] checkBoard = new int[15][15];

	private GameData()
	{
	}

	public static GameData getInstance()
	{
		if (instance == null)
			instance = new GameData();

		return instance;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setOppoId(int oppoId)
	{
		this.oppoId = oppoId;
	}

	public int getOppoId()
	{
		return oppoId;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void changeTurn()
	{
		if (turn == BLACK)
			turn = WHITE;
		else
			turn = BLACK;
	}
	
	public void clearTurn()
	{
		turn = 0;
	}

	public int getTurn()
	{
		return turn;
	}

	public void setMyChess(int color)
	{
		myChess = color;
		oppoChess = color * -1;
	}

	public int getMyChess()
	{
		return myChess;
	}

	public int getOppoChess()
	{
		return oppoChess;
	}

	public void setConnected(boolean connected)
	{
		this.connected = connected;
	}

	public boolean getConnected()
	{
		return connected;
	}

	public void setStarted(boolean started)
	{
		this.started = started;
	}

	public boolean getStarted()
	{
		return started;
	}

	public void playChess(int position)
	{
		int y = position / 15;
		int x = position % 15;

		checkBoard[x][y] = turn;
		turn *= -1;
	}

	public int getChess(int x, int y)
	{
		return checkBoard[x][y];
	}

	public void clearChess()
	{
		checkBoard = new int[15][15];
	}

}
