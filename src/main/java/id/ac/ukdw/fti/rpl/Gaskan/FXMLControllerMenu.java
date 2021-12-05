package id.ac.ukdw.fti.rpl.Gaskan;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.Gaskan.database.Database;
import id.ac.ukdw.fti.rpl.Gaskan.modal.Verse;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Node;

public class FXMLControllerMenu implements Initializable {

    private ObservableList<Verse> verses = FXCollections.observableArrayList();
    private Scene scene;
    private Stage stage;

    @FXML
    private Button Button_try;

    @FXML
    void kembaliKeTable(ActionEvent event) throws IOException {
        verses.clear();
        Parent root = FXMLLoader.load(getClass().getResource("tableevents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}