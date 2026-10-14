# Progression and balance notes

These are documented decisions from past development. **Exact recipes and numeric armor values must be checked against the imported KubeJS/scripts before recording them as current.**

## Gating

- Explore, fight bosses and visit dimensions for late-game magic. Rich mining doesn't need further nerfs just because spells, mining constructs, or automated mining exist.
- Botania flowers gated into Twilight Forest, with Floral Fertilizer and related recipes adjusted.
- Ice and Fire Dragonsteel tied to Botania **Manasteel**, Dragonforge progression to Cataclysm **Witherite ingots**.
- Vampire ascension uses the **Ancient Remnant** progression gate; Werewolves, Vampirism and other combat systems coordinated.
- Cataclysm boss buffs and/or progression changes are pack-specific; tests should check encounter difficulty and drops.

## Mana & Artifice

- Tier-5 armor stats rebalanced in **KubeJS** (faction-specific). Eldritch target defense lies **between demon and undead**; not a generic upstream mod change.
- Ritual and spell power is intended to feel substantial, especially at high tier. Preserve faction diversity and allow creative affinity choices.
- Pouch ritual relabeling is now available via separate addon source, but remains integrated in Eldritch Artifice. **Never install both patches at the same time without an explicit compatibility fix.**

## Other scripts/compatibility

- Dragon restraint escape targets abuse of permanent, trivializing immobilization while retaining tactical utility.
- Silver recipes/ingredient unification exist for some earlier mods; audit whether Occultism is incorporated and preserve its chalk progression and Iesnium.
- Recipes should be tested with JEI for conflicts, especially with Polymorph.

## Future audit when importing source

Record individual recipe IDs, before/after ingredients, entity attributes, config keys, patch versions, test cases, and affected dimensions. Those details should come from **actual project files**, not inferred from this design summary.
