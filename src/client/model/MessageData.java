package client.model;

import java.awt.TextArea;

public class MessageData
{
	private static MessageData instance = null;

	private TextArea MessageArea = null;

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
		if (MessageArea == null)
			MessageArea = new TextArea("", 16, 28, TextArea.SCROLLBARS_VERTICAL_ONLY);
		return MessageArea;
	}

	public void addMessage(String message)
	{
		getMessageArea().append(message + "\n");
	}
}
