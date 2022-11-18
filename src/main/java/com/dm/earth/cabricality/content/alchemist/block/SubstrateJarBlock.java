package com.dm.earth.cabricality.content.alchemist.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.content.alchemist.substrate.Substrate;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.core.ISettingableBlockItem;

import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public abstract class SubstrateJarBlock extends JarBlock implements ISettingableBlockItem {
	public SubstrateJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public String getTranslationKey() {
		if (this.getContent() == null)
			return new TranslatableText(CabricalityClient.genTranslationKey("block", this.getDefaultBlockId().getPath())).getString();
		return new TranslatableText(CabricalityClient.genTranslationKey("block", this.getDefaultBlockId().getPath())).getString() + " - " + this.getContent();
	}

	@Nullable
	public String getContent() {
		if (this.getSubstrate() == null) return null;
		return new TranslatableText(CabricalityClient.genTranslationKey(this.getSubstrate().getType(), this.getSubstrate().getId().getPath())).getString();
	}

	@Nullable
	public abstract Substrate getSubstrate();

	@NotNull
	public abstract Identifier getDefaultBlockId();

	@Override
	public abstract Identifier getBlockModelId();

	@Override
	public Item.Settings getSettings() {
		return CabfItems.Properties.JAR;
	}
}
