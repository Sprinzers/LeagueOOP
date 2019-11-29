package com.company.champion;

public class Wizard extends Champion {

    Wizard(int newPosX, int newPosY) {
        posX = newPosX;
        posY = newPosY;
        preferredTerrain = 'D';
        terrainBonus = 0.1F;
        name = 'W';
        hp = 400F;
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
