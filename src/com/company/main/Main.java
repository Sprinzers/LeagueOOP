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

        TileMap.generateMap(gameInput.getTerrainDescription());
        TileMap map = TileMap.getInstance();

        ArrayList<Champion> champions = new ArrayList<Champion>();

        for (int i = 0; i < gameInput.getChampionsOrder().size(); ++i) {
            ArrayList<String> currChampion = gameInput.getChampionsOrder().get(i);
            int posX = Integer.parseInt(currChampion.get(Constants.CHAMPION_POS_X));
            int posY = Integer.parseInt(currChampion.get(Constants.CHAMPION_POS_Y));
            Champion newChampion = ChampionFactory.getChampion(currChampion
                    .get(Constants.CHAMPION_TYPE), posX, posY);
            newChampion.setId(i);
            champions.add(newChampion);
        }

        for (int i = 0; i < gameInput.getRoundsOrder().size(); ++i) {
            for (int j = 0; j < gameInput.getRoundsOrder().get(i).length(); ++j) {

                if (champions.get(j).isAlive() && !champions.get(j).isIncapacitated()) {
                    char move = gameInput.getRoundsOrder().get(i).charAt(j);
                    champions.get(j).makeMove(move);
                }

                if (champions.get(j).isAlive()) {
                    champions.get(j).hasTerrainModifier(map.getTileType(champions.get(j).getPosX(),
                            champions.get(j).getPosY()));

                    for (Champion currChampion : champions) {
                        if (champions.get(j).getPosX() == currChampion.getPosX()
                                && champions.get(j).getPosY() == currChampion.getPosY()
                                && !currChampion.hasFoughtThisRound()
                                && currChampion != champions.get(j)) {
                            if (champions.get(j).getDamageOverTime().size() > 0) {
                                champions.get(j).reduceHP(champions.get(j).getDamageOverTime().get(0));
                                champions.get(j).appliedDamageOverTime();
                            }

                            if (currChampion.getDamageOverTime().size() > 0) {
                                currChampion.reduceHP(currChampion.getDamageOverTime().get(0));
                                currChampion.appliedDamageOverTime();
                            }

                            champions.get(j).isAttackedBy(currChampion);
                        }
                    }
                }
            }
        }



        for (Champion c : champions) {
            System.out.println(c.getName() + " " + c.getPosX() + " " + c.getPosY());
        }
    }
}
