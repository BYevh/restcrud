package ua.epam.crud.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UtilsController {

    String inputData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


}
