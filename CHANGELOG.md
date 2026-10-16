# Changelog / historical release notes

_This chronology is reconstructed from modpack development conversations and a verified v2.5 ZIP inspection. It is deliberately not a complete CurseForge release changelog. Dates below refer to development discussion/inspection, not necessarily publication dates._

## Working-instance changes — October 16, 2026 (unreleased version not specified)

**Reported installed/updated, not yet a verified public release:**
- **Added:** Champions (rare elite monsters) and Apotheosis. The proposed elite rewards and Apotheosis module choices are **not yet confirmed as configured**.
- **Updated:** MNA More Structures, MNA Integrated Adventures, and One Mana Bar to Rule Them All. Exact before/after mod versions not yet supplied.
- **Tested:** Combined changes **booted successfully in single-player** (user report).
- **Pending:** Dedicated-server startup and client join, gameplay/loot/structures/mana-HUD validation, world backup and data integrity, and confirming release version.
- Tracking: [Champions + Apotheosis #17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17), [M&A updates #18](https://github.com/PhaedrusXY/Strange_Aeons/issues/18).

## 2.5 — CurseForge upload confirmed (October 2026)

**Observed in the v2.5 export:**
- Occultism and its **SmartBrainLib**, **Modonomicon**, and other installed companion integrations.
- Eldritch Artifice; additional Mana & Artifice integrations including Arcane Adornment, Arcane Additions, MnA Integrated Adventures, Factions and Curiosities.
- A KubeJS/data-function sequence for a Twilight Forest **Alex's Mobs Void Worm** encounter; Twilight Tweaks command configured to `function tf_voidworm:start`.
- `kubejs/startup_scripts/sa_escape_spin.js` v0.1.12 performance cleanup for wild dragon escape from restraint.
- Ice and Fire's `"Custom main menu" = false`, allowing FancyMenu to run.
- User's local Aurelis/Oculus/Embeddium/shader setup **absent from the public export**.

**Cleanup changes implemented:**
- Remove development cache `kubejs/probe/` (~15 MB of compressed ZIP bulk) and exclude ProbeJS itself.
- Remove temporary operator-only `/tfworm` test script when confident the finale is verified.
- Preserve Forgified Fabric API / Connector / Connector Extras until a real dependency audit.
- Review optional resourcepack and retired mod configs.

## 2.4 — CurseForge upload confirmed (October 2026)

- User uploaded 2.4 to CurseForge.
- FancyMenu custom interface appeared missing/overridden: its assets were present but **Ice and Fire's own custom main menu** was enabled. Confirmed fix: in client `config/iceandfire-client.toml`, set `"Custom main menu" = false`.
- Work toward a staged replacement for Twilight Forest's final encounter and inclusion of additional M&A content followed.

## 2.1 — earlier development notes (August 2026)

- FancyMenu customization / bespoke title presentation.
- Botania armor rebalance in KubeJS.
- Earlier Mana & Artifice Tier-5 faction armor balancing was already part of previous work; **do not count it as newly introduced in 2.1**.
- Multiplayer/Join Server FancyMenu customization had been disabled during earlier stability testing.

## Earlier integration and progression work (dates/versions not fully reconstructed)

- Botania Mystical Flowers primarily gated into Twilight Forest, with Floral Fertilizer recipe adjustments.
- Ice and Fire dragon progression linked to other mods; Dragonsteel Manasteel feedstock and Dragonforge core Witherite gating.
- Cataclysm boss difficulty/progression, Vampirism/Werewolves cross-mod progression (including Ancient Remnant requirement for vampire ascension).
- Questlog lore, progression instructions, and early quests.
- Dimensional Doors compatibility; Carry On restrictions for problem blocks/entities.
- KubeJS balancing, silver recipes, storage options, and worldgen fixes.
- Tom's Simple Storage, Storage Drawers, sophisticated storage, and fantasy exploration structures.

## Separate repositories

- [Eldritch Artifice](https://github.com/PhaedrusXY/Eldritch_Artifice) maintains its **own mod version history**; integrated pouch labeling is in v0.12.3-alpha.
- [M&A Ritual Pouch Labels](https://github.com/PhaedrusXY/Mana-and-Artifice-Ritual-Pouch-Labels) v0.1.0-alpha is a standalone derivative. Its in-game validation is distinct from this modpack's test status.

## Maintenance policy

Add a release heading only when the version/milestone is known. Separate **implemented**, **verified**, **packaged**, and **published** claims. Preserve official CurseForge release notes as the final authority when available.
