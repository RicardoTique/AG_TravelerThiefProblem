/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem.haea;

import knapsackProblem.data.Data;
import knapsackProblem.genetic.Knapsack;
import unalcol.Tagged;
import unalcol.clone.DefaultClone;
import unalcol.descriptors.WriteDescriptors;
import unalcol.io.DefaultWrite;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.BitMutation;
import unalcol.random.raw.JavaGenerator;
import unalcol.search.Search;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.solution.SolutionDescriptors;
import unalcol.search.solution.SolutionWrite;
import unalcol.search.space.Space;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.tracer.VectorTracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;

/**
 *
 * @author Ricardo Tique
 */
public class MethodTest {

    public static ServicePool service(OptimizationFunction<?> function, Search<?, Double> search) {
        // Tracking the goal evaluations
        ServicePool service = new ServicePool();
        service.register(new JavaGenerator(), Object.class);
        service.register(new DefaultClone(), Object.class);
        service.register(new DefaultWrite(), Object.class);
        Tracer<Object> t = new ConsoleTracer<Object>();
        t.start();
        service.register(t, search);
        t = new VectorTracer<Object>();
        t.start();
        service.register(t, function);
        Service.set(service);
        return service;
    }    

    public static Space<BitArray> binary_space(int DIM) {
        // Search Space definition       
        return new BinarySpace(DIM);
    }

    public static OptimizationFunction<BitArray> binary_f(Data data) {
        // Optimization Function
        OptimizationFunction<BitArray> function = new Knapsack(data);        
        function.minimize(false); // Set to true if minimizing
        return function;
    }

    public static ServicePool binary_service(OptimizationFunction<BitArray> function,
            Search<BitArray, Double> search) {
        ServicePool service = service(function, search);
        service.register(new DoubleArrayPlainWrite(',', false), double[].class);
        service.register(new SolutionDescriptors<BitArray>(function), Tagged.class);
        service.register(new SolutionWrite<BitArray>(function, true), Tagged.class);
        return service;
    }

    public static BitMutation binary_mutation() {
        return new BitMutation();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ServicePool population_service(OptimizationFunction<?> function) {
        ServicePool service = (ServicePool) Service.get();
        PopulationDescriptors pd = new PopulationDescriptors();
        pd.setGoal(function);
        service.register(pd, Tagged[].class);
        service.register(new WriteDescriptors<Tagged[]>(), Tagged[].class);
        return service;
    }
}
