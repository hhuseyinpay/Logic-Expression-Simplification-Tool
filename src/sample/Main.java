package ver5;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {

    private String PATH;
    String[] read;
    int len = 0;
    private FileChooser fileChooser;
    private File file;
    Stage window;
    Scene mainScene, readScene, manuelScene, manuelTTScene, manuelBEscene;
    Button readButton, manuelButton;
    FileOperation fo;
    Converter convert = new Converter();
    ArrayList<String> fC = new ArrayList<>();
    String css = this.getClass().getResource("application.css").toExternalForm();
    Boolean isBE = false;
    int inputs = 3;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    public void start(Stage primaryStage) throws FileNotFoundException {
        window = primaryStage;
        fo = new FileOperation();
        //Ana menu ekranı
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Truth Table", "*.tt"),
                new FileChooser.ExtensionFilter("Boolean Expression", "*.be"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        readButton = new Button("Read File");

        manuelButton = new Button("Manuel Enter");
        manuelButton.setOnAction(e -> {
            Label input = new Label("How much inputs you want to enter?");
            input.setAlignment(Pos.CENTER);
            ToggleGroup group = new ToggleGroup();
            RadioButton rb1 = new RadioButton("2");
            rb1.setToggleGroup(group);
            RadioButton rb2 = new RadioButton("3");
            rb2.setToggleGroup(group);
            RadioButton rb3 = new RadioButton("4");
            rb3.setToggleGroup(group);
            HBox inputsLayOut = new HBox();
            inputsLayOut.getChildren().addAll(rb1,rb2,rb3);
            VBox topManuel = new VBox();
            topManuel.getChildren().addAll(input,inputsLayOut);
            topManuel.setAlignment(Pos.CENTER);
            window.setScene(manuelScene);
        });
        HBox layOut = new HBox();
        layOut.setSpacing(30.0);
        layOut.setAlignment(Pos.CENTER);
        layOut.getChildren().addAll(readButton, manuelButton);
        mainScene = new Scene(layOut, 800, 800);
        mainScene.getStylesheets().add(css);
        layOut.getStyleClass().add("mainScene");
        readButton.getStyleClass().add("readButton");
        manuelButton.getStyleClass().add("manuelButton");
        int columnNum = 9;


        //Read File

        TextArea ttable = new TextArea();
        ttable.setEditable(false);
        TextArea fColumn = new TextArea();
        fColumn.setPrefColumnCount(columnNum);
        fColumn.appendText("F\n");
        fColumn.setMaxWidth(10);

        Button ttfrombe = new Button("   > >   ");
        ttfrombe.getStyleClass().add("button");

        Button befromtt = new Button("   < <   ");
        befromtt.getStyleClass().add("button");

        Button returnMain = new Button("Main Menu");
        returnMain.getStyleClass().add("button");


        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(30);
        buttons.getChildren().addAll(ttfrombe, befromtt);

        TextArea booleanA = new TextArea();
        booleanA.setMaxSize(400, 50);
        booleanA.getStyleClass().add("BA");

        TextArea simpleEx = new TextArea();
        simpleEx.setMaxSize(400, 50);
        simpleEx.getStyleClass().add("BA");

        Button simplify = new Button("Simplify");
        simplify.getStyleClass().add("button");
        VBox simpLayOut = new VBox();
        simpLayOut.setAlignment(Pos.CENTER);
        simpLayOut.getChildren().addAll(booleanA, simplify, simpleEx);
        simpLayOut.setSpacing(30);

        readButton.setOnAction(e -> {
            file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                PATH = file.getAbsolutePath().toString();    //to find path
            }


            if (PATH.endsWith(".be")) {
                read = fo.read(PATH);
                int lenbe = read.length;
                for (int i = 0; i < lenbe; i++) {
                    booleanA.appendText(read[i] + "+");
                    if (read[i].contains("D")) len = 16;
                    else if (read[i].contains("C")) len = 8;
                    else if (read[i].contains("B")) len = 4;
                }
                isBE = true;
                ttfrombe.setVisible(false);
                befromtt.setVisible(true);
            } else if (PATH.endsWith(".tt")) {
                read = fo.read(PATH);
                len = read.length;
                for (int i = 0; i < len; i++) {
                    fColumn.appendText(read[i] + "\n"); //Read Truth Table
                }
                befromtt.setVisible(false);
                ttfrombe.setVisible(true);
            }

            if (len == 4) {
                ttable.setText("A B \n"
                        + "0 0\n"
                        + "0 1\n"
                        + "1 0\n"
                        + "1 1\n");
                ttable.getStyleClass().add("ttable2");
                fColumn.getStyleClass().add("ttable2");

            } else if (len == 8) {
                ttable.setText("A B C\n"
                        + "0 0 0\n"
                        + "0 0 1\n"
                        + "0 1 0\n"
                        + "0 1 1\n"
                        + "1 0 0\n"
                        + "1 0 1\n"
                        + "1 1 0\n"
                        + "1 1 1");
                ttable.getStyleClass().add("ttable3");
                fColumn.getStyleClass().add("ttable3");
            } else if (len == 16) {
                ttable.setText("A B C D\n"
                        + "0 0 0 0\n"
                        + "0 0 0 1\n"
                        + "0 0 1 0\n"
                        + "0 0 1 1\n"
                        + "0 1 0 0\n"
                        + "0 1 0 1\n"
                        + "0 1 1 0\n"
                        + "0 1 1 1\n"
                        + "1 0 0 0\n"
                        + "1 0 0 1\n"
                        + "1 0 1 0\n"
                        + "1 0 1 1\n"
                        + "1 1 0 0\n"
                        + "1 1 0 1\n"
                        + "1 1 1 0\n"
                        + "1 1 1 1");
                ttable.getStyleClass().add("ttable4");
                fColumn.getStyleClass().add("ttable4");

            } else {
                fColumn.setText("ERROR");
            }


            ttable.setPrefRowCount(len);


            window.setScene(readScene);
        });


        //buttons actions
        befromtt.setOnAction(e -> {
            int len = convert.beTOtt(booleanA.getText()).length;
            for (int i = 0; i < len; i++) {
                fColumn.appendText(convert.beTOtt(booleanA.getText())[i] + "\n");
            }

        });
        simplify.setOnAction(e -> {

            BESimplification simplification = new BESimplification(isBE ? read[0].split("\\+") : convert.ttTObe(read).split("\\+"));
            isBE=false;

            for (String sim : simplification.simplify()) {
                simpleEx.appendText((sim.endsWith(".") ? sim.subSequence(0, sim.length() - 2) : sim) + "+");
                System.out.println(sim);
            }
        });

        ttfrombe.setOnAction(e -> {           //truth table dan boolean expression a >>
            booleanA.setText("F = " + convert.ttTObe(read));
        });

        returnMain.setOnAction(e -> {
            ttable.clear();
            ttable.getStyleClass().clear();
            fColumn.clear();
            fColumn.getStyleClass().clear();
            fColumn.setText("F\n");
            booleanA.clear();
            simpleEx.clear();
            window.setScene(mainScene);
        });

        HBox readEnd = new HBox();
        readEnd.setAlignment(Pos.CENTER);
        readEnd.getChildren().addAll(ttable, fColumn, buttons, simpLayOut, returnMain);
        readEnd.getStyleClass().add("readScene");
        readScene = new Scene(readEnd, 800, 800);
        readScene.getStylesheets().add(css);

        //manuel enter scene
        Label input = new Label("How much inputs you want to enter?");
        input.setAlignment(Pos.CENTER);
        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("2");
        rb1.setToggleGroup(group);
        RadioButton rb2 = new RadioButton("3");
        rb2.setToggleGroup(group);
        RadioButton rb3 = new RadioButton("4");
        rb3.setToggleGroup(group);
        //for manuel truth table



        HBox inputsLayOut = new HBox();
        inputsLayOut.getChildren().addAll(rb1,rb2,rb3);
        inputsLayOut.setAlignment(Pos.CENTER);
        inputsLayOut.setSpacing(15);

        VBox topManuel = new VBox();
        topManuel.getChildren().addAll(input,inputsLayOut);
        topManuel.setAlignment(Pos.CENTER);
        topManuel.setSpacing(15);

        Button withTT = new Button("With Truth Table");
        withTT.getStyleClass().add("readButton");
        Button withBB = new Button("With Boolean Algebra");
        withBB.getStyleClass().add("readButton");
        withTT.setOnAction(e -> window.setScene(manuelTTScene));
        withBB.setOnAction(e -> window.setScene(manuelBEscene));

        HBox layoutButtons = new HBox();
        layoutButtons.getChildren().addAll(withTT, withBB);
        layoutButtons.setAlignment(Pos.CENTER);
        layoutButtons.setSpacing(15);

        VBox manuelEnd = new VBox();
        manuelEnd.getChildren().addAll(topManuel,layoutButtons);
        manuelEnd.getStylesheets().add(css);
        manuelEnd.setAlignment(Pos.CENTER);
        manuelEnd.getStyleClass().add("mainScene");
        manuelEnd.setSpacing(15);
        manuelScene = new Scene(manuelEnd, 800, 800);

        //manuelttscene


        TextArea manuelTTable = new TextArea();
        VBox chooses = new VBox();
        manuelTTable.setPrefRowCount(len);

        Label fCol = new Label("F Column");
        ChoiceBox<String> one = new ChoiceBox<>();
        one.getItems().addAll("0", "1", "X");
        one.getStyleClass().add("cb");
        one.setOnAction(e -> fC.set(0,one.getValue()));
        ChoiceBox<String> two = new ChoiceBox<>();
        two.getItems().addAll("0", "1", "X");
        two.getStyleClass().add("cb2");
        two.setOnAction(e -> fC.set(1,two.getValue()));
        ChoiceBox<String> three = new ChoiceBox<>();
        three.getItems().addAll("0", "1", "X");
        three.getStyleClass().add("cb");
        three.setOnAction(e -> fC.set(2,three.getValue()));
        ChoiceBox<String> four = new ChoiceBox<>();
        four.getItems().addAll("0", "1", "X");
        four.getStyleClass().add("cb2");
        four.setOnAction(e -> fC.set(3,four.getValue()));
        ChoiceBox<String> five = new ChoiceBox<>();
        five.getItems().addAll("0", "1", "X");
        five.getStyleClass().add("cb");
        five.setOnAction(e -> fC.set(4,five.getValue()));
        ChoiceBox<String> six = new ChoiceBox<>();
        six.getItems().addAll("0", "1", "X");
        six.getStyleClass().add("cb2");
        six.setOnAction(e -> fC.set(5,six.getValue()));
        ChoiceBox<String> seven = new ChoiceBox<>();
        seven.getItems().addAll("0", "1", "X");
        seven.getStyleClass().add("cb");
        seven.setOnAction(e -> fC.set(6,seven.getValue()));
        ChoiceBox<String> eight = new ChoiceBox<>();
        eight.getItems().addAll("0", "1", "X");
        eight.getStyleClass().add("cb2");
        eight.setOnAction(e -> fC.set(7,eight.getValue()));
        ChoiceBox<String> nine = new ChoiceBox<>();
        nine.getItems().addAll("0", "1", "X");
        nine.getStyleClass().add("cb");
        nine.setOnAction(e -> fC.set(8,nine.getValue()));
        ChoiceBox<String> ten = new ChoiceBox<>();
        ten.getItems().addAll("0", "1", "X");
        ten.getStyleClass().add("cb2");
        ten.setOnAction(e -> fC.set(9,ten.getValue()));
        ChoiceBox<String> eleven = new ChoiceBox<>();
        eleven.getItems().addAll("0", "1", "X");
        eleven.getStyleClass().add("cb");
        eleven.setOnAction(e -> fC.set(10,eleven.getValue()));
        ChoiceBox<String> twelve = new ChoiceBox<>();
        twelve.getItems().addAll("0", "1", "X");
        twelve.getStyleClass().add("cb2");
        twelve.setOnAction(e -> fC.set(11,twelve.getValue()));
        ChoiceBox<String> thirteen = new ChoiceBox<>();
        thirteen.getItems().addAll("0", "1", "X");
        thirteen.getStyleClass().add("cb");
        thirteen.setOnAction(e -> fC.set(12,thirteen.getValue()));
        ChoiceBox<String> fourteen = new ChoiceBox<>();
        fourteen.getItems().addAll("0", "1", "X");
        fourteen.getStyleClass().add("cb2");
        fourteen.setOnAction(e -> fC.set(13,fourteen.getValue()));
        ChoiceBox<String> fifteen = new ChoiceBox<>();
        fifteen.getItems().addAll("0", "1", "X");
        fifteen.getStyleClass().add("cb");
        fifteen.setOnAction(e -> fC.set(14,fifteen.getValue()));
        ChoiceBox<String> sixteen = new ChoiceBox<>();
        sixteen.getItems().addAll("0", "1", "X");
        sixteen.getStyleClass().add("cb2");
        sixteen.setOnAction(e -> fC.set(15,sixteen.getValue()));
        Button ttBe = new Button(">>");
        ttBe.getStyleClass().add("button");


        TextArea booleanA2 = new TextArea();
        booleanA2.getStyleClass().add("BA");
        booleanA2.setMaxSize(400, 50);
        Button mainTurn2 = new Button("Main menu");

        ttBe.setOnAction(e -> {
            String[] temp = new String[fC.size()];
            for (int i = 0; i < fC.size(); i++) {
                temp[i] = fC.get(i);
            }
            booleanA2.setText("F = " + convert.ttTObe(temp));
        });


        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(group.getSelectedToggle() != null){
                    if(group.getSelectedToggle()==rb1){
                        len = 4;
                        for (int i = 0; i < len; i++) {
                            fC.add("X");
                        }
                        manuelTTable.setText("A B \n"
                                + "0 0\n"
                                + "0 1\n"
                                + "1 0\n"
                                + "1 1\n");
                        manuelTTable.getStyleClass().add("ttable2");
                        chooses.getChildren().addAll(fCol,one, two, three, four);
                        chooses.setPadding(new Insets(320, 5, 100, 5));
                        System.out.println("4");
                    }
                    else if(group.getSelectedToggle() == rb2){
                        len = 8;
                        for (int i = 0; i < len; i++) {
                            fC.add("X");
                        }
                        manuelTTable.setText("A B C\n"
                                + "0 0 0\n"
                                + "0 0 1\n"
                                + "0 1 0\n"
                                + "0 1 1\n"
                                + "1 0 0\n"
                                + "1 0 1\n"
                                + "1 1 0\n"
                                + "1 1 1");
                        manuelTTable.getStyleClass().add("ttable3");
                        chooses.getChildren().addAll(fCol,one, two, three, four, five, six, seven, eight);
                        chooses.setPadding(new Insets(280, 5, 100, 5));
                        System.out.println("8");
                    }
                    else {
                        len = 16;
                        for (int i = 0; i < len; i++) {
                            fC.add("X");
                        }
                        manuelTTable.setText(" A B C D\n"
                                + " 0 0 0 0\n"
                                + " 0 0 0 1\n"
                                + " 0 0 1 0\n"
                                + " 0 0 1 1\n"
                                + " 0 1 0 0\n"
                                + " 0 1 0 1\n"
                                + " 0 1 1 0\n"
                                + " 0 1 1 1\n"
                                + " 1 0 0 0\n"
                                + " 1 0 0 1\n"
                                + " 1 0 1 0\n"
                                + " 1 0 1 1\n"
                                + " 1 1 0 0\n"
                                + " 1 1 0 1\n"
                                + " 1 1 1 0\n"
                                + " 1 1 1 1");
                        manuelTTable.getStyleClass().add("ttable4");
                        chooses.getChildren().addAll(fCol,one, two, three, four, five, six, seven, eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen);
                        chooses.setPadding(new Insets(177, 5, 100, 5));
                        System.out.println("16");
                    }
                }
            }
        });

        TextArea simpleExManuelT = new TextArea();
        simpleExManuelT.setMaxSize(400, 50);
        simpleExManuelT.getStyleClass().add("BA");

        Button simplifyManuelT = new Button("Simplify");
        simplifyManuelT.getStyleClass().add("button");
        VBox simpLayOutManuelT = new VBox();
        simpLayOutManuelT.setAlignment(Pos.CENTER);
        simpLayOutManuelT.getChildren().addAll(booleanA2, simplifyManuelT, simpleExManuelT);
        simpLayOutManuelT.setSpacing(30);

        simplifyManuelT.setOnAction(e -> {
            if(ttBe.getText()==null){
                String[] temp = new String[fC.size()];
                for (int i = 0; i < fC.size(); i++) {
                    temp[i] = fC.get(i);
                }
                booleanA2.setText("F = " + convert.ttTObe(temp));
            }
            System.out.println(fC);
            BESimplification simplification = new BESimplification(booleanA2.getText().split("\\+"));

            for (String sim : simplification.simplify()) {
                simpleExManuelT.appendText((sim.endsWith(".") ? sim.subSequence(0, sim.length() - 2) : sim) + "+");
                System.out.println(sim);
            }
        });

        mainTurn2.setOnAction(e -> {
            manuelTTable.clear();
            manuelTTable.getStyleClass().clear();
            booleanA2.clear();
            chooses.getChildren().clear();
            simpleExManuelT.clear();
            window.setScene(mainScene);
        });
        mainTurn2.getStyleClass().add("button");

        HBox layout3 = new HBox();
        layout3.getChildren().addAll(manuelTTable, chooses, ttBe, simpLayOutManuelT, mainTurn2);
        layout3.getStylesheets().add(css);
        layout3.getStyleClass().add("mainScene");
        layout3.setAlignment(Pos.CENTER);
        manuelTTScene = new Scene(layout3, 800, 800);

        //manuelBAscene
        TextArea ttable3 = new TextArea();
        ttable3.setPrefRowCount(columnNum);
        ttable3.getStyleClass().add("ttable3");
        ttable3.appendText("A B C\n"
                + "0 0 0\n"
                + "0 0 1\n"
                + "0 1 0\n"
                + "0 1 1\n"
                + "1 0 0\n"
                + "1 0 1\n"
                + "1 1 0\n"
                + "1 1 1");
        ttable3.setMaxWidth(50);
        ttable3.setEditable(false);
        TextArea fColumn2 = new TextArea();
        fColumn2.getStyleClass().add("ttable3");
        fColumn2.appendText("F\n");
        fColumn2.setMaxWidth(10);
        Button beTt = new Button("<<");
        beTt.getStyleClass().add("button");
        TextArea booleanA3 = new TextArea();
        booleanA3.getStyleClass().add("BA");
        booleanA3.setMaxSize(400, 50);
        TextArea simpleExManuel = new TextArea();
        simpleExManuel.setMaxSize(400, 50);
        simpleExManuel.getStyleClass().add("BA");

        Button simplifyManuel = new Button("Simplify");
        simplifyManuel.getStyleClass().add("button");
        simplifyManuel.setOnAction(e -> {
            try {
                BESimplification simplification = new BESimplification(convert.toSOP(booleanA3.getText())[0].split("\\+"));
            } catch (Exception ee) {
                return;
            }
//            for (String sim : simplification.simplify()) {
//                simpleExManuel.appendText((sim.endsWith(".") ? sim.subSequence(0, sim.length() - 2) : sim) + "+");
//                System.out.println(sim);
//            }
        });
        VBox simpLayOut2 = new VBox();
        simpLayOut2.setAlignment(Pos.CENTER);
        simpLayOut2.getChildren().addAll(booleanA3, simplifyManuel, simpleExManuel);
        simpLayOut2.setSpacing(30);
        convert.beTOtt(booleanA3.getText());
        Button mainTurn = new Button("Main menu");
        mainTurn.setOnAction(e -> {
            ttable3.clear();
            ttable3.getStyleClass().clear();
            fColumn2.clear();
            fColumn2.getStyleClass().clear();
            fColumn2.setText("F\n");
            booleanA3.clear();
            window.setScene(mainScene);
        });
        mainTurn.getStyleClass().add("button");

        beTt.setOnAction(e -> {
            int len = convert.beTOtt(booleanA3.getText()).length;
            System.out.println(booleanA3.getText());
            String[] temp = convert.beTOtt(booleanA3.getText());
            for (int i = 0; i < len; i++) {
                fColumn2.appendText(temp[i] + "\n");
            }
        });

        HBox layout6 = new HBox();
        layout6.setAlignment(Pos.CENTER);
        layout6.getStylesheets().add(css);
        layout6.getStyleClass().add("mainScene");
        layout6.getChildren().addAll(ttable3, fColumn2, beTt, simpLayOut2, mainTurn);
        manuelBEscene = new Scene(layout6, 800, 800);

        window.setScene(mainScene);

        window.show();
    }


}
