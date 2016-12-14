package client.model;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import client.control.PlayListListener;

public class ListData {
	private static ListData instance = null;
	
	private JList<String> playerList = null;
	
	private DefaultListModel<String> model = null;
	
	private ListData(){}
	
	public static ListData getInstance()
	{
		if(instance == null)
			instance = new ListData();
		return instance;
	}
	
	public DefaultListModel<String> getModel()
	{
		if(model == null)
			model = new DefaultListModel<String>();
		return model;
	}
	
	public JList<String> getList()
	{
		if(playerList == null)
		{
			playerList = new JList<String>(getModel());
			playerList.addMouseListener(new PlayListListener());
		}
		return playerList;
	}
	
	public void addPlayer(String name)
	{
		this.getModel().addElement(name);
		this.getList().repaint();
	}
	
	public void removePlayer(String name)
	{
		this.getModel().removeElement(name);
		this.getList().repaint();
	}
	
	public void clearList()
	{
		this.getModel().removeAllElements();
		this.getList().repaint();
	}
	
}
