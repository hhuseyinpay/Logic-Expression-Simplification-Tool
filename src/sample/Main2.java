package sample;

public class Main2 {


    public static void main(String[] args) {
//        String [] expression = {"A.B.C", "A'.B'.C'", "A'.B.C", "A'.B.C'"};
//
//        BooleanExpression simplification = new BooleanExpression(expression);
//
//       // System.out.println(simplification.simplify());
//        printTruthTable(4);
//        FileOperation fo = new FileOperation();
//        String [] denemeRead = fo.read("/Users/aaa/IdeaProjects/karnaughMap ver2/src/sample/");
//
//        for (int i = 0; i < denemeRead.length; i++) {
//            System.out.println(denemeRead[i]);
//        }
//
//        System.out.println("\n\n\n\n\n\n");
//
//        Converter convert = new Converter();
//


        String[] arr1 = {"A.B.C.D", "A'.B'.C'.D'", "A'.B.C.D'", "A'.B.C'.D", "A'.B'.C'.D"
                , "A'.B.C'.D'", "A.B.C'.D'", "A.B.C'.D", "A.B'.C'.D'", "A.B'.C'.D"};

        String[] arr2 = {"A'.B'.C'.D'", "A.B'.C.D'", "A.B'.C'.D'", "A'.B'.C.D'"};
        String[] arr3 = {"A'.B'.C'.D'", "A.B'.C'.D'", "A'.B.C'.D'", "A.B.C'.D'", "A'.B.C.D'", "A.B.C.D'", "A'.B.C'.D", "A'.B.C.D", "A.B.C.D", "A.B.C'.D"};
//        System.out.println(convert.ttTObe(arr));

        String[] arr4 = {"A'.B'.C'.D", "A.B'.C.D'", "A.B.C'.D'", "A'.B'.C.D'"};

        String[] ar1 = {"A'.B'.C'", "A'.B'.C", "A.B'.C'", "A.B'.C"};

        String[] ar2 = {"A'.B'.C'", "A'.B.C'", "A.B'.C'"};

        String[] ar3 = {"A'.B'.C'", "A'.B'.C", "A.B'.C'", "A.B'.C", "A'.B.C'","A'.B.C"};

        String[] ar4 = {"A'.B'.C'", "A'.B'.C", "A.B'.C'", "A.B'.C", "A'.B.C'","A'.B.C","A.B.C'", "A.B.C"};

        String[] a1 = {"A'.B'", "A'.B"};
        String[] a2 = {"A'.B'", "A'.B","A.B'"};


        KarnaughMap km = new KarnaughMap();
        Converter converter = new Converter();

        System.out.println(km.simplify(converter.beTOkm(a2)));
        // String temp = "";
//        int i = 0;
//        int j = 3;
//        temp += (i == 0 ? "A'" : (i == 1 ? "B" : (i == 2 ? "A" : "B'")));
//        temp += "." + (j == 0 ? "C'" : (j == 1 ? "D" : (j == 2 ? "C" : "D'")));
//        System.out.println(temp);

    }

    private static void printTruthTable(int n) {
        int rows = (int) Math.pow(2, n);

        for (int i = 0; i < rows; i++) {
            int counter = 0;
            for (int j = n - 1; j >= 0; j--) {
                System.out.print((i / (int) Math.pow(2, j)) % 2 + " ");
                counter += 2;
            }
            System.out.println();
        }
    }

}
