import java.util.*;
import java.io.*;

public class TitanicResearch{
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File("titanic3.csv");
    allData(f);

    System.out.println(mostExpensiveTicketSurvival());
    System.out.println(findHighestPrice());
    System.out.println(genderSurvival());

  }

  public static ArrayList<ArrayList<String>> allData(File pathname) throws FileNotFoundException{
    //method that takes all information from the titanic3.csv and puts data into 2D ArrayList
    //embedded while loop that will iterate through each token and add them to a String[][]
    //the String[] in the String[][] will be one for each person's data (one line in csv)

    Scanner input = new Scanner(pathname);

    int counter = 0;
    //(while hasNextLine, while hasNext, arraylist.add(pathname.next)

    //take and add first line of csv to the String[][] (first line is key)
    String takeLine = input.nextLine();
    String [] tempAdd = takeLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    ArrayList<String> addFirstLine = new ArrayList<>(Arrays.asList(tempAdd));
    ArrayList<ArrayList<String>> takeAllData = new ArrayList<>();
    takeAllData.add(addFirstLine);

    //while loop that takes each line and adds it to the String[][] with all data
    while(input.hasNextLine()){
      takeLine = input.nextLine();
      tempAdd = takeLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      ArrayList<String> tempToArrList = new ArrayList<>(Arrays.asList(tempAdd));
      takeAllData.add(tempToArrList);

    }

    //returns the String[][] with all the data - to be used in other methods
    return takeAllData;

  }


  public static int findIndex(String find) throws FileNotFoundException{
    //method that finds index of certain key (in case order of key list changes, will always have correct spot)
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    //changes the find String to include quotes (because csv Strings for data have quotes)
    String tempFind = find;
    find = "\"" + find + "\"";

    String temp = "";

    //for loop that iterates through ArrayList[0] to find matching key to what looking for
    for(int i = 0; i < dataSet.get(0).size(); i++){
      temp = dataSet.get(0).get(i);
      //if key is the same as what looking for, return the index of the key
      if(temp.equals(find)){
        return i;
      }
    }

    //return -1 if the key was not found
    return -1;
  }


  public static double findLowestPrice() throws FileNotFoundException{
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    int priceIndex = findIndex("fare");

    //holder variables
    String price = "";
    double currentPrice = 0.0;
    double lowestPrice = 100.0;

    //loop to iterate through names to find price of person's ticket
    for(int row = 1; row < dataSet.size(); row++){
      //saves current price from String to double
      price = dataSet.get(row).get(priceIndex);
      if(price == ""){
        continue;
      }
      currentPrice = Double.parseDouble(price);
      if(currentPrice < lowestPrice){
        lowestPrice = currentPrice;
      }
    }

    return lowestPrice;

  }


  public static double findHighestPrice() throws FileNotFoundException{
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    int priceIndex = findIndex("fare");

    //holder variables
    String price = "";
    double currentPrice = 0.0;
    double highestPrice = 0.0;

    //loop to iterate through names to find price of person's ticket
    for(int row = 1; row < dataSet.size(); row++){
      //saves current price from String to double
      price = dataSet.get(row).get(priceIndex);
      if(price == ""){
        continue;
      }
      currentPrice = Double.parseDouble(price);
      if(currentPrice > highestPrice){
        highestPrice = currentPrice;
      }
    }

    return highestPrice;

  }


  public static String mostExpensiveTicketSurvival() throws FileNotFoundException{
    //method that finds the people with the most expensive tickets and sees if they survived
    //split up by top half of expensive and bottom half and compare? or maybe top, middle, bottom and compare

    //find highest and lowest ticket price and split halves by that
    //have counter variables for lower and upper halves, add by boolean values and then divide
    //also have counter variables for all upper and lower
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);
    int priceIndex = findIndex("fare");
    int surviveIndex = findIndex("survived");

    double middle = (findLowestPrice() + findHighestPrice()) *.3;
    System.out.println(middle);
    String currentPriceString = "";
    double currentPrice = 0.0;

    String currentSurviveString = "";
    double currentSurvive = 0.0;

    int highTotal = 0;
    int lowTotal = 0;
    double highSurvive = 0.0;
    double lowSurvive = 0.0;

    for(int row = 1; row < dataSet.size(); row++){
      //saves the name of the row currently looking at
      currentPriceString = dataSet.get(row).get(priceIndex);

      //if the price is empty, move on
      if(currentPriceString == "" ){
        continue;
      }

      currentPrice = Double.parseDouble(currentPriceString);

      //if price is equal to middle, disregard in count
      //in titanic there is no price equal to middle, but just to be safe (because doesn't work for either side)
      if(currentPrice == middle){
        continue;
      }

      //if the price is high, increase highTotal and increase highSurvive (if true)
      if(currentPrice > middle){
        //System.out.println(row);
        highTotal ++;
        currentSurviveString = dataSet.get(row).get(surviveIndex);
        currentSurvive = Integer.valueOf(currentSurviveString);

        if(currentSurvive == 1){
          highSurvive++;
        } else {}

      //if the price is low, increase lowTotal and increase lowSurvive (if true)
      } else{
        lowTotal++;
        currentSurvive = Integer.valueOf(dataSet.get(row).get(surviveIndex));

        if(currentSurvive == 1){
          lowSurvive++;
        } else{}
      }

    }

    System.out.println("high total: " + highTotal + " high survive: " + highSurvive);
    System.out.println("low total: " + lowTotal + " low survive: " + lowSurvive);


    //find survival percentage rates for each side
    double highAverage = (highSurvive/highTotal) * 100.0;
    double lowAverage = (lowSurvive/lowTotal) * 100.0;

    //String to return percentages cut to two decimal points
    String ret = "Expensive ticket survival rate: " + String.format("%.2f",highAverage) + "%.\n";
    ret = ret + "Less expensive ticket survival rate: " + String.format("%.2f",lowAverage) + "%.";

    return ret;
  }



  public static String genderSurvival() throws FileNotFoundException{
    //method that determines whether there is a trend between gender and genderSurvival
    //while loop that iterates through each person, adds 1 to female if F survived and opp for men
    //when iterating through genders, add one to female one to male (total f passengers and m)
    //compares the female and male survivals (compare female/male survival percentages)
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);
    int genderIndex = findIndex("sex");
    int surviveIndex = findIndex("survived");

    String gender = "";
    String surviveString = "";
    int currentSurvive = 0;

    double femaleTotal = 0.0;
    int femaleSurvive = 0;

    double maleTotal = 0.0;
    int maleSurvive = 0;



    for(int row = 1; row < dataSet.size(); row++){
      gender = dataSet.get(row).get(genderIndex);
      surviveString = dataSet.get(row).get(surviveIndex);

      //if gender or survival is not listed, skip the line
      if(gender.equals("") || surviveString.equals("")){
        continue;
      }
      currentSurvive = Integer.valueOf(surviveString);

      if(gender.equals("\"female\"")){
        femaleTotal++;

        if(currentSurvive == 1){
          femaleSurvive++;
        }
      } else {
        maleTotal++;

        if(currentSurvive == 1){
          maleSurvive++;
        }
      }

    }

    //find percentages of survival rates per gender
    //System.out.println("f total: " + femaleTotal + " f surv: " + femaleSurvive);
    //System.out.println("m total: " + maleTotal + " m surv: " + maleSurvive);

    double femalePercent = (femaleSurvive/femaleTotal) * 100.0;
    double malePercent = (maleSurvive/maleTotal) * 100.0;

    //String to return percentages cut to two decimal points
    String ret = "Female survival rate is " + String.format("%.2f",femalePercent) + "%.\n";
    ret = ret + "Male survival rate is " + String.format("%.2f",malePercent) + "%.";

    return ret;

  }

}
