package org.abos.fabricmc.planetb.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.abos.fabricmc.planetb.Content;
import org.abos.fabricmc.planetb.PlanetB;

import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        generateDecorative(exporter);
        generateSmelting(exporter);
    }

    protected void generateDecorative(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Rock rock : Content.Rock.values()) {
            ShapedRecipeJsonBuilder.create(rock.getSlab())
                    .pattern("XXX")
                    .input('X', rock)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, new Identifier(PlanetB.MOD_ID, "crafting/" + rock.getName() + "_rock_slab"));
            SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(rock), rock.getSlab(), 2)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, new Identifier(PlanetB.MOD_ID, "crafting/" + rock.getName() + "_rock_slab_from_stonecutting"));
            ShapedRecipeJsonBuilder.create(rock.getStairs())
                    .pattern("X  ")
                    .pattern("XX ")
                    .pattern("XXX")
                    .input('X', rock)
                    .criterion(hasItem(rock) + rock.getName(), conditionsFromItem(rock))
                    .offerTo(exporter, new Identifier(PlanetB.MOD_ID, "crafting/" + rock.getName() + "_rock_stairs"));
            SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(rock), rock.getStairs(), 1)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, new Identifier(PlanetB.MOD_ID, "crafting/" + rock.getName() + "_rock_stairs_from_stonecutting"));
            ShapedRecipeJsonBuilder.create(rock.getWall())
                    .pattern("XXX")
                    .pattern("XXX")
                    .input('X', rock)
                    .criterion(hasItem(rock) + rock.getName(), conditionsFromItem(rock))
                    .offerTo(exporter, new Identifier(PlanetB.MOD_ID, "crafting/" + rock.getName() + "_rock_wall"));
            SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(rock), rock.getWall(), 1)
                    .criterion(hasItem(rock), conditionsFromItem(rock))
                    .offerTo(exporter, new Identifier(PlanetB.MOD_ID, "crafting/" + rock.getName() + "_rock_wall_from_stonecutting"));
        }
    }

    protected void generateSmelting(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Rock rock : Content.Rock.values()) {
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(rock.asGlowingItem()), rock, 0.1f, 200)
                    .criterion(hasItem(rock.asGlowingItem()),conditionsFromItem(rock.asGlowingItem()))
                    .offerTo(exporter, new Identifier(PlanetB.MOD_ID, "smelting/" + rock.getName() + "_rock"));
        }
    }

}
