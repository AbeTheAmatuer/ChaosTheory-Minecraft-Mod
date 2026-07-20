package abe.chaostheory.client;


import abe.chaostheory.ChaosTheory;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

import java.util.Random;

public class ChaosEntityRenderState extends LivingEntityRenderState {

    String[] TEXTURES = {"chaos_entity.png", "megatexture2.png", "me.png"};
    int[] offsetPointsState;
    public String texturePathState;

    public ChaosEntityRenderState(){
        texturePathState = "textures/entity/default.png";
        offsetPointsState = new int[12];
    }

}