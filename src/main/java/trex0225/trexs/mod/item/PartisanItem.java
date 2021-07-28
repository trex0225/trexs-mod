package trex0225.trexs.mod.item;

import java.util.List;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import trex0225.trexs.mod.init.EffectsInit;

public class PartisanItem extends Item implements Vanishable {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    private final ToolMaterial material;
    private final float attackDamage;

    public PartisanItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(settings.maxDamageIfAbsent(material.getDurability()));
        this.material = material;
        this.attackDamage = attackDamage + material.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID,
                "Tool modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID,
                "Tool modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public ToolMaterial getMaterial() {
        return this.material;
    }

    @Override
    public int getEnchantability() {
        return this.material.getEnchantability();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        Block block = state.getBlock();
        if (block == Blocks.COBWEB) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT
                    && material != Material.UNDERWATER_PLANT && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD
                            ? 1.0F
                            : 1.5F;
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        float h = target.getHealth();

        target.setHealth(h - ((this.getAttackDamage())));

        //float attackCooldown = 

        target.addStatusEffect(new StatusEffectInstance(EffectsInit.BLEEDING, 60, 0, false, false, true)); 

        stack.damage(1, attacker, entity -> entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getHardness(world, pos) != 0.0F) {
            stack.damage(2, miner, entity -> entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? attributeModifiers
                : super.getAttributeModifiers(equipmentSlot);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        //tooltip.add( new TranslatableText("item.testmod.SpearItem.tooltip") );
    }
}
