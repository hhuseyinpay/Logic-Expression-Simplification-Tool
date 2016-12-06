package sample;

import java.io.*;

/**
 * Created by Hasan Hüseyin PAY on 6.12.2016.
 */
public class FileOperation {

    private int[] functionRead(){
        return null;
    }

    public int[] read(String directory) {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            System.out.println("file not found");
            return null;
        }
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".be")) {
                return functionRead(directory + listOfFiles[i].getName());
            } else if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".tt")) {
                return tableRead(directory + listOfFiles[i].getName());
            }
        }
        return null;
    }

    private int[] tableRead(String path) {

        String[] table;
        String line;
        try {
            InputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            if ((line = br.readLine()) == null) {
                br.close();
                return null;
            }

            int counter = 0;
            try {
                switch (line.length()) {
                    case 5:

                        if (line.charAt(0) == 'A' && line.charAt(1) == ','
                                && line.charAt(2) == 'B' && line.charAt(3) == ';'
                                && line.charAt(4) == 'F') {

                            while ((line = br.readLine()) != null) {


                            }

                            break;
                        } else return null;

                    case 7:


                    case 9:

                    default:
                        return null;
                }


            } catch (Exception e) {
                return null;
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("Error: FileOperation > tableRead()");
        }

        return null;
    }



}
