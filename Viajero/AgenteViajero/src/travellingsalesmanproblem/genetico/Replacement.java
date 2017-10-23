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

/**
 * @author Ricardo Tique & Carlos Andres Sierra
 *
 */
public class Replacement {

	private int mechanism = 0; //0: Generational   1: Steady-State 
	
	/**
	 * 
	 * @param mechanism
	 */
	public Replacement(int mechanism) 
	{
		this.mechanism = mechanism;
	}
	
	
	/**
	 * 
	 * @param parents
	 * @param offsprings
	 * @return
	 */
	private Individual[] generational(Individual[] parents, Individual[] offsprings)
	{
		return offsprings;
	}
	
	
	/**
	 * 
	 * @param parents
	 * @param offsprings
	 * @return
	 */
	private Individual[] steady_state(Individual[] parents, Individual[] offsprings)
	{
		Individual[] next_generation = new Individual[2];
		
		if(parents[0].getFitness() > parents[1].getFitness())
			if(offsprings[0].getFitness() > offsprings[1].getFitness())
			{
				next_generation[0] = roulette(parents[0], offsprings[0]);
				next_generation[1] = roulette(parents[1], offsprings[1]);
			}
			else
			{
				next_generation[0] = roulette(parents[0], offsprings[1]);
				next_generation[1] = roulette(parents[1], offsprings[0]);
			}
		else
			if(offsprings[0].getFitness() > offsprings[1].getFitness())
			{
				next_generation[0] = roulette(parents[1], offsprings[0]);
				next_generation[1] = roulette(parents[0], offsprings[1]);
			}
			else
			{
				next_generation[0] = roulette(parents[1], offsprings[1]);
				next_generation[1] = roulette(parents[0], offsprings[0]);
			}
		
		return next_generation;
	}
	
	
	/**
	 * 
	 * @param competitor_1
	 * @param competitor_2
	 * @return
	 */
	private Individual roulette(Individual competitor_1, Individual competitor_2) 
	{
        double fitness_total = competitor_1.getFitness() + competitor_2.getFitness();
        double break_point = competitor_1.getFitness() / fitness_total;
        
        if (Math.random() < break_point) 
        {
            return competitor_1;
        } 
        else 
        {
            return competitor_2;
        }
    }
	
	
	/**
	 * 
	 * @param parents
	 * @param offsprings
	 * @return
	 */
	public Individual[] execute(Individual parent_1, Individual parent_2, Individual[] offsprings)
	{
		Individual[] parents = {parent_1, parent_2};
		
		if(mechanism == 1)
			return generational(parents, offsprings);
		else
			return steady_state(parents, offsprings);
	}
}
