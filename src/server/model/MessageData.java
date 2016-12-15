package server.model;

import javax.swing.JTextArea;

//����������˵���Ϣ
public class MessageData
{
	private static MessageData instance = null;
	
	private JTextArea messageArea = null;
	
	private MessageData(){
		
	}
	
	public static MessageData getInstance(){
		if(instance == null)
			instance = new MessageData();
		return instance;
	}
	
	public JTextArea getMessageArea(){
		if(messageArea == null)
			messageArea = new JTextArea("",20,25);
		return messageArea;
	}
	
	public void addMessage(String message)
	{
		messageArea.append(message + "\n");
	}
}
