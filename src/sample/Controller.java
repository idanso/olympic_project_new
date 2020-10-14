package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class Controller implements Initializable {

    Model model = new Model();

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

    public void addItemsToMenu(){
        countryBox.getItems().clear();
        countryBox.getItems().addAll(model.getAllStateString());
        countryBox1.getItems().clear();
        countryBox1.getItems().addAll(model.getAllStateString());
        ArrayList <MenuItem> menulistArray = new ArrayList<>();
        countryMenu.getItems().clear();
        for (String i : model.getAllStateString()) {
            menulistArray.add(new MenuItem(i));
        }
        countryMenu.getItems().addAll(menulistArray);
        menulistArray.clear();
//        for (String i : model.getAllTournamentsString()) {
//            menulistArray.add(new MenuItem(i));
//        }
//        tournamentMenu.getItems().addAll(menulistArray);
    }
    public void editAthleteBtnsEvent (ActionEvent e) {
        String fullName;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("status massage");
        try {
            if (e.getSource() == athleteSubmitBtn) {
                fullName = fullNameRegisterInput.getText();
                SportTypeAthleteANDReferee sportType = (SportTypeAthleteANDReferee)sportTypeBox.getValue();
                String country = countryInput.getText();
                if (!fullName.isEmpty() && sportType != null && country != null) {
                    dialogMassage(model.addAthlete(fullName,country,sportType));
                    addItemsToMenu();
                    fullNameRegisterInput.clear();
                    sportTypeBox.getSelectionModel().clearSelection();
                    countryInput.clear();
                } else
                    dialogMassage(eDialogMassage.EMPTY);

            } else if (e.getSource() == athleteDeleteBtn) {
                fullName = fullNameDeleteInput.getText();
                System.out.println(fullName);
                if (!fullName.isEmpty()) {
                    dialogMassage(model.deleteAthlete(fullName));
                    fullNameDeleteInput.clear();
                }
                else
                    dialogMassage(eDialogMassage.EMPTY);
            }
        } catch (Exception e1){
            dialogMassage(eDialogMassage.FAILED);
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
        if (!name.isEmpty() && !location.isEmpty() && numOfSeats != 0){
            dialogMassage(model.addstadium(name,location,numOfSeats));
            stadiumSeatsInput.clear();
            stadiumLocationInput.clear();
            stadiumNameInput.clear();
        }
        else {
            stadiumSeatsInput.clear();
            stadiumLocationInput.clear();
            stadiumNameInput.clear();
            dialogMassage(eDialogMassage.EMPTY);
        }

    }

    public void tornamenBoxFill (ActionEvent e){
        String strTournamentType = tournamentType.getValue();
        String sportType = sportTypeBox1.getValue();
        if(sportType != null && strTournamentType != null){
            refereeBox.getItems().clear();
            refereeBox.getItems().addAll();//need to add the array of all matching referees
            if (countryBox.getSelectionModel() != null){
                athleteBox1.getItems().addAll();////need to add the array of all matching referees
            }
            if (countryBox1.getSelectionModel() != null){
                athleteBox2.getItems().addAll();////need to add the array of all matching referees
            }
        }
    }
    public void addRefereeBtn (ActionEvent e){
        String name = refereeFullNameInput1.getText();
        String country = refereeCountryInput.getText();
        SportTypeAthleteANDReferee sportType = sportTypeBox2.getValue();
        if (!name.isEmpty() && !country.isEmpty() && sportType != null ){
            dialogMassage(model.addReferee(name, country, sportType));
            addItemsToMenu();
        }
        else{
            dialogMassage(eDialogMassage.EMPTY);
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
        addItemsToMenu();
        tournamentType.getItems().addAll("solo", "group");
        sportTypeBox.getItems().addAll(SportTypeAthleteANDReferee.values());
        sportTypeBox1.getItems().addAll(SportTypeAthleteANDReferee.getAllSportTypes());
        sportTypeBox2.getItems().addAll(SportTypeAthleteANDReferee.values());
        stadiumBox.getItems().addAll();//need to add the array of all stadiums
        countryBox.getItems().addAll(model.getAllStateString());//need to add the array of all countrys
        countryBox1.getItems().addAll(model.getAllStateString());//need to add the array of all countrys

    }


    public void dialogMassage(eDialogMassage massage ){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("status massage");
        if (massage == eDialogMassage.EMPTY)
            alert.setHeaderText("please fill all the fields correctly");
        else if (massage == eDialogMassage.FAILED)
            alert.setHeaderText("somthing went wrong, try again and fill all fields");
        else if (massage == eDialogMassage.SUCCESS)
            alert.setHeaderText("added success!");
        else if(massage == eDialogMassage.IN_SYSTEM)
            alert.setHeaderText("not added, already exist in the system");
        alert.showAndWait();

    }
    

}
