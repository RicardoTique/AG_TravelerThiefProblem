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

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class GeneticOperators {

    public Individual[] crossover(Individual individuo1, Individual individuo2) {
        Individual[] hijos = new Individual[2];
        int tamano = individuo1.getGenotype().length;
        int[] hijo1 = new int[individuo1.getGenotype().length];
        int[] hijo2 = new int[individuo2.getGenotype().length];
        int indRecom = (int) (Math.random() * (tamano - 2)) + 1;
        for (int i = 0; i < indRecom; i++) {
            hijo1[i] = individuo1.getGenotype()[i];
            hijo2[i] = individuo2.getGenotype()[i];
        }
        for (int i = indRecom; i < tamano; i++) {
            hijo1[i] = individuo1.getGenotype()[i];
            hijo2[i] = individuo2.getGenotype()[i];
        }
        hijos[0] = new Individual(hijo1);
        hijos[1] = new Individual(hijo2);
        return hijos;
    }

    public Individual[] mutation(Individual individuo1, Individual individuo2) {
        int tamano = 0;
        Individual[] hijos = new Individual[2];
        hijos[0] = (Individual) individuo1.clone();
        hijos[1] = (Individual) individuo2.clone();

        for (int i = 0; i < 2; i++) {
            int indice = (int) (Math.random() * (tamano));
            if (hijos[i].getGenotype()[indice] == 0) {
                hijos[i].getGenotype()[indice] = 1;
            } else {
                hijos[i].getGenotype()[indice] = 0;
            }
        }

        return hijos;
    }
}
