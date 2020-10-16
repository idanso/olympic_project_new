package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;



public class EditController implements Initializable {

    Model model = new Model();

    @FXML
    private Button changeToViewPage;

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
    private ChoiceBox<SportTypeAthleteANDReferee> sportTypeBox1;

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
    private TextField stadiumNameInput;

    @FXML
    private TextField stadiumLocationInput;

    @FXML
    private TextField stadiumSeatsInput;

    @FXML
    private Label seatsInputExceptionLabel;

    @FXML
    private TextField refereeFullNameInput;

    @FXML
    private ChoiceBox<SportTypeAthleteANDReferee> sportTypeBox2;

    @FXML
    private TextField refereeCountryInput;

    @FXML
    private Label tournamentRedLabel;

    @FXML
    private Label sportTypeRedLabel;

    @FXML
    private Label countryRedLabel;

    @FXML
    private Label country1RedLabel;


    public void sceneSwitchEvent (ActionEvent event) throws IOException {
        Parent root;
        Stage stage;
        stage = (Stage) changeToViewPage.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addItemsToMenu(){
        countryBox.getItems().clear();
        countryBox.getItems().addAll(model.getAllStatesWithAthletes());
        countryBox1.getItems().clear();
        countryBox1.getItems().addAll(model.getAllStatesWithAthletes());
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
            stadiumBox.getItems().clear();
            stadiumBox.getItems().addAll(model.getStadiumString());
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
    public void emptyFieldsRedMassage(MouseEvent e){
        String str1TournamentType = tournamentType.getValue();
        SportTypeAthleteANDReferee sportType1 = sportTypeBox1.getValue();
        if(sportType1 == null) {
            sportTypeRedLabel.setText("required field first");
            sportTypeRedLabel.setTextFill(Paint.valueOf("red"));
        }
        if (str1TournamentType == null){
            tournamentRedLabel.setText("required field first");
            tournamentRedLabel.setTextFill(Paint.valueOf("red"));
        }
        if(e.getSource().equals(athleteBox1) && countryBox.getValue() == null){
            countryRedLabel.setText("required field first");
            countryRedLabel.setTextFill(Paint.valueOf("red"));
        }
        if(e.getSource().equals(athleteBox2) && countryBox1.getValue() == null){
            country1RedLabel.setText("required field first");
            country1RedLabel.setTextFill(Paint.valueOf("red"));
        }

    }
    public void addTournamenBtn(ActionEvent e){
        String strTournamentType = tournamentType.getValue();
        SportTypeAthleteANDReferee sportType = sportTypeBox1.getValue();
        String country = countryBox.getValue();
        String country1 = countryBox1.getValue();
        String athlete1 = athleteBox1.getValue();
        String athlete2 = athleteBox1.getValue();
        String referee = refereeBox.getValue();
        String stadium = stadiumBox.getValue();
        if (!strTournamentType.equals(null) && !sportType.equals(null) && !country.equals(null) &&
                !country1.equals(null) && !athlete1.equals(null) && !athlete2.equals(null) &&
                !referee.equals(null) && !stadium.equals(null)){
            //need to add reference to build tournament
        }
        else
            dialogMassage(eDialogMassage.EMPTY);
    }

    public void tornamenBoxFill (ActionEvent e){
        if (e.getSource().equals(tournamentType) || e.getSource().equals(sportTypeBox1)){
            refereeBox.getSelectionModel().clearSelection();
            countryBox.getSelectionModel().clearSelection();
            countryBox1.getSelectionModel().clearSelection();
            athleteBox1.getSelectionModel().clearSelection();
            athleteBox2.getSelectionModel().clearSelection();
        }
        String strTournamentType = tournamentType.getValue();
        SportTypeAthleteANDReferee sportType = sportTypeBox1.getValue();
        if (sportType != null)
            sportTypeRedLabel.setText("");
        if (strTournamentType != null)
            tournamentRedLabel.setText("");
        if(sportType != null && strTournamentType != null){
            refereeBox.getItems().clear();
            refereeBox.getItems().addAll(model.getRefereesBySportTypeString(sportType));
            String country = countryBox.getValue();
            String country1 = countryBox1.getValue();
            if (tournamentType.getValue().equals("group")) {
                athleteBox1.getItems().clear();
                athleteBox1.getItems().add("The team");
                athleteBox2.getItems().clear();
                athleteBox2.getItems().add("The team");
            }
            else {
                if (country != null) {
                    countryRedLabel.setText("");
                    if (e.getSource().equals(countryBox)) {
                        athleteBox1.getItems().clear();
                        athleteBox1.getItems().addAll(model.getAthletesByCountryAndSportType(country, sportType));
                    }
                }
                if (country1 != null) {
                    country1RedLabel.setText("");
                    if (e.getSource().equals(countryBox1)) {
                        athleteBox2.getItems().clear();
                        athleteBox2.getItems().addAll(model.getAthletesByCountryAndSportType(country1, sportType));
                    }
                }

            }
        }
    }

    public void addRefereeBtn (ActionEvent e){
        String name = refereeFullNameInput.getText();
        String country = refereeCountryInput.getText();
        SportTypeAthleteANDReferee sportType = sportTypeBox2.getValue();
        if (!name.isEmpty() && !country.isEmpty() && sportType != null ){
            dialogMassage(model.addReferee(name, country, sportType));
            refereeFullNameInput.clear();
            refereeCountryInput.clear();
            sportTypeBox2.getSelectionModel().clearSelection();
            addItemsToMenu();
        }
        else{
            dialogMassage(eDialogMassage.EMPTY);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tournamentType.getItems().clear();
        sportTypeBox.getItems().clear();
        sportTypeBox1.getItems().clear();
        sportTypeBox2.getItems().clear();
        stadiumBox.getItems().clear();
        countryBox.getItems().clear();
        countryBox1.getItems().clear();
        addItemsToMenu();
        tournamentType.getItems().addAll("solo", "group");
        sportTypeBox.getItems().addAll(SportTypeAthleteANDReferee.values());
        sportTypeBox1.getItems().addAll(SportTypeAthleteANDReferee.getAllSportTypes());
        sportTypeBox2.getItems().addAll(SportTypeAthleteANDReferee.values());
        stadiumBox.getItems().addAll(model.getStadiumString());
        countryBox.getItems().addAll(model.getAllStatesWithAthletes());
        countryBox1.getItems().addAll(model.getAllStatesWithAthletes());
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
