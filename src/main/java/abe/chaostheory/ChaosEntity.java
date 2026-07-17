package abe.chaostheory;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class ChaosEntity extends PathfinderMob {

    private static final EntityDataAccessor<Boolean> SPAWNED = SynchedEntityData.defineId(ChaosEntity.class, EntityDataSerializers.BOOLEAN);
    String[] TEXTURES = {"chaos_entity.png", "megatexture2.png", "me.png"};
    public String texturePath;

    public ChaosEntity(Level world) {
        this(ModEntityTypes.CHAOS_ENTITY, world);
    }

    public static Integer[] texturePoints = {(int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600), (int)(Math.random() * 600)};

    public ChaosEntity(EntityType<? extends ChaosEntity> entityType, Level world) {
        super(entityType, world);

        texturePath = "textures/entity/" + TEXTURES[(new Random()).nextInt(3)];

        texturePoints = new Integer[12];
        for(int i = 0; i < 12; i++){
            texturePoints[i] = (int)(Math.random() * 600);
        }


        ChaosTheory.LOGGER.info("---------------randomized texture is " + texturePath);

        ChaosEntityPayload payload = new ChaosEntityPayload(new BlockPos(0, 0, 0));

        if (level() instanceof ServerLevel){
            for(ServerPlayer player: PlayerLookup.level((ServerLevel) level())){
                ServerPlayNetworking.send(player, payload);
            }
            //ChaosTheory.LOGGER.info("PAYLOAD SENT YAYAYA");
        }else{
           // ChaosTheory.LOGGER.info("PAYLOAD NOT SENT FUCKFUCKFUCKFUCK");
        }
    }

    //public int[] getTexturePoints(){return texturePoints;}

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SPAWNED, false);
    }

    public boolean getSpawned() {
        return entityData.get(SPAWNED);
    }

    private void setSpawned(boolean update) {
        entityData.set(SPAWNED, update);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        super.onSyncedDataUpdated(data);
    }


    public static AttributeSupplier.Builder createCubeAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.TEMPT_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.3);
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TemptGoal(this, 1, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Cow.class, 4));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }
}