package com.asosnovskis;

import java.io.FileWriter;

public class FileOperator {
    private IBAN_Validator validator = new IBAN_Validator();

    private java.io.FileReader fr;
    private FileWriter fw;
    private java.io.BufferedReader br;
    private String nameOfOutputFile;

    public FileOperator(String pathToFolder, String fileName)
            throws java.io.IOException {
        nameOfOutputFile = fileName.replace(".txt", ".out");

        fr = new java.io.FileReader(pathToFolder + System.getProperty("file.separator") + fileName);
        fw = new FileWriter(pathToFolder + System.getProperty("file.separator") + nameOfOutputFile);
        br = new java.io.BufferedReader(fr);
    }

    public String getNameOfOutputFile() {
        return nameOfOutputFile;
    }

    public void IBAN_CheckFromFile()
            throws java.io.IOException {
        String lineBeingChecked;
        while ((lineBeingChecked = br.readLine()) != null) {
            if (validator.IBAN_Validation(lineBeingChecked)) lineBeingChecked = lineBeingChecked + ";true\n";
            else
                lineBeingChecked = lineBeingChecked + ";false\n";
            fw.write(lineBeingChecked);
        }
        fw.close();
    }
}


