package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;
//import trex0225.trexs.mod.item.MagicBoltItem;
import trex0225.trexs.mod.item.MagicMissileItem;
import trex0225.trexs.mod.item.PartisanItem;
import trex0225.trexs.mod.item.SpearItem;
import trex0225.trexs.mod.item.toolClassExtension.CustomAxeItem;
import trex0225.trexs.mod.item.toolClassExtension.CustomHoeItem;
import trex0225.trexs.mod.item.toolClassExtension.CustomPickaxeItem;

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
            0, 4, 1, 5, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final MagicMissileItem MAGIC_BOLT_ITEM = register("magic_bolt",
            new MagicMissileItem(ToolMaterials.IRON, 250, 1.0F, -2.4F, 20, 
            0, 4, 2, 7, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final MagicMissileItem ARCANE_BULLET_ITEM = register("arcane_bullet",
            new MagicMissileItem(ToolMaterials.DIAMOND, 1300, 1.0F, -2.4F, 20, 
            0, 4, 3, 11, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
  
    // Raw Ore

    public static final Item RAW_INDUSIUM = register("raw_indusium", new Item((new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    public static final Item RAW_MITHRIL = register("raw_mithril", new Item((new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    // Ingots

    public static final Item INDUSIUM_INGOT = register("indusium_ingot", new Item((new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    public static final Item MITHRIL_INGOT = register("mithril_ingot", new Item((new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    // Ore

    public static final Item INDUSIUM_ORE = register("indusium_ore", new BlockItem(BlockInit.INDUSIUM_ORE, (new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    public static final Item MITHRIL_ORE = register("mithril_ore", new BlockItem(BlockInit.MITHRIL_ORE, (new Item.Settings()).group(TrexsMod.ITEM_GROUP)));

    // Armor

    public static final Item MITHRIL_HELMET = register("mithril_helmet", new ArmorItem(ArmorMaterialInit.MITHRIL, EquipmentSlot.HEAD, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
    public static final Item MITHRIL_CHESTPLATE = register("mithril_chestplate", new ArmorItem(ArmorMaterialInit.MITHRIL, EquipmentSlot.CHEST, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
    public static final Item MITHRIL_LEGGINGS = register("mithril_leggings", new ArmorItem(ArmorMaterialInit.MITHRIL, EquipmentSlot.LEGS, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
    public static final Item MITHRIL_BOOTS = register("mithril_boots", new ArmorItem(ArmorMaterialInit.MITHRIL, EquipmentSlot.FEET, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static final Item SILVER_HELMET = register("silver_helmet", new ArmorItem(ArmorMaterialInit.SILVER, EquipmentSlot.HEAD, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
    public static final Item SILVER_CHESTPLATE = register("silver_chestplate", new ArmorItem(ArmorMaterialInit.SILVER, EquipmentSlot.CHEST, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
    public static final Item SILVER_LEGGINGS = register("silver_leggings", new ArmorItem(ArmorMaterialInit.SILVER, EquipmentSlot.LEGS, new Item.Settings().group(TrexsMod.ITEM_GROUP)));
    public static final Item SILVER_BOOTS = register("silver_boots", new ArmorItem(ArmorMaterialInit.SILVER, EquipmentSlot.FEET, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    // Tool

    public static ToolItem MITHRIL_SHOVEL = register("mithril_shovel", new ShovelItem(ToolMaterialInit.MITHRIL, 1.5F, -3.0F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static ToolItem MITHRIL_PICKAXE = register("mithril_pickaxe", new CustomPickaxeItem(ToolMaterialInit.MITHRIL, 1, -2.8F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static ToolItem MITHRIL_AXE = register("mithril_axe", new CustomAxeItem(ToolMaterialInit.MITHRIL, 1.5F, -3.2F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

    public static ToolItem MITHRIL_HOE = register("mithril_hoe", new CustomHoeItem(ToolMaterialInit.MITHRIL, 7, -3.2F, new Item.Settings().group(TrexsMod.ITEM_GROUP)));

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
