package rxs.dedouwe.mcplugins.blockvalley;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import rxs.dedouwe.mcplugins.blockvalley.api.Api;
import rxs.dedouwe.mcplugins.blockvalley.events.Events;
import rxs.dedouwe.mcplugins.blockvalley.rankings.Rankings;


public class Blockvalley extends JavaPlugin {

	@Override
	public void onEnable() {
		Api.makePw();
		Rankings.setList(Config.loadFromFiles(new File(this.getDataFolder().getPath())));
        getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
    @Override
	public void onDisable() {
    	Config.saveToFiles(Rankings.getList(),new File(this.getDataFolder().getPath()));
	}

}