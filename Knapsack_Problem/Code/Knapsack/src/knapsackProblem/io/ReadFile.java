/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem.io;

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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import knapsackProblem.data.Data;
import knapsackProblem.genetic.Knapsack;

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
        return saveData();
    }

    private Data saveData() {
        String[] temp = information.get(0).split(" ");
        int numberProducts = Integer.valueOf(temp[0]);
        int knapsackCapacity = Integer.valueOf(temp[1]);
        int[] benefit = new int[numberProducts];
        int[] productsWeight = new int[numberProducts];
        for (int i = 1; i <= numberProducts; i++) {
            temp = information.get(i).split(" ");
            benefit[i - 1] = Integer.valueOf(temp[0]);
            productsWeight[i - 1] = Integer.valueOf(temp[1]);
        }
        return new Data(knapsackCapacity, numberProducts, productsWeight, benefit);
    }
}
