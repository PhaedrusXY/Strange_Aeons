# Roadmap

_Initialized 2026-10-13. Priority/target versions are tentative; no unscheduled feature should be described as shipped._

## Issue dashboard

| Track | Active issues |
| --- | --- |
| Next release and maintenance | [Polymorph update #1](https://github.com/PhaedrusXY/Strange_Aeons/issues/1), [duplicate JAR cleanup #2](https://github.com/PhaedrusXY/Strange_Aeons/issues/2), [v2.5 export verification #3](https://github.com/PhaedrusXY/Strange_Aeons/issues/3), [ProbeJS/export cleanup #9](https://github.com/PhaedrusXY/Strange_Aeons/issues/9), [public pack name/changelog #15](https://github.com/PhaedrusXY/Strange_Aeons/issues/15) |
| Tests | [Champions + Apotheosis #17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17), [M&A addon updates #18](https://github.com/PhaedrusXY/Strange_Aeons/issues/18), [Void Worm #4](https://github.com/PhaedrusXY/Strange_Aeons/issues/4), [Dragon Escape Spin #5](https://github.com/PhaedrusXY/Strange_Aeons/issues/5), [M&A armor #11](https://github.com/PhaedrusXY/Strange_Aeons/issues/11), [Occultism gameplay #12](https://github.com/PhaedrusXY/Strange_Aeons/issues/12), [pouch-label compatibility #16](https://github.com/PhaedrusXY/Strange_Aeons/issues/16) |
| Integration and research | [Occultism storage #7](https://github.com/PhaedrusXY/Strange_Aeons/issues/7), [Fabric/Connector dependency audit #8](https://github.com/PhaedrusXY/Strange_Aeons/issues/8) |
| Source and technical debt | [Import actual source #10](https://github.com/PhaedrusXY/Strange_Aeons/issues/10), [obsolete scripts/config #13](https://github.com/PhaedrusXY/Strange_Aeons/issues/13) |
| Future ideas | [Azathoth/Limbo triggered soundscape #14](https://github.com/PhaedrusXY/Strange_Aeons/issues/14) |

These entries are **not** a completed-work checklist; they link to open issues with the test criteria and decision background. Close issues only on verified completion.

## Current working-instance test batch — October 16, 2026

**Single-player boot succeeded; dedicated-server deployment/testing in progress, not yet confirmed.** No new CurseForge release number or verified exact mod versions have been supplied.

- **Added:** Champions and Apotheosis to the working instance. Actual Champions elite spawns, enchanted rewards, empowered Wither/boss behavior, and **Apotheosis Adventure / affix / gem module configuration** still need verification. [#17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17)
- **Updated:** MNA More Structures, MNA Integrated Adventures, and One Mana Bar to Rule Them All. Need dedicated server join, new-chunk worldgen, M&A integration, mana HUD, and save-compatibility checks. [#18](https://github.com/PhaedrusXY/Strange_Aeons/issues/18)
- **Next checkpoint:** server launch and client join, then targeted in-game tests. Do not mark any of this as published solely because SP loads.

## Next maintenance release

- **Update Polymorph** from 0.49.8 to a corrected 1.20.1 Forge build (target 0.49.11) on clients **and** servers. A confirmed upstream startup NPE in 0.49.8 has a fix in 0.49.9; subsequent concurrency fixes in 0.49.10. Back up worlds before updates.
- Remove outdated duplicate JARs from old instances (Astral Dimension 1.8.1; Macaw's Windows 2.3.0; Xaero's Minimap 25.1.0). Preserve newer versions. Clean up optional ProbeJS, WorldEdit, and personal graphics files from **public exports**, keeping Spark available server-side if desired.
- Confirm the Void Worm finale and Dragon Escape Spin work on a real dedicated server for the latest version, including server permission/configuration and performance.

## Recently resolved

- [Occultism silver #6](https://github.com/PhaedrusXY/Strange_Aeons/issues/6): user confirmed existing **two-way silver conversion recipes**, so additional KubeJS unification is unnecessary unless a specific incompatibility is found. No exhaustive tag/ore audit claimed.

## Gameplay/integration exploration

- Evaluate Occultism's dimensional storage as a thematic alternative to **Tom's Simple Storage**. Don't remove Tom's until reliability, chest/drawer interoperability, recovery, and server performance are demonstrated.
- Investigate if **Forgified Fabric API**, **Sinytra Connector**, and **Connector Extras** remain necessary. Do not remove without identifying affected Fabric-side mods, especially BetterEnd and its dependencies, and testing server/client startup.
- Review botania/faction/dragonsteel progression and cross-mod gates only where gameplay testing identifies regressions. Mining shortcuts are not themselves a concern; progression rewards need not be mineable.
- The functionality of the standalone [Mana & Artifice Ritual Pouch Labels](https://github.com/PhaedrusXY/Mana-and-Artifice-Ritual-Pouch-Labels) is currently bundled into the Eldritch Artifice mod. Consider splitting these (mod development, but pack related).

## Further development (not scheduled)

- More complete Eldritch Artifice gameplay and faction presentation (its own issue tracker remains canonical for **mod code**).
- Atmospheric **Azathoth/Limbo audio**: rare triggered ambient layers, dissonant flutes plus choral murmuring, not permanent music activated by high Fray.
- Polish quests, lore, encounter gating, and major boss encounters after current gameplay testing.
- Evaluate any new mod individually for cross-mod value, aesthetic fit, performance and compatibility before committing to a release.

## Status language

- **Implemented:** code or configuration exists; not necessarily tested in every environment.
- **Verified:** tested in-game on specified instance/build; server verification is separate.
- **Staged:** included in a local export; may not have been uploaded/published.
- **Released:** confirmed live CurseForge distribution.
- **Proposed:** no implementation implied.

Specific actionable items should have a corresponding [GitHub Issue](https://github.com/PhaedrusXY/Strange_Aeons/issues). Closed issues should record outcome and test evidence, not just “done”.
