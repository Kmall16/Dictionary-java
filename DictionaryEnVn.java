/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryenvn;

import static dictionaryenvn.DicManagement.file2String;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The class DictionaryEnVn is used to manage dictionary
 *
 * @author Group 1
 */
public class DictionaryEnVn {

    /**
     */
    public static DicManagement fa;

    /**
     * Shows menu and gets function user choose
     *
     * @return
     */
    public static int menu() {
        //Shows menu
        System.out.println("<<<ENGLISH-VIETNAMESE DICTIONARY>>>");
        System.out.println("1. Look up Vietnamese meaning.");
        System.out.println("2. Look up English word.");
        System.out.println("3. Add new word into dictionary.");
        System.out.println("4. Vocabulary test.");
        System.out.println("5. Exit.");
        int n = 0;
        boolean isFalse = false;
        int numberOfWord = fa.getNumberOfWord(); //gets number of word
        do {
            do {
                try {
                    System.out.print("Please choose a function from 1 to 5: ");
                    Scanner sc = new Scanner(System.in); //Creates a scanner
                    n = Integer.parseInt(sc.nextLine().trim()); //Enter function
                    if ((n >= 1 && n <= 5)) {
                        isFalse = true;
                        if (numberOfWord == 0 && (n == 2 || n == 1 || n == 4)) {
                            //Case number of fish is 0 and user selects function different 1 and 6
                            System.out.println("There is no word! Please add new word before choosing other functions!");
                        }
                    } else {
                        //user enter invalid number
                        System.out.println("Invalid values! Please try again!");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid values! Please try again!");
                }
            } while (numberOfWord == 0 && (n == 2 || n == 1 || n == 4));
        } while (isFalse == false);
        return n;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            fa = new DicManagement("src/data/EnVn.txt"); //Loads data
            String data = DicManagement.decrypt(file2String("src/data/EnVn.txt"));   //converts file to string and decrypts data into data
            fa.saveFile(data, "src/data/EnVn.txt"); //saves converted data into data file
            fa.loadWord("src/data/EnVn.txt"); //Loads word into hashtable
            Scanner sc = new Scanner(System.in); //Creates a scanner
            int function; //The function that selected by user
            fa.showTable();
            do {
                function = menu(); //Gets function that selected by user
                boolean isFalse;
                String confirm = "";
                switch (function) {
                    case 1:
                        do {
                            System.out.println("<<<LOOK UP VIETNAMESE MEANING>>>");
                            String key;
                            String checkValue = "^[a-zA-Z]+"; //variable to check if it is special character
                            do {
                                isFalse = false;
                                System.out.print("Please enter a keyword that you want to look up: ");
                                key = sc.nextLine().trim().replaceAll(" ", "");
                                //enter key to search
                                if (key.equals("")) {
                                    //if key is null
                                    System.out.println("The keyword can't be empty! Please try again!");
                                } else if (!key.matches(checkValue)) {
                                    //if key has special characters and numbers
                                    System.out.println("Invalid values! The keyword must be english alphabet! Please try again!");
                                } else {
                                    //if key is valid
                                    isFalse = true;
                                }
                            } while (!isFalse);
                            fa.showLookUpVietnameseMeaning(key); //shows list of english words contain key or definition if key equals a english word
                            do {
                                System.out.print("Do you want to continue to look up? [(Y/y)/(N/n)]: ");
                                confirm = sc.nextLine().trim(); //variable to confirm out
                                if (!confirm.equals("Y") && !confirm.equals("y") && !confirm.equals("N") && !confirm.equals("n")) {
                                    System.out.println("Invalid values! Please try again!");
                                }
                            } while (!confirm.equals("Y") && !confirm.equals("y") && !confirm.equals("N") && !confirm.equals("n"));

                        } while (confirm.equals("Y") || confirm.equals("y"));
                        break;
                    case 2:
                        do {
                            System.out.println("<<<LOOK UP ENGLISH WORD>>>");
                            String key, key0;
                            String checkSpecialCharacter = "^[a-zA-Z]+"; //variable to check if it is special character
                            do {
                                isFalse = false;
                                System.out.print("Please enter a keyword that you want to look up: ");
                                key = sc.nextLine().trim().replaceAll(" ", "");
                                //enter key to search
                                if (key.equals("")) {
                                    //if key is empty
                                    System.out.println("The keyword can't be empty! Please try again!");
                                } else if (!key.matches(checkSpecialCharacter)) {
                                    //if key has special characters and numbers
                                    System.out.println("Invalid values! The keyword must be in english alphabets! Please try again!");
                                } else {
                                    //if key is valid
                                    isFalse = true;
                                }
                            } while (!isFalse);
                            fa.showLookUpEnglishWord(key);  //shows list of vietnamese words contain key or definition if key equals a vietnamese word
                            do {
                                System.out.print("Do you want to continue to look up? [(Y/y)/(N/n)]: ");
                                confirm = sc.nextLine().trim(); //variable to confirm out
                                if (!confirm.equals("Y") && !confirm.equals("y") && !confirm.equals("N") && !confirm.equals("n")) { // case invalid values
                                    System.out.println("Invalid values! Please try again!");
                                }
                            } while (!confirm.equals("Y") && !confirm.equals("y") && !confirm.equals("N") && !confirm.equals("n")); //Loop if invalid values

                        } while (confirm.equals("Y") || confirm.equals("y")); //Loop if invalid values
                        break;
                    case 3:
                        System.out.println("<<<ADD NEW WORD INTO DICTIONARY>>>");
                        String english,
                         vietnamese;
                        String checkValue = "^[a-zA-Z]+";
                        do {
                            isFalse = false;
                            System.out.print("Please enter new english word: ");
                            english = sc.nextLine().trim(); //enter new english word
                            if (english.equals("")) { //if new english word is empty
                                System.out.println("English word can't be empty! Please try again!");
                            } else if (!english.matches(checkValue)) { //if new english word has number or specical chacracter
                                System.out.println("Invalid values! English word must be in english alphabets! Please try again!");
                            } else { //valid value
                                if (fa.englishWordExisted(english)) { //if new english word existed
                                    System.out.println("The english word existed! Please try again!");
                                } else {// if valid value
                                    isFalse = true;
                                }
                            }
                        } while (!isFalse); // Loop if invalid values

                        do {
                            isFalse = false;
                            System.out.print("Please enter vietnamese meaning: ");
                            vietnamese = sc.nextLine(); //enter new vietnamese word
                            if (vietnamese.equals("")) { //if vietnamese meaning is empty
                                System.out.println("Vietnamese meaning can't be empty! Please try again!");
                            } else if (!vietnamese.replaceAll(" ", "").matches(checkValue)) {//if vietnamese meaning has number or specical chacracter
                                System.out.println("Invalid values! Vietnamese meaning word must be in english alphabets! Please try again!");
                            } else {//valid value
                                isFalse = true;
                            }
                        } while (!isFalse);// Loop if invalid values
                        fa.addWord(english.toLowerCase().trim(), vietnamese.toLowerCase().trim()); //adds new word into dictionary
                        System.out.println("The new word has been added successfully!"); //Notice that new word has added success
                        fa.showTable(); //show table of dictionary
                        System.out.print("Please press ENTER to continue!");
                        sc.nextLine().trim(); //Enter to continue
                        break;
                    case 4:
                        System.out.println("<<<VOCABULARY TEST>>>(We will randomly put 10 words into the test)");
                        ArrayList<String> Wrong = new ArrayList<String>(); //Array list stores wrong answers
                        ArrayList<String> Vietnamese = new ArrayList<String>(); //Array list stores Vietnamese in test
                        ArrayList<String> English = new ArrayList<String>(); //Array list stores English in test
                        checkValue = "^[a-zA-Z]+"; //value to check valid or not
                        String Answer = null;
                        int score = 0;

                        if (fa.getSizeOfDictionary() >= 10) { //if dictionary has enough words to test
                            fa.getWord();
                            int no = 0;
                            for (int i = 0; i < 10; i++) {
                                Dic dc = fa.getDIC().get(i); //gets word and saves into dc
                                System.out.printf("%d. ", ++no);
                                System.out.print("The Vietnamese word is \"" + dc.getVietnamese() + "\". "); //shows test's question
                                do {
                                    isFalse = false;
                                    System.out.print("Enter the English: ");
                                    sc = new Scanner(System.in);
                                    Answer = sc.nextLine().trim(); //enter answer
                                    if (Answer.equals("")) { //if the answer is empty
                                        System.out.print("The answer can't be empty! Please try again! ");
                                    } else if (!Answer.matches(checkValue)) { //if the answer has number or specical chacracter
                                        System.out.print("Invalid values! The answer must be in english alphabets! Please try again!");
                                    } else { //if valid value
                                        isFalse = true;
                                    }
                                } while (!isFalse); //Loop if invalid value

                                if (Answer.toLowerCase().equals(dc.getEnglish()) || fa.getSynonyms(Answer, dc.getVietnamese())) { //if answer is correct
                                    System.out.println("The answer is correct!");

                                    //Wrong.add(Answer); //adds answer into list answer
                                    score += 1; //Plus one point if the answer is correct
                                    System.out.println("The score is: " + score + "/10");
                                } else { //if answer is incorrect
                                    System.out.println("The answer is wrong!");
                                    English.add(dc.getEnglish());
                                    Vietnamese.add(dc.getVietnamese());
                                    Wrong.add(Answer); //adds answer into list answer
                                    System.out.println("The score is: " + score + "/10");
                                }
                                System.out.print("Please press ENTER to continue!");
                                sc.nextLine().trim();
                            }
                            System.out.printf("The test finished! The result is being calculated...Done! The total score is: %d/10 [%.0f", score, score * ((float) 100) / 10); //notices finishing the test and show score
                            System.out.println("%]");

                            if (Wrong.size() != 0) { //only show when there is at least one wrong answer 
                                System.out.println("+---------------------------------------------------------------------------------+");
                                System.out.println("| RESULT TABLE                                                                    |");
                                System.out.println("+------+------------------------+------------------------+------------------------+");
                                System.out.println("| No.  | Vietnamese meaning     | Correct Egnlish Answer | Wrong English Answer   |");
                                System.out.println("+------+------------------------+------------------------+------------------------+");
                                for (int i = 0; i < Wrong.size(); i++) {
                                    System.out.printf("|%5d | %-23s| %-23s| %-23s| \n", i + 1, Vietnamese.get(i), English.get(i), Wrong.get(i));
                                }
                                System.out.println("+------+------------------------+------------------------+------------------------+");
                            }
                        } else { //if dictionary does not have enough 10 word 
                            System.out.print("The dictionary does not have enough 10 words for the test! ");
                        }
                        System.out.print("Please press ENTER to continue!");
                        sc.nextLine().trim();
                        break;
                    case 5: //case to exit
                        break;
                }
            } while (function != 5);//Loop if function is not equal 5
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fa.saveFile("src/data/EnVn.txt"); //encrypts and saves data
                System.out.println("Thanks for using our software!");
            } catch (Exception e) {
                System.out.println("Can't save the data!");
            }
        }
    }

}
