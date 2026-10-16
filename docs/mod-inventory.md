# Mod inventory (selected, not exhaustive)

This is a **working index**, not a substitute for a verified CurseForge manifest. The inspected v2.5 export had **147** CurseForge-managed files. Full versions/IDs must be generated from the newest export before treating any inventory as authoritative.

| Area | Relevant content / libraries |
| --- | --- |
| Primary magic | Mana & Artifice 3.1.11; MagiChem; Botania; Iron's Spells 'n Spellbooks; Malum |
| M&A expansions | Eldritch Artifice; Arcane Adornment; Arcane Additions; **MNA Integrated Adventures (update reported Oct 16)**; **MNA More Structures (update reported Oct 16)**; Factions and Curiosities; other M&A addons |
| Other ritual systems | Occultism; Modonomicon; SmartBrainLib; Occultism KubeJS; Occultism Ritual Satchel |
| Dimensions/world | Twilight Forest; Dimensional Doors 5.4.4; BetterEnd; Astral Dimension; The Graveyard |
| Bosses and creatures | Ice and Fire; Alex's Mobs; Cataclysm; Vampirism/Werewolves; **Champions (added to working instance Oct 16)** |
| Storage | Tom's Simple Storage; Storage Drawers; Sophisticated Backpacks |
| Recipes/automation | KubeJS, Rhino, LootJS, CraftTweaker/JEI integrations |
| UI/map | FancyMenu, Xaero maps/minimap, Questlog, JEI; **One Mana Bar to Rule Them All (update reported Oct 16)** |
| Enchanting / spawners / potions | **Apotheosis (added to working instance Oct 16; module switches unverified)** |
| Compatibility | Forgified Fabric API; Sinytra Connector; Connector Extras; ModernFix |
| Diagnostics/dev | Spark (server), ProbeJS (local development), WorldEdit (local building) — not required for public player export |

## October 16 working-instance additions/updates

These are **user-reported modifications**, not confirmation of what is in the current public CurseForge release. Combined modifications booted in single-player; server deployment was still underway at the time of reporting. Exact JAR versions and Apotheosis Adventure-module settings have not been recorded. See [#17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17) and [#18](https://github.com/PhaedrusXY/Strange_Aeons/issues/18).

## Exact inventory to import next

Import the newest `manifest.json` + `modlist.html` and validate `overrides/`. Then document each managed mod ID, version, CurseForge project ID, platform, gameplay vs client-only classification, and known dependencies. Exclude optional private client addons from the intended public manifest.

**Note:** Mods may share names or change JAR filenames between versions; compare actual mod IDs instead of only filenames.
