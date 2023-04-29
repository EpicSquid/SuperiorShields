package dev.epicsquid.superiorshields

import com.tterrag.registrate.Registrate
import net.minecraft.world.item.CreativeModeTab
import net.minecraftforge.fml.common.Mod
import org.apache.logging.log4j.LogManager

@Mod(SuperiorShields.MODID)
object SuperiorShields {
	const val MODID = "superiorshields"

	val logger = LogManager.getLogger(MODID)

	val registrate by lazy {
		Registrate.create(MODID)
	}

	val tab = object : CreativeModeTab(MODID) {
		override fun makeIcon() = TODO()
	}

	init {
//		MOD_CONTEXT.getKEventBus().addListener(::setup)
	}
}