package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import client.model.GameData;
import client.model.ListData;
import client.model.NetData;
import client.model.MessageData;

public class InvitationListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (GameData.getInstance().getOppoId() == 0)
		{
			if (ListData.getInstance().getList().isSelectionEmpty() == false)
			{
				JList<String> list = ListData.getInstance().getList();

				String info = list.getSelectedValue();
				String[] split = info.split("-");
				String oppoId = split[0];

				int oppo = Integer.parseInt(oppoId);

				if (oppo != GameData.getInstance().getId())
				{
					MessageData.getInstance().addMessage("Waiting for other player receving your invitation...");
					NetData.getInstance().getPrintStream().println("OPER:" + "INVI:" + oppo);
				} else
					JOptionPane.showMessageDialog(null, "You can't invitate yourself!");

			} else
				JOptionPane.showMessageDialog(null, "You should select a opponent!");
		} else
			MessageData.getInstance().addMessage("You have already joined a game!");
	}
}
