import java.util.*;
import java.io.*;

public class TitanicResearch{

  public static void main(String[] args) throws FileNotFoundException{
    File f = new File("titanic3.csv");
    allData(f);

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

/*
  public static double ticketPrice(String name){
    //iterate through each data line to find price of ticket using name
    //use a while loop (while next is true) to iterate through names
    //once find an exact name, find and return the price of the ticket
    //price is

  }

  public static boolean survival(String name){
    //iterate through each data line to find if the person survived
    //while loop to find name of passenger, return true or false for survival


  }

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
