
import java.util.ArrayList;
import java.util.Arrays;
//Test repurposed from MagnusSnorri's github repo
class Test {
	 private static int generationSize = 200;
   

	int nOfInputs=5; 
	int nOfHiddenLayers=2;
	int nOfHiddenNodes= 10;
	int nOfOutputs=6;
	static int deathcounter=0;
    int numberOfGenerations = 200;
    int Pop=0;
	int Evo=0;
	
	int avgcounter=0;
	float psum=0;
	int surviving=50; //recommend top 25%
	double mutationRate =0.02;
    public static void main(String[] args) {
    	Evolution ev;
       
        ev=new Evolution();
    	 ArrayList<NeuralNetwork> population = new ArrayList<NeuralNetwork>();
       
        int numberOfGenerations = 20;
        int numberOfActions = 10;
        
        for (int i=0;i<generationSize;i++) {
			population.add(new NeuralNetwork(5,  1, 8, 3,2));
	    	}
       
        for(int i = 0; i<numberOfGenerations;i++){
            for(int j = 0; j<generationSize;j++) {
            	NeuralNetwork n = population.get(j);
                double fitnessSum = 0;
                for (int k = 0; k < numberOfActions; k++) {
                    fitnessSum += fitnessFunction(n.Evaluate(generateInputs()));
                }
                double averageFitness = 10-fitnessSum/numberOfActions;
                population.get(j).setfitness((float) averageFitness);
                System.out.println(averageFitness);
            }
            population = ev.Evolve(population,20,200,0.001);
        }
        System.out.println("Done");
        
    }

    public static double fitnessFunction(double[] outputs){
        double[] target = new double[]{5, 2,0};
       
        double distance = 0;
        for(int i = 0; i<outputs.length;i++) {
            distance += Math.abs(outputs[i] - target[i]);
        }
        return distance;
    }
    public static double[] generateInputs(){
        double[] inputs = new double[5];
        inputs[0] = Math.random()*5;
        inputs[1] = Math.random()*2;
        inputs[2] = Math.random()*4 ;
        inputs[3] = Math.random() ;
        inputs[4] = Math.random()*3;
        return inputs;
    }

}