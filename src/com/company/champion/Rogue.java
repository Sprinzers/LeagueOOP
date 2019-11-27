package com.company.champion;

public class Rogue extends Champion {
    private final float TERRAIN_BONUS = 0.15F;
    private final String PREFERRED_TERRAIN = "W";

    Rogue(int newPosX, int newPosY) {
        posX = newPosX;
        posY = newPosY;
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
}
