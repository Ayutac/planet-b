package org.abos.fabricmc.planetb;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import org.abos.fabricmc.planetb.worldgen.ConfiguredFeatures;
import org.abos.fabricmc.planetb.worldgen.PlacedFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlanetB implements ModInitializer {

	public static final String MOD_ID = "planet-b";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(
			Registry.DIMENSION_KEY,
			new Identifier(MOD_ID, MOD_ID)
	);

	public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(
			Registry.WORLD_KEY,
			DIMENSION_KEY.getValue()
	);

	public static final RegistryKey<DimensionType> DIMENSION_TYPE_KEY = RegistryKey.of(
			Registry.DIMENSION_TYPE_KEY,
			new Identifier(MOD_ID, MOD_ID + "_type")
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing...");
		Content.init();
		ConfiguredFeatures.init();
		PlacedFeatures.init();
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			ServerWorld overworld = server.getWorld(World.OVERWORLD);
			ServerWorld world = server.getWorld(WORLD_KEY);

			if (world == null)
				throw new AssertionError("Planet B world doesn't exist.");

			Entity entity = EntityType.COW.create(overworld);

			if (!entity.world.getRegistryKey().equals(World.OVERWORLD))
				throw new AssertionError("Entity starting world isn't the overworld");

			TeleportTarget target = new TeleportTarget(Vec3d.ZERO, new Vec3d(1, 1, 1), 45f, 60f);

			Entity teleported = FabricDimensions.teleport(entity, world, target);

			if (teleported == null)
				throw new AssertionError("Entity didn't teleport");

			if (!teleported.world.getRegistryKey().equals(WORLD_KEY))
				throw new AssertionError("Target world not reached.");

			if (!teleported.getPos().equals(target.position))
				throw new AssertionError("Target Position not reached.");
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
				dispatcher.register(CommandManager.literal("planet-b_dimension_test").executes(PlanetB.this::swapTargeted))
		);
		LOGGER.info("Initializing done! Good travels!");
	}

	private int swapTargeted(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity player = context.getSource().getPlayer();
		ServerWorld serverWorld = player.getWorld();
		ServerWorld modWorld = getModWorld(context);

		if (serverWorld != modWorld) {
			TeleportTarget target = new TeleportTarget(new Vec3d(0.5, 101, 0.5), Vec3d.ZERO, 0, 0);
			FabricDimensions.teleport(player, modWorld, target);

			if (player.world
					!= modWorld) {
				throw new CommandException(new LiteralText("Teleportation failed!"));
			}

			modWorld.setBlockState(new BlockPos(0, 100, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
			modWorld.setBlockState(new BlockPos(0, 101, 0), Blocks.TORCH.getDefaultState());
		} else {
			TeleportTarget target = new TeleportTarget(new Vec3d(0, 100, 0), Vec3d.ZERO,
					(float) Math.random() * 360 - 180, (float) Math.random() * 360 - 180);
			FabricDimensions.teleport(player, getWorld(context, World.OVERWORLD), target);
		}

		return 1;
	}

	private ServerWorld getModWorld(CommandContext<ServerCommandSource> context) {
		return getWorld(context, WORLD_KEY);
	}

	private ServerWorld getWorld(CommandContext<ServerCommandSource> context, RegistryKey<World> dimensionRegistryKey) {
		return context.getSource().getServer().getWorld(dimensionRegistryKey);
	}
}
