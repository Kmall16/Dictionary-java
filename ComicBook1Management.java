/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook1shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author MY LAPTOPS
 */
public class ComicBook1Management {

    private int numberOfBook;
    private String fileName;
    private ArrayList<ComicBook1> ComicBook1;
    private int id;

    public ComicBook1Management(String fileName) throws ComicBook1Exception {
        if (fileName.equals("")) {
            throw new ComicBook1Exception("Name of file can't be empty");
        } else {
            this.numberOfBook = 0;
            this.fileName = fileName;
            this.ComicBook1 = new ArrayList<ComicBook1>();
            this.id = 0;
        }
    }

    public void loadComicBook() throws FileNotFoundException, IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("The data file does not exist!");
        } else {
            if (file.length() == 0) {
                System.out.println("The file contains nothing! Please give input for the file");
            } else {
                System.out.println("The data file exist!");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                this.numberOfBook = Integer.parseInt(reader.readLine());
                for (int i = 0; i < this.numberOfBook; i++) {
                    String id = reader.readLine();
                    String title = reader.readLine();
                    String author = reader.readLine();
                    String volume = reader.readLine();
                    String bookRentalPrice = reader.readLine();

                    this.ComicBook1.add(new ComicBook1(Integer.parseInt(id), title, author,
                            Integer.parseInt(volume), Double.parseDouble(bookRentalPrice)));
                }
                System.out.println("Data is loading...Done!");
                System.out.println();
            }
        }
    }

    public void showComicBooks() {
        System.out.println();
        System.out.println("+-----+-----+-----------------------------+--------------------+--------+");
        System.out.println("|                   TABLE OF COMIC BOOKS                                |");
        System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
        System.out.println("| No. | ID  | Title              | Price  | Author             | Volume |");
        System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
        for (int i = 0; i < this.numberOfBook; i++) {
            System.out.printf("| %3d |", i + 1);
            System.out.printf(" %3d |", this.ComicBook1.get(i).getID());
            System.out.printf(" %-19s|", this.ComicBook1.get(i).getTitle());
            System.out.printf(" $%6.1f|", this.ComicBook1.get(i).getBookRentalPrice());
            System.out.printf(" %-19s|", this.ComicBook1.get(i).getAuthor());
            System.out.printf("   %4d |", this.ComicBook1.get(i).getVolume());
            System.out.println();
        }
        System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
    }
    


    public void addNewComicBook(int id, String title, double bookRentalPrice, String author, int volume) {
        //if (idExisted(id) || bookExisted(title, author, volume)) {
        //    System.out.println("The comic book existed!");
        //} else {
            this.ComicBook1.add(new ComicBook1(id, title, author, volume, bookRentalPrice));
            this.numberOfBook++;
        //}
        this.showComicBooks();
    }

    public boolean searchComicBookByTitle(String query) {
        for (int i = 0; i < this.numberOfBook; i++) {
            if (this.ComicBook1.get(i).getTitle().toLowerCase().contentEquals(query.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void showSearchComicBookByTitle(String query) {
        if (searchComicBookByTitle(query)) {
            System.out.println("+-----+-----+-----------------------------+--------------------+--------+");
            System.out.println("|                   TABLE OF COMIC BOOKS SEARCH BY TITLE                |");
            System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
            System.out.println("| No. | ID  | Title              | Price  | Author             | Volume |");
            System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
            for (int i = 0; i < this.numberOfBook; i++) {
                if (this.ComicBook1.get(i).getTitle().toLowerCase().contentEquals(query.toLowerCase())) {
                    showASpecificBook(i);
                }
            }
        } else {
            System.out.println("The comic book with title is \"" + query + "\" can't be found!");
        }
    }

    public void showASpecificBook(int index) {
        System.out.printf("| %3d |", index + 1);
        System.out.printf(" %3d |", this.ComicBook1.get(index).getID());
        System.out.printf(" %-19s|", this.ComicBook1.get(index).getTitle());
        System.out.printf("%8.1f|", this.ComicBook1.get(index).getBookRentalPrice());
        System.out.printf(" %-19s|", this.ComicBook1.get(index).getAuthor());
        System.out.printf("   %4d |", this.ComicBook1.get(index).getVolume());
        System.out.println();
        System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
    }

    public boolean searchComicBookByAuthor(String query) {
        for (int i = 0; i < this.numberOfBook; i++) {
            if (this.ComicBook1.get(i).getAuthor().toLowerCase().contentEquals(query.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void showSearchComicBookByAuthor(String query) {
        if (searchComicBookByAuthor(query)) {
            System.out.println("+-----+-----+-----------------------------+--------------------+--------+");
            System.out.println("|                   TABLE OF COMIC BOOKS SEARCH BY AUTHOR               |");
            System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
            System.out.println("| No. | ID  | Title              | Price  | Author             | Volume |");
            System.out.println("+-----+-----+--------------------+--------+--------------------+--------+");
            for (int i = 0; i < this.numberOfBook; i++) {
                if (this.ComicBook1.get(i).getAuthor().toLowerCase().contentEquals(query.toLowerCase())) {
                    showASpecificBook(i);
                }
            }
        } else {
            System.out.println("The comic book with author is \"" + query + "\" can't be found!");
        }
    }

    public void updateRentalPriceOfComicBook(int id, double newPrice) {
       // if (idExisted(id) == true) {
            this.ComicBook1.get(indexOfId(id)).setBookRentalPrice(newPrice);
        //} else {
        //    System.out.println("The comic book has the ID is \"" + id + "\" does not exist!");
        //}
    }

    public int indexOfId(int id) {
        for (int i = 0; i < this.numberOfBook; i++) {
            if (id == this.ComicBook1.get(i).getID()) {
                return i;
            }
        }
        return -1;
    }

    public boolean idExisted(int id) {
        for (int i = 0; i < this.numberOfBook; i++) {
            if (id == this.ComicBook1.get(i).getID()) {
                return true;
            }
        }
        return false;
    }

    public boolean bookExisted(String title, String author, int volume) {
        for (int i = 0; i < this.numberOfBook; i++) {
            if (       this.ComicBook1.get(i).getTitle().toLowerCase().contentEquals(title.toLowerCase())
                    && this.ComicBook1.get(i).getAuthor().toLowerCase().contentEquals(author.toLowerCase())
                    && volume == this.ComicBook1.get(i).getVolume()) {
                return true;
            }
        }
        return false;
    }

    public void deleteAComicBook(int id) {
        //if (indexOfId(id) != -1) {
            this.ComicBook1.remove(indexOfId(id));
            --this.numberOfBook;
            this.showComicBooks();
        //} else {
         //   System.out.println("The comic book has the ID is \"" + id + "\" does not exist!");
        //}
    }

    public void saveComicBook() throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(Integer.toString(numberOfBook));
        writer.write("\n");
        for (int i = 0; i < this.numberOfBook; i++) {
            writer.write(Integer.toString(this.ComicBook1.get(i).getID()));
            writer.write("\n");

            writer.write(this.ComicBook1.get(i).getTitle());
            writer.write("\n");

            writer.write(this.ComicBook1.get(i).getAuthor());
            writer.write("\n");

            writer.write(Integer.toString(this.ComicBook1.get(i).getVolume()));
            writer.write("\n");

            writer.write(Double.toString(this.ComicBook1.get(i).getBookRentalPrice()));
            writer.write("\n");
        }
        System.out.println("Data is saving...Done!");
        writer.close();
    }
    
    public int getNumeberOfBook(int number){
        number=this.numberOfBook;
        return number;
    }
}
