import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

import java.util.concurrent.ThreadLocalRandom;






//NN Template
//Created by Chunfeng Li
//Released 07/30/2020
//This is my go at an evolutionary network





public class Evolution {
	ArrayList<NeuralNetwork> breededPopulation=new ArrayList<NeuralNetwork>();
	ArrayList<NeuralNetwork> breedingPopulation=new ArrayList<NeuralNetwork>();
	//input layer nodes ,number of hidden layers, nodes in hidden layers,output layer nodes
	
	
	 public static void main(String[] args){
		
		 //INPUT for network as an ARRAY
//		double[] input = new double[inputLayersNode];
		
		//Output of network as an ARRAY
//		double[] output=n.Evaluate(input);
//			 
//
////		 just comment this line out when using the test set!
//		 writeToFile(storage, n);
		 
		 
	 }
	 
	 ArrayList<NeuralNetwork> Evolve(ArrayList<NeuralNetwork> nnlist, int surv, int popsize, double mutation) {
		 nnlist=sort(nnlist);
		 for (int i=0;i<nnlist.size();i++) {
			 //System.out.println(nnlist.get(i).getfitness());
		 }
		 nnlist=kill(nnlist, surv);
		 for (int i=0;i<nnlist.size();i++) {
			 //System.out.println(nnlist.get(i).getfitness());
		 }
		 nnlist=repopulate(nnlist, popsize, mutation);
		 return nnlist;
	 }
	 
	 ArrayList<NeuralNetwork> sort(ArrayList<NeuralNetwork> nnlist) {
		//sort by fitness high to low
		 int n = nnlist.size(); 
	        for (int i = 1; i < n; ++i) { 
	            NeuralNetwork key = nnlist.get(i);
	            int j = i - 1; 
	  
	            /* Move elements of arr[0..i-1], that are 
	               greater than key, to one position ahead 
	               of their current position */
	            while (j >= 0 && nnlist.get(j).getfitness() < key.getfitness()) { 
	                nnlist.set(j + 1,nnlist.get(j)) ; 
	                j = j - 1; 
	            } 
	            nnlist.set(j + 1,key); 
	        }
	        return nnlist;
	 }
	 
	 ArrayList<NeuralNetwork> kill (ArrayList<NeuralNetwork> nnlist, int live) {
		 return new ArrayList<NeuralNetwork>(nnlist.subList(0, live));
	 }
	 
	 ArrayList<NeuralNetwork> repopulate (ArrayList<NeuralNetwork> nnlist, int population, double mutrate) {
		 Random rand = new Random();
		 //Requires both neural networks to have the same size and amount of layers/connections
		 while ((nnlist.size()+breededPopulation.size()+breedingPopulation.size())<population) {
	
			 //if all pairs are used up
			 if (nnlist.size()<=1) {
				 nnlist.addAll(breedingPopulation);
				 breedingPopulation.clear();
			
			 }
			 NeuralNetwork firstnn = nnlist.get(0);
			 nnlist.remove(firstnn);
			 breedingPopulation.add(firstnn);
			 NeuralNetwork secondnn = nnlist.get(rand.nextInt(nnlist.size()));
			 nnlist.remove(secondnn);
			 breedingPopulation.add(secondnn);
			 NeuralNetwork child= (NeuralNetwork)deepCopy(firstnn); //starts off by cloning the first parent to reduce uneeded constructing
			 ArrayList<Layer> childLayers= child.getLayer(); //gets node layers of child
			 ArrayList<Layer> parent2Layers= secondnn.getLayer(); //gets node layers of the second parent
			 
			 //Loops through each layer of the child's neural network excluding final layer 
			 //starts at the second last layer
			 for (int i=childLayers.size()-2;i>0;i-- ) {
				 //Obtains the nodes in that layer and previous
				 ArrayList<Node> childnodes =childLayers.get(i).getNodes();
				 ArrayList<Node> parent2nodes =parent2Layers.get(i).getNodes();
				 //Loops through each node, we need to change each NODE'S bias and each CONNECTION'S weight
				 for (int t=0;t<childnodes.size();t++ ) {
					 Node currentChildNode=childnodes.get(t);
					 Node currentParent2Node=parent2nodes.get(t);
					 //0 = keep bias (parent 1's bias), 1 = parent2 bias
					 if (rand.nextInt(2)==0) {
						 currentChildNode.changeBias(rand.nextGaussian() *0.03);
					 }
					 else {
						 currentChildNode.setBias(currentParent2Node.getBias()); 
						 currentChildNode.changeBias(rand.nextGaussian() *0.03);
					 }
					 //mutation chance (error during copy of DNA)
					 if (rand.nextDouble()*100<mutrate) {
						 currentChildNode.setBias(rand.nextGaussian() *0.5);
					 }
					 //time to change the weights in the connections of the Nodes!
					 double[] currentParent2Weights= currentParent2Node.getOutWeights();
					 double[] neWeights=currentChildNode.getOutWeights();
					 //loops through each node's outgoing connection weight
					 for (int j=0;j<neWeights.length;j++) {
						 
						 //0 = keep weight (parent 1's weight), 1 = parent2 weight
						 if (rand.nextInt(2)==1) {
							 neWeights[j]= currentParent2Weights[j] +(rand.nextGaussian() *0.03);
						 }
						 
						 //mutation chance (error during copy of DNA)
						 if (rand.nextDouble()*100<mutrate) {
							 neWeights[j]= rand.nextGaussian() *0.5;
						 }
						 
					 }
					 currentChildNode.setOutWeights(neWeights);
				 }
			 }
			 breededPopulation.add(child);
		 }
		 ArrayList<NeuralNetwork> all = new ArrayList<NeuralNetwork>();
		 all.addAll(nnlist);
		 all.addAll(breededPopulation);
		 all.addAll(breedingPopulation);
		 //System.out.println(all.size());
		 breededPopulation.clear();
		 breedingPopulation.clear();
		 return all;
	 }
	 
