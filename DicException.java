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
public class DicException extends Exception {

    /**
     * Creates new DictionaryException
     *
     * @param message
     */
    public DicException(String message) {
        super(message);
    }

    /*
    * Gets the exception message
    *@return
     */
    @Override
    public String getMessage() {
        return "Excepttion >>" + super.getMessage();
    }

}
