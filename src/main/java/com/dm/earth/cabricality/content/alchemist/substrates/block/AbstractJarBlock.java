package com.dm.earth.cabricality.content.alchemist.substrates.block;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.assets.ResourcedBlock;
import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.content.alchemist.substrates.core.AbstractSubstrate;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.core.SettingableBlockItem;
import com.dm.earth.cabricality.util.VoxelShapeUtil;
import net.devtech.arrp.json.blockstate.JBlockStates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public abstract class AbstractJarBlock extends Block implements SettingableBlockItem, ResourcedBlock {
	public AbstractJarBlock(Settings settings) {
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
	public abstract AbstractSubstrate getSubstrate();

	@NotNull
	public abstract Identifier getDefaultBlockId();

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapeUtil.simpleBox(4, 0, 4, 12, 14, 12);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.union(VoxelShapeUtil.simpleBox(4, 0, 4, 12, 12, 12), VoxelShapeUtil.simpleBox(5, 12, 5, 11, 14, 11));
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return world.getBlockState(pos.offset(Direction.DOWN)).isSideSolidFullSquare(world, pos.offset(Direction.DOWN), Direction.UP);
	}

	@Override
	public Item.Settings getSettings() {
		return CabfItems.Properties.JAR;
	}

	@Override
	public boolean doLootTable() {
		return true;
	}

	@Override
	public boolean doBlockStates() {
		return true;
	}

	@Override
	public boolean doItemModel() {
		return true;
	}

	@Override
	public Identifier getBlockModelId() {
		return Cabricality.id("block/jar/substrate");
	}

	@Override
	public @Nullable JBlockStates getBlockStates() {
		return JBlockStates.simple(this.getBlockModelId());
	}
}
