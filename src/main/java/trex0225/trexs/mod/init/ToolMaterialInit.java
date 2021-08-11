package trex0225.trexs.mod.init;

import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import trex0225.trexs.mod.TrexsMod;
import trex0225.trexs.mod.toolmaterial.MithrilToolMaterial;

public class ToolMaterialInit {
    private static final Map<Identifier, ToolMaterial> MATERIALS = new LinkedHashMap<>();

    public static final MithrilToolMaterial MITHRIL = register("mithril", new MithrilToolMaterial());

    private static <M extends ToolMaterial> M register(String name, M toolMaterial) {
        MATERIALS.put(new Identifier(TrexsMod.MOD_ID, name), toolMaterial);
        return toolMaterial;
    }
}
