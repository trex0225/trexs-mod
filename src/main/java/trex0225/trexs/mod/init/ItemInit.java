package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;
import trex0225.trexs.mod.item.MagicBoltItem;
import trex0225.trexs.mod.item.MagicMissileItem;
import trex0225.trexs.mod.item.PartisanItem;
import trex0225.trexs.mod.item.SpearItem;

public class ItemInit {
    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    //Spears

    public static final SpearItem WOODEN_SPEAR_ITEM = register("wooden_spear", new SpearItem(ToolMaterials.WOOD, 2.2F,
            -2.7F, () -> EntityInit.WOODEN_SPEAR, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final SpearItem STONE_SPEAR_ITEM = register("stone_spear", new SpearItem(ToolMaterials.STONE, 2.2F,
            -2.7F, () -> EntityInit.STONE_SPEAR, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final SpearItem IRON_SPEAR_ITEM = register("iron_spear", new SpearItem(ToolMaterials.IRON, 2.2F,
            -2.7F, () -> EntityInit.IRON_SPEAR, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final SpearItem GOLDEN_SPEAR_ITEM = register("golden_spear", new SpearItem(ToolMaterials.GOLD, 2.2F,
            -2.7F, () -> EntityInit.GOLDEN_SPEAR, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final SpearItem DIAMOND_SPEAR_ITEM = register("diamond_spear", new SpearItem(ToolMaterials.DIAMOND, 2.2F,
            -2.7F, () -> EntityInit.DIAMOND_SPEAR, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final SpearItem NETHERITE_SPEAR_ITEM = register("netherite_spear", new SpearItem(ToolMaterials.NETHERITE, 2.2F,
            -2.7F, () -> EntityInit.NETHERITE_SPEAR, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    // Partisans

    public static final PartisanItem WOODEN_PARTISAN_ITEM = register("wooden_partisan", new PartisanItem(ToolMaterials.WOOD, 3.4F,
            -2.8F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final PartisanItem STONE_PARTISAN_ITEM = register("stone_partisan", new PartisanItem(ToolMaterials.STONE, 3.4F,
            -2.8F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final PartisanItem IRON_PARTISAN_ITEM = register("iron_partisan", new PartisanItem(ToolMaterials.IRON, 3.4F,
            -2.8F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final PartisanItem GOLDEN_PARTISAN_ITEM = register("golden_partisan", new PartisanItem(ToolMaterials.GOLD, 3.4F,
            -2.8F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final PartisanItem DIAMOND_PARTISAN_ITEM = register("diamond_partisan", new PartisanItem(ToolMaterials.DIAMOND, 3.4F,
            -2.8F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final PartisanItem NETHERITE_PARTISAN_ITEM = register("netherite_partisan", new PartisanItem(ToolMaterials.NETHERITE, 3.4F,
            -2.8F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    // Indus Projectiles

    public static final MagicMissileItem MAGIC_MISSILE_ITEM = register("magic_missile",
            new MagicMissileItem(ToolMaterials.WOOD, 60, 1.0F, -2.4F, 20, 
            0, 4, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final MagicBoltItem MAGIC_BOLT_ITEM = register("magic_bolt",
            new MagicBoltItem(ToolMaterials.IRON, 250, 1.0F, -2.4F, 20, 
            0, 4, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
  
    // Raw Ore

    public static final Item RAW_INDUSIUM = register("raw_indusium", new Item((new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    // Ingots

    public static final Item INDUSIUM_INGOT = register("indusium_ingot", new Item((new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    // Ore

    public static final Item INDUSIUM_ORE = register("indusium_ore", new BlockItem(BlockInit.INDUSIUM_ORE, (new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    private static <I extends Item> I register(String name, I item) {
        ITEMS.put(new Identifier(TrexsMod.MOD_ID, name), item);
        return item;
    }

    public static void init() {
        for (Identifier id : ITEMS.keySet()) {
            Registry.register(Registry.ITEM, id, ITEMS.get(id));
        }
    }
}
