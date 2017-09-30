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
public class AgenteViajero {

    /**
     * @param args the command line arguments
     */
    public static int[][] poblacionInicial(int tamanoPoblacion, int cantidadCiudades) {
        int[][] poblacion = new int[tamanoPoblacion][cantidadCiudades];
        for (int i = 0; i < tamanoPoblacion; i++) {
            int[] ciudad = new int[cantidadCiudades];
            for (int j = 0; j < cantidadCiudades; j++) {
                int aux = (int) (Math.random() * cantidadCiudades);
                if (ciudad[aux] == 0) {
                    ciudad[aux] = 1;
                    poblacion[i][j] = aux;
                } else {
                    j--;
                }
            }
        }
        return poblacion;
    }
    public static int [] calcularFuncionObjetivo(int[][]poblacion,int[][]distancias,int tamanoPoblacion, int cantidadCiudades){
        int[] funcionObjetivo = new int[tamanoPoblacion];
        for (int i = 0; i < tamanoPoblacion; i++) {
            for (int j = 0; j < cantidadCiudades-1; j++) {
                funcionObjetivo[i]+=distancias[poblacion[i][j]][poblacion[i][j+1]];
            }
            funcionObjetivo[i]+=distancias[poblacion[i][0]][poblacion[i][cantidadCiudades-1]];
        }
        return funcionObjetivo;
    }
    public static int [] seleccion(int []funcionObjetivo, int tamanoPoblacion, int tamanoTorneo){
        int [] descendientes = new int[tamanoPoblacion];
        int contador=0;
        while(contador<tamanoPoblacion){
            int [] participantes = new int[tamanoPoblacion];
            int [][] padrespotenciales = new int[tamanoTorneo][2];
            // Se encarga de seleccionar a los participantes.
            for (int i = 0; i < tamanoTorneo; i++) {
                int aux = (int)(Math.random()*tamanoPoblacion);
                if (participantes[aux]==0) {
                    padrespotenciales[i][0]=aux;
                    padrespotenciales[i][1]=funcionObjetivo[aux];
                    participantes[aux]=1;
                }else{
                    i--;
                }
            }
            //Determina al ganador del torneo.
            int ganador = 0;
            for (int i = 1; i < tamanoTorneo; i++) {
                if (padrespotenciales[ganador][1]> padrespotenciales[i][1]) {
                    ganador =i;
                }
            }
            descendientes[padrespotenciales[ganador][0]]+=1;
            contador++;
            
            
            
        }
        return descendientes;
    }
    

    public static void main(String[] args) {
        // TODO code application logic here
        int cantidadCiudades = 5;
        int tamanoPoblacion = 20;
        int[][] distancias = new int[cantidadCiudades][cantidadCiudades];
        distancias[0][1] = distancias[1][0] = 2; //0 1 = 2
        distancias[0][2] = distancias[2][0] = 1;// 0 2 = 1
        distancias[0][3] = distancias[3][0] = 10;//0 3 = 10
        distancias[0][4] = distancias[4][0] = 25;//0 4 = 25
        distancias[1][2] = distancias[2][1] = 18;//1 2 = 18
        distancias[1][3] = distancias[3][1] = 5;// 1 3 = 5
        distancias[1][4] = distancias[4][1] = 5;//1 4 = 5
        distancias[2][3] = distancias[3][2] = 20;// 2 3 = 20
        distancias[2][4] = distancias[4][2] = 2;//2 4 = 2
        distancias[3][4] = distancias[4][3] = 8;//3 4 = 8

        int[][] poblacion = poblacionInicial(tamanoPoblacion, cantidadCiudades);
        int[] funcionObjetivo = calcularFuncionObjetivo(poblacion, distancias, tamanoPoblacion, cantidadCiudades);
        int[] des = seleccion(funcionObjetivo, tamanoPoblacion, 4);
        for (int i = 0; i < tamanoPoblacion; i++) {
            for (int j = 0; j < cantidadCiudades; j++) {
                System.out.print(poblacion[i][j]);
            }
            System.out.println(" "+funcionObjetivo[i]+" "+des[i]);
        }

    }

}
