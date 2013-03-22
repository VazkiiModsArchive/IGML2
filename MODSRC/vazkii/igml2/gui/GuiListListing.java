/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.gui;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import vazkii.igml2.fetcher.ModDatabase;

/**
 * GuiListListing
 *
 * Gui that lists the mod lists available.
 *
 * @author Vazkii
 */
public class GuiListListing extends GuiScreen implements IModGui {

	public int selected = -1;

	private GuiListListingContainer container;

	@Override
	public void initGui() {
		container = new GuiListListingContainer(this);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		container.drawScreen(par1, par2, par3);
		if (ModDatabase.listListings.isEmpty()) {
			GL11.glPushMatrix();
			GL11.glScalef(2F, 2F, 2F);
			drawCenteredString(fontRenderer, "No Mods Found...", width / 4, 40, 0xFF0000);
			GL11.glPopMatrix();
			drawCenteredString(fontRenderer, "Maybe modlist.mcf.li is down?", width / 2, 104, 0xFFFFFF);
		}
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		drawCenteredString(fontRenderer, "Minecraft Mod Lists:", width / 4, 4, 0x4444FF);
		GL11.glPopMatrix();
		drawCenteredString(fontRenderer, "Double Click a list to Browse it!", width / 2, height - 20, 0xFF4444);
		super.drawScreen(par1, par2, par3);
	}

	public void select(int i, boolean doubleClick) {
		selected = i;
		if (doubleClick)
			mc.displayGuiScreen(new GuiModList(ModDatabase.listListings.get(selected)));
	}
}
