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
import unalcol.optimization.OptimizationFunction;

/**
 *
 * @author Ricardo Tique & Carlos Andres Sierra
 */
public class TTP extends OptimizationFunction<TTP_Individual> {

    private double knapsackCapacity;
    private double[] productsWeight;
    private double[] productsBenefit;
    private double[][] distances;
    private double vMax;
    private double vMin;
    private double knapsackRent;
    public double[][] orderBenefit;

    public TTP(Data data) {
        this.distances = data.getDistances();
        this.knapsackCapacity = data.getKnapsackCapacity();
        this.knapsackRent = data.getKnapsackRent();
        this.productsBenefit = data.getProductBenefits();
        this.productsWeight = data.getProductWeights();
        this.vMax = data.getVmax();
        this.vMin = data.getVmin();
        this.orderBenefit();
    }

    @Override
    public Double compute(TTP_Individual genome) {

        double cBenefit = 0;
        double time = 0;
        double cWeight = 0;
        double aux = (this.vMax - this.vMin) / this.knapsackCapacity;
        for (int i = 0; i < genome.size(); i++) {
            if (genome.getProduct(i) != 0) {
                cBenefit += this.productsBenefit[genome.getProduct(i) - 1];
            }
        }

        for (int i = 0; i < genome.size() - 1; i++) {
            if (genome.getProduct(i) != 0) {
                cWeight += this.productsWeight[genome.getProduct(i) - 1];
            }
            double cV = this.vMax - (cWeight * aux);
            time += (distances[genome.getCity(i) - 1][genome.getCity(i + 1) - 1]) / cV;
        }
        if (genome.getProduct(genome.size() - 1) != 0) {
            cWeight += this.productsWeight[genome.getProduct(genome.size() - 1) - 1];
        }
        double cV = this.vMax - (cWeight * aux);
        time += (distances[genome.getCity(0) - 1][genome.getCity(genome.size() - 1) - 1]) / cV;
        double result = cBenefit - (knapsackRent * time);
        return (result > 0) ? result : 0;
    }

    private void orderBenefit() {
        this.orderBenefit = new double[this.productsBenefit.length][2];
        for (int i = 0; i < this.productsBenefit.length; i++) {
            this.orderBenefit[i][0] = i;
            this.orderBenefit[i][1] = this.productsBenefit[i];
        }
        orderMethot(this.orderBenefit, 0, this.productsBenefit.length - 1);
    }

    private void orderMethot(double[][] array, int left, int right) {
        double indicePivote = array[left][0];
        double pivote = array[left][1];
        int i = left;
        int j = right;
        double auxIndice;
        double auxPeso;
        while (i < j) {
            while (array[i][1] <= pivote && i < j) {
                i++;
            }
            while (array[j][1] > pivote) {
                j--;
            }
            if (i < j) {
                auxIndice = array[i][0];
                auxPeso = array[i][1];
                array[i][0] = array[j][0];
                array[i][1] = array[j][1];
                array[j][0] = auxIndice;
                array[j][1] = auxPeso;
            }
        }
        array[left][0] = array[j][0];
        array[left][1] = array[j][1];
        array[j][0] = indicePivote;
        array[j][1] = pivote;
        if (left < j - 1) {
            orderMethot(array, left, j - 1);
        }
        if (j + 1 < right) {
            orderMethot(array, j + 1, right);
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
