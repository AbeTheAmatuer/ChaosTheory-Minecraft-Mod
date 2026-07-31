package abe.chaostheory;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ChaosTheory implements ModInitializer {
	public static final String MOD_ID = "chaostheory";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		for(int i = 0; i < 20; i++){
			generateTexture(i);
		}
		ModEntityTypes.registerAttributes();
		ModEntityTypes.registerModEntityTypes();
		PayloadTypeRegistry.clientboundPlay().register(ChaosEntityPayload.TYPE, ChaosEntityPayload.CODEC);

	}
	public String generateTexture(int ind){
		int width = 64, height = 32;


		Random rand = new Random();

		int initial = rand.nextInt(256);
		int offsetR = rand.nextInt(30);
		int offsetG = rand.nextInt(30);
		int offsetB = rand.nextInt(30);
		//int alpha = rand.nextInt(256);
		// Create buffered image object
		BufferedImage img = null;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// file object
		File f = null;

		// create random values pixel by pixel
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// generating values less than 256
				int a = (int) (100);
				int r = (int) (initial + (y + offsetR));
				int g = (int) (initial + (Math.abs(Math.round(Math.sin(x)))));
				int b = (int) (initial + (Math.abs(Math.round(Math.cos(x)))));
				offsetR += 10;
//* Math.abs(offsetG*Math.sin(rand.nextInt(100)))
				if(initial > 256){
					initial = rand.nextInt(256);
				}
				//pixel
				int p = (a << 24) | (r << 16) | (g << 8) | b;

				img.setRGB(x, y, p);
			}
		}
//chaostheory-template-26.2/src/main/resources/assets/chaostheory/textures/entity/random_texture" + entityID + ".png
		// write image
		try {
			f = new File("/Users/sirabe/Downloads/chaostheory-template-26.2/src/main/resources/assets/chaostheory/textures/entity/random" + ind + ".png");
			ImageIO.write(img, "png", f);
			ChaosTheory.LOGGER.info("------- FILE CORRECTLY MADE AT " + f.getAbsolutePath());
		} catch (IOException e) {
			ChaosTheory.LOGGER.warn("---------Filemake Error: " + e);
		}

		return "textures/entity/random" + ind + ".png";
	}



	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
