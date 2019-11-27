package com.company.main;

import com.company.champion.Champion;
import com.company.champion.ChampionFactory;
import com.company.util.Constants;
import com.company.game.TileMap;
import com.company.input.GameInput;
import com.company.input.GameInputLoader;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        System.out.println(gameInput.getTerrainDescription());
        System.out.println(gameInput.getChampionsOrder());
        System.out.println(gameInput.getRoundsOrder());

        TileMap.generateMap(gameInput.getTerrainDescription());
        TileMap map = TileMap.getInstance();

        for (int i = 0; i < gameInput.getTerrainDescription().size(); ++i) {
            for (int j = 0; j < gameInput.getTerrainDescription().get(i).length(); ++j) {
                System.out.print(map.getTileType(i, j));
            }
            System.out.println();
        }

        ArrayList<Champion> champions = new ArrayList<Champion>();

        for (int i = 0; i < gameInput.getChampionsOrder().size(); ++i) {
            ArrayList<String> currChampion = gameInput.getChampionsOrder().get(i);
            int posX = Integer.parseInt(currChampion.get(Constants.CHAMPION_POS_X));
            int posY = Integer.parseInt(currChampion.get(Constants.CHAMPION_POS_Y));
            Champion newChampion = ChampionFactory.getChampion(currChampion
                    .get(Constants.CHAMPION_TYPE), posX, posY);
        }

        for (Champion c : champions) {

        }
    }
}
