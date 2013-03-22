/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.fetcher.data;

import vazkii.igml2.Lib;

/**
 * InfoType
 *
 * Type of Info, Regular info, modloader
 * or other API requirement.
 *
 * @author Vazkii
 */
public class InfoType {

	public static final InfoType REGULAR = new InfoType("desctt");
	public static final InfoType MODLOADER = new InfoType("ml desctt", "ModLoader Mod", Lib.MODLOADER_DESC);

	/** Token to look for **/
	String token;

	/** If the mod uses a custom API, links to it, and gives it's name **/
	public String apiName, apiDescription;

	public InfoType(String token) {
		this(token, null);
	}

	public InfoType(String token, String apiName) {
		this(token, apiName, null);
	}

	public InfoType(String token, String apiName, String apiDescription) {
		this.token = token;
		this.apiName = apiName;
		this.apiDescription = apiDescription;
	}

	public static InfoType fromToken(String token, String fullLine) {
		if(token.equalsIgnoreCase(REGULAR.token))
			return REGULAR;

		if(token.equalsIgnoreCase(MODLOADER.token))
			return REGULAR;

		if(token.equalsIgnoreCase("d desctt"))
			return customRequirement(fullLine);

		new IllegalArgumentException(token + " is not a valid InfoType token!").printStackTrace();
		return REGULAR; // Not a valid token, default to regular type.
	}

	public static InfoType customRequirement(String fullLine) {
		String afterStart = fullLine.substring(Lib.CUSTOM_API_LINE.length());
		int end = afterStart.indexOf("<");
		String requirement = afterStart.substring(0, end);
		if(requirement.endsWith(":"))
			requirement = requirement.substring(0, requirement.length() - 1);
		String afterEnd = fullLine.substring(end);
		int firstBr = afterEnd.indexOf("<br/>");
		String afterFirstBr = afterEnd.substring(firstBr + 4, afterEnd.length() - 1);
		int secondBr = afterFirstBr.indexOf("<br/>");
		String afterSecondBr = afterFirstBr.substring(secondBr + 5);
		int descEnd = afterSecondBr.indexOf("<");
		String desc = afterSecondBr.substring(0, descEnd);


		return new InfoType("", requirement, desc);
	}
}
