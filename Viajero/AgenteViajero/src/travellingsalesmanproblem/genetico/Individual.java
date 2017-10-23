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

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class Individual implements Cloneable {

    private int [] genotype; //
    private double fitness; //

    
    /**
     * 
     * @param tamano
     */
    public Individual(int size) 
    {
        this.genotype = new int[size];
        this.random_genotype(size);
    }

    
    
    private void random_genotype(int size)
    {
    	Random rd = new Random();
    	int swap, index; 
    	
    	for(int i = 0; i < size; i++)
    		this.genotype[i] = (i + 1);
    	
    	for(int i = 0; i < size; i++)
    	{
    		index = rd.nextInt( size );
    		
    		swap = this.genotype[i];
    		this.genotype[i] = this.genotype[index];
    		this.genotype[index] = swap;
    	}
    	
    	for(int i = size - 1; i >= 0; i--)
    	{
    		index = rd.nextInt( size );
    		
    		swap = this.genotype[i];
    		this.genotype[i] = this.genotype[index];
    		this.genotype[index] = swap;
    	}
    	
    }
    
    
    /**
     * 
     * @param genotipo
     */
    public Individual(int[] genotype) 
    {
        this.genotype = genotype;
    }

    
    /**
     * 
     * @return
     */
    public double getFitness() 
    {
        return fitness;
    }

    
    /**
     * 
     * @return
     */
    public int[] getGenotype() 
    {
        return genotype;
    }

    
    /**
     * 
     * @param genotipo
     */
    public void setGenotype(int[] genotype) 
    {
        this.setGenotype(genotype);
    }

    
    /**
     * 
     * @param fitnest
     */
    public void setFitness(double fitness) 
    {
        this.fitness = fitness;
    }

    
    /**
     * 
     */
    public Object clone() {
        Object obj = null;
        
        try 
        {
            obj = super.clone();
        } 
        catch (CloneNotSupportedException ex) 
        {
            System.out.println("The individual can't be cloned.");
        }
        return obj;
    }
}
