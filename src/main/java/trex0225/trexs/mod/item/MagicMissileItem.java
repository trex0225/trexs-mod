package trex0225.trexs.mod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import trex0225.trexs.mod.entity.MagicMissileEntity;
import trex0225.trexs.mod.managers.StatManager;
import trex0225.trexs.mod.access.StatManagerAccess;

public class MagicMissileItem extends MagicItem {
    private float speed;
    private float damage;

    public MagicMissileItem(ToolMaterial material, int durability, float attackDamage, float attackSpeed,
            float spellUseTime, int add, int manaUse, float speedMod, float damage, Item.Settings settings) {
        super(material, durability, attackDamage, attackSpeed, spellUseTime, add, manaUse, settings);
        this.speed = speedMod;
        this.damage = damage;
    }

    @Override
    public void castSpell(LivingEntity user, ItemStack stack, int remainingUseTicks, World world) {
        PlayerEntity playerEntity = (PlayerEntity) user;
        MagicMissileEntity magic_Missile_Entity = new MagicMissileEntity(user, world, this.getAdd());
        StatManager statManager = ((StatManagerAccess) playerEntity).getStatManager(playerEntity);
        int damageBonus = statManager.getMagic();
        magic_Missile_Entity.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 0.5F,
                1.0F);
        magic_Missile_Entity.setDamage(damage + damageBonus);
        magic_Missile_Entity.setSpeed(speed);
        magic_Missile_Entity.setPos(playerEntity.getX(), playerEntity.getY() + 1.6D, playerEntity.getZ());
        world.spawnEntity(magic_Missile_Entity);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
}
