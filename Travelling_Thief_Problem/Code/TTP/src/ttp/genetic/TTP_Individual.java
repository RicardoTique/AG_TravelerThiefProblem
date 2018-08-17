/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.genetic;

import java.util.ArrayList;
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

    public TTP_Individual(int n, int p, int[][] avItems) {

        this.n = n;
        this.p = p;
        this.route = new int[n];
        this.products = new int[n];
        this.random_rute(n);
        this.random_products(n, avItems);

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

    public void removeCity(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.getCity(j) == numbers[i]) {
                    this.setCity(j, 0);
                }
            }
        }
    }

    public void slideLeft(int index) {
        for (int i = 0; i < index; i++) {
            if (this.getCity(i) == 0) {
                for (int j = i + 1; j < this.size(); j++) {
                    if (this.getCity(j) != 0) {
                        this.setCity(i, this.getCity(j));
                        this.setCity(j, 0);
                        break;
                    }
                }
            }
        }
    }

    public void slideRight(int index) {
        for (int i = this.size() - 1; i > index; i--) {
            if (this.getCity(i) == 0) {
                for (int j = i - 1; j >= 0; j--) {
                    if (this.getCity(j) != 0) {
                        this.setCity(i, this.getCity(j));
                        this.setCity(j, 0);
                        break;
                    }
                }
            }
        }
    }

    public void orderRight(int index) {
        int j = 0;
        for (int i = index + 1; i < this.size(); i++) {
            if (this.getCity(i) == 0) {
                while (j <= index) {
                    if (this.getCity(j) != 0) {
                        this.setCity(i, this.getCity(j));
                        this.setCity(j, 0);
                        break;
                    }
                    j++;
                }
            }
        }
    }

    public void orderLeft(int index) {

        for (int i = 0; i <= index; i++) {
            if (this.getCity(i) == 0) {
                int j = i + 1;
                while (j <= index) {
                    if (this.getCity(j) != 0) {
                        this.setCity(i, this.getCity(j));
                        this.setCity(j, 0);
                        break;
                    }
                    j++;
                }
            }
        }
    }

    public void insertSubGenome(int start, int end, int[] subGenome) {
        for (int i = start; i <= end; i++) {
            this.setCity(i, subGenome[i - start]);
        }

    }

    private void random_products(int size, int[][] itemsAv) {
        for (int i = 0; i < size; i++) {
            this.products[i] = getIdProduct(this.getCity(i), itemsAv);
        }
    }

    private int getIdProduct(int city, int[][] itemsAv) {
        ArrayList<Integer> itemsAv_City = new ArrayList<Integer>();

        for (int i = 0; i < this.p; i++) {
            if (itemsAv[i][city - 1] != 0) {
                itemsAv_City.add(i + 1);
            }
        }
        if (!itemsAv_City.isEmpty()) {
            Random rd = new Random();
            int in = rd.nextInt(itemsAv_City.size() + 1);
            if (in != 0) {
                return itemsAv_City.get(in - 1);
            }
        }
        return 0;

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

    @Override
	public Object clone() {
        return new TTP_Individual(this);
    }
}
