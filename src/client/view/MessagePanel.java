package client.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import client.control.MessageListener;
import client.model.MessageData;

public class MessagePanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JPanel message = new JPanel(new BorderLayout());
	private JTextField inputMessage = null;

	public MessagePanel()
	{
		this.setLayout(new BorderLayout());
		
		message.add(MessageData.getInstance().getMessageArea(), BorderLayout.CENTER);
		getInputMessage().addActionListener(new MessageListener());
		message.add(this.getInputMessage(), BorderLayout.SOUTH);
		this.add(message);
	}

	public JTextField getInputMessage()
	{
		if (inputMessage == null)
			inputMessage = new JTextField();
		return inputMessage;
	}
}
