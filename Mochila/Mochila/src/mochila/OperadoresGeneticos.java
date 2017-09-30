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
    public ArrayList<Individuo> cruce(Individuo individuo1, Individuo individuo2){
        int tamano= 0;
        int [] hijo1 = new int[individuo1.getGenotipo().length];
        int [] hijo2 = new int[individuo1.getGenotipo().length];
        
        int indRecom = (int) (Math.random() * (tamano - 2)) + 1;
        
        return null;
    }
    public ArrayList<Individuo> mutacion(Individuo individuo1, Individuo individuo2){
        int tamano= 0;
        
        ArrayList <Individuo> hijos = new ArrayList<Individuo>();
        hijos.add((Individuo) individuo1.clone());
        hijos.add((Individuo) individuo2.clone());
        int indice = (int) (Math.random() * (tamano));
         
        return hijos;
    }
}
