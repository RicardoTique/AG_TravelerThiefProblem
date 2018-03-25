/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.io;

/*
# Project: TTP: Traveling Thief Problem.
#
# Created by Ricardo Tique & Carlos Andres Sierra on Octuber 2017.
# Copyright (c) 2017  Ricardo Tique & Carlos Andres Sierra. Corporacion Universitaria Minuto de Dios. All rights reserved.
#
# This file is part of GA_TravellingThiefProblem project.
#
# GA_TravellingThiefProblem is free software: you can redistribute it and/or modify it under the terms of the
# GNU General Public License as published by the Free Software Foundation, version 2.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import ttp.data.Data;
import ttp.genetic.FitnessFunction;

/**
 *
 * @author Ricardo Tique
 */
public class ReadFile {

    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private ArrayList<String> information;

    public Data readFile(String file) {

        try {
            String line;
            this.fileReader = new FileReader(file);
            this.bufferedReader = new BufferedReader(fileReader);
            this.information = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                this.information.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException exception) {

        } catch (IOException exception) {

        }
        return buildFitness();
    }

    private Data buildFitness() {
              
        int numCities = Integer.valueOf(this.information.get(0));
        int numItems = Integer.valueOf(this.information.get(1));
        double knapsackWeight = Double.valueOf(this.information.get(2));
        double vmax = Double.valueOf(this.information.get(3));
        double vmin = Double.valueOf(this.information.get(4));
        double knapsackRent = Double.valueOf(this.information.get(5));
        double[][] distances = new double[numCities][numCities];
        int[][] avProducts = new int[numItems][numCities];
        double[] weightProducts = new double[numItems];
        double[] benefitProducts = new double[numItems];
        int index = 6;
        String[] temp;
        while (index < numCities) {
            temp = this.information.get(index).split(" ");
            for (int j = 0; j < numCities; j++) {
                distances[index][j] = Integer.valueOf(temp[j]);
            }
            index++;
        }
        temp = this.information.get(index).split(" ");
        for (int i = 0; i < numItems; i++) {
            weightProducts[i] = Integer.valueOf(temp[i]);
        }
        index++;
        temp = this.information.get(index).split(" ");
        for (int i = 0; i < numItems; i++) {
            benefitProducts[i] = Integer.valueOf(temp[i]);
        }
        index++;
        int aux = index;
        while (index < this.information.size()) {
            temp = this.information.get(index).split(" ");
            for (int i = 0; i < numCities; i++) {
                avProducts[index - aux][i] = Integer.valueOf(temp[i]);
            }
            index++;
        }

        return new Data(numCities, numItems, knapsackWeight, vmax, vmin, knapsackRent, distances, avProducts, weightProducts, benefitProducts);
    }
}
