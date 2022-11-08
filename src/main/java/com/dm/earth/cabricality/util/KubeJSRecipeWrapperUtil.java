package com.dm.earth.cabricality.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.recipe.impl.ImmutableMapBuilderUtil;

import java.util.Map;

public class KubeJSRecipeWrapperUtil {
	public static Map<RecipeType<?>, Map<Identifier, Recipe<?>>> recipes = ImmutableMap.of();
	public static Map<Identifier, Recipe<?>> globalRecipes = ImmutableMap.of();

	public static Map<RecipeType<?>, ImmutableMap.Builder<Identifier, Recipe<?>>> builderMap = Maps.newHashMap();
	public static ImmutableMap.Builder<Identifier, Recipe<?>> globalBuilder = ImmutableMap.builder();

	public static void clean() {
		recipes = ImmutableMap.of();
		globalRecipes = ImmutableMap.of();
		builderMap = Maps.newHashMap();
		globalBuilder = ImmutableMap.builder();
	}

	public static void build() {
		recipes = builderMap.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, (entry) -> ImmutableMapBuilderUtil.specialBuild(entry.getValue())));
		globalRecipes = globalBuilder.build();
	}
}
