package client.view;

import java.awt.BorderLayout;
import javax.swing.*;

public class ClientFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static ClientFrame instance = null;
	GamePanel gamePanel = null;
	FunctionPanel functionPanel = null;

	private ClientFrame()
	{
		super("Renju Online");
	}

	public static ClientFrame getInstance()
	{
		if (instance == null)
			instance = new ClientFrame();
		return instance;
	}

	public void clientPowerUp()
	{
		this.setLayout(new BorderLayout());
		this.add(getGamePanel(), BorderLayout.WEST);
		this.add(getFunctionPanel(), BorderLayout.EAST);

		this.pack();
		this.setLocation(200, 10);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public GamePanel getGamePanel()
	{
		if (gamePanel == null)
			gamePanel = new GamePanel();
		return gamePanel;
	}

	public FunctionPanel getFunctionPanel()
	{
		if (functionPanel == null)
			functionPanel = new FunctionPanel();
		return functionPanel;
	}
}
