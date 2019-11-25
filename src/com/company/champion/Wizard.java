package com.company.champion;

public class Wizard extends Champion {
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
