package client.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import client.control.InvitationListener;
import client.control.LeaveListener;
import client.model.ListData;

public class PlayerPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JPanel PlayerBar;
	private JPanel PlayerBody;
	private JPanel PlayerButtom;
	private JLabel oppoInfo;

	private JScrollPane listPane;

	private JButton invitation;
	private JButton leave;

	public PlayerPanel()
	{
		PlayerBar = new JPanel();
		PlayerBody = new JPanel(new BorderLayout());
		PlayerButtom = new JPanel(new BorderLayout());

		listPane = new JScrollPane();

		invitation = new JButton("Invitate");
		invitation.addActionListener(new InvitationListener());
		leave = new JButton("leave");
		leave.addActionListener(new LeaveListener());

		ListData.getInstance().getList().setFixedCellWidth(200);
		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listPane.setViewportView(ListData.getInstance().getList());

		PlayerBody.add(listPane,BorderLayout.CENTER);
		PlayerBody.add(getOppoInfo(), BorderLayout.SOUTH);

		PlayerButtom.add(invitation, BorderLayout.WEST);
		PlayerButtom.add(leave, BorderLayout.EAST);

		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder(new EtchedBorder(), "Player", TitledBorder.CENTER, TitledBorder.TOP));

		this.add(PlayerBar, BorderLayout.NORTH);
		this.add(PlayerBody, BorderLayout.CENTER);
		this.add(PlayerButtom, BorderLayout.SOUTH);
	}
	
	public JLabel getOppoInfo()
	{
		if(oppoInfo == null)
			oppoInfo = new JLabel("opponent: null");
		return oppoInfo;
	}

}
