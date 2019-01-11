package epicsquid.superiorshields.render;

import javax.annotation.Nonnull;

import org.lwjgl.opengl.GL11;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import epicsquid.superiorshields.item.ISuperiorShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

public class LayerShield implements LayerRenderer<AbstractClientPlayer> {

  @Override
  public void doRenderLayer(@Nonnull AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    GlStateManager.pushMatrix();

    if (Minecraft.isAmbientOcclusionEnabled()) {
      GlStateManager.shadeModel(GL11.GL_SMOOTH);
    } else {
      GlStateManager.shadeModel(GL11.GL_FLAT);
    }

    GlStateManager.translate(-0.26, 0.5 + (entitylivingbaseIn.isSneaking() ? 0.1 : 0), 0);
    GlStateManager.rotate(-90, 0, 1, 0);
    GlStateManager.depthMask(false);
    GlStateManager.enableBlend();
    GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
    GlStateManager.depthMask(true);

    Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);

    GlStateManager.enableRescaleNormal();
    GlStateManager.enableAlpha();
    GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);

    // Get the player's shield
    ItemStack stack = getShieldStack(entitylivingbaseIn);
    IBakedModel bakedModel = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, null, Minecraft.getMinecraft().player);
    bakedModel = ForgeHooksClient.handleCameraTransforms(bakedModel, TransformType.GROUND, false);
    Minecraft.getMinecraft().getRenderItem().renderItem(stack, bakedModel);

    GlStateManager.disableRescaleNormal();
    Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
    GlStateManager.disableBlend();
    GlStateManager.color(1f, 1f, 1f, 1f);
    GlStateManager.popMatrix();
  }

  private ItemStack getShieldStack(@Nonnull EntityPlayer player) {
    IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
    if (handler != null) {
      ItemStack stack = handler.getStackInSlot(BaubleType.CHARM.getValidSlots()[0]);
      if (!stack.isEmpty() && stack.getItem() instanceof ISuperiorShield) {
        return stack;
      }
    }
    return ItemStack.EMPTY;
  }

  @Override
  public boolean shouldCombineTextures() {
    return false;
  }
}
