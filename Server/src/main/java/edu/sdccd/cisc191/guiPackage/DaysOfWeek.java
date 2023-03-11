package edu.sdccd.cisc191.guiPackage;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class DaysOfWeek extends HBox
{
    public static Insets LABEL_PADDING = new Insets(15);

    public DaysOfWeek()
    {
        for(int i = 0; i < 7; i++)
        {
            Label day = new Label();
            //day.setPadding(LABEL_PADDING);
            day.setFont(new Font("Cambria", 20));

            switch(i)
            {
                case 0: day.setText("Sunday"); break;
                case 1: day.setText("Monday"); break;
                case 2: day.setText("Tuesday"); break;
                case 3: day.setText("Wednesday"); break;
                case 4: day.setText("Thursday"); break;
                case 5: day.setText("Friday"); break;
                default: day.setText("Saturday"); break;
            }
            day.setPrefSize(130,20);
            this.getChildren().add(day);
            day.setAlignment(Pos.CENTER);
        }
        this.setPadding(new Insets(0, 20, 20 ,20));
        this.setSpacing(0);
        this.setAlignment(Pos.CENTER);
    }
}
