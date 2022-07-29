package epicsquid.superiorshields.lang;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

public class TooltipUtil {

	public static MutableComponent withArgs(MutableComponent component, Object... args) {
		// Translate with args.
		if (component.getContents() instanceof TranslatableContents translatableContents) {
			return Component.translatable(translatableContents.getKey(), args);
		}

		// Fallback.
		return component;
	}
}
