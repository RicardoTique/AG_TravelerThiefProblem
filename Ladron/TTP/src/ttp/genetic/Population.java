/*
# Project: Travelling Salesman Problem.
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
import ttp.data.Data;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class Population {

    private int population_size = 500;
    public int generations = 1000;
    private int products;
    public Vector<Individual> population;
    private FitnessFunction fitness_function;
    private GeneticOperators genetic_operators = new GeneticOperators();
    private Replacement replacement = new Replacement(0);

    public Population(Data data) {
        this.products = fitness_function.getBenefit().length;
        this.fitness_function = new FitnessFunction(data);
        this.population = new Vector<Individual>();
        this.init_population();

        for (int i = 0; i < generations; i++) {
            generation();                        
        }

    }

    
    private void init_population() {
        for (int i = 0; i < this.population_size; i++) {
           
        }
    }

    private void generation() {
        Vector<Individual> next_generation = null;
        next_generation = new Vector<Individual>();
        int[] indexes;
        Individual[] offsprings = new Individual[2];
        
        for (int i = 0; i < (this.population_size / 2); i++) {
            indexes = selection_tournament();
            if (Math.random() < 0.40) //Mutation
            {
                offsprings = genetic_operators.mutation(this.population.get(indexes[0]), this.population.get(indexes[1]));
            } else //Crossover
            {
                offsprings = genetic_operators.crossover(this.population.get(indexes[0]), this.population.get(indexes[1]));
            }
            for (int j = 0; j < 2; j++) {
             //   offsprings[j].setFitness(fitness_function.calculateFitness(offsprings[j].getGenotype()));
                while (offsprings[j].getFitness() == -1) {
                    genetic_operators.repair(offsprings[j], fitness_function);
               //     offsprings[j].setFitness(fitness_function.calculateFitness(offsprings[j].getGenotype()));
                }
            }
            Individual[] winners = replacement.execute(this.population.get(indexes[0]), this.population.get(indexes[1]), offsprings);
            next_generation.add(winners[0]);
            next_generation.add(winners[1]);
        }
        population = null; 
        population =  next_generation;
        System.gc();
    }

    private int[] selection_uniform() {
        Random rd = new Random();
        int[] parent_indexes = new int[2];

        parent_indexes[0] = rd.nextInt(this.population_size);
        parent_indexes[1] = rd.nextInt(this.population_size);

        return parent_indexes;
    }

    private int[] selection_tournament() {
        Random rd = new Random();
        int[] parent_indexes = new int[2];

        parent_indexes[0] = roulette(roulette(rd.nextInt(this.population_size), rd.nextInt(this.population_size)), roulette(rd.nextInt(this.population_size), rd.nextInt(this.population_size)));
        parent_indexes[1] = roulette(roulette(rd.nextInt(this.population_size), rd.nextInt(this.population_size)), roulette(rd.nextInt(this.population_size), rd.nextInt(this.population_size)));

        return parent_indexes;
    }

    private int roulette(int competitor_1, int competitor_2) {
        double fitness_total = population.get(competitor_1).getFitness() + population.get(competitor_2).getFitness();
        double break_point = population.get(competitor_1).getFitness() / fitness_total;

        if (Math.random() < break_point) {
            return competitor_1;
        } else {
            return competitor_2;
        }
    }

    public Individual bestSolution() {
        Individual best = population.get(0);
        for (int i = 1; i < this.population_size; i++) {
            if (this.population.get(i).getFitness() > best.getFitness()) {
                best = population.get(i);
            }
        }
        return best;
    }
    public Individual wortsSolution() {
        Individual best = population.get(0);
        for (int i = 1; i < this.population_size; i++) {
            if (this.population.get(i).getFitness() < best.getFitness()) {
                best = population.get(i);
            }
        }
        return best;
    }
}
