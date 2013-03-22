/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2;

import java.util.logging.Logger;

import vazkii.igml2.fetcher.ModDatabase;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * IngameModList
 *
 * Ingame Mod List main class.
 *
 * @author Vazkii
 */
@Mod(modid = Lib.MOD_ID, name = Lib.MOD_NAME, version = Lib.VERSION)
public final class IngameModList {

	public static boolean isOnline;

	public static Logger logger;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}

	@Init
	public void init(FMLInitializationEvent event) {
		isOnline = Utils.isOnline();

		KeyBindingRegistry.registerKeyBinding(new KeybindHandler());

		if(!isOnline)
			return;
		// The client isn't online, no need for anything else

		ModDatabase.init();
	}
}
