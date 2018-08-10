/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.data;

/**
 *
 * @author Ricardo Tique
 */
public class Data {
    private String problemName;
    private int size;
    private double [][] coordinates;
    
    public Data(int size, double[][]coordinates,String problemName){
        this.size = size;
        this.coordinates = coordinates;
        this.problemName = problemName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }
    
    
    
}
