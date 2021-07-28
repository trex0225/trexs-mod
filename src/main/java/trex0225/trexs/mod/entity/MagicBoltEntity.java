package trex0225.trexs.mod.entity;

import net.fabricmc.api.Environment;

import java.util.Iterator;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.Vanishable;
import net.minecraft.network.Packet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import trex0225.trexs.mod.init.EntityInit;
import trex0225.trexs.mod.network.EntitySpawnPacket;
import net.minecraft.util.hit.EntityHitResult;

public class MagicBoltEntity extends ThrownEntity implements Vanishable {
    private int add = 0;
    private float damage = 0;

    public MagicBoltEntity(EntityType<? extends MagicBoltEntity> entityType, World world) {
        super(entityType, world);
    }

    public MagicBoltEntity(EntityType<? extends MagicBoltEntity> entityType, double d, double e, double f, World world) {
        super(entityType, d, e, f, world);
    }

    public MagicBoltEntity(LivingEntity livingEntity, World world, int addition) {
        super(EntityInit.MAGIC_BOLT_ENTITY, livingEntity, world);
        this.add = addition;
    }

    @Environment(EnvType.CLIENT)
    public MagicBoltEntity(World world, double x, double y, double z, double directionX, double directionY, double directionZ) {
        super(EntityInit.MAGIC_BOLT_ENTITY, x, y, z, world);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.createPacket(this);
    }

    @Override
    public boolean collides() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void initDataTracker() {
    }


    @Override
    public void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
            if (!this.world.isClient) {
                //List<LivingEntity> list = this.world.getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox().expand(4.0D, 2.0D, 4.0D));
                this.discard();
            } 
    }

    @Override
    public void onEntityHit(EntityHitResult entityHitResult) {
        System.out.println("HIT TARGET");
        super.onEntityHit(entityHitResult);
        Entity owner = this.getOwner();
        DamageSource damageSource = createDamageSource(this, owner == null ? this : owner);
        entityHitResult.getEntity().damage(damageSource, damage);
    }

    public static DamageSource createDamageSource(Entity entity, Entity owner) {
        return new ProjectileDamageSource("magic_missile", entity, owner).setProjectile();
    }

    @Override
    public void tick() {
        //super.tick();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        boolean bl = false;
        if (hitResult.getType() != HitResult.Type.MISS && !bl) {
            this.onCollision(hitResult);
         }
        this.checkBlockCollision();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x*2;
        double e = this.getY() + vec3d.y*2;
        double f = this.getZ() + vec3d.z*2;
        this.updateRotation();
        float j;
        if (this.isTouchingWater()) {
            for (int i = 0; i < 4; ++i) {
                this.world.addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25D, e - vec3d.y * 0.25D, f - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
            }

            j = 0.8F;
        } else {
            j = 0.99F;
        }

        this.updatePosition(d, e, f);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

}
