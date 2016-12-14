package client.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.control.QuitListener;
import client.control.RestartListener;

public class OperationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton restart = null;
	private JButton quit = null;
	
	public OperationPanel(){
		restart = new JButton("Restart");
		restart.addActionListener(new RestartListener());
		quit = new JButton("Quit");
		quit.addActionListener(new QuitListener());
		
		this.setLayout(new BorderLayout());
		this.add(restart, BorderLayout.WEST);
		this.add(quit, BorderLayout.EAST);
	}
}
