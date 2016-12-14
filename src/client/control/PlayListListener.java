package client.control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;

import client.model.GameData;
import client.model.ListData;
import client.model.NetData;
import server.model.MessageData;

public class PlayListListener extends MouseAdapter
{

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (GameData.getInstance().getOppoId() == 0)
		{
			if (ListData.getInstance().getList().isSelectionEmpty() == false)
			{
				if (e.getClickCount() == 2)
				{
					JList<String> list = ListData.getInstance().getList();
					int index = list.locationToIndex(e.getPoint());

					String info = list.getModel().getElementAt(index);
					String[] split = info.split("-");
					String oppoId = split[0];

					int oppo = Integer.parseInt(oppoId);

					if (oppo != GameData.getInstance().getId())
					{
						MessageData.getInstance().addMessage("Waiting for other player receving your invitation...");
						NetData.getInstance().getPrintStream().println("OPER:" + "INVI:" + oppo);
					} else
						JOptionPane.showMessageDialog(null, "You can't invitate yourself!");
				}
			} else
				JOptionPane.showMessageDialog(null, "You should select a opponent!");
		} else
			MessageData.getInstance().addMessage("You have already joined a game!");
	}
}
