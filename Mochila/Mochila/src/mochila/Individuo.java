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
public class Individuo implements Cloneable{
    private int [] genotipo;
    private double fitnest;

    public Individuo(int tamano) {
        this.genotipo = new int[tamano];
    }

    public double getFitnest() {
        return fitnest;
    }

    public int[] getGenotipo() {
        return genotipo;
    }
    
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
    
    
    
    
}
