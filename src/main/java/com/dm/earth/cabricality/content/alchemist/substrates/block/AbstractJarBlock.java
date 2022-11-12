package com.dm.earth.cabricality.content.alchemist.substrates.block;

import com.dm.earth.cabricality.client.CabricalityClient;
import com.dm.earth.cabricality.content.alchemist.substrates.core.AbstractSubstrate;
import com.dm.earth.cabricality.content.entries.CabfItems;
import com.dm.earth.cabricality.core.SettingableBlockItem;
import com.dm.earth.cabricality.util.VoxelShapeUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Item;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

@SuppressWarnings("deprecation")
public abstract class AbstractJarBlock extends Block implements SettingableBlockItem {
	public AbstractJarBlock(Settings settings) {
		super(settings);
	}

	@Override
	public String getTranslationKey() {
		return new TranslatableText(CabricalityClient.genTranslationKey("block", this.getSubstrate().getType())).getString();
	}

	public abstract AbstractSubstrate getSubstrate();

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapeUtil.simpleBox(4, 0, 4, 12, 14, 12);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.union(VoxelShapeUtil.simpleBox(4, 0, 4, 12, 12, 12), VoxelShapeUtil.simpleBox(5, 12, 5, 11, 14, 11));
	}

	@Override
	public Item.Settings getSettings() {
		return CabfItems.Properties.JAR;
	}
}
