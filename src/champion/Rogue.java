package champion;

import util.Constants;

public class Rogue extends Champion {

    Rogue(int newPosX, int newPosY) {
        setPosX(newPosX);
        setPosY(newPosY);
        setPreferredTerrain(Constants.ROGUE_PREFERRED_TERRAIN);
        setTerrainModifier(Constants.ROGUE_TERRAIN_MODIFIER);
        setHp(Constants.ROGUE_HP);
        setHpStart(Constants.ROGUE_HP);
        setHpGrowth(Constants.ROGUE_HP_GROWTH);
        setXp(0);
        setLevel(0);
        setIncapacitated(0);
        setFoughtThisRound(false);
        setFirstAbilityBase(Constants.ROGUE_ABILITY_1_BASE);
        setFirstAbilityGrowth(Constants.ROGUE_ABILITY_1_GROWTH);
        setSecondAbilityBase(Constants.ROGUE_ABILITY_2_BASE);
        setSecondAbilityGrowth(Constants.ROGUE_ABILITY_2_GROWTH);
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
        float overTime = secondDamage;

        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter(0);
            }
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            knight.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            knight.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }

        firstDamage -= firstDamage * Constants.MODIFIER_10;
        secondDamage -= secondDamage * Constants.MODIFIER_20;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (knight.getDamageOverTime().size() > 0) {
            knight.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            knight.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            knight.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }

        knight.reduceHP(totalDamage);
    }

    @Override
    public void attack(Pyromancer pyromancer) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        float overTime = secondDamage;

        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter(0);
            }
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            pyromancer.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            pyromancer.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }

        firstDamage += firstDamage * Constants.MODIFIER_25;
        secondDamage += secondDamage * Constants.MODIFIER_20;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (pyromancer.getDamageOverTime().size() > 0) {
            pyromancer.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            pyromancer.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            pyromancer.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }

        pyromancer.reduceHP(totalDamage);
    }

    @Override
    public void attack(Rogue rogue) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        float overTime = secondDamage;

        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter(0);
            }
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            rogue.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            rogue.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }

        firstDamage += firstDamage * Constants.MODIFIER_20;
        secondDamage -= secondDamage * Constants.MODIFIER_10;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (rogue.getDamageOverTime().size() > 0) {
            rogue.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            rogue.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            rogue.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }

        rogue.reduceHP(totalDamage);
    }

    @Override
    public void attack(Wizard wizard) {
        float firstDamage = firstAbility();
        float secondDamage = secondAbility();
        float overTime = secondDamage;

        if (getRoundCounter() % Constants.ROGUE_CRITICAL_HIT_CHANCE == 0) {
            if (getApplyTerrainModifier()) {
                firstDamage *= Constants.ROGUE_CRITICAL_HIT_MULTIPLIER;
            } else {
                setRoundCounter(0);
            }
        }

        if (getApplyTerrainModifier()) {
            firstDamage += firstDamage * getTerrainModifier();
            secondDamage += secondDamage * getTerrainModifier();
            wizard.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            wizard.setIncapacitated(Constants.ROGUE_OVERTIME_ROUNDS);
        }

        firstDamage += firstDamage * Constants.MODIFIER_25;
        secondDamage += secondDamage * Constants.MODIFIER_25;

        int totalDamage = Math.round(firstDamage) + Math.round(secondDamage);

        if (wizard.getDamageOverTime().size() > 0) {
            wizard.resetDamageOverTime();
        }
        if (getApplyTerrainModifier()) {
            wizard.addDamageOverTime(Math.round(secondDamage),
                    Constants.ROGUE_OVERTIME_ROUNDS_WOODS);
        } else {
            wizard.addDamageOverTime(Math.round(secondDamage), Constants.ROGUE_OVERTIME_ROUNDS);
        }

        wizard.reduceHP(totalDamage);
    }
}
