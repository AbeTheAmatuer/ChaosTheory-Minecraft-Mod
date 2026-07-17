package abe.chaostheory.client;

import abe.chaostheory.ChaosEntity;
import abe.chaostheory.ChaosTheory;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import java.util.Random;

public class ChaosEntityRenderer extends MobRenderer<ChaosEntity, ChaosEntityRenderState, ChaosEntityModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(ChaosTheory.MOD_ID, "textures/entity/megatexture2.png");

    String[] TEXTURES = {"chaos_entity.png", "megatexture2.png", "me.png"};

    private EntityRendererProvider.Context contextCopy;
    private String path;

    public ChaosEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new ChaosEntityModel(context.bakeLayer(ModEntityModelLayers.CHAOS_ENTITY)), 0.375f); // 0.375 shadow radius
        path = "textures/entity/" + TEXTURES[(new Random()).nextInt(3)];
        contextCopy = context;
    }

    @Override
    public ChaosEntityRenderState createRenderState() {
        return new ChaosEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(ChaosEntityRenderState state) {
        super.model = new ChaosEntityModel(contextCopy.bakeLayer(ModEntityModelLayers.CHAOS_ENTITY));
        return Identifier.fromNamespaceAndPath(ChaosTheory.MOD_ID, path);
    }
}
