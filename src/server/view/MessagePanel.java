package server.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import server.model.MessageData;

public class MessagePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public MessagePanel()
	{
		this.setLayout(new BorderLayout());
		this.add(MessageData.getInstance().getMessageArea(), BorderLayout.CENTER);
		this.setBorder(new TitledBorder(new EtchedBorder(), "Message list" ,TitledBorder.CENTER ,TitledBorder.TOP ));
	}
}
