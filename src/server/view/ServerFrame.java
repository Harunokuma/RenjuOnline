package server.view;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import server.control.ServerThread;
import server.model.MessageData;

public class ServerFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static ServerFrame instance = null;

	ServerSocket serverSocket;
	int port = 10086;

	private ClientPanel clientPanel = null;
	private MessagePanel messagePanel = null;
	private MatchsPanel matchsPanel = null;

	private ServerFrame()
	{
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setLocation(400, 100);

		this.add(getClientPanel(), BorderLayout.WEST);
		this.add(getMessagePanel(), BorderLayout.CENTER);
		this.add(getMatchsPanel(), BorderLayout.EAST);

		this.pack();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void ServerPowerUp()
	{
		try
		{
			serverSocket = new ServerSocket(port);
			MessageData.getInstance().addMessage("Server start!");
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "port" + port + " is not available");
			System.exit(0);
		}

		while (true)
		{
			try
			{
				Socket socket = serverSocket.accept();
				Thread thread = new Thread(new ServerThread(socket));
				thread.start();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public ClientPanel getClientPanel()
	{
		if (clientPanel == null)
			clientPanel = new ClientPanel();
		return clientPanel;
	}

	public MessagePanel getMessagePanel()
	{
		if (messagePanel == null)
			messagePanel = new MessagePanel();
		return messagePanel;
	}

	public MatchsPanel getMatchsPanel()
	{
		if (matchsPanel == null)
			matchsPanel = new MatchsPanel();
		return matchsPanel;
	}

	public static ServerFrame getInstance()
	{
		if (instance == null)
			instance = new ServerFrame();
		return instance;
	}
}