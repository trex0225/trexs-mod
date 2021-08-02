package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;

public class BlockInit {
    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();

    public static final Block INDUSIUM_ORE = register("indusium_ore", new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 1)));

    public static final Block MITHRIL_ORE = register("mithril_ore", new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F).breakByTool(FabricToolTags.PICKAXES, 2)));

    private static <B extends Block> B register(String name, B block) {
        BLOCKS.put(new Identifier(TrexsMod.MOD_ID, name), block);
        return block;
    }
    
    public static void init() {
        for (Identifier id : BLOCKS.keySet()) {
            Registry.register(Registry.BLOCK, id, BLOCKS.get(id));
        }
    }
}
