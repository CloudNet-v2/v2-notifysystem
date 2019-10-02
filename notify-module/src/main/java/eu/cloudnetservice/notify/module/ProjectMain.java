package eu.cloudnetservice.notify.module;

import de.dytanic.cloudnetcore.api.CoreModule;

public class ProjectMain extends CoreModule {

    @Override
    public void onLoad() {

    }

    @Override
    public void onBootstrap() {
        getCloud().getNetworkManager().getModuleProperties().append("notifyService", getCloud().getConfig().isNotifyService());
    }
}
