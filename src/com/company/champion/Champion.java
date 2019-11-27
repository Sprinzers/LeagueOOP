package com.company.champion;

public abstract class Champion {
    protected int posX;
    protected int posY;

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

    public abstract void isAttackedBy(Champion champion);
    public abstract void attack(Knight knight);
    public abstract void attack(Pyromancer pyromancer);
    public abstract void attack(Rogue rogue);
    public abstract void attack(Wizard wizard);

}
