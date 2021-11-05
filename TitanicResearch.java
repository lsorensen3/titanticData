import java.util.*;
import java.io.*;

public class TitanicResearch{
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File("titanic3.csv");
    allData(f);

    //testing methods:
    System.out.println(findIndex("name"));

    System.out.println(ticketPrice("Allison, Master. Hudson Trevor")); //should be 151.5500

    System.out.println(survival("Allison, Miss. Helen Loraine")); //false (0)

    System.out.println(findGender("Aubart, Mme. Leontine Pauline")); //female

    System.out.println(findAge("Allison, Master. Hudson Trevor")); //0.92

    System.out.println(findHighestPrice()); //should be 512.3292

    System.out.println(findLowestPrice()); //should be 0.0

    System.out.println(mostExpensiveTicketSurvival());

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


  public static double ticketPrice(String name) throws FileNotFoundException{
    //iterate through each data line to find price of ticket using name
    //use a while loop (while next is true) to iterate through names
    //once find an exact name, find and return the price of the ticket

    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    //find indexes of name and fare for this person
    int nameIndex = findIndex("name");
    int priceIndex = findIndex("fare");

    //add quotes to find so it can match setup of csv data
    name = "\"" + name + "\"";

    //holder variables
    String currentName = "";
    String holdDouble = "";
    double price = 0.0;

    //loop to iterate through names to find price of person's ticket
    for(int row = 0; row < dataSet.size(); row++){
      //saves the name of the row currently looking at
      currentName = dataSet.get(row).get(nameIndex);

      if(currentName.equals(name)){
        //changes price (since currently a String) to a double
        holdDouble = dataSet.get(row).get(priceIndex);
        price = Double.parseDouble(holdDouble);
        return price;
      }
    }

    return price;
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




  public static boolean survival(String name) throws FileNotFoundException{
    //iterate through each data line to find if the person survived
    //while loop to find name of passenger, return true or false for survival
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    //save indexes of name and survival, add quotes around name
    name = "\"" + name + "\"";
    int nameIndex = findIndex("name");
    int surviveIndex = findIndex("survived");

    String currentName = "";

    //holder variables
    boolean survive = false;
    int binarySurvive = 0;

    //loop that finds whether the person survived or not
    for(int row = 0; row < dataSet.size(); row++){
      //saves the name of the row currently looking at
      currentName = dataSet.get(row).get(nameIndex);

      if(currentName.equals(name)){
        //saves survival as int (since in binary on csv)
        binarySurvive = Integer.valueOf(dataSet.get(row).get(surviveIndex));
        //changes binary to true/false (easier to read and matches boolean return)
        if(binarySurvive ==1){
          return true;
        } else {
          return false;
        }
      }
    }

    return survive;
  }


  public static String findGender(String name) throws FileNotFoundException{
    //iterate through each data line to find gender of person
    //while loop to find name, return F for female, M for male
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    //save indexes of name and gender, add quotes around name
    name = "\"" + name + "\"";
    int nameIndex = findIndex("name");
    int genderIndex = findIndex("sex");

    //holder variables
    String currentName = "";
    String sex = "";
    String tempSex = "";

    for(int row = 0; row < dataSet.size(); row++){
      //saves the name of the row currently looking at
      currentName = dataSet.get(row).get(nameIndex);

      if(currentName.equals(name)){
        sex = dataSet.get(row).get(genderIndex);
        //removes quotes around the sex of the person
        tempSex = sex.substring(1);
        sex = tempSex.substring(0, tempSex.length()-1);
        return sex;
      }
    }

    return sex;
  }



  public static double findAge(String name) throws FileNotFoundException{
    //iterate through each data line to find the age of each person
    //find name, return age
    //return is a double because one of the passengers is less than a year old (need double or will be 0yo)
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    name = "\"" + name + "\"";
    int nameIndex = findIndex("name");
    int ageIndex = findIndex("age");

    String currentName = "";

    String stringAge = "";
    double age = 0;

    for(int row = 0; row < dataSet.size(); row++){
      //saves the name of the row currently looking at
      currentName = dataSet.get(row).get(nameIndex);

      if(currentName.equals(name)){
        //turns String age to a double for the age (double returned)
        stringAge = dataSet.get(row).get(ageIndex);
        age = Double.parseDouble(stringAge);
        return age;
      }
    }

    return age;
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

    double middle = (findLowestPrice() + findHighestPrice()) /2;
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
      if(currentPrice == middle){
        continue;
      }

      //if the price is high, increase highTotal and increase highSurvive (if true)
      if(currentPrice > middle){
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

    double highAverage = (highSurvive/highTotal) * 100.0;
    double lowAverage = (lowSurvive/lowTotal) * 100.0;

    String ret = "More expensive tickets have a survival rate of " + highAverage + "%, ";
    ret = ret + "while less expensive tickets have a survival rate of " + lowAverage + "%.";

    //return percentage of survival
    return ret;
  }


/*
  public static double genderSurvival() throws FileNotFoundException{
    //method that determines whether there is a trend between gender and genderSurvival
    //while loop that iterates through each person, adds 1 to female if F survived and opp for men
    //when iterating through genders, add one to female one to male (total f passengers and m)
    //compares the female and male survivals (compare female/male survival percentages)
  }

*/
}
