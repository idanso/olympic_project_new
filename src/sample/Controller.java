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
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private Button changeTomanagerPage;

    @FXML
    private Button submitBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TabPane tapPaneSummery;

    @FXML
    private Tab viewTournamentsTap;

    @FXML
    private MenuButton tournamentMenu;

    @FXML
    private TableView<?> tournamentsTableView;

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
    private TextField countryInput;

    @FXML
    private ChoiceBox sportTypeBox;

    @FXML
    private TableView<?> athletesTableView;

    @FXML
    private TableColumn<?, ?> athlleteNameCol;

    @FXML
    private TableColumn<?, ?> athleteMedalsCol;

    @FXML
    private TableColumn<?, ?> athleteTypecol;

    @FXML
    private Tab viewRefereesTab;

    @FXML
    private TextField fullNameRegisterInput;

    @FXML
    private TextField fullNameDeleteInput;

    @FXML
    private TableView<?> refereeTableView;

    @FXML
    private TableColumn<?, ?> refereeNameCol;

    @FXML
    private TableColumn<?, ?> refereeTypeCol;

    @FXML
    private Tab viewMedalsTab;

    @FXML
    private TableView<?> medalsTableView;

    @FXML
    private TableColumn<?, ?> medalCountryNameCol;

    @FXML
    private TableColumn<?, ?> medalAllMedalsCol;

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
    public void buttonEvent (ActionEvent e) {
        String fullName;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean actionStatus = true;//to change if needed
        alert.setTitle("status massage");
        try {
            if (e.getSource() == submitBtn) {
                fullName = fullNameRegisterInput.getText();
                SportTypeAthleteANDReferee sportType = (SportTypeAthleteANDReferee)sportTypeBox.getValue();
                System.out.println(sportType);
                String country = countryInput.getText();
                // boolean = (retunr if player added or not) need to send to th add athlete function
                if (actionStatus)
                    alert.setHeaderText("Athlete added sucesfully!");
                else
                    alert.setHeaderText("somthing went wrong, athlete didn't added");
                alert.showAndWait();

            } else if (e.getSource() == deleteBtn) {
                fullName = fullNameDeleteInput.getText();
                //send to delete player function
                if (actionStatus)
                    alert.setHeaderText("Athlete delete sucesfully!");
                else
                    alert.setHeaderText("somthing went wrong, athlete didn't delete");
                alert.showAndWait();
            }
        } catch (Exception e1){
            alert.setHeaderText("somthing went wrong, try again and fill all " +
                    "fields");
            alert.showAndWait();
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

        sportTypeBox.getItems().addAll(SportTypeAthleteANDReferee.values());


        for (int i = 0; i < 5; i++) {
            menulistArray.add(new MenuItem("country num " + (i+1)));//need to add refference to all countrys array
        }
        countryMenu.getItems().addAll(menulistArray);



    }

}
