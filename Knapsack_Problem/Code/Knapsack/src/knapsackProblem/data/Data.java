/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem.data;

/**
 *
 * @author Ricardo Tique
 */
public class Data {

    private int knapsackCapacity;
    private int numberProducts;
    private int[] productsWeight;
    private int[] benefit;
    
    public Data(int knapsackCapacity,int numberProducts,int[] productsWeight, int[] benefit) {
        this.knapsackCapacity = knapsackCapacity;
        this.productsWeight = productsWeight;
        this.benefit = benefit;        
        this.numberProducts = numberProducts;
    }

    public int getKnapsackCapacity() {
        return knapsackCapacity;
    }

    public void setKnapsackCapacity(int knapsackCapacity) {
        this.knapsackCapacity = knapsackCapacity;
    }

    public int[] getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(int[] productsWeight) {
        this.productsWeight = productsWeight;
    }

    public int[] getBenefit() {
        return benefit;
    }

    public void setBenefit(int[] benefit) {
        this.benefit = benefit;
    }

    public int getNumberProducts() {
        return numberProducts;
    }

    public void setNumberProducts(int numberProducts) {
        this.numberProducts = numberProducts;
    }

}
