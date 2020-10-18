package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SampleController implements Initializable {

    Model model = new Model();

    @FXML
    private Button changeTomanagerPage;

    @FXML
    private ChoiceBox<String> countryBox2;

    @FXML
    private TableView<Athlete> athletesTableView;

    @FXML
    private TableColumn<Athlete, String> athlleteNameCol;

    @FXML
    private TableColumn<Athlete, SportTypeAthleteANDReferee> athleteTypecol;

    @FXML
    private TableView<Referee> refereeTableView;

    @FXML
    private TableColumn<Referee , String> refereeNameCol;

    @FXML
    private TableColumn<Referee , String > refereeCountryCol;

    @FXML
    private TableColumn<Referee, SportTypeAthleteANDReferee> refereeTypecol;

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

    public void athleteTableViewUpdate(String country){
        athletesTableView.getItems().clear();
        model.athleteList.addAll(model.getStateByName(country).getAthletes());
        athlleteNameCol.setCellValueFactory(new PropertyValueFactory<Athlete, String>("name"));
        athleteTypecol.setCellValueFactory(new PropertyValueFactory<Athlete,SportTypeAthleteANDReferee>("sportType"));
        athletesTableView.setItems(model.athleteList);
    }

    public void refereeTableViewUpdate(){
        refereeTableView.getItems().clear();
        model.refereeList.addAll(model.getAllReferees());
        refereeNameCol.setCellValueFactory(new PropertyValueFactory<Referee,String>("name"));
        refereeCountryCol.setCellValueFactory((new PropertyValueFactory<Referee,String>("stateString")));
        refereeTypecol.setCellValueFactory(new PropertyValueFactory<Referee, SportTypeAthleteANDReferee>("sportType"));
        refereeTableView.setItems(model.refereeList);
    }

    public void sceneSwitchEvent (ActionEvent event) throws IOException {
        Parent root;
        Stage stage;
        stage = (Stage) changeTomanagerPage.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("EditPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addItemsToMenu(){
        countryBox2.getItems().clear();
        countryBox2.getItems().addAll(model.getAllStatesWithAthletes());
    }

    public void updateMedalView (){
        ArrayList<State> podiumCountrys = new ArrayList<>(model.getPodium());
        if(!podiumCountrys.isEmpty()){
            countryFirstLable.setText(podiumCountrys.get(0).getName());
            numOfMedalsFirstLable.setText("Points: " + podiumCountrys.get(0).getTotalPoints());
            countrySecondLable.setText(podiumCountrys.get(1).getName());
            numOfMedalsSecondLable.setText("Points: " + podiumCountrys.get(1).getTotalPoints());
            countryThirdLable.setText(podiumCountrys.get(2).getName());
            numOfMedalsThirdLable.setText("Points: " + podiumCountrys.get(2).getTotalPoints());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addItemsToMenu();
        refereeTableViewUpdate();
        countryBox2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                athleteTableViewUpdate(countryBox2.getItems().get((Integer)t1));
            }
        });
    }
}
