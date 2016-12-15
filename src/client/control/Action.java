package client.control;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import client.model.*;
import client.view.ClientFrame;

public class Action
{
	String line;

	//执行初始化
	public void init(String _line)
	{
		line = _line;

		String[] strings = line.split("-");
		String name = strings[1];
		int id = Integer.parseInt(strings[0]);

		GameData.getInstance().setId(id);
		GameData.getInstance().setName(name);

		NetData.getInstance().getPrintStream().println("LIST:");
	}

	//打印系统消息
	public void system(String _line)
	{
		line = _line;
		MessageData.getInstance().addMessage(line);
	}
	
	//更新玩家列表
	public void list(String _line)
	{
		line = _line;
		
		try{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				
				@Override
				public void run()
				{
					ListData.getInstance().clearList();
					String[] strings = line.split("&");
					for(int i=0;i<strings.length;i++)
					{
						ListData.getInstance().addPlayer(strings[i]);
					}
					
				}
			});
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//添加玩家
	public void addList(String _line)
	{
		line = _line;
		
		try{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				
				@Override
				public void run()
				{
					ListData.getInstance().addPlayer(line);
				}
			});
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//删除玩家
	public void delList(String _line)
	{
		line = _line;
		
		try{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				
				@Override
				public void run()
				{
					ListData.getInstance().removePlayer(line);
				}
			});
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//聊天
	public void chat(String _line)
	{
		line = _line;
		
		String[] strings = line.split("&");
		String name = strings[1];
		String message = strings[0];
		
		MessageData.getInstance().addMessage(name + ":" + message);
	}
	
	//操作
	public void operation(String _line)
	{
		line = _line;
		
		//邀请
		if(line.substring(0, 5).equals("INVI:"))
		{
			line = line.substring(5);
			
			String[] strings = line.split("-");
			int src = Integer.parseInt(strings[0]);
			String name = strings[1];
			
			int value = JOptionPane.showConfirmDialog(null,	name + " invite you to join a game, do you accept?",
					"Invitation", JOptionPane.YES_NO_OPTION);
			
			if(value == JOptionPane.YES_OPTION)
			{
				NetData.getInstance().getPrintStream().println("REPL:"+ src + "&YES");
				GameData.getInstance().setOppoId(src);
				
				ClientFrame.getInstance().getFunctionPanel().getPlayerPanel().getOppoInfo().setText(
						"opponent: " +name);
				MessageData.getInstance().addMessage("You accept " + name + "'s invitation!");
			}
			else{
				NetData.getInstance().getPrintStream().println("REPL:"+ src + "&NO");
				MessageData.getInstance().addMessage("You reject " + name + "'s invitation!");
			}
		}
		
		else if(line.substring(0, 5).equals("QUIT:"))
		{
			GameData.getInstance().setOppoId(0);
			GameData.getInstance().clearChess();
			GameData.getInstance().setStarted(false);
			GameData.getInstance().setMyChess(0);
			
			ClientFrame.getInstance().getGamePanel().getBoard().update();
			ClientFrame.getInstance().getFunctionPanel().getPlayerPanel().getOppoInfo().setText(
					"opponent: null");
			MessageData.getInstance().addMessage("Another player has leaved this room!");
			MessageData.getInstance().addMessage("You can invite another player now!");
		}
	}
	
	public void replyInvitation(String _line)
	{
		line = _line;
		String[] strings = line.split("-");
		int src = Integer.parseInt(strings[0]);
		String[] strings2 = strings[1].split("&");
		String name = strings2[0];
		String result = strings2[1];
		
		if(result.equals("YES"))
		{
			GameData.getInstance().setOppoId(src);
			ClientFrame.getInstance().getFunctionPanel().getPlayerPanel().getOppoInfo().setText(
					"opponent: " +name);
			MessageData.getInstance().addMessage(name + " accept your invitation!");
		}
		else if(result.equals("NO"))
		{
			MessageData.getInstance().addMessage(name + " That player reject your invitation!");
		}
		
	}
	
	public void start(String _line)
	{
		line = _line;
		int color =0;
		
		if(line.substring(0, 5).equals("BLACK"))
			color = GameData.BLACK;
		else if(line.substring(0, 5).equals("WHITE"))
			color = GameData.WHITE;
		
		GameData.getInstance().setMyChess(color);
		GameData.getInstance().changeTurn();
		GameData.getInstance().setStarted(true);
	}
	
	public void play(String _line)
	{
		line = _line;
		
		int position = Integer.parseInt(line);
		
		GameData.getInstance().playChess(position);
		ClientFrame.getInstance().getGamePanel().getBoard().update();
		MessageData.getInstance().addMessage("It's your turn to play.");
	}
	
	public void win()
	{
		JOptionPane.showMessageDialog(null, "You win!");
		GameData.getInstance().clearTurn();
//		GameData.getInstance().setStarted(false);
	}
	
	public void lose()
	{
		JOptionPane.showMessageDialog(null, "You lose!");
		GameData.getInstance().clearTurn();
//		GameData.getInstance().setStarted(false);
	}
}
