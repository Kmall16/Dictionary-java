/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryenvn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class DicManagement is used to manage Dictionary
 *
 * @author Group 1
 */
public class DicManagement {

    private int numberOfWord;
    private String fileName;
    private Hashtable<String, String> Dic;
    private ArrayList<Dic> DIC;
    private ArrayList<String> VietnameseMeaning = new ArrayList<String>();

    /**
     * Creates instance for dictionary management
     *
     * @param fileName
     * @throws DicException
     */
    public DicManagement(String fileName) throws DicException {
        if (fileName.equals("")) {
            throw new DicException("Name of data file can't be empty");
        } else {
            this.numberOfWord = 0;
            this.fileName = fileName;
            this.Dic = new Hashtable<String, String>();
            this.DIC = new ArrayList<Dic>();
        }
    }

    /**
     * Gets number of English word in dictionary
     *
     * @return
     */
    public int getNumberOfWord() {
        return numberOfWord;
    }

    /**
     *
     * @param filePath
     * @throws IOException
     */
    public void loadWord(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) { //Checks is file created
            file.createNewFile(); //If not, creates new file
            System.out.print("The data file does not exist! ");
            this.numberOfWord = 0; //New data file with the number of answer is 0
        } else {
            //If file is existed
            if (file.length() == 0) { //If file contans nothing
                System.out.printf("The file contains nothing! Please give input for the file \n");
            } else { //If file is valid, so loading this data file
                //fileName = decrypt(file2String(fileName));
                //System.out.println(fileName);
                System.out.print("The data file exist! ");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                try {
                    this.numberOfWord = Integer.parseInt(reader.readLine());  //Reads number of word                  
                    for (int i = 0; i < this.numberOfWord; i++) {
                        //Reads english and vietnamese
                        String english = reader.readLine().trim();
                        String vietnamese = reader.readLine().trim();
                        try {
                            //Creates new instance of fish and adds to dictionary
                            Dic.put(english, vietnamese);
                        } catch (Exception ex) {
                            Logger.getLogger(DicManagement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } finally {
                    reader.close();
                    System.out.printf("Data is loading...Done![%d word(s)]", getNumberOfWord());
                    System.out.println();
                }
            }
        }
    }

    /**
     * Shows table of word
     */
    public void showTable() {
        System.out.println("+-------+------------------------+----------------------------------------+");
        System.out.println("| No.   | English                | Vietnamese                             |");
        System.out.println("+-------+------------------------+----------------------------------------+");
        int no = 0;
        for (String key : Dic.keySet()) {
            //Shows english and vietnamese
            String EngWord = key;
            String VietWord = this.Dic.get(key);
            System.out.printf("|%6d | %-23s| %-39s|\n", ++no, EngWord, VietWord);
        }
        System.out.println("+-------+------------------------+----------------------------------------+");
    }

    private static final int KEY = 5;

    /**
     * encrypts data
     *
     * @param text
     * @return
     */
    public static String encrypt(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            result += (char) ((int) text.charAt(i) + KEY);
        }
        return result;
    }

    /**
     * decrypts data
     *
     * @param text
     * @return
     */
    public static String decrypt(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            result += (char) ((int) text.charAt(i) - KEY);
        }
        return result;
    }

