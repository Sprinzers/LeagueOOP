package champion;

import util.Constants;

public class Wizard extends Champion {

    Wizard(final int newPosX, final int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.WIZARD_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.WIZARD_TERRAIN_MODIFIER);
        setHp(Constants.WIZARD_HP);
        setHpStart(Constants.WIZARD_HP);
        setHpGrowth(Constants.WIZARD_HP_GROWTH);
        setXp();
        setLevel();
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.WIZARD_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.WIZARD_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.WIZARD_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.WIZARD_ABILITY_2_GROWTH);
        setRoundCounter();
    }

    /**
     * Method is used to complete the double-dispatch mechanism.
     * @param champion that attacks "this"
     */
    @Override
    public void isAttackedBy(final Champion champion) {
        champion.attack(this);
    }

    /**
     *  Method is used to calculate the damage against a knight.
     * @param knight opponent champion.
     */
    @Override
    public void attack(final Knight knight) {
        // base damage percentages
        float percentageFirst = firstAbility();
        float percentageSecond = secondAbility();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
            percentageSecond += percentageSecond * getTerrainModifier();
        }
        // race modifier
        percentageFirst += percentageFirst * Constants.MODIFIER_20;
        percentageSecond += percentageSecond * Constants.MODIFIER_40;
        // damage to apply from first ability
        float firstDamage = percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * knight.calculateTeoreticalHp(), knight.getHp());

        if (percentageSecond > Constants.WIZARD_ABILITY_2_CAP) {
            percentageSecond = Constants.WIZARD_ABILITY_2_CAP;
        }
        // calculate taken damage
        float dmgTakenFirst = knight.firstAbility();
        float dmgTakenSecond = knight.secondAbility();
        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * knight.getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }
        if (this.getHpBeforeRound() <= this.calculateTeoreticalHp() * executePercent) {
            // wizard will die from execute
            int executeDamage = this.getHp();
            float damageReflected = percentageSecond * executeDamage;
            // apply damage to enemy
            int totalDamageToGive = Math.round(firstDamage) + Math.round(damageReflected);
            knight.reduceHP(totalDamageToGive);
        } else {
            if (knight.getApplyTerrainModifier()) {
                dmgTakenFirst += dmgTakenFirst * knight.getTerrainModifier();
                dmgTakenSecond += dmgTakenSecond * knight.getTerrainModifier();
            }
            int totalDamageTaken = Math.round(dmgTakenFirst) + Math.round(dmgTakenSecond);
            float damageReflected = percentageSecond * totalDamageTaken;
            int totalDamageToGive = Math.round(firstDamage) + Math.round(damageReflected);
            // apply damage to enemy
            knight.reduceHP(totalDamageToGive);
        }
    }

    /**
     *  Method is used to calculate the damage against a pyromancer.
     * @param pyromancer opponent champion.
     */
    @Override
    public void attack(final Pyromancer pyromancer) {
        // base damages
        float percentageFirst = firstAbility();
        float percentageSecond = secondAbility();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
            percentageSecond += percentageSecond * getTerrainModifier();
        }
        // race modifier
        percentageFirst -= percentageFirst * Constants.MODIFIER_10;
        percentageSecond += percentageSecond * Constants.MODIFIER_30;
        // damage to apply from first ability
        float firstDamage = percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * pyromancer.calculateTeoreticalHp(), pyromancer.getHp());

        if (percentageSecond > Constants.WIZARD_ABILITY_2_CAP) {
            percentageSecond = Constants.WIZARD_ABILITY_2_CAP;
        }
        // calculate taken damage
        float dmgTakenFirst = pyromancer.firstAbility();
        float dmgTakenSecond = pyromancer.secondAbility();
        if (pyromancer.getApplyTerrainModifier()) {
            dmgTakenFirst += dmgTakenFirst * pyromancer.getTerrainModifier();
            dmgTakenSecond += dmgTakenSecond * pyromancer.getTerrainModifier();
        }
        int totalDamageTaken = Math.round(dmgTakenFirst) + Math.round(dmgTakenSecond);
        float reflectedDamage = percentageSecond * totalDamageTaken;
        // apply damage to enemy
        int totalDamageToGive = Math.round(firstDamage) + Math.round(reflectedDamage);
        pyromancer.reduceHP(totalDamageToGive);
    }

    /**
     *  Method is used to calculate the damage against a rogue.
     * @param rogue opponent champion.
     */
    @Override
    public void attack(final Rogue rogue) {
        // base damages
        float percentageFirst = firstAbility();
        float percentageSecond = secondAbility();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
            percentageSecond += percentageSecond * getTerrainModifier();
        }
        // race modifier
        percentageFirst -= percentageFirst * Constants.MODIFIER_20;
        percentageSecond += percentageSecond * Constants.MODIFIER_20;
        // damage to apply from first ability
        float firstDamage = percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * rogue.calculateTeoreticalHp(), rogue.getHp());

        if (percentageSecond > Constants.WIZARD_ABILITY_2_CAP) {
            percentageSecond = Constants.WIZARD_ABILITY_2_CAP;
        }
        // calculate taken damage
        float dmgTakenFirst = rogue.firstAbility();
        float dmgTakenSecond = rogue.secondAbility();
        if (rogue.getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (rogue.getApplyTerrainModifier()) {
                dmgTakenFirst *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                rogue.setRoundCounter();
            }
        }
        if (rogue.getApplyTerrainModifier()) {
            dmgTakenFirst += dmgTakenFirst * rogue.getTerrainModifier();
            dmgTakenSecond += dmgTakenSecond * rogue.getTerrainModifier();
        }
        int totalDamageTaken = Math.round(dmgTakenFirst) + Math.round(dmgTakenSecond);
        float reflectedDamage = percentageSecond * totalDamageTaken;
        // apply damage to enemy
        int totalDamageToGive = Math.round(firstDamage) + Math.round(reflectedDamage);
        rogue.reduceHP(totalDamageToGive);
    }

    /**
     *  Method is used to calculate the damage against a wizard.
     * @param wizard opponent champion.
     */
    @Override
    public void attack(final Wizard wizard) {
        // base damage
        float percentageFirst = firstAbility();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
        }
        // race modifier
        percentageFirst += percentageFirst * Constants.MODIFIER_5;

        int damageToGive = Math.round(percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * wizard.calculateTeoreticalHp(), wizard.getHp()));
        // apply damage to enemy
        wizard.reduceHP(damageToGive);
    }
}
