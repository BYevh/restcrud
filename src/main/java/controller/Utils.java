package controller;

import model.Account;
import model.AccountStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

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

    Account createAccount (Integer id){
        Account account;
        switch (id){
            case 1: {
                account = new Account(AccountStatus.ACTIVE);
                break;
            }
            case 2: {
                account = new Account(AccountStatus.BANNED);
                break;
            }
            case 3: {
                account = new Account(AccountStatus.DELETED);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        return account;
    }

}
