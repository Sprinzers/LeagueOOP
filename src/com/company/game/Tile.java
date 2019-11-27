package com.company.game;

public class Tile {
    private final char type;
//    private final int volcanicBonusPyromancer = 25;
//    private final int landBonusKnight = 15;
//    private final int desertBonusWizard = 10;
//    private final int woodsBonusRouge = 15;

    Tile(char newType) {
        type = newType;
    }

    public char getType() {
        return type;
    }
}
