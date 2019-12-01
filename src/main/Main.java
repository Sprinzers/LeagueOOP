package main;

import champion.Champion;
import champion.ChampionFactory;
import util.Constants;
import game.TileMap;
import input.GameInput;
import input.GameInputLoader;

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
            }

            for (Champion currChampion : champions) {
                currChampion.hasTerrainModifier(map.getTileType(currChampion.getPosX(),
                        currChampion.getPosY()));
            }

            for (Champion currChampion : champions) {
                if (currChampion.isAlive()) {

                    for (Champion opponent : champions) {
                        if (currChampion.verifyOpponent(opponent)) {
                            currChampion.applyDamageOverTime();
                            opponent.applyDamageOverTime();
                            if (currChampion.isAlive() && opponent.isAlive()) {
                                currChampion.isAttackedBy(opponent);
                                opponent.isAttackedBy(currChampion);
                                currChampion.setFoughtThisRound(true);
                                opponent.setFoughtThisRound(true);

                                int levelFirstChampion = currChampion.getLevel();
                                int levelSecondChampion = opponent.getLevel();
                                if (!currChampion.isAlive() && opponent.isAlive()) {
                                    if (opponent.awardXp(levelFirstChampion)) {
                                        opponent.restoreHP();
                                    }
                                } else if (currChampion.isAlive() && !opponent.isAlive()) {
                                    if (currChampion.awardXp(levelSecondChampion)) {
                                        currChampion.restoreHP();
                                    }
                                } else if (!currChampion.isAlive() && !opponent.isAlive()) {
                                    currChampion.awardXp(levelSecondChampion);
                                    opponent.awardXp(levelFirstChampion);
                                }
                            }
                        }
                    }
                }
            }
            for (Champion currChampion : champions) {
                currChampion.setFoughtThisRound(false);
                currChampion.increaseRoundCounter();
            }
        }

        for (Champion c : champions) {
            System.out.println(c.printFinalStats());
        }
    }
}
