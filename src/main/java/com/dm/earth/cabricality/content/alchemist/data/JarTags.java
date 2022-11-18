package com.dm.earth.cabricality.content.alchemist.data;

import com.dm.earth.cabricality.Cabricality;
import com.dm.earth.cabricality.content.alchemist.Reagents;
import com.dm.earth.cabricality.content.alchemist.substrate.Reagent;

import net.devtech.arrp.json.tags.JTag;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class JarTags {
    public static final TagKey<Item> JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars"));
    public static final TagKey<Item> REAGENT_JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars/reagent"));
    public static final TagKey<Item> CATALYST_JARS = TagKey.of(Registry.ITEM_KEY, Cabricality.id("jars/catalyst"));

    public static void load() {
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

        Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + JARS.id().getPath()), jars);
        Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + REAGENT_JARS.id().getPath()), reagentJars);
        Cabricality.SERVER_RESOURCES.addTag(Cabricality.id("items/" + CATALYST_JARS.id().getPath()), catalystJars);
    }
}
