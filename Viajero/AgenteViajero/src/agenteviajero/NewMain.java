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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int [] a ={1,2,3,4,5};
        int [] b ={2,3,5,5,1};
        OperadoresGeneticos op = new OperadoresGeneticos();
        
        int []vecinos = op.generarVecinos(a, b);
        for (int i = 0; i < vecinos.length; i++) {
            System.out.println(vecinos[i]);
        }
    }
    
}
