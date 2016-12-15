package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.model.GameData;
import client.model.NetData;
import client.view.ClientFrame;
import client.model.MessageData;

public class LeaveListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(GameData.getInstance().getOppoId() != 0)
		{
			NetData.getInstance().getPrintStream().println("OPER:" + "QUIT:" + GameData.getInstance().getOppoId());
			
			GameData.getInstance().setOppoId(0);
			GameData.getInstance().clearChess();
			GameData.getInstance().setStarted(false);
			GameData.getInstance().setMyChess(0);
			
			ClientFrame.getInstance().getGamePanel().getBoard().update();
			ClientFrame.getInstance().getFunctionPanel().getPlayerPanel().getOppoInfo().setText(
					"opponent: null");
			
			MessageData.getInstance().addMessage("You can invite another player now!");
		}
		
		else
			JOptionPane.showMessageDialog(null, "You have not join a game!");
	}
}
