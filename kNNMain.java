import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

  public static void main(String... args) throws FileNotFoundException{

    // TASK 1: Use command line arguments to point DataSet.readDataSet method to
    // the desired file. Choose a given DataPoint, and print its features and label

    List<DataPoint> DataSetUsed = DataSet.readDataSet("data/iris.csv");
    System.out.println( DataSetUsed.get(0).label + "  " + DataSetUsed.get(0).x[0] + "  " +  DataSetUsed.get(0).x[1] + "  " + DataSetUsed.get(0).x[2] + "  " + DataSetUsed.get(0).x[3]);

    //TASK 2:Use the DataSet class to split the fullDataSet into Training and Held Out Test Dataset

    double trainingFraction = 0.5;
    double testFraction = 1 - trainingFraction ;

    List<DataPoint> testSet = DataSet.getTestSet(DataSetUsed, testFraction);
    List<DataPoint> trainingSet = DataSet.getTrainingSet(DataSetUsed, trainingFraction);


    // TASK 3: Use the DataSet class methods to plot the 2D data (binary and multi-class)

    //Nope!

    // TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
    // and returns the Euclidean distance between those two points (as a double)

/*
    DataPoint dp1 = trainingSet.get(val1);
    DataPoint dp2 = trainingSet.get(val2);
    distance = distanceEuclid(dp1, dp2);
    double[] x1 = val1.getX();
**/

    // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
    // and make a print of a predicted target label

    KNNClassifier Classify5 = new KNNClassifier(3);

    DataPoint[] nearestNeighbors = Classify5.getNearestNeighbors(trainingSet, trainingSet.get(0));

    String label = Classify5.predict(trainingSet, trainingSet.get(0));

    System.out.println(label);


    // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
    // point based on nearest neighbors in training set. Calculate accuracy of model.

    double modelLabelAsDouble = 0;
    int ctr = 0;
    double accuracy = 0;

    double mean = 0;
    double SD = 0;

    for(int j = 0; j<100; j++)
    {
      for(int i =0; i< trainingSet.size(); i++)
      {
        String modelLabel = Classify5.predict(trainingSet, testSet.get(i));
        String actualLabel = testSet.get(i).getLabel();

        if(modelLabel.length() == actualLabel.length())
          {
            trainingSet.get(i).setLabelAsDouble(1);
          }
        else
          {
            trainingSet.get(i).setLabelAsDouble(0);
          }
        modelLabelAsDouble = trainingSet.get(i).getLabelAsDouble();
        if(modelLabelAsDouble == 1)
          {
            ctr++ ;
          }
        System.out.println( modelLabel + "\t " + actualLabel + "    \t " + modelLabelAsDouble + "  \t ");
      }

      accuracy = (ctr*100)/testSet.size() ;
      System.out.println("Accuracy: " + accuracy);
      mean += accuracy ;
      System.out.println()

    }

    mean = (mean)/100 ;
    System.out.println("mean " + mean);


    }
}
