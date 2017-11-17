/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem.genetic;

import java.util.Random;

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
        int[] c = {825594,
            1677009,
            1676628,
            1523970,
            943972,
            97426,
            69666,
            1296457,
            1679693,
            1902996,
            1844992,
            1049289,
            1252836,
            1319836,
            953277,
            2067538,
            675367,
            853655,
            1826027,
            65731,
            901489,
            577243,
            466257,
            369261};
        int[] v = {382745,
            799601,
            909247,
            729069,
            467902,
            44328,
            34610,
            698150,
            823460,
            903959,
            853665,
            551830,
            610856,
            670702,
            488960,
            951111,
            323046,
            446298,
            931161,
            31385,
            496951,
            264724,
            224916,
            169684};
        int b = 6404180;
        int[] x = {0, 1, 1, 1, 0};
        FitnessFunction funcionF = new FitnessFunction(b, v, c);
        Population algot = new Population(funcionF);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(algot.population.get(i).getBenefit() + " " + algot.population.get(i).getFitness());
//        }        
        int f = funcionF.calculate(x);
        int f1 = funcionF.calculateBenefit(x);
        System.out.println(f);
        System.out.println(f1);
        System.out.println("---");
        Individual res = algot.bestSolution();
        System.out.println(res.getFitness());
        System.out.println(res.getBenefit());
        for (int i = 0; i < res.getGenotype().length; i++) {
            System.out.print(res.getGenotype()[i]);
        }
        System.out.println("");

    }

}
