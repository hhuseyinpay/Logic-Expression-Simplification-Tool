package sample;


/**
 * Created by aaa on 12.12.2016.
 */
public class Converter {

    public String ttTObe(String[] tt) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String temp = "";

            int len = (int) (Math.log((double) tt.length) / Math.log(2));
            int rows = (int) Math.pow(2, len);

            for (int i = 0; i < rows; i++) {
                if (tt[i].equals("1")) {
                    for (int j = len - 1; j >= 0; j--) {
                        temp += ((i / (int) Math.pow(2, j)) % 2);
                    }
                    switch (len) {
                        case 2:
                            stringBuilder.append(temp.charAt(0) == '0' ? "A'." : "A.");
                            stringBuilder.append(temp.charAt(1) == '0' ? "B'+" : "B+");
                            break;
                        case 3:
                            stringBuilder.append(temp.charAt(0) == '0' ? "A'." : "A.");
                            stringBuilder.append(temp.charAt(1) == '0' ? "B'." : "B.");
                            stringBuilder.append(temp.charAt(2) == '0' ? "C'+" : "C+");
                            break;
                        case 4:
                            stringBuilder.append(temp.charAt(0) == '0' ? "A'." : "A.");
                            stringBuilder.append(temp.charAt(1) == '0' ? "B'." : "B.");
                            stringBuilder.append(temp.charAt(2) == '0' ? "C'." : "C.");
                            stringBuilder.append(temp.charAt(3) == '0' ? "D'+" : "D+");
                            break;
                        default:
                            return null;
                    }
                }
                temp = "";
            }
            return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error: Converter > ttTObe()");
            return null;
        }
    }

    public KMNode[][] beTOkm(String[] function) {

        KMNode[][] kMap;

        if (function[0].contains("D"))
            kMap = new KMNode[4][4];
        else if (function[0].contains("C"))
            kMap = new KMNode[4][2];
        else
            kMap = new KMNode[2][2];

        for (int i = 0; i < kMap.length; i++) {
            for (int j = 0; j < kMap[0].length; j++) {
                kMap[i][j] = new KMNode("0");
            }
        }

        int len = function.length;
        String[] splited = function[0].split("\\.");
        int slen = splited.length;
        int B;
        int D;
        for (int i = 0; i < len; i++) {
            splited = function[i].split("\\.");

            switch (slen) {
                case 2:
                    kMap[splited[0].length() == 2 ? 0 : 1][splited[1].length() == 2 ? 0 : 1] = new KMNode("1");
                    break;

                case 3:
                    B = (splited[1].length() == 2 ? 0 : 1);
                    kMap[splited[0].length() == 2 ? B : (3 - B)][splited[2].length() == 2 ? 0 : 1] = new KMNode("1");
                    break;

                case 4:
                    B = (splited[1].length() == 2 ? 0 : 1);
                    D = (splited[3].length() == 2 ? 0 : 1);
                    kMap[splited[0].length() == 2 ? B : (3 - B)][splited[2].length() == 2 ? D : (3 - D)] = new KMNode("1");
                    break;
            }
        }

        for (int i = 0; i < kMap.length; i++) {
            for (int j = 0; j < kMap[0].length; j++) {
                System.out.print(kMap[i][j].getKey());
                System.out.print(" ");
            }
            System.out.println("");
        }
        return kMap;
    }


    public String[] beTOtt(String be) {
        int count = 0;
        if (be.contains("D")) {
            count = 4;
        } else if (be.contains("C")) {
            count = 3;
        } else if (be.contains("B")){
            count = 2;
        }

        String[] temp = be.split("\\+");
        String[] F = null;
        switch (count) {
            case 2:
                for (int i = 0; i < temp.length; i++) {
                    F = new String[4];
                    if (F[0].equals("0")) F[0] = temp[i].equals("A'.B'") ? "1" : "0";
                    if (F[1].equals("0")) F[1] = temp[i].equals("A'.B") ? "1" : "0";
                    if (F[2].equals("0")) F[2] = temp[i].equals("A.B'") ? "1" : "0";
                    if (F[3].equals("0")) F[3] = temp[i].equals("A.B") ? "1" : "0";
                }
                break;
            case 3:
                F = new String[8];
                for (int i = 0; i < F.length; i++) {
                    F[i] = "0";
                }
                for (int i = 0; i < temp.length; i++) {
                    if (F[0].equals("0")) F[0] = temp[i].equals("A'.B'.C'") ? "1" : "0";
                    if (F[1].equals("0")) F[1] = temp[i].equals("A'.B'.C") ? "1" : "0";
                    if (F[2].equals("0")) F[2] = temp[i].equals("A'.B.C'") ? "1" : "0";
                    if (F[3].equals("0")) F[3] = temp[i].equals("A'.B.C") ? "1" : "0";
                    if (F[4].equals("0")) F[4] = temp[i].equals("A.B'.C'") ? "1" : "0";
                    if (F[5].equals("0")) F[5] = temp[i].equals("A.B'.C") ? "1" : "0";
                    if (F[6].equals("0")) F[6] = temp[i].equals("A.B.C'") ? "1" : "0";
                    if (F[7].equals("0")) F[7] = temp[i].equals("A.B.C") ? "1" : "0";
                }
                break;
            case 4:
                for (int i = 0; i < temp.length; i++) {
                    F = new String[16];
                    F[0] = temp[i].equals("A'.B'.C'.D'") ? "1" : "0";
                    F[1] = temp[i].equals("A'.B'.C'.D") ? "1" : "0";
                    F[2] = temp[i].equals("A'.B'.C.D'") ? "1" : "0";
                    F[3] = temp[i].equals("A'.B'.C.D") ? "1" : "0";
                    F[4] = temp[i].equals("A'.B.C'.D'") ? "1" : "0";
                    F[5] = temp[i].equals("A'.B.C'.D") ? "1" : "0";
                    F[6] = temp[i].equals("A'.B.C.D'") ? "1" : "0";
                    F[7] = temp[i].equals("A'.B.C.D") ? "1" : "0";
                    F[8] = temp[i].equals("A.B'.C'.D'") ? "1" : "0";
                    F[9] = temp[i].equals("A.B'.C'.D") ? "1" : "0";
                    F[10] = temp[i].equals("A.B'.C.D'") ? "1" : "0";
                    F[11] = temp[i].equals("A'.B'.C.D") ? "1" : "0";
                    F[12] = temp[i].equals("A.B.C'.D'") ? "1" : "0";
                    F[13] = temp[i].equals("A.B.C'.D") ? "1" : "0";
                    F[14] = temp[i].equals("A.B.C.D'") ? "1" : "0";
                    F[15] = temp[i].equals("A.B.C.D") ? "1" : "0";

                }
                break;
        }


        return F;
    }

    public BENode[] beTObaNode(String[] expression) {

        BENode[] BENode = new BENode[expression.length];
        int len = expression.length;
        for (int i = 0; i < len; i++) {
            String[] splited = expression[i].split("\\.");
            switch (splited.length) {
                case 2:
                    BENode[i] = new BENode(splited[0].length() == 1 ? 1 : 0, splited[1].length() == 1 ? 1 : 0);
                    break;
                case 3:
                    BENode[i] = new BENode(splited[0].length() == 1 ? 1 : 0, splited[1].length() == 1 ? 1 : 0,
                            splited[2].length() == 1 ? 1 : 0);
                    break;
                case 4:
                    BENode[i] = new BENode(splited[0].length() == 1 ? 1 : 0, splited[1].length() == 1 ? 1 : 0,
                            splited[2].length() == 1 ? 1 : 0, splited[3].length() == 1 ? 1 : 0);
                    break;
                default:
                    break;
            }
        }
        return BENode;
    }


    // this function has been written by Tolga Atış.
    public String[] toSOP(String Control) {

        String[] Dizi = new String[1];
        int count = 0;
        String standart = " ";
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
        //kelime = control2.split("\\=");
        kelime2 = control2.split("\\+");
            /* for (int i = 0; i < kelime.length; i++) {
                System.out.println(kelime[i]);
			}*/
        for (int i = 0; i < kelime2.length; i++) {
            System.out.println(kelime2[i]); // splıt edılen fonksıyonlar kelıme 2 de burdan standarta cevır
        }


        if (count == 1) {
            for (int i = 0; i < kelime2.length; i++) {

                standart+=(kelime2[i] + "+");           // TEK ELEMANLILARI AYNEN YAZ EM FAZLA TEK ELEMAN VARSA
            }
        }

        else
        if (count ==2) {
            for (int i = 0; i < kelime2.length; i++) {

                if (kelime2[i].equals("A") ) {
                    standart+=("A.B+A.B'");
                }
                else if (kelime2[i].equals("A'") ) {
                    standart+=("A'.B+A'.B'");                // 2 ELEMANLI INPUTLARDA TEK ELEMANLARI 2 ELEMANLI STANDART HALE GETIR
                }
                else if (kelime2[i].equals("B") ) {
                    standart+=("A.B+A'.B");
                }
                else if (kelime2[i].equals("B'") ) {
                    standart+=("A.B'+A'.B'");
                }
                else if (kelime2[i].equals("A.B") ) {
                    standart+=("A.B");

                }
                else if (kelime2[i].equals("A.B'") ) {
                    standart+=("A.B'");

                }
                else if (kelime2[i].equals("A'.B") ) {
                    standart+=("A'.B");

                }
                else if (kelime2[i].equals("A'.B'") ) {
                    standart+=("A'.B'");

                }
            }

        }


        else
        if (count ==3) {
            for (int i = 0; i < kelime2.length; i++) {
                if (kelime2[i].equals("A") ) {
                    standart+=("A.B.C+");
                    standart+=("A.B'.C+");

                    standart+=("A.B.C'+");
                    standart+=("A.B'.C'+");
                }
                else if (kelime2[i].equals("A'") ) {

                    standart+=("A'.B.C+");
                    standart+=("A'.B'.C+");

                    standart+=("A'.B.C'+");
                    standart+=("A'.B'.C'+");

                }
                else if (kelime2[i].equals("B") ) {

                    standart+=("A'.B.C+");
                    standart+=("A.B.C+");

                    standart+=("A'.B.C'+");
                    standart+=("A.B.C'+");

                }
                else if (kelime2[i].equals("B'") ) {

                    standart+=("A'.B'.C+");
                    standart+=("A.B'.C+");

                    standart+=("A'.B'.C'+");
                    standart+=("A.B'.C'+");

                }
                else if (kelime2[i].equals("C") ) {

                    standart+=("A'.B.C+");
                    standart+=("A.B.C+");

                    standart+=("A'.B'.C+");
                    standart+=("A.B'.C+");

                }
                else if (kelime2[i].equals("C'") ) {

                    standart+=("A'.B.C'+");

                    standart+=("A.B.C'+");


                    standart+=("A'.B'.C'+");  // 3 ELAMANLI IMPUTLARDA TEK ELEMANLARI 3 LU YAP

                    standart+=("A.B'.C'+");

                }
                else if (kelime2[i].equals("A.B") ) {
                    standart+=("A.B.C+");
                    standart+=("A.B.C'+");

                }
                else if (kelime2[i].equals("A.B'") ) {
                    standart+=("A.B'.C+");

                    standart+=("A.B'.C'+");

                }
                else if (kelime2[i].equals("A.C") ) {
                    standart+=("A.B.C+");
                    standart+=("A.B'.C+");
                }
                else if (kelime2[i].equals("A.C'") ) {
                    standart+=("A.B.C'+");

                    standart+=("A.B'.C'+");

                }
                else if (kelime2[i].equals("A'.B") ) {
                    standart+=("A'.B.C+");
                    standart+=("A'.B.C'+");

                }
                else if (kelime2[i].equals("A'.B'") ) {
                    standart+=("A'.B'.C+");

                    standart+=("A'.B'.C'+");

                }
                else if (kelime2[i].equals("A'.C") ) {
                    standart+=("A'.B.C+");
                    standart+=("A'.B'.C+");
                }
                else if (kelime2[i].equals("A'.C'") ) {
                    standart+=("A'.B.C'+");

                    standart+=("A'.B'.C'+");

                }
                else if (kelime2[i].equals("B.C") ) {
                    standart+=("A.B.C+");

                    standart+=("A'.B.C+");

                }
                else if (kelime2[i].equals("B.C'") ) {
                    standart+=("A.B.C'+");

                    standart+=("A'.B.C'+");

                }
                else if (kelime2[i].equals("B'.C") ) {
                    standart+=("A.B'.C+");

                    standart+=("A'.B'.C+");

                }
                else if (kelime2[i].equals("B'.C'") ) {
                    standart+=("A.B'.C'+");

                    standart+=("A'.B'.C'+");
// 3 ELEMANLI IMPUTLARDA 2 ELEMANLILARI 3 LU YAP
                }
                else if (kelime2[i].equals("A.B.C") ) {
                    standart+=("A.B.C+");

                }
                else if (kelime2[i].equals("A.B.C'") ) {
                    standart+=("A.B.C'+");

                }
                else if (kelime2[i].equals("A.B'.C") ) {
                    standart+=("A.B'.C+");

                }
                else if (kelime2[i].equals("A.B'.C'") ) {
                    standart+=("A.B'.C'+");

                }
                else if (kelime2[i].equals("A'.B.C") ) {
                    standart+=("A'.B.C+");

                }
                else if (kelime2[i].equals("A'.B.C'") ) {
                    standart+=("A'.B.C'+");

                }
                else if (kelime2[i].equals("A'.B'.C'") ) {
                    standart+=("A'.B'.C'+");

                }
                else if (kelime2[i].equals("A'.B'.C") ) {    // 3 ELEMANLILARI AYNEN EKLE
                    standart+=("A'.B'.C+");

                }

            }

        }

        if (count ==4) {
            for (int i = 0; i < kelime2.length; i++) {
                if (kelime2[i].equals("A") ) {
                    standart+=("A.B.C.D+A.B.C.D'+A.B.C'.D+A.B.C'.D'+A.B'.C.D+A.B'.C.D'+A.B'.C'.D+A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'") ) {
                    standart+=("A'.B.C.D+A'.B.C.D'+A'.B.C'.D+A'.B.C'.D'+A'.B'.C.D+A'.B'.C.D'+A'.B'.C'.D+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("B") ) {
                    standart+=("A.B.C.D+A.B.C.D'+A.B.C'.D+A.B.C'.D'+A'.B.C.D+A'.B.C.D'+A'.B.C'.D+A'.B.C'.D'+");

                }
                else if (kelime2[i].equals("B'") ) {
                    standart+=("A.B'.C.D+A.B'.C.D'+A.B'.C'.D+A.B'.C'.D'+A'.B'.C.D+A'.B'.C.D'+A'.B'.C'.D+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("C") ) {
                    standart+=("A.B.C.D+A.B.C.D'+A.B'.C.D+A.B'.C.D'+A'.B.C.D+A'.B.C.D'+A'.B'.C.D+A'.B'.C.D'+");

                }
                else if (kelime2[i].equals("C'") ) {
                    standart+=("A.B.C'.D+A.B.C'.D'+A.B'.C'.D+A.B'.C'.D'+A'.B.C'.D+A'.B.C'.D'+A'.B'.C'.D+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("D") ) {
                    standart+=("A.B.C.D+A.B.C'.D+A.B'.C.D+A.B'.C'.D+A'.B.C.D+A'.B.C'.D+A'.B'.C.D+A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("D'") ) {
                    standart+=("A.B.C.D'+A.B.C'.D'+A.B'.C.D'+A.B'.C'.D'+A'.B.C.D'+A'.B.C'.D'+A'.B'.C.D'+A'.B'.C'.D'+");
// 4 IMPUT 1 ELEMAN STANDART FORM YAPMA
                }
                else if (kelime2[i].equals("A.B") ) {
                    standart+=("A.B.C.D+A.B.C.D'+A.B.C'.D+A.B.C'.D'+");

                }
                else if (kelime2[i].equals("A.B'") ) {
                    standart+=("A.B'.C.D+A.B'.C.D'+A.B'.C'.D+A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'.B") ) {
                    standart+=("A'.B.C.D+A'.B.C.D'+A'.B.C'.D+A'.B.C'.D'+");

                }
                else if (kelime2[i].equals("A'.B'") ) {
                    standart+=("A'.B'.C.D+A'.B'.C.D'+A'.B'.C'.D+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A.C") ) {
                    standart+=("A.B.C.D+A.B.C.D'+A.B'.C.D+A.B'.C.D'+");

                }
                else if (kelime2[i].equals("A'.C") ) {
                    standart+=("A'.B.C.D+A'.B.C.D'+A'.B'.C.D+A'.B'.C.D'+");

                }
                else if (kelime2[i].equals("A.C'") ) {
                    standart+=("A.B.C'.D+A.B.C'.D'+A.B'.C'.D+A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'.C'") ) {
                    standart+=("A'.B.C'.D+A'.B.C'.D'+A'.B'.C'.D+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A.D") ) {
                    standart+=("A.B.C.D+A.B.C'.D+A.B'.C.D+A.B'.C'.D+");

                }
                else if (kelime2[i].equals("A'.D") ) {
                    standart+=("A'.B.C.D+A'.B.C'.D+A'.B'.C.D+A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("A.D'") ) {
                    standart+=("A.B.C.D'+A.B.C'.D'+A.B'.C.D'+A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'.D'") ) {
                    standart+=("A'.B.C.D'+A'.B.C'.D'+A'.B'.C.D'+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("B.C") ) {
                    standart+=("A.B.C.D+A.B.C.D'+A'.B.C.D+A'.B.C.D'+");

                }
                else if (kelime2[i].equals("B'.C") ) {
                    standart+=("A.B'.C.D+A.B'.C.D'+A'.B'.C.D+A'.B'.C.D'+");

                }
                else if (kelime2[i].equals("B.C'") ) {
                    standart+=("A.B.C'.D+A.B.C'.D'+A'.B.C'.D+A'.B.C'.D'+");

                }
                else if (kelime2[i].equals("B'.C'") ) {
                    standart+=("A.B'.C'.D+A.B'.C'.D'+A'.B'.C'.D+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("B.D") ) {
                    standart+=("A.B.C.D+A.B.C'.D+A'.B.C.D+A'.B.C'.D+");

                }
                else if (kelime2[i].equals("B'.D") ) {
                    standart+=("A.B'.C.D+A.B'.C'.D+A'.B'.C.D+A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("B'.D'") ) {
                    standart+=("A.B'.C.D'+A.B'.C'.D'+A'.B'.C.D'+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("B.D'") ) {
                    standart+=("A.B.C.D'+A.B.C'.D'+A'.B.C.D'+A'.B.C'.D'+");

                }
                else if (kelime2[i].equals("C.D") ) {
                    standart+=("A.B.C.D+A.B'.C.D+A'.B.C.D+A'.B'.C.D+");

                }
                else if (kelime2[i].equals("C.D'") ) {
                    standart+=("A.B.C.D'+A.B'.C.D'+A'.B.C.D'+A'.B'.C.D'+");

                }
                else if (kelime2[i].equals("C'.D") ) {
                    standart+=("A.B.C'.D+A.B'.C'.D+A'.B.C'.D+A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("C'.D'") ) {
                    standart+=("A.B.C'.D'+A.B'.C'.D'+A'.B.C'.D'+A'.B'.C'.D'+");
// 4 IMPUTLUDA 2 ELEMANLARIN STANDART FORMA DONUSU
                }
                else if (kelime2[i].equals("A.B.C") ) {
                    standart+=("A.B.C.D+A.B.C.D'+");

                }
                else if (kelime2[i].equals("A.B'.C") ) {
                    standart+=("A.B'.C.D+A.B'.C.D'+");

                }
                else if (kelime2[i].equals("A.B'.C'") ) {
                    standart+=("A.B'.C'.D+A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'.B.C") ) {
                    standart+=("A'.B.C.D+A'.B.C.D'+");

                }
                else if (kelime2[i].equals("A'.B'.C") ) {
                    standart+=("A'.B'.C.D+A'.B'.C.D'+");

                }
                else if (kelime2[i].equals("A'.B'.C'") ) {
                    standart+=("A'.B'.C'.D+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A.B.D") ) {
                    standart+=("A.B.C.D+A.B.C'.D+");

                }
                else if (kelime2[i].equals("A.B'.D") ) {
                    standart+=("A.B'.C.D+A.B'.C'.D+");

                }
                else if (kelime2[i].equals("A.B'.D'") ) {
                    standart+=("A.B'.C.D'+A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'.B.D") ) {
                    standart+=("A'.B.C.D+A'.B.C'.D+");

                }
                else if (kelime2[i].equals("A'.B'.D") ) {
                    standart+=("A'.B'.C.D+A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("A'.B'.D'") ) {
                    standart+=("A'.B'.C.D'+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A.C.D") ) {
                    standart+=("A.B.C.D+A.B'.C.D+");

                }
                else if (kelime2[i].equals("A.C'.D") ) {
                    standart+=("A.B.C'.D+A.B'.C'.D+");

                }
                else if (kelime2[i].equals("A.C'.D'") ) {
                    standart+=("A.B.C'.D'+A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'.C.D") ) {
                    standart+=("A'.B.C.D+A'.B'.C.D+");

                }
                else if (kelime2[i].equals("A'.C'.D") ) {
                    standart+=("A'.B.C'.D+A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("A'.C'.D'") ) {
                    standart+=("A'.B.C'.D'+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("B.C.D") ) {
                    standart+=("A.B.C.D+A'.B.C.D+");

                }
                else if (kelime2[i].equals("B.C.D'") ) {
                    standart+=("A.B.C.D'+A'.B.C.D'+");

                }
                else if (kelime2[i].equals("B.C'.D") ) {
                    standart+=("A.B.C'.D+A'.B.C'.D+");

                }
                else if (kelime2[i].equals("B'.C.D") ) {
                    standart+=("A.B'.C.D+A'.B'.C.D+");

                }
                else if (kelime2[i].equals("B.C'.D'") ) {
                    standart+=("A.B.C'.D'+A'.B.C'.D'+");

                }
                else if (kelime2[i].equals("B'.C'.D") ) {
                    standart+=("A.B'.C'.D+A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("B'.C'.D'") ) {
                    standart+=("A.B'.C'.D'+A'.B'.C'.D'+");

                }
                else if (kelime2[i].equals("B'.C.D'") ) {
                    standart+=("A.B'.C.D'+A'.B'.C.D'+");
// 4 LU INPUTLARDA 3 ELEMANLILARIN STANDART FORMU
                }
                else if (kelime2[i].equals("A.B.C.D") ) {
                    standart+=("A.B.C.D+");

                }
                else if (kelime2[i].equals("A'.B.C.D") ) {
                    standart+=("A'.B.C.D+");

                }
                else if (kelime2[i].equals("A.B.C.D'") ) {
                    standart+=("A.B.C.D'+");

                }
                else if (kelime2[i].equals("A'.B.C.D'") ) {
                    standart+=("A'.B.C.D'+");

                }
                else if (kelime2[i].equals("A.B.C'.D") ) {
                    standart+=("A.B.C'.D+");

                }
                else if (kelime2[i].equals("A'.B.C'.D") ) {
                    standart+=("A'.B.C'.D+");

                }

                else if (kelime2[i].equals("A.B'.C.D") ) {
                    standart+=("A.B'.C.D+");

                }
                else if (kelime2[i].equals("A'.B'.C.D") ) {
                    standart+=("A'.B'.C.D+");

                }
                else if (kelime2[i].equals("A.B.C'.D'") ) {
                    standart+=("A.B.C'.D'+");

                }
                else if (kelime2[i].equals("A'.B.C'.D'") ) {
                    standart+=("A'.B.C'.D'+");

                }
                else if (kelime2[i].equals("A.B'.C.D'") ) {
                    standart+=("A.B'.C.D'+");

                }
                else if (kelime2[i].equals("A'.B'.C.D'") ) {
                    standart+=("A'.B'.C.D'+");

                }
                else if (kelime2[i].equals("A.B'.C'.D") ) {
                    standart+=("A.B'.C'.D+");

                }
                else if (kelime2[i].equals("A'.B'.C'.D") ) {
                    standart+=("A'.B'.C'.D+");

                }
                else if (kelime2[i].equals("A.B'.C'.D'") ) {
                    standart+=("A.B'.C'.D'+");

                }
                else if (kelime2[i].equals("A'.B'.C'.D'") ) {
                    standart+=("A'.B'.C'.D'+");

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
            Dizi[0] = standart.substring(0, standart.length() - 2);
            System.out.println(standart);   // STANDART HALINI DIZNIN ILK ELEMANINA AT STANDARTI YAZDIR
            String k = Dizi[0];

            return Dizi; // k;
        } else if (t == false) {
            return null;   // YANLIS FONKSIYONSA NULL DONDUR
        }
        return null;

    }

}
