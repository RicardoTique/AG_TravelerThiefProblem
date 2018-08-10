/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteviajero.haea;

import tsp.data.Data;
import tsp.genetic.TSP_Individual;
import tsp.io.ReadFile;
import tsp.operators.xover.LinearOrder_XOver;
import unalcol.Tagged;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.EAFactory;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.HaeaStep;
import unalcol.evolution.haea.SimpleHaeaOperators;
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

    public static void main(String[] args) {
        // TODO code application logic here
        ReadFile read = new ReadFile();
        Data data = read.readFile("../Data/a280.tsp");
        Space<TSP_Individual> space = MethodTest.TSP_Space(data.getSize());
        OptimizationFunction<TSP_Individual> function = MethodTest.tsp_f(data.getCoordinates());
        
        HaeaOperators<TSP_Individual> operators = MethodTest.operators();
        int POPSIZE = 500;
        int MAXITERS = 1000;
        EAFactory<TSP_Individual> factory = new EAFactory<TSP_Individual>();
        PopulationSearch<TSP_Individual, Double> search
                = factory.HAEA(POPSIZE, operators, new Tournament<TSP_Individual, Double>(function, 4), MAXITERS);
        search.setGoal(function);
        // Apply the search method
        // Services
        ServicePool service = MethodTest.TTP_service(function, search);
        service.register(new SimpleHaeaOperatorsDescriptor<TSP_Individual>(), HaeaOperators.class);
        service.register(new WriteDescriptors<TSP_Individual>(), HaeaOperators.class);
        haea_service(function);

        // Apply the search method
        Tagged<TSP_Individual> sol = search.solve(space);
       // print_function(function);
    }

}
