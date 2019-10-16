package eu.cloudnetservice.notify.plugin.network;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.handlers.NetworkHandler;
import de.dytanic.cloudnet.lib.CloudNetwork;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import de.dytanic.cloudnet.lib.server.info.ProxyInfo;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.dytanic.cloudnet.lib.utility.document.Document;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotifyNetworkHandler implements NetworkHandler {
    private static final Pattern COMPILE = Pattern.compile("%server%", Pattern.LITERAL);

    @Override
    public void onServerAdd(ServerInfo serverInfo) {
        sendNotifyMessage("notify-message-server-add", serverInfo);
    }

    @Override
    public void onServerInfoUpdate(ServerInfo serverInfo) {

    }

    @Override
    public void onServerRemove(ServerInfo serverInfo) {
        sendNotifyMessage("notify-message-server-remove", serverInfo);
    }

    @Override
    public void onProxyAdd(ProxyInfo proxyInfo) {

    }

    @Override
    public void onProxyInfoUpdate(ProxyInfo proxyInfo) {

    }

    @Override
    public void onProxyRemove(ProxyInfo proxyInfo) {

    }

    @Override
    public void onCloudNetworkUpdate(CloudNetwork cloudNetwork) {

    }

    @Override
    public void onCustomChannelMessageReceive(String s, String s1, Document document) {

    }

    @Override
    public void onCustomSubChannelMessageReceive(String s, String s1, Document document) {

    }

    @Override
    public void onPlayerLoginNetwork(CloudPlayer cloudPlayer) {

    }

    @Override
    public void onPlayerDisconnectNetwork(CloudPlayer cloudPlayer) {

    }

    @Override
    public void onPlayerDisconnectNetwork(UUID uuid) {

    }

    @Override
    public void onPlayerUpdate(CloudPlayer cloudPlayer) {

    }

    @Override
    public void onOfflinePlayerUpdate(OfflinePlayer offlinePlayer) {

    }

    @Override
    public void onUpdateOnlineCount(int i) {

    }

    private static void sendNotifyMessage(String key, ServerInfo serverInfo) {
        if (CloudAPI.getInstance().getModuleProperties().contains("notifyService") &&
            CloudAPI.getInstance().getModuleProperties().getBoolean("notifyService")) {
            for (ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
                if (proxiedPlayer.hasPermission("cloudnet.notify")) {
                    proxiedPlayer.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        COMPILE.matcher(CloudAPI.getInstance()
                                                .getCloudNetwork()
                                                .getMessages()
                                                .getString(key)).replaceAll(Matcher.quoteReplacement(serverInfo.getServiceId()
                                                                                                               .getServerId())))));
                }
            }
        }
    }
}
