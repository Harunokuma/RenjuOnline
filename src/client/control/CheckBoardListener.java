package client.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import client.model.GameData;
import client.model.NetData;
import client.view.CheckBoardCanvas;
import client.view.ClientFrame;
import client.model.MessageData;

public class CheckBoardListener extends MouseAdapter
{
	@Override
	public void mouseClicked(MouseEvent e)
	{
		CheckBoardCanvas board = (CheckBoardCanvas) e.getSource();
		GameData gameData = GameData.getInstance();

		if (gameData.getConnected() == true)
		{
			if (gameData.getStarted() == true)
			{
				if (gameData.getTurn() != 0)
				{
					if (gameData.getTurn() == gameData.getMyChess())
					{
						if (e.getX() < board.getWidth() - 6 && e.getY() < board.getHeight() - 7)
						{
							int x;
							int y;

							x = e.getX() / 35;
							y = e.getY() / 35;

							if (gameData.getChess(x, y) == 0)
							{
								int position = x + y*15;
								gameData.playChess(position);
								ClientFrame.getInstance().getGamePanel().getBoard().update();
								
								NetData.getInstance().getPrintStream().println("PLAY:" + position);
								MessageData.getInstance().addMessage("Waiting...");
							}
						}
					} else
						MessageData.getInstance().addMessage("It's not your turn!");
				} else
					MessageData.getInstance().addMessage("Game is over!");
			} else
				MessageData.getInstance().addMessage("You should join a game first!");
		} else
			MessageData.getInstance().addMessage("You should connect first!");
	}
}
