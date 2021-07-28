package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;
import trex0225.trexs.mod.effects.BleedingEffect;

public class EffectsInit {
    private static final Map<Identifier, StatusEffect> EFFECTS = new LinkedHashMap<>();

    public static StatusEffect BLEEDING = register("bleeding", new BleedingEffect());

    private static <E extends StatusEffect> E register(String name, E effect) {
        EFFECTS.put(new Identifier(TrexsMod.MOD_ID, name), effect);
        return effect;
    }
    
    public static void init() {
        for (Identifier id : EFFECTS.keySet()) {
            Registry.register(Registry.STATUS_EFFECT, id, EFFECTS.get(id));
        }
    }
}
