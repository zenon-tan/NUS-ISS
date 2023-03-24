import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static int numOfPeople = 0;

    public static void main(String[] args) throws IOException {

        String csvFile = args[0];
        String emailFile = args[1];
        createEmail(readCSV(csvFile), readFormat(emailFile));

        
    }

    public static HashMap<String, List<String>> readCSV(String fileName) throws IOException {

        HashMap<String, List<String>> userMap = new HashMap<String, List<String>>();

        // Read the csv file
        try {

            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);

            List<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();

            String line = bf.readLine();
            String[] splitHeader = line.split(",");

            // Read the header

            for(int i = 0; i < splitHeader.length; i++) {

                ArrayList<String> interiorList = new ArrayList<String>();
                interiorList.add(splitHeader[i]);
                tempList.add(interiorList);

                System.out.println(interiorList);

            }

            //System.out.println(tempList);

            // Read the rest

            String otherLine;
            while(null != (otherLine = bf.readLine())) {

                numOfPeople++;

                String[] splitString = otherLine.split(",");
                for(int i = 0; i < splitString.length; i++) {

                    tempList.get(i).add(splitString[i]);
                    // Use the headers as the key for the hashmap
                    userMap.put("<<%s>>".formatted(tempList.get(i).get(0)), tempList.get(i));
                    
                }



            }

            bf.close();
            fr.close();

            //System.out.println("Num of ppl: " + numOfPeople);
            //System.out.println(userMap);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userMap;

    }

    public static String readFormat(String fileName) throws IOException {

        StringBuilder formatString = new StringBuilder();

        try {

            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String line;

            while(null != (line = bf.readLine())) {

                formatString.append(line + "\n");

            }

            bf.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return formatString.toString();
    }

    public static void createEmail(HashMap<String, List<String>> userMap, String emailFormat) {


        for(int i = 1; i < numOfPeople; i++) {
            String emailString = emailFormat;

            for(String key : userMap.keySet()) {

                emailString = emailString.replace(key, userMap.get(key).get(i)).replace("\\n", "\n\n");

            }

            System.out.println(emailString);

        }

    }

}
