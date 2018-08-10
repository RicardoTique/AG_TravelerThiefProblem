/*
# Project: Knapsack Problem.
#
# Created by Ricardo Tique & Carlos Andres Sierra on Octuber 2017.
# Copyright (c) 2017  Ricardo Tique & Carlos Andres Sierra. Corporacion Universitaria Minuto de Dios. All rights reserved.
#
# This file is part of GA_TravellingThiefProblem project.
#
# GA_TravellingThiefProblem is free software: you can redistribute it and/or modify it under the terms of the
# GNU General Public License as published by the Free Software Foundation, version 2.
 */
package knapsackProblem.genetic;

import knapsackProblem.data.Data;
import unalcol.optimization.OptimizationFunction;
import unalcol.types.collection.bitarray.BitArray;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class Knapsack extends OptimizationFunction<BitArray> {

    private int knapsackCapacity;
    private int[] productsWeight;
    private int[] benefit;
    public int[][] orderBenefit;

    public Knapsack(Data data) {
        this.knapsackCapacity = data.getKnapsackCapacity();
        this.productsWeight = data.getProductsWeight();
        this.benefit = data.getBenefit();
        this.orderBenefit();
    }

    @Override
    public Double compute(BitArray genome) {
        Double fitness = 0.0;
        while(calculateWeight(genome) > knapsackCapacity){
            repair(genome);
        }
        
        if (calculateWeight(genome) <= knapsackCapacity) {
            for (int i = 0; i < genome.size(); i++) {
                if (genome.get(i)) {
                    fitness += this.benefit[i];
                }
            }
        } else {
            fitness = -1.0;
        }
        return fitness;
    }

    private int calculateWeight(BitArray genome) {
        int weight = 0;
        for (int i = 0; i < genome.size(); i++) {
            if (genome.get(i)) {
                weight += this.productsWeight[i];
            }

        }

        return weight;
    }

    private void repair(BitArray genome) {
        for (int i = 0; i < genome.size(); i++) {
            if (genome.get(this.orderBenefit[i][0])) {
                genome.set(this.orderBenefit[i][0], false);
                break;
            }
        }
    }

    private void orderBenefit() {
        this.orderBenefit = new int[this.benefit.length][2];
        for (int i = 0; i < this.benefit.length; i++) {
            this.orderBenefit[i][0] = i;
            this.orderBenefit[i][1] = this.benefit[i];
        }
        orderMethot(this.orderBenefit, 0, this.benefit.length - 1);
    }

    private void orderMethot(int[][] vector, int izquierda, int derecha) {
        int indicePivote = vector[izquierda][0];
        int pivote = vector[izquierda][1];
        int i = izquierda;
        int j = derecha;
        int auxIndice;
        int auxPeso;
        while (i < j) {
            while (vector[i][1] <= pivote && i < j) {
                i++;
            }
            while (vector[j][1] > pivote) {
                j--;
            }
            if (i < j) {
                auxIndice = vector[i][0];
                auxPeso = vector[i][1];
                vector[i][0] = vector[j][0];
                vector[i][1] = vector[j][1];
                vector[j][0] = auxIndice;
                vector[j][1] = auxPeso;
            }
        }
        vector[izquierda][0] = vector[j][0];
        vector[izquierda][1] = vector[j][1];
        vector[j][0] = indicePivote;
        vector[j][1] = pivote;
        if (izquierda < j - 1) {
            orderMethot(vector, izquierda, j - 1);
        }
        if (j + 1 < derecha) {
            orderMethot(vector, j + 1, derecha);
        }
    }

    public int getKnapsackCapacity() {
        return knapsackCapacity;
    }

    public void setKnapsackCapacity(int knapsackCapacity) {
        this.knapsackCapacity = knapsackCapacity;
    }

    public int[] getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(int[] productsWeight) {
        this.productsWeight = productsWeight;
    }

    public int[] getBenefit() {
        return benefit;
    }

    public void setCostos(int[] costos) {
        this.benefit = costos;
    }

}
