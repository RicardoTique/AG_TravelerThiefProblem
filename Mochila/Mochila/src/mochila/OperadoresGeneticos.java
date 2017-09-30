/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

import java.util.ArrayList;



/**
 *
 * @author Ricardo Tique
 */
public class OperadoresGeneticos {
    public ArrayList<Individuo> cruce(Individuo individuo1, Individuo individuo2, int tamano){
        int [] hijo1 = new int[individuo1.getGenotipo().length];
        int [] hijo2 = new int[individuo1.getGenotipo().length];
        
        int indRecom = (int) (Math.random() * (tamano - 2)) + 1;
        
        return null;
    }
    public ArrayList<Individuo> mutacion(Individuo individuo1, Individuo individuo2, int tamano){
        Individuo hijo1 = (Individuo) individuo1.clone();
        Individuo hijo2 = (Individuo) individuo1.clone();
        ArrayList <Individuo> hijos = new ArrayList<Individuo>();
        int indice = (int) (Math.random() * (tamano));
        if(hijo1.getGenotipo()[indice]==0){
            
        } 
        return hijos;
    }
}
