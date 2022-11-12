package com.dm.earth.cabricality.content.alchemist.substrates.core;

import com.dm.earth.cabricality.core.IHashStringable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public abstract class AbstractSubstrate implements IHashStringable {
	private final Identifier id;
	private final int tint;

	public AbstractSubstrate(Identifier id, int tint) {
		this.id = id;
		this.tint = tint;
	}

	public Identifier getId() {
		return id;
	}

	public int getTint() {
		return tint;
	}

	public String getTranslationKey() {
		return this.getType() + "." + this.getId().getNamespace() + "." + this.getId().getPath();
	}

	public Text getName() {
		return new TranslatableText(this.getTranslationKey());
	}

	public abstract String getType();

	public abstract boolean consumeSubstrate();

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
