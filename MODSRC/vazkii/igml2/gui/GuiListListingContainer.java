/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import vazkii.igml2.fetcher.ModDatabase;
import vazkii.igml2.fetcher.ModList;

/**
 * GuiListListingContainer
 *
 * Container for List entries.
 *
 * @author Vazkii
 */
public class GuiListListingContainer extends GuiSlot {

	GuiListListing parent;
	FontRenderer font;

	public GuiListListingContainer(GuiListListing parent) {
		super(Minecraft.getMinecraft(), parent.width, parent.height, 32, parent.height - 32, 36);
		this.parent = parent;
		font = Minecraft.getMinecraft().fontRenderer;
	}

	@Override
	protected int getSize() {
		return ModDatabase.listListings.size();
	}

	@Override
	protected void elementClicked(int var1, boolean var2) {
		parent.select(var1, var2);
	}

	@Override
	protected boolean isSelected(int var1) {
		return parent.selected == var1;
	}

	@Override
	protected void drawBackground() {
		parent.drawBackground(0);
	}

	@Override
	protected int getContentHeight() {
		return getSize() * 36;
	}

	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) {
		ModList list = ModDatabase.listListings.get(var1);
		font.drawStringWithShadow(list.name + " List", var2 + 4, var3 + 6, 0xFFFFFF);
		font.drawString(list.mods.size() + " mods", var2 + 10, var3 + 18, 0x888888);
	}
}
