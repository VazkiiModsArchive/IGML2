/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.fetcher;

import java.util.ArrayList;
import java.util.List;

import vazkii.igml2.Lib;
import vazkii.igml2.Utils;

/**
 * ModDatabase
 *
 * Database of all mod lists loaded.
 *
 * @author Vazkii
 */
public class ModDatabase {

	/** List of all the Mod Lists loaded **/
	public static final List<ModList> listListings = new ArrayList();

	/** The list currently being loaded **/
	public static ModList activeList;

	public static void init() {
		List<String> lists = Utils.recieveWebpageData(Lib.MOD_LISTINGS_URL);
		for(String s : lists) {
			if(s.startsWith("#"))
				continue; // Commented line

			String[] tokens = s.split("=");
			if(tokens.length == 2) {
				String name = tokens[0];
				String url = tokens[1];
				ModList modList = new ModList(name, url);
				activeList = modList;
				modList.load();
				listListings.add(modList);
			}
		}
	}
}
