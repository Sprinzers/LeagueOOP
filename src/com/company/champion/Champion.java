package com.company.champion;

import java.util.ArrayList;

public abstract class Champion {
    protected char preferredTerrain;
    protected float terrainBonus;
    protected char name;
    protected int hp;
    protected int xp;
    protected int level;
    public boolean applyTerrainModifier;
    ArrayList<Integer> damageOverTime;
    boolean incapacitated;
    boolean foughtThisRound;

    protected int posX;
    protected int posY;
    protected int id;

    /**
     *  Method is used to move the champion
     * @param newMove direction of the move
     */
    public void makeMove(char newMove) {
        switch (newMove) {
            case 'U':
                --posX;
                break;
            case 'D':
                ++posX;
                break;
            case 'R':
                ++posY;
                break;
            case 'L':
                --posY;
                break;
            case '_':
                break;
            default:
                break;
        }
    }

    public boolean hasFoughtThisRound() {
        return foughtThisRound;
    }

    public void setFoughtThisRound(boolean fightStatus) {
        foughtThisRound = fightStatus;
    }

    public void addXP(int newXP) {
        xp += newXP;
    }

    public void calculateXp(int levelLoser) {
        int levelDiff = getLevel() - levelLoser;
        int xpWinner = Math.max(0, 200 - levelDiff * 40);
        addXP(xpWinner);
    }

    public int getLevel() {
        return level;
    }

    /**
     *  Method is used to test if the champion is alive or dead.
     * @return true if the champion is alive, false if he is dead
     */
    public boolean isAlive() {
        if (hp > 0) {
            return true;
        }
        return false;
    }

    public boolean isIncapacitated() {
        return incapacitated;
    }

    public void addDamageOverTime(int damage, int rounds) {
        for (int i = 0; i < rounds; ++i) {
            damageOverTime.add(damage);
        }
    }

    public void resetDamageOverTime() {
        damageOverTime.clear();
    }

    public ArrayList<Integer> getDamageOverTime() {
        return damageOverTime;
    }

    public void hasTerrainModifier(char terrainType) {
        if (getPreferredTerrain() == terrainType) {
            setApplyTerrainModifier(true);
        }
        setApplyTerrainModifier(false);
    }

    public void setApplyTerrainModifier(boolean apply) {
        applyTerrainModifier = apply;
    }

    public char getPreferredTerrain() {
        return preferredTerrain;
    }

    public float getTerrainBonus() {
        return terrainBonus;
    }

    public char getName() {
        return name;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int newPosX) {
        posX = newPosX;
    }

    public void setPosY(int newPosY) {
        posY = newPosY;
    }

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

    public void reduceHP(int damage) {
        hp -= damage;
    }

    public void appliedDamageOverTime() {
        damageOverTime.remove(0);
    }

    public abstract void isAttackedBy(Champion champion);
    public abstract void attack(Knight knight);
    public abstract void attack(Pyromancer pyromancer);
    public abstract void attack(Rogue rogue);
    public abstract void attack(Wizard wizard);
    public abstract int firstAbility(float raceModifier);
    public abstract int secondAbility(float raceModifier);

}
