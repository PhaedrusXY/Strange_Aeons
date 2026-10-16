# Decision log

The “why” behind changes. Entries are **historical decisions**, **working choices**, or **open questions**; do not infer that every idea has been implemented.

## D-001 — Exploration and cross-mod progression before easier mining
**Status:** Accepted philosophy.

Not every valuable resource should be mineable: e.g., Dragonsteel and boss-gated drops. Automating normal mining (such as construct miners) is acceptable if it doesn't bypass the designed progression gates. Structures, special encounters, and magic provide their own exploration incentives.

## D-002 — Dragonsteel integration and Dragonforge gating
**Status:** Historical implemented design; re-check current recipes when importing scripts.

Use **Botania Manasteel** in Dragonsteel feedstock. Dragonforge core recipes were ultimately returned to consuming **Cataclysm Witherite ingots**. An earlier idea used the reusable Ender Guardian Gauntlet of Guard; that choice was **reverted**.

## D-003 — Botania Mystical Flowers in the Twilight Forest
**Status:** Historical implemented design; scripts to be imported.

Position Botania progression as an incentive to visit the Twilight Forest. Restrict common Mystical Flower availability there and adjust related recipes such as Floral Fertilizer. Preserve coherent cross-mod crafting.

## D-004 — Balance Mana & Artifice armor in the pack, not Eldritch Artifice
**Status:** Accepted.

KubeJS `mna_faction_armor_balance.js` implements pack-specific faction balance, including stronger Tier-5 armor. The Eldritch armor target is between demon and undead armor in defenses. An upstream armor PR was deliberately **not merged**: don't silently move pack tuning into the custom mod.

## D-005 — Twilight Forest finale uses an existing Void Worm
**Status:** Implemented staging; dedicated-server verification pending.

Prefer a compatible, spectacular boss over maintaining a new bespoke aerial boss AI. The **Alex's Mobs Void Worm** is staged through Twilight Tweaks and Minecraft functions to appear as a late Twilight Forest consequence. The encounter preserves native attack AI, splitting mechanics, loot, and advancements. Avoid confusing it with the optional operator-only `/tfworm` test command.

Earlier proposed Valkyrie/Grimoire of Gaia and Mo'Bosses replacements are **not the chosen plan**.

## D-006 — Dragon restraint escape without permanent scanning
**Status:** KubeJS v0.1.12 implemented, live-load/performance validation ongoing.

Wild Ice and Fire dragons should not be permanently neutralized by cobwebs or M&A restraint tricks. Use delayed checks, targeting/tamed safeguards, throttling/cooldowns, and event-driven logic rather than constant spam. Preserve ordinary player restraint effects where appropriate.

## D-007 — FancyMenu takes precedence over Ice and Fire's main menu
**Status:** Confirmed fix.

Use the custom FancyMenu title screen. On clients set `config/iceandfire-client.toml` `"Custom main menu" = false`. FancyMenu asset files were present in v2.4; apparent missing layout was a menu override, not absent files.

## D-008 — Keep the public pack independent of personal graphics
**Status:** Accepted.

Aurelis resource pack, shaders, Oculus and Embeddium are part of a personal client setup, **not the public CurseForge export**. Similarly, exclude ProbeJS-generated `kubejs/probe` files. WorldEdit and ProbeJS are development tools, not gameplay dependencies. Spark can remain on the dedicated server for profiling.

## D-009 — Keep Fabric compatibility bridge pending investigation
**Status:** Provisional; verify concrete dependencies.

Forgified Fabric API + Sinytra Connector + Connector Extras are currently retained because some content may rely on them. Remove only after identifying Fabric consumers and running clean client and dedicated-server startup tests.

## D-010 — Occultism without deleting its distinct mechanics
**Status:** Existing reciprocal silver conversion recipes confirmed by player (October 14, 2026); issue #6 closed without adding KubeJS recipes.

Occultism fits the ritual/mysticism theme. The pack already supports converting **Occultism silver to other silver forms and back**, making new cross-mod silver unification unnecessary for now. Preserve functionally distinct Occultism ritual chalk, unique Iesnium, and Occultism rituals. Mining automation isn't a disqualifier. Revisit only when an actual conversion gap is identified; no exhaustive tag/ore audit has been completed.

## D-011 — Storage: reliability and aesthetic fit first
**Status:** Tom's Simple Storage retained; alternatives under evaluation.

Need cross-chest/drawer search and strong recoverability, stable multiplayer, and low lag. Occultism dimensional storage may better match tone, but Tom's remains until a supported, thoroughly tested alternative is demonstrably usable.

## D-012 — Keep the standalone pouch-label mod separate
**Status:** Accepted.

The independent [M&A Ritual Pouch Labels](https://github.com/PhaedrusXY/Mana-and-Artifice-Ritual-Pouch-Labels) is for people who want saved pouch ritual names without Eldritch Artifice. Its original NBT key is preserved for existing saved names. Do not distribute/load both implementations in one environment until compatibility is intentionally addressed.

## D-013 — Eldritch soundscape should be event-triggered
**Status:** Proposed design, not shipped.

In Limbo or discrete supernatural events, layer uncanny flute/piping sounds with voices or choir and ambient texture. **Do not** impose perpetual music simply because a character has high Fray; respect players who disable music.

## D-014 — Pack tracking and release discipline
**Status:** Accepted October 2026.

GitHub issues carry active work; this repo records reasons, configurations and scripts when imported. CurseForge distributes dependencies as a manifest, not as checked-in third-party JARs. A successful local run is not proof of dedicated server compatibility; a ZIP inspection is not proof of public release.

## D-015 — Rare raid-like threats, not mandatory solo balance
**Status:** Accepted design philosophy, October 16, 2026. [Issue #17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17) tracks the immediate implementation proposal.

The player fondly recalls 30+-person MMO raid bosses, Valheim's punishing fights, and Minecraft boss-mod combinations such as unexpectedly empowered Withers. Some favorite experiences involved a group repeatedly dying, regrouping and strategizing. **Preserve very rare, exceptionally dangerous encounters that may require teamwork and multiple attempts**. Solo accessibility is not an absolute balance rule, and major bosses should not be categorically excluded from random elite modifiers. Keep regular exploration playable; exceptional challenges should stay exceptional.

**Unfair is not necessarily broken:** repeated deaths and tactical withdrawal may be desirable. Bugs, effectively invulnerable combinations, unrecoverable/despawning targets, broken advancement/reward conditions, destructive server lag and crashes are not. Evaluate specific dangerous modifiers empirically before excluding them. The player's MMO raids are a reference for encounter feel, not a plan to require 30 Minecraft players in one fight.

## D-016 — Use Champions for rare elite encounters; disable Apotheosis Adventure
**Status:** Selected configuration approach, October 16, 2026; deployment and server validation pending. [Issue #17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17).

Retain Apotheosis for useful independent modules (enchanting, spawners, potions, village/garden as desired), but turn off **Enable Adventure Module** in `config/apotheosis/apotheosis.cfg` on both client and server. Do not try to zero numerous Adventure loot probabilities merely to keep its native named bosses: the boss code guarantees affixed item drops independently of those rates. This decision deliberately removes **Apotheosis's boss system** as well as its equipment affixes, gems and Adventure content; **Champions** is responsible for occasional powerful enemies and conventional enchanted gear rewards. Champions' combat-ability affixes on mobs are not equipment affixes. Preserve the possibility of extremely difficult encounters and empowered major bosses unless a specific tested bug requires exclusion. This is a **decision, not evidence that configuration is already applied**.
