/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

import mochila.Mochila;

/**
 *
 * @author Ricardo Tique
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] c = {100, 60, 70, 15, 15};
        int[] v = {42, 23, 21, 15, 7};
        int b = 60;
        Mochila m = new Mochila(c, v, b, 5);
        FuncionFitnetss funcionF = new FuncionFitnetss(b, v, c);
        Principal mochila = new Principal(funcionF);
        mochila.start();
    }

}
