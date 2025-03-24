package ru.matveyelovskikh.naujavaspring.platforms;

import ru.matveyelovskikh.naujavaspring.interfaces.InputOutput;

import java.util.Scanner;

/**
 * Консоль
 */
public class Console implements InputOutput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String input() {
        return scanner.nextLine();
    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }
}
