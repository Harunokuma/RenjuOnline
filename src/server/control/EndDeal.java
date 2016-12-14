package server.control;

import server.model.*;

public class EndDeal
{
	public void clientOff(int id)
	{
		HashMapData HMP = HashMapData.getInstance();
		
		if(HMP.getMatchs().containsKey(id))		//如果已经匹配成功
		{
			int oppoId = HMP.getMatchs().get(id);
			
			if(HMP.getGameDatas().containsKey(id))	//如果已经生成了游戏数据
			{
				if(HMP.getGameDatas().containsKey(oppoId))	//如果对手也生成了游戏数据
					HMP.getGameDatas().remove(oppoId);
				
				HMP.getGameDatas().remove(id);
			}
			
			HMP.getMatchs().remove(id);
		}
		
		if(HMP.getMatching().containsKey(id))	//如果正在匹配
			HMP.getMatching().remove(id);
		
		new Action().removeClient(id);
		MessageData.getInstance().addMessage("Client " + id + " is offline");
		
		HMP.removeClient(id);
	}
}
