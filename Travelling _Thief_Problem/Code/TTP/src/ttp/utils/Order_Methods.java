/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.utils;

/**
 *
 * @author Ricardo Tique
 */
public class Order_Methods {

    private static void merge(double array[][], int left, int middle, int right) {

        int n1 = middle - left + 1;
        int n2 = right - middle;

        double L[][] = new double[n1][2];
        double R[][] = new double[n2][2];

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
            if (L[i][1] >= R[j][1]) {
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

    public static void sort(double array[][], int left, int right) {
        if (left < right) {
            int m = (left + right) / 2;
            sort(array, left, m);
            sort(array, m + 1, right);
            merge(array, left, m, right);
        }
    }
}
