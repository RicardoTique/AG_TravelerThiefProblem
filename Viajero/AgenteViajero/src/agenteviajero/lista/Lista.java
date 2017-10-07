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
public class Lista {

    private ArrayList<Nodo> nodos;

    public Lista() {
        nodos = new ArrayList<Nodo>();
    }

    public void agregarNodo(int numeroCiudad) {
        if (nodos.isEmpty()) {
            nodos.add(new Nodo(numeroCiudad));
        } else {
            if (!existeNodo(numeroCiudad)) {
                nodos.add(new Nodo(numeroCiudad));
            }            
        }
    }
    private boolean existeNodo(int numeroCiudad){
        boolean respuesta = false;
        for (int i = 0; i <nodos.size(); i++) {
            if (nodos.get(i).getNumeroCiudad()==numeroCiudad) {
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

}
