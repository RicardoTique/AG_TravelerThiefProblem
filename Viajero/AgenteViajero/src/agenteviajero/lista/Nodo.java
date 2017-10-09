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

package agenteviajero.lista;

import java.util.ArrayList;

/**
 *
 * @author Ricardo Tique
 */
public class Nodo {

    private int numeroCiudad;
    private ArrayList<Integer> vecinos;

    public Nodo(int numeroCiudad) {
        this.numeroCiudad = numeroCiudad;
        this.vecinos = new ArrayList<Integer>();
    }

    public void agregarVecino(int vecino) {
        if (this.getVecinos().isEmpty()) {
            this.getVecinos().add(vecino);
        } else {
            int i = 0;
            while (i < this.getVecinos().size()) {
                if (this.getVecinos().get(i) == vecino) {
                    this.getVecinos().set(i, vecino * -1);
                }
                i++;
            }
            if (i == this.getVecinos().size()) {
                this.getVecinos().add(vecino);
            }
        }
    }

    public void eliminarVecino(int vecino) {
        for (int i = 0; i < this.getVecinos().size(); i++) {
            if (this.getVecinos().get(i) == vecino || this.getVecinos().get(i) == (vecino * -1)) {
                this.getVecinos().remove(i);
            }
        }
    }

    public int getNumeroCiudad() {
        return numeroCiudad;
    }

    public ArrayList<Integer> getVecinos() {
        return vecinos;
    }

}
