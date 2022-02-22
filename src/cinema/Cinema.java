package cinema;

import java.io.PrintStream;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static boolean isWorking = true;
    static int rows = 0;
    static int seats = 0;
    static int userRow = 0;
    static int userSeat = 0;
    static int costTicket = 10;
    static int costForBackRows = 8;
    static int currentIncome = 0;
    static int totalIncome = 0;
    static final char FREE_SEAT = 'S';
    static final char BUSY_SEAT = 'B';
    static boolean isSameSeat = false;
    static int countBusySeats = 0;
    static char[][] arrayCinemaSeats;

    public static void main(String[] args) {
        start();

    }

    public static void start() {
        creatCinemaMap();
        System.out.println();
        while (isWorking) {
            getMenu();
            getInPut(scanner.nextInt());
        }
    }

    public static void getMenu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static void getInPut(int flagSwitch) {
        switch (flagSwitch) {
            case 1:
                getCinemaMap();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                getStatistic();
                break;
            case 0:
                isWorking = !isWorking;
                break;
            default:
                System.out.println("Invalid input, try again!");


        }
    }

    public static void creatCinemaMap() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        arrayCinemaSeats = new char[rows][seats];
        for (int i = 0; i < arrayCinemaSeats.length; i++) {
            for (int j = 0; j < arrayCinemaSeats[i].length; j++) {
                arrayCinemaSeats[i][j] = FREE_SEAT;
                if (i < 4) {
                    totalIncome += costTicket;
                } else {
                    totalIncome += costForBackRows;
                }
            }
        }
    }

    public static void buyTicket() {
        while (!isSameSeat) {
            System.out.println("\nEnter a row number:");
            userRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            userSeat = scanner.nextInt();
            if (userRow > rows || userSeat > seats) {
                System.out.println("Wrong input!");
            } else if (arrayCinemaSeats[userRow - 1][userSeat - 1] == BUSY_SEAT) {
                System.out.println("\nThat ticket has already been purchased!\n");
            } else {
                isSameSeat = true;
            }
        }
        isSameSeat = false;

        for (int i = 0; i < arrayCinemaSeats.length; i++) {
            for (int j = 0; j < arrayCinemaSeats[i].length; j++) {
                if (i == userRow - 1 && j == userSeat - 1) {
                    if (arrayCinemaSeats[i][j] != BUSY_SEAT) {
                        arrayCinemaSeats[i][j] = BUSY_SEAT;
                        countBusySeats++;
                        getCost();
                        if (i < 4) {
                            currentIncome += costTicket;
                        } else {
                            currentIncome += costForBackRows;
                        }
                    }
                }
            }
        }
    }


    public static void getCinemaMap() {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int k = 1; k <= seats; k++) {
            System.out.print(k + " ");
        }
        System.out.println();
        for (int i = 0; i < arrayCinemaSeats.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < arrayCinemaSeats[i].length; j++) {
                System.out.print(" " + arrayCinemaSeats[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void getCost() {
        PrintStream printStream = userRow <= 4 ? System.out.printf("\nTicket price: $%d\n\n", costTicket) : System.out.printf("Ticket price: $%d\n\n", costForBackRows);
    }

    public static void getStatistic() {
        double percentage = (double) countBusySeats / (rows * seats) * 100 ;
        System.out.printf("\nNumber of purchased tickets: %d\n" +
                "Percentage: %.2f%%\n" +
                "Current income: $%d\n" +
                "Total income: $%d\n", countBusySeats, percentage, currentIncome, totalIncome);
        System.out.println();
    }
}