package com.company.main;

import com.company.input.GameInput;
import com.company.input.GameInputLoader;

public class Main {

    public static void main(String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();

        System.out.println(gameInput.getTerrainDescription());
        System.out.println(gameInput.getChampionsOrder());
        System.out.println(gameInput.getRoundsOrder());

        
    }
}
