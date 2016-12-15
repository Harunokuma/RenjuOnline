package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.model.GameData;
import client.model.NetData;
import client.view.ClientFrame;
import client.model.MessageData;

public class MessageListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String message = ClientFrame.getInstance().getFunctionPanel().getMessagePanel().getInputMessage().getText();
		String name = GameData.getInstance().getName();
		
		int oppoId = GameData.getInstance().getOppoId();
		
		if(oppoId != 0)
			NetData.getInstance().getPrintStream().println("CHAT:" + message + "&" + oppoId);
		
		ClientFrame.getInstance().getFunctionPanel().getMessagePanel().getInputMessage().setText("");
		MessageData.getInstance().addMessage(name + ":" + message);
	}
}
