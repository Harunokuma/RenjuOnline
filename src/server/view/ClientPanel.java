package server.view;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClientPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	JList<Integer> clientList = null;
	DefaultListModel<Integer> model = null;
	
	public ClientPanel(){
		JScrollPane listPane = new JScrollPane();
		
		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listPane.setViewportView(this.getClientList());
		
		this.setLayout(new BorderLayout());
		this.add(listPane, BorderLayout.CENTER);
		this.setBorder(new TitledBorder(new EtchedBorder(), "Client list" ,TitledBorder.CENTER ,TitledBorder.TOP ));
	}
	
	public JList<Integer> getClientList(){
		if(clientList == null)
		{
			clientList = new JList<Integer>(this.getModel());
			clientList.setFixedCellWidth(150);
			clientList.setVisibleRowCount(10);
		}
		
		return clientList;
	}
	
	public DefaultListModel<Integer> getModel()
	{
		if(model == null)
			model = new DefaultListModel<Integer>();
		
		return model;
	}
	
	public void addClient(int id)
	{
		this.getModel().addElement(id);
	}
	
	public void removeClient(int id)
	{
		this.getModel().removeElement(id);
	}
	
}
