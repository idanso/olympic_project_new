package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
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
    private ChoiceBox<String> athleteBox1;

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
    private Label tournamentAthleteLabel;

    @FXML
    private Label tournametTableViewRedLabel;;

    @FXML
    private TableView tournamentAthletesTapbleView;

    public void sceneSwitchEvent () throws IOException {//button switch to got to the  sample.fxml page
       model.switchScenes(changeToViewPage);
    }

    public void editAthleteBtnsEvent (ActionEvent e) {
        String fullName;
        String country;
        try {
            if (e.getSource() == athleteSubmitBtn) {
                fullName = fullNameRegisterInput.getText();
                SportTypeAthleteANDReferee athleteSportType = sportTypeBox.getValue();
                country = countryInput.getText();
                if (!fullName.isEmpty() && athleteSportType != null && !country.isEmpty()) {
                    dialogMassage(model.addAthlete(fullName,country,athleteSportType));
                    fullNameRegisterInput.clear();
                    sportTypeBox.getSelectionModel().clearSelection();
                    countryInput.clear();
                } else
                    dialogMassage(eDialogMassage.EMPTY);

            } else if (e.getSource() == athleteDeleteBtn) {
                fullName = fullNameDeleteInput.getText();
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

    public void emptyFieldsRedMassage(MouseEvent e){//triggered when clicked on a choiceBox who still not initialize cause it depends on other choiceboxes and popping up red massages under the relevant boxesin the "add tournament" tab
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
    }

    public void addTournamenBtn(ActionEvent e){
        String strTournamentType = tournamentType.getValue();
        SportTypeAthleteANDReferee sportType = sportTypeBox1.getValue();
        String referee = refereeBox.getValue();
        String stadium = stadiumBox.getValue();
        if (strTournamentType != null && sportType != null && (!model.tournametAthleteList.isEmpty() || !model.tournametStateList.isEmpty()) &&
                referee != null && stadium != null && (tournamentAthletesTapbleView.getItems().size() >= 3)){
            tournametTableViewRedLabel.setVisible(false);
            if (strTournamentType.equals("solo")) {
                dialogMassage(model.addTournament(sportType,model.getStadiumByName(stadium), model.getRefereeByName(referee),new ArrayList<Athlete> (model.tournametAthleteList)));
            }
            else
                dialogMassage(model.addTournament(sportType,model.getStadiumByName(stadium), model.getRefereeByName(referee), new ArrayList<State> (model.tournametStateList)));

        refereeBox.getSelectionModel().clearSelection();
        athleteBox1.getSelectionModel().clearSelection();
        countryBox.getSelectionModel().clearSelection();
        stadiumBox.getSelectionModel().clearSelection();
        tournamentType.getSelectionModel().clearSelection();
        sportTypeBox1.getSelectionModel().clearSelection();
        tournamentAthletesTapbleView.getColumns().clear();
        model.tournametAthleteList.clear();
        model.tournametStateList.clear();
        }
        else{
            dialogMassage(eDialogMassage.EMPTY);
            tournametTableViewRedLabel.setVisible(true);
        }
    }

    public void tornamenBoxFill (ActionEvent e){//filling the relevant choiceboxes according the relevant tournament and sport type in the "add tournament" tab
        if (e.getSource().equals(tournamentType) || e.getSource().equals(sportTypeBox1)){
            refereeBox.getSelectionModel().clearSelection();
            countryBox.getSelectionModel().clearSelection();
            athleteBox1.getSelectionModel().clearSelection();
            tournamentAthletesTapbleView.getItems().clear();
            model.tournametAthleteList.clear();
        }
        String strTournamentType = tournamentType.getValue();
        if(!e.getSource().equals(countryBox)) {
            if (strTournamentType != null && strTournamentType.equals("solo")) {
                athleteBox1.setVisible(true);
                tournamentAthleteLabel.setVisible(true);
                tournamentAthletesTapbleView.getColumns().clear();
                tournamentAthletesTapbleView.getColumns().addAll(new TableColumn("full name"), new TableColumn("country"), new TableColumn("sport type"));
                ((TableColumn) tournamentAthletesTapbleView.getColumns().get(0)).setPrefWidth(143);
                ((TableColumn) tournamentAthletesTapbleView.getColumns().get(1)).setPrefWidth(135);
                ((TableColumn) tournamentAthletesTapbleView.getColumns().get(2)).setPrefWidth(172);
            } else {
                athleteBox1.setVisible(false);
                tournamentAthleteLabel.setVisible(false);
                tournamentAthletesTapbleView.getColumns().clear();
                tournamentAthletesTapbleView.getColumns().add(new TableColumn("country"));
                ((TableColumn) tournamentAthletesTapbleView.getColumns().get(0)).setPrefWidth(180);
            }
        }
        SportTypeAthleteANDReferee sportType = sportTypeBox1.getValue();
        if (sportType != null)
            sportTypeRedLabel.setText("");
        if (strTournamentType != null)
            tournamentRedLabel.setText("");
        if(sportType != null && strTournamentType != null){
            if(!e.getSource().equals(countryBox)) {
                countryBox.getItems().clear();
                countryBox.getItems().addAll(model.getStatesBysportTypeString(sportType));
            }
            if(!e.getSource().equals(countryBox)) {
                refereeBox.getItems().clear();
                refereeBox.getItems().addAll(model.getRefereesBySportTypeString(sportType));
            }
            String country = countryBox.getValue();
            if (country != null) {
                countryRedLabel.setText("");
                if (e.getSource().equals(countryBox) && strTournamentType.equals("solo")) {
                    athleteBox1.getItems().clear();
                    athleteBox1.getItems().addAll(model.getAthletesByCountryAndSportType(country, sportType));
                }
            }
        }
    }

    public void toutnamentAddToTableView(ActionEvent e){// filling the table view of the athletes/states (depends on the tournament type) when clicked on the add button in the "add tournament" tab
        if (tournamentType.getValue() != null && sportTypeBox1.getValue() != null) {
            if (tournamentType.getValue().equals("solo")) {
                String country = countryBox.getValue();
                String athlete = athleteBox1.getValue();
                if (country != null && athlete != null) {
                    if (!model.tournametAthleteList.contains(model.getStateByName(country).getAthleteByName(athlete))) {
                        model.tournametAthleteList.add(model.getStateByName(country).getAthleteByName(athlete));
                        ((TableColumn) tournamentAthletesTapbleView.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<Athlete, String>("name"));
                        ((TableColumn) tournamentAthletesTapbleView.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<Athlete, String>("stateString"));
                        ((TableColumn) tournamentAthletesTapbleView.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<Athlete, SportTypeAthleteANDReferee>("sportType"));
                        tournamentAthletesTapbleView.setItems(model.tournametAthleteList);
                    } else dialogMassage(eDialogMassage.IN_SYSTEM);
                } else dialogMassage(eDialogMassage.EMPTY);
            } else {
                String country = countryBox.getValue();
                if (country != null) {
                    if (!model.tournametStateList.contains(model.getStateByName(country))) {
                        model.tournametStateList.add(model.getStateByName(country));
                        ((TableColumn) tournamentAthletesTapbleView.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<State, String>("name"));
                        tournamentAthletesTapbleView.setItems(model.tournametStateList);
                    } else dialogMassage(eDialogMassage.IN_SYSTEM);
                } else dialogMassage(eDialogMassage.EMPTY);
            }
        }
        else dialogMassage(eDialogMassage.EMPTY);
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
        tournamentType.getItems().addAll("solo", "team");
        sportTypeBox.getItems().addAll(SportTypeAthleteANDReferee.values());
        sportTypeBox1.getItems().addAll(SportTypeAthleteANDReferee.getAllSportTypes());
        sportTypeBox2.getItems().addAll(SportTypeAthleteANDReferee.values());
        stadiumBox.getItems().addAll(model.getStadiumString());
        athleteBox1.setVisible(false);
        tournamentAthleteLabel.setVisible(false);
        tournametTableViewRedLabel.setVisible(false);
    }

    public void dialogMassage(eDialogMassage massage ){//getting an edialogMassage from any addButton function and show alert with a right massage
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
        else if(massage == eDialogMassage.DELETE_SUCCESS)
            alert.setHeaderText("athlete delete from system");
        alert.showAndWait();
    }
}
