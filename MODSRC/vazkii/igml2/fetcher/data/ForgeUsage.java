/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.fetcher.data;

/**
 * ForgeUsage
 *
 * Usage of the Forge API.
 *
 * @author Vazkii
 */
public enum ForgeUsage {

	REQUIRES("Forge Required"),
	COMPATIBLE("Forge Compatible"),
	INCOMPATIBLE("Not Forge Compatible"),
	FORGE("Forge Itself"), // Forge Itself
	UNKNOWN("???"); // Used to not return null

	private ForgeUsage(String token) {
		this.token = token;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public static ForgeUsage parseToken(String token) {
		for(ForgeUsage type : ForgeUsage.class.getEnumConstants())
			if(token.contains(type.getToken()))
				return type;

		return UNKNOWN;
	}
}
