package repository;

import model.Skill;

import java.io.*;
import java.util.ArrayList;

class UtilsFileProcessor {
    private String fileName;

    UtilsFileProcessor(String fileName) {
        this.fileName = fileName;
    }

    ArrayList<String> readAllFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<String>();
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        return list;
    }

    void writeAllFile(ArrayList<String> newList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String line : newList) {
            writer.write(line);
        }
        writer.close();
    }
}
