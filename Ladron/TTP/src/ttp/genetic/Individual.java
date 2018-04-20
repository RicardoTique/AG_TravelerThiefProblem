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
package ttp.genetic;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class Individual implements Cloneable {

    private int[] genotype;
    private int[] packingTrace;
    private double fitness;

    public Individual() {
        this.genotype = new int[10];
        this.random_genotype(10);
    }

    public Individual(int size, int[][] productDispo, int productsSize) {
        this.genotype = new int[size];
        this.packingTrace = new int[size];
        this.random_genotype(size);
        this.random_packingTrace(size, productsSize, productDispo);
    }

    public Individual(int[] genotype) {
        this.genotype = genotype;
    }

    private void random_genotype(int size) {
        Random rd = new Random();
        int swap, index;

        for (int i = 0; i < size; i++) {
            this.genotype[i] = (i + 1);
        }

        for (int i = 0; i < size; i++) {
            index = rd.nextInt(size);
            swap = this.genotype[i];
            this.genotype[i] = this.genotype[index];
            this.genotype[index] = swap;
        }

        for (int i = size - 1; i >= 0; i--) {
            index = rd.nextInt(size);
            swap = this.genotype[i];
            this.genotype[i] = this.genotype[index];
            this.genotype[index] = swap;
        }
    }

    private void random_packingTrace(int size, int productsSize, int[][] productDispo) {
        Random rd = new Random();
        int indexP;
        for (int i = 0; i < size; i++) {
            Vector<Integer> productsDispo = productsDispo(productDispo, productsSize, i);
            if (Math.random() > 0.5 ? true : false) {
                if (productsDispo.size() != 0) {
                    indexP = rd.nextInt(productsDispo.size());
                    getPackingTrace()[i] = productsDispo.get(indexP);
                }
            }
        }
    }

    private Vector<Integer> productsDispo(int[][] productsDispo, int productsSize, int index) {
        Vector<Integer> products = new Vector<Integer>();
        for (int i = 0; i < productsSize; i++) {
            if (productsDispo[i][index] != 0) {
                products.add(i+1);
            }
        }
        return products;
    }

    public double getFitness() {
        return fitness;
    }

    public int[] getGenotype() {
        return genotype;
    }

    public void setGenotype(int[] genotipo) {
        this.setGenotype(genotipo);
    }

    public void setFitness(double fitnest) {
        this.fitness = fitnest;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }

    public int[] getPackingTrace() {
        return packingTrace;
    }

    public void setPackingTrace(int[] packingTrace) {
        this.packingTrace = packingTrace;
    }

}
