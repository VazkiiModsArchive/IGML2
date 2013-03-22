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
import vazkii.igml2.fetcher.ModData;
import vazkii.igml2.fetcher.data.ForgeUsage;

/**
 * GuiModListContainer
 *
 * Container for mods in a list.
 *
 * @author Vazkii
 */
public class GuiModListContainer extends GuiSlot {

	GuiModList parent;
	FontRenderer font;

	public GuiModListContainer(GuiModList parent) {
		super(Minecraft.getMinecraft(), 300, parent.height, 32, parent.height - 32, 36);
		this.parent = parent;
		font = Minecraft.getMinecraft().fontRenderer;
	}

	@Override
	protected int getSize() {
		return parent.list.size();
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
		ModData mod = parent.list.get(var1);
		font.drawStringWithShadow(mod.modName + " by " + mod.author, var2 + 2, var3 + 1, mod.author.equals("Vazkii") || mod.author.equals("maxpowa") ? 0x5555FF :  0xFFFFFF);
		font.drawString(mod.availability.getDisplayName(), var2 + 6, var3 + 12, 0x444444);
		String index = var1 + 1 + " |";
		font.drawString(index, var2 - 6 - font.getStringWidth(index), var3 + 12, 0x444444);
		ForgeUsage usage = mod.forgeUsage;
		font.drawString(usage.getToken(), var2 + 6, var3 + 23, 0x444444);
	}
}
