package rxs.dedouwe.mcplugins.blockvalley;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	private static File configFile;
	private static FileConfiguration createConfig(String name, File file) {
		if (!(file.exists())) {file.mkdir();}
    	configFile = new File(file, name+".yml");
    	if(!configFile.exists()) {
    		try {
				configFile.createNewFile();
			} catch (IOException e) {
			}
    	}
    	FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
    	return cfg;
    }
	public static void saveToFiles(HashMap<UUID, Short> players, File file) {
		players.forEach((id,rank)->{
			FileConfiguration a =createConfig(id.toString(),file);
			a.set("rank",Short.toString(rank));
			try {
				a.save(configFile);
			} catch (IOException e) {
			}
			
		});
	}
	public static HashMap<UUID,Short> loadFromFiles(File file) {
		HashMap<UUID,Short> outcome = new HashMap<>();
		for (File data : file.listFiles()) {
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(data);
			outcome.put(UUID.fromString(data.getName().replace(".yml", "")), (Short) cfg.get("rank"));
		}
		return outcome;
		
	}
	public static int getServerId(File f) {
		configFile = new File(f, "config.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
		return (int) cfg.get("id");
	}
}