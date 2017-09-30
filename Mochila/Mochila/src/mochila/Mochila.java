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
public class Mochila {

    private int[] costo;
    private int[] volumen;
    private int beneficio;
    private int cantidadProductos;
    private int[][] poblacion;
    private int[] funcionObjetivo;
    private int[] infactibilidad;
    private double alpha;
    private final int TAMANO_POBLACION = 20;
    private final double TASA_RECOMBINACION = 0.9;
    private final double TASA_MUTACION = 0.05;
    private final int NUMERO_ITERACIONES = 5;

    public Mochila(int[] costo, int[] valor, int beneficio, int cantidadProductos) {
        this.costo = costo;
        this.volumen = valor;
        this.beneficio = beneficio;
        this.cantidadProductos = cantidadProductos;
        this.alpha = 0;
    }

    private void generarPoblacionInicial() {
        this.poblacion = new int[TAMANO_POBLACION][cantidadProductos];
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            int prod = (int) (Math.random() * cantidadProductos) + 1;
            if (prod == cantidadProductos) {
                for (int j = 0; j < cantidadProductos; j++) {
                    poblacion[i][j] = 1;
                }
            } else {
                for (int j = 0; j < prod; j++) {
                    int n = (int) (Math.random() * cantidadProductos);
                    if (poblacion[i][n] == 0) {
                        poblacion[i][n] = 1;
                    } else {
                        j--;
                    }
                }
            }

        }
        calcularFuncionObjetivoInfactibilidad();

    }

    private void calcularFuncionObjetivoInfactibilidad() {
        this.funcionObjetivo = new int[TAMANO_POBLACION];
        this.infactibilidad = new int[TAMANO_POBLACION];
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            for (int j = 0; j < cantidadProductos; j++) {
                if (poblacion[i][j] == 1) {
                    funcionObjetivo[i] += costo[j];
                    infactibilidad[i] += volumen[j];
                }
            }
            infactibilidad[i] = (infactibilidad[i] - beneficio) < 0 ? 0 : infactibilidad[i] - beneficio;// Ajuste de la infactibilidad.
        }
    }



    private int[][] seleccion() {
        double[] funcionAdaptacion = new double[TAMANO_POBLACION];//Vector para la funcion de adaptacion
        double[] numeroDescendientes = new double[TAMANO_POBLACION];//Numero de descendientes para cada uno apartir de la FA.
        int[] numeroDescendientesEntero = new int[TAMANO_POBLACION];//Numero de descendientes
        double promedioFA = 0;
        int totalDescendientes = 0;
        //Calcular funcion adaptabilidad para la poblacion
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            double aux = funcionObjetivo[i] - alpha * infactibilidad[i];
            funcionAdaptacion[i] = (aux < 0) ? 0 : aux;
            promedioFA += funcionAdaptacion[i];
        }
        promedioFA /= TAMANO_POBLACION;
        //Calcula el numero de descendietes.
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            numeroDescendientes[i] = funcionAdaptacion[i] / promedioFA;
            numeroDescendientesEntero[i] = (int) numeroDescendientes[i];
            numeroDescendientes[i] -= numeroDescendientesEntero[i];
            totalDescendientes += numeroDescendientesEntero[i];
        }
        while (totalDescendientes < TAMANO_POBLACION) {
            int aux = 0;
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                if (numeroDescendientes[i] > numeroDescendientes[aux]) {
                    aux = i;
                }
            }
            numeroDescendientesEntero[aux] += 1;
            numeroDescendientes[aux] = 0;
            totalDescendientes++;
        }
        //Se crean los padres apartir de la selecion.
        int[][] padres = new int[TAMANO_POBLACION][cantidadProductos];
        int contadador = 0;
        while (contadador < TAMANO_POBLACION) {
            int aux = 0;
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                if (numeroDescendientesEntero[i] > numeroDescendientesEntero[aux]) {
                    aux = i;
                }
            }
            for (int i = 0; i < numeroDescendientesEntero[aux]; i++) {
                System.arraycopy(poblacion[aux], 0, padres[contadador], 0, cantidadProductos);
                contadador++;
            }
            numeroDescendientesEntero[aux] = 0;
        }
        return padres;
    }

    private void recombinar() {
        int[] padresU = new int[TAMANO_POBLACION];//Indica si un padre ya ha sido usado para recombinar.
        int[] padre1 = new int[cantidadProductos];
        int[] padre2 = new int[cantidadProductos];
        int[][] padres = seleccion();
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            if (padresU[i] == 0) {
                System.arraycopy(padres[i], 0, padre1, 0, cantidadProductos);
                padresU[i] = 1;
                int next = i;
                if (next < TAMANO_POBLACION) {
                    while (padresU[next] != 0) {
                        next = (int) (Math.random() * (TAMANO_POBLACION - (1 + i))) + (1 + i);
                    }
                    System.arraycopy(padres[i], 0, padre2, 0, cantidadProductos);
                    padresU[next] = 1;
                    if (Math.random() <= TASA_RECOMBINACION) {
                        int indRecom = (int) (Math.random() * (cantidadProductos - 2)) + 1;
                        int aux[] = new int[cantidadProductos];
                        for (int j = indRecom; j < cantidadProductos; j++) {
                            padre1[j] = padre2[j];
                            padre2[j] = aux[j];
                        }
                    }
                    for (int j = 0; j < cantidadProductos; j++) {
                        poblacion[i][j] = padre1[j];
                        poblacion[next][j] = padre2[j];
                    }
                }
            }
        }
        calcularFuncionObjetivoInfactibilidad();
    }

    private void mutacion() {
        if (Math.random() <= TASA_MUTACION) {

            int poblador = (int) (Math.random() * TAMANO_POBLACION);
            int gen = (int) (Math.random() * cantidadProductos);
            poblacion[poblador][gen] = (poblacion[poblador][gen]) == 0 ? 1 : 0;
            funcionObjetivo[poblador] = infactibilidad[poblador] = 0;
            for (int j = 0; j < cantidadProductos; j++) {
                if (poblacion[poblador][j] == 1) {
                    funcionObjetivo[poblador] += costo[j];
                    infactibilidad[poblador] += volumen[j];
                }
            }
            infactibilidad[poblador] = (infactibilidad[poblador] - beneficio) < 0 ? 0 : infactibilidad[poblador] - beneficio;
        }
    }

    public void start() {
        generarPoblacionInicial();
        
        
        int n =0;
        while(n<NUMERO_ITERACIONES){
            recombinar();
            mutacion();
            alpha+=0.15;
            n++;
        }
        int aux=0;
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            if (funcionObjetivo[aux]>funcionObjetivo[i] && infactibilidad[i]==0) {
                aux=i;
            }
        }
        for (int i = 0; i < cantidadProductos; i++) {
            System.out.print(poblacion[aux][i]);
        }
        System.out.println("\n"+funcionObjetivo[aux]);
        
        

    }

}
