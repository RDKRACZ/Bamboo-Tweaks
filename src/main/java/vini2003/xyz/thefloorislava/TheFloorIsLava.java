package vini2003.xyz.thefloorislava;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import vini2003.xyz.thefloorislava.registry.common.TheFloorIsLavaFeatures;

public class TheFloorIsLava implements ModInitializer {
	public static final String ID = "thefloorislava";
	
	public static Identifier identifier(String path) {
		return new Identifier(ID, path);
	}
	
	@Override
	public void onInitialize() {
		TheFloorIsLavaFeatures.initialize();
	}
}
