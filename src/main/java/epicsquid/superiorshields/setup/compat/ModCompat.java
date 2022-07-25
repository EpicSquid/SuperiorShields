package epicsquid.superiorshields.setup.compat;

import net.minecraftforge.fml.ModList;

public class ModCompat {

	static boolean THERMAL_LOADED;
	static boolean BOTANIA_LOADED;

	static boolean MALUM_LOADED;


	public static void init() {
		THERMAL_LOADED = ModList.get().isLoaded("thermal");
		BOTANIA_LOADED = ModList.get().isLoaded("botania");
		MALUM_LOADED = ModList.get().isLoaded("malum");
	}
}
