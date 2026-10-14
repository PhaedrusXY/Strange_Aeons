# Twilight Forest finale: staged Void Worm encounter

**Design status:** Implemented in v2.5 export; full dedicated-server playtest pending.

## Rationale

- Rather than create a unique Valkyrie/dragon AI for the Twilight Forest finale, use the native **Alex's Mobs Void Worm** for a high-mobility, dramatic multi-phase boss battle.
- Preserve natural Void Worm movement, splitting, its head/segment tactics, advancements, and native loot. Do not claim extra loot mechanics without in-game confirmation.
- The boss can fly unusually high; previous testing reported a battle reaching approximately Y=450, with three boss bars after splits. Players can wait for it to return when it travels away.

## Integration observed in v2.5 export

In `config/twilighttweaks-common.toml`, the command-runner was set to:

```toml
"The command that the spawner should run: " = "function tf_voidworm:start"
```

Staged functions under `kubejs/data/tf_voidworm/functions/` include `start.mcfunction`, `begin.mcfunction`, stage/release functions and variants for execution context. The staging sequence uses text, sounds, effects, and a delay before releasing the boss; the native mob handles combat.

Representative narrative text:

> You have not conquered the Twilight Forest. You have freed what it feared.

The operator-only KubeJS `/tfworm` command is a **test convenience**, separate from the finale's function chain. Plan to remove it once testing is complete.

## Test plan

- Confirm Twilight Tweaks calls the intended `tf_voidworm:start` function at the proper encounter point.
- Dedicated-server command execution: verify `enable-command-block=true` where relevant for the integration; verify commands actually run at correct dimension/position and with permissions.
- Check timed messages/particles/sounds and exact spawn location.
- Check world/chunk unloading and player disconnect during countdown.
- Check mob difficulty, motion/teleport behavior, splits, native loot, achievement, head kill condition, and reward timing.
- Check fights with multiple players and avoid adding server lag or mob griefing that destroys the intended arena.
- Verify a fresh CurseForge install and a separately updated old server.

This is pack-side orchestration, **not** a separate custom boss mod. Only commit source functions when imported from an actual verified export.
