package jdbc.schoolAplication;

import java.io.IOException;

import jdbc.schoolAplication.util.DataGenerator;
import jdbc.schoolAplication.util.TableCreator;

public class Main {

    public static void main(String[] args) throws IOException {
        new TableCreator();
        DataGenerator dataGenerator = new DataGenerator();
        
        dataGenerator.generateData();
    }
}
