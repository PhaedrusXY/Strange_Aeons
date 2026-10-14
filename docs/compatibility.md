# Compatibility & known problems

**Minecraft:** 1.20.1. **Forge:** 47.4.5 in v2.5 export / October server logs. Do not assume mod compatibility beyond this baseline.

## Polymorph intermittent startup crash

- October 2026 dedicated-server logs: `polymorph-forge-0.49.8+1.20.1.jar` threw an NPE while attaching recipe information to block entities; server then shut down and a watchdog killed a stalled tick.
- Reproduces an upstream confirmed issue [#307](https://github.com/illusivesoulworks/polymorph/issues/307). The [upstream changelog](https://raw.githubusercontent.com/illusivesoulworks/polymorph/1.20.x/CHANGELOG.md) says **0.49.9** fixes potential NPE crashes (#299, #307), **0.49.10** fixes potential concurrency crashes.
- A second startup succeeded. **Do not assume the error is harmless**: some reports mention lost block-entity contents following the failure. Back up the world before further testing/updates.
- **Planned:** update Polymorph on servers *and* clients together (target 0.49.11 for 1.20.1 Forge), then observe restarts and world integrity.
- Concurrent startup of two large Forge servers may exacerbate resource contention but **does not explain away the Polymorph exception**.

## Legacy duplicate server JARs

Previous folder copy/paste left duplicate IDs in an older server folder. Forge chose the newer versions, but remove the older copies during cleanup.

| Mod | Retain | Remove older duplicate |
| --- | --- | --- |
| Astral Dimension | `astral_dimension-forge-1.20.1-2.2.jar` | `Astral_1.8.1_1.20.1.jar` |
| Macaw's Windows | `mcw-mcwwindows-2.4.2-mc1.20.1forge.jar` | `mcw-windows-2.3.0-mc1.20.1forge.jar` |
| Xaero's Minimap | `xaerominimap-forge-1.20.1-26.1.0.jar` | `Xaeros_Minimap_25.1.0_Forge_1.20.jar` |

## FancyMenu vs Ice and Fire

Ice and Fire's custom main menu overrides FancyMenu, even if FancyMenu's custom layout files exist. Client setting in `config/iceandfire-client.toml`:

```toml
"Custom main menu" = false
```

This was confirmed to restore FancyMenu in-game. Preserve the FancyMenu layouts/assets in releases.

## Sinytra / Forgified Fabric API

Several content/library mods and Fabric compatibility APIs can make these needed. Retain **Sinytra Connector**, **Connector Extras**, and **Forgified Fabric API** until audited. Some benign `invalid dist DEDICATED_SERVER` mixin target warnings may come from this stack and Mana & Artifice; distinguish them from the *actual* exception that ends a startup.

## Other observed warnings

- Older logs mention M&A aura-query / recipe config issues. Eldritch Artifice includes a synchronized M&A 3.1.11 config-cache guard; keep this version-sensitive integration under test.
- Dimensional Doors 5.4.4 previously produced unexpected rendering packet errors near a broken rift. A general diagnosis/fix is not established.
- A missing external Patchouli book JSON (`forbidden_arcanus_guide-main`) appears in server startup warnings after Forbidden & Arcanus removal; likely stale data/config and should be cleaned only after checking use. Do not equate every warning with fatal failure.

## Operating principle

When diagnosing, retain **`logs/latest.log`**, **`logs/debug.log`**, and crash report *locally*. For public Issues, redact player IDs, addresses, authentication data, coordinates if private, and other server-specific details. Verify repeated startups before removing gameplay mods.
