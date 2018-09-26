import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class CC_SimplePerceptron extends PApplet {

//Shaminder Galla 
//Machine Learning - Neural Networks - Perceptron

// Simple Perceptron Example
// See: http://en.wikipedia.org/wiki/Perceptron

Perceptron brain;

Point[] points = new Point[100];

int trainingIndex = 0;

public void setup() {
  
  brain = new Perceptron(3);
  
  for (int i = 0; i < points.length; i++) {
    points[i] = new Point();
  }  
  
}

public void draw() {
  background(255);
  stroke(0);
  //line(0, height, width, 0);
  Point p1 = new Point(-1, f(-1));
  Point p2 = new Point(1, f(1));
  line(p1.pixelX(), p1.pixelY(), p2.pixelX(), p2.pixelY());
  
  Point p3 = new Point(-1, brain.guessY(-1));
  Point p4 = new Point(1, brain.guessY(1));
  line(p3.pixelX(), p3.pixelY(), p4.pixelX(), p4.pixelY());
  
  for (Point pt : points) {
    pt.show();
  }
  
  for (Point pt : points) {
    float[] inputs = {pt.x, pt.y, pt.bias};
    int target = pt.label;
    int guess = brain.guess(inputs);
    if (guess == target) {
      fill(0, 255, 0);
    } else {
      fill(255, 0, 0);
    }
      noStroke();
      ellipse(pt.pixelX(), pt.pixelY(), 16 , 16);
  }
  
  Point training = points[trainingIndex];
  float[] inputs = {training.x, training.y, training.bias};
  int target = training.label;
  brain.train(inputs, target);
  trainingIndex++;
  if(trainingIndex == points.length) {
    trainingIndex = 0;
  }
  
}

//void mousePressed() {
//  for (Point pt : points) {
//  }
//}
//Activation function
//Shaminder Galla 
//Machine Learning - Neural Networks - Perceptron

// Simple Perceptron Example
// See: http://en.wikipedia.org/wiki/Perceptron

public int sign(float n) {
  if (n >= 0) {
    return 1;
  } else {
    return -1;
  }
}

class Perceptron {
 float[] weights;
 float lr = 0.01f;
 
 //Constructor
 Perceptron(int n) {
   weights = new float[n];
   //Initialize the weights randomly
   for (int i = 0; i < weights.length; i++) {
     weights[i] = random(-1,1);
   }
 }
 
 public int guess(float[] inputs) {
   float sum = 0;
   for (int i = 0; i < weights.length; i++) {
     sum += inputs[i]*weights[i];
   }
   int output = sign(sum);
   return output;
 }
 
 public void train(float[] inputs, int target) {
   int guess = guess(inputs);
   int error = target - guess;
   //Tune all the weights 
   for (int i = 0; i < weights.length; i++) {
     weights[i] += error * inputs[i] * lr;
   }
   
 }
 
 public float guessY(float x) {
   //float m = weights[1] / weights[0];
   //float b = weights[2];
   //return m * x * b;
   float w0 = weights[0];
   float w1 = weights[1];
   float w2 = weights[2];
   
   return -(w2/w1) - (w0/w1) * x;
 }
 
}
//Shaminder Galla 
//Machine Learning - Neural Networks - Perceptron

// Simple Perceptron Example
// See: http://en.wikipedia.org/wiki/Perceptron

//formula for the line - this will make machine re-learn -1 and 1 
public float f(float x) {
  //y = mx + b
  return 0.89f * x - 0.1f;
}

class Point {
  float x;
  float y;
  float bias = 1 ;
  int label;
  
  Point(float x_, float y_) {
    x = x_;
    y = y_;
  }
  
  Point() {
  x = random(-1, 1);
  y = random(-1, 1);
  
  float lineY = f(x);
  
  if (y > lineY) {
    label = 1;
  } else {
    label = -1;
  }
}

  public float pixelX() {
    return map(x, -1, 1, 0, width);
  }
  
  public float pixelY() {
    return map(y, -1, 1, height, 0);
  }
  
  public void show(){
    stroke(0);
    if (label == 1) {
      fill(255);
    } else {
      fill(0);
    }
    float px = pixelX();
    float py = pixelY();
    ellipse(px, py, 32, 32);
  }
}
  public void settings() {  size(800,800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "CC_SimplePerceptron" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
