package twilightforest.client.renderer.entity;


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.TwilightForestMod;
import twilightforest.client.model.entity.ModelTFLich;
import twilightforest.entity.boss.EntityTFLich;

public class RenderTFLich extends RenderBiped<EntityTFLich> {
	public static final ResourceLocation LICH_TEXTURE = new ResourceLocation(TwilightForestMod.MODEL_DIR + "twilightlich64.png");

	public RenderTFLich(RenderManager manager, ModelBiped modelbiped, float shadowSize) {
		super(manager, modelbiped, shadowSize);
		addLayer(new LayerLichSpecials());
	}

	class LayerLichSpecials implements LayerRenderer<EntityTFLich> {
		private final ModelTFLich model = new ModelTFLich(true);

		@Override
		public void doRenderLayer(EntityTFLich lich, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			// TODO fix this
			GlStateManager.enableBlend();
//	        GlStateManager.disableAlpha();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (lich.isShadowClone()) {
				// clone alpha
				float shadow = 0.33f;
				GlStateManager.color(shadow, shadow, shadow, 0.8F);
				//	return 2;
			} else {
				// shield alpha (shield texture already has alpha)
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0f);
				//	return 1;
			}

			bindTexture(LICH_TEXTURE);
			model.setModelAttributes(RenderTFLich.this.getMainModel());
			model.render(lich, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		}

		@Override
		public boolean shouldCombineTextures() {
			return false;
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTFLich entity) {
		return LICH_TEXTURE;
	}

}
