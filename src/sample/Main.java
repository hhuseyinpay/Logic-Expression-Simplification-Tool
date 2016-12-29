package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main extends Application {

    private String PATH;
    String[] read;
    int len = 0;
    private FileChooser fileChooser;
    private File file;
    Stage window;
    Scene mainScene, readScene, manuelScene, manuelTTScene, manuelBEscene,karnaughMapScene,karnaughMapManuel;
    Button readButton, manuelButton;
    FileOperation fo;
    KarnaughMap karnaughMap = new KarnaughMap();
    ArrayList<String> karMap = new ArrayList<>();
    Converter convert = new Converter();
    BooleanExpression booleanExpression = new BooleanExpression();
    ArrayList<String> fC = new ArrayList<>();
    String css = this.getClass().getResource("application.css").toExternalForm();
    Boolean isBE = false;
    String[] booleanEx;


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
            inputsLayOut.getChildren().addAll(rb1, rb2, rb3);
            VBox topManuel = new VBox();
            topManuel.getChildren().addAll(input, inputsLayOut);
            topManuel.setAlignment(Pos.CENTER);
            window.setScene(manuelScene);
        });
        HBox layOut = new HBox();
        layOut.setSpacing(30.0);
        layOut.setAlignment(Pos.CENTER);
        layOut.getChildren().addAll(readButton, manuelButton);
        mainScene = new Scene(layOut, 900, 800);
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
        Button map = new Button("Karnaugh Map Simplify");
        map.getStyleClass().add("button");

        HBox simpbuttons = new HBox();
        simpbuttons.getChildren().addAll(simplify, map);
        simpbuttons.setSpacing(15);
        simpbuttons.setAlignment(Pos.CENTER);
        VBox simpLayOut = new VBox();
        simpLayOut.setAlignment(Pos.CENTER);
        simpLayOut.getChildren().addAll(booleanA, simpbuttons, simpleEx);
        simpLayOut.setSpacing(30);

        readButton.setOnAction(e -> {
            file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                PATH = file.getAbsolutePath().toString();    //to find path
            }


            if (PATH.endsWith(".be")) {
                read = fo.read(PATH);
                booleanEx= read[0].split("\\+");
                    booleanA.appendText(read[0] );
                    if (read[0].contains("D")) len = 16;
                    else if (read[0].contains("C")) len = 8;
                    else if (read[0].contains("B")) len = 4;

                isBE = true;
                ttfrombe.setVisible(false);
                befromtt.setVisible(true);


            } else if (PATH.endsWith(".tt")) {
                read = fo.read(PATH);
                len = read.length;
                booleanEx= new String[convert.ttTObe(read).length()];
                String temp = convert.ttTObe(read);
                booleanEx=temp.split("\\+");
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
                fColumn.getStyleClass().add("ftable2");

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
                fColumn.getStyleClass().add("ftable3");
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
                fColumn.getStyleClass().add("ftable4");

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

            for (String sim : booleanExpression.simplify(convert.beTObaNode((isBE ? read[0].split("\\+") : convert.ttTObe(read).split("\\+"))))) {
                simpleEx.appendText((sim.endsWith(".") ? sim.subSequence(0, sim.length() - 2) : sim) + "+");
                System.out.println(sim);
            }
            simpleEx.deletePreviousChar();
            simpleEx.deletePreviousChar();
            simpleEx.deletePreviousChar();

            isBE = false;
        });
        map.setOnAction(e->{

            window.setScene(karnaughMapScene(len,convert.beTOkm(booleanEx),karnaughMap.simplify(convert.beTOkm(booleanEx))));
            ttable.clear();
            len=0;
            ttable.getStyleClass().clear();
            fColumn.clear();
            fColumn.getStyleClass().clear();
            fColumn.setText("F\n");
            booleanA.clear();
            simpleEx.clear();
        });

        ttfrombe.setOnAction(e -> {           //truth table dan boolean expression a >>
            booleanA.setText("F = " + convert.ttTObe(read));
        });

        returnMain.setOnAction(e -> {
            ttable.clear();
            len=0;
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
        readScene = new Scene(readEnd, 900, 800);
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
        inputsLayOut.getChildren().addAll(rb1, rb2, rb3);
        inputsLayOut.setAlignment(Pos.CENTER);
        inputsLayOut.setSpacing(15);

        VBox topManuel = new VBox();
        topManuel.getChildren().addAll(input, inputsLayOut);
        topManuel.setAlignment(Pos.CENTER);
        topManuel.setSpacing(15);

        Button withTT = new Button("With Truth Table");
        withTT.getStyleClass().add("readButton");
        Button withBB = new Button("With Boolean Algebra");
        withBB.getStyleClass().add("readButton");
        Button withMap = new Button("With Karnaugh Map");
        withMap.getStyleClass().add("readButton");
        withMap.setOnAction(e -> window.setScene(manuelKarnaughMap(len)));
        withTT.setOnAction(e -> window.setScene(manuelTTScene));
        withBB.setOnAction(e -> window.setScene(manuelBEscene));

        HBox layoutButtons = new HBox();
        layoutButtons.getChildren().addAll(withTT, withBB,withMap);
        layoutButtons.setAlignment(Pos.CENTER);
        layoutButtons.setSpacing(15);

        VBox manuelEnd = new VBox();
        manuelEnd.getChildren().addAll(topManuel, layoutButtons);
        manuelEnd.getStylesheets().add(css);
        manuelEnd.setAlignment(Pos.CENTER);
        manuelEnd.getStyleClass().add("mainScene");
        manuelEnd.setSpacing(15);
        manuelScene = new Scene(manuelEnd, 900, 800);

        //manuelttscene


        TextArea manuelTTable = new TextArea();
        VBox chooses = new VBox();
        manuelTTable.setPrefRowCount(len);

        Label fCol = new Label("F Column");
        ChoiceBox<String> one = new ChoiceBox<>();
        one.getItems().addAll("0", "1");
        one.getStyleClass().add("cb");
        one.setOnAction(e -> fC.set(0, one.getValue()));
        ChoiceBox<String> two = new ChoiceBox<>();
        two.getItems().addAll("0", "1");
        two.getStyleClass().add("cb2");
        two.setOnAction(e -> fC.set(1, two.getValue()));
        ChoiceBox<String> three = new ChoiceBox<>();
        three.getItems().addAll("0", "1");
        three.getStyleClass().add("cb");
        three.setOnAction(e -> fC.set(2, three.getValue()));
        ChoiceBox<String> four = new ChoiceBox<>();
        four.getItems().addAll("0", "1");
        four.getStyleClass().add("cb2");
        four.setOnAction(e -> fC.set(3, four.getValue()));
        ChoiceBox<String> five = new ChoiceBox<>();
        five.getItems().addAll("0", "1");
        five.getStyleClass().add("cb");
        five.setOnAction(e -> fC.set(4, five.getValue()));
        ChoiceBox<String> six = new ChoiceBox<>();
        six.getItems().addAll("0", "1");
        six.getStyleClass().add("cb2");
        six.setOnAction(e -> fC.set(5, six.getValue()));
        ChoiceBox<String> seven = new ChoiceBox<>();
        seven.getItems().addAll("0", "1");
        seven.getStyleClass().add("cb");
        seven.setOnAction(e -> fC.set(6, seven.getValue()));
        ChoiceBox<String> eight = new ChoiceBox<>();
        eight.getItems().addAll("0", "1");
        eight.getStyleClass().add("cb2");
        eight.setOnAction(e -> fC.set(7, eight.getValue()));
        ChoiceBox<String> nine = new ChoiceBox<>();
        nine.getItems().addAll("0", "1");
        nine.getStyleClass().add("cb");
        nine.setOnAction(e -> fC.set(8, nine.getValue()));
        ChoiceBox<String> ten = new ChoiceBox<>();
        ten.getItems().addAll("0", "1");
        ten.getStyleClass().add("cb2");
        ten.setOnAction(e -> fC.set(9, ten.getValue()));
        ChoiceBox<String> eleven = new ChoiceBox<>();
        eleven.getItems().addAll("0", "1");
        eleven.getStyleClass().add("cb");
        eleven.setOnAction(e -> fC.set(10, eleven.getValue()));
        ChoiceBox<String> twelve = new ChoiceBox<>();
        twelve.getItems().addAll("0", "1", "X");
        twelve.getStyleClass().add("cb2");
        twelve.setOnAction(e -> fC.set(11, twelve.getValue()));
        ChoiceBox<String> thirteen = new ChoiceBox<>();
        thirteen.getItems().addAll("0", "1");
        thirteen.getStyleClass().add("cb");
        thirteen.setOnAction(e -> fC.set(12, thirteen.getValue()));
        ChoiceBox<String> fourteen = new ChoiceBox<>();
        fourteen.getItems().addAll("0", "1");
        fourteen.getStyleClass().add("cb2");
        fourteen.setOnAction(e -> fC.set(13, fourteen.getValue()));
        ChoiceBox<String> fifteen = new ChoiceBox<>();
        fifteen.getItems().addAll("0", "1");
        fifteen.getStyleClass().add("cb");
        fifteen.setOnAction(e -> fC.set(14, fifteen.getValue()));
        ChoiceBox<String> sixteen = new ChoiceBox<>();
        sixteen.getItems().addAll("0", "1");
        sixteen.getStyleClass().add("cb2");
        sixteen.setOnAction(e -> fC.set(15, sixteen.getValue()));
        Button ttBe = new Button(">>");
        ttBe.getStyleClass().add("button");


        TextArea booleanA2 = new TextArea();
        booleanA2.getStyleClass().add("BA");
        booleanA2.setMaxSize(400, 50);
        Button mainTurnTT = new Button("Main menu");

        ttBe.setOnAction(e -> {
            String[] temp = new String[fC.size()];
            for (int i = 0; i < fC.size(); i++) {
                temp[i] = fC.get(i);
            }
            booleanA2.setText("F = " + convert.ttTObe(temp));
        });




        TextArea simpleExManuelT = new TextArea();
        simpleExManuelT.setMaxSize(400, 50);
        simpleExManuelT.getStyleClass().add("BA");

        Button simplifyManuelT = new Button("Simplify");
        simplifyManuelT.getStyleClass().add("button");
        Button mapTT = new Button("Karnaugh Map Simplify");
        mapTT.getStyleClass().add("button");
        HBox simpbuttonsTT = new HBox();
        simpbuttonsTT.getChildren().addAll(simplifyManuelT, mapTT);
        simpbuttonsTT.setSpacing(15);
        simpbuttonsTT.setAlignment(Pos.CENTER);
        VBox simpLayOutManuelT = new VBox();
        simpLayOutManuelT.setAlignment(Pos.CENTER);
        simpLayOutManuelT.getChildren().addAll(booleanA2, simpbuttonsTT, simpleExManuelT);
        simpLayOutManuelT.setSpacing(30);

        simplifyManuelT.setOnAction(e -> {
            simpleExManuelT.clear();
            if (ttBe.getText() == null) {
                String[] temp = new String[fC.size()];
                for (int i = 0; i < fC.size(); i++) {
                    temp[i] = fC.get(i);
                }
                booleanEx=convert.ttTObe(temp).split("\\+");
                booleanA2.setText("F = " + convert.ttTObe(booleanEx));
            }
            System.out.println(fC);
            for (String sim : booleanExpression.simplify(convert.beTObaNode(booleanA2.getText().split("\\+")))) {
                simpleExManuelT.appendText((sim.endsWith(".") ? sim.subSequence(0, sim.length() - 2) : sim) + "+");
                System.out.println(sim);
            }
        });

        mapTT.setOnAction(e->{
            String[] temp = new String[fC.size()];
            for (int i = 0; i < fC.size(); i++) {
                temp[i] = fC.get(i);
            }
            booleanEx=convert.ttTObe(temp).split("\\+");
            window.setScene(karnaughMapScene(len,convert.beTOkm(booleanEx),karnaughMap.simplify(convert.beTOkm(booleanEx))));
            booleanA2.clear();
            chooses.getChildren().clear();
            simpleExManuelT.clear();
            manuelTTable.clear();
            manuelTTable.getStyleClass().clear();
        });



        HBox layout3 = new HBox();
        layout3.getChildren().addAll(manuelTTable, chooses, ttBe, simpLayOutManuelT, mainTurnTT);
        layout3.getStylesheets().add(css);
        layout3.getStyleClass().add("mainScene");
        layout3.setAlignment(Pos.CENTER);
        manuelTTScene = new Scene(layout3, 900, 800);

        //manuelBAscene
        TextArea ttableBA = new TextArea();
        ttableBA.getStyleClass().add("ttable3");

        ttableBA.setMaxWidth(50);
        ttableBA.setEditable(false);
        TextArea fColumnBA = new TextArea();

        fColumnBA.appendText("F\n");
        fColumnBA.setMaxWidth(10);
        Button beTt = new Button("<<");
        beTt.getStyleClass().add("button");
        TextArea booleanABA = new TextArea();
        booleanABA.getStyleClass().add("BA");
        booleanABA.setMaxSize(400, 50);
        TextArea simpleExManuelBA = new TextArea();
        simpleExManuelBA.setMaxSize(400, 50);
        simpleExManuelBA.getStyleClass().add("BA");

        Button simplifyManuel = new Button("Simplify");
        simplifyManuel.getStyleClass().add("button");
        Button mapBA = new Button("Karnaugh Map");
        mapBA.getStyleClass().add("button");

        HBox simples = new HBox();
        simples.getChildren().addAll(simplifyManuel,mapBA);
        simples.setAlignment(Pos.CENTER);
        simples.setSpacing(15);

        VBox simpLayOut2 = new VBox();
        simpLayOut2.setAlignment(Pos.CENTER);
        simpLayOut2.getChildren().addAll(booleanABA, simples, simpleExManuelBA);
        simpLayOut2.setSpacing(30);
        convert.beTOtt(booleanABA.getText());
        Button mainTurn = new Button("Main menu");

        mainTurn.getStyleClass().add("button");

        beTt.setOnAction(e -> {
            fColumnBA.clear();
            int len = convert.beTOtt(booleanABA.getText()).length;
            System.out.println(booleanABA.getText());
            String[] temp = convert.beTOtt(booleanABA.getText());
            fColumnBA.appendText("F\n");
            for (int i = 0; i < len; i++) {
                fColumnBA.appendText(temp[i] + "\n");
            }
        });

        HBox layout6 = new HBox();
        layout6.setAlignment(Pos.CENTER);
        layout6.getStylesheets().add(css);
        layout6.getStyleClass().add("mainScene");
        layout6.getChildren().addAll(ttableBA, fColumnBA, beTt, simpLayOut2, mainTurn);
        manuelBEscene = new Scene(layout6, 900, 800);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (group.getSelectedToggle() != null) {
                    if (group.getSelectedToggle() == rb1) {
                        len = 4;
                        for (int i = 0; i < len; i++) {
                            fC.add("0");
                        }
                        for (int i = 0; i < len; i++) {
                            karMap.add("0");
                        }
                        ttableBA.setText("A B \n"
                                + "0 0\n"
                                + "0 1\n"
                                + "1 0\n"
                                + "1 1\n");
                        manuelTTable.setText("A B \n"
                                + "0 0\n"
                                + "0 1\n"
                                + "1 0\n"
                                + "1 1\n");
                        fColumnBA.getStyleClass().add("ftable2");
                        ttableBA.getStyleClass().add("ttable2");
                        manuelTTable.getStyleClass().add("ttable2");
                        chooses.getChildren().addAll(fCol, one, two, three, four);
                        chooses.setPadding(new Insets(320, 5, 100, 5));
                        System.out.println("4");
                    } else if (group.getSelectedToggle() == rb2) {
                        len = 8;
                        for (int i = 0; i < len; i++) {
                            fC.add("0");
                        }
                        ttableBA.setText("A B C\n"
                                + "0 0 0\n"
                                + "0 0 1\n"
                                + "0 1 0\n"
                                + "0 1 1\n"
                                + "1 0 0\n"
                                + "1 0 1\n"
                                + "1 1 0\n"
                                + "1 1 1");
                        manuelTTable.setText("A B C\n"
                                + "0 0 0\n"
                                + "0 0 1\n"
                                + "0 1 0\n"
                                + "0 1 1\n"
                                + "1 0 0\n"
                                + "1 0 1\n"
                                + "1 1 0\n"
                                + "1 1 1");
                        fColumnBA.getStyleClass().add("ftable3");
                        ttableBA.getStyleClass().add("ttable3");
                        manuelTTable.getStyleClass().add("ttable3");
                        chooses.getChildren().addAll(fCol, one, two, three, four, five, six, seven, eight);
                        chooses.setPadding(new Insets(275, 5, 100, 5));
                        System.out.println("8");
                    } else {
                        len = 16;
                        for (int i = 0; i < len; i++) {
                            fC.add("0");
                        }
                        ttableBA.setText(" A B C D\n"
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
                        fColumnBA.getStyleClass().add("ftable4");
                        ttableBA.getStyleClass().add("ttable4");
                        manuelTTable.getStyleClass().add("ttable4");
                        chooses.getChildren().addAll(fCol, one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen);
                        chooses.setPadding(new Insets(177, 5, 100, 5));
                        System.out.println("16");
                    }

                }
                else{
                    window.setScene(manuelScene);
                }
            }
        });

        simplifyManuel.setOnAction(e -> {
            simpleExManuelBA.clear();
            if (booleanABA.getText() != null) {
                booleanEx= (convert.toSOP(booleanABA.getText()));
                if (booleanEx[0].contains("D")) len = 16;
                else if (booleanEx[0].contains("C")) len = 8;
                else if (booleanEx[0].contains("B")) len = 4;
            }
            for (String sim :  booleanExpression.simplify(convert.beTObaNode(convert.toSOP(booleanABA.getText())))) {
                simpleExManuelBA.appendText((sim.endsWith(".") ? sim.subSequence(0, sim.length() - 2) : sim) + "+");
                System.out.println(sim);
            }
        });

        mainTurnTT.setOnAction(e -> {
            manuelTTable.clear();
            manuelTTable.getStyleClass().clear();
            ttableBA.clear();
            ttableBA.getStyleClass().clear();
            booleanA2.clear();
            chooses.getChildren().clear();
            simpleExManuelT.clear();
            fColumnBA.getStyleClass().clear();
            fColumnBA.setText("F\n");
            window.setScene(mainScene);
        });
        mainTurn.setOnAction(e -> {
            ttableBA.clear();
            ttableBA.getStyleClass().clear();
            manuelTTable.clear();
            manuelTTable.getStyleClass().clear();
            fColumnBA.clear();
            fColumnBA.getStyleClass().clear();
            fColumnBA.setText("F\n");
            booleanABA.clear();
            booleanA2.clear();
            chooses.getChildren().clear();
            simpleExManuelT.clear();
            window.setScene(mainScene);
        });
        mainTurnTT.getStyleClass().add("button");


        window.setScene(mainScene);
        window.show();
    }


    private Scene manuelKarnaughMap(int len) {
        for (int i = 0; i < len; i++) {
            karMap.add("0");
        }
        ChoiceBox<String> one = new ChoiceBox<>();
        one.getItems().addAll("0", "1");
        one.getStyleClass().add("cb");
        one.setOnAction(e -> karMap.set(0, one.getValue()));
        one.setValue("0");
        ChoiceBox<String> two = new ChoiceBox<>();
        two.getItems().addAll("0", "1");
        two.getStyleClass().add("cb2");
        two.setOnAction(e -> karMap.set(1, two.getValue()));
        two.setValue("0");
        ChoiceBox<String> three = new ChoiceBox<>();
        three.getItems().addAll("0", "1");
        three.getStyleClass().add("cb");
        three.setOnAction(e -> karMap.set(2, three.getValue()));
        three.setValue("0");
        ChoiceBox<String> four = new ChoiceBox<>();
        four.getItems().addAll("0", "1");
        four.getStyleClass().add("cb2");
        four.setOnAction(e -> karMap.set(3, four.getValue()));
        four.setValue("0");
        ChoiceBox<String> five = new ChoiceBox<>();
        five.getItems().addAll("0", "1");
        five.getStyleClass().add("cb");
        five.setOnAction(e -> karMap.set(4, five.getValue()));
        five.setValue("0");
        ChoiceBox<String> six = new ChoiceBox<>();
        six.getItems().addAll("0", "1");
        six.getStyleClass().add("cb2");
        six.setValue("0");
        six.setOnAction(e -> karMap.set(5, six.getValue()));
        ChoiceBox<String> seven = new ChoiceBox<>();
        seven.getItems().addAll("0", "1");
        seven.getStyleClass().add("cb");
        seven.setValue("0");
        seven.setOnAction(e -> karMap.set(6, seven.getValue()));
        ChoiceBox<String> eight = new ChoiceBox<>();
        eight.getItems().addAll("0", "1");
        eight.getStyleClass().add("cb2");
        eight.setValue("0");
        eight.setOnAction(e -> karMap.set(7, eight.getValue()));
        ChoiceBox<String> nine = new ChoiceBox<>();
        nine.getItems().addAll("0", "1");
        nine.getStyleClass().add("cb");
        nine.setValue("0");
        nine.setOnAction(e -> karMap.set(8, nine.getValue()));
        ChoiceBox<String> ten = new ChoiceBox<>();
        ten.getItems().addAll("0", "1");
        ten.getStyleClass().add("cb2");
        ten.setValue("0");
        ten.setOnAction(e -> karMap.set(9, ten.getValue()));
        ChoiceBox<String> eleven = new ChoiceBox<>();
        eleven.getItems().addAll("0", "1");
        eleven.getStyleClass().add("cb");
        eleven.setValue("0");
        eleven.setOnAction(e -> karMap.set(10, eleven.getValue()));
        ChoiceBox<String> twelve = new ChoiceBox<>();
        twelve.getItems().addAll("0", "1");
        twelve.setValue("0");
        twelve.getStyleClass().add("cb2");
        twelve.setOnAction(e -> karMap.set(11, twelve.getValue()));
        ChoiceBox<String> thirteen = new ChoiceBox<>();
        thirteen.getItems().addAll("0", "1");
        thirteen.getStyleClass().add("cb");
        thirteen.setValue("0");
        thirteen.setOnAction(e -> karMap.set(12, thirteen.getValue()));
        ChoiceBox<String> fourteen = new ChoiceBox<>();
        fourteen.getItems().addAll("0", "1");
        fourteen.getStyleClass().add("cb2");
        fourteen.setOnAction(e -> karMap.set(13, fourteen.getValue()));
        eleven.setValue("0");
        ChoiceBox<String> fifteen = new ChoiceBox<>();
        fifteen.getItems().addAll("0", "1");
        fifteen.getStyleClass().add("cb");
        fifteen.setValue("0");
        fifteen.setOnAction(e -> karMap.set(14, fifteen.getValue()));
        ChoiceBox<String> sixteen = new ChoiceBox<>();
        sixteen.getItems().addAll("0", "1");
        sixteen.getStyleClass().add("cb2");
        sixteen.setValue("0");
        sixteen.setOnAction(e -> karMap.set(15, sixteen.getValue()));
        HBox firstLine =new HBox();
        HBox secondLine = new HBox();
        HBox thirdLine = new HBox();
        HBox fourthLine = new HBox();
        VBox map = new VBox();
        Label B=new Label();
        Label A=new Label();
        if(len==4){
            B.setText("B:   0          1");
            A.setText("A:\n0\n1");
            A.setLineSpacing(9);
            A.setMaxHeight(100);
            firstLine.getChildren().addAll(one,two);
            secondLine.getChildren().addAll(three,four);
            map.getChildren().addAll(B,firstLine,secondLine);
        }
        else if(len == 8){
            B.setText("C:   0          1");
            A.setText("AB\n00\n01\n11\n10");
            A.setLineSpacing(10);
            A.setMaxWidth(50);
            A.setMinHeight(150);
            firstLine.getChildren().addAll(one,two);
            secondLine.getChildren().addAll(three,four);
            thirdLine.getChildren().addAll(seven,eight);
            fourthLine.getChildren().addAll(five,six);
            map.getChildren().addAll(B,firstLine,secondLine,thirdLine,fourthLine);
        }
        else if(len==16){
            B.setText("CD:00     01     11     10");
            A.setText("AB:\n00\n01\n11\n10");
            A.setMaxHeight(300);
            A.setLineSpacing(9);

            firstLine.getChildren().addAll(one,two,four,three);
            secondLine.getChildren().addAll(five,six,eight,seven);
            fourthLine.getChildren().addAll(nine,ten,twelve,eleven);
            thirdLine.getChildren().addAll(thirteen,fourteen,sixteen,fifteen);
            map.getChildren().addAll(B,firstLine,secondLine,thirdLine,fourthLine);
        }
        map.setAlignment(Pos.CENTER);
        map.setSpacing(2);
        firstLine.setSpacing(2);
        secondLine.setSpacing(2);
        thirdLine.setSpacing(2);
        fourthLine.setSpacing(2);
        Button simp = new Button("Simplify >>");
        simp.getStyleClass().add("button");


        TextArea simpEx = new TextArea();
        simpEx.getStyleClass().add("BA");
        simpEx.setMaxSize(400, 50);
        simp.setOnAction(e ->{
            String[] temp =new String[len];
            for (int i = 0; i < len; i++) {
                temp[i]=karMap.get(i);
            }
            LinkedList<String> simplify=new LinkedList<String>();
            if(convert.beTOkm(convert.ttTObe(temp).split("\\+"))!=null) {
                simplify = karnaughMap.simplify(convert.beTOkm(convert.ttTObe(temp).split("\\+")));
                simpEx.clear();
                if(simplify!=null) {
                    for (int i = 0; i < simplify.size(); i++) {
                        simpEx.appendText(simplify.get(i));
                        if (i != simplify.size() - 1)
                            simpEx.appendText("+");
                    }
                }
            }
        });
        Button mainTurnKM = new Button("Main Menu");
        mainTurnKM.getStyleClass().add("button");
        mainTurnKM.setOnAction(e->{
            simpEx.clear();
            karMap.clear();
            simpEx.getStyleClass().clear();
            window.setScene(mainScene);
        });
        HBox KMapEnd = new HBox();
        KMapEnd.getChildren().addAll(A,map,simp,simpEx,mainTurnKM);
        KMapEnd.setSpacing(15);
        KMapEnd.setAlignment(Pos.CENTER);
        KMapEnd.getStyleClass().add("mainScene");
        KMapEnd.getStylesheets().add(css);
        return karnaughMapManuel = new Scene(KMapEnd,900,800);
    }

    private Scene karnaughMapScene(int len, KMNode[][] karnaugh, LinkedList<String> simplify) {
        TextArea KMap = new TextArea();
        KMap.setEditable(false);
        if (karnaugh != null) {
            if (len == 4) {
                KMap.appendText("   B'  B\n"
                        + "A' " + karnaugh[0][0].getKey() + "   " + karnaugh[0][1].getKey() + "\n"
                        + "A  " + karnaugh[1][0].getKey() + "   " + karnaugh[1][1].getKey());
                KMap.getStyleClass().add("KMap2");
            } else if (len == 8) {
                KMap.appendText("A B |C:0  1\n"
                        + "----|------\n"
                        + "0 0 |  " + karnaugh[0][0].getKey() + "  " + karnaugh[0][1].getKey() + "\n"
                        + "0 1 |  " + karnaugh[1][0].getKey() + "  " + karnaugh[1][1].getKey() + "\n"
                        + "1 1 |  " + karnaugh[2][0].getKey() + "  " + karnaugh[2][1].getKey() + "\n"
                        + "1 0 |  " + karnaugh[3][0].getKey() + "  " + karnaugh[3][1].getKey());
                KMap.getStyleClass().add("KMap3");
            } else if (len == 16) {
                KMap.appendText("A B |C D: 00  01  11  10\n"
                        + "----|----------------\n"
                        + "0 0 |     " + karnaugh[0][0].getKey() + "   " + karnaugh[0][1].getKey()+"   " + karnaugh[0][2].getKey() + "   " + karnaugh[0][3].getKey() + "\n"
                        + "0 1 |     " + karnaugh[1][0].getKey() + "   " + karnaugh[1][1].getKey()+"   " + karnaugh[1][2].getKey() + "   " + karnaugh[1][3].getKey() + "\n"
                        + "1 1 |     " + karnaugh[2][0].getKey() + "   " + karnaugh[2][1].getKey()+"   " + karnaugh[2][2].getKey() + "   " + karnaugh[2][3].getKey() + "\n"
                        + "1 0 |     " + karnaugh[3][0].getKey() + "   " + karnaugh[3][1].getKey()+"   " + karnaugh[3][2].getKey() + "   " + karnaugh[3][3].getKey());
                KMap.getStyleClass().add("KMap4");
            }
        }
        TextArea simpKmap = new TextArea();
        if (simplify != null) {
            for (int i = 0; i < simplify.size(); i++) {
                simpKmap.appendText(simplify.get(i));
                if (i != simplify.size() - 1)
                    simpKmap.appendText(" + ");
            }
        }
        simpKmap.setMaxSize(400, 50);
        simpKmap.setEditable(false);
        simpKmap.getStyleClass().add("BA");
        Button mainTurnKmap = new Button("Main Menu");
        mainTurnKmap.setOnAction(e-> {
            simpKmap.clear();
            simpKmap.getStyleClass().clear();
            KMap.clear();
            KMap.getStyleClass().clear();
            KMap.clear();
            window.setScene(mainScene);
        });

        HBox KmapLay = new HBox();
        KmapLay.getChildren().addAll(KMap,simpKmap,mainTurnKmap);
        KmapLay.setSpacing(15);
        KmapLay.setAlignment(Pos.CENTER);
        KmapLay.getStylesheets().add(css);
        KmapLay.getStyleClass().add("mainScene");

        return karnaughMapScene = new Scene(KmapLay,900,800);

    }


}
