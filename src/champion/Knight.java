package champion;

import util.Constants;

public class Knight extends Champion {

    Knight(final int newPosX, final int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.KNIGHT_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.KNIGHT_TERRAIN_MODIFIER);
        setHp(Constants.KNIGHT_HP);
        setHpStart(Constants.KNIGHT_HP);
        setHpGrowth(Constants.KNIGHT_HP_GROWTH);
        setXp();
        setLevel();
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.KNIGHT_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.KNIGHT_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.KNIGHT_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.KNIGHT_ABILITY_2_GROWTH);
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
        // base damages
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        // calculate execute threshold
        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }
        if (knight.getHp() < knight.calculateTeoreticalHp() * executePercent) {
            knight.setHp(0);
            return;
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }
        // race modifier
        secondDamage += secondDamage * Constants.MODIFIER_20;
        // DOT effects
        if (knight.getDamageOverTime().size() > 0) {
            knight.resetDamageOverTime();
        }
        knight.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);
        // apply damage to enemy
        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        knight.reduceHP(totalDamage);
    }

    /**
     *  Method is used to calculate the damage against a pyromancer.
     * @param pyromancer opponent champion.
     */
    @Override
    public void attack(final Pyromancer pyromancer) {
        // base damages
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        // calculate execute threshold
        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }
        if (pyromancer.getHp() < pyromancer.calculateTeoreticalHp() * executePercent) {
            pyromancer.setHp(0);
            return;
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }
        // race modifier
        firstDamage += firstDamage * Constants.MODIFIER_10;
        secondDamage -= secondDamage * Constants.MODIFIER_10;
        // DOT effects
        if (pyromancer.getDamageOverTime().size() > 0) {
            pyromancer.resetDamageOverTime();
        }
        pyromancer.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);
        // apply damage to enemy
        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        pyromancer.reduceHP(totalDamage);
    }

    /**
     *  Method is used to calculate the damage against a rogue.
     * @param rogue opponent champion.
     */
    @Override
    public void attack(final Rogue rogue) {
        // base damages
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        // calculate execute threshold
        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }
        if (rogue.getHp() < rogue.calculateTeoreticalHp() * executePercent) {
            rogue.setHp(0);
            return;
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }
        // race modifier
        firstDamage += firstDamage * Constants.MODIFIER_15;
        secondDamage -= secondDamage * Constants.MODIFIER_20;
        // DOT efects
        if (rogue.getDamageOverTime().size() > 0) {
            rogue.resetDamageOverTime();
        }
        rogue.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);
        // apply damage to enemy
        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        rogue.reduceHP(totalDamage);
    }

    /**
     *  Method is used to calculate the damage against a wizard.
     * @param wizard opponent champion.
     */
    @Override
    public void attack(final Wizard wizard) {
        // base damages
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        // calculate execute threshold
        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }
        if (wizard.getHp() <= wizard.calculateTeoreticalHp() * executePercent) {
            wizard.setHp(0);
            return;
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }
        // race modifier
        firstDamage -= firstDamage * Constants.MODIFIER_20;
        secondDamage += secondDamage * Constants.MODIFIER_5;
        // DOT effects
        if (wizard.getDamageOverTime().size() > 0) {
            wizard.resetDamageOverTime();
        }
        wizard.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);
        // apply damage to enemy
        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        wizard.reduceHP(totalDamage);
    }
}
