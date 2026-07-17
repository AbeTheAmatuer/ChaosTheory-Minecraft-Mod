package abe.chaostheory.client;

import abe.chaostheory.ChaosEntity;
import abe.chaostheory.ChaosTheory;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import java.util.Random;

public class ChaosEntityModel extends EntityModel<ChaosEntityRenderState> {
    private final ModelPart head;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftArm;
    private final ModelPart rightArm;


    public static int[] texturePoints;

    public ChaosEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild(PartNames.HEAD);
        this.leftLeg = root.getChild(PartNames.LEFT_LEG);
        this.rightLeg = root.getChild(PartNames.RIGHT_LEG);
        this.leftArm = root.getChild(PartNames.LEFT_ARM);
        this.rightArm = root.getChild(PartNames.RIGHT_ARM);
        //this.points = ChaosEntity.texturePoints;

    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition root = modelData.getRoot();

        //int rand = (int)(Math.random() * (max - min + 1)) + min;

        texturePoints = new int[12];
        for(int i = 0; i < 12; i++){
            texturePoints[i] = (int)(Math.random() * 600);
        }
        ChaosTheory.LOGGER.info("FROM ENTITYMODEL: JUST GENERATED NEW TEXTUREPOINTS------------");



        root.addOrReplaceChild(
                PartNames.BODY,
                CubeListBuilder.create().texOffs(texturePoints[0], texturePoints[1]).addBox(
                        /* x: */ -6,
                        /* y: */ -6,
                        /* z: */ -6,
                        /* width: */ (new Random().nextInt(25)),
                        /* height: */ 12,
                        /* depth: */ 12
                ),
                PartPose.offset(0, 8, 0)
        );
        root.addOrReplaceChild(
                PartNames.HEAD,
                CubeListBuilder.create().texOffs((int)(Math.random() * 600), (int)(Math.random() * 600)).addBox(-3, -6, -3, 6, 6, 6),
                PartPose.offset(0, 2, 0)
        );
        root.addOrReplaceChild(
                PartNames.LEFT_LEG,
                CubeListBuilder.create().texOffs((int)(Math.random() * 600), (int)(Math.random() * 600)).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(-2.5f, 14, 0)
        );
        root.addOrReplaceChild(
                PartNames.RIGHT_LEG,
                CubeListBuilder.create().texOffs((int)(Math.random() * 600), (int)(Math.random() * 600)).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(2.5f, 14, 0)
        );
        root.addOrReplaceChild(
                PartNames.LEFT_ARM,
                CubeListBuilder.create().texOffs((int)(Math.random() * 600), (int)(Math.random() * 600)).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(8, 2, 0)
        );
        root.addOrReplaceChild(
                PartNames.RIGHT_ARM,
                CubeListBuilder.create().texOffs((int)(Math.random() * 600), (int)(Math.random() * 600)).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(-8, 2, 0)
        );

        return LayerDefinition.create(modelData, 600, 600);
    }

    @Override
    public void setupAnim(ChaosEntityRenderState state) {
        super.setupAnim(state);

        this.head.xRot = state.xRot * Mth.RAD_TO_DEG;
        this.head.yRot = state.yRot * Mth.RAD_TO_DEG;
        float limbSwingAmplitude = state.walkAnimationSpeed;
        float limbSwingAnimationProgress = state.walkAnimationPos;

        float leftLimbRot =  Mth.cos(limbSwingAnimationProgress * 0.2f + Mth.PI) * 1.4f * limbSwingAmplitude;
        float rightLimbRot = Mth.cos(limbSwingAnimationProgress * 0.2f) * 1.4f * limbSwingAmplitude;

        this.leftLeg.xRot = leftLimbRot;
        this.rightLeg.xRot = rightLimbRot;
        this.leftArm.xRot = leftLimbRot;
        this.rightArm.xRot = rightLimbRot;
    }
}
