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
public class ComicBook1 {

    private int ID;
    private double bookRentalPrice;
    private String Author, Title;
    private int Volume;

    public ComicBook1(int id, String title, String author, int volume, double bookRentalPrice) {
        this.ID = id;
        this.bookRentalPrice = bookRentalPrice;
        this.Author = author;
        this.Title = title;
        this.Volume = volume;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getBookRentalPrice() {
        return bookRentalPrice;
    }

    public void setBookRentalPrice(double bookRentalPrice) {
        this.bookRentalPrice = bookRentalPrice;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getVolume() {
        return Volume;
    }

    public void setVolume(int Volume) {
        this.Volume = Volume;
    }

}
