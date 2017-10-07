/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteviajero.lista;

import java.util.ArrayList;

/**
 *
 * @author Ricardo Tique
 */
public class Nodo {

    private int numeroCiudad;
    private ArrayList<Integer> vecinos;

    public Nodo(int numeroCiudad) {
        this.numeroCiudad = numeroCiudad;
        this.vecinos = new ArrayList<Integer>();
    }

    public void agregarVecino(int vecino) {
        if (this.getVecinos().isEmpty()) {
            this.getVecinos().add(vecino);
        } else {
            int i = 0;
            while (i < this.getVecinos().size()) {
                if (this.getVecinos().get(i) == vecino) {
                    this.getVecinos().set(i, vecino * -1);
                }
                i++;
            }
            if (i == this.getVecinos().size()) {
                this.getVecinos().add(vecino);
            }
        }
    }

    public void eliminarVecino(int vecino) {
        for (int i = 0; i < this.getVecinos().size(); i++) {
            if (this.getVecinos().get(i) == vecino || this.getVecinos().get(i) == (vecino * -1)) {
                this.getVecinos().remove(i);
            }
        }
    }

    public int getNumeroCiudad() {
        return numeroCiudad;
    }

    public ArrayList<Integer> getVecinos() {
        return vecinos;
    }

}
