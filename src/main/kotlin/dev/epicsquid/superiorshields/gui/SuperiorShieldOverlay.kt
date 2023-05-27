package dev.epicsquid.superiorshields.gui

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS
import dev.epicsquid.superiorshields.registry.CapabilityRegistry.shield
import epicsquid.superiorshields.SuperiorShields
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.player.Player
import net.minecraftforge.client.gui.overlay.ForgeGui
import net.minecraftforge.client.gui.overlay.IGuiOverlay
import org.lwjgl.opengl.GL11
import kotlin.math.roundToInt

class SuperiorShieldOverlay : GuiComponent(), IGuiOverlay {

	private fun drawQuad(
		buffer: BufferBuilder,
		x1: Float,
		y1: Float,
		x2: Float,
		y2: Float,
		x3: Float,
		y3: Float,
		x4: Float,
		y4: Float,
		minU: Int,
		minV: Int,
		maxU: Int,
		maxV: Int
	) {
		val f = 0.00390625f
		val f1 = 0.00390625f
		buffer.vertex((x1 + 0.0f).toDouble(), (y1 + 0.0f).toDouble(), 0.0).uv(minU * f, (minV + maxV) * f1).endVertex()
		buffer.vertex((x2 + 0.0f).toDouble(), (y2 + 0.0f).toDouble(), 0.0).uv((minU + maxU) * f, (minV + maxV) * f1)
			.endVertex()
		buffer.vertex((x3 + 0.0f).toDouble(), (y3 + 0.0f).toDouble(), 0.0).uv((minU + maxU) * f, minV * f1).endVertex()
		buffer.vertex((x4 + 0.0f).toDouble(), (y4 + 0.0f).toDouble(), 0.0).uv(minU * f, minV * f1).endVertex()
	}

	override fun render(gui: ForgeGui?, poseStack: PoseStack?, partialTick: Float, screenWidth: Int, screenHeight: Int) {
		val minecraft = Minecraft.getInstance()
		val player: Player? = minecraft.player
		if (player == null || player.isSpectator || player.isCreative || minecraft.options.hideGui) {
			return
		}

		val defaultXOffset = -101
		var defaultYOffset = 49

		if (player.armorValue > 0) {
			defaultYOffset += 10
		}
		if (player.activeEffectsMap.containsKey(MobEffects.ABSORPTION)) {
			defaultYOffset += 10
		}
		if (player.maxHealth > 20.0f) {
			// TODO test if this works with Blood Magic
			defaultYOffset += 10
		}

		RenderSystem.enableBlend()
		RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
		RenderSystem.setShaderTexture(0, ResourceLocation(SuperiorShields.MODID, "textures/gui/shield.png"))

		val tess = Tesselator.getInstance()
		val b = tess.builder
		val w = minecraft.window.guiScaledWidth
		val h = minecraft.window.guiScaledHeight

		var shieldHp = player.shield.hp
		var shieldCapacity = player.shield.capacity

		var offsetX = defaultXOffset

		b.begin(QUADS, DefaultVertexFormat.POSITION_TEX)
		while (shieldCapacity > 0) {
			drawQuad(
				b,
				(w / 2 + 10 + offsetX).toFloat(),
				(h - (defaultYOffset - 9)).toFloat(),
				(w / 2 + 19 + offsetX).toFloat(),
				(h - (defaultYOffset - 9)).toFloat(),
				(w / 2 + 19 + offsetX).toFloat(),
				(h - defaultYOffset).toFloat(),
				(w / 2 + 10 + offsetX).toFloat(),
				(h - defaultYOffset).toFloat(),
				0,
				0,
				9,
				9
			)
			if (shieldCapacity > 2) {
				shieldCapacity -= 2
				offsetX += 8
			} else {
				shieldCapacity = 0
			}
		}
		offsetX = defaultXOffset
		while (shieldHp > 0) {
			if (shieldHp > 1) {
				drawQuad(
					b,
					(w / 2 + 10 + offsetX).toFloat(),
					(h - (defaultYOffset - 9)).toFloat(),
					(w / 2 + 19 + offsetX).toFloat(),
					(h - (defaultYOffset - 9)).toFloat(),
					(w / 2 + 19 + offsetX).toFloat(),
					(h - defaultYOffset).toFloat(),
					(w / 2 + 10 + offsetX).toFloat(),
					(h - defaultYOffset).toFloat(),
					0,
					16,
					9,
					9
				)
				shieldHp -= 2
				offsetX += 8
			} else {
				drawQuad(
					b,
					(w / 2 + 10 + offsetX).toFloat(),
					(h - (defaultYOffset - 9)).toFloat(),
					(w / 2 + 19 + offsetX).toFloat(),
					(h - (defaultYOffset - 9)).toFloat(),
					(w / 2 + 19 + offsetX).toFloat(),
					(h - defaultYOffset).toFloat(),
					(w / 2 + 10 + offsetX).toFloat(),
					(h - defaultYOffset).toFloat(),
					32,
					16,
					9,
					9
				)
			}
			shieldHp = 0
		}
		tess.end()

		RenderSystem.disableBlend()
	}
}