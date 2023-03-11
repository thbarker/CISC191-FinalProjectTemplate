package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import edu.sdccd.cisc191.calendarPackage.Event;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.Date;

/**
 *
 FIXES and Train of Thought : Main problem right now arises from the fact that the buttons cannot set on action
 Right now i do not have the daybutton array from the CalendarController class linked with the buttons in the grid
 This is what needs to be fixed
 */

public class MainWindow extends Application {
    private HeaderLabel month;
    private DaysOfWeek days;
    private CalendarController myCalendar;
    private VBox grid;

    private Scene mainScene;

    private Stage window;

    private DayButton[][] dayButtons = new DayButton[6][7];

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        myCalendar = new CalendarController();
        window = primaryStage;

        window.setTitle("Calendar");

        generateMainScene();

        window.setScene(mainScene);
        window.show();
        window.setResizable(false);
    }

    public void generateMainScene() {
        month = new HeaderLabel(); //Label that contains the month name at the top of the scene
        days = new DaysOfWeek(); //HBOox that contains all the days of the week below the month name

        HBox root = new HBox(); //Root to be set to the scene
        BorderPane mainLayout = new BorderPane(); //Borderpane to contain the whole calendar view
        VBox headerRoot = new VBox(); //VBox to contain the header and the days of the week
        HBox headerLabelBox = new HBox(); //HBox to contain the month name label, next, back buttons
        HBox buttonBox = new HBox(); //HBox to contain bottom buttons

        myCalendar.addEvent(new Event());

        grid = new VBox();
        update();

        Button addEvent = new Button("+");
        Button todayButton = new Button("Today");
        BackButton back = new BackButton();
        NextButton next = new NextButton();

        addEvent.setOnAction(e -> {
            AddEventWindow.display(myCalendar);
            update();
        });
        todayButton.setOnAction(e -> {
            myCalendar.today();
            update();
        });
        back.setOnAction(e -> {
            back.handleClick(myCalendar);
            update();
        });
        next.setOnAction(e -> {
            next.handleClick(myCalendar);
            update();
        });

        //SET header and Bottom buttons to the root borderpane
        mainLayout.setTop(headerRoot);
        mainLayout.setBottom(buttonBox);
        mainLayout.setCenter(grid);

        headerLabelBox.getChildren().addAll(back, month, next);
        headerRoot.getChildren().addAll(headerLabelBox, days);
        buttonBox.getChildren().addAll(addEvent, todayButton);

        headerLabelBox.setAlignment(Pos.CENTER);

        root.getChildren().add(mainLayout);

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);
        buttonBox.setPadding(new Insets(10));
        mainScene = new Scene(root, 1300, 800);
    }

    public void update()
    {
        month.setText(myCalendar.getMonthAlpha() + " " + Integer.toString(myCalendar.getCurrentYear()));

        grid.getChildren().clear();

        myCalendar.updateArray();

        for(int i = 0; i < 6; i++)
        {
            HBox branch = new HBox();
            for(int j = 0; j < 7; j++)
            {
                dayButtons[i][j] = new DayButton(myCalendar.getDate(i, j), myCalendar.getCurrentDate().getMonth(), myCalendar);
                branch.getChildren().add(dayButtons[i][j]);
                int finalI = i;
                int finalJ = j;
                dayButtons[i][j].setOnAction(e -> {
                    if(!dayButtons[finalI][finalJ].disabled())
                        resetStyle();
                    dayButtons[finalI][finalJ].handleClick(myCalendar);
                    System.out.println(myCalendar.getCurrentDate().toString());
                });
            }
            branch.setSpacing(5);
            branch.setAlignment(Pos.CENTER);
            grid.getChildren().add(branch);
        }
        grid.setSpacing(5);
    }

    public void resetStyle()
    {
        for(int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if(!dayButtons[i][j].disabled())
                    dayButtons[i][j].setDefaultStyle();
            }
        }
    }

}
