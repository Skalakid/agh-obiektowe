package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox extends Node {
    private VBox pictureRegion;

    public GuiElementBox(IMapElement element) {

        try {
            Image image = new Image(new FileInputStream(element.getImagePath()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);

            Label label = new Label(element.getLabel());
            label.setFont(new Font("Serif", 9));

            this.pictureRegion = new VBox();
            this.pictureRegion.setAlignment(Pos.CENTER);
            this.pictureRegion.getChildren().addAll(imageView, label);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getPictureRegion() {
        return this.pictureRegion;
    }
}
