# Titanic Research


## Introduction

Here is my introduction. include source of data here (where/who/if have legal permission to use).

I have always been fascinated with history, the Titanic included. I was excited when I found this data source on the list and knew that it would be a great option to study (my other final choice was fossils, once again, history). I somewhat assumed the general answers to my questions, but I thought it would be fun to test myself by creating the code that would help me find the exact and statistically correct answers.

The questions that I attempted to answer with my code were:
  1. Is there a trend between ticket fare and survival? If so, what are the different survival rates for more expensive tickets vs. less expensive tickets?
  2. Is there a trend between gender and survival? If so, what are the exact survival rates for females and males?


## Methods

Here are my Methods.
1. Dataset: describe how data was generated (what i know), who owns it, why am i able to use it
2. Process: self explanatory - How did you go about answering your questions? What specific pieces of
information did you need from your data set and how did you combine data to arrive at
your answer?

Process: I first started with creating additional methods that would help me with the necessary methods that worked to answer my questions. One of the most useful methods that I created allowed me to take all the data from my csv file and turn it into a String ArrayList of ArrayLists. Putting all the data into an ArrayList made it a lot easier to access necessary data, especially since I am more familiar with the syntax for ArrayLists and its methods. Another crucial method was my method findIndex(String find). findIndex has a String parameter that takes in the key (ex. age, name, etc.). The method returns the index in the ArrayList of data that would have the information. This method was not necessary since the order of my data set will not change, but it is important to include for the what-if scenarios and to ensure full coverage. I found that some of the other helper methods I created, such as findName and findGender, were not necessary for my code.

3. Challenges: having to disregard empty strings/tokens, account for quotes in tokens, cut to two decimals
The biggest challenge that I faced was figuring out a way to get all of the csv data in an ArrayList to optimize readability and simplify the means of obtaining said data. I was originally going to use the .split() method in order to turn each line of my csv into an array, but since some of my data tokens had necessary commas in them, the .split() method on its own would not work. I then tried to put all of the information into an ArrayList using a while loop, but this proved difficult since each line of a csv has a hidden \n (since it goes on to the next line). I presented my difficulties to my teacher, who then showed me a line of code that would work around this issue (String..split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");).
Another major challenge in answering my questions was dealing with empty elements in the data ArrayList. While this may seem like a minor issue, it was fairly time consuming to debug as it took a long time to figure out what the real issue was. Once I found out why my code wasn't working, I just needed to include a simple if/else statement that disregarded lines in the dataset that did not include a real value for the specific token.

## Results and Conclusion

Here is what I found.
1. What does data show/what are takeaways? Plots might be helpful (questions dependent)
2. What are some related or additional questions that you would like to explore granted more time/resources?
