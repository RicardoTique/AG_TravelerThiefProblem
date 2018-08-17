package ttp.haea;

import ttp.data.Data;
import ttp.genetic.TTP;
import ttp.genetic.TTP_Individual;
import ttp.genetic.TTP_Space;
import ttp.genetic.operators.crossover.Cycle_XOver;
import ttp.genetic.operators.crossover.LinearOrder_XOver;
import ttp.genetic.operators.crossover.Order_XOver;
import ttp.genetic.operators.mutation.Inversion_Mutation;
import ttp.genetic.operators.mutation.Scramble_Mutation;
import ttp.genetic.operators.mutation.Swap_Mutation;
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

    public static Space<TTP_Individual> TTP_Space(int n, int p, double kc, int[][] apc, double[] pw) {
        return new TTP_Space(n, p, kc, apc, pw);
    }

    public static void TTP_service(OptimizationFunction<TTP_Individual> function,
            Search<TTP_Individual, Double> search) {
        service(function, search);
        Service.register(new DoubleArrayPlainWrite(',', false), double[].class);
        Service.register(new SolutionDescriptors<TTP_Individual>(function), Tagged.class);
        Service.register(new SolutionWrite<TTP_Individual>(function, true), Tagged.class);
    }
    
    public static void population_service(OptimizationFunction<?> function) {
    	@SuppressWarnings("rawtypes")
    	PopulationDescriptors pd= new PopulationDescriptors();
    	
		Service.register(pd, Tagged[].class);
		Service.register(new WriteDescriptors(), Tagged[].class);
    }

    public static OptimizationFunction<TTP_Individual> ttp_f(Data data) {
        OptimizationFunction<TTP_Individual> function = new TTP(data);
        function.minimize(false);
        return function;
    }
    public static SimpleHaeaOperators<TTP_Individual> operators() {
        LinearOrder_XOver lox = new LinearOrder_XOver();
        Order_XOver ox = new Order_XOver();
        Cycle_XOver cx = new Cycle_XOver();
        Swap_Mutation s_m = new Swap_Mutation();
        Inversion_Mutation i_m = new Inversion_Mutation();
        Scramble_Mutation sc_m = new Scramble_Mutation();
        return new SimpleHaeaOperators<TTP_Individual>(cx, ox, sc_m, s_m, i_m, lox);
    }
}
