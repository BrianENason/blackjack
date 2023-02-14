import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

public class writeToFile {

    public static File createFile() {
        try {
            File myObj = new File("deckIterations.csv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                return myObj;
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public static void writeDeckToFile(ArrayList<Card> deck, File myObj) {
        try {
            FileWriter myWriter = new FileWriter(myObj, true);

            myWriter.write(String.valueOf(deck));
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}

