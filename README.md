<div align="center"> <img src="https://github.com/DM-Earth/Cabricality-Utils/blob/1.18/icon.png?raw=true" width = 250 alt="Cabricality Utils"> </div>
<p> </p>
<div align="center"> <img src="https://img.shields.io/modrinth/dt/cabricality-utils" width = 100 alt="Modrinth Downloads"> </div>
<h1 align="center"> Cabricality Utils </h1>

This is a small Minecraft mod for [Cabricality](https://github.com/DM-Earth/Cabricality) Modpack. Currently, it only supports Fabric/Quilt 1.18.2.

## Functions

- Make Trade Cards and Profession Cards easier to translate.
- Replace Ad Astra and Bits and Chisels' wrench with the Create one.
- Tweak bounding boxes of Flags from Ad Astra to match the custom models in Cabricality.
- Catch the registrations with namespace of `cabricality` and add them to vanilla datafixers.
- Move items form KubeJS to a new item group instead of that KubeJS one.

When certain necessary mods are not loaded:

- Stop dedicated server at startup.
- Replace client translatable texts to notify the player.

## Depends

- Ad Astra
- Bits and Chisels
- Create
- KubeJS

## Mixins

*The package names in `net.minecraft` are from **Yarn Mappings**.*

### With Changes

- `com.github.alexnijjar.ad_astra.blocks.flags.FlagBlock`
- `com.simibubi.create.content.contraptions.wrench.WrenchItem`
- `dev.latvian.mods.kubejs.KubeJS`
- `net.minecraft.util.registry.Registry`
- `net.minecraft.datafixer.Schemas`
- `net.minecraft.text.TranslatableText`

### Without Changes

- `net.minecraft.util.registry.SimpleRegistry`
