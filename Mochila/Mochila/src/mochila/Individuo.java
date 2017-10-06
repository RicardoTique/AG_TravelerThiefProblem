/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

/**
 *
 * @author Ricardo Tique
 */
public class Individuo implements Cloneable {

    private int[] genotipo;
    private int fitnest;
    private int costo;

    public Individuo(int tamano) {
        this.genotipo = new int[tamano];
    }

    public Individuo(int[] genotipo) {
        this.genotipo = genotipo;
    }

    public int getFitnest() {
        return fitnest;
    }

    public int[] getGenotipo() {
        return genotipo;
    }

    public void setGenotipo(int[] genotipo) {
        this.setGenotipo(genotipo);
    }

    public void setFitnest(int fitnest) {
        this.fitnest = fitnest;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
}
