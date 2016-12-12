package sample;

import java.io.*;

/**
 * Created by Hasan Hüseyin PAY on 6.12.2016.
 */
public class FileOperation {


    public String[] read(String path) {
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
        BufferedReader br = null;
        String[] Dizi = new String[1];
        int count = 0;
        String standart = null;
        try {

            String sCurrentLine;
            String Control;

            br = new BufferedReader(new FileReader(path));
            Control = br.readLine();
          /*	while ((sCurrentLine = br.readLine()) != null) {
                  System.out.println(sCurrentLine);
  			}*/

            if (Control.contains("A")) {
                count = 1;
            }
            if (Control.contains("B")) {
                count = 2;
            }

            if (Control.contains("C")) {
                count = 3;
            }
            if (Control.contains("D")) {   /// BU KODLARLA FONKSIYONDA EN FAZLA KAC ELEMANLI INPUT OLDUGUNA BAK
                count = 4;
            }

            String[] kelime = null;
            String[] kelime2 = null;
            String control2 = Control;
            kelime = control2.split("\\=");
            kelime2 = kelime[1].split("\\+");
        	/* for (int i = 0; i < kelime.length; i++) {
				System.out.println(kelime[i]);
			}*/
            for (int i = 0; i < kelime2.length; i++) {
                System.out.println(kelime2[i]); // splıt edılen fonksıyonlar kelıme 2 de burdan standarta cevır
            }


            if (count == 1) {
                for (int i = 0; i < kelime2.length; i++) {
                    standart.concat(kelime2[i]);           // TEK ELEMANLILARI AYNEN YAZ EM FAZLA TEK ELEMAN VARSA
                }
            }
            if (count == 2) {
                for (int i = 0; i < kelime2.length; i++) {
                    if (kelime2[i].equals("A")) {
                        standart.concat("A.B");
                        standart.concat("A.B'");
                    }
                    if (kelime2[i].equals("A'")) {
                        standart.concat("A'.B");                // 2 ELEMANLI INPUTLARDA TEK ELEMANLARI 2 ELEMANLI STANDART HALE GETIR
                        standart.concat("A'.B'");
                    }
                    if (kelime2[i].equals("B")) {
                        standart.concat("A.B");
                        standart.concat("A.'B");
                    }
                    if (kelime2[i].equals("B'")) {
                        standart.concat("A.B'");
                        standart.concat("A'B'");
                    }
                    if (kelime2[i].equals("A.B")) {
                        standart.concat("A.B");
                    }
                    if (kelime2[i].equals("A.B'")) {
                        standart.concat("A.B'");
                    }
                    if (kelime2[i].equals("A'.B")) {
                        standart.concat("A'.B");
                    }
                    if (kelime2[i].equals("A'.B'")) {
                        standart.concat("A'.B'");
                    }
                }
            }


            if (count == 3) {
                for (int i = 0; i < kelime2.length; i++) {
                    if (kelime2[i].equals("A")) {
                        standart.concat("A.B.C");
                        standart.concat("A.B'.C");
                        standart.concat("A.B.C'");
                        standart.concat("A.B'.C'");
                    }
                    if (kelime2[i].equals("A'")) {
                        standart.concat("A'.B.C");
                        standart.concat("A'.B'.C");
                        standart.concat("A'.B.C'");
                        standart.concat("A'.B'.C'");
                    }
                    if (kelime2[i].equals("B")) {
                        standart.concat("A'.B.C");
                        standart.concat("A.B.C");
                        standart.concat("A'.B.C'");
                        standart.concat("A.B.C'");
                    }
                    if (kelime2[i].equals("B'")) {
                        standart.concat("A'.B'.C");
                        standart.concat("A.B'.C");
                        standart.concat("A'.B'.C'");
                        standart.concat("A.B'.C'");
                    }
                    if (kelime2[i].equals("C")) {
                        standart.concat("A'.B.C");
                        standart.concat("A.B.C");
                        standart.concat("A'.B'.C");
                        standart.concat("A.B'.C");
                    }
                    if (kelime2[i].equals("C")) {
                        standart.concat("A'.B.C'");
                        standart.concat("A.B.C'");
                        standart.concat("A'.B'.C'");  // 3 ELAMANLI IMPUTLARDA TEK ELEMANLARI 3 LU YAP
                        standart.concat("A.B'.C'");
                    }
                    if (kelime2[i].equals("A.B")) {
                        standart.concat("A.B.C");
                        standart.concat("A.B.C'");
                    }
                    if (kelime2[i].equals("A.B'")) {
                        standart.concat("A.B'.C");
                        standart.concat("A.B'.C'");
                    }
                    if (kelime2[i].equals("A.C")) {
                        standart.concat("A.B.C");
                        standart.concat("A.B'.C");
                    }
                    if (kelime2[i].equals("A.C'")) {
                        standart.concat("A.B.C'");
                        standart.concat("A.B'.C'");
                    }
                    if (kelime2[i].equals("A'.B")) {
                        standart.concat("A'.B.C");
                        standart.concat("A'.B.C'");
                    }
                    if (kelime2[i].equals("A'.B'")) {
                        standart.concat("A'.B'.C");
                        standart.concat("A'.B'.C'");
                    }
                    if (kelime2[i].equals("A'.C")) {
                        standart.concat("A'.B.C");
                        standart.concat("A'.B'.C");
                    }
                    if (kelime2[i].equals("A'.C'")) {
                        standart.concat("A'.B.C'");
                        standart.concat("A'.B'.C'");
                    }
                    if (kelime2[i].equals("B.C")) {
                        standart.concat("A.B.C");
                        standart.concat("A'.B.C");
                    }
                    if (kelime2[i].equals("B.C'")) {
                        standart.concat("A.B.C'");
                        standart.concat("A'.B.C'");
                    }
                    if (kelime2[i].equals("B'.C")) {
                        standart.concat("A.B'.C");
                        standart.concat("A'.B'.C");
                    }
                    if (kelime2[i].equals("B'.C'")) {
                        standart.concat("A.B'.C'");
                        standart.concat("A'.B'.C'");    // 3 ELEMANLI IMPUTLARDA 2 ELEMANLILARI 3 LU YAP
                    }
                    if (kelime2[i].equals("A.B.C")) {
                        standart.concat("A.B.C");
                    }
                    if (kelime2[i].equals("A.B.C'")) {
                        standart.concat("A.B.C'");
                    }
                    if (kelime2[i].equals("A.B'.C")) {
                        standart.concat("A.B'.C");
                    }
                    if (kelime2[i].equals("A.B'.C'")) {
                        standart.concat("A.B'.C'");
                    }
                    if (kelime2[i].equals("A'.B.C")) {
                        standart.concat("A'.B.C");
                    }
                    if (kelime2[i].equals("A'.B.C'")) {
                        standart.concat("A'.B.C'");
                    }
                    if (kelime2[i].equals("A'.B'.C'")) {
                        standart.concat("A.B.C");
                    }
                    if (kelime2[i].equals("A'.B'.C")) {    // 3 ELEMANLILARI AYNEN EKLE
                        standart.concat("A.B'.C");
                    }

                }
            }


            boolean t = true;
            for (int i = 0; i < Control.length() - 1; i++) {
                if (!(Control.substring(i, i + 1).equals("A")) && !(Control.substring(i, i + 1).equals("B")) &&
                        !(Control.substring(i, i + 1).equals("C")) && !(Control.substring(i, i + 1).equals("D")) &&
                        !(Control.substring(i, i + 1).equals("F")) && !(Control.substring(i, i + 1).equals("=")) &&
                        !(Control.substring(i, i + 1).equals(".")) && !(Control.substring(i, i + 1).equals("'")) &&
                        !(Control.substring(i, i + 1).equals(" ")) && !(Control.substring(i, i + 1).equals("+"))) {
                    // BURDA HATALI FONKSIYON GIRERSE NULL DONDURMEK ICIN KONTROL YAP
                    t = false;
                    break;

                }

            }
            if (t == true) {
                Dizi[0] = standart;
                System.out.println(standart);   // STANDART HALINI DIZNIN ILK ELEMANINA AT STANDARTI YAZDIR
                String k = Dizi[0];

                return Dizi; // k;
            } else if (t == false) {
                return null;   // YANLIS FONKSIYONSA NULL DONDUR
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: FileOperation > functionRead()");
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
