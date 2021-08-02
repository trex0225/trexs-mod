package trex0225.trexs.mod.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder.Living;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.event.GameEvent;
import trex0225.trexs.mod.managers.StatManager;
import net.minecraft.entity.DamageUtil;
import net.minecraft.enchantment.EnchantmentHelper;

public class StatHelper {
    public void UpdateStats(PlayerEntity player, StatManager statManager) {
        int totalMelee = 0;

        String helmet = Registry.ITEM.getId(player.getEquippedStack(EquipmentSlot.HEAD).getItem()).getPath();
        String chestplate = Registry.ITEM.getId(player.getEquippedStack(EquipmentSlot.CHEST).getItem()).getPath();
        String leggings = Registry.ITEM.getId(player.getEquippedStack(EquipmentSlot.LEGS).getItem()).getPath();
        String boots = Registry.ITEM.getId(player.getEquippedStack(EquipmentSlot.FEET).getItem()).getPath();

        if (chestplate.equals("diamond_chestplate") || chestplate.equals("netherite_chestplate")) {
            totalMelee += 1;
        }
        if (leggings.equals("diamond_leggings") || leggings.equals("netherite_leggings")) {
            totalMelee += 1;
        }

        if (helmet.equals("diamond_helmet") && chestplate.equals("diamond_chestplate")
                && leggings.equals("diamond_leggings") && boots.equals("diamond_boots")) {
            totalMelee += 1;
        }

        if (helmet.equals("netherite_helmet") && chestplate.equals("netherite_chestplate")
                && leggings.equals("netherite_leggings") && boots.equals("netherite_boots")) {
            totalMelee += 2;
        }

        statManager.setMelee(totalMelee);
        // System.out.println(statManager.getMelee());
    }

    public static void dealBonusMelee(LivingEntity attacker, StatManager statManager, LivingEntity target) {
        // PlayerEntity player = (PlayerEntity) attacker;
        int damageBonus = statManager.getMelee();
        //System.out.println(target.getHealth());
        // System.out.println(damageBonus);
        // boolean d = target.damage(DamageSource.player((PlayerEntity) attacker),
        // damageBonus);
        // if(d) {
        // System.out.println("shuodl work");
        // }
        // if (!this.isInvulnerableTo(source)) {
        DamageSource source = DamageSource.player((PlayerEntity) attacker);
        float amount = damageBonus;
        //amount = target.applyArmorToDamage(source, amount);
        amount = DamageUtil.getDamageLeft(amount, (float)target.getArmor(), (float)target.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));

        int k;
        if (target.hasStatusEffect(StatusEffects.RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
           k = (target.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1) * 5;
           int j = 25 - k;
           float f = amount * (float)j;
           float g = amount;
           amount = Math.max(f / 25.0F, 0.0F);
           float h = g - amount;
           if (h > 0.0F && h < 3.4028235E37F) {
              if (target instanceof ServerPlayerEntity) {
                 ((ServerPlayerEntity)target).increaseStat(Stats.DAMAGE_RESISTED, Math.round(h * 10.0F));
              } else if (source.getAttacker() instanceof ServerPlayerEntity) {
                 ((ServerPlayerEntity)source.getAttacker()).increaseStat(Stats.DAMAGE_DEALT_RESISTED, Math.round(h * 10.0F));
              }
           }
        }

        if (amount <= 0.0F) {
           amount =  0.0F;
        } else {
           k = EnchantmentHelper.getProtectionAmount(target.getArmorItems(), source);
           if (k > 0) {
              amount = DamageUtil.getInflictedDamage(amount, (float)k);
           }
        }

        //amount = target.applyEnchantmentsToDamage(source, amount);
        float f = amount;
        amount = Math.max(amount - target.getAbsorptionAmount(), 0.0F);
        target.setAbsorptionAmount(target.getAbsorptionAmount() - (f - amount));
        float g = f - amount;
        if (g > 0.0F && g < 3.4028235E37F && source.getAttacker() instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) source.getAttacker()).increaseStat(Stats.DAMAGE_DEALT_ABSORBED,
                    Math.round(g * 10.0F));
        }

        if (amount != 0.0F) {
            float h = target.getHealth();
            target.setHealth(h - amount);
            target.getDamageTracker().onDamage(source, h, amount);
            target.setAbsorptionAmount(target.getAbsorptionAmount() - amount);
            target.emitGameEvent(GameEvent.ENTITY_DAMAGED, source.getAttacker());
        }

        //System.out.println(target.getHealth());
        // }
    }
}
