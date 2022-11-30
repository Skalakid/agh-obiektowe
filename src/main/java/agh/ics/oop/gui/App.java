package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application  {
    IWorldMap map;
    int cellWidth = 40;
    int cellHeight = 40;
    private final GridPane gridPane = new GridPane();
    private final OptionsParser optionsParser = new OptionsParser();
    private SimulationEngine engine;

    String grassImagePath = "src/main/resources/grass.png";
    public void init() {
//        RectangularMap
//        try {
//            MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
//            this.map = new RectangularMap(10, 5);
//            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
//        } catch(IllegalArgumentException exception) {
//            exception.printStackTrace();
//            Platform.exit();
//        }

//        GrassField
        try {
//        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
            this.map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(5,5) };
            this.engine = new SimulationEngine(map, positions, this, 500);
        } catch(IllegalArgumentException exception) {
            exception.printStackTrace();
            Platform.exit();
        }
    }


    private void createRowBox(BoxType type, GridPane gridPane, String value, int x, int y ) {
        Label yxLabel = new Label(value);
        gridPane.add(yxLabel, x, y, 1, 1);
        if(type != BoxType.ROW) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(this.cellWidth));
        }
        if(type != BoxType.COLUMN) {
            gridPane.getRowConstraints().add(new RowConstraints(this.cellHeight));
        }
        GridPane.setHalignment(yxLabel, HPos.CENTER);
    }


    public void start(Stage primaryStage) {
        renderGridPane();
        TextField moveDirections = new TextField();
        Button start = new Button("Start");
        start.setOnAction((click) -> {
            MoveDirection[] directions = this.optionsParser.parse(moveDirections.getText().split(" "));
            this.engine.setDirections(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
            moveDirections.clear();
        });

        HBox guiControls = new HBox(moveDirections, start);
        VBox gui = new VBox(guiControls, this.gridPane);
        guiControls.setAlignment(Pos.TOP_CENTER);
        gui.setSpacing(20);

        this.gridPane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(gui, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void renderGridPane() {
        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        this.gridPane.getChildren().clear();

        this.gridPane.setGridLinesVisible(true);
        this.gridPane.setGridLinesVisible(true);

        Vector2d[] borders = this.map.getMinBorders();
        int leftBorder = borders[0].x;
        int rightBorder = borders[1].x;
        int bottomBorder = borders[0].y;
        int topBorder = borders[1].y;

        createRowBox(BoxType.BOTH,  this.gridPane, "y\\x", 0, 0);

        for (int i = 1; i < topBorder - bottomBorder + 2; i++) {
            int value = topBorder - i + 1;
            createRowBox(BoxType.ROW,  this.gridPane, value + "", 0, i);
        }

        for (int i = 1; i < rightBorder - leftBorder + 2; i++) {
            int value = leftBorder + i - 1;
            createRowBox(BoxType.COLUMN,  this.gridPane, value + "", i, 0);
        }

        for (int x = 1; x < rightBorder - leftBorder + 2; x++) {
            for (int y = 1; y < topBorder - bottomBorder + 2; y++) {
               Label label;
               IMapElement element = (IMapElement) this.map.objectAt(new Vector2d(leftBorder + x - 1, topBorder - y + 1));
               if(element == null) {
                   label = new Label("");
               } else {
                   GuiElementBox mapElement = new GuiElementBox(element);
                   label = new Label(element.toString());
                   this.gridPane.add(mapElement.getPictureRegion(), x, y);
               }
                this.gridPane.add(label, x, y, 1, 1);
               GridPane.setHalignment(label, HPos.CENTER);
            }
        }


    }
}
