package com.tablekat.agame;

import java.util.ArrayList;

public class World {
    public ArrayList<Creep> creeps;
    public ArrayList<Creep> creepEnemies;

    public World(){
        creeps = new ArrayList<>();
        creepEnemies = new ArrayList<>();
    }
}
