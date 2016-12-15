package client.model;

import java.awt.TextArea;

public class MessageData
{
	private static MessageData instance = null;

	private TextArea messageArea = null;

	private MessageData()
	{
	}

	public static MessageData getInstance()
	{
		if (instance == null)
			instance = new MessageData();
		return instance;
	}

	public TextArea getMessageArea()
	{
		if (messageArea == null)
			messageArea = new TextArea("", 16, 35, TextArea.SCROLLBARS_VERTICAL_ONLY);
		return messageArea;
	}

	public void addMessage(String message)
	{
		getMessageArea().append(message + "\n");
	}
}
