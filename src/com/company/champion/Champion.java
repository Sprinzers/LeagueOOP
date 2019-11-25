package com.company.champion;

public abstract class Champion {
    public abstract void isAttackedBy(Champion champion);
    public abstract void attack(Knight knight);
    public abstract void attack(Pyromancer pyromancer);
    public abstract void attack(Rogue rogue);
    public abstract void attack(Wizard wizard);
}
