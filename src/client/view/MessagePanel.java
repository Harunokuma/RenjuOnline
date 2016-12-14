package client.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import client.control.MessageListener;
import client.model.MessageData;

public class MessagePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTextField inputMessage = null;
	
	public MessagePanel()
	{
		this.setLayout(new BorderLayout());
		this.add(MessageData.getInstance().getMessageArea(), BorderLayout.CENTER);
		getInputMessage().addActionListener(new MessageListener());
		this.add(this.getInputMessage(), BorderLayout.SOUTH);
	}
	
	public JTextField getInputMessage()
	{
		if(inputMessage == null)
			inputMessage = new JTextField();
		return inputMessage;
	}
}
