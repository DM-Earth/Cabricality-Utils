package com.dm.earth.cabricality.util;

import com.github.alexnijjar.ad_astra.blocks.flags.FlagBlock;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class VoxelShapeUtils {
    public static VoxelShape simpleBox(double x1, double y1, double z1, double x2, double y2, double z2) {
        return FlagBlock.boxSimple(x1, y1, z1, x2, y2, z2);
    }

    //input: north
    public static VoxelShape simpleBox(Direction direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        VoxelShape returnBox = simpleBox(x1, y1, z1, x2, y2, z2);
        switch (direction) {
            case NORTH -> {
                returnBox = simpleBox(x1, y1, z1, x2, y2, z2);
            }
            case WEST -> {
                returnBox = simpleBox(z1, y1, 16-x1, z2, y2, 16-x2);
            }
            case SOUTH -> {
                returnBox = simpleBox(16-x1, y1, 16-z1, 16-x2, y2, 16-z2);
            }
            case EAST -> {
                returnBox = simpleBox(16-z1, y1, x1, 16-z2, y2, x2);
            }
        }
        return returnBox;
    }
}
