/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem.haea;

import knapsackProblem.data.Data;
import knapsackProblem.io.ReadFile;
import unalcol.Tagged;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.EAFactory;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.HaeaStep;
import unalcol.evolution.haea.SimpleHaeaOperators;
import unalcol.evolution.haea.SimpleHaeaOperatorsDescriptor;
import unalcol.evolution.haea.WriteHaeaStep;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.BitMutation;
import unalcol.optimization.binary.Transposition;
import unalcol.optimization.binary.XOver;
//import unalcol.optimization.binary.testbed.Deceptive;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.mutation.Mutation;
import unalcol.optimization.real.xover.LinearXOver;
import unalcol.optimization.real.xover.RealArityTwo;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.multilevel.MultiLevelSearch;
import unalcol.search.population.PopulationSearch;
import unalcol.search.selection.Tournament;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.AbstractMicroService;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.tracer.Tracer;
import unalcol.tracer.VectorTracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.collection.keymap.ImmutableKeyMap;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Ricardo Tique
 */
public class Test {

    @SuppressWarnings("rawtypes")
    public static void haea_service(OptimizationFunction<?> function) {
        ServicePool service = MethodTest.population_service(function);
        service.register(new WriteHaeaStep(), HaeaStep.class);
    }

    public static void print_function(OptimizationFunction<?> function) {
        try {
            ServicePool service = (ServicePool) Service.get();
            AbstractMicroService<?> s = service.get(Tracer.get, function);
            System.out.println(s);
            @SuppressWarnings("unchecked")
            ImmutableKeyMap<AbstractMicroService<?>, Object> objs = (ImmutableKeyMap<AbstractMicroService<?>, Object>) s.run();
            System.out.println(objs);
            // Since we have just one tracer object associated to the function we pick the first set of results
            // provided by the tracer	
            s = null;
            for (AbstractMicroService<?> k : objs.keys()) {
                if (k instanceof VectorTracer) {
                    s = k;
                }
            }
            @SuppressWarnings("unchecked")
            Vector<Object[]> v = (Vector<Object[]>) objs.get(s);
            Object[] f = (Object[]) v.get(0);
            // The fitness value is located as the second element in the array (the first one is the object)
            double bf = (Double) (f[1]);
            for (int i = 0; i < v.size(); i++) {
                f = (Object[]) v.get(i);
                double cf = (Double) (f[1]);
                if (function.order().lt(bf, cf)) {
                    bf = cf;
                }
                System.out.println(i + " " + bf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void binary() {
        ReadFile file = new ReadFile();
//      Data data = file.readFile("../DataSets/low-dimensional/f1_l-d_kp_10_269");
        Data data = file.readFile("../DataSets/large_scale/knapPI_1_100_1000_1");
//        BitArray genome = new BitArray("0101011001");
//        System.out.println(genome.size());
//        for (int i = 0; i < 10; i++) {
//            System.out.println(genome.get(i));
//        }        
        // Search Space definition
        Space<BitArray> space = MethodTest.binary_space(data.getNumberProducts());
        // Optimization Function
        OptimizationFunction<BitArray> function = MethodTest.binary_f(data);
//
//        // Variation definition
        BitMutation mutation = MethodTest.binary_mutation();
        XOver xover = new XOver();
        Transposition transposition = new Transposition();
        HaeaOperators<BitArray> operators = new SimpleHaeaOperators<BitArray>(mutation, transposition, xover);

        // Search method
        int POPSIZE = 150;
        int MAXITERS =150;
        EAFactory<BitArray> factory = new EAFactory<BitArray>();

        PopulationSearch<BitArray, Double> search = factory.HAEA(POPSIZE, operators, new Tournament<BitArray, Double>(function, 4), MAXITERS);

        search.setGoal(function);

        // Apply the search method
        // Services
        ServicePool service = MethodTest.binary_service(function, search);
        service.register(new SimpleHaeaOperatorsDescriptor<BitArray>(), HaeaOperators.class);
        service.register(new WriteDescriptors<BitArray>(), HaeaOperators.class);
        haea_service(function);

        // Apply the search method
        Tagged<BitArray> sol = search.solve(space);
        print_function(function);
    }

    public static void main(String[] args) {
        binary();

    }

}
