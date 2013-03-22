/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Mar 2013
package vazkii.igml2.fetcher;

import net.minecraft.util.MathHelper;
import vazkii.igml2.fetcher.data.Availability;
import vazkii.igml2.fetcher.data.ForgeUsage;
import vazkii.igml2.fetcher.data.InfoType;

/**
 * ModData
 *
 * Data for a Mod Entry.
 *
 * @author Vazkii
 */
public class ModData {

	public String modName,
						 url,
						 author,
						 description;

	public ForgeUsage forgeUsage;
	public InfoType infoType;
	public Availability availability;

	/** Called as a safe check, case a mod wasn't
	 * loaded propperly **/
	public void fillNulls() {
		if(MathHelper.stringNullOrLengthZero(modName))
			modName = "???";

		if(url == null)
			url = "";

		if(MathHelper.stringNullOrLengthZero(author))
			author = "???";

		if(MathHelper.stringNullOrLengthZero(description))
			description = "No Description Available. :(";

		if(forgeUsage == null)
			forgeUsage = ForgeUsage.UNKNOWN;

		if(infoType == null)
			infoType = InfoType.REGULAR;

		if(availability == null)
			availability = Availability.UNKNOWN;
	}

	@Override
	public String toString() {
		return modName
				+ " by "
				+ author
				+ " @ "
				+ url
				+ ", "
				+ (infoType == InfoType.REGULAR ? "" : infoType.apiName)
				+ " "
				+ description
				+ "; "
				+ availability.getDisplayName()
				+ ", "
				+ forgeUsage.getToken();
	}

}
