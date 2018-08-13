/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.haea;

import tsp.data.Data;
import tsp.genetic.TSP_Individual;
import tsp.io.ReadFile;
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
import unalcol.services.ProvidersSet;
import unalcol.services.Service;
import unalcol.tracer.Tracer;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Ricardo Tique
 */
public class HaeaTest {

    @SuppressWarnings("rawtypes")
    public static void haea_service(OptimizationFunction<?> function) {
    	Service.register( new WriteHaeaStep(), HaeaStep.class);
        Service.register( new SimpleHaeaOperatorsDescriptor(), HaeaOperators.class);
        Service.register( new WriteDescriptors(), HaeaOperators.class);
		MethodTest.population_service(function);
    }

    public static void print_function(OptimizationFunction<?> function) {
    	try{
			ProvidersSet tracers = Service.providers(Tracer.class, function);
			Tracer s = (Tracer)tracers.get("VectorTracer");
			@SuppressWarnings("unchecked")
			Vector<Object[]> v = (Vector<Object[]>)s.get();
			Object[] f = (Object[])v.get(0);
			// The fitness value is located as the second element in the array (the first one is the object)
			double bf = (Double)(f[1]);			
			/**	
			for( int i=0; i<v.size(); i++ ){
				f = (Object[])v.get(i);
				double cf = (Double)(f[1]);

				if( function.order().compare(bf, cf) < 0 ) bf = cf;
				//System.out.println(i+" "+bf);
			}
			System.out.println("Best fitnests: "+ bf);
			*/
		}catch(Exception e){ e.printStackTrace(); }
    }

    public static void main(String[] args) {
        // Carga de datos
        ReadFile read = new ReadFile();
        Data data = read.readFile("../../Dataset/a280.tsp");
        // Defnicion del espacio de solucion
        Space<TSP_Individual> space = MethodTest.TSP_Space(data.getSize());
        // Funcion de optimizacion
        OptimizationFunction<TSP_Individual> function = MethodTest.tsp_f(data.getCoordinates());
        // Definicion de operadores
        HaeaOperators<TSP_Individual> operators = MethodTest.operators();
        // Definicion de poblacion e iteraciones
        int POPSIZE = 500;
        int MAXITERS = 100;

        EAFactory<TSP_Individual> factory = new EAFactory<TSP_Individual>();

        PopulationSearch<TSP_Individual, Double> search
                = factory.HAEA(POPSIZE, operators, new Tournament<TSP_Individual, Double>(function, 4), MAXITERS);
        
        search.setGoal(function);
        // Apply the search method
        // Services
        MethodTest.TSP_service(function, search);
        haea_service(function);

        // Apply the search method
        search.solve(space);
//        print_function(function);
    }

}
