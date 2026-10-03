# ToA Helper

Tombs of Amascut encounter overlays, built against RuneLite **1.13.1** (the stable release reported by RuneLite's Maven repository on 2026-10-03).

## Build

Install a JDK (Java 21 recommended) and Maven 3.9+, then run:

```sh
mvn clean verify
```

The plugin JAR is `target/toahelper-1.4.2.jar`. RuneLite dependencies are provided by the client and are not bundled into the JAR. The old `releases/toaHelp-1.4.1.jar` is a historical binary, not the updated build.

To check another RuneLite release, use `mvn clean verify -Drunelite.version=1.13.2-SNAPSHOT`.

## Development client

Import the Maven project into an IDE and run `net.runelite.client.plugins.toa.ToaPluginLauncher` with the test classpath. Enable **Tombs** in the plugin panel. A stock RuneLite installation does not load arbitrary JARs simply by copying them into its directory; use a development client or a compatible external plugin loader.

## Compatibility

Overlays use RuneLite's public APIs. Prayer widgets reference generated `InterfaceID.Prayerbook` constants, NPC overhead icons use the NPC overhead archive/sprite APIs, and Kephri's effect detection supports multiple simultaneous spot animations. Startup no longer opens a blocking revision-warning dialog.

The optional SkylerMiner/Ethan packet integrations rely on third-party plugins not included here. Their existing revision-213 guard remains in place; automatic prayer/equipment actions are not verified for the current game revision. Updating those providers requires their current source/API. This build verifies compilation and unit tests; live raid behavior still needs testing in a RuneLite client.

Community: https://discord.gg/PqGDasuzR4
