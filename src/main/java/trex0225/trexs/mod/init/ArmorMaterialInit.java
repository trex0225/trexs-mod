package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;
import trex0225.trexs.mod.armormaterial.MithrilArmorMaterial;
import trex0225.trexs.mod.armormaterial.SilverArmorMaterial;
import trex0225.trexs.mod.toolmaterial.MithrilToolMaterial;

public class ArmorMaterialInit {
    private static final Map<Identifier, ArmorMaterial> MATERIALS = new LinkedHashMap<>();

    public static final MithrilArmorMaterial MITHRIL = register("mithril", new MithrilArmorMaterial());
    public static final SilverArmorMaterial SILVER = register("silver", new SilverArmorMaterial());

    private static <M extends ArmorMaterial> M register(String name, M armorMaterial) {
        MATERIALS.put(new Identifier(TrexsMod.MOD_ID, name), armorMaterial);
        return armorMaterial;
    }
}