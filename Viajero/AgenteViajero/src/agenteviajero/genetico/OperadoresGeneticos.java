/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteviajero.genetico;

import agenteviajero.lista.*;

/**
 *
 * @author Ricardo Tique
 */
public class OperadoresGeneticos {

    public Individuo[] cruce(Individuo individuo1, Individuo individuo2) {
        Individuo[] hijos = new Individuo[2];

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

    public Lista generarTablaAdyacencia(int[] genotipo1, int[] genotipo2) {
        Lista tabla = new Lista();
        int tamanoAux = genotipo1.length + 2;
        int[] aux1 = new int[tamanoAux];
        int[] aux2 = new int[tamanoAux];

        aux1[0] = genotipo1[genotipo1.length - 1];
        aux2[0] = genotipo2[genotipo2.length - 1];
        aux1[aux1.length - 1] = genotipo1[0];
        aux2[aux2.length - 1] = genotipo2[0];

        for (int i = 0; i < genotipo1.length; i++) {
            aux1[i + 1] = genotipo1[i];
            aux2[i + 1] = genotipo2[i];
        }

        for (int i = 1; i < tamanoAux - 1; i++) {
            tabla.agregarNodo(aux1[i]);
            
        }
        
        return tabla;
    }
}
