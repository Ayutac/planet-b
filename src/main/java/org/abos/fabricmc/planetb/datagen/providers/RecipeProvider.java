package org.abos.fabricmc.planetb.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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
        generateCraftingTable(exporter);
    }

    protected void generateCraftingTable(Consumer<RecipeJsonProvider> exporter) {
        for (Content.Rock rock : Content.Rock.values()) {
            ShapedRecipeJsonBuilder.create(rock.getStairs()).input('X', rock)
                        .pattern("X  ")
                        .pattern("XX ")
                        .pattern("XXX")
                        .criterion("has_" + rock.getName(), conditionsFromItem(rock))
                        .offerTo(exporter, new Identifier(PlanetB.MOD_ID, rock.getName() + "_stairs"));
            offerStonecuttingRecipe(exporter, rock.getStairs(), rock);
            offerSlabRecipe(exporter, rock.getSlab(), rock);
            offerStonecuttingRecipe(exporter, rock.getSlab(), rock, 2);
            offerWallRecipe(exporter, rock.getWall(), rock);
            offerStonecuttingRecipe(exporter, rock.getWall(), rock);
        }
    }
}
