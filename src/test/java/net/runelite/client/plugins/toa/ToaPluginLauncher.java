package net.runelite.client.plugins.toa;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

/** Run with the test classpath to load the plugin in a development client. */
public class ToaPluginLauncher {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(ToaPlugin.class);
        RuneLite.main(args);
    }
}
