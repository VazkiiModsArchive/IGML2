/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.fetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import vazkii.igml2.IngameModList;
import vazkii.igml2.Lib;
import vazkii.igml2.Utils;
import vazkii.igml2.fetcher.data.Availability;
import vazkii.igml2.fetcher.data.ForgeUsage;
import vazkii.igml2.fetcher.data.InfoType;

/**
 * ModList
 *
 * A Mod List.
 *
 * @author Vazkii
 */
public class ModList {

	/** The mod currently being loaded **/
	public ModData currentMod;

	public List<ModData> mods = new ArrayList();

	/** Mod List data **/
	public String name, url;

	public ModList(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public void load() {
		List<String> lines = Utils.recieveWebpageData(url);
		if(lines == null)
			return; // Seems like no connection happened.

		/** The token used to start mod reading appears
		 * twice in the code, once before it is meant to
		 * be read, this prevents it from triggering during
		 * the first occurence **/
		boolean foundFirstToken = false;

		/**  Are the mods currently being loaded? If
		 * so, each line will be parsed accordingly. **/
		boolean loadingMods = false;

		/** Is there a mod currently being in the process
		 * of loading? **/
		boolean isLoadingSpecificMod = false;

		/** What type of line was the last one? (name/url,
		 * info, author, availability, etc) **/
		String lastLineType = "";

		for(String line : lines) { // Iterate trough the lines, find the mod data
			line = line.replaceAll("	", "    "); // Remove the TAB character
			line = line.trim(); // Trim the line, you never know...

			if(line.equals(Lib.START_LINE))
				if(!foundFirstToken)
					foundFirstToken = true;
				else {
					loadingMods = true;
					continue;
				}

			if(line.equals(Lib.END_LINE) && loadingMods)
				break; // Found the end of the mod list

			if(line.startsWith("<!--") || line.isEmpty())
				continue; // Comment line, nothing to do here...

			if(!isLoadingSpecificMod && line.startsWith(Lib.MOD_START_LINE)) {
				isLoadingSpecificMod = true;
				currentMod = new ModData();
				continue;
			}

			if(isLoadingSpecificMod) {
				if(line.startsWith("<td><a href=")) { // Name and URL line
					String afterStart = line.substring("<td><a href=".length() + 1);
					int firstQuotes = afterStart.indexOf('"');
					String url = afterStart.substring(0, firstQuotes);
					String afterUrl = afterStart.substring(firstQuotes + 2);
					int end = afterUrl.indexOf("<");
					String name = afterUrl.substring(0, end);

					currentMod.url = url;
					currentMod.modName = name;

					lastLineType = "name";

					continue;
				}

				if(lastLineType.equals("name")) { // Must be a description line now
					boolean isExclusion = false;
					for(String s : Lib.EXCLUSIONS)
						if(currentMod.modName.equalsIgnoreCase(s))
							isExclusion = true;

					if(!isExclusion) {
						String afterStart = line.substring("<td class=".length() + 1);
						int firstQuotes = afterStart.indexOf('"');
						String type = afterStart.substring(0, firstQuotes);
						String afterType = afterStart.substring(firstQuotes + "\"><span class=\"tt\">".length());
						if(type.equals("ml desctt"))
							afterType = line.substring(Lib.MODLOADER_DESC_LINE.length());
						int end = afterType.indexOf("<");
						String desc = afterType.substring(0, end);

						currentMod.description = desc;
						currentMod.infoType = InfoType.fromToken(type, line);
					}

					lastLineType = "desc";

					continue;
				}

				if(lastLineType.equals("desc")) { // Must be an author line now
					String author = line.replaceAll("<td>", "").replaceAll("</td>", "");
					currentMod.author = author;

					lastLineType = "author";

					continue;
				}

				if(lastLineType.equals("author")) { // Must be the availability line now
					String token = line.replaceAll("<td>", "").replaceAll("</td>", "");
					currentMod.availability = Availability.parseToken(token);

					lastLineType = "avail";

					continue;
				}

				if(lastLineType.equals("avail")) { // Must be the forge usage line now
					currentMod.forgeUsage = ForgeUsage.parseToken(line);

					lastLineType = "forge";

					continue;
				}

				if(line.equalsIgnoreCase(Lib.MOD_END_LINE) && currentMod != null) { // End the loading of the current mod
					currentMod.fillNulls();
					IngameModList.logger.log(Level.INFO, "Loaded Mod [" + currentMod + "] on list " + name);
					mods.add(currentMod);
					currentMod = null;
					isLoadingSpecificMod = false;

					continue;
				}
			}
		}
	}

}
