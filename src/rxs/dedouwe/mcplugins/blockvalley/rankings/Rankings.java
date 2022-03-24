package rxs.dedouwe.mcplugins.blockvalley.rankings;

import java.util.HashMap;
import java.util.UUID;

public class Rankings {
	private static HashMap<UUID, Short> ranks = new HashMap<UUID, Short>();
	
	public static Short getRank(UUID id) {
		return ranks.get(id);
	}
	public static void setRank(UUID id, Short r) {
		ranks.put(id, r);
	}
	public static void setList(HashMap<UUID, Short> r) {
		ranks=r;
		
	}
	public static HashMap<UUID, Short> getList() {
		return ranks;
		
	}
}
