/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttp.haea;

import ttp.data.Data;
import ttp.genetic.TTP_Individual;
import ttp.genetic.operators.crossover.Cycle_XOver;
import ttp.io.ReadFile;
import unalcol.Tagged;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.EAFactory;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.HaeaStep;
import unalcol.evolution.haea.SimpleHaeaOperatorsDescriptor;
import unalcol.evolution.haea.WriteHaeaStep;
import unalcol.optimization.OptimizationFunction;
import unalcol.search.population.PopulationSearch;
import unalcol.search.selection.Tournament;
import unalcol.search.space.Space;
import unalcol.services.AbstractMicroService;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.tracer.Tracer;
import unalcol.tracer.VectorTracer;
import unalcol.types.collection.keymap.ImmutableKeyMap;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Ricardo Tique
 */
public class HaeaTest {

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

    public static void run() {
        //Data Load
        ReadFile read = new ReadFile();
        Data data = read.readFile("D:\\Informacion\\Desktop\\TTP1\\10\\10_3_1_50.txt");
        //Space
        Space<TTP_Individual> space = MethodTest.TTP_Space(data.getNumCities(),
                data.getNumItems(), data.getKnapsackCapacity(), data.getAvProducts(), data.getProductWeights());
        //Function
        OptimizationFunction<TTP_Individual> function = MethodTest.ttp_f(data);
        //Operators
        HaeaOperators<TTP_Individual> operators = MethodTest.operators();

        // Search method
        int POPSIZE = 450;
        int MAXITERS = 250;
        EAFactory<TTP_Individual> factory = new EAFactory<TTP_Individual>();
        PopulationSearch<TTP_Individual, Double> search
                = factory.HAEA(POPSIZE, operators, new Tournament<TTP_Individual, Double>(function, 4), MAXITERS);

        search.setGoal(function);

        // Apply the search method
        // Services
        ServicePool service = MethodTest.TTP_service(function, search);
        service.register(new SimpleHaeaOperatorsDescriptor<TTP_Individual>(), HaeaOperators.class);
        service.register(new WriteDescriptors<TTP_Individual>(), HaeaOperators.class);
        haea_service(function);

        // Apply the search method
        Tagged<TTP_Individual> sol = search.solve(space);
//        print_function(function);
    }

    public static void main(String[] args) {
         run();     
    }
}
