package server.control;

import server.model.*;

public class EndDeal
{
	public void clientOff(int id)
	{
		HashMapData HMP = HashMapData.getInstance();
		
		if(HMP.getMatchs().containsKey(id))		//����Ѿ�ƥ��ɹ�
		{
			int oppoId = HMP.getMatchs().get(id);
			
			if(HMP.getGameDatas().containsKey(id))	//����Ѿ���������Ϸ����
			{
				if(HMP.getGameDatas().containsKey(oppoId))	//�������Ҳ��������Ϸ����
					HMP.getGameDatas().remove(oppoId);
				
				HMP.getGameDatas().remove(id);
			}
			
			HMP.getMatchs().remove(id);
		}
		
		if(HMP.getMatching().containsKey(id))	//�������ƥ��
			HMP.getMatching().remove(id);
		
		new Action().removeClient(id);
		MessageData.getInstance().addMessage("Client " + id + " is offline");
		
		HMP.removeClient(id);
	}
}
