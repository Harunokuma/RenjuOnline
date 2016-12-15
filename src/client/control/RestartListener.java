package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import client.model.GameData;
import client.model.NetData;
import client.view.ClientFrame;

public class RestartListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (GameData.getInstance().getConnected() == true)
		{
			if (GameData.getInstance().getStarted() == true)
			{
				GameData.getInstance().clearChess();
				GameData.getInstance().clearTurn();
				GameData.getInstance().setStarted(false);

				GameData.getInstance().setMyChess(0);

				ClientFrame.getInstance().getGamePanel().getBoard().update();

				NetData.getInstance().getPrintStream().println("OPER:" + "REST:");
			} else
				JOptionPane.showMessageDialog(null, "You have not joined a game!");
		} else
			JOptionPane.showMessageDialog(null, "You have not connected!");
	}
}
