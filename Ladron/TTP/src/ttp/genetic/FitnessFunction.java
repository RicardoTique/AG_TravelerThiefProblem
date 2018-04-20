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
package ttp.genetic;

import ttp.data.Data;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class FitnessFunction {

    private double knapsackCapacity;
    private double[] productsWeight;
    private double[] productsBenefit;
    private double[][] distances;
    private double vMax;
    private double vMin;
    private double knapsackRent;
    public double[][] orderBenefit;

    public FitnessFunction(Data data) {
        this.distances = data.getDistances();
        this.knapsackCapacity = data.getKnapsackCapacity();
        this.knapsackRent = data.getKnapsackRent();
        this.productsBenefit = data.getProductBenefits();
        this.productsWeight = data.getProductWeights();
        this.vMax = data.getVmax();
        this.vMin = data.getVmin();
    }

    public double calculateFitness(int[] genotype, int[] packingTrace) {
        
        double cBenefit = 0;
        double time = 0;
        double cWeight = 0;
        double aux = (this.vMax - this.vMin) / this.knapsackCapacity;

        //Calcular el beneficio y el peso de los items.        
        for (int i = 0; i < genotype.length; i++) {
            if (packingTrace[i] != 0) {
                cBenefit += this.productsBenefit[packingTrace[i] - 1];
            }
        }
        for (int i = 0; i < genotype.length - 1; i++) {
            if (packingTrace[i] != 0) {
                cWeight += this.productsWeight[packingTrace[i] - 1];
            }           
            double cV = this.vMax - (cWeight * aux);
            time += (distances[genotype[i] - 1][genotype[i + 1] - 1]) / cV;
        }
        if (packingTrace[genotype.length - 1] != 0) {
            cWeight += this.productsWeight[packingTrace[genotype.length - 1] - 1];
        }
        double cV = this.vMax - (cWeight * aux);
        time += (distances[genotype[0] - 1][genotype[genotype.length - 1] - 1]) / cV;

        return cBenefit - (knapsackRent * time);
    }

    private void orderBenefit() {
        this.orderBenefit = new double[this.productsBenefit.length][2];
        for (int i = 0; i < this.productsBenefit.length; i++) {
            this.orderBenefit[i][0] = i;
            this.orderBenefit[i][1] = this.productsBenefit[i];
        }
        orderMethot(this.orderBenefit, 0, this.productsBenefit.length - 1);
    }

    private void orderMethot(double[][] vector, int izquierda, int derecha) {
        double indicePivote = vector[izquierda][0];
        double pivote = vector[izquierda][1];
        int i = izquierda;
        int j = derecha;
        double auxIndice;
        double auxPeso;
        while (i < j) {
            while (vector[i][1] <= pivote && i < j) {
                i++;
            }
            while (vector[j][1] > pivote) {
                j--;
            }
            if (i < j) {
                auxIndice = vector[i][0];
                auxPeso = vector[i][1];
                vector[i][0] = vector[j][0];
                vector[i][1] = vector[j][1];
                vector[j][0] = auxIndice;
                vector[j][1] = auxPeso;
            }
        }
        vector[izquierda][0] = vector[j][0];
        vector[izquierda][1] = vector[j][1];
        vector[j][0] = indicePivote;
        vector[j][1] = pivote;
        if (izquierda < j - 1) {
            orderMethot(vector, izquierda, j - 1);
        }
        if (j + 1 < derecha) {
            orderMethot(vector, j + 1, derecha);
        }
    }

    public double getKnapsackCapacity() {
        return knapsackCapacity;
    }

    public void setKnapsackCapacity(int knapsackCapacity) {
        this.knapsackCapacity = knapsackCapacity;
    }

    public double[] getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(double[] productsWeight) {
        this.productsWeight = productsWeight;
    }

    public double[] getBenefit() {
        return productsBenefit;
    }

    public void setCostos(double[] costos) {
        this.productsBenefit = costos;
    }

}
