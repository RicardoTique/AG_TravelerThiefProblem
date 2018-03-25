/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem.genetic;

import java.util.Random;
import knapsackProblem.io.ReadFile;

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
        ReadFile file = new ReadFile();
        FitnessFunction funcionF = file.readFile("../DataSets/large_scale/knapPI_1_100_1000_1");

        for (int i = 0; i < 30; i++) {
            Population genetic = new Population(funcionF);
            System.out.println(genetic.bestSolution().getFitness() +" "+genetic.wortsSolution() );
            
        }
//        GeneticOperators g = new GeneticOperators();
//        int[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int[] b = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//        Individual uno = new Individual(a);
//        Individual dos = new Individual(b);
//        Individual[] crossover = g.crossover(uno, dos);
//        Individual[] mutation = g.mutation(uno, dos);
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(crossover[i].getGenotype()[j]);
//            }
//            System.out.println("");
//        }
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(mutation[i].getGenotype()[j]);
//            }
//            System.out.println("");
//        }


        /* String[] dataSets = {
            "low-dimensional/f1_l-d_kp_10_269",
            "low-dimensional/f2_l-d_kp_20_878",
            "low-dimensional/f3_l-d_kp_4_20",
            "low-dimensional/f4_l-d_kp_4_11",
           // "low-dimensional/f5_l-d_kp_15_375",
            "low-dimensional/f6_l-d_kp_10_60",
            "low-dimensional/f7_l-d_kp_7_50",
            "low-dimensional/f8_l-d_kp_23_10000",
            "low-dimensional/f9_l-d_kp_5_80",
            "low-dimensional/f10_l-d_kp_20_879",
            "large_scale/knapPI_1_100_1000_1",
            "large_scale/knapPI_1_200_1000_1",
            "large_scale/knapPI_1_500_1000_1",
            "large_scale/knapPI_1_1000_1000_1",
            "large_scale/knapPI_1_2000_1000_1",
            "large_scale/knapPI_1_5000_1000_1",
            "large_scale/knapPI_1_10000_1000_1",
            "large_scale/knapPI_2_100_1000_1",
            "large_scale/knapPI_2_200_1000_1",
            "large_scale/knapPI_2_500_1000_1",
            "large_scale/knapPI_2_1000_1000_1",
            "large_scale/knapPI_2_2000_1000_1",
            "large_scale/knapPI_2_5000_1000_1",
            "large_scale/knapPI_2_10000_1000_1",
            "large_scale/knapPI_3_100_1000_1",
            "large_scale/knapPI_3_200_1000_1",
            "large_scale/knapPI_3_500_1000_1",
            "large_scale/knapPI_3_1000_1000_1",
            "large_scale/knapPI_3_2000_1000_1",
            "large_scale/knapPI_3_5000_1000_1",
            "large_scale/knapPI_3_10000_1000_1"};
        for (int i = 0;
                i < dataSets.length;
                i++) {
            System.out.println("DataSet: " + dataSets[i]);
            FitnessFunction funcionF = file.readFile("../DataSets/" + dataSets[i]);
            double medBenefit = 0;
            double medFitness = 0;
            int[] benefit = new int[30];
            int[] fitness = new int[30];
            System.out.println("Volumen: " + funcionF.getKnapsackCapacity());
            for (int j = 0; j < 30; j++) {
                Population algot = new Population(funcionF);
                Individual res = algot.bestSolution();
//                benefit[j] = res.getBenefit();
                fitness[j] = res.getFitness();
  //              medBenefit = medBenefit + res.getBenefit();
                medFitness = medFitness + res.getFitness();               
            }
            int benefitMin = benefit[0];
            int fitnessMin = fitness[0];
            int benefitMax = benefit[0];
            int fitnessMax = fitness[0];
            for (int j = 1; j < 30; j++) {
                if (benefit[j] > benefitMax) {
                    benefitMax = benefit[j];
                }
                if (benefit[j] < benefitMin) {
                    benefitMin = benefit[j];
                }
                if (fitness[j] > fitnessMax) {
                    fitnessMax = fitness[j];
                }
                if (fitness[j] < fitnessMin) {
                    fitnessMin = fitness[j];
                }
            }
            burbuja(fitness);
            burbuja(benefit);
            for (int j = 0; j < fitness.length; j++) {
                System.out.println(fitness[j]+" "+benefit[i]);
            }
            System.out.println("Fitness Max: " + fitnessMax + " Fitness Min: " + fitnessMin + " Med Fitness: " + medFitness / 30);
            System.out.println("Benefit Max: " + benefitMax + " Benefit Min: " + benefitMin + " Med Benefit: " + medBenefit / 30);
            System.out.println("-----");
        }

    }
    public static void burbuja(int[]matrix){
        int temp;
        for(int i=1;i < matrix.length;i++){
            for (int j=0 ; j < matrix.length- 1; j++){
                if (matrix[j] > matrix[j+1]){
                    temp = matrix[j];
                    matrix[j] = matrix[j+1];
                    matrix[j+1] = temp;
                }
            }
        }*/
    }

}
