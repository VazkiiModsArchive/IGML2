/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2;

/**
 * Lib
 *
 * Library class with data to reference during the code
 * process.
 *
 * @author Vazkii
 */
public final class Lib {

	/** Mods that won't currently load descriptions due to the formatting. These will
	 * get fixed. But by ZeroLevels, I don't maintain the list -- Vazkii **/
	public static final String[] EXCLUSIONS = {
		"Forestry",
		"Rei's Minimap",
		"Sign Tags",
		"Trail Mix"
	};

	/** @Mod annotation data **/
	public static final String MOD_ID = "IGML2",
							   MOD_NAME = "Ingame Mod List 2",
							   VERSION = "1.0";

	/** The URL of the Mod List page **/
	public static final String MODLIST_URL = "http://modlist.mcf.li/";

	/** URL to search for the mod listings **/
	public static final String MOD_LISTINGS_URL = "http://modlist.mcf.li/resources/igml2/listListing.txt";

	/** URL for the ModLoader link **/
	public static final String MODLOADER_URL = "bit.ly/modloader";

	/** Description for mods that use ModLoader **/
	public static final String MODLOADER_DESC = "ModLoader can be used instead of Forge for this Mod.";

	/** Line to try and find to start loading mods **/
	public static final String START_LINE = "<tbody>";

	/** Line to indicate that mod loading is done **/
	public static final String END_LINE = "</tbody>";

	/** Line to try and find to start loading a specific mod **/
	public static final String MOD_START_LINE = "<tr>";

	/** Line to indicate that specific mod loading is done **/
	public static final String MOD_END_LINE = "</tr>";

	/** Generic line for ModLoader mods **/
	public static final String MODLOADER_DESC_LINE = "<td class=\"ml desctt\"><span class=\"tt\"><center><big class=\"d\">ModLoader Mod</big><br/><small class=\"d\">ModLoader can be used instead of Forge for this mod.</small><br/>See <i class=\"d\">bit.ly/modloader</i></center><br/>";

	/** Generic line for Custom API mods **/
	public static final String CUSTOM_API_LINE = "<td class=\"d desctt\"><span class=\"tt\"><center><big class=\"d\">";
}
