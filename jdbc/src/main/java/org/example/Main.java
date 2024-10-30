package org.example;

import org.example.dao.UserDao;
import org.example.dao.CardDao;
import org.example.model.User;
import org.example.model.Card;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        CardDao cardDao = new CardDao();
        Scanner scanner = new Scanner(System.in);
        int userId = -1;
        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("0. Exit");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Sign Up:");
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setPhone_number(phoneNumber);
                    newUser.setPassword(password);
                    newUser.setEmail(email);

                    try {
                        userDao.signUp(newUser);
                        System.out.println("amaliyot muvaffaqiyatli yakunlandi!");
                    } catch (SQLException e) {
                        System.out.println("xatolik yuz berdi: " + e.getMessage());
                    }
                }

                case 2 -> { // Sign In
                    System.out.println("Sign In:");
                    System.out.print("Enter name: ");
                    String signInUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String signInPassword = scanner.nextLine();

                    try {
                        boolean success = userDao.signIn(signInUsername, signInPassword);
                        if (success) {
                            System.out.println("Amaliyot muvaffaqiyatli yakunlandi!");
                            userId = getUserId(signInUsername, userDao);

                            while (true) {
                                System.out.println("1. Add Card");
                                System.out.println("2. My Cards");
                                System.out.println("3. Delete Card");
                                System.out.println("0. Exit");

                                int postSignInChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (postSignInChoice) {
                                    case 1 -> {
                                        System.out.println("Add Card:");
                                        System.out.print("Enter Card Owner Name: ");
                                        String ownerName = scanner.nextLine();
                                        System.out.print("Enter Card Number: ");
                                        String cardNumber = scanner.nextLine();
                                        System.out.print("Enter Card Password: ");
                                        String cardPassword = scanner.nextLine();
                                        System.out.print("Enter Expiration Date: ");
                                        String expireDate = scanner.nextLine();

                                        Card card = new Card();
                                        card.setOwnerName(ownerName);
                                        card.setCardNumber(cardNumber);
                                        card.setPassword(cardPassword);
                                        card.setExpireDate(expireDate);
                                        card.setUserId(userId);

                                        try {
                                            cardDao.createCard(card);
                                            System.out.println("karta muvaffaqiyatli qo'shildi!");
                                        } catch (SQLException e) {
                                            System.out.println("Karta qo‘shishda xatolik yuz berdi: " + e.getMessage());
                                        }
                                    }

                                    case 2 -> {
                                        try {
                                            List<Card> cards = cardDao.getMyCards(userId);
                                            System.out.println("kartalat ro'yxati:");
                                            for (Card c : cards) {
                                                System.out.println("ID: " + c.getId() + ", Owner: " + c.getOwnerName() + ", Card Number: " + c.getCardNumber());
                                            }
                                        } catch (SQLException e) {
                                            System.out.println("Kartalarni olishda xatolik yuz berdi: " + e.getMessage());
                                        }
                                    }

                                    case 3 -> {
                                        System.out.print("karta idsini kiriting: ");
                                        int cardId = readInt(scanner);

                                        try {
                                            if (cardDao.isCardOwnedByUser(cardId, userId)) {
                                                cardDao.deleteCard(cardId,userId);
                                                System.out.println("karta muvaffaqiyatli o'chirildi!");
                                            } else {
                                                System.out.println("ushbu idli karta mavjud emas.");
                                            }
                                        } catch (SQLException e) {
                                            System.out.println("karta o'chirishda xatolik yuz berdi: " + e.getMessage());
                                        }
                                    }


                                    case 0 -> {
                                        System.out.println("chiqish...");
                                        scanner.close();
                                        return;
                                    }

                                    default -> System.out.println("qayta urinib koʻring.");
                                }
                            }
                        } else {
                            System.out.println("name yoki password xato.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error during sign in: " + e.getMessage());
                    }
                }

                case 0 -> {
                    System.out.println("Chiqish...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("qayta urinib koʻring.");
            }
        }
    }

    private static int getUserId(String username, UserDao userDao) {
        try {
            return userDao.getUserIdByUsername(username);
        } catch (SQLException e) {
            System.out.println("Foydalanuvchi identifikatorini olishda xatolik yuz berdi: " + e.getMessage());
            return -1;
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("butun son kiriting.");
                scanner.nextLine();
            }
        }
    }
}
