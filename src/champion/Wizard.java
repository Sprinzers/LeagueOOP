package champion;

import util.Constants;

public class Wizard extends Champion {

    Wizard(int newPosX, int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.WIZARD_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.WIZARD_TERRAIN_MODIFIER);
        setHp(Constants.WIZARD_HP);
        setHpStart(Constants.WIZARD_HP);
        setHpGrowth(Constants.WIZARD_HP_GROWTH);
        setXp(0);
        setLevel(0);
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.WIZARD_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.WIZARD_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.WIZARD_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.WIZARD_ABILITY_2_GROWTH);
        setRoundCounter(0);
    }

    @Override
    public void isAttackedBy(Champion champion) {
        champion.attack(this);
    }

    @Override
    public void attack(Knight knight) {
        float percentageFirst = firstAbility();
        float percentageSecond = secondAbility();

        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
            percentageSecond += percentageSecond * getTerrainModifier();
        }

        percentageFirst += percentageFirst * Constants.MODIFIER_20;
        percentageSecond += percentageSecond * Constants.MODIFIER_40;

        float firstDamage = percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * knight.calculateTeoreticalHp(), knight.getHp());


        if (percentageSecond > Constants.WIZARD_ABILITY_2_CAP) {
            percentageSecond = Constants.WIZARD_ABILITY_2_CAP;
        }

        float dmgTakenFirst = knight.firstAbility();
        float dmgTakenSecond = knight.secondAbility();

        float executePercent = Constants.KNIGHT_HP_PERCENT_INITIAL
                + Constants.KNIGHT_HP_PERCENT_GROWTH * knight.getLevel();
        if (executePercent > Constants.KNIGHT_HP_PERCENT_CAP) {
            executePercent = Constants.KNIGHT_HP_PERCENT_CAP;
        }

        if (this.getHp() <= this.calculateTeoreticalHp() * executePercent) {
            int executeDamage = this.getHp();
            float damageReflected = percentageSecond * executeDamage;
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
            knight.reduceHP(totalDamageToGive);

        }
    }

    @Override
    public void attack(Pyromancer pyromancer) {
        float percentageFirst = firstAbility();
        float percentageSecond = secondAbility();

        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
            percentageSecond += percentageSecond * getTerrainModifier();
        }

        percentageFirst -= percentageFirst * Constants.MODIFIER_10;
        percentageSecond += percentageSecond * Constants.MODIFIER_30;

        float firstDamage = percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * pyromancer.calculateTeoreticalHp(), pyromancer.getHp());


        if (percentageSecond > Constants.WIZARD_ABILITY_2_CAP) {
            percentageSecond = Constants.WIZARD_ABILITY_2_CAP;
        }

        float dmgTakenFirst = pyromancer.firstAbility();
        float dmgTakenSecond = pyromancer.secondAbility();

        if (pyromancer.getApplyTerrainModifier()) {
            dmgTakenFirst += dmgTakenFirst * pyromancer.getTerrainModifier();
            dmgTakenSecond += dmgTakenSecond * pyromancer.getTerrainModifier();
        }

        int totalDamageTaken = Math.round(dmgTakenFirst) + Math.round(dmgTakenSecond);

        float reflectedDamage = percentageSecond * totalDamageTaken;

        int totalDamageToGive = Math.round(firstDamage) + Math.round(reflectedDamage);

        pyromancer.reduceHP(totalDamageToGive);
    }

    @Override
    public void attack(Rogue rogue) {
        float percentageFirst = firstAbility();
        float percentageSecond = secondAbility();

        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
            percentageSecond += percentageSecond * getTerrainModifier();
        }

        percentageFirst -= percentageFirst * Constants.MODIFIER_20;
        percentageSecond += percentageSecond * Constants.MODIFIER_20;

        float firstDamage = percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * rogue.calculateTeoreticalHp(), rogue.getHp());


        if (percentageSecond > Constants.WIZARD_ABILITY_2_CAP) {
            percentageSecond = Constants.WIZARD_ABILITY_2_CAP;
        }

        float dmgTakenFirst = rogue.firstAbility();
        float dmgTakenSecond = rogue.secondAbility();

        if (rogue.getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (rogue.getApplyTerrainModifier()) {
                dmgTakenFirst *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                rogue.setRoundCounter(0);
            }
        }

        if (rogue.getApplyTerrainModifier()) {
            dmgTakenFirst += dmgTakenFirst * rogue.getTerrainModifier();
            dmgTakenSecond += dmgTakenSecond * rogue.getTerrainModifier();
        }

        int totalDamageTaken = Math.round(dmgTakenFirst) + Math.round(dmgTakenSecond);

        float reflectedDamage = percentageSecond * totalDamageTaken;

        int totalDamageToGive = Math.round(firstDamage) + Math.round(reflectedDamage);

        rogue.reduceHP(totalDamageToGive);
    }

    @Override
    public void attack(Wizard wizard) {
        float percentageFirst = firstAbility();

        if (getApplyTerrainModifier()) {
            percentageFirst += percentageFirst * getTerrainModifier();
        }

        percentageFirst += percentageFirst * Constants.MODIFIER_5;

        float damageToGive = percentageFirst * Math.min(Constants.WIZARD_ABILITY_1_PERCENT
                * wizard.calculateTeoreticalHp(), wizard.getHp());

        wizard.reduceHP(Math.round(damageToGive));
    }
}
