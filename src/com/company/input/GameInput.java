package com.company.input;

import java.util.List;

public class GameInput {
    private final List<String> terrainDescription;
    private final List<String> championsOrder;
    private final List<String> roundsOrder;
    private final int roundsNum;

    public GameInput() {
        terrainDescription = null;
        championsOrder = null;
        roundsOrder = null;
        roundsNum = -1;
    }

    public GameInput(final List<String> terrainDescription, final List<String> championsOrder,
                     final List<String> roundsOrder, final int roundsNum) {
        this.terrainDescription = terrainDescription;
        this.championsOrder = championsOrder;
        this.roundsOrder = roundsOrder;
        this.roundsNum = roundsNum;
//        championsOrder.get(0).split(" ");
    }

    public List<String> getTerrainDescription() {
        return terrainDescription;
    }

    public List<String> getChampionsOrder() {
        return championsOrder;
    }

    public List<String> getRoundsOrder() {
        return roundsOrder;
    }

    public int getRoundsNum() {
        return roundsNum;
    }

    public final boolean isValidInput() {
        boolean membersInstantiated = terrainDescription != null && championsOrder != null
                && roundsOrder != null;
        boolean membersNotEmpty = terrainDescription.size() > 0 && championsOrder.size() > 0
                && roundsOrder.size() > 0 && roundsNum > 0;
        return membersInstantiated && membersNotEmpty;
    }
}
