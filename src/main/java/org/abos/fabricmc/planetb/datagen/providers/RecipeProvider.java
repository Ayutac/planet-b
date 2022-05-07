package org.abos.fabricmc.planetb.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.abos.fabricmc.planetb.Content;
import org.abos.fabricmc.planetb.PlanetB;
import techreborn.init.TRContent;

import java.util.Locale;
import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        generateDecorative(exporter);
        generateSmelting(exporter);
        generateDustDuplication(exporter);
    }

    protected void generateDecorative(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Rock rock : Content.Rock.values()) {
            ShapedRecipeJsonBuilder.create(rock.getSlab())
                    .pattern("XXX")
                    .input('X', rock)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, PlanetB.id("crafting/" + rock.getName() + "_rock_slab"));
            SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(rock), rock.getSlab(), 2)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, PlanetB.id("crafting/" + rock.getName() + "_rock_slab_from_stonecutting"));
            ShapedRecipeJsonBuilder.create(rock.getStairs())
                    .pattern("X  ")
                    .pattern("XX ")
                    .pattern("XXX")
                    .input('X', rock)
                    .criterion(hasItem(rock) + rock.getName(), conditionsFromItem(rock))
                    .offerTo(exporter, PlanetB.id("crafting/" + rock.getName() + "_rock_stairs"));
            SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(rock), rock.getStairs(), 1)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, PlanetB.id("crafting/" + rock.getName() + "_rock_stairs_from_stonecutting"));
            ShapedRecipeJsonBuilder.create(rock.getWall())
                    .pattern("XXX")
                    .pattern("XXX")
                    .input('X', rock)
                    .criterion(hasItem(rock) + rock.getName(), conditionsFromItem(rock))
                    .offerTo(exporter, PlanetB.id("crafting/" + rock.getName() + "_rock_wall"));
            SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(rock), rock.getWall(), 1)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, PlanetB.id("crafting/" + rock.getName() + "_rock_wall_from_stonecutting"));
        }
    }

    protected void generateSmelting(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Rock rock : Content.Rock.values()) {
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(rock.asGlowingItem()), rock, 0.1f, 200)
                    .criterion(hasItem(rock.asGlowingItem()),conditionsFromItem(rock.asGlowingItem()))
                    .offerTo(exporter, PlanetB.id("smelting/" + rock.getName() + "_rock"));
        }
    }

    protected void generateDustDuplication(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Dust dust : Content.Dust.values()) {
            if (dust != Content.Dust.GALAXY) {
                ShapelessRecipeJsonBuilder.create(dust, 2)
                        .input(dust.asTag()).input(Content.Dust.GALAXY.asTag()).input(TRContent.Parts.UU_MATTER)
                        .criterion(hasItem(dust), conditionsFromTag(dust.asTag()))
                        .offerTo(exporter, PlanetB.id("crafting/" + dust.getName() + "_dust"));
            }
        }
        for (TRContent.Dusts dust : TRContent.Dusts.values()) {
            ShapelessRecipeJsonBuilder.create(dust, 2)
                    .input(dust.asTag()).input(Content.Dust.GALAXY.asTag()).input(TRContent.Parts.UU_MATTER)
                    .criterion(hasItem(dust), conditionsFromTag(dust.asTag()))
                    .offerTo(exporter, PlanetB.id("crafting/" + dust.name().toLowerCase(Locale.ROOT) + "_dust"));
        }
        // vanilla dusts
        ShapelessRecipeJsonBuilder.create(Items.REDSTONE, 2)
                .input(Items.REDSTONE).input(Content.Dust.GALAXY.asTag()).input(TRContent.Parts.UU_MATTER)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter, PlanetB.id("crafting/redstone"));
        ShapelessRecipeJsonBuilder.create(Items.GLOWSTONE_DUST, 2)
                .input(Items.GLOWSTONE_DUST).input(Content.Dust.GALAXY.asTag()).input(TRContent.Parts.UU_MATTER)
                .criterion(hasItem(Items.GLOWSTONE_DUST), conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter, PlanetB.id("crafting/glowstone_dust"));
        ShapelessRecipeJsonBuilder.create(Items.GUNPOWDER, 2)
                .input(Items.GUNPOWDER).input(Content.Dust.GALAXY.asTag()).input(TRContent.Parts.UU_MATTER)
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .offerTo(exporter, PlanetB.id("crafting/gunpowder"));
    }
}
