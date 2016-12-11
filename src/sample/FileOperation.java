package sample;

import java.io.*;

/**
 * Created by Hasan Hüseyin PAY on 6.12.2016.
 */
public class FileOperation {

    private int[] functionRead(String path) {
        return null;
    }

    public int[] read(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            System.out.println("file not found");
            return null;
        }
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".be")) {
                return functionRead(path + listOfFiles[i].getName());
            } else if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".tt")) {
                return tableRead(path + listOfFiles[i].getName());
            }
        }
        return null;
    }

    private int[] tableRead(String path) {

        String line;
        try {
            InputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            if ((line = br.readLine()) == null) {
                br.close();
                return null;
            }

            int len = line.length();
            int[] table = new int[len];
            try {
                switch (len) {
                    case 5:
                        if (line.charAt(0) == 'A' && line.charAt(1) == ','
                                && line.charAt(2) == 'B' && line.charAt(3) == ';'
                                && line.charAt(4) == 'F') {
                            return checker(br, 2);
                        } else return null;

                    case 7:
                        if (line.charAt(0) == 'A' && line.charAt(1) == ','
                                && line.charAt(2) == 'B' && line.charAt(3) == ','
                                && line.charAt(2) == 'C' && line.charAt(3) == ';'
                                && line.charAt(4) == 'F') {
                            return checker(br, 3);
                        } else return null;

                    case 9:
                        if (line.charAt(0) == 'A' && line.charAt(1) == ','
                                && line.charAt(2) == 'B' && line.charAt(3) == ','
                                && line.charAt(2) == 'C' && line.charAt(3) == ','
                                && line.charAt(2) == 'D' && line.charAt(3) == ';'
                                && line.charAt(4) == 'F') {
                            return checker(br, 4);
                        } else return null;

                    default:
                        return null;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (
                IOException e) {
            System.out.println(e.toString());
            System.out.println("Error: FileOperation > tableRead()");
        }
        return null;
    }


    private int[] checker(BufferedReader br, int len) {
        try {
            int counter;
            int rows = (int) Math.pow(2, len); // stun sayısına göre satır sayısı
            int[] table = new int[rows];

            for (int i = 0; i < rows; i++) {//satırları dolaşıyor
                String[] splited = br.readLine().split(";");
                counter = 0;
                if (splited[1].equals("1") || splited[1].equals("0")) {
                    for (int j = len - 1; j >= 0; j--) {//stunları dolaşıyor
                        if ((i / (int) Math.pow(2, j)) % 2 == Character.getNumericValue(splited[0].charAt(counter)))  //truth table'ın doğruluğu
                            table[i] = Integer.parseInt(splited[1]);
                        else return null;

                        counter++;
                        if (counter < len && splited[0].charAt(counter) != ',')
                            return null;
                        counter++;
                    }
                } else return null;
            }

            if (br.readLine() != null)
                return null;

            return table;
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error: FileOperation > checker()");
            return null;
        }
    }
}
