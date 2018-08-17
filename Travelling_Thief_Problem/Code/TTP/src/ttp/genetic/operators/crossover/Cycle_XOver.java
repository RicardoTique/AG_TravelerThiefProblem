/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic.operators.crossover;

import java.util.ArrayList;
import ttp.genetic.TTP_Individual;
import unalcol.search.variation.Variation_2_2;

/**
 *
 * @author Ricardo Tique
 */
public class Cycle_XOver implements Variation_2_2<TTP_Individual> {

    @Override
    public TTP_Individual[] apply(TTP_Individual child1, TTP_Individual child2) {
        try {
            TTP_Individual[] offspring = new TTP_Individual[2];
            ArrayList<Integer> cycle = new ArrayList<Integer>();
            ArrayList<Integer> index_cycle = new ArrayList<Integer>();
            offspring[0] = new TTP_Individual(child1);
            offspring[1] = new TTP_Individual(child2);

            int size = child1.size();
            int city[][] = copyCitys(child1);
            sort(city, 0, size - 1);

            cycle.add(child1.getCity(0));
            index_cycle.add(0);
            int index = 0;
            do {
                cycle.add(child2.getCity(index_cycle.get(index)));
                index++;
                index_cycle.add(search(city, cycle.get(index), size));

            } while (cycle.get(0) != cycle.get(index));

            for (int i = 0; i < index_cycle.size() - 1; i++) {
                offspring[0].setCity(index_cycle.get(i), child2.getCity(index_cycle.get(i)));
                offspring[0].setProduct(index_cycle.get(i), child2.getProduct(index_cycle.get(i)));
                offspring[1].setCity(index_cycle.get(i), child1.getCity(index_cycle.get(i)));
                offspring[1].setProduct(index_cycle.get(i), child1.getProduct(index_cycle.get(i)));
            }
            return offspring;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[XOver]" + e.getMessage());
        }
        return null;
    }

    private void merge(int array[][], int left, int middle, int right) {

        int n1 = middle - left + 1;
        int n2 = right - middle;

        int L[][] = new int[n1][2];
        int R[][] = new int[n2][2];

        for (int i = 0; i < n1; ++i) {
            L[i][0] = array[left + i][0];
            L[i][1] = array[left + i][1];
        }
        for (int j = 0; j < n2; ++j) {
            R[j][0] = array[middle + 1 + j][0];
            R[j][1] = array[middle + 1 + j][1];
        }

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (L[i][0] <= R[j][0]) {
                array[k][0] = L[i][0];
                array[k][1] = L[i][1];
                i++;
            } else {
                array[k][0] = R[j][0];
                array[k][1] = R[j][1];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k][0] = L[i][0];
            array[k][1] = L[i][1];
            i++;
            k++;
        }

        while (j < n2) {
            array[k][0] = R[j][0];
            array[k][1] = R[j][1];
            j++;
            k++;
        }
    }

    public void sort(int array[][], int left, int right) {
        if (left < right) {
            int m = (left + right) / 2;
            sort(array, left, m);
            sort(array, m + 1, right);
            merge(array, left, m, right);
        }
    }

    private int[][] copyCitys(TTP_Individual genome) {
        int size = genome.size();
        int[][] matrix = new int[size][2];

        for (int i = 0; i < size; i++) {
            matrix[i][0] = genome.getCity(i);
            matrix[i][1] = i;
        }

        return matrix;
    }

    public static int search(int array[][], int city, int size) {
        int n = size;
        int middle, left = 0, right = n - 1;
        while (left <= right) {
            middle = (right + left) / 2;
            if (array[middle][0] == city) {
                return array[middle][1];
            } else if (city < array[middle][0]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
