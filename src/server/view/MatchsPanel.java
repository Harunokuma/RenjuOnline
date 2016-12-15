package server.view;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import server.model.HashMapData;

public class MatchsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	JList<String> matchsList = null;
	DefaultListModel<String> model = null;

	public MatchsPanel()
	{

		JScrollPane listPane = new JScrollPane();

		listPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listPane.setViewportView(this.getMatchsList());

		this.setLayout(new BorderLayout());
		this.add(listPane, BorderLayout.CENTER);
		this.setBorder(new TitledBorder(new EtchedBorder(), "Match List", TitledBorder.CENTER, TitledBorder.TOP));

	}

	public JList<String> getMatchsList()
	{
		if (matchsList == null)
		{
			matchsList = new JList<String>(getModel());
			matchsList.setFixedCellWidth(200);
			matchsList.setVisibleRowCount(10);
		}
		return matchsList;
	}

	public DefaultListModel<String> getModel()
	{

		if (model == null)
			model = new DefaultListModel<String>();

		return model;
	}

	public void addMatchs(Integer idA, Integer idB)
	{

		this.getModel().addElement(idA + " vs " + idB);

	}

	public void removeMatchs(Integer id)
	{
		this.getModel().removeElement(id + " vs " + HashMapData.getInstance().getMatchs().get(id));
	}
}
