package abe.chaostheory.client;

import abe.chaostheory.ModEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class ChaosTheoryClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModEntityModelLayers.registerModelLayers();
		//EntityRenderers.register(ModEntityTypes.CHAOS_ENTITY, ChaosEntityRenderer::new);
	}
}