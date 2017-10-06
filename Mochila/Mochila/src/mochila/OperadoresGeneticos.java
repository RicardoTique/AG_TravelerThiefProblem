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
public class OperadoresGeneticos {

    public Individuo[] cruce(Individuo individuo1, Individuo individuo2) {
        Individuo[] hijos = new Individuo[2];
        int tamano = individuo1.getGenotipo().length;
        int[] hijo1 = new int[individuo1.getGenotipo().length];
        int[] hijo2 = new int[individuo2.getGenotipo().length];

        int indRecom = (int) (Math.random() * (tamano - 2)) + 1;

        for (int i = 0; i < indRecom; i++) {
            hijo1[i] = individuo1.getGenotipo()[i];
            hijo2[i] = individuo2.getGenotipo()[i];
        }
        for (int i = indRecom; i < tamano; i++) {
            hijo1[i] = individuo1.getGenotipo()[i];
            hijo2[i] = individuo2.getGenotipo()[i];
        }
        hijos[0] = new Individuo(hijo1);
        hijos[1] = new Individuo(hijo2);
        return hijos;
    }

    public Individuo[] mutacion(Individuo individuo1, Individuo individuo2) {
        int tamano = 0;

        Individuo[] hijos = new Individuo[2];
        hijos[0] = (Individuo) individuo1.clone();
        hijos[1] = (Individuo) individuo2.clone();

        for (int i = 0; i < 2; i++) {
            int indice = (int) (Math.random() * (tamano));
            if (hijos[i].getGenotipo()[indice] == 0) {
                hijos[i].getGenotipo()[indice] = 1;
            } else {
                hijos[i].getGenotipo()[indice] = 0;
            }
        }

        return hijos;
    }
}
