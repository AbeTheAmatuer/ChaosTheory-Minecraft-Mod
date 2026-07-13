package abe.chaostheory.client;

import abe.chaostheory.ChaosEntity;
import abe.chaostheory.ChaosEntityPayload;
import abe.chaostheory.ChaosTheory;
import abe.chaostheory.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.mixin.client.rendering.ModelLayersAccessor;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class ChaosTheoryClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityModelLayers.registerModelLayers();
		EntityRenderers.register(ModEntityTypes.CHAOS_ENTITY, ChaosEntityRenderer::new);

		ClientPlayNetworking.registerGlobalReceiver(ChaosEntityPayload.TYPE, (payload, context) -> {
			ClientLevel level = context.client().level;

			//ChaosEntityModel.getTexturedModelData();
			//ModelLayersAccessor.getLayers().add(ModEntityModelLayers.CHAOS_ENTITY);

			if(level == null){
				return;
			}
		});
	}
}