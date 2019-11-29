package com.company.champion;

import com.company.util.Constants;

import java.util.ArrayList;

public class Pyromancer extends Champion {
    private final float TERRAIN_BONUS = 0.25F;
    private final char PREFERRED_TERRAIN = 'V';
    private final char NAME = 'P';
    private final int INITIAL_HP = 500;
    private final int FIRST_ABILITY_BASE_DAMAGE = 350;
    private final int FIRST_ABILITY_DAMAGE_PER_LEVEL = 50;
    private final int SECOND_ABILITY_BASE_DAMAGE = 150;
    private final int SECOND_ABILITY_DAMAGE_PER_LEVEL = 20;
    private final int SECOND_ABILTIY_BASE_DOT = 50;
    private final int SECOND_ABILITY_DOT_PER_LEVEL = 30;
    private final int OVERTIME_ROUNDS = 2;

    Pyromancer(int newPosX, int newPosY) {
        posX = newPosX;
        posY = newPosY;
        preferredTerrain = PREFERRED_TERRAIN;
        terrainBonus = TERRAIN_BONUS;
        name = NAME;
        hp = INITIAL_HP;
        xp = 0;
        level = 0;
        incapacitated = false;
        foughtThisRound = false;
        damageOverTime = new ArrayList<Integer>();
    }

    @Override
    public void isAttackedBy(Champion champion) {
        champion.attack(this);
    }

    @Override
    public void attack(Knight knight) {
        int totalDamage = firstAbility(Constants.MODIFIER_20);
        totalDamage += secondAbility(Constants.MODIFIER_20);
        int overTime = SECOND_ABILTIY_BASE_DOT;
        overTime += SECOND_ABILITY_DOT_PER_LEVEL * level;
        if (applyTerrainModifier) {
            overTime += overTime * getTerrainBonus();
        }
        overTime += overTime * Constants.MODIFIER_20;
        if (knight.getDamageOverTime().size() > 0) {
            knight.resetDamageOverTime();
        }
        knight.addDamageOverTime(overTime, OVERTIME_ROUNDS);

        knight.reduceHP(totalDamage);
    }

    @Override
    public void attack(Pyromancer pyromancer) {
        int totalDamage = firstAbility(Constants.MODIFIER_10 * (-1));
        totalDamage += secondAbility(Constants.MODIFIER_10 * (-1));
        int overTime = SECOND_ABILTIY_BASE_DOT;
        overTime += SECOND_ABILITY_DOT_PER_LEVEL * level;
        if (applyTerrainModifier) {
            overTime += overTime * getTerrainBonus();
        }
        overTime -= overTime * Constants.MODIFIER_10;
        if (pyromancer.getDamageOverTime().size() > 0) {
            pyromancer.resetDamageOverTime();
        }
        pyromancer.addDamageOverTime(overTime, OVERTIME_ROUNDS);

        pyromancer.reduceHP(totalDamage);
    }

    @Override
    public void attack(Rogue rogue) {
        int totalDamage = firstAbility(Constants.MODIFIER_20 * (-1));
        totalDamage += secondAbility(Constants.MODIFIER_20 * (-1));
        int overTime = SECOND_ABILTIY_BASE_DOT;
        overTime += SECOND_ABILITY_DOT_PER_LEVEL * level;
        if (applyTerrainModifier) {
            overTime += overTime * getTerrainBonus();
        }
        overTime -= overTime * Constants.MODIFIER_20;
        if (rogue.getDamageOverTime().size() > 0) {
            rogue.resetDamageOverTime();
        }
        rogue.addDamageOverTime(overTime, OVERTIME_ROUNDS);

        rogue.reduceHP(totalDamage);
    }

    @Override
    public void attack(Wizard wizard) {
        int totalDamage = firstAbility(Constants.MODIFIER_5);
        totalDamage += secondAbility(Constants.MODIFIER_5);
        int overTime = SECOND_ABILTIY_BASE_DOT;
        overTime += SECOND_ABILITY_DOT_PER_LEVEL * level;
        if (applyTerrainModifier) {
            overTime += overTime * getTerrainBonus();
        }
        overTime += overTime * Constants.MODIFIER_5;
        if (wizard.getDamageOverTime().size() > 0) {
            wizard.resetDamageOverTime();
        }
        wizard.addDamageOverTime(overTime, OVERTIME_ROUNDS);

        wizard.reduceHP(totalDamage);
    }

    @Override
    public int firstAbility(float raceModifier) {
        int damage = FIRST_ABILITY_BASE_DAMAGE;
        damage += FIRST_ABILITY_DAMAGE_PER_LEVEL * level;
        if (applyTerrainModifier) {
            damage += damage * getTerrainBonus();
        }
        damage += damage * raceModifier;

        return Math.round(damage);
    }

    @Override
    public int secondAbility(float raceModifier) {
        int damage = SECOND_ABILITY_BASE_DAMAGE;
        damage += SECOND_ABILITY_DAMAGE_PER_LEVEL * level;
        if (applyTerrainModifier) {
            damage += damage * getTerrainBonus();
        }
        damage += damage * raceModifier;

        return Math.round(damage);
    }
}
