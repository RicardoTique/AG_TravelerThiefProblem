/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.genetic;

import java.util.Random;

/**
 *
 * @author Ricardo Tique
 */
public class TSP_Individual implements Cloneable {

    private int[] genome;
    private int size;

    public TSP_Individual(int size) {
        this.size = size;
        this.genome = new int[size];
        random_genotype(size);
    }
    public TSP_Individual(TSP_Individual source){
        if (source.genome != null) {
            this.size = source.size();
            this.genome = new int[this.size];
            for (int i = 0; i < size(); i++) {
                genome[i]=source.get(i);
            }
        }
    }

    private void random_genotype(int size) {
        Random rd = new Random();
        int swap, index;

        for (int i = 0; i < size; i++) {
            this.genome[i] = (i + 1);
        }

        for (int i = 0; i < size; i++) {
            index = rd.nextInt(size);

            swap = this.genome[i];
            this.genome[i] = this.genome[index];
            this.genome[index] = swap;
        }

        for (int i = size - 1; i >= 0; i--) {
            index = rd.nextInt(size);

            swap = this.genome[i];
            this.genome[i] = this.genome[index];
            this.genome[index] = swap;
        }

    }

    public Object clone() {
        Object obj = null;

        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("The individual can't be cloned.");
        }
        return obj;
    }

    public int size() {
        return size;
    }

    public void removeNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.get(j) == numbers[i]) {
                    this.set(j, 0);
                }
            }
        }
    }

    public void slideLeft(int indice) {
        for (int i = 0; i < indice; i++) {
            if (this.get(i) == 0) {
                for (int j = i + 1; j < this.size(); j++) {
                    if (this.get(j) != 0) {
                        this.set(i, this.get(j));
                        this.set(j, 0);
                        break;
                    }
                }
            }
        }
    }

    public void slideRight(int indice) {
        for (int i = this.size() - 1; i > indice; i--) {
            if (this.get(i) == 0) {
                for (int j = i - 1; j >= 0; j--) {
                    if (this.get(j) != 0) {
                        this.set(i, this.get(j));
                        this.set(j, 0);
                        break;
                    }
                }
            }
        }
    }

    public void insertSubArray(int start, int end, int[] subArray) {
        for (int i = start; i <= end; i++) {
            this.set(i, subArray[i - start]);
        }

    }

    public int get(int i) {
        return genome[i];
    }

    public void set(int i, int city) {
        this.genome[i] = city;
    }
}
