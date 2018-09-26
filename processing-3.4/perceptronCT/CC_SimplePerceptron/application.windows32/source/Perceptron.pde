//Activation function
//Shaminder Galla 
//Machine Learning - Neural Networks - Perceptron

// Simple Perceptron Example
// See: http://en.wikipedia.org/wiki/Perceptron

int sign(float n) {
  if (n >= 0) {
    return 1;
  } else {
    return -1;
  }
}

class Perceptron {
 float[] weights;
 float lr = 0.01;
 
 //Constructor
 Perceptron(int n) {
   weights = new float[n];
   //Initialize the weights randomly
   for (int i = 0; i < weights.length; i++) {
     weights[i] = random(-1,1);
   }
 }
 
 int guess(float[] inputs) {
   float sum = 0;
   for (int i = 0; i < weights.length; i++) {
     sum += inputs[i]*weights[i];
   }
   int output = sign(sum);
   return output;
 }
 
 void train(float[] inputs, int target) {
   int guess = guess(inputs);
   int error = target - guess;
   //Tune all the weights 
   for (int i = 0; i < weights.length; i++) {
     weights[i] += error * inputs[i] * lr;
   }
   
 }
 
 float guessY(float x) {
   //float m = weights[1] / weights[0];
   //float b = weights[2];
   //return m * x * b;
   float w0 = weights[0];
   float w1 = weights[1];
   float w2 = weights[2];
   
   return -(w2/w1) - (w0/w1) * x;
 }
 
}
