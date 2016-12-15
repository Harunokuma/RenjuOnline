package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.GameData;
import client.model.NetData;
import client.view.ClientFrame;
import client.model.MessageData;

public class LoginListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(GameData.getInstance().getConnected() == false)
		{
			new Connect().connect();
			
			if(GameData.getInstance().getConnected() == true)
			{
				Thread thread = new Thread(new Analysis());
				thread.start();
				
				String name = ClientFrame.getInstance().getGamePanel().getNameIn().getText();
				NetData.getInstance().getPrintStream().println("INIT:" + name);
			}
		}
		
		else
			MessageData.getInstance().addMessage("Already connected!");
	}
}
