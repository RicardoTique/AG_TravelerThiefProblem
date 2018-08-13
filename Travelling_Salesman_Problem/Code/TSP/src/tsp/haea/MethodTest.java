package tsp.haea;

import tsp.genetic.TSP;
import tsp.genetic.TSP_Individual;
import tsp.genetic.TSP_Space;
import tsp.operators.mutation.Inversion_Mutation;
import tsp.operators.mutation.Scramble_Mutation;
import tsp.operators.mutation.Swap_Mutation;
import tsp.operators.xover.LinearOrder_XOver;
import unalcol.types.object.tagged.Tagged;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.haea.SimpleHaeaOperators;
import unalcol.optimization.OptimizationFunction;
import unalcol.search.Search;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.solution.SolutionDescriptors;
import unalcol.search.solution.SolutionWrite;
import unalcol.search.space.Space;
import unalcol.services.Service;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.tracer.VectorTracer;
import unalcol.types.real.array.DoubleArrayPlainWrite;

/**
 *
 * @author Ricardo Tique
 */
public class MethodTest {

    public static void service(OptimizationFunction<?> function, Search<?, Double> search) {
        // Tracking the goal evaluations
		Tracer t = new ConsoleTracer();
		t.start();
		Service.register(t, search);
		t = new VectorTracer();
		t.start();
		Service.register(t, function);
    }

    public static Space<TSP_Individual> TSP_Space(int DIM) {
        return new TSP_Space(DIM);
    }

    public static void TSP_service(OptimizationFunction<TSP_Individual> function,
            Search<TSP_Individual, Double> search) {
    	service(function,search);
    	Service.register(new DoubleArrayPlainWrite(',',false), double[].class);
        Service.register(new SolutionDescriptors<TSP_Individual>(function), Tagged.class);
        Service.register(new SolutionWrite<TSP_Individual>(function,true), Tagged.class);    	       
    }

    
    public static void population_service(OptimizationFunction<?> function) {
    	@SuppressWarnings("rawtypes")
    	PopulationDescriptors pd= new PopulationDescriptors();
		//pd.setGoal(function);
		Service.register(pd, Tagged[].class);
		Service.register(new WriteDescriptors(), Tagged[].class);
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
