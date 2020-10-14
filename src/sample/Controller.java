package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private Button changeTomanagerPage;

    @FXML
    private TabPane tapPaneSummery;

    @FXML
    private Tab viewTournamentsTap;

    @FXML
    private MenuButton tournamentMenu;

    @FXML
    private TableView<?> rournamentsTableView;

    @FXML
    private TableColumn<?, ?> tournamentTypeCol;

    @FXML
    private TableColumn<?, ?> tornamentParticipentsCol;

    @FXML
    private TableColumn<?, ?> tornamentRefereeCol;

    @FXML
    private TableColumn<?, ?> tornamentLocationCol;

    @FXML
    private Tab viewAthletesTap;

    @FXML
    private MenuButton countryMenu;

    @FXML
    private TableView<?> athletesTableView;

    @FXML
    private TableColumn<?, ?> athlleteNameCol;

    @FXML
    private TableColumn<?, ?> athleteMedalsCol;

    @FXML
    private TableColumn<?, ?> athleteTypecol;

    @FXML
    private Tab viewMedalsTab;

    @FXML
    private Label countryFirstLable;

    @FXML
    private Label numOfMedalsFirstLable;

    @FXML
    private Label countrySecondLable;

    @FXML
    private Label numOfMedalsSecondLable;

    @FXML
    private Label countryThirdLable;

    @FXML
    private Label numOfMedalsThirdLable;

    @FXML
    private Button athleteSubmitBtn;

    @FXML
    private TextField fullNameRegisterInput;

    @FXML
    private TextField fullNameDeleteInput;

    @FXML
    private Button athleteDeleteBtn;

    @FXML
    private ChoiceBox<SportTypeAthleteANDReferee> sportTypeBox;

    @FXML
    private TextField countryInput;

    @FXML
    private ChoiceBox<String> tournamentType;

    @FXML
    private ChoiceBox<String> sportTypeBox1;

    @FXML
    private ChoiceBox<String> refereeBox;

    @FXML
    private ChoiceBox<String> stadiumBox;

    @FXML
    private ChoiceBox<String> countryBox;

    @FXML
    private ChoiceBox<String> countryBox1;

    @FXML
    private ChoiceBox<String> athleteBox1;

    @FXML
    private ChoiceBox<String> athleteBox2;

    @FXML
    private Button createTornamentBtn;

    @FXML
    private TextField stadiumNameInput;

    @FXML
    private TextField stadiumLocationInput;

    @FXML
    private TextField stadiumSeatsInput;

    @FXML
    private Button addStadiumBtn;

    @FXML
    private Label seatsInputExceptionLabel;

    @FXML
    private Button refereeSubmitBtn;

    @FXML
    private TextField refereeFullNameInput;

    @FXML
    private TextField refereeFullNameInput1;

    @FXML
    private ChoiceBox<SportTypeAthleteANDReferee> sportTypeBox2;

    @FXML
    private TextField refereeCountryInput;


    /*public void sceneSwitchEvent (ActionEvent event) throws IOException {
        Parent root;
        Stage stage;
        stage = (Stage) changeTomanagerPage.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Olympic_project/Olympic_project/src/sample/sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    public void menuButtonEvent (String tornamentType){
        //  tournamentTypeCol.setCellValueFactory(new PropertyValueFactory<>());
    }
    public void editAthleteBtnsEvent (ActionEvent e) {
        String fullName;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean actionStatus = false;//to change if needed
        alert.setTitle("status massage");
        try {
            if (e.getSource() == athleteSubmitBtn) {
                fullName = fullNameRegisterInput.getText();
                SportTypeAthleteANDReferee sportType = (SportTypeAthleteANDReferee)sportTypeBox.getValue();
                String country = countryInput.getText();
                if (fullName != null && sportType != null && country != null) {
                    //**********send to delete player function*************
                    // boolean = (retunr if player added or not) need to send to th add athlete function
                    if (actionStatus)
                        alert.setHeaderText("Athlete added successfully!");
                    else
                        alert.setHeaderText("something went wrong, athlete didn't added");
                    alert.showAndWait();
                } else
                    emptyFieldsDialog();
            } else if (e.getSource() == athleteDeleteBtn) {
                fullName = fullNameDeleteInput.getText();
                if (fullNameDeleteInput != null) {
                    //**********send to delete player function*************
                    if (actionStatus)
                        alert.setHeaderText("Athlete delete sucesfully!");
                    else
                        alert.setHeaderText("somthing went wrong, athlete didn't delete");
                    alert.showAndWait();
                }
                else
                    emptyFieldsDialog();
            }
        } catch (Exception e1){
            alert.setHeaderText("somthing went wrong, try again and fill all " +
                    "fields");
            alert.showAndWait();
        }
    }

    public void addStadiumBtn (ActionEvent e){
        seatsInputExceptionLabel.setText("");
        String name, location;
        int numOfSeats = 0;
        name = stadiumNameInput.getText();
        location = stadiumLocationInput.getText();

        try {
            numOfSeats = Integer.valueOf(stadiumSeatsInput.getText());
            if (numOfSeats == 0)
                throw new Exception("the number zero not allowed");
        } catch(Exception exception) {
            seatsInputExceptionLabel.setTextFill(Paint.valueOf("Red"));
            seatsInputExceptionLabel.setText("enter only numbers and no zero");
            stadiumSeatsInput.clear();

        }
        if (name != null && location != null && numOfSeats != 0){
            //************to fill function to add stadium to system*************
            stadiumSeatsInput.clear();
            stadiumLocationInput.clear();
            stadiumNameInput.clear();
        }
        else {
            stadiumSeatsInput.clear();
            stadiumLocationInput.clear();
            stadiumNameInput.clear();
            emptyFieldsDialog();
        }

    }

    public void tornamenBoxFill (ActionEvent e){
        String strTournamentType = tournamentType.getValue();
        String sportType = sportTypeBox1.getValue();
        if(sportType != null && strTournamentType != null){
            refereeBox.getItems().clear();
            refereeBox.getItems().addAll();//need to add the array of all matching referees
            if (countryBox != null){
                athleteBox1.getItems().addAll();////need to add the array of all matching referees
            }
            if (countryBox1 != null){
                athleteBox2.getItems().addAll();////need to add the array of all matching referees
            }
        }
    }
    public void addRefereeBtn (ActionEvent e){
        String name = refereeFullNameInput1.getText();
        String country = refereeCountryInput.getText();
        SportTypeAthleteANDReferee sportType = sportTypeBox2.getValue();
        if (name != null && country != null && sportType != null ){

        }
        else{

        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList <MenuItem> menulistArray = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            menulistArray.add(new MenuItem("tournament type num" + (i+1)));//need to add refference to all tournament types array
            menulistArray.get(i).setId("type" + i);

            menulistArray.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(((MenuItem)event.getSource()).getId());
                    System.out.println(((MenuItem)event.getSource()).getText());
                    menuButtonEvent(((MenuItem)event.getSource()).getText());
                }
            });
        }
        tournamentMenu.getItems().addAll(menulistArray);

        for (int i = 0; i < 5; i++) {
            menulistArray.add(new MenuItem("country num " + (i+1)));//need to add refference to all countrys array
        }
        countryMenu.getItems().addAll(menulistArray);
        tournamentType.getItems().addAll("solo", "group");
        sportTypeBox.getItems().addAll(SportTypeAthleteANDReferee.values());
        sportTypeBox1.getItems().addAll(SportTypeAthleteANDReferee.getAllSportTypes());
        sportTypeBox2.getItems().addAll(SportTypeAthleteANDReferee.values());
        stadiumBox.getItems().addAll();//need to add the array of all stadiums
        countryBox.getItems().addAll();//need to add the array of all countrys
        countryBox1.getItems().addAll();//need to add the array of all countrys

    }

    public void emptyFieldsDialog (){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("status massage");
        alert.setHeaderText("please fill all the fields correctly");
        alert.showAndWait();
    }

}
