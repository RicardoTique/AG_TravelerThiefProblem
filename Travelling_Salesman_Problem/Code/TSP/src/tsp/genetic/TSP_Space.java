/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.genetic;

import tsp.genetic.TSP_Individual;
import unalcol.search.space.Space;

/**
 *
 * @author Ricardo Tique
 */
public class TSP_Space implements Space<TSP_Individual>{
    protected int size;
    
    public TSP_Space(int size){
        this.size = size;
    }
    @Override
    public boolean feasible(TSP_Individual t) {
        return true;
    }

    @Override
    public double feasibility(TSP_Individual t) {
        return 0;
    }

    @Override
    public TSP_Individual repair(TSP_Individual t) {
        return t;
    }

    @Override
    public TSP_Individual pick() {
        return new TSP_Individual(size);
    }
    
}
