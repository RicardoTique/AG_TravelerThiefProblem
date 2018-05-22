/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import tsp.data.Data;

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
        String temp[] = this.information.get(0).split(" ");
        String problemName = temp[2];
        temp = this.information.get(1).split(" ");
        int DIM = Integer.valueOf(temp[1]);
        double[][] coordinates = new double[DIM][2];

        for (int i = 3; i < this.information.size(); i++) {

            temp = this.information.get(i).split("\t");
            coordinates[i - 3][0] = Double.valueOf(temp[1]);
            coordinates[i - 3][1] = Double.valueOf(temp[2]);
        }
        return new Data(DIM, coordinates, problemName);
    }
}
