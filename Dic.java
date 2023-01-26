/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryenvn;

/**
 *
 * @author Group 1
 */
public class Dic {

    private String english;     //dictionary's english
    private String vietnamese;  //dictionary's vietnamese

    /**
     *
     * @param english
     * @param vietnamese
     */
    public Dic(String english, String vietnamese) {
        this.english = english;
        this.vietnamese = vietnamese;
    }

    /**
     * Gets English for dictionary
     *
     * @return
     */
    public String getEnglish() {
        return english;
    }

    /**
     * Sets English for dictionary
     *
     * @param english
     */
    public void setEnglish(String english) {
        this.english = english;
    }

    /**
     * Gets Vietnamese for dictionary
     *
     * @return
     */
    public String getVietnamese() {
        return vietnamese;
    }

    /**
     * Sets Vietnamese for dictionary
     *
     * @param vietnamese
     */
    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

}
