/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.utils;

/**
 *
 * @author Ricardo Tique
 */
public class Utils {

    /**
     * generate two diferent random numbers
     *
     * @param size
     * @return
     */
    public static int[] generateTwoRandomNumbers(int size) {
        
        int[] numbers = new int[2];
        numbers[0] = (int) (Math.random() * (size));
        numbers[1] = numbers[0];

        do {
            numbers[1] = (int) (Math.random() * (size));
        } while (numbers[0] == numbers[1]);
        
        if (numbers[0] > numbers[1]) {
            int aux = numbers[0];
            numbers[0] = numbers[1];
            numbers[1] = aux;
        }
        return numbers;
    }
}
