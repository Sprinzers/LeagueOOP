package champion;

import util.Constants;

public class Knight extends Champion {

    Knight(int newPosX, int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.KNIGHT_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.KNIGHT_TERRAIN_MODIFIER);
        setHp(Constants.KNIGHT_HP);
        setHpStart(Constants.KNIGHT_HP);
        setHpGrowth(Constants.KNIGHT_HP_GROWTH);
        setXp(0);
        setLevel(0);
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.KNIGHT_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.KNIGHT_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.KNIGHT_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.KNIGHT_ABILITY_2_GROWTH);
        setRoundCounter(0);
    }

    @Override
    public void isAttackedBy(Champion champion) {
        champion.attack(this);
    }

    @Override
    public void attack(Knight knight) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();

        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }

        if (knight.getHp() < knight.calculateTeoreticalHp() * executePercent) {
            knight.setHp(0);
            return;
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }

        secondDamage += secondDamage * Constants.MODIFIER_20;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (knight.getDamageOverTime().size() > 0) {
            knight.resetDamageOverTime();
        }

        knight.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);

        knight.reduceHP(totalDamage);
    }

    @Override
    public void attack(Pyromancer pyromancer) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();

        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }

        if (pyromancer.getHp() < pyromancer.calculateTeoreticalHp() * executePercent) {
            pyromancer.setHp(0);
            return;
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }

        firstDamage += firstDamage * Constants.MODIFIER_10;
        secondDamage -= secondDamage * Constants.MODIFIER_10;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (pyromancer.getDamageOverTime().size() > 0) {
            pyromancer.resetDamageOverTime();
        }

        pyromancer.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);

        pyromancer.reduceHP(totalDamage);
    }

    @Override
    public void attack(Rogue rogue) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();

        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }

        if (rogue.getHp() < rogue.calculateTeoreticalHp() * executePercent) {
            rogue.setHp(0);
            return;
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }

        firstDamage += firstDamage * Constants.MODIFIER_15;
        secondDamage -= secondDamage * Constants.MODIFIER_20;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (rogue.getDamageOverTime().size() > 0) {
            rogue.resetDamageOverTime();
        }

        rogue.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);

        rogue.reduceHP(totalDamage);
    }

    @Override
    public void attack(Wizard wizard) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();

        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }

        if (wizard.getHp() <= wizard.calculateTeoreticalHp() * executePercent) {
            wizard.setHp(0);
            return;
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
        }

        firstDamage -= firstDamage * Constants.MODIFIER_20;
        secondDamage += secondDamage * Constants.MODIFIER_5;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (wizard.getDamageOverTime().size() > 0) {
            wizard.resetDamageOverTime();
        }

        wizard.setIncapacitated(Constants.KNIGHT_INCAPACITATION_ROUNDS);

        wizard.reduceHP(totalDamage);
    }
}
