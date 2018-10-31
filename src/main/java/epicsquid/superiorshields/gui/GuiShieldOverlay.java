package epicsquid.superiorshields.gui;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import epicsquid.superiorshields.SuperiorShields;
import epicsquid.superiorshields.capability.IShieldCapability;
import epicsquid.superiorshields.capability.SuperiorShieldsCapabilityManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = SuperiorShields.MODID)
public class GuiShieldOverlay {

  @SideOnly(Side.CLIENT)
  private static void drawQuad(BufferBuilder buffer, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int minU, int minV,
      int maxU, int maxV) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    buffer.pos(x1 + 0.0F, y1 + 0.0F, 0).tex((minU + 0) * f, (minV + maxV) * f1).endVertex();
    buffer.pos(x2 + 0.0F, y2 + 0.0F, 0).tex((minU + maxU) * f, (minV + maxV) * f1).endVertex();
    buffer.pos(x3 + 0.0F, y3 + 0.0F, 0).tex((minU + maxU) * f, (minV + 0) * f1).endVertex();
    buffer.pos(x4 + 0.0F, y4 + 0.0F, 0).tex((minU + 0) * f, (minV + 0) * f1).endVertex();
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public static void onRenderPlayerHud(@Nonnull RenderGameOverlayEvent event) {
    EntityPlayer player = Minecraft.getMinecraft().player;
    if (player.isSpectator() || player.isCreative()) {
      return;
    }

    if (player.hasCapability(SuperiorShieldsCapabilityManager.shieldCapability, null)) {
      renderShieldBar(event);
    }
  }

  @SideOnly(Side.CLIENT)
  private static void renderShieldBar(@Nonnull RenderGameOverlayEvent event) {
    EntityPlayer player = Minecraft.getMinecraft().player;
    IShieldCapability shield = player.getCapability(SuperiorShieldsCapabilityManager.shieldCapability, null);
    if (event.getType() != ElementType.HOTBAR || shield.getMaxHp() <= 0) {
      return;
    }

    int defaultXOffset = -101;
    int defaultYOffset = 49;

    if (player.getTotalArmorValue() > 0) {
     defaultYOffset += 10;
    }
    if (player.getActivePotionEffect(MobEffects.ABSORPTION) != null) {
      defaultYOffset += 10;
    }
    if (player.getMaxHealth() > 20.0f) {
      // TODO test if this works with Blood Magic
      defaultYOffset += 10;
    }

    GlStateManager.pushMatrix();
    GlStateManager.enableAlpha();
    GlStateManager.enableBlend();
    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("superiorshields:textures/gui/shield.png"));

    Tessellator tess = Tessellator.getInstance();
    BufferBuilder b = tess.getBuffer();
    int w = event.getResolution().getScaledWidth();
    int h = event.getResolution().getScaledHeight();
    GlStateManager.color(1f, 1f, 1f, 1f);

    int shieldCurrentHp = Math.round(shield.getCurrentHp());
    int shieldMaxHp = Math.round(shield.getMaxHp());

    int offsetX = defaultXOffset;

    b.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
    while (shieldMaxHp > 0) {
      drawQuad(b, w / 2 + 10 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - defaultYOffset,
          w / 2 + 10 + offsetX, h - defaultYOffset, 0, 0, 9, 9);
      if (shieldMaxHp > 3) {
        shieldMaxHp -= 3;
        offsetX += 8;
      } else {
        shieldMaxHp = 0;
      }
    }
    offsetX = defaultXOffset;
    while (shieldCurrentHp > 0) {
      if (shieldCurrentHp > 2) {
        drawQuad(b, w / 2 + 10 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - defaultYOffset,
            w / 2 + 10 + offsetX, h - defaultYOffset, 0, 16, 9, 9);
        shieldCurrentHp -= 3;
        offsetX += 8;
      } else {
        if (shieldCurrentHp == 2) {
          drawQuad(b, w / 2 + 10 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - defaultYOffset,
              w / 2 + 10 + offsetX, h - defaultYOffset, 16, 16, 9, 9);
        }
        if (shieldCurrentHp == 1) {
          drawQuad(b, w / 2 + 10 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - (defaultYOffset - 9), w / 2 + 19 + offsetX, h - defaultYOffset,
              w / 2 + 10 + offsetX, h - defaultYOffset, 32, 16, 9, 9);
        }
        shieldCurrentHp = 0;
      }
    }
    tess.draw();

    GlStateManager.popMatrix();
  }
}
