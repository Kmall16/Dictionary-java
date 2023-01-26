/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook1shop;

import java.util.Scanner;

/**
 *
 * @author MY LAPTOP
 */
public class ComicBook1Shop {

    /**
     * @param args the command line arguments
     */
    private static ComicBook1Management fa;

    public static int menu() {
        System.out.println("<<<COMIC BOOK SHOP>>>");
        System.out.println("1. Add new comic book.");
        System.out.println("2. Search book by title.");
        System.out.println("3. Search book by author.");
        System.out.println("4. Update book's rental price.");
        System.out.println("5. Delete a comic book.");
        System.out.println("6. Exit.");
        int n = 0, number =0;
        boolean isFalse = false;
        int numberOfBook = fa.getNumeberOfBook(number);
        do {
            do{
            try {
                System.out.print("Please choose a function from 1 to 6: ");
                Scanner sc = new Scanner(System.in);
                n = Integer.parseInt(sc.nextLine());
                if ((n > 0 && n < 7)) {
                    isFalse = true;
                    if (numberOfBook==0 && (n==2 || n==3 || n==4 || n==5)){
                        System.out.println("There is no comic book! Please add new comic book before choosing other functions!");
                    }
                } else {
                    System.out.println("Invalid values! Please try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid values! Please try again!");
            }
            }while(numberOfBook==0 && (n==2 || n==3 || n==4 || n==5));
        } while (isFalse == false);
        return n;
    }

    public static void main(String[] args) {
        try {
            fa = new ComicBook1Management("src/data/ComicBooks.txt");
            fa.loadComicBook();
            fa.showComicBooks();
            Scanner sc = new Scanner(System.in);
            int function;
            do {
                function = menu();
                switch (function) {
                    case 1:
                        System.out.println("<<<ADD A NEW COMIC BOOK>>>");
                        int id = 0;
                        String title = "",
                         author = "";
                        int volume = 0;
                        double bookRentalPrice = 0;
                        boolean isFalse = false;
                        do {
                            do {
                                try {
                                    System.out.print("Please enter ID of the comic book: ");
                                    id = Integer.parseInt(sc.nextLine());
                                    if (id > 0) {
                                        isFalse = true;
                                        if (fa.idExisted(id)) {
                                            System.out.println("The ID existed!");
                                        }
                                    } else {
                                        System.out.println("Invalid values! ID must be an integer greater than 0!");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid values! ID must be an integer greater than 0!");
                                }
                            } while (isFalse == false);
                        } while (fa.idExisted(id));

                        do {
                            do {
                                System.out.print("Please enter title of the comic book: ");
                                title = sc.nextLine();
                                if (title.equals("")) {
                                    System.out.println("Title of comic book can't be empty!");
                                }
                            } while (title.equals(""));

                            do {
                                System.out.print("Please enter the author of the conmic book: ");
                                author = sc.nextLine();
                                if (author.equals("")) {
                                    System.out.println("Author of the comic book can't be empty!");
                                }
                            } while (author.equals(""));

                            do {
                                try {
                                    isFalse = false;
                                    System.out.print("Please enter the volume of the comic book: ");
                                    volume = Integer.parseInt(sc.nextLine());
                                    if (volume > 0) {
                                        isFalse = true;
                                    } else {
                                        System.out.println("Invalid values! Volume of the comic book must be an integer greater than 0!");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid values! Volume of the comic book must be an integer greater than 0!");
                                }
                            } while (isFalse == false);
                            if (fa.bookExisted(title, author, volume)) {
                                System.out.println("The comic book exited!");
                            }
                        } while (fa.bookExisted(title, author, volume));

                        do {
                            try {
                                isFalse = false;
                                System.out.print("Please enter the rental price of the comic book: ");
                                bookRentalPrice = Double.parseDouble(sc.nextLine());
                                if (bookRentalPrice > 0) {
                                    isFalse = true;
                                } else {
                                    System.out.println("Invalid values. Rental price of the comic book must be a number greater than 0!");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid values. Rental price of the comic book must be a number greater than 0!");
                            }
                        } while (isFalse == false);
                        fa.addNewComicBook(id, title, bookRentalPrice, author, volume);
                        break;

                    case 2:
                        System.out.println("<<<SEARCH COMIC BOOK BY TITLE>>>");
                        String key;
                        System.out.print("Please enter a tilte to find the comic book: ");
                        key = sc.nextLine();
                        fa.searchComicBookByTitle(key.toLowerCase());
                        fa.showSearchComicBookByTitle(key.toLowerCase());
                        break;

                    case 3:
                        System.out.println("<<<SEARCH COMIC BOOK BY AUTHOR>>>");
                        System.out.print("Please enter a author to find the comic book: ");
                        key = sc.nextLine();
                        fa.searchComicBookByAuthor(key.toLowerCase());
                        fa.showSearchComicBookByAuthor(key.toLowerCase());
                        break;

                    case 4:
                        System.out.println("<<<UPDATE THE RENTAL PRICE OF THE COMIC BOOK>>>");

                        id = 0;
                        isFalse = false;
                        bookRentalPrice = 0;
                        do {
                            do {
                                try {
                                    System.out.print("Please enter the ID of the comic book that you want to update rental price: ");
                                    id = Integer.parseInt(sc.nextLine());
                                    if (id > 0) {
                                        isFalse = true;
                                        if (!fa.idExisted(id)) {
                                            System.out.println("The comic book has ID is \"" + id + "\" did not exist!");
                                        }
                                    } else {
                                        System.out.println("Invalid values! ID must be an integer greater than 0!");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid values! ID must be an integer greater than 0!");
                                }
                            } while (isFalse == false);
                        } while (!fa.idExisted(id));
                        do {
                            try {
                                isFalse = false;
                                System.out.print("Please enter the new retal price for the comic book: ");

                                bookRentalPrice = Double.parseDouble(sc.nextLine());
                                if (bookRentalPrice > 0) {
                                    isFalse = true;
                                } else {
                                    System.out.println("Invalid values. Rental price of the comic book must be a number greater than 0!");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid values. Rental price of the comic book must be a number greater than 0!");
                            }
                        } while (isFalse == false);

                        //bookRentalPrice = sc.nextDouble();
                        fa.updateRentalPriceOfComicBook(id, bookRentalPrice);
                        fa.showComicBooks();
                        break;

                    case 5:
                        System.out.println("<<<DELETE A COMIC BOOK>>>");
                        id = 0;
                        isFalse = false;
                        do {
                            do {
                                try {
                                    System.out.print("Please enter the ID of the comic book that you want to delete: ");
                                    id = Integer.parseInt(sc.nextLine());
                                    if (id > 0) {
                                        isFalse = true;
                                        if (!fa.idExisted(id)) {
                                            System.out.println("The comic book has ID is \"" + id + "\" did not exist!");
                                        }
                                    } else {
                                        System.out.println("Invalid values! ID must be an integer greater than 0!");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid values! ID must be an integer greater than 0!");
                                }
                            } while (isFalse == false);
                        } while (!fa.idExisted(id));
                        fa.deleteAComicBook(id);

                    case 6:
                        System.out.println("Thanks for using our service!");
                }
            } while (function != 6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fa.saveComicBook();
            } catch (Exception e) {
                System.out.println("Can't save the data");
            }
        }
    }

}
