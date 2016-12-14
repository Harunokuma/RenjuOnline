package client.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class FunctionPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private PlayerPanel playerPanel = null;
	private MessagePanel messagePanel = null;
	private OperationPanel operationPanel = null;

	public FunctionPanel()
	{
		this.setLayout(new BorderLayout());
		this.add(getPlayerPanel(), BorderLayout.NORTH);
		this.add(getMessagePanel(), BorderLayout.CENTER);
		this.add(getOperationPanel(), BorderLayout.SOUTH);
	}

	public PlayerPanel getPlayerPanel()
	{
		if (playerPanel == null)
			playerPanel = new PlayerPanel();
		return playerPanel;
	}

	public MessagePanel getMessagePanel()
	{
		if (messagePanel == null)
			messagePanel = new MessagePanel();
		return messagePanel;
	}

	public OperationPanel getOperationPanel()
	{
		if (operationPanel == null)
			operationPanel = new OperationPanel();
		return operationPanel;
	}
}