	 /**
	  * Makes a deep copy of any Java object that is passed.
	  */
	  private static Object deepCopy(Object object) {
	    try {
	      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	      ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
	      outputStrm.writeObject(object);
	      ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	      ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
	      return objInputStream.readObject();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	  }
	 
	 static double[][] Multiply(double[][] a,double[][] b) {
		  double[][] data=new double[a.length][a[0].length];
		      // hadamard product
		      for ( int i = 0; i < a.length; i++) {
		        for ( int j = 0; j < a[0].length; j++) {
		        
		          data[i][j] = a[i][j]*b[i][j];
		          
		        }
		      }
		      return data;
		    
	 
	 }
	 static double[][] Multiply(double[][] a,double b) {
		  double[][] data=new double[a.length][a[0].length];
		  
		      for ( int i = 0; i < a[0].length; i++) {
		        for ( int j = 0; j < a.length; j++) {
		        
		          data[j][i] = a[j][i]*b;
		          
		        }
		      }
		      return data;
		    
	 
	 }
	 static double[][] dSigmoid(double[] input) {
		//derivative sigmoid output 
		 double dOutput[][]= new double[input.length][1];
		 for (int j=0;j<input.length;j++) {
			 dOutput[j][0]=input[j]*(1-input[j]);
		 }
		 return dOutput;
	 }
	 static void print2D(double[][] matrix) {
		 for (int i = 0; i < matrix.length; i++) {
			    for (int j = 0; j < matrix[i].length; j++) {
			        System.out.print(matrix[i][j] + " ");
			    }
			    System.out.println();
			}
	 }
	 static double[] labelToArray(int l) {
		 double[] array= {0,0,0,0,0,0,0,0,0,0};
		 array[l]=1; 
		 return array; 
	 }
	 public static void writeToFile(File path, NeuralNetwork data)
	 {
	     try(ObjectOutputStream write= new ObjectOutputStream (new FileOutputStream(path)))
	     {
	         write.writeObject(data);
	     }
	     catch(NotSerializableException nse)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     catch(IOException eio)
	     {
	         //do something
	    	 
	     }
	 }


	 public static NeuralNetwork readFromFile(File path)
	 {
	     NeuralNetwork data = null;

	     try(ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path)))
	     {
	         data = (NeuralNetwork) inFile.readObject();
	         return data;
	     }
	     catch(ClassNotFoundException cnfe)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     catch(FileNotFoundException fnfe)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     catch(IOException e)
	     {
	         //do something
	    	 System.out.print("help");
	     }
	     return data;
	 }
	 static int getAnswer(double[] output) {
		 int answer=-1;
		 double highest=0;
		 for (int i=0;i<output.length;i++) {
			 if (output[i]>highest) {
				 
				 answer=i;
				 highest=output[i];
			 }
		 }
		 return answer;
	 }
	 
}
