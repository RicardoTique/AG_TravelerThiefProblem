/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenteviajero.haea;

import tsp.genetic.TSP;
import tsp.genetic.TSP_Individual;
import tsp.genetic.TSP_Space;
import tsp.operators.mutation.Inversion_Mutation;
import tsp.operators.mutation.Scramble_Mutation;
import tsp.operators.mutation.Swap_Mutation;
import tsp.operators.xover.LinearOrder_XOver;
import unalcol.Tagged;
import unalcol.clone.DefaultClone;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.haea.SimpleHaeaOperators;
import unalcol.io.DefaultWrite;
import unalcol.optimization.OptimizationFunction;
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

    public static Space<TSP_Individual> TSP_Space(int DIM) {
        return new TSP_Space(DIM);
    }

    public static ServicePool TTP_service(OptimizationFunction<TSP_Individual> function,
            Search<TSP_Individual, Double> search) {
        ServicePool service = service(function, search);
        service.register(new DoubleArrayPlainWrite(',', false), double[].class);
        service.register(new SolutionDescriptors<TSP_Individual>(function), Tagged.class);
        service.register(new SolutionWrite<TSP_Individual>(function, true), Tagged.class);
        return service;

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
    
    public static OptimizationFunction<TSP_Individual> tsp_f(double [][] coordinates) {
        OptimizationFunction<TSP_Individual> function = new TSP(coordinates);
        function.minimize(true);
        return function;
    }
    
    public static SimpleHaeaOperators<TSP_Individual> operators(){
        LinearOrder_XOver lox = new LinearOrder_XOver();
        Swap_Mutation swap_mutation = new Swap_Mutation();
        Inversion_Mutation inversion_mutation = new Inversion_Mutation();
        Scramble_Mutation scramble_mutation = new Scramble_Mutation();
        return new SimpleHaeaOperators<TSP_Individual>(lox,swap_mutation,inversion_mutation,scramble_mutation);
    }
}
