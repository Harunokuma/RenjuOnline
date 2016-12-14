package client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.control.LoginListener;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JPanel gameBody;
	private JPanel gameBar;

	private JButton connectBtn;

	private JLabel splitter = null;
	private JLabel connectInfo = null;

	private JTextField ipIn = null;
	private JTextField portIn = null;
	private JTextField nameIn = null;

	private CheckBoardCanvas board = null;

	public GamePanel()
	{
		connectBtn = new JButton("Login");
		splitter = new JLabel(":");

		connectBtn.addActionListener(new LoginListener());

		gameBar = new JPanel();
		gameBar.setLayout(new FlowLayout());
		gameBar.add(this.getConnectInfo());
		gameBar.add(this.getIpIn());
		gameBar.add(this.splitter);
		gameBar.add(this.getPortIn());
		gameBar.add(this.connectBtn);
		gameBar.add(this.getNameIn());

		gameBody = new JPanel();
		gameBody.add(this.getBoard());

		this.setLayout(new BorderLayout());
		this.add(gameBar, BorderLayout.NORTH);
		this.add(gameBody, BorderLayout.CENTER);
	}

	public JLabel getConnectInfo()
	{
		if (connectInfo == null)
			connectInfo = new JLabel("Unconnected!");
		return connectInfo;
	}

	public CheckBoardCanvas getBoard()
	{
		if (board == null)
			board = new CheckBoardCanvas();
		return board;
	}

	public JTextField getIpIn()
	{
		if (ipIn == null)
			ipIn = new JTextField("127.0.0.1", 9);
		return ipIn;
	}

	public JTextField getPortIn()
	{
		if (portIn == null)
			portIn = new JTextField("10086", 5);
		return portIn;
	}

	public JTextField getNameIn()
	{
		if (nameIn == null)
		{
			nameIn = new JTextField("Newbie", 6);
			nameIn.setEditable(true);
		}

		return nameIn;
	}
}
