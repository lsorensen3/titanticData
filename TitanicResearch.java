import java.util.*;
import java.io.*;

public class TitanicResearch{
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File("titanic3.csv");
    allData(f);

    System.out.println(findIndex("name"));

    System.out.println(ticketPrice("Allison, Master. Hudson Trevor")); //should be 151.5500

    System.out.println(survival("Allison, Miss. Helen Loraine")); //true (1)

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



  public static boolean survival(String name) throws FileNotFoundException{
    //iterate through each data line to find if the person survived
    //while loop to find name of passenger, return true or false for survival
    File f = new File("titanic3.csv");
    ArrayList<ArrayList<String>> dataSet = allData(f);

    name = "\"" + name + "\"";
    int nameIndex = findIndex("name");
    int surviveIndex = findIndex("survived");

    String currentName = "";

    boolean survive = false;
    int binarySurvive = 0;

    for(int row = 0; row < dataSet.size(); row++){
      //saves the name of the row currently looking at
      currentName = dataSet.get(row).get(nameIndex);

      if(currentName.equals(name)){
        binarySurvive = Integer.valueOf(dataSet.get(row).get(surviveIndex));
        if(binarySurvive ==1){
          return true;
        } else {
          return false;
        }
      }
    }

    return survive;

  }

/*
  public static String findGender(String name){
    //iterate through each data line to find gender of person
    //while loop to find name, return F for female, M for male

  }

  public static int findAge(String name){
    //iterate through each data line to find the age of each person
    //find name, return age

  }

  public static double mostExpensiveTicketSurvival(){
    //method that finds the people with the most expensive tickets and sees if they survived
    //split up by top half of expensive and bottom half and compare? or maybe top, middle, bottom and compare


    //return percentage of survival
  }

  public static double genderSurvival(){
    //method that determines whether there is a trend between gender and genderSurvival
    //while loop that iterates through each person, adds 1 to female if F survived and opp for men
    //when iterating through genders, add one to female one to male (total f passengers and m)
    //compares the female and male survivals (compare female/male survival percentages)
  }

*/
}
