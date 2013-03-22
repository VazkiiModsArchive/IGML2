/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.fetcher.data;

/**
 * Availability
 *
 * For which formats is a mod available.
 *
 * @author Vazkii
 */
public enum Availability {

	CLIENTSIDE("Clientside Mod", "Clientside Mod"),
	UNIVERSAL("Universal", "Universal Mod"),
	SSP_SMP_LAN("SSP SMP LAN", "Single/Multiplayer/LAN"),
	SSP_SMP("SSP SMP", "Single/Multiplayer"),
	SSP_LAN("SSP LAN", "LAN Compatible"),
	SMP_LAN("SMP LAN", "Multiplayer/LAN"),
	SSP("SSP", "Singleplayer Exclusive"),
	SMP("SMP", "Multiplayer Exclusive"),
	UNKNOWN("???", "Unknown");

	private Availability(String token, String displayName) {
		this.token = token;
		this.displayName = displayName;
	}

	private String token, displayName;

	public String getToken() {
		return token;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Availability parseToken(String token) {
		for(Availability type : Availability.class.getEnumConstants())
			if(type.getToken().equalsIgnoreCase(token))
				return type;

		return UNKNOWN;
	}

}
