package champion;

import util.Constants;

public class Pyromancer extends Champion {

    Pyromancer(final int newPosX, final int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.PYRO_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.PYRO_TERRAIN_MODIFIER);
        setHp(Constants.PYROMANCER_HP);
        setHpStart(Constants.PYROMANCER_HP);
        setHpGrowth(Constants.PYROMANCER_HP_GROWTH);
        setXp();
        setLevel();
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.PYRO_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.PYRO_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.PYRO_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.PYRO_ABILITY_2_GROWTH);
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
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }
        // race modifier
        firstDamage += firstDamage * Constants.MODIFIER_20;
        secondDamage += secondDamage * Constants.MODIFIER_20;
        overTime += overTime * Constants.MODIFIER_20;
        // DOT effects
        int overTimeDamage = Math.round(overTime);
        if (knight.getDamageOverTime().size() > 0) {
            knight.resetDamageOverTime();
        }
        knight.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);
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
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }
        // race modifier
        firstDamage -= firstDamage * Constants.MODIFIER_10;
        secondDamage -= secondDamage * Constants.MODIFIER_10;
        // DOT effects
        overTime -= overTime * Constants.MODIFIER_10;
        int overTimeDamage = Math.round(overTime);
        if (pyromancer.getDamageOverTime().size() > 0) {
            pyromancer.resetDamageOverTime();
        }
        pyromancer.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);
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
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }
        // race modifier
        firstDamage -= firstDamage * Constants.MODIFIER_20;
        secondDamage -= secondDamage * Constants.MODIFIER_20;
        overTime -= overTime * Constants.MODIFIER_20;
        // DOT effects
        int overTimeDamage = Math.round(overTime);
        if (rogue.getDamageOverTime().size() > 0) {
            rogue.resetDamageOverTime();
        }
        rogue.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);
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
        float overTime = Constants.PYRO_OVERTIME_BASE + Constants.PYRO_OVERTIME_GROWTH * getLevel();
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            overTime += overTime * getTerrainModifier();
        }
        // race modifier
        firstDamage += firstDamage * Constants.MODIFIER_5;
        secondDamage += secondDamage * Constants.MODIFIER_5;
        overTime += overTime * Constants.MODIFIER_5;
        // DOT effects
        int overTimeDamage = Math.round(overTime);
        if (wizard.getDamageOverTime().size() > 0) {
            wizard.resetDamageOverTime();
        }
        wizard.addDamageOverTime(overTimeDamage, Constants.PYRO_OVERTIME_ROUNDS);
        // apply damage to enemy
        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        wizard.reduceHP(totalDamage);
    }
}
