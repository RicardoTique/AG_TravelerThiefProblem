/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.data;

/**
 *
 * @author Ricardo Tique
 */
public class Data {

    private int numCities;
    private int numItems;
    private double knapsackCapacity;
    private double vmax;
    private double vmin;
    private double knapsackRent;
    private double[][] distances;
    private int[][] avProducts;
    private double[] productWeights;
    private double[] productBenefits;

    public Data(int numCities, int numItems, double knapsackCapacity, double vmax, 
            double vmin, double knapsackRent, double[][] distances, int[][] avProducts, 
            double[] productWeights, double[] productBenefits) {
        this.numCities = numCities;
        this.numItems = numItems;
        this.knapsackCapacity = knapsackCapacity;
        this.vmax = vmax;
        this.vmin = vmin;
        this.knapsackRent = knapsackRent;
        this.distances = distances;
        this.avProducts = avProducts;
        this.productWeights = productWeights;
        this.productBenefits = productBenefits;
    }

    public int getNumCities() {
        return numCities;
    }

    public void setNumCities(int numCities) {
        this.numCities = numCities;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public double getKnapsackCapacity() {
        return knapsackCapacity;
    }

    public void setKnapsackCapacity(double knapsackCapacity) {
        this.knapsackCapacity = knapsackCapacity;
    }

    public double getVmax() {
        return vmax;
    }

    public void setVmax(double vmax) {
        this.vmax = vmax;
    }

    public double getVmin() {
        return vmin;
    }

    public void setVmin(double vmin) {
        this.vmin = vmin;
    }

    public double getKnapsackRent() {
        return knapsackRent;
    }

    public void setKnapsackRent(double knapsackRent) {
        this.knapsackRent = knapsackRent;
    }

    public double[][] getDistances() {
        return distances;
    }

    public void setDistances(double[][] distances) {
        this.distances = distances;
    }

    public int[][] getAvProducts() {
        return avProducts;
    }

    public void setAvProducts(int[][] avProducts) {
        this.avProducts = avProducts;
    }

    public double[] getProductWeights() {
        return productWeights;
    }

    public void setProductWeights(double[] productWeights) {
        this.productWeights = productWeights;
    }

    public double[] getProductBenefits() {
        return productBenefits;
    }

    public void setProductBenefits(double[] productBenefits) {
        this.productBenefits = productBenefits;
    }

    
    

}
