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

package travellingsalesmanproblem.genetico;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class Population {

    private int population_size = 100;
    private int cities = 20;
    private int generations = 100;
    private Vector<Individual> population = null;
    private FitnessFunction fitness_function = new FitnessFunction(null); //add coordenates
    private GeneticOperators genetic_operators = new GeneticOperators();
    private Replacement replacement = new Replacement(0);
	
	
    /**
     * 
     */
	public Population()
	{
		this.population = new Vector<Individual>();	
		this.init_population();
		
		for(int i = 0; i < generations; i++)
		{
			generation();
		}
	}
    
	
	/**
	 * 
	 */
	public void init_population()
	{	
		for(int i = 0; i < this.population_size; i++)
		{
			population.add( new Individual(this.cities) );
			population.get(i).setFitness( fitness_function.calculate( population.get(i).getGenotype() ) );
		}
	}
	
	
	/**
	 * 
	 */
	public void generation()
	{
		Vector<Individual> next_generation = new Vector<Individual>();	
		int[] indexes;
		Individual[] offsprings = new Individual[2];
		
		for(int i = 0; i < (this.population_size / 2); i++)
		{
			indexes = selection_tournament();
			
			if(Math.random() < 0.5)  //Mutation
				offsprings = genetic_operators.mutation( this.population.get(indexes[0]), this.population.get(indexes[1]));
			else //Crossover
				offsprings = genetic_operators.crossover( this.population.get(indexes[0]), this.population.get(indexes[1]));
			
			offsprings[0].setFitness( fitness_function.calculate( offsprings[0].getGenotype() ) );
			offsprings[1].setFitness( fitness_function.calculate( offsprings[1].getGenotype() ) );
			
			
			Individual[] winners = replacement.execute(this.population.get(indexes[0]), this.population.get(indexes[1]), offsprings);
			next_generation.add( winners[0] );
			next_generation.add( winners[1] );
		}
		
		population = next_generation;
	}
	
	
    /**
     * 
     * @return
     */
    public int[] selection_uniform()
    {
    	Random rd = new Random();
    	int[] parent_indexes = new int[2];
    	
    	parent_indexes[0] = rd.nextInt( this.population_size );
    	parent_indexes[0] = rd.nextInt( this.population_size );
    	
    	return parent_indexes;
    }
    

    /**
     * 
     */
    public int[] selection_tournament()
    {
    	Random rd = new Random();
    	int[] parent_indexes = new int[2];
    	
    	parent_indexes[0] = roulette( roulette( rd.nextInt( this.population_size ), rd.nextInt( this.population_size )) , roulette( rd.nextInt( this.population_size ), rd.nextInt( this.population_size )));
    	parent_indexes[1] = roulette( roulette( rd.nextInt( this.population_size ), rd.nextInt( this.population_size )) , roulette( rd.nextInt( this.population_size ), rd.nextInt( this.population_size )));
    	
    	return parent_indexes;
    }
     
    
    /**
	 * 
	 * @param competitor_1
	 * @param competitor_2
	 * @return
	 */
	private int roulette(int competitor_1, int competitor_2) 
	{
        double fitness_total = population.get(competitor_1).getFitness() + population.get(competitor_2).getFitness();
        double break_point = population.get(competitor_1).getFitness() / fitness_total;
        
        if (Math.random() < break_point) 
        {
            return competitor_1;
        } 
        else 
        {
            return competitor_2;
        }
    }
}
