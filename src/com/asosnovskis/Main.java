package com.asosnovskis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static IBAN_Validator iban_validator = new IBAN_Validator();

    public static void main(String[] args) throws IOException {
        boolean quit = false;

        while (!quit) {
            printInstructions();
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    IBAN_ValidationFromConsoleInput();
                    break;
                case "2":
                    IBAN_ValidationFromFile();
                    break;
                case "3":
                    quit = true;
                    System.out.println("Application finished.");
                    break;
                default:
                    System.out.println("Invalid choice, try again\n");
            }
        }
    }

    public static void printInstructions() {
        System.out.println("\nPress:");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - To enter IBAN by hand.");
        System.out.println("\t 2 - To check IBAN from file.");
        System.out.println("\t 3 - To quit the application.\n");
    }

    public static void IBAN_ValidationFromConsoleInput() {
        System.out.println("Please enter IBAN: ");
        if (iban_validator.IBAN_Validation(scanner.nextLine()))
            System.out.println("\033[32m IBAN is valid. \033[0m");
        else {
            System.out.println("\033[31m IBAN is invalid. \033[0m");
        }
    }

    public static void IBAN_ValidationFromFile() throws IOException {
        boolean quit = false;

        while (!quit) {
            System.out.println("Please enter path to the file:");
            System.out.println("(eg.: C:\\Users\\User\\Desktop)");
            String pathToFolder = scanner.nextLine();
            System.out.println("Please enter file name:");
            System.out.println("(eg.: File.txt)");
            String fileName = scanner.nextLine();

            if (isPathToFileOrFilenameEmpty(pathToFolder, fileName)) {
                System.out.println("Input field/fields empty, try again.\n");
            } else {
                String pathToFile = pathToFileCreator(pathToFolder, fileName);
                if (doesPathToFileExist(pathToFile)) {
                    System.out.println("File in path found.");
                    FileOperator fileOperator = new FileOperator(pathToFolder, fileName);
                    fileOperator.IBAN_CheckFromFile();
                    System.out.println("Output file " + fileOperator.getNameOfOutputFile() + " generated.");
                    quit = true;
                } else {
                    System.out.println("Error in path or filename, try again.\n");
                }
            }
        }
    }

    private static boolean isPathToFileOrFilenameEmpty(String pathToFolder, String fileName) {
        return (whitespaceRemove(pathToFolder).isEmpty()) || (whitespaceRemove(fileName).isEmpty());
    }

    private static String whitespaceRemove(String string) {
        String stringWithoutWhitespaces = string.replaceAll("\\s+", "");
        return stringWithoutWhitespaces;
    }

    private static String pathToFileCreator(String pathToFolder, String fileName) {
        String pathToFolderWithoutWhitespaces = whitespaceRemove(pathToFolder);
        String nameOfFileWithoutWhitespaces = whitespaceRemove(fileName);
        String pathToFile = pathToFolderWithoutWhitespaces + System.getProperty("file.separator") + nameOfFileWithoutWhitespaces;
        return pathToFile;
    }

    private static boolean doesPathToFileExist(String pathToFile) {
        return Files.exists(Paths.get(pathToFile));
    }
}