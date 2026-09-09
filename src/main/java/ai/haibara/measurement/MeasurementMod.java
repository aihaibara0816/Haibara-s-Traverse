package ai.haibara.measurement;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
/** Bootstrap only. Tool interactions arrive in the next playable milestone. */
@Mod(MeasurementMod.ID)
public final class MeasurementMod {
    public static final String ID = "haibaras_measurement";
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ID);
    static {
        for (String name : new String[]{"boundary_marker", "survey_stake", "survey_pen", "map_tablet", "field_radio", "engineering_desk"})
            ITEMS.registerSimpleItem(name, new Item.Properties());
    }
    public MeasurementMod(IEventBus bus) { ITEMS.register(bus); }
}
