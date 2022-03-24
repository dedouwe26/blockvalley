package rxs.dedouwe.mcplugins.blockvalley.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import rxs.dedouwe.mcplugins.blockvalley.rankings.Rankings; 

public class Api extends HttpServlet implements CommandExecutor{
	private static String pw;
	private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String OTHER_PUNCTUATION = "!@#&()–[{}]:;',?/*~$^+=<>";
    
	public static void makePw() {
		SecureRandom random = new SecureRandom();
		String joined = String.join(CHAR_UPPERCASE, DIGIT,OTHER_PUNCTUATION,CHAR_LOWERCASE);
		for (int a=0; a<= 20; a++) {
			pw+=joined.charAt(random.nextInt(joined.length()-1));
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json;
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) { e.printStackTrace(); }

		try {
			JSONParser parser = new JSONParser(); 
			json = (JSONObject) parser.parse(jb.toString());
		} catch (Exception e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}
		if (!json.get("password").equals(pw)) {
			PrintWriter p = response.getWriter();
			HashMap<String, String> b = new HashMap<String, String>();
			b.put("task", "wrong password");
			JSONObject a = new JSONObject(b);

			p.print(a.toString());
			p.close();
			return;
			}
		
		Rankings.setRank(UUID.fromString((String) json.get("player")), (Short) json.get("rank"));	
		
	}
	
	
	private static final long serialVersionUID = 1L;


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (sender instanceof ConsoleCommandSender) 
			if (lbl.equalsIgnoreCase("getapipw")) {
				Bukkit.getConsoleSender().sendMessage(pw);
			}
		
		return false;
	}


}
