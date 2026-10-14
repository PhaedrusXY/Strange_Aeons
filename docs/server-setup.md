# Dedicated server operations

**Baseline:** Minecraft 1.20.1 / Forge 47.4.5. Server, client, and world data must be backed up prior to destructive upgrades.

## Distribution and synchronization

- Install required gameplay mods and both-side libraries in client and server as applicable. This includes **Occultism**, **SmartBrainLib**, and **Modonomicon** at matching versions.
- Keep the server's `config/`, `defaultconfigs/`, `kubejs/`, and datapack resources aligned with the intended release. Personal client display settings and shaderpacks don't belong on the server.
- Ensure `config/iceandfire-client.toml` client-menu setting is applied **on clients**; it's unrelated to server startup.
- Use one Forge instance per port/world. Start the servers **one at a time** during troubleshooting to avoid excess competing load.
- Allow access to server console and log files; have a known pre-change world backup.

## Performance and startup

- A crash report beginning with “SINYTRA CONNECTOR IS PRESENT!” does not prove Connector is faulty.
- A warning `invalid dist DEDICATED_SERVER` can come from optional client-target mixins and may be nonfatal.
- A 60-second `ServerHangWatchdog` message may be **secondary** to an earlier fatal exception. Check `latest.log` immediately before shutdown; October 2026 incident came from **Polymorph 0.49.8**.
- Only temporarily use `max-tick-time=180000` to diagnose long loads. Don't permanently suppress actual hangs.
- Spark can remain installed server-side for diagnostics, independent of its inclusion in CurseForge client exports.
- Keep a single current JAR per mod ID; folder copy operations can leave renamed duplicates.

## Customizations to verify

- Twilight Tweaks Void Worm finale and its functions/data location. Check `enable-command-block=true` where required in this setup.
- KubeJS `sa_escape_spin.js` v0.1.12 performance behavior around wild dragons.
- Mana & Artifice + Eldritch Artifice network, faction armor, spell cache guard.
- Occultism and supporting libraries.
- Questlog, recipes, and data updates on existing worlds.

## Folder safety

Never overwrite or publish `world/`, `backups/`, player data, server owner settings, `server.properties` secrets, passwords, local performance logs, or Xaero personal waypoints when importing source. Copy/merge **mods/config/kubejs** deliberately, comparing full JAR mod IDs rather than just filenames.

For release verification, see [release checklist](release-checklist.md) and [testing](testing.md).
