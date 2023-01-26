/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook1shop;

/**
 *
 * @author MY LAPTOP
 */
public class ComicBook1Exception extends Exception {
    
    /**
     * Create Exception
     *
     * @param message
     */
    public ComicBook1Exception(String message) {
        super(message);
    }

    /**
     * Write Exception
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "Exception >>" + super.getMessage();
    }
}
