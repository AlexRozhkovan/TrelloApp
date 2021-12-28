package spd.trello.service;

import spd.trello.repository.CardRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {
    DataSource dataSource;


    public MenuService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void processMenu() throws SQLException {
        CardService cardService = new CardService(new CardRepository(dataSource));
       /* CardlistService cardlistService = new CardlistService();*/
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (!input.equals("0")) {
            System.out.println("press 1 - go to Card menu");
            System.out.println("press 2 - go to CardList menu");
            System.out.println("press 0 - exit");
            input = scanner.nextLine();
            if (input.equals("1")) {
                String cardMenuinput = "";
                while (!cardMenuinput.equals("0")) {
                    System.out.println("Card Menu:");
                    System.out.println("press 1 - create card");
                    System.out.println("press 2 - show all cards");
                    System.out.println("press 0 - exit to main menu");
                    cardMenuinput = scanner.nextLine();
                    if (cardMenuinput.equals("1")) {
                        cardService.print(cardService.create(scanner));
                    }
                    if (cardMenuinput.equals("2")) {
                        System.out.println(cardService.findAll());
                    }
                }
            }
            /*if (input.equals("2")) {
                String cardlistMenuinput = "";
                while (!cardlistMenuinput.equals("0")) {
                    System.out.println("Cardlist Menu:");
                    System.out.println("press 1 - create cardlist");
                    System.out.println("press 0 - exit to main menu");
                    cardlistMenuinput = scanner.nextLine();
                    if (cardlistMenuinput.equals("1")) {
                        cardlistService.print(cardlistService.create(scanner));
                    }
                }
            }*/
        }
    }
}
