package rxs.dedouwe.mcplugins.blockvalley;

import org.bukkit.plugin.java.JavaPlugin;

import rxs.dedouwe.mcplugins.blockvalley.events.Events;


public class Blockvalley extends JavaPlugin {

	@Override
	public void onEnable() {

        getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
    @Override
	public void onDisable() {
		
	}

}