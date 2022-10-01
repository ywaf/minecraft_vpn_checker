package dev.leho.ipchecker;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin implements Listener {


    String apikey, vpn_disconnect_message, proxy_disconnect_message;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        System.out.println("IPCHECKER] ENABLED! Loading Plugin...");
        if (!getDataFolder().exists()) {
            this.saveDefaultConfig();
        }
        FileConfiguration config = this.getConfig();
        apikey = config.getString("api_key");
        if (apikey == null || apikey.isEmpty()) {
            System.out.println("[IPCHECKER] API key is not set! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        vpn_disconnect_message = config.getString("vpn_disconnect_message");
        proxy_disconnect_message = config.getString("proxy_disconnect_message");


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        String ip = event.getPlayer().getAddress().getAddress().getHostAddress();
        System.out.println(ip);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://vpnapi.io/api/" + ip + "?key=" + apikey)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        if (responseBody.contains("message")) {
            System.out.println("[IPCHECKER] ERROR CHECKING IP!");
        } else {
            Gson gson = new Gson();
            JsonObject object = gson.fromJson(responseBody, JsonObject.class);
            String isVPN = object.get("security").getAsJsonObject().get("vpn").getAsString();
            String isProxy = object.get("security").getAsJsonObject().get("proxy").getAsString();
            System.out.println(isVPN);
            if (isVPN.equals("true")) {
                event.getPlayer().kickPlayer(ChatColor.RED + vpn_disconnect_message);
            } else if (isProxy.equals("true")) {
                event.getPlayer().kickPlayer(ChatColor.RED + proxy_disconnect_message);
            }

        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("IPCHECKER] IPCHECKER DISABLED!");
    }
}
