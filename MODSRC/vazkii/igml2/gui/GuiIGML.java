/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import vazkii.igml2.Lib;
import vazkii.igml2.Utils;

/**
 * GuiIGML
 *
 * The Main GUI for the Ingame Mod List
 *
 * @author Vazkii
 */
public class GuiIGML extends GuiScreen implements IModGui {

	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 - 100, 110, "Browse Mods!"));
		buttonList.add(new GuiButton(1, width / 2 - 100, 135, "Browse Mods Online"));
		buttonList.add(new GuiButton(2, width / 2 - 100, 170, "Back"));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 0) {
            mc.displayGuiScreen(new GuiListListing());
		} else if(par1GuiButton.id == 1) {
			Utils.openWebpage(Lib.MODLIST_URL);
		} else if(par1GuiButton.id == 2){
            mc.displayGuiScreen((GuiScreen)null);
            mc.setIngameFocus();
		}
		super.actionPerformed(par1GuiButton);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawWorldBackground(0);

		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		drawCenteredString(fontRenderer, "Ingame Mod List!", width / 4, 12, 0x1111AA);
		GL11.glPopMatrix();

		drawCenteredString(fontRenderer, "modlist.mcf.li", width / 2, 156, 0xFFFFFF);

		drawCenteredString(fontRenderer, "List maintained by ZeroLevels and catqueen5 and hosted by criticsquid.", width / 2, 45, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Mod coded and designed by Vazkii.", width / 2, 60, 0xFFFFFF);

		drawCenteredString(fontRenderer, "How To Use:", width / 2, 210, 0xFF0000);
		drawCenteredString(fontRenderer, "Press Browse mods, and pick the list of mods you want to browse.", width / 2, 230, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Click on a mod to see info on it, you can search mods by simply", width / 2, 240, 0xFFFFFF);
		drawCenteredString(fontRenderer, "typing while browsing a mod list. A search bar will appear automatically.", width / 2, 250, 0xFFFFFF);

		super.drawScreen(par1, par2, par3);
	}
}
