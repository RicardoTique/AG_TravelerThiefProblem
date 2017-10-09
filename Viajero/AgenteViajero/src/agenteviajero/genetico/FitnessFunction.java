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

package agenteviajero.genetico;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class FitnessFunction {
    
    private int [][] coordenates;

    
    /**
     * 
     * @param coordenates
     */
    public FitnessFunction(int[][] coordenates) 
    {        
        this.coordenates = coordenates;
    }
    
    
    /**
     * 
     * @param genotipo
     * @return
     */
    public double calculate(int[] genotype)
    {
        int [] aux = new int[ genotype.length + 1 ];        
        aux[aux.length-1] = genotype[0];
        
        for (int i = 0; i < genotype.length; i++) 
        {
            aux[i + 1] = genotype[i];
        }
        
        double respuesta = 0;
        
        for (int i = 0; i < aux.length-1; i++) 
        {
            double x = coordenates[ aux[i+1] ][0] - coordenates[ aux[i] ][0];
            double y = coordenates[ aux[i+1] ][1] - coordenates[ aux[i] ][1];
            respuesta += Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
        
        return respuesta;
    }
}