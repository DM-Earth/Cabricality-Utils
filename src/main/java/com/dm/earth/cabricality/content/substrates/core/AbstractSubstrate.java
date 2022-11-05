package com.dm.earth.cabricality.content.substrates.core;

import net.minecraft.util.Identifier;

public abstract class AbstractSubstrate {
	private final Identifier id;
	private final int color;

	public AbstractSubstrate(Identifier id, int tint) {
		this.id = id;
		this.color = tint;
	}

	public Identifier getId() {
		return id;
	}

	public int getColor() {
		return color;
	}

	public abstract void registerRelatives();

	public String getTranslationKey() {
		return this.getType() + "." + this.getId().getNamespace() + "." + this.getId().getPath();
	}

	public abstract String getType();

	public abstract boolean consumeSubstrate();
}
