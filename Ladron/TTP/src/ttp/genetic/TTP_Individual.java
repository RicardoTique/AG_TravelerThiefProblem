/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic;

import java.util.Random;

/**
 *
 * @author Ricardo Tique
 */
public class TTP_Individual implements Cloneable {

    private int[] route = null;//Array usado para almacenar la ruta.
    private int[] products = null; //Array usado para guardar el
    private int n = 0; // Cantidad de ciudades en el plan de robo.
    private int p = 0; // Cantidad de productos en el conjunto de ciudades

    public TTP_Individual(int n, int p) {
        
        this.n = n;
        this.p = p;
        this.route = new int[n];
        this.products = new int[n];
        this.random_rute(n);
        this.random_products(n, p);

    }

    public TTP_Individual(TTP_Individual source) {
        if (source.route != null) {
            n = source.n;
            p = source.p;
            route = new int[source.route.length];
            products = new int[source.products.length];
            for (int i = 0; i < source.route.length; i++) {
                route[i] = source.route[i];
                products[i] = source.products[i];
            }
        }
    }

    private void random_rute(int size) {
        Random rd = new Random();
        int swap, index;

        for (int i = 0; i < size; i++) {
            this.route[i] = (i + 1);
        }

        for (int i = 0; i < size; i++) {
            index = rd.nextInt(size);
            swap = this.route[i];
            this.route[i] = this.route[index];
            this.route[index] = swap;
        }

        for (int i = size - 1; i >= 0; i--) {
            index = rd.nextInt(size);
            swap = this.route[i];
            this.route[i] = this.route[index];
            this.route[index] = swap;
        }
    }

    private void random_products(int size, int p) {
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            this.products[i] = rd.nextInt(p + 1);
        }
    }

    public int getCity(int i) {
        return route[i];
    }

    public int getProduct(int i) {
        return products[i];
    }

    public void setCity(int i, int city) {
        this.route[i] = city;
    }

    public void setProduct(int i, int product) {
        this.products[i] = product;
    }

    public int size() {
        return n;
    }

    public Object clone() {
        return new TTP_Individual(this);
    }
}
