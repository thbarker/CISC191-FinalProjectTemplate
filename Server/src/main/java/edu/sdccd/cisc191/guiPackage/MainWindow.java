package edu.sdccd.cisc191.guiPackage;

import edu.sdccd.cisc191.calendarPackage.CalendarController;
import edu.sdccd.cisc191.calendarPackage.Event;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 FIXES and Train of Thought : Main problem right now arises from the fact that the buttons cannot set on action
 Right now I do not have the daybutton array from the CalendarController class linked with the buttons in the grid
 This is what needs to be fixed
 */

public class MainWindow extends Application {
    private HeaderLabel month, day;
    private DaysOfWeek days;

    private VBox eventArea;
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

        HBox root = new HBox(); //Root to be set to the scene

        /*
        Below is the code for the left hand side display of the virtual calendar
         */
        BorderPane mainLayout = new BorderPane(); //Borderpane to contain the whole calendar view
        VBox headerRoot = new VBox(); //VBox to contain the header and the days of the week
        HBox headerLabelBox = new HBox(); //HBox to contain the month name label, next, back buttons


        month = new HeaderLabel(); //Label that contains the month name at the top of the calendar
        days = new DaysOfWeek(); //HBOox that contains all the days of the week below the month name

        day = new HeaderLabel(); //Label that contains the day at the top of the sidebar
        day.setAlignment(Pos.CENTER_LEFT);
        day.setPadding(new Insets(0));

        eventArea = new VBox();
        initEventArea();

        grid = new VBox();
        update();

        BackButton back = new BackButton();
        NextButton next = new NextButton();

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
        mainLayout.setCenter(grid);

        headerLabelBox.getChildren().addAll(back, month, next);
        headerRoot.getChildren().addAll(headerLabelBox, days);

        headerLabelBox.setAlignment(Pos.CENTER);


        /*
        Below is the code for the right hand side panel to display the current day and calendar manipulation
         */

        VBox sideBar = new VBox(); //HBox to contain bottom buttons

        Button addEvent = new Button();
        Button todayButton = new Button();

        styleButton(addEvent);
        styleButton(todayButton);

        addEvent.setText("Add Event");
        todayButton.setText("Go to Today");

        TextField titleField = new TextField();
        titleField.setText("New Event");
        titleField.setMaxWidth(300);

        TextField locationField = new TextField();
        locationField.setPromptText("Location");
        locationField.setMaxWidth(300);

        styleTextField(titleField);
        styleTextField(locationField);

        addEvent.setOnAction(e -> {
            Date temp = new Date(myCalendar.getCurrentDate().getTime());
            myCalendar.addEvent(new Event(titleField.getText(), locationField.getText(), temp));
            update();
        });
        todayButton.setOnAction(e -> {
            myCalendar.today();
            update();
        });

        sideBar.getChildren().addAll(day, eventArea, todayButton, titleField, locationField, addEvent);

        sideBar.setAlignment(Pos.TOP_CENTER);
        sideBar.setSpacing(20);
        sideBar.setPadding(new Insets(20));

        sideBar.setStyle("-fx-background-color: rgb(218,218,218)");

        /*
        Below is the code to actually add both sections to the root layout and create the main scene
         */

        root.getChildren().addAll(mainLayout, sideBar);
        mainScene = new Scene(root, 1290, 800);
    }

    public void update()
    {
        month.setText(myCalendar.getMonthAlpha() + " " + Integer.toString(myCalendar.getCurrentYear()));
        setDayLabel();
        initEventArea();

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
                    setDayLabel();
                    initEventArea();
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

    public void styleButton(Button button)
    {
        button.setStyle("-fx-background-color: rgb(234,234,234);" +
                " -fx-border-color: #1a1a1a; " +
                "-fx-border-width: 1px; " +
                "-fx-pref-width: 300px; " +
                "-fx-pref-height: 30px;");
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: rgba(0,0,0,0.11);" +
                    " -fx-border-color: #1a1a1a; " +
                    "-fx-border-width: 1px; " +
                    "-fx-pref-width: 300px; " +
                    "-fx-pref-height: 30px;");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: rgb(234,234,234);" +
                    " -fx-border-color: #1a1a1a; " +
                    "-fx-border-width: 1px; " +
                    "-fx-pref-width: 300px; " +
                    "-fx-pref-height: 30px;");
        });
    }

    public void styleTextField(TextField field)
    {
        field.setStyle("-fx-background-color: rgb(255,255,255);" +
                " -fx-border-color: #1a1a1a; " +
                "-fx-border-width: 1px; " +
                "-fx-pref-width: 300px; " +
                "-fx-pref-height: 30px;");
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[a-zA-Z0-9 !/?]*")) {
                return change;
            } else {
                return null;
            }
        });

        field.setTextFormatter(formatter);
    }

    public void setDayLabel()
    {
        String temp;
        switch(myCalendar.getCurrentDate().getDay())
        {
            case 0:
                 temp = "Sunday"; break;
            case 1:
                temp = "Monday"; break;
            case 2:
                temp = "Tuesday"; break;
            case 3:
                temp = "Wednesday"; break;
            case 4:
                temp = "Thursday"; break;
            case 5:
                temp = "Friday"; break;
            default:
                temp = "Saturday"; break;
        }
        day.setText(temp + ", " +
                (myCalendar.getMonthAlpha()) +
                " " + myCalendar.getCurrentDate().getDate() + ", " +
                (myCalendar.getCurrentDate().getYear() + 1900));
        day.setFont(new Font("Cambria", 20));
    }

    public void initEventArea()
    {
        eventArea.setMaxWidth(300);
        eventArea.setMaxHeight(400);
        eventArea.setMinHeight(400);
        eventArea.setMaxWidth(300);
        eventArea.setMaxWidth(300);
        eventArea.setSpacing(10);

        eventArea.getChildren().clear();

        ArrayList<String> temp  = new ArrayList();
        temp = myCalendar.getCurrentEvents();
        for(int i = 0; i < temp.size(); i++)
        {
            int spot = temp.get(i).indexOf(",");
            Label title = new Label(temp.get(i).substring(0,spot));
            title.setFont(new Font("Cambria", 15));
            Label location = new Label("Location: "+temp.get(i).substring(spot+1,temp.get(i).length()));
            location.setFont(new Font("Cambria", 10));

            VBox smallBox = new VBox();
            smallBox.getChildren().addAll(title,location);
            eventArea.getChildren().add(smallBox);
        }
    }

}
