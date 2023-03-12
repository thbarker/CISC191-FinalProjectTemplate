package edu.sdccd.cisc191.guiPackage;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * This Class extends from the JavaFX Label class
 * and serves the purpose of initializing a custom font,
 * alignment, padding, and size for the Month text at the
 * top of the Calendar
 */
public class HeaderLabel extends Label
{
    public static Insets LABEL_PADDING = new Insets(30,0,30,0);

    public HeaderLabel()
    {
        this.setPadding(LABEL_PADDING);
        this.setFont(new Font("Cambria", 25));
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(300, 50);
    }
}