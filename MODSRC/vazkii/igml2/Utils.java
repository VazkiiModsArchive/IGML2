/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils
 *
 * Utilities for the mod.
 *
 * @author Vazkii
 */
public final class Utils {

	public static void openWebpage(String pageUrl) {
		try {
			if(Desktop.isDesktopSupported())
				Desktop.getDesktop().browse(new URL(pageUrl).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> recieveWebpageData(String pageUrl) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(pageUrl).openStream()));
			List<String> lines = new ArrayList();
			String line = null;
			while ((line = bufferedReader.readLine()) != null)
				lines.add(line);
			bufferedReader.close();
			return lines;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isOnline() {
		try {
			URL url = new URL(Lib.MODLIST_URL);
			url.openConnection();
			return true;
		} catch(Exception e) {
			return false; // The exception was thrown, no connection was opened
		}
	}

}
