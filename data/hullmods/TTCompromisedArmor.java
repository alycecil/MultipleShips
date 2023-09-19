package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.hullmods.CompromisedStructure;

public class TTCompromisedArmor extends BaseHullMod {
	public static final float ARMOR_PENALTY_MULT = 0.9f;
	
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		float effect = stats.getDynamic().getValue(Stats.DMOD_EFFECT_MULT);
		float mult = ARMOR_PENALTY_MULT + (1f - ARMOR_PENALTY_MULT) * (1f - effect);
		
		stats.getArmorBonus().modifyMult(id, mult);
	}
	
	public String getDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
		if (index == 0) {
			float effect = 1f;
			if (ship != null) effect = ship.getMutableStats().getDynamic().getValue(Stats.DMOD_EFFECT_MULT);

			float mult = ARMOR_PENALTY_MULT + (1f - ARMOR_PENALTY_MULT) * (1f - effect);

			return "" + (int) Math.round((1f - mult) * 100f) + "%";
		}
		return null;
	}
}
