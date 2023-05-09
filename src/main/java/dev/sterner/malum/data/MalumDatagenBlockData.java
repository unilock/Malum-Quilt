package dev.sterner.malum.data;

import net.minecraft.block.Block;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MalumDatagenBlockData {
	public static final MalumDatagenBlockData EMPTY = new MalumDatagenBlockData();

	private final List<TagKey<Block>> tags                  = new ArrayList<>();
	public        boolean             hasInheritedLootTable = false;

	public MalumDatagenBlockData addTag(TagKey<Block> blockTagKey) {
		tags.add(blockTagKey);
		return this;
	}

	@SafeVarargs
	public final MalumDatagenBlockData addTags(TagKey<Block>... blockTagKeys) {
		tags.addAll(Arrays.asList(blockTagKeys));
		return this;
	}

	public List<TagKey<Block>> getTags() {
		return tags;
	}

	public MalumDatagenBlockData hasInheritedLoot() {
		hasInheritedLootTable = true;
		return this;
	}

	public MalumDatagenBlockData needsPickaxe() {
		return addTag(BlockTags.PICKAXE_MINEABLE);
	}

	public MalumDatagenBlockData needsAxe() {
		return addTag(BlockTags.AXE_MINEABLE);
	}

	public MalumDatagenBlockData needsShovel() {
		return addTag(BlockTags.SHOVEL_MINEABLE);
	}

	public MalumDatagenBlockData needsHoe() {
		return addTag(BlockTags.HOE_MINEABLE);
	}

	public MalumDatagenBlockData needsStone() {
		return addTag(BlockTags.NEEDS_STONE_TOOL);
	}

	public MalumDatagenBlockData needsIron() {
		return addTag(BlockTags.NEEDS_IRON_TOOL);
	}

	public MalumDatagenBlockData needsDiamond() {
		return addTag(BlockTags.NEEDS_DIAMOND_TOOL);
	}
}
