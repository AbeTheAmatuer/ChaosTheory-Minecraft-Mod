package abe.chaostheory.client;

import abe.chaostheory.ChaosTheory;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModEntityModelLayers {
    public static final ModelLayerLocation CHAOS_ENTITY = createMain("Chaos_Entity");

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(ChaosTheory.MOD_ID, name), "main");
    }

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(ModEntityModelLayers.CHAOS_ENTITY, ChaosEntityModel::getTexturedModelData);
    }
}