package epicsquid.superiorshields.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.capability.shield.CapabilityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.lwjgl.opengl.GL11;

public class GuiShieldOverlay extends GuiComponent implements IIngameOverlay {

	private static void drawQuad(BufferBuilder buffer, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int minU, int minV, int maxU, int maxV) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		buffer.vertex(x1 + 0.0F, y1 + 0.0F, 0).uv((minU) * f, (minV + maxV) * f1).endVertex();
		buffer.vertex(x2 + 0.0F, y2 + 0.0F, 0).uv((minU + maxU) * f, (minV + maxV) * f1).endVertex();
		buffer.vertex(x3 + 0.0F, y3 + 0.0F, 0).uv((minU + maxU) * f, (minV) * f1).endVertex();
		buffer.vertex(x4 + 0.0F, y4 + 0.0F, 0).uv((minU) * f, (minV) * f1).endVertex();
//		buffer.pos(x4 + 0.0F, y4 + 0.0F, 0).tex((minU + 0) * f, (minV + 0) * f1).endVertex();
	}

	public static void init() {
		OverlayRegistry.registerOverlayAbove(ForgeIngameGui.PLAYER_HEALTH_ELEMENT, "superior_shield_overlay", new GuiShieldOverlay());
	}

	@Override
	public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		Minecraft minecraft = Minecraft.getInstance();
		Player player = minecraft.player;
		if (player == null || player.isSpectator() || player.isCreative() || minecraft.options.hideGui) {
			return;
		}

		CapabilityRegistry.getShield(player).ifPresent(shield -> {
			int defaultXOffset = -101;
			int defaultYOffset = 49;

			if (player.getArmorValue() > 0) {
				defaultYOffset += 10;
			}
			if (player.getActiveEffectsMap().containsKey(MobEffects.ABSORPTION)) {
				defaultYOffset += 10;
			}
			if (player.getMaxHealth() > 20.0f) {
				// TODO test if this works with Blood Magic
				defaultYOffset += 10;
			}

			RenderSystem.enableBlend();
			RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
			RenderSystem.setShaderTexture(0, new ResourceLocation(SuperiorShields.MODID, "textures/gui/shield.png"));

			Tesselator tess = Tesselator.getInstance();
			BufferBuilder b = tess.getBuilder();
			int w = minecraft.getWindow().getGuiScaledWidth();
			int h = minecraft.getWindow().getGuiScaledHeight();

			int shieldCurrentHp = Math.round(shield.getCurrentHp());
			int shieldMaxHp = Math.round(shield.getMaxHp());

			int offsetX = defaultXOffset;

			b.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
			while (shieldMaxHp > 0) {
				drawQuad(b, w / 2 + 10 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - defaultYOffset, w / 2 + 10 + offsetX, h - defaultYOffset, 0, 0, 9, 9);
				if (shieldMaxHp > 2) {
					shieldMaxHp -= 2;
					offsetX += 8;
				} else {
					shieldMaxHp = 0;
				}
			}
			offsetX = defaultXOffset;
			while (shieldCurrentHp > 0) {
				if (shieldCurrentHp > 1) {
					drawQuad(b, w / 2 + 10 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - defaultYOffset, w / 2 + 10 + offsetX, h - defaultYOffset, 0, 16, 9, 9);
					shieldCurrentHp -= 2;
					offsetX += 8;
				} else {
					if (shieldCurrentHp == 1) {
						drawQuad(b, w / 2 + 10 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - defaultYOffset, w / 2 + 10 + offsetX, h - defaultYOffset, 32, 16, 9, 9);
					}
					shieldCurrentHp = 0;
				}
			}
			tess.end();

			RenderSystem.disableBlend();
		});
	}
}