    /**
     * converts from file to string
     *
     * @param filePath
     * @return
     */
    public static String file2String(String filePath) {
        String result = "";
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                result += sc.nextLine() + "\n";
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    /**
     * writes and encrypts word into data file
     *
     * @param filePath
     * @throws IOException
     */
    public void saveFile(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        try {
            writer.write(encrypt(Integer.toString(numberOfWord)));
            writer.write(encrypt("\n"));
            for (String key : Dic.keySet()) {
                writer.write(encrypt(key));
                writer.write(encrypt("\n"));
                writer.write(encrypt(this.Dic.get(key)));
                writer.write(encrypt("\n"));
            }

            System.out.println("Data is saving...Done!");
        } finally {
            writer.close();
        }
    }

    /**
     * Saves data into file
     *
     * @param text
     * @param filePath
     */
    public static void saveFile(String text, String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * shows word with keyword
     *
     * @param keysearch
     * @param no
     */
    public void showWord(String keysearch, int no) {
        //Shows english and vietnamese
        //keysearch:
        Dic.keySet();
        System.out.printf("|%6d | %-27s|\n", ++no, keysearch);
        System.out.println("+-------+----------------------------+");
    }

    /**
     * show word equal keyword
     *
     * @param keysearch
     * @param wordkeysearch
     * @param no
     */
    public void showWordEqual(String keysearch, String wordkeysearch, int no) {
        Dic.keySet();
        System.out.printf("|%6d | %-27s| %-27s|\n", ++no, keysearch, wordkeysearch);
        System.out.println("+-------+----------------------------+----------------------------+");
    }

    /**
     * search English word contains query
     *
     * @param query
     * @return
     */
    public boolean lookUpConTainsVietnameseMeaning(String query) {
        for (String key : Dic.keySet()) {
            if (key.toLowerCase().contains(query.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * search English word equals query
     *
     * @param query
     * @return
     */
    public int lookUpContentEqualVietnameseMeaning(String query) {
        int count = 0;
        for (String key : Dic.keySet()) {
            if (key.trim().toLowerCase().contentEquals(query.trim().toLowerCase())) {
                ++count;
            }
        }
        return count;
    }

    /**
     * shows lists of English word contains query or shows definition if query
     * equals English word
     *
     * @param query
     */
    public void showLookUpVietnameseMeaning(String query) {
        if (lookUpConTainsVietnameseMeaning(query) && lookUpContentEqualVietnameseMeaning(query) == 0) {
            //if Vietnamese meaning contains keyword and does not equal keyword
            System.out.println("+------------------------------------+");
            System.out.println("| ENGLISH WORDS CONTAINS KEYWORD     |");
            System.out.println("+-------+----------------------------+");
            System.out.println("| No.   | English                    |");
            System.out.println("+-------+----------------------------+");
            int no = 0;
            for (String key : Dic.keySet()) {
                if (key.toLowerCase().contains(query.toLowerCase())) {
                    showWord(key, no++);
                }
            }
        } else if (lookUpContentEqualVietnameseMeaning(query) == 1) {
            //if Vietnamese meaning equal keyword that equals 1
            for (String key : Dic.keySet()) {
                if (key.toLowerCase().contentEquals(query.toLowerCase())) {
                    System.out.println("The Vietnamese meaning of \"" + key + "\" is \"" + Dic.get(key) + "\"");
                }
            }
        } else if (lookUpContentEqualVietnameseMeaning(query) > 1) {
            ////if Vietnamese meanings equal keyword that are greater than 1
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("| TABLE OF VIETNAMESE                                             |");
            System.out.println("+-------+----------------------------+----------------------------+");
            System.out.println("| No.   | English                    | Vietnamese                 |");
            System.out.println("+-------+----------------------------+----------------------------+");
            int no = 0;
            for (String key : Dic.keySet()) {
                if (Dic.get(key).replaceAll(" ", "").trim().toLowerCase().contentEquals(query.toLowerCase())) {
                    showWordEqual(key, Dic.get(key), no);
                    ++no;
                }
            }
        } else {
            System.out.println("There is no word contains the keyword!");
        }
    }

    /**
     * search Vietnamese word contains query
     *
     * @param query
     * @return
     */
    public boolean lookUpConTainsEnglishWord(String query) {
        for (String key : Dic.keySet()) {
            if (Dic.get(key).toLowerCase().contains(query.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * search Vietnamese word equals query
     *
     * @param query
     * @return
     */
    public int lookUpContentEqualEnglishWord(String query) {
        int count = 0;
        for (String key : Dic.keySet()) {
            if (Dic.get(key).replaceAll(" ", "").trim().toLowerCase().contentEquals(query.replaceAll(" ", "").toLowerCase())) {
                ++count;
            }
        }
        return count;
    }

    /**
     * shows lists of Vietnamese word contains query or shows definition if
     * query equals Vietnamese word
     *
     * @param query
     */
    public void showLookUpEnglishWord(String query) {
        if (lookUpConTainsEnglishWord(query) && lookUpContentEqualEnglishWord(query) == 0) {
            //if English word contains keyword and does not equal keyword
            System.out.println("+------------------------------------+");
            System.out.println("| VIETNAMESE WORDS CONTAINS KEYWORD  |");
            System.out.println("+-------+----------------------------+");
            System.out.println("| No.   | Vietnamese                 |");
            System.out.println("+-------+----------------------------+");
            int no = 0;
            /*ArrayList<String>*/ VietnameseMeaning = new ArrayList<String>();
            for (String key : Dic.keySet()) {
                if (Dic.get(key).toLowerCase().contains(query.toLowerCase()) 
                        && !checkVietnameseExisted(Dic.get(key))) { //check if next Vietnamese meaning is the same with the Vietnamese meanings before                   
                    VietnameseMeaning.add(Dic.get(key));
                }
            }
            for (int i = 0; i < VietnameseMeaning.size(); i++) { //shows vietnamese meaning contains keyword
                showWord(VietnameseMeaning.get(i), no++);
            }

        } else if (lookUpContentEqualEnglishWord(query) == 1) { //if Vietnamese meaning equals keyword is 1
            for (String key : Dic.keySet()) {
                if (Dic.get(key).replaceAll(" ", "").trim().toLowerCase().contentEquals(query.replaceAll(" ", "").toLowerCase())) {
                    System.out.println("The English word of \"" + Dic.get(key) + "\" is \"" + key + "\"");
                }
            }
        } else if (lookUpContentEqualEnglishWord(query) > 1) {  //if Vietnamese meanings equal keyword that is greater than 1
            System.out.println("+-----------------------------------------------------------------+");
            System.out.println("| TABLE OF ENGLISH                                                |");
            System.out.println("+-------+----------------------------+----------------------------+");
            System.out.println("| No.   | Vietnamese                 | English                    |");
            System.out.println("+-------+----------------------------+----------------------------+");
            int no = 0;
            for (String key : Dic.keySet()) {
                if (Dic.get(key).replaceAll(" ", "").trim().toLowerCase().contentEquals(query.replaceAll(" ", "").trim().toLowerCase())) {
                    showWordEqual(Dic.get(key), key, no++);
                }
            }
        } else {
            System.out.println("There is no word contains the keyword!");
        }
    }

    /**
     * adds new word into dictionary
     *
     * @param english
     * @param vietnamese
     */
    public void addWord(String english, String vietnamese) {
        Dic.put(english, vietnamese);
        ++this.numberOfWord;
    }

    /**
     * checks if new English word existed in dictionary
     *
     * @param english
     * @return
     */
    public boolean englishWordExisted(String english) {
        for (String key : Dic.keySet()) {
            if (key.toLowerCase().contentEquals(english.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * adds word in test into array list
     *
     * @return
     */
    public ArrayList<Dic> getWord() {
        this.DIC = new ArrayList<Dic>(); //reset array list DIC
        boolean isFalse = false; //value to check
        String[] keys = Dic.keySet().toArray(new String[Dic.size()]);
        for (int i = 0; i < 10; i++) {
            keys = Dic.keySet().toArray(new String[Dic.size()]);
            String newkey = keys[new Random().nextInt(keys.length)]; //gets random one new key
            do {

                isFalse = false;
                for (i = 0; i < DIC.size(); i++) {
                    Dic dc = DIC.get(i); //gets data in DIC
                    if (newkey.equals(dc.getEnglish())) { //if new key equal a key was already in DIC
                        keys = Dic.keySet().toArray(new String[Dic.size()]);
                        newkey = keys[new Random().nextInt(keys.length)]; //gets another new key 
                        isFalse = true;
                    }
                }
            } while (isFalse == true);
            DIC.add(new Dic(newkey, Dic.get(newkey))); //adds key into DIC
        }
        /*for (int i = 0; i < DIC.size(); i++) {
            Dic dc = DIC.get(i);
            System.out.println(dc.getEnglish());
        }*/
        return DIC;
    }

    /**
     * Gets array list DIC
     *
     * @return
     */
    public ArrayList<Dic> getDIC() {
        return DIC;
    }

    /**
     * checks if word is synonyms
     *
     * @param english
     * @param vietnamese
     * @return
     */
    public boolean getSynonyms(String english, String vietnamese) {
        for (String key : Dic.keySet()) {
            if (english.toLowerCase().trim().contentEquals(key.toLowerCase().trim())
                    && Dic.get(key).replaceAll(" ", "").trim().toLowerCase().contentEquals(vietnamese.toLowerCase().replaceAll(" ", "").trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets size of dictionary
     *
     * @return
     */
    public int getSizeOfDictionary() {
        return Dic.size();
    }

    /**
     * checks new Vietnamese meaning that equals keyword equal Vietnamese meanings before 
     * @param Vietnamese
     * @return
     */
    public boolean checkVietnameseExisted(String Vietnamese) {
        for (int i=0;i<VietnameseMeaning.size();i++){
            if (Vietnamese.replaceAll(" ", "").trim().toLowerCase().contentEquals(VietnameseMeaning.get(i).replaceAll(" ", "").trim())){
                return true;
            }
        }
            return false;
    }
}
