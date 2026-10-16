# Strange Aeons — Reborn

A dark magic, high fantasy, exploration-focused Minecraft modpack for **Minecraft 1.20.1 / Forge 47.4.5**, managed and distributed through CurseForge.

This repository is the **development record** for the modpack: implementation notes, tracked issues, balance/design decisions, changelog, and eventually the curated configuration, KubeJS, and datapack files. It is **not** a complete standalone modpack download.

> **Repository initialized 2026-10-13.** The notes below reconstruct earlier work and may contain items requiring verification against the installed pack.
> 
## Design priorities

- Routine exploration should be harsh but playable; **rare extraordinary enemies may be punishing, raid-like group encounters** that need planning, teamwork, and repeated attempts.
- Advancement options should be powerful and entertaining, with interesting factions to join and reasons to experiment.
- Advancement depends on **exploration, bosses, ritual systems, and cross-mod materials**, not just digging for increasingly valuable ores.
- Connect large mods into a coherent world without trivializing their unique mechanics.
- Keep performance and reliability high, especially on dedicated servers; distinguish extremely dangerous fights from broken, unwinnable or lag-inducing encounters, and prioritize reversible, testable changes.
- Preserve a mystical/sword-and-sorcery aesthetic rather than industrializing every system.

## Key systems (see [decision log](DECISIONS.md))

- **Mana & Artifice 3.1.11**, with faction armor balancing primarily in KubeJS.
- **Eldritch Artifice**, a separately developed custom M&A / Dimensional Doors addon. See [its repository](https://github.com/PhaedrusXY/Eldritch_Artifice).
- **Twilight Forest** progression and a staged, native **Alex's Mobs Void Worm** finale built with functions and Twilight Tweaks; test and balance work remains.
- **Ice and Fire** dragons and Dragonsteel rebalanced around **Botania Manasteel** and **Cataclysm Witherite**.
- **Botania** Mystical Flowers constrained to the Twilight Forest; compatible armor and recipe rebalance.
- **Vampirism / Werewolves**, **Cataclysm**, **Occultism**, and other fantasy content linked by curated progression and recipes.
- **FancyMenu** custom UI; disable Ice and Fire's competing custom main menu on clients.
- **KubeJS** for pack-specific recipes, behavior, balance, and dimension/boss orchestration.

## Navigation

| Document | Purpose |
| --- | --- |
| [Roadmap](ROADMAP.md) | What is next, under evaluation, or awaiting verification |
| [Changelog](CHANGELOG.md) | Changes by release/staging milestone, without inventing release dates |
| [Decisions](DECISIONS.md) | Why design and technical choices were made |
| [Compatibility](docs/compatibility.md) | Known issues, workarounds, and version-sensitive mod interactions |
| [Balance](docs/balance.md) | Gameplay/progression rules and known balance adjustments |
| [Twilight finale](docs/twilight-finale.md) | Void Worm encounter design and staging |
| [Server setup](docs/server-setup.md) | Dedicated server requirements and troubleshooting |
| [Release checklist](docs/release-checklist.md) | Repeatable CurseForge export and release checks |
| [Mod inventory](docs/mod-inventory.md) | Selected mod list and verification notes |
| [Testing](docs/testing.md) | High-risk functionality to verify in-game |
| [GitHub Issues](https://github.com/PhaedrusXY/Strange_Aeons/issues) | Concrete bugs, plans, experiments and follow-ups |

## Current development status

On **October 16, 2026**, the working instance was updated with **Champions and Apotheosis** and newer **MNA More Structures**, **MNA Integrated Adventures**, and **One Mana Bar to Rule Them All**. **Single-player boot succeeded**; dedicated-server testing was still underway. Configuration choices, exact versions, and release publication are not verified yet. See [#17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17) and [#18](https://github.com/PhaedrusXY/Strange_Aeons/issues/18).

## Source files and boundaries

Initially this repository contains **documentation and issues only**. The following directories will be imported from a verified pack snapshot when provided:

```text
kubejs/
config/                 # curated changes only
defaultconfigs/
```

Pack source must not include third-party `mods/*.jar`, world saves, player data, backup archives, logs/crash reports, private server settings, personal Xaero waypoints, large generated `kubejs/probe/` output, or locally installed shader/resource packs. CurseForge's manifest is the authority for distributed mod JARs.

Personal client enhancements such as **Aurelis**, **Oculus**, **Embeddium** and shaders are not part of the public pack export. Development-only **ProbeJS** and **WorldEdit** are also excluded in releases; **Spark** may remain installed on the server for profiling, not as a required player mod. However, all of these have been used successfully in the modpack and can be manually added if desired.

## Maintenance convention

Use a **GitHub Issue** for every planned change, compatibility concern, or test requiring follow-up. When a change is implemented, update the issue and corresponding design note/changelog. **Implemented** is not the same as **tested**, and **exported** is not the same as **published**.

The original pack was first developed for Minecraft v 1.12, and has gone through several revisions and name changes. For the 1.18 update, it was called “Stranger Aeons” and for 1.20.1 it is now “Stranger Aeons Reborn”. It has been mostly done for the Forge launcher, but there was one Fabric version for 1.18.
