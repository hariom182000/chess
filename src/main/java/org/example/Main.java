package org.example;

import services.GameMaster;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        final Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your player A name: ");
        final String playerAName = scanner.nextLine();
        System.out.print("Enter your player B name: ");
        final String playerBName = scanner.nextLine();

        try {
            final GameMaster gameMaster = new GameMaster(playerAName, playerBName);
            gameMaster.play();
        } catch (final Exception e) {
            System.out.print("");
        } finally {
            scanner.close();
        }

    }
}