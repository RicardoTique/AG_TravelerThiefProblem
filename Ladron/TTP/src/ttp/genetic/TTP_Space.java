/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic;

import unalcol.search.space.Space;
import unalcol.services.MicroService;

/**
 *
 * @author Ricardo Tique
 */
public class TTP_Space extends MicroService<TTP_Individual> implements Space<TTP_Individual> {

    protected int numberCitys;
    protected int numberProducts;
    protected int[] avaibleProdCity;

    public TTP_Space(int numberCitys, int numberProducts) {
        this.numberCitys = numberCitys;
        this.numberProducts = numberProducts;
    }

    @Override
    public boolean feasible(TTP_Individual x) {
        return false;
    }

    @Override
    public double feasibility(TTP_Individual x) {
        return 0;
    }

    @Override
    public TTP_Individual repair(TTP_Individual x) {
        return new TTP_Individual(x);
    }

    @Override
    public TTP_Individual pick() {

        return new TTP_Individual(numberCitys, numberProducts);
    }
}
