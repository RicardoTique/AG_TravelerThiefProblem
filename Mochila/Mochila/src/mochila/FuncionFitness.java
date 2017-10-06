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
public class FuncionFitness {

    private int volumenMochila;
    private int[] pesosMohcila;
    private int[] costos;

    public FuncionFitness(int volumenMochila, int[] pesosMohcila, int[] costos) {
        this.volumenMochila = volumenMochila;
        this.pesosMohcila = pesosMohcila;
        this.costos = costos;
    }

    public int calcularFitness(int[] genotipo) {
        int fitnes = 0;
        for (int i = 0; i < genotipo.length; i++) {
            if (genotipo[i] == 1) {
                fitnes += getPesosMohcila()[i];
            }
        }
        if (fitnes > getVolumenMochila()) {
            return fitnes;
        } else {
            return fitnes;
        }
    }

    public int calularCostos(int[] genotipo) {
        int costo = 0;
        for (int i = 0; i < genotipo.length; i++) {
            if (genotipo[i] == 1) {
                costo += getCostos()[i];
            }
        }
        return costo;
    }

    public int getVolumenMochila() {
        return volumenMochila;
    }

    public void setVolumenMochila(int volumenMochila) {
        this.volumenMochila = volumenMochila;
    }

    public int[] getPesosMohcila() {
        return pesosMohcila;
    }

    public void setPesosMohcila(int[] pesosMohcila) {
        this.pesosMohcila = pesosMohcila;
    }

    public int[] getCostos() {
        return costos;
    }

    public void setCostos(int[] costos) {
        this.costos = costos;
    }

}
