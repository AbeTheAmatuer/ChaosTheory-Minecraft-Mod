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

    static int[] texturePoints = new int[12];

    public ChaosEntityModel(ModelPart root) {
        super(root);
        this.head = root.getChild(PartNames.HEAD);
        this.leftLeg = root.getChild(PartNames.LEFT_LEG);
        this.rightLeg = root.getChild(PartNames.RIGHT_LEG);
        this.leftArm = root.getChild(PartNames.LEFT_ARM);
        this.rightArm = root.getChild(PartNames.RIGHT_ARM);
        for(int i = 0; i < 12; i++){
            texturePoints[i] = (int)(Math.random() * 600);
        }

    }

   /* public ChaosEntityModel(ModelPart root, int[] points) {
        super(root);
        this.head = root.getChild(PartNames.HEAD);
        this.leftLeg = root.getChild(PartNames.LEFT_LEG);
        this.rightLeg = root.getChild(PartNames.RIGHT_LEG);
        this.leftArm = root.getChild(PartNames.LEFT_ARM);
        this.rightArm = root.getChild(PartNames.RIGHT_ARM);
        this.texturePoints = points;
    }*/

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition root = modelData.getRoot();

        //int rand = (int)(Math.random() * (max - min + 1)) + min;


        for(int i = 0; i < 12; i++){
            texturePoints[i] = (int)(Math.random() * 600);
        }

        root.addOrReplaceChild(
                PartNames.BODY,
                CubeListBuilder.create().addBox(
                        /* x: */ -6,
                        /* y: */ -6,
                        /* z: */ -6,
                        /* width: */ 12,
                        /* height: */ 12,
                        /* depth: */ 12
                ),
                PartPose.offset(0, 8, 0)
        );
        root.addOrReplaceChild(
                PartNames.HEAD,
                CubeListBuilder.create().texOffs(36, 0).addBox(-3, -6, -3, 6, 6, 6),
                PartPose.offset(0, 2, 0)
        );
        root.addOrReplaceChild(
                PartNames.LEFT_LEG,
                CubeListBuilder.create().texOffs(48, 12).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(-2.5f, 14, 0)
        );
        root.addOrReplaceChild(
                PartNames.RIGHT_LEG,
                CubeListBuilder.create().texOffs(48, 12).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(2.5f, 14, 0)
        );
        root.addOrReplaceChild(
                PartNames.LEFT_ARM,
                CubeListBuilder.create().texOffs(48, 12).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(8, 2, 0)
        );
        root.addOrReplaceChild(
                PartNames.RIGHT_ARM,
                CubeListBuilder.create().texOffs(48, 12).addBox(-2, 0, -2, 4, 10, 4),
                PartPose.offset(-8, 2, 0)
        );
        return LayerDefinition.create(modelData, 64, 32);
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
