package champion;

import util.Constants;

public class Pyromancer extends Champion {

    Pyromancer(int newPosX, int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.PYRO_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.PYRO_TERRAIN_MODIFIER);
        setHp(Constants.PYROMANCER_HP);
        setHpStart(Constants.PYROMANCER_HP);
        setHpGrowth(Constants.PYROMANCER_HP_GROWTH);
        setXp(0);
        setLevel(0);
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.PYRO_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.PYRO_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.PYRO_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.PYRO_ABILITY_2_GROWTH);
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
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }

        firstDamage += firstDamage * Constants.MODIFIER_20;
        secondDamage += secondDamage * Constants.MODIFIER_20;
        overTime += overTime * Constants.MODIFIER_20;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        int overTimeDamage = Math.round(overTime);

        if (knight.getDamageOverTime().size() > 0) {
            knight.resetDamageOverTime();
        }

        knight.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);

        knight.reduceHP(totalDamage);
    }

    @Override
    public void attack(Pyromancer pyromancer) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }

        firstDamage -= firstDamage * Constants.MODIFIER_10;
        secondDamage -= secondDamage * Constants.MODIFIER_10;
        overTime -= overTime * Constants.MODIFIER_10;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        int overTimeDamage = Math.round(overTime);

        if (pyromancer.getDamageOverTime().size() > 0) {
            pyromancer.resetDamageOverTime();
        }

        pyromancer.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);

        pyromancer.reduceHP(totalDamage);
    }

    @Override
    public void attack(Rogue rogue) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }

        firstDamage -= firstDamage * Constants.MODIFIER_20;
        secondDamage -= secondDamage * Constants.MODIFIER_20;
        overTime -= overTime * Constants.MODIFIER_20;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        int overTimeDamage = Math.round(overTime);

        if (rogue.getDamageOverTime().size() > 0) {
            rogue.resetDamageOverTime();
        }

        rogue.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);

        rogue.reduceHP(totalDamage);
    }

    @Override
    public void attack(Wizard wizard) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }

        firstDamage += firstDamage * Constants.MODIFIER_5;
        secondDamage += secondDamage * Constants.MODIFIER_5;
        overTime += overTime * Constants.MODIFIER_5;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        int overTimeDamage = Math.round(overTime);

        if (wizard.getDamageOverTime().size() > 0) {
            wizard.resetDamageOverTime();
        }

        wizard.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);

        wizard.reduceHP(totalDamage);
    }
}
