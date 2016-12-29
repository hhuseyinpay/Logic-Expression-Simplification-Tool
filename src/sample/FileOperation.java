package sample;


import java.io.*;

/**
 * Created by Hasan Hüseyin PAY on 6.12.2016.
 */
public class FileOperation {


    public String[] read(String path) {

        if (path.endsWith(".be")) {
            return functionRead(path);
        } else if (path.endsWith(".tt")) {
            return tableRead(path);
        } else return null;
    }

    private String[] tableRead(String path) {

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
                                && line.charAt(4) == 'C' && line.charAt(5) == ';'
                                && line.charAt(6) == 'F') {
                            return checker(br, 3);
                        } else return null;

                    case 9:
                        if (line.charAt(0) == 'A' && line.charAt(1) == ','
                                && line.charAt(2) == 'B' && line.charAt(3) == ','
                                && line.charAt(4) == 'C' && line.charAt(5) == ','
                                && line.charAt(6) == 'D' && line.charAt(7) == ';'
                                && line.charAt(8) == 'F') {
                            return checker(br, 4);
                        } else return null;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("Error: FileOperation > tableRead()");
        }
        return null;
    }


    private String[] checker(BufferedReader br, int len) {
        try {
            int counter;
            int rows = (int) Math.pow(2, len); // stun sayısına göre satır sayısı
            String[] table = new String[rows];

            for (int i = 0; i < rows; i++) {//satırları dolaşıyor
                String[] splited = br.readLine().split(";");
                counter = 0;
                if (splited[1].equals("1") || splited[1].equals("0")) {
                    for (int j = len - 1; j >= 0; j--) {//stunları dolaşıyor
                        if ((i / (int) Math.pow(2, j)) % 2 == Character.getNumericValue(splited[0].charAt(counter)))  //truth table'ın doğruluğu
                            table[i] = splited[1];
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




    // this function has been written by Tolga Atış.
    public String[] functionRead(String path) {

        String Control="";

        try {
            InputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            if ((Control = br.readLine()) == null) {
                br.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: FileOperation > functionRead()");
        }
        Converter converter = new Converter();
        return converter.toSOP(Control);
    }






}
