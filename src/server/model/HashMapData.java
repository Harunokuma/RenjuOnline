package server.model;

import java.util.HashMap;

import server.view.ServerFrame;

public class HashMapData
{
	private static HashMapData instance = null;
	
	private HashMap<Integer, ClientData> clients = null;
	private HashMap<Integer, Integer> matchs = null;
	private HashMap<Integer, Integer> matching = null;
	private HashMap<Integer, GameData> gameDatas = null;
	
	private HashMapData(){
		
	}
	
	public static HashMapData getInstance(){
		if(instance == null)
			instance = new HashMapData();
		return instance;
	}
	
	public HashMap<Integer, ClientData> getClients()
	{
		if(clients == null)
			clients = new HashMap<Integer, ClientData>();
		
		return clients;
	}
	
	public ClientData getClient(Integer id)
	{
		return getClients().get(id);
	}
	
	public void addClient(Integer id, ClientData clientData)
	{
		getClients().put(id, clientData);
		ServerFrame.getInstance().getClientPanel().addClient(id);
	}
	
	public void removeClient(Integer id)
	{
		getClients().remove(id);
		ServerFrame.getInstance().getClientPanel().removeClient(id);
	}
	
	public String getName(Integer id)
	{
		return getClient(id).getName();
	}
	
	public HashMap<Integer, Integer> getMatchs()
	{
		if(matchs == null)
			matchs = new HashMap<Integer, Integer>();
		
		return matchs;
	}
	
	public void addMatchs(Integer idA, Integer idB)
	{
		getMatchs().put(idA, idB);
		
		ServerFrame.getInstance().getMatchsPanel().addMatchs(idA, idB);
	}
	
	public void removeMatchs(Integer id)
	{
		getMatchs().remove(id);
		
		ServerFrame.getInstance().getMatchsPanel().removeMatchs(id);
	}
	
	public HashMap<Integer, Integer> getMatching()
	{
		if(matching == null)
			matching = new HashMap<Integer, Integer>();
		
		return matching;
	}
	
	public HashMap<Integer, GameData> getGameDatas()
	{
		if(gameDatas == null)
			gameDatas = new HashMap<Integer, GameData>();
		
		return gameDatas;
	}
}
