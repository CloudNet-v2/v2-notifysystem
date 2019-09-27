package eu.cloudnetservice.notify.plugin;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudProxy;
import eu.cloudnetservice.notify.plugin.network.NotifyNetworkHandler;
import net.md_5.bungee.api.plugin.Plugin;

public class ProjectMain extends Plugin {

	@Override
	public void onEnable() {
		CloudAPI.getInstance().getNetworkHandlerProvider().registerHandler(new NotifyNetworkHandler());
	}
}
