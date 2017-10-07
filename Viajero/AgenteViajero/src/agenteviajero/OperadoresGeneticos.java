/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteviajero;

/**
 *
 * @author Ricardo Tique
 */
public class OperadoresGeneticos {

    public Individuo[] cruce(Individuo individuo1, Individuo individuo2) {
        Individuo[] hijos = new Individuo[2];
        int tamano = individuo1.getGenotipo().length;

        int[] hijo1 = new int[tamano + 2];
        int[] hijo2 = new int[tamano + 2];

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

    public int[] generarVecinos(int[] genotipo1, int[] genotipo2) {
        int[] vecinos = new int[genotipo1.length];

        int[] aux1 = new int[genotipo1.length + 2];
        int[] aux2 = new int[genotipo1.length + 2];
        aux1[0] = genotipo1[0];
        aux1[genotipo1.length + 1] = genotipo1[genotipo1.length - 1];
        aux2[0] = genotipo2[0];
        aux2[genotipo1.length + 1] = genotipo2[genotipo2.length - 1];
        for (int i = 0; i < genotipo1.length; i++) {
            aux1[i + 1] = genotipo1[i];
            aux2[i + 1] = genotipo2[i];
        }
        for (int i = 1; i <= genotipo1.length; i++) {
            vecinos[aux1[i - 1]] += 1;
            vecinos[aux1[i + 1]] += 1;
            vecinos[aux2[i - 1]] += 1;
            vecinos[aux2[i + 1]] += 1;
        }

        return vecinos;
    }
}
