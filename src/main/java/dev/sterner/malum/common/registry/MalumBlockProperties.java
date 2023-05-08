package dev.sterner.malum.common.registry;

import dev.sterner.malum.common.block.BlockTagRegistry;
import dev.sterner.malum.data.MalumDatagenBlockData;
import dev.sterner.malum.data.MalumTagProviders;
import me.alphamode.forgetags.Tags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.block.extensions.mixin.AbstractBlockAccessor;
import org.quiltmc.qsl.block.extensions.mixin.AbstractBlockSettingsAccessor;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public interface MalumBlockProperties {
	static BlockSettings TAINTED_ROCK() {
		return BlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
				.addTag(MalumTagRegistry.TAINTED_ROCK)
				.needsPickaxe()
				.requiresTool()
				.sounds(MalumSoundRegistry.TAINTED_ROCK)
				.strength(1.25f, 9f);
	}

	static BlockSettings TWISTED_ROCK() {
		return BlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
				.addTag(BlockTagRegistry.TWISTED_ROCK)
				.needsPickaxe()
				.requiresTool()
				.sounds(MalumSoundRegistry.TWISTED_ROCK)
				.strength(1.25F, 9.0F);
	}

	static BlockSettings RUNEWOOD() {
		return BlockSettings.of(Material.WOOD, MapColor.YELLOW)
				.needsAxe()
				.sounds(BlockSoundGroup.WOOD)
				.strength(1.75F, 4.0F);
	}

	static BlockSettings RUNEWOOD_SAPLING() {
		return BlockSettings.of(Material.PLANT, MapColor.YELLOW)
				.addTag(BlockTags.SAPLINGS)
				.noCollision()
				.nonOpaque()
				.sounds(BlockSoundGroup.GRASS)
				.breakInstantly();
	}

	static BlockSettings RUNEWOOD_LEAVES() {
		return BlockSettings.of(Material.LEAVES, MapColor.YELLOW)
				.addTag(BlockTags.LEAVES)
				.strength(0.2F)
				.ticksRandomly()
				.nonOpaque()
				.allowsSpawning(Blocks::canSpawnOnLeaves)
				.suffocates(Blocks::never)
				.blockVision(Blocks::never)
				.sounds(MalumSoundRegistry.RUNEWOOD_LEAVES)
				.needsHoe();
	}

	static BlockSettings SOULWOOD() {
		return BlockSettings.of(Material.WOOD, MapColor.PURPLE)
				.sounds(MalumSoundRegistry.SOULWOOD)
				.strength(1.75F, 4.0F)
				.needsAxe();
	}

	static BlockSettings BLIGHTED_PLANTS() {
		return BlockSettings.of(Material.REPLACEABLE_PLANT, MapColor.PURPLE)
				.addTag(BlockTagRegistry.BLIGHTED_PLANTS)
				.noCollision()
				.nonOpaque()
				.sounds(MalumSoundRegistry.BLIGHTED_FOLIAGE)
				.breakInstantly();
	}

	static BlockSettings SOULWOOD_LEAVES() {
		return BlockSettings.of(Material.LEAVES, MapColor.PURPLE)
				.addTag(BlockTags.LEAVES)
				.needsHoe()
				.strength(0.2F)
				.ticksRandomly()
				.nonOpaque()
				.allowsSpawning(Blocks::canSpawnOnLeaves)
				.suffocates(Blocks::never)
				.blockVision(Blocks::never)
				.sounds(MalumSoundRegistry.SOULWOOD_LEAVES);
	}

	static BlockSettings BLIGHT() {
		return BlockSettings.of(Material.MOSS_BLOCK, MapColor.PURPLE)
				.addTag(BlockTagRegistry.BLIGHTED_BLOCKS)
				.needsShovel()
				.needsHoe()
				.sounds(MalumSoundRegistry.BLIGHTED_EARTH)
				.strength(0.7f);
	}

	static BlockSettings BRILLIANCE_ORE(boolean isDeepslate) {
		return BlockSettings.of(Material.STONE, isDeepslate ? MapColor.DEEPSLATE_GRAY : MapColor.STONE_GRAY)
				.addTag(Tags.Blocks.ORES)
				.needsPickaxe()
				.requiresTool()
				.strength(isDeepslate ? 5f : 3f, 3f)
				.sounds(isDeepslate ? BlockSoundGroup.DEEPSLATE : BlockSoundGroup.STONE);
	}

	static BlockSettings NATURAL_QUARTZ_ORE(boolean isDeepslate) {
		return BlockSettings.of(Material.STONE, isDeepslate ? MapColor.DEEPSLATE_GRAY : MapColor.STONE_GRAY)
				.addTag(Tags.Blocks.ORES)
				.needsPickaxe()
				.requiresTool()
				.strength(isDeepslate ? 6f : 4f, 3f)
				.sounds(isDeepslate ? MalumSoundRegistry.DEEPSLATE_QUARTZ : MalumSoundRegistry.NATURAL_QUARTZ);
	}

	static BlockSettings SOULSTONE_ORE(boolean isDeepslate) {
		return BlockSettings.of(Material.STONE, isDeepslate ? MapColor.DEEPSLATE_GRAY : MapColor.STONE_GRAY)
				.addTag(Tags.Blocks.ORES)
				.needsPickaxe()
				.requiresTool()
				.strength(isDeepslate ? 7.0f : 5.0F, 3.0F)
				.sounds(isDeepslate ? MalumSoundRegistry.DEEPSLATE_SOULSTONE : MalumSoundRegistry.SOULSTONE);
	}

	static BlockSettings BLAZING_QUARTZ_ORE() {
		return BlockSettings.of(Material.STONE, MapColor.DARK_RED)
				.addTag(Tags.Blocks.ORES)
				.needsPickaxe()
				.requiresTool()
				.strength(3.0F, 3.0F)
				.sounds(MalumSoundRegistry.BLAZING_QUARTZ_ORE);
	}

	static BlockSettings CTHONIC_GOLD_ORE() {
		return BlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
				.addTag(Tags.Blocks.ORES)
				.needsPickaxe()
				.requiresTool()
				.strength(25f, 9999f)
				.sounds(MalumSoundRegistry.CTHONIC_GOLD);
	}

	static BlockSettings NATURAL_QUARTZ_CLUSTER() {
		return BlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
				.needsPickaxe()
				.requiresTool()
				.strength(1.5F)
				.sounds(MalumSoundRegistry.QUARTZ_CLUSTER);
	}

	static BlockSettings ETHER() {
		return BlockSettings.of(Material.WOOL, MapColor.YELLOW)
				.addTag(BlockTagRegistry.TRAY_HEAT_SOURCES)
				.sounds(MalumSoundRegistry.ETHER)
				.nonOpaque()
				.breakInstantly()
				.luminance(14);
	}

	static BlockSettings SOULSTONE_BLOCK() {
		return BlockSettings.of(Material.STONE, MapColor.DARK_RED)
				.addTag(Tags.Blocks.STORAGE_BLOCKS)
				.addTag(BlockTags.BEACON_BASE_BLOCKS)
				.needsPickaxe()
				.requiresTool()
				.strength(5.0F, 3.0F)
				.sounds(MalumSoundRegistry.SOULSTONE);
	}

	static BlockSettings BLAZING_QUARTZ_BLOCK() {
		return BlockSettings.of(Material.STONE, MapColor.RED)
				.addTag(Tags.Blocks.STORAGE_BLOCKS)
				.addTag(BlockTags.BEACON_BASE_BLOCKS)
				.addTags(BlockTagRegistry.HEAT_SOURCES)
				.needsPickaxe()
				.requiresTool()
				.strength(5.0F, 6.0F)
				.sounds(MalumSoundRegistry.BLAZING_QUARTZ_BLOCK);
	}

	static BlockSettings BRILLIANCE_BLOCK() {
		return BlockSettings.of(Material.STONE, MapColor.GREEN)
				.addTag(Tags.Blocks.STORAGE_BLOCKS)
				.addTag(BlockTags.BEACON_BASE_BLOCKS)
				.needsPickaxe()
				.requiresTool()
				.strength(5.0F, 3.0F);
	}

	static BlockSettings ARCANE_CHARCOAL_BLOCK() {
		return BlockSettings.of(Material.STONE, MapColor.BLACK)
				.addTag(Tags.Blocks.STORAGE_BLOCKS)
				.needsPickaxe()
				.requiresTool()
				.strength(5.0F, 6.0F)
				.sounds(MalumSoundRegistry.ARCANE_CHARCOAL_BLOCK);
	}

	static BlockSettings HALLOWED_GOLD() {
		return BlockSettings.of(Material.METAL, MapColor.YELLOW)
				.addTag(Tags.Blocks.STORAGE_BLOCKS)
				.addTag(BlockTags.BEACON_BASE_BLOCKS)
				.requiresTool()
				.needsPickaxe()
				.sounds(MalumSoundRegistry.HALLOWED_GOLD)
				.nonOpaque()
				.strength(2F, 16.0F);
	}

	static BlockSettings SOUL_STAINED_STEEL_BLOCK() {
		return BlockSettings.of(Material.METAL, MapColor.PURPLE)
				.addTag(Tags.Blocks.STORAGE_BLOCKS)
				.addTag(BlockTags.BEACON_BASE_BLOCKS)
				.requiresTool()
				.needsPickaxe()
				.sounds(MalumSoundRegistry.SOUL_STAINED_STEEL)
				.strength(5f, 64.0f);
	}

	static BlockSettings SPIRIT_JAR() {
		return BlockSettings.of(Material.GLASS, MapColor.BLUE)
				.strength(0.5f, 64f)
				.sounds(MalumSoundRegistry.HALLOWED_GOLD)
				.nonOpaque();
	}

	static BlockSettings SOUL_VIAL() {
		return BlockSettings.of(Material.GLASS, MapColor.BLUE)
				.strength(0.75f, 64f)
				.sounds(MalumSoundRegistry.SOUL_STAINED_STEEL)
				.nonOpaque();
	}

	static BlockSettings WEEPING_WELL() {
		return BlockSettings.of(Material.STONE, MapColor.STONE_GRAY)
				.needsPickaxe()
				.sounds(MalumSoundRegistry.TAINTED_ROCK)
				.requiresTool()
				.strength(-1.0F, 3600000.0F);
	}

	static BlockSettings PRIMORDIAL_SOUP() {
		return BlockSettings.of(Material.PORTAL, MapColor.BLACK)
				.sounds(MalumSoundRegistry.BLIGHTED_EARTH)
				.strength(-1.0F, 3600000.0F);
	}

	class BlockSettings extends QuiltBlockSettings
	{
		protected BlockSettings(Material material, Function<BlockState, MapColor> mapColorProvider) {
			super(material, mapColorProvider);
		}

		protected BlockSettings(Material material, MapColor mapColor) {
			super(material, mapColor);
		}

		protected BlockSettings(AbstractBlock.Settings settings) {
			super(settings);
		}

		public static BlockSettings of(Material material) {
			return of(material, material.getColor());
		}

		public static BlockSettings of(Material material, Function<BlockState, MapColor> mapColorProvider) {
			return new BlockSettings(material, mapColorProvider);
		}

		public static BlockSettings of(Material material, MapColor color) {
			return new BlockSettings(material, color);
		}

		public static BlockSettings of(Material material, DyeColor color) {
			return new BlockSettings(material, color.getMapColor());
		}

		public static BlockSettings copyOf(AbstractBlock block) {
			return new BlockSettings(((AbstractBlockAccessor) block).getSettings());
		}

		public static BlockSettings copyOf(AbstractBlock.Settings settings) {
			return new BlockSettings(settings);
		}

		public BlockSettings addDatagenData(Function<MalumDatagenBlockData, MalumDatagenBlockData> function) {
			MalumTagProviders.MalumBlockTags.CACHE.put(
					this,
					function.apply(MalumTagProviders.MalumBlockTags.CACHE.getOrDefault(this, new MalumDatagenBlockData()))
			);
			return this;
		}

		public MalumDatagenBlockData getDatagenData() {
			return MalumTagProviders.MalumBlockTags.CACHE.getOrDefault(this, MalumDatagenBlockData.EMPTY);
		}

		public BlockSettings addTag(TagKey<Block> tag) {
			addDatagenData(d -> d.addTag(tag));
			return this;
		}

		@SafeVarargs
		public final BlockSettings addTags(TagKey<Block>... tags) {
			addDatagenData(d -> d.addTags(tags));
			return this;
		}

		public BlockSettings hasInheritedLoot() {
			addDatagenData(MalumDatagenBlockData::hasInheritedLoot);
			return this;
		}

		public BlockSettings needsPickaxe() {
			addDatagenData(MalumDatagenBlockData::needsPickaxe);
			return this;
		}

		public BlockSettings needsAxe() {
			addDatagenData(MalumDatagenBlockData::needsAxe);
			return this;
		}

		public BlockSettings needsShovel() {
			addDatagenData(MalumDatagenBlockData::needsShovel);
			return this;
		}

		public BlockSettings needsHoe() {
			addDatagenData(MalumDatagenBlockData::needsHoe);
			return this;
		}

		public BlockSettings needsStone() {
			addDatagenData(MalumDatagenBlockData::needsStone);
			return this;
		}

		public BlockSettings needsIron() {
			addDatagenData(MalumDatagenBlockData::needsIron);
			return this;
		}

		public BlockSettings needsDiamond() {
			addDatagenData(MalumDatagenBlockData::needsDiamond);
			return this;
		}

		@Override
		public BlockSettings noCollision() {
			super.noCollision();
			return this;
		}

		@Override
		public BlockSettings nonOpaque() {
			super.nonOpaque();
			return this;
		}

		@Override
		public BlockSettings slipperiness(float slipperiness) {
			super.slipperiness(slipperiness);
			return this;
		}

		@Override
		public BlockSettings velocityMultiplier(float velocityMultiplier) {
			super.velocityMultiplier(velocityMultiplier);
			return this;
		}

		@Override
		public BlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
			super.jumpVelocityMultiplier(jumpVelocityMultiplier);
			return this;
		}

		@Override
		public BlockSettings sounds(BlockSoundGroup soundGroup) {
			super.sounds(soundGroup);
			return this;
		}

		@Override
		public BlockSettings luminance(ToIntFunction<BlockState> luminance) {
			super.luminance(luminance);
			return this;
		}

		@Override
		public BlockSettings strength(float hardness, float resistance) {
			super.strength(hardness, resistance);
			return this;
		}

		@Override
		public BlockSettings breakInstantly() {
			super.breakInstantly();
			return this;
		}

		@Override
		public BlockSettings strength(float strength) {
			super.strength(strength);
			return this;
		}

		@Override
		public BlockSettings ticksRandomly() {
			super.ticksRandomly();
			return this;
		}

		@Override
		public BlockSettings dynamicBounds() {
			super.dynamicBounds();
			return this;
		}

		@Override
		public BlockSettings dropsNothing() {
			super.dropsNothing();
			return this;
		}

		@Override
		public BlockSettings dropsLike(Block source) {
			super.dropsLike(source);
			return this;
		}

		@Override
		public BlockSettings air() {
			super.air();
			return this;
		}

		@Override
		public BlockSettings allowsSpawning(AbstractBlock.TypedContextPredicate<EntityType<?>> predicate) {
			super.allowsSpawning(predicate);
			return this;
		}

		@Override
		public BlockSettings solidBlock(AbstractBlock.ContextPredicate predicate) {
			super.solidBlock(predicate);
			return this;
		}

		@Override
		public BlockSettings suffocates(AbstractBlock.ContextPredicate predicate) {
			super.suffocates(predicate);
			return this;
		}

		@Override
		public BlockSettings blockVision(AbstractBlock.ContextPredicate predicate) {
			super.blockVision(predicate);
			return this;
		}

		@Override
		public BlockSettings postProcess(AbstractBlock.ContextPredicate predicate) {
			super.postProcess(predicate);
			return this;
		}

		@Override
		public BlockSettings emissiveLighting(AbstractBlock.ContextPredicate predicate) {
			super.emissiveLighting(predicate);
			return this;
		}

		@Override
		public BlockSettings requiresTool() {
			super.requiresTool();
			return this;
		}

		@Override
		public BlockSettings mapColor(MapColor color) {
			super.mapColor(color);
			return this;
		}

		@Override
		public BlockSettings hardness(float hardness) {
			super.hardness(hardness);
			return this;
		}

		@Override
		public BlockSettings resistance(float resistance) {
			super.resistance(resistance);
			return this;
		}

		@Override
		public BlockSettings material(Material material) {
			((AbstractBlockSettingsAccessor) this).setMaterial(material);
			return this;
		}

		@Override
		public BlockSettings collidable(boolean collidable) {
			((AbstractBlockSettingsAccessor) this).setCollidable(collidable);
			return this;
		}

		@Override
		public BlockSettings opaque(boolean opaque) {
			((AbstractBlockSettingsAccessor) this).setOpaque(opaque);
			return this;
		}

		@Override
		public BlockSettings ticksRandomly(boolean ticksRandomly) {
			((AbstractBlockSettingsAccessor) this).setRandomTicks(ticksRandomly);
			return this;
		}

		@Override
		public BlockSettings dynamicBounds(boolean dynamicBounds) {
			((AbstractBlockSettingsAccessor) this).setDynamicBounds(dynamicBounds);
			return this;
		}

		@Override
		public BlockSettings requiresTool(boolean requiresTool) {
			((AbstractBlockSettingsAccessor) this).setToolRequired(requiresTool);
			return this;
		}

		@Override
		public BlockSettings air(boolean isAir) {
			((AbstractBlockSettingsAccessor) this).setIsAir(isAir);
			return this;
		}

		@Override
		public BlockSettings luminance(int luminance) {
			return this.luminance(ignored -> luminance);
		}

		@Override
		public BlockSettings drops(Identifier dropTableId) {
			((AbstractBlockSettingsAccessor) this).setLootTableId(dropTableId);
			return this;
		}

		@Override
		public BlockSettings mapColor(DyeColor color) {
			return this.mapColor(color.getMapColor());
		}

		@Override
		public BlockSettings mapColorProvider(Function<BlockState, MapColor> mapColorProvider) {
			((AbstractBlockSettingsAccessor) this).setMapColorProvider(mapColorProvider);
			return this;
		}
	}
}
