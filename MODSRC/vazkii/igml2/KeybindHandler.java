/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import vazkii.igml2.gui.GuiIGML;
import vazkii.igml2.gui.IModGui;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

/**
 * KeybindHandler
 *
 * Handler for the mod's Keybind.
 *
 * @author Vazkii
 */
public class KeybindHandler extends KeyHandler {

	private static KeyBinding key = new KeyBinding("List of Mods (Minecraft Forums)", Keyboard.KEY_F12);

	public KeybindHandler() {
		super(new KeyBinding[] { key }, new boolean[] { false });
	}

	@Override
	public String getLabel() {
		return Lib.MOD_ID;
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		// NO-OP
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen == null || !(mc.currentScreen instanceof IModGui))
			mc.displayGuiScreen(new GuiIGML());
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

}
