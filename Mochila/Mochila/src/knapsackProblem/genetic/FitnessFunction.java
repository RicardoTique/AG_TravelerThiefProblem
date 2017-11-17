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
public class FitnessFunction {

    private int volumenMochila;
    private int[] pesosMohcila;
    private int[] benefit;

    public FitnessFunction(int volumenMochila, int[] pesosMohcila, int[] benefit) {
        this.volumenMochila = volumenMochila;
        this.pesosMohcila = pesosMohcila;
        this.benefit = benefit;
    }

    public int calculate(int[] genotipo) {
        int fitnes = 0;
        for (int i = 0; i < genotipo.length; i++) {
            if (genotipo[i] == 1) {
                fitnes += getPesosMohcila()[i];
            }
        }
        if (fitnes > getVolumenMochila()) {
            return 0;
        } else {
            return fitnes;
        }
    }

    public int calculateBenefit(int[] genotipo) {
        int costo = 0;
        for (int i = 0; i < genotipo.length; i++) {
            if (genotipo[i] == 1) {
                costo += getBenefit()[i];
            }
        }
        return costo;
    }

    public int getVolumenMochila() {
        return volumenMochila;
    }

    public void setVolumenMochila(int volumenMochila) {
        this.volumenMochila = volumenMochila;
    }

    public int[] getPesosMohcila() {
        return pesosMohcila;
    }

    public void setPesosMohcila(int[] pesosMohcila) {
        this.pesosMohcila = pesosMohcila;
    }

    public int[] getBenefit() {
        return benefit;
    }

    public void setCostos(int[] costos) {
        this.benefit = costos;
    }

}
