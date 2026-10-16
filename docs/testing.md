# Test matrix

A passing compile/ZIP inspection is not equivalent to in-game testing. Keep a short summary per item and link test evidence to a GitHub Issue.

| Feature | Known evidence | Still to verify |
| --- | --- | --- |
| FancyMenu vs Ice and Fire | Disabling Ice & Fire custom menu restored layout on client | Fresh-profile release check |
| Void Worm fight | Void Worm head/split/loot behavior observed in Twilight Forest | Full staged encounter on dedicated server; multiplayer, reloading, rewards |
| `sa_escape_spin.js` v0.1.12 | Earlier proof-of-concept verified, v0.1.12 uses performance cleanup | Live wild-dragon scenarios, cooldown behavior, profiling |
| M&A faction armor rebalance | Pack-side KubeJS script previously used | Full faction armor tooltip/damage tests incl. Eldritch |
| Eldritch Artifice pouch names | Integrated implementation tested; overwrite clears names | Cross-client/session persistence, both pouches, dedicated server |
| Standalone pouch labels | Standalone Forge build/unit tests pass in its own repository | Independent in-game test; **do not load both implementations** |
| Occultism | Mod and libraries installed, startup discovery observed | Intro rituals, storage and silver unification |
| Polymorph 0.49.8 | Intermittent NPE on startup matched known issue, second launch worked | Corrected release update, safe repeated restarts, inventory integrity |
| Champions + Apotheosis additions (Oct 16 working instance) | Combined modified instance booted successfully in SP, user-reported | Dedicated server start/join, actual elites and enchanted drops, empowered bosses, Apotheosis module configuration, version list — [#17](https://github.com/PhaedrusXY/Strange_Aeons/issues/17) |
| MNA More Structures + MNA Integrated Adventures + One Mana Bar updates | Combined modified instance booted successfully in SP, user-reported | Dedicated server start/join, new-chunk structures, recipes/integrations, HUD and M&A spell casting — [#18](https://github.com/PhaedrusXY/Strange_Aeons/issues/18) |
| Full v2.5 export | Manifest/overrides inspected | Verified CurseForge publication, clean client/server |

## Recommended process

For each feature, note Minecraft/Forge version, exact pack version/export ID, dedicated vs integrated server, steps attempted, result, and error screenshot/log excerpt. If a test changes the world irreversibly, use a disposable test world or verified backup.
