package com.company.champion;

public class Rogue extends Champion {

    Rogue(int newPosX, int newPosY) {
        posX = newPosX;
        posY = newPosY;
        preferredTerrain = 'W';
        terrainBonus = 0.15F;
        name = 'R';
        hp = 600F;
        xp = 0;
        level = 0;
    }

    @Override
    public void isAttackedBy(Champion champion) {
        champion.attack(this);
    }

    @Override
    public void attack(Knight knight) {

    }

    @Override
    public void attack(Pyromancer pyromancer) {

    }

    @Override
    public void attack(Rogue rogue) {

    }

    @Override
    public void attack(Wizard wizard) {

    }

    @Override
    public int firstAbility(float raceModifier) {
        return 0;
    }

    @Override
    public int secondAbility(float raceModifier) {
        return 0;
    }
}
