package client.control;

import java.io.BufferedReader;
import java.io.IOException;

import client.model.*;

public class Analysis implements Runnable{
	@Override
	public void run()
	{
		BufferedReader bReader = NetData.getInstance().getBufferedReader();
		
		String line;
		
		while(GameData.getInstance().getConnected() == true)
		{
			try
			{
				line = bReader.readLine();
				
				//初始化信息
				if(line.substring(0, 5).equals("INIT:"))
					new Action().init(line.substring(5));
				
				//系统消息
				else if(line.substring(0, 5).equals("SYST:"))
					new Action().system(line.substring(5));
				
				//获取列表信息
				else if(line.substring(0, 5).equals("LIST:"))
					new Action().list(line.substring(5));
				
				//增加玩家
				else if(line.substring(0,5).equals("ADDL:"))
					new Action().addList(line.substring(5));
				
				//删除玩家
				else if(line.substring(0,5).equals("DELE:"))
					new Action().delList(line.substring(5));
				
				//聊天
				else if(line.substring(0,5).equals("CHAT:"))
					new Action().chat(line.substring(5));
				
				//操作
				else if(line.substring(0,5).equals("OPER:"))
					new Action().operation(line.substring(5));
				
				//回复邀请
				else if(line.substring(0,5).equals("REPL:"))
					new Action().replyInvitation(line.substring(5));
				
				//游戏开始
				else if(line.substring(0,5).equals("STAR:"))
					new Action().start(line.substring(5));
				
				//下棋
				else if(line.substring(0,5).equals("PLAY:"))
					new Action().play(line.substring(5));
				
				//胜利
				else if(line.substring(0, 5).equals("UWIN:"))
					new Action().win();
				//失败
				else if(line.substring(0, 5).equals("LOSE:"))
					new Action().lose();
				
			} catch (IOException e)
			{
				GameData gameData = GameData.getInstance();
				gameData.clearChess();
				gameData.clearTurn();
				gameData.setMyChess(0);
				gameData.setId(0);
				gameData.setName(null);
				gameData.setOppoId(0);
				gameData.setStarted(false);
			}
		}
	}
	
}
