// package trex0225.trexs.mod.item;

// import net.minecraft.entity.LivingEntity;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.item.Item;
// import net.minecraft.item.ItemStack;
// import net.minecraft.item.ToolMaterial;
// import net.minecraft.util.UseAction;
// import net.minecraft.world.World;
// import trex0225.trexs.mod.entity.MagicBoltEntity;
// import trex0225.trexs.mod.entity.MagicMissileEntity;

// public class MagicBoltItem extends MagicItem {
//     public MagicBoltItem(ToolMaterial material, int durability, float attackDamage, float attackSpeed,
//             float spellUseTime, int add, int manaUse, Item.Settings settings) {
//         super(material, durability, attackDamage, attackSpeed, spellUseTime, add, manaUse, settings);
//     }

//     @Override
//     public void castSpell(LivingEntity user, ItemStack stack, int remainingUseTicks, World world) {
//         PlayerEntity playerEntity = (PlayerEntity) user;
//         MagicBoltEntity magic_Bolt_Entity = new MagicBoltEntity(user, world, this.getAdd());
//         magic_Bolt_Entity.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 0.5F,
//                 1.0F);
//         magic_Bolt_Entity.setDamage(7.0F);
//         magic_Bolt_Entity.setPos(playerEntity.getX(), playerEntity.getY() + 1.6D, playerEntity.getZ());
//         world.spawnEntity(magic_Bolt_Entity);
//     }

//     @Override
//     public UseAction getUseAction(ItemStack stack) {
//         return UseAction.BOW;
//     }
// }