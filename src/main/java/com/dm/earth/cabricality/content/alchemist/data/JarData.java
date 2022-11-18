package com.dm.earth.cabricality.content.alchemist.data;

import static com.dm.earth.cabricality.util.JRecipeUtil.fluidEntry;
import static com.dm.earth.cabricality.util.JRecipeUtil.itemEntry;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.devtech.arrp.json.tags.JTag;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class JarData implements AddRecipesCallback {
    public static final TagKey<Item> JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars"));
    public static final TagKey<Item> REAGENT_JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars/reagent"));
    public static final TagKey<Item> CATALYST_JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars/catalyst"));

    public static void load() {
        JarData thisL = new JarData();

        JTag jars = new JTag();
        JTag reagentJars = new JTag();
        JTag catalystJars = new JTag();

        for (Reagents reagents : Reagents.values()) {
            Identifier catalystId = Cabricality.id("catalyst_jar_" + reagents.getCatalyst().hashString());
            jars.add(catalystId);
            catalystJars.add(catalystId);

            for (Reagent reagent : reagents.getReagents()) {
                Identifier reagentId = Cabricality.id("reagent_jar_" + reagent.hashString());
                jars.add(reagentId);
                reagentJars.add(reagentId);
            }
        }

        // Tags
        Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + JARS.id().getPath()), jars);
        Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + REAGENT_JARS.id().getPath()), reagentJars);
        Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + CATALYST_JARS.id().getPath()), catalystJars);

        // Recipes
        RecipeManagerHelper.addRecipes(thisL);
    }

    @Override
    public void addRecipes(RecipeHandler handler) {
        for (Reagents reagents : Reagents.values())
            for (Reagent reagent : reagents.getReagents()) {
                // Identifier reagentId = Cabricality.id("reagent_jar_" + reagent.hashString());
                handler.register(Cabricality.id("alchemist/fluid_infuse/reagent_jar/" + reagent.hashString()),
                        id -> RecipeManager.deserialize(id, generateInfuse(reagent)));
                handler.register(Cabricality.id("alchemist/sawmill/reagent_jar/" + reagent.hashString()),
                        id -> RecipeManager.deserialize(id, generateReagentToItem(reagent)));
            }
    }

    private static JsonObject generateInfuse(Reagent reagent) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "indrev:fluid_infuse");

        JsonArray ingredients = new JsonArray();
        ingredients.add(itemEntry(reagent.getItemId(), 1));
        json.add("ingredients", ingredients);

        json.add("fluidInput", fluidEntry(new Identifier("tconstruct", "molten_glass"), FluidConstants.BOTTLE));

        json.add("output", itemEntry(Cabricality.id("reagent_jar_" + reagent.hashString()), 1));

        json.addProperty("processTime", 350);
        return json;
    }

    private static JsonObject generateReagentToItem(Reagent reagent) {
        Identifier reagentId = Cabricality.id("reagent_jar_" + reagent.hashString());

        JsonObject json = new JsonObject();
        json.addProperty("type", "indrev:sawmill");

        json.add("ingredients", itemEntry(reagentId, 1));

        JsonArray outputs = new JsonArray();
        outputs.add(itemEntry(reagent.getItemId(), 1));
        json.add("output", outputs);

        json.addProperty("processTime", 64);
        return json;
    }
}
