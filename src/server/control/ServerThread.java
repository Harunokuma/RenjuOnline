package server.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import server.model.*;

public class ServerThread implements Runnable
{
	Socket socket;
	
	public ServerThread(Socket socket){
		this.socket = socket;
		
		ClientData clientData = new ClientData(this.hashCode(), socket);
		HashMapData.getInstance().addClient(this.hashCode(), clientData);
	}
	
	
	@Override
	public void run(){
		
		boolean connected = true;
		InputStream is;
		String read = null;
		MessageData.getInstance().addMessage("Player" + this.hashCode() + " is online!");
		
		while(connected){
			try{
				is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				read = br.readLine();
				
				new Analysis().analysis(this.hashCode(), socket, read);
			}
			catch(IOException e){
				connected = false;
				new EndDeal().clientOff(this.hashCode());
			}
		}
	}
}
