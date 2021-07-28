package trex0225.trexs.mod.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class BleedingEffect extends StatusEffect {
    public BleedingEffect() {
        super(
          StatusEffectType.HARMFUL, // whether beneficial or harmful for entities
          0xBFD1415); // color in RGB
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
      return true;
    }
     
}
