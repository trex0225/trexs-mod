package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;
import trex0225.trexs.mod.enchantment.Hunt;
import trex0225.trexs.mod.enchantment.WindRider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;

public class EnchantmentInit {
    private static final Map<Identifier, Enchantment> ENCHANTMENTS = new LinkedHashMap<>();

    public static Enchantment WIND_RIDER = register("wind_rider", new WindRider());
    public static Enchantment HUNT = register("hunt", new Hunt());

    private static <E extends Enchantment> E register(String name, E enchantment) {
        ENCHANTMENTS.put(new Identifier(TrexsMod.MOD_ID, name), enchantment);
        return enchantment;
    }
    
    public static void init() {
        for (Identifier id : ENCHANTMENTS.keySet()) {
            Registry.register(Registry.ENCHANTMENT, id, ENCHANTMENTS.get(id));
        }
    }
}
