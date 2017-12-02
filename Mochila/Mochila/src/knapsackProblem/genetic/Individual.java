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

import java.util.Random;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class Individual implements Cloneable {

    private int[] genotype;
    private int fitness;
    
    public Individual(){}
    
    public Individual(int tamano) {
        this.genotype = new int[tamano];
        this.random_genotype(tamano);
    }

    public Individual(int[] genotipo) {
        this.genotype = genotipo;
    }

    private void random_genotype(int size) {
        for(int i = 0;  i < size; i++)
            this.genotype[i] = Math.random() > 0.5 ? 1 : 0;
    }

    public int getFitness() {
        return fitness;
    }

    public int[] getGenotype() {
        return genotype;
    }

    public void setGenotype(int[] genotipo) {
        this.setGenotype(genotipo);
    }

    public void setFitness(int fitnest) {
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

}
