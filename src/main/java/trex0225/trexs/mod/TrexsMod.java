package trex0225.trexs.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import trex0225.trexs.mod.init.BlockInit;
import trex0225.trexs.mod.init.ConfiguredFeatureInit;
import trex0225.trexs.mod.init.EffectsInit;
import trex0225.trexs.mod.init.EnchantmentInit;
import trex0225.trexs.mod.init.EntityInit;
import trex0225.trexs.mod.init.ItemInit;
import trex0225.trexs.mod.init.ModelProviderInit;
import trex0225.trexs.mod.init.RendererInit;
import trex0225.trexs.mod.network.ManaUpdatePacket;
import trex0225.trexs.mod.network.StatUpdatePacket;

public class TrexsMod implements ModInitializer {
	public static final String MOD_NAME = "Trex's Mod";
	public static final String MOD_ID = "trexsmod";

	@Override
	public void onInitialize() {
		ItemInit.init();
		EntityInit.init();
		RendererInit.init();
		EnchantmentInit.init();
		EffectsInit.init();
		ModelProviderInit.init();
		BlockInit.init();
		ConfiguredFeatureInit.init();
		ManaUpdatePacket.init();
		//StatUpdatePacket.init();
	}

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("trexsmod", "other"))
			.icon(() -> new ItemStack(Items.IRON_AXE)) // icon
			.appendItems(stacks -> {
				stacks.add(new ItemStack(ItemInit.WOODEN_SPEAR_ITEM)); // SPEARS
				stacks.add(new ItemStack(ItemInit.STONE_SPEAR_ITEM));
				stacks.add(new ItemStack(ItemInit.IRON_SPEAR_ITEM));
				stacks.add(new ItemStack(ItemInit.GOLDEN_SPEAR_ITEM));
				stacks.add(new ItemStack(ItemInit.DIAMOND_SPEAR_ITEM));
				stacks.add(new ItemStack(ItemInit.NETHERITE_SPEAR_ITEM));
				stacks.add(new ItemStack(ItemInit.WOODEN_PARTISAN_ITEM)); // PARTISANS
				stacks.add(new ItemStack(ItemInit.STONE_PARTISAN_ITEM));
				stacks.add(new ItemStack(ItemInit.IRON_PARTISAN_ITEM));
				stacks.add(new ItemStack(ItemInit.GOLDEN_PARTISAN_ITEM));
				stacks.add(new ItemStack(ItemInit.DIAMOND_PARTISAN_ITEM));
				stacks.add(new ItemStack(ItemInit.NETHERITE_PARTISAN_ITEM));
				stacks.add(new ItemStack(ItemInit.MAGIC_MISSILE_ITEM)); // INDUS
				stacks.add(new ItemStack(ItemInit.MAGIC_BOLT_ITEM));
				stacks.add(new ItemStack(ItemInit.ARCANE_BULLET_ITEM));
				stacks.add(new ItemStack(ItemInit.RAW_INDUSIUM));
				stacks.add(new ItemStack(ItemInit.INDUSIUM_INGOT));
				stacks.add(new ItemStack(ItemInit.INDUSIUM_ORE));
				stacks.add(new ItemStack(ItemInit.RAW_MITHRIL));
				stacks.add(new ItemStack(ItemInit.MITHRIL_INGOT));
				stacks.add(new ItemStack(ItemInit.MITHRIL_ORE));
				stacks.add(new ItemStack(ItemInit.MITHRIL_HELMET));
				stacks.add(new ItemStack(ItemInit.MITHRIL_CHESTPLATE));
				stacks.add(new ItemStack(ItemInit.MITHRIL_LEGGINGS));
				stacks.add(new ItemStack(ItemInit.MITHRIL_BOOTS));
				stacks.add(new ItemStack(ItemInit.MITHRIL_PICKAXE));
				stacks.add(new ItemStack(ItemInit.MITHRIL_AXE));
				stacks.add(new ItemStack(ItemInit.MITHRIL_SHOVEL));
				stacks.add(new ItemStack(ItemInit.MITHRIL_HOE));
			}).build(); // builds

}
