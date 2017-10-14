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

import agenteviajero.lista.*;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class GeneticOperators {

	/**
	 * 
	 * @param individuo1
	 * @param individuo2
	 * @return
	 */
    public Individual[] cruce(Individual individuo1, Individual individuo2)
    {
        Individual[] hijos = new Individual[2];

        return hijos;
    }

    
    /**
     * 
     * @param individuo1
     * @param individuo2
     * @return
     */
    public Individual[] mutacion(Individual individuo1, Individual individuo2) 
    {
        int tamano = 0;
        Individual[] hijos = new Individual[2];
        hijos[0] = (Individual) individuo1.clone();
        hijos[1] = (Individual) individuo2.clone();

        for (int i = 0; i < 2; i++) 
        {
            int indice = (int) (Math.random() * (tamano));
            
            if (hijos[i].getGenotype()[ indice ] == 0) 
            {
                hijos[i].getGenotype()[ indice ] = 1;
            } 
            else 
            {
                hijos[i].getGenotype()[ indice ] = 0;
            }
        }

        return hijos;
    }

    
    public Lista generarTablaAdyacencia(int[] genotipo1, int[] genotipo2) {
        Lista tabla = new Lista();
        int tamanoAux = genotipo1.length + 2;
        int[] aux1 = new int[tamanoAux];
        int[] aux2 = new int[tamanoAux];

        aux1[0] = genotipo1[genotipo1.length - 1];
        aux2[0] = genotipo2[genotipo2.length - 1];
        aux1[aux1.length - 1] = genotipo1[0];
        aux2[aux2.length - 1] = genotipo2[0];

        for (int i = 0; i < genotipo1.length; i++) {
            aux1[i + 1] = genotipo1[i];
            aux2[i + 1] = genotipo2[i];
        }

        for (int i = 1; i < tamanoAux - 1; i++) {
            tabla.agregarNodo(aux1[i]);
            
        }
        
        return tabla;
    }
}