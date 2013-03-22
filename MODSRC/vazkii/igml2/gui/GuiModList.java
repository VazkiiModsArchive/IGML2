/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import vazkii.igml2.Utils;
import vazkii.igml2.fetcher.ModData;
import vazkii.igml2.fetcher.ModList;

/**
 * GuiModList
 *
 * Gui for a specific list.
 *
 * @author Vazkii
 */
public class GuiModList extends GuiScreen implements IModGui {

	public ModList originalList;
	public List<ModData> list;
	public int selected = -1;

	public GuiModListContainer container;
	private GuiTextField textField;

	List<String> description = new ArrayList();

	public GuiModList(ModList list) {
		originalList = list;
		this.list = originalList.mods;
	}

	@Override
	public void initGui() {
		container = new GuiModListContainer(this);
		buttonList.clear();
		buttonList.add(new GuiButton(0, 25, height - 25, "Back"));
		buttonList.add(new GuiButton(1, width - 240, height - 45, "Get the Mod!"));
		((GuiButton) buttonList.get(1)).drawButton = false;
		textField = new GuiTextField(fontRenderer, width - 210, 6, 180, 16);
		textField.setFocused(true);
		textField.setCanLoseFocus(false);
		textField.setMaxStringLength(35);
		asignDescription();
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		textField.textboxKeyTyped(par1, par2);
		if (MathHelper.stringNullOrLengthZero(textField.getText()))
			list = originalList.mods;
		else {
			List<ModData> mods = new ArrayList();
			for (ModData mod : originalList.mods)
				if ((mod.modName + " by " + mod.author).toLowerCase().contains(textField.getText().toLowerCase()))
					mods.add(mod);
			list = mods;
		}

		if(list.isEmpty())
			((GuiButton) buttonList.get(1)).drawButton = false;
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 0)
            mc.displayGuiScreen(new GuiListListing());
		else Utils.openWebpage(list.get(selected).url);

		super.actionPerformed(par1GuiButton);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		container.drawScreen(par1, par2, par3);
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		drawCenteredString(fontRenderer, originalList.name + " List:", 75, 4, 0x4444FF);
		GL11.glPopMatrix();

		boolean drawText = !MathHelper.stringNullOrLengthZero(textField.getText());

		if (list.isEmpty()) {
			GL11.glPushMatrix();
			GL11.glScalef(2F, 2F, 2F);
			drawCenteredString(fontRenderer, "No Mods Found...", 75, 40, 0xFF0000);
			GL11.glPopMatrix();
			drawCenteredString(fontRenderer, originalList.mods.isEmpty() ? "Something went wrong..." : "Search for something else, I guess", 150, 104, 0xFFFFFF);
		}

		int trySelect = Math.min(list.size() - 1, selected);
		if(trySelect != selected)
			select(trySelect);

		if(selected != -1) {
			ModData mod = list.get(selected);
			GL11.glPushMatrix();
			GL11.glScalef(2F, 2F, 2F);
			fontRenderer.drawStringWithShadow(mod.modName, 155, 25, 0xFFFF33);
			GL11.glPopMatrix();
			fontRenderer.drawStringWithShadow("Mod by " + mod.author, 315, 70, 0x77FF77);

			boolean vazkiiMod = mod.author.equals("Vazkii") || mod.author.equals("maxpowa");
			if(vazkiiMod)
				fontRenderer.drawStringWithShadow("(Mod by the creator of the Ingame Mod List mod!)", 317, 83, 0xFF8888);

			int i1 = vazkiiMod ? 100 : 90;
			fontRenderer.setUnicodeFlag(true);
			for(String s : description) {
				fontRenderer.drawStringWithShadow(s.trim(), 325, i1, 0xFFFFFF);
				i1 += 11;
			}
			fontRenderer.setUnicodeFlag(false);
		}

		if (drawText)
			textField.drawTextBox();

		super.drawScreen(par1, par2, par3);
	}


	public void select(int sel) {
		selected = sel;
		((GuiButton) buttonList.get(1)).drawButton = sel != -1;
	}

	public void asignDescription() {
		description.clear();
		if(selected != -1) {
			ModData mod = list.get(selected);
			String descString = mod.description;
			int maxLenght = width - 340;
			System.out.println(descString);
			fontRenderer.setUnicodeFlag(true);
			while(fontRenderer.getStringWidth(descString) > maxLenght) {
				int lastSpace = 0;
				int i = 0;
				for(char c : (descString + " ").toCharArray()) {
					if(Character.isWhitespace(c)) {
						int width = fontRenderer.getStringWidth(descString.substring(0, i));
						System.out.println(width + " " + maxLenght);
						if(width >= maxLenght) {
							String newString = descString.substring(0, lastSpace);
							System.out.println(newString);
							description.add(newString);
							descString = descString.substring(lastSpace);
							i -= lastSpace;
							break;
						}

						lastSpace = i;
					}
					i++;
				}
			}
			fontRenderer.setUnicodeFlag(false);
			description.add(descString);
		}
	}
}
