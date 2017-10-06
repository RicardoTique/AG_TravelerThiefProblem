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
public class Principal {

    private int tamanoPolacion = 100;
    private Individuo[] poblacion = new Individuo[tamanoPolacion];
    private FuncionFitness funcionFitness;

    public Principal(FuncionFitness funcionFitness) {
        this.funcionFitness = funcionFitness;
    }

    public void generarPoblacion() {
        int tamanoGenotipo = funcionFitness.getCostos().length;
        for (int i = 0; i < tamanoPolacion; i++) {
            int[] genotipo = new int[tamanoGenotipo];
            int cantidadGenes = (int) (Math.random() * tamanoGenotipo) + 1;
            if (cantidadGenes == tamanoGenotipo) {
                for (int j = 0; j < tamanoGenotipo; j++) {
                    genotipo[j] = 1;
                }
            } else {
                for (int j = 0; j < cantidadGenes; j++) {
                    int n = (int) (Math.random() * cantidadGenes);
                    if (genotipo[n] == 0) {
                        genotipo[n] = 1;
                    } else {
                        j--;
                    }
                }
            }
            poblacion[i] = new Individuo(genotipo);
            poblacion[i].setFitnest(funcionFitness.calcularFitness(genotipo));
            poblacion[i].setCosto(funcionFitness.calularCostos(genotipo));
        }
    }

    public Individuo[] seleccionToreno() {
        Individuo[] padres = new Individuo[tamanoPolacion];
        for (int i = 0; i < tamanoPolacion; i++) {
            int[] participantes = new int[4];
            for (int j = 0; j < 4; j++) {
                participantes[j] = (int) (Math.random() * tamanoPolacion);
            }
            Individuo[] ganadores = new Individuo[2];

            for (int j = 0, k = 0; j < 2; j++, k += 2) {
                ganadores[j] = ruleta(poblacion[participantes[k]], poblacion[participantes[k + 1]]);
            }
            padres[i] = ruleta(ganadores[0], ganadores[1]);
        }
        return padres;
    }

    public Individuo[] reemplazo() {
        Individuo[] hijos = new Individuo[tamanoPolacion];
        for (int i = 0; i < tamanoPolacion; i += 2) {
            int[] participantes = new int[2];
            for (int j = 0; j < 2; j++) {
                participantes[j] = (int) (Math.random() * tamanoPolacion);
            }
            Individuo[] hijosPotenciales = aplicarOperador(poblacion[participantes[0]], poblacion[participantes[1]]);

            int mejorPadre = poblacion[participantes[0]].getFitnest() > poblacion[participantes[1]].getFitnest() ? 0 : 1;
            int mejorHijo = hijosPotenciales[0].getFitnest() > hijosPotenciales[0].getFitnest() ? 0 : 1;

            hijos[i] = ruleta(poblacion[mejorPadre], hijosPotenciales[mejorHijo]);
            mejorPadre = mejorPadre == 0 ? 1 : 0;
            mejorHijo = mejorHijo == 0 ? 1 : 0;
            hijos[i + 1] = ruleta(poblacion[mejorPadre], hijosPotenciales[mejorHijo]);
        }
        return hijos;

    }

    public Individuo[] aplicarOperador(Individuo individuo1, Individuo individuo2) {
        double porcentaje = Math.random();
        OperadoresGeneticos op = new OperadoresGeneticos();
        Individuo[] hijosPotenciales;
        hijosPotenciales = porcentaje < 0.70 ? op.cruce(individuo1, individuo2) : op.mutacion(individuo1, individuo2);
        for (int i = 0; i < hijosPotenciales.length; i++) {
            hijosPotenciales[i].setFitnest(funcionFitness.calcularFitness(hijosPotenciales[i].getGenotipo()));
            hijosPotenciales[i].setCosto(funcionFitness.calularCostos(hijosPotenciales[i].getGenotipo()));
        }
        return hijosPotenciales;
    }

    public Individuo ruleta(Individuo individuo1, Individuo individuo2) {
        int fitnesTotal = individuo1.getFitnest() + individuo2.getFitnest();
        double temporal = individuo1.getFitnest() / fitnesTotal;
        if (Math.random() < temporal) {
            return individuo1;
        } else {
            return individuo2;
        }
    }

    public Individuo buscarMejor() {
        int indice;
        Individuo mejor = null;
        for (indice = 0; indice < tamanoPolacion; indice++) {
            if (poblacion[indice].getFitnest() <= funcionFitness.getVolumenMochila()) {
                mejor = poblacion[indice];
                break;
            }
        }
        for (int i = indice + 1; i < tamanoPolacion; i++) {
            if (poblacion[i].getCosto() > mejor.getCosto() && poblacion[i].getFitnest() <= funcionFitness.getVolumenMochila()) {
                mejor = poblacion[i];
            }
        }
        return mejor;
    }

    public void start() {
        this.generarPoblacion();

        this.poblacion = this.seleccionToreno();
        this.poblacion = this.reemplazo();
        Individuo a = buscarMejor();
        System.out.println(a.getCosto());
        System.out.println(a.getFitnest());
        for (int i = 0; i < a.getGenotipo().length; i++) {
            System.out.print(a.getGenotipo()[i]);
        }
        System.out.println("");
    }
}
