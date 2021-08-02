package trex0225.trexs.mod.item;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder.Living;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import trex0225.trexs.mod.access.ManaManagerAccess;
import trex0225.trexs.mod.managers.ManaManager;

public class MagicItem extends SwordItem {
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    private final ToolMaterial material;
    private final float attackDamage;
    private final float spellUseTime;
    private int add;
    private int manaUse;
    //private final Supplier<EntityType<SpearEntity>> typeSupplier;
    //private EntityType<SpearEntity> cachedType = null;

    public MagicItem(ToolMaterial material, int durability, float attackDamage, float attackSpeed, float spellUseTime,
            /*Supplier<EntityType<SpearEntity>> typeSupplier,*/int add, int manaUse, Item.Settings settings) {
        super(material, (int)attackDamage, attackSpeed, settings);
        this.material = material;
        this.attackDamage = attackDamage + material.getAttackDamage();
        this.spellUseTime = spellUseTime;
        this.add = add;
        this.manaUse = manaUse;
        //this.typeSupplier = typeSupplier;
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

        target.setHealth(h - 2);


        stack.damage(2, attacker, entity -> entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
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
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) user;
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= spellUseTime) {
                if (!world.isClient) {
                    stack.damage(2, playerEntity, entity -> entity.sendToolBreakStatus(user.getActiveHand()));
                    PlayerEntity player = (PlayerEntity) user;
                    ManaManager manaManager = ((ManaManagerAccess) player).getManaManager(player);
                    if(manaManager.getManaLevel()>=manaUse) {
                        if (!playerEntity.getAbilities().creativeMode) {
                            manaManager.sutract(manaUse);
                        }
                        castSpell(user, stack, remainingUseTicks, world);
                    }
                }
                playerEntity.getItemCooldownManager().set(this, 20 + (this.add * 20));
            }
        }
    }

    public void castSpell(LivingEntity user, ItemStack stack, int remainingUseTicks, World world) {
        
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    public int getAdd() {
        return add;
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
           f = 1.0F;
        }
  
        return f;
     }
}
