package client.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.omg.CORBA.portable.UnknownException;

import client.model.GameData;
import client.model.NetData;
import client.view.ClientFrame;
import client.model.MessageData;

public class Connect
{
	public void connect()
	{
		try
		{
			String ip = ClientFrame.getInstance().getGamePanel().getIpIn().getText();
			String port = ClientFrame.getInstance().getGamePanel().getPortIn().getText();

			int portInt = Integer.parseInt(port);

			Socket socket = new Socket(ip, portInt);

			GameData.getInstance().setConnected(true);
			MessageData.getInstance().addMessage("Connect to server!");
			ClientFrame.getInstance().getGamePanel().getConnectInfo().setText("Connected");

			InputStream iStream = socket.getInputStream();
			OutputStream oStream = socket.getOutputStream();
			InputStreamReader iStreamReader = new InputStreamReader(iStream);

			NetData.getInstance().setBufferedReader(iStreamReader);
			NetData.getInstance().setPrintStream(oStream);
		} catch (UnknownException e)
		{
			MessageData.getInstance().addMessage("Can't find this server!");
		} catch (IOException e)
		{
			MessageData.getInstance().addMessage("Error in connecting!");
		}
	}
}
