package server.model;

import java.io.IOException;
import java.io.PrintStream;


//保存一盘游戏的信息
public class GameData
{
	//客户端的ID
	int ClientA;
	int ClientB;
	
	//此轮下棋的一方
	int turn = 0;
	int BLACK = 1;
	int WHITE = -1;
	
	//是否同意重新开始的信息
	boolean restartA = false;
	boolean restartB = false;
	
	//向客户端发送的消息
	PrintStream printStreamA = null;
	PrintStream printStreamB = null;
	
	//棋盘
	int [][] checkBoard = new int[15][15];
	
	public void setClientA(int ClientA){
		this.ClientA = ClientA;
	}
	
	public void setClientB(int ClientB){
		this.ClientB = ClientB;
	}
	
	public PrintStream getPrintStreamA(){
		if(printStreamA == null)
		{
			try{
				printStreamA = new PrintStream(HashMapData.getInstance().getClient(ClientA).getSocket().getOutputStream());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return printStreamA;
	}
	
	public PrintStream getPrintStreamB(){
		if(printStreamB == null)
		{
			try{
				printStreamB = new PrintStream(HashMapData.getInstance().getClient(ClientB).getSocket().getOutputStream());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return printStreamB;
	}
	
	public void startPlay(int ClientA, int ClientB){
		this.setClientA(ClientA);
		this.setClientB(ClientB);
		
		this.getPrintStreamA().println("STAR:BLACK");
		this.getPrintStreamB().println("STAR:WHITE");
	}
	
	public void sendStartMessage(){
		this.getPrintStreamA().println("SYST:Game start, it's your turn");
		this.getPrintStreamB().println("SYST:Game start, please wait for your opponent");
		
		turn = BLACK;
	}
	
	public void sendPlay(int src, int position){
		if(src == ClientA)			//黑棋下子
		{
			int y = position / 15;
			int x = position % 15;
			
			checkBoard[x][y] = BLACK;
			this.getPrintStreamB().println("PLAY:" + position);
			turn = WHITE;
			
			if(this.isWin(x,y,BLACK))
			{
				this.getPrintStreamA().println("UWIN:");
				this.getPrintStreamB().println("LOSE:");
			}
		}
		else if(src == ClientB)		//白棋下子
		{
			int y = position / 15;
			int x = position % 15;
			
			checkBoard[x][y] = WHITE;
			this.getPrintStreamA().println("PLAY:" + position);
			turn = BLACK;
			
			if(this.isWin(x,y,WHITE))
			{
				this.getPrintStreamB().println("UWIN:");
				this.getPrintStreamA().println("LOSE:");
			}
		}
		else						//下子信息错误
		{
			MessageData.getInstance().addMessage("Play info error! " + "position is: " + position);
		}
	}
	
	public void restart(int src)
	{
		int dst = HashMapData.getInstance().getClient(src).getOppoID();
		
		if(src == ClientA)
		{
			if(restartA == false)
				restartA = true;
			
			if(restartB == false)
				this.getPrintStreamB().println("SYST:" + HashMapData.getInstance().getName(dst) + " is waiting for you to restart a game."
						+ "If you want to restart a game, click restart button.");
		}
			
		
		if(src == ClientB)
		{
			if(restartB == false)
				restartB = true;
			
			if(restartA == false)
				this.getPrintStreamA().println("SYST:" + HashMapData.getInstance().getName(dst) + " is waiting for you to restart a game."
						+ "If you want to restart a game, click restart button.");
		}
		
		if(restartA && restartB)
		{
			checkBoard = new int[15][15];
			this.startPlay(ClientA, ClientB);
			this.sendStartMessage();
			restartA = false;
			restartB = false;
		}
	}
	
	public boolean isWin(int x,int y, int color)
	{
		if(maxLen(x, y, 0, color) >= 5)
			return true;
		else
			return false;
	}
	
	/*
	 * 1 2 3 
	 * 4 0 5 
	 * 6 7 8
	 */
	//检测最长的连续棋子长度
	public int maxLen(int x, int y, int way, int color)
	{
		int maxLen = 0;
		int myColor = color;
		
		if (way == 0) // count a position's max length
		{
			myColor = checkBoard[x][y];
			for (int i = 0; i < 4; i++)
			{
				int len = 0;
				switch(i){
					case 0:
						len = maxLen(x-1,y-1,1,myColor) + maxLen(x+1,y+1,8,myColor);
						break;
					case 1:
						len = maxLen(x,y-1,2,myColor) + maxLen(x,y+1,7,myColor);
						break;
					case 2:
						len = maxLen(x+1,y-1,3,myColor) + maxLen(x-1,y+1,6,myColor);
						break;
					case 3:
						len = maxLen(x-1,y,4,myColor) + maxLen(x+1,y,5,myColor);
						break;
				}
				if(len > maxLen)
					maxLen = len;
			}
			return maxLen + 1;
		}
		else
		{
			if(x < 0 || x >=15 || y < 0 || y >=15)
				return 0;
			
			if(checkBoard[x][y] == myColor)
				maxLen = 1;
			else 
				return maxLen;
			
			switch(way){
			case 1:
				maxLen += maxLen(x-1,y-1,way, myColor);
				break;
			case 2:
				maxLen += maxLen(x,y-1,way, myColor);
				break;
			case 3:
				maxLen += maxLen(x+1,y-1,way, myColor);
				break;
			case 4:
				maxLen += maxLen(x-1,y,way, myColor);
				break;
			case 5:
				maxLen += maxLen(x+1,y,way, myColor);
				break;
			case 6:
				maxLen += maxLen(x-1,y+1,way, myColor);
				break;
			case 7:
				maxLen += maxLen(x,y+1,way, myColor);
				break;
			case 8:
				maxLen += maxLen(x+1,y+1,way, myColor);
				break;
			}
			return maxLen;
		}
	}
	
}
