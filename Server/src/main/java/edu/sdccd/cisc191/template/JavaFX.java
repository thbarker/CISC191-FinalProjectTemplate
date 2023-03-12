package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class JavaFX extends Application {
    /**
     * @param event from .setOnAction
     * @param toTransfer car to be transferred
     * @param transferDealership location of dealership receiving car
     * @param dealership dealership sending car
     * @param allDealerships arraylist of all dealerships
     * @param grid used for rendering
     *             this loops though all dealerships until it finds the one with the
     *             same location passed though the parameter. The car is then
     *             added and removed from the respective dealerships,and the display is re-rendered.
     */
    private void transferDealership(ActionEvent event, Car toTransfer, String transferDealership, Dealership dealership, ArrayList<Dealership> allDealerships, GridPane grid){

        for(int transferIndex = 0; transferIndex<allDealerships.size(); transferIndex++){
            if(allDealerships.get(transferIndex).getLocation().equals(transferDealership)){
                dealership.removeCar(toTransfer);
                allDealerships.get(transferIndex).addCar(toTransfer);
                grid.getChildren().clear();
                renderDealership(dealership, grid, allDealerships);
            }
            
        }

    };

    /**
     * @param dealership dealership to be rendered
     * @param grid parent element
     * @param allDealerships arraylist of all dealerships
     *  This renders the cars and labels for the dealership.
     */
    public void renderDealership(Dealership dealership,  GridPane grid, ArrayList<Dealership> allDealerships){
        ArrayList<Car> allCars = dealership.getAllCars();
        grid.add(new Label("Brand"), 0, 0);
        grid.add(new Label("Model"), 1, 0);
        grid.add(new Label("Year"), 2, 0);
        grid.add(new Label("Miles"), 3, 0);

        //get strings to be used in combobox
        String d1 = "";
        String d2 = "";
        if(dealership.getLocation().equals("San Diego")){
            d1 = "Los Angeles";
            d2 = "San Francisco";
        }
        if(dealership.getLocation().equals("Los Angeles")){
            d1 = "San Diego";
            d2 = "San Francisco";
        }
        if(dealership.getLocation().equals("San Francisco")){
            d1 = "San Diego";
            d2 = "Los Angeles";
        }
        //loops though each car and renders a row for each.
        for(int i =0; i<allCars.size(); i++){
            //get all information
            String modelString = allCars.get(i).getBrand();
            Label modelLabel = new Label(modelString);
            grid.add(modelLabel, 0, i+1);

            String brandString = allCars.get(i).model;
            Label brandLabel = new Label(brandString);
            grid.add(brandLabel, 1, i+1);

            String yearString = allCars.get(i).getYear();
            Label yearLabel = new Label(yearString);
            grid.add(yearLabel, 2, i+1);

            String milesString = allCars.get(i).getMiles();
            Label milesLabel = new Label(milesString);
            grid.add(milesLabel, 3, i+1);

            //transferring label and combobox
            grid.add(new Label("Transfer to:"), 4, i+1);
            ComboBox transferDealership = new ComboBox<>();
            transferDealership.getItems().add(d1);
            transferDealership.getItems().add(d2);
            grid.add(transferDealership, 5, i+1);

            //button for transfer, bound to the transfer dealership method
            Button transferButton = new Button("Transfer");
            Car toTransfer = allCars.get(i);
            transferButton.setOnAction(e -> transferDealership(e, toTransfer, transferDealership.getValue().toString(), dealership, allDealerships, grid));
            grid.add(transferButton, 6, i+1);
        }
    }

    /**
     * @param allDealerships arraylist of all dealerships
     * @return returns the oldest car in all dealerships.
     */
    public static Car getOldestCar(ArrayList<Dealership> allDealerships){
        //get first car in 2d array
        Car minCar = allDealerships.get(0).getAllCars().get(0);
        //loop though array of dealerships
        for(int r=0; r<allDealerships.size(); r++){
            //get all cars in each dealership
            ArrayList<Car> allDealershipCars = allDealerships.get(r).getAllCars();
            //loop though each car
            for(int c=0; c<allDealershipCars.size(); c++){
                //check if current car year is older than current youngest.
                Car currentCar = allDealershipCars.get(c);
                if(parseInt(minCar.getYear())>parseInt(currentCar.getYear())){
                    //set the youngest car to current car
                    minCar = currentCar;
                }
            }
        }
        return minCar;
    }

    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException
     * this method is called by javaFX to create the first render.
     */
    @Override
    public void start(Stage stage) throws IOException {

        //creating dealerships
        Dealership dealership1 = new Dealership("San Diego");
        Dealership dealership2 = new Dealership("Los Angeles");
        Dealership dealership3 = new Dealership("San Francisco");

        dealership1.addCar(new Ford("F-150",2017, 20000));
        dealership1.addCar(new Ford("Mustang",2012, 60000));
        dealership1.addCar(new Toyota("Corolla",2008, 120000));
        dealership1.addCar(new Toyota("Camry",2020, 20000));

        dealership2.addCar(new Honda("CR-V", 2015, 55000));
        dealership2.addCar(new Honda("Pilot", 2022, 5000));
        dealership2.addCar(new Ford("Explorer", 2016, 90000));

        dealership3.addCar(new Toyota("Prius", 2007, 115000));
        dealership3.addCar(new Honda("Accord", 2017, 85000));
        dealership3.addCar(new Ford("Bronco", 2019, 40000));

        ArrayList<Dealership> allDealerships = new ArrayList<>();
        allDealerships.add(dealership1);
        allDealerships.add(dealership2);
        allDealerships.add(dealership3);

        //creating the 3 grid panes. They are seperated in order to allow for
        // efficient re-rendering

        GridPane headerGrid = new GridPane();
        headerGrid.setAlignment(Pos.TOP_LEFT);
        headerGrid.setHgap(10);
        headerGrid.setVgap(10);
        headerGrid.setPadding(new Insets(5, 5, 5, 5));

        GridPane dealershipGrid = new GridPane();
        dealershipGrid.setAlignment(Pos.TOP_LEFT);
        dealershipGrid.setHgap(10);
        dealershipGrid.setVgap(10);
        dealershipGrid.setPadding(new Insets(5, 5, 5, 5));

        GridPane actionsGrid = new GridPane();
        actionsGrid.setAlignment(Pos.TOP_LEFT);
        actionsGrid.setHgap(10);
        actionsGrid.setVgap(10);
        actionsGrid.setPadding(new Insets(5, 5, 5, 5));

        //creates the header with the dealership and sort combo-boxes, never re-renders
        Label criteriaLabel = new Label("Select Dealership:");
        headerGrid.add(criteriaLabel, 0, 0);
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add(dealership1.getLocation());
        comboBox.getItems().add(dealership2.getLocation());
        comboBox.getItems().add(dealership3.getLocation());
        comboBox.setValue(dealership1.getLocation());
        ComboBox sortComboBox = new ComboBox();
        sortComboBox.getItems().add("Brand");
        sortComboBox.getItems().add("Model");
        sortComboBox.getItems().add("Year");
        sortComboBox.getItems().add("Miles");
        sortComboBox.setValue("Brand");
        //event handler for both combo-boxes. Gets value from both and re-renders
        //based on the values.
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                for(int i = 0; i<allDealerships.size(); i++){
                    if(allDealerships.get(i).getLocation() == comboBox.getValue()){
                        Dealership currentDealership = allDealerships.get(i);
                        currentDealership.sort(sortComboBox.getValue().toString());
                        dealershipGrid.getChildren().clear();
                        renderDealership(allDealerships.get(i), dealershipGrid, allDealerships);
                    }
                }
            }
        };

        // Set on action
        comboBox.setOnAction(event);
        headerGrid.add(comboBox, 1, 0);
        headerGrid.add(new Label("Sort Criteria: "),2,0);
        sortComboBox.setOnAction(event);
        headerGrid.add(sortComboBox,3,0);

        //renders the dealership information
        renderDealership(dealership1, dealershipGrid, allDealerships);

        //add car gui
        actionsGrid.add(new Label("Brand:"),0,1);
        TextField brandInput = new TextField();
        actionsGrid.add(brandInput,1,1);

        actionsGrid.add(new Label("Model:"),0,2);
        TextField modelInput = new TextField();
        actionsGrid.add(modelInput,1,2);

        actionsGrid.add(new Label("Year:"),0,3);
        TextField yearInput = new TextField();
        actionsGrid.add(yearInput,1,3);

        actionsGrid.add(new Label("Miles:"),0,4);
        TextField milesInput = new TextField();
        actionsGrid.add(milesInput,1,4);
        //event handler for adding car, gets value from input fields and
        //creates a car which is added to the dealership. the dealership is then re-rendered
        EventHandler<ActionEvent> addCar = e -> {

            for(int i = 0; i<allDealerships.size(); i++){
                if(allDealerships.get(i).getLocation() == comboBox.getValue()){
                    Dealership currentDealership = allDealerships.get(i);
                    if(brandInput.getText().toLowerCase().equals("ford")){
                        currentDealership.addCar(new Ford(modelInput.getText(), parseInt(yearInput.getText()), parseInt(milesInput.getText())));
                    }
                    if(brandInput.getText().toLowerCase().equals("honda")){
                        currentDealership.addCar(new Honda(modelInput.getText(), parseInt(yearInput.getText()), parseInt(milesInput.getText())));
                    }
                    if(brandInput.getText().toLowerCase().equals("toyota")){
                        currentDealership.addCar(new Toyota(modelInput.getText(), parseInt(yearInput.getText()), parseInt(milesInput.getText())));
                    }
                    currentDealership.sort(sortComboBox.getValue().toString());
                    dealershipGrid.getChildren().clear();
                    renderDealership(allDealerships.get(i), dealershipGrid, allDealerships);
                }
            }
        };
        Label oldestCarText = new Label("");
        actionsGrid.add(oldestCarText, 1, 6);
        Button addButton = new Button("Add car");
        addButton.setOnAction(addCar);
        actionsGrid.add(addButton,0,5);
        EventHandler<ActionEvent> oldestCarAction = e -> {
            Car oldestCar = getOldestCar(allDealerships);
           oldestCarText.setText(oldestCar.getYear()+" "+oldestCar.getBrand()+" "+oldestCar.getModel()+" with "+oldestCar.getMiles()+ " miles");
        };

        Button oldestButton = new Button("Get oldest car of all dealerships");
        oldestButton.setOnAction(oldestCarAction);
        actionsGrid.add(oldestButton, 0, 6);

        //the three grids are then added to a vbox, and rendered to the scene
        VBox vbox = new VBox(headerGrid, dealershipGrid, actionsGrid);
        Scene scene = new Scene(vbox, 500, 500);
        stage.setTitle("Dealership Manager");
        stage.setScene(scene);
        stage.show();
    }
    //used to launch javaFX
    public static void main(String[] args) {
        launch();
    }
}