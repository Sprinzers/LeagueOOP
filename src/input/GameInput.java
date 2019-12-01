package input;

import java.util.ArrayList;
import java.util.List;

public class GameInput {
    private final List<String> terrainDescription;
    private final ArrayList<ArrayList<String>> championsOrder;
    private final List<String> roundsOrder;

    public GameInput() {
        terrainDescription = null;
        championsOrder = null;
        roundsOrder = null;
    }

    public GameInput(final List<String> terrainDescription,
                     final ArrayList<ArrayList<String>> championsOrder,
                     final List<String> roundsOrder) {
        this.terrainDescription = terrainDescription;
        this.championsOrder = championsOrder;
        this.roundsOrder = roundsOrder;
    }

    public final List<String> getTerrainDescription() {
        return terrainDescription;
    }

    public final ArrayList<ArrayList<String>> getChampionsOrder() {
        return championsOrder;
    }

    public final List<String> getRoundsOrder() {
        return roundsOrder;
    }

    public final boolean isValidInput() {
        boolean membersInstantiated = terrainDescription != null && championsOrder != null
                && roundsOrder != null;
        boolean membersNotEmpty = terrainDescription.size() > 0 && championsOrder.size() > 0
                && roundsOrder.size() > 0;
        return membersInstantiated && membersNotEmpty;
    }
}
