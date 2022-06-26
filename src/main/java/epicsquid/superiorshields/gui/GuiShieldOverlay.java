package epicsquid.superiorshields.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.capability.shield.IShieldCapability;
import epicsquid.superiorshields.capability.shield.SuperiorShieldsCapabilityManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = SuperiorShields.MODID, value = Dist.CLIENT)
public class GuiShieldOverlay {

    private static void drawQuad(BufferBuilder buffer, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int minU, int minV, int maxU, int maxV) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        buffer.vertex(x1 + 0.0F, y1 + 0.0F, 0).uv((minU + 0) * f, (minV + maxV) * f1).endVertex();
        buffer.vertex(x2 + 0.0F, y2 + 0.0F, 0).uv((minU + maxU) * f, (minV + maxV) * f1).endVertex();
        buffer.vertex(x3 + 0.0F, y3 + 0.0F, 0).uv((minU + maxU) * f, (minV + 0) * f1).endVertex();
        buffer.vertex(x4 + 0.0F, y4 + 0.0F, 0).uv((minU + 0) * f, (minV + 0) * f1).endVertex();
//		buffer.pos(x4 + 0.0F, y4 + 0.0F, 0).tex((minU + 0) * f, (minV + 0) * f1).endVertex();
    }

    @SubscribeEvent
    public static void onRenderPlayerHud(@Nonnull RenderGameOverlayEvent event) {
        Player player = Minecraft.getInstance().player;
        if (player.isSpectator() || player.isCreative()) {
            return;
        }

        if (player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).isPresent()) {
            renderShieldBar(event);
        }
    }

    private static void renderShieldBar(@Nonnull RenderGameOverlayEvent event) {
        Player player = Minecraft.getInstance().player;
        IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability).orElseGet(() -> null);
        if (event.getType() != ElementType.HOTBAR || shield.getMaxHp() <= 0) {
            return;
        }

        int defaultXOffset = -101;
        int defaultYOffset = 49;

        if (player.getArmorValue() > 0) {
            defaultYOffset += 10;
        }
        if (player.getArmorValue() > 20) {
            defaultXOffset += 10;
        }
        if (player.getActiveEffectsMap().containsKey(MobEffects.ABSORPTION)) {
            defaultYOffset += 10;
        }
        if (player.getMaxHealth() > 20.0f) {
            // TODO test if this works with Blood Magic
            defaultYOffset += 10;
        }

        GlStateManager._pushMatrix();
        GlStateManager._enableAlphaTest();
        GlStateManager._enableBlend();
        Minecraft.getInstance().getTextureManager().getTexture(new ResourceLocation("superiorshields:textures/gui/shield.png"));

        Tesselator tess = Tesselator.getInstance();
        BufferBuilder b = tess.getBuilder();
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        GlStateManager._clearColor(1f, 1f, 1f, 1f);

        int shieldCurrentHp = Math.round(shield.getCurrentHp());
        int shieldMaxHp = Math.round(shield.getMaxHp());

        int offsetX = defaultXOffset;

        b.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION_TEX);
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

        GlStateManager._popMatrix();
    }
}
