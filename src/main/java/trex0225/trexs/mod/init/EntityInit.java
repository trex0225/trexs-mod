package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;
//import trex0225.trexs.mod.entity.MagicBoltEntity;
import trex0225.trexs.mod.entity.MagicMissileEntity;
import trex0225.trexs.mod.entity.SpearEntity;
import trex0225.trexs.mod.item.SpearItem;

public class EntityInit {
    private static final Map<Identifier, EntityType<?>> ENTITY_TYPES = new LinkedHashMap<>();

    public static final EntityType<SpearEntity> WOODEN_SPEAR = register("wooden_spear",
            create_Spear(ItemInit.WOODEN_SPEAR_ITEM));

    public static final EntityType<SpearEntity> STONE_SPEAR = register("stone_spear",
            create_Spear(ItemInit.STONE_SPEAR_ITEM));

    public static final EntityType<SpearEntity> IRON_SPEAR = register("iron_spear",
            create_Spear(ItemInit.IRON_SPEAR_ITEM));

    public static final EntityType<SpearEntity> GOLDEN_SPEAR = register("golden_spear",
            create_Spear(ItemInit.GOLDEN_SPEAR_ITEM));

    public static final EntityType<SpearEntity> DIAMOND_SPEAR = register("diamond_spear",
            create_Spear(ItemInit.DIAMOND_SPEAR_ITEM));

    public static final EntityType<SpearEntity> NETHERITE_SPEAR = register("netherite_spear",
            create_Spear(ItemInit.NETHERITE_SPEAR_ITEM));


    public static final EntityType<MagicMissileEntity> MAGIC_MISSILE_ENTITY = register("magic_missile",
        FabricEntityTypeBuilder.<MagicMissileEntity>create(SpawnGroup.MISC, MagicMissileEntity::new).dimensions(EntityDimensions.fixed(0.3F, 0.3F)).build());

    // public static final EntityType<MagicBoltEntity> MAGIC_BOLT_ENTITY = register("magic_bolt",
    //     FabricEntityTypeBuilder.<MagicBoltEntity>create(SpawnGroup.MISC, MagicBoltEntity::new).dimensions(EntityDimensions.fixed(0.3F, 0.3F)).build());

    public static void init() {
        for (Identifier id : ENTITY_TYPES.keySet()) {
            Registry.register(Registry.ENTITY_TYPE, id, ENTITY_TYPES.get(id));
        }
    }

    private static <T extends EntityType<?>> T register(String name, T type) {
        Identifier id = new Identifier(TrexsMod.MOD_ID, name);
        ENTITY_TYPES.put(id, type);
        return type;
    }

    private static EntityType<SpearEntity> create_Spear(SpearItem item) {
        return FabricEntityTypeBuilder
                .<SpearEntity>create(SpawnGroup.MISC, (entity, world) -> new SpearEntity(entity, world, item))
                .dimensions(EntityDimensions.fixed(0.6F, 0.6F)).build();
    }
}