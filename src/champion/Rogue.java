package champion;

import util.Constants;

public class Rogue extends Champion {

    Rogue(final int newPosX, final int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.ROGUE_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.ROGUE_TERRAIN_MODIFIER);
        setHp(Constants.ROGUE_HP);
        setHpStart(Constants.ROGUE_HP);
        setHpGrowth(Constants.ROGUE_HP_GROWTH);
        setXp();
        setLevel();
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.ROGUE_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.ROGUE_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.ROGUE_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.ROGUE_ABILITY_2_GROWTH);
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
        // apply critical hit
        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter();
            }
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            knight.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            knight.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }
        // race modifier
        firstDamage -= firstDamage * Constants.MODIFIER_10;
        secondDamage -= secondDamage * Constants.MODIFIER_20;
        // DOT effects
        if (knight.getDamageOverTime().size() > 0) {
            knight.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            knight.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            knight.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }
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
        // apply critical hit
        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter();
            }
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            pyromancer.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            pyromancer.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }
        // race modifier
        firstDamage += firstDamage * Constants.MODIFIER_25;
        secondDamage += secondDamage * Constants.MODIFIER_20;
        // DOT effects
        if (pyromancer.getDamageOverTime().size() > 0) {
            pyromancer.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            pyromancer.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            pyromancer.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }
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
        // apply critical hit
        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter();
            }
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            rogue.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            rogue.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }
        // race modifier
        firstDamage += firstDamage * Constants.MODIFIER_20;
        secondDamage -= secondDamage * Constants.MODIFIER_10;
        // DOT effects
        if (rogue.getDamageOverTime().size() > 0) {
            rogue.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            rogue.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            rogue.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }
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
        // apply critical hit
        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter();
            }
        }
        // terrain modifier
        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            wizard.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            wizard.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }
        // race modifier
        firstDamage += firstDamage * Constants.MODIFIER_25;
        secondDamage += secondDamage * Constants.MODIFIER_25;
        // DOT efects
        if (wizard.getDamageOverTime().size() > 0) {
            wizard.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            wizard.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            wizard.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }
        // apply damage to enemy
        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);
        wizard.reduceHP(totalDamage);
    }
}
