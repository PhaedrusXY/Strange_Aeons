# CurseForge release checklist

Do not call an export “released” until the uploaded public version and downloadable manifest are verified.

## Before export

- [ ] Select intended Minecraft/Forge/pack version and compare expected mod IDs/versions.
- [ ] Check required dependencies on **both sides**; pay attention to Occultism/SmartBrainLib/Modonomicon and M&A/Eldritch Artifice.
- [ ] Verify there are no duplicate mod IDs under renamed JAR files.
- [ ] Check no accidental standalone pouch-label JAR alongside Eldritch Artifice's bundled pouch label feature.
- [ ] Preserve `config/`, `defaultconfigs/`, `kubejs/`, `kubejs/data/`, and FancyMenu layouts/assets.
- [ ] Verify client `config/iceandfire-client.toml` `"Custom main menu" = false`.
- [ ] Exclude local shaders, Aurelis, Oculus, Embeddium, personal waypoints/worldmaps, worlds, backups and logs.
- [ ] Exclude `kubejs/probe/` auto-generated ProbeJS typings and optional development tools (ProbeJS, WorldEdit, Spark) from the public player export.
- [ ] Don't remove Sinytra/Connector Extras/Forgified Fabric API until a completed compatibility audit.
- [ ] Update changelog, planned changes, and decisions against code actually included.

## Verify exported ZIP

- [ ] `manifest.json` has expected Forge version and mod files; no unexpected client graphics mods.
- [ ] Overrides include necessary KubeJS, FancyMenu, resources/configs and Twilight boss functions.
- [ ] Detect obsolete material; do not package unrelated generated caches.
- [ ] No secrets, IPs, identities, player data, or proprietary third-party JARs committed to GitHub.

## Smoke tests

- [ ] New CurseForge profile launches; custom FancyMenu loads.
- [ ] New world can start.
- [ ] Old world upgrades from a **backup**, and critical items/data are intact.
- [ ] Dedicated server boots, closes cleanly, boots again; at least one actual client joins.
- [ ] Sample M&A spell/ritual, KubeJS recipe, inventory/crafting and Occultism interaction.
- [ ] Void Worm finale and dragon escape behavior in suitable test environments.
- [ ] Check `latest.log`/crash logs for new actual exceptions; don't overreact to nonfatal mixin warnings.

## Release

- [ ] Upload and verify public CurseForge file listing for the intended version.
- [ ] Capture publication date, release link, manifest version and tested environment here.
- [ ] Close or update corresponding GitHub issues only when evidence supports completion.
