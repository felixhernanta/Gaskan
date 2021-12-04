package id.ac.ukdw.fti.rpl.Gaskan;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.Gaskan.database.Database;
import id.ac.ukdw.fti.rpl.Gaskan.modal.VerseTable;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Node;

public class FXMLControllerTable implements Initializable {

    private ObservableList<VerseTable> verses = FXCollections.observableArrayList();
    private Scene scene;
    private Stage stage;

    @FXML
    private TableView<VerseTable> tableVerses;

    @FXML
    private TableColumn<VerseTable, String> AyatEvents;

    @FXML
    private TableColumn<VerseTable, String> verseEvent;

    @FXML
    private TableColumn<VerseTable, String> verseDate;

    @FXML
    private TableColumn<VerseTable, String> verseDuration;

    @FXML
    private TextField filterField;

    @FXML
    private Button kembaliKeDiagram;

    @FXML
    private Button kembaliKeMenu1;

    @FXML
    void kembaliKeDiagram(ActionEvent event) throws IOException {
        verses.clear();
        tableVerses.getItems().clear();
        Parent root = FXMLLoader.load(getClass().getResource("diagramevents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void kembaliKeMenu(ActionEvent event) throws IOException {
        verses.clear();
        tableVerses.getItems().clear();
        Parent root = FXMLLoader.load(getClass().getResource("cover.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verses = Database.instance.getAllVerseTable();
        AyatEvents.setCellValueFactory(new PropertyValueFactory<VerseTable, String>("AyatEvents1"));
        verseEvent.setCellValueFactory(new PropertyValueFactory<VerseTable, String>("verseEvent1"));
        verseDate.setCellValueFactory(new PropertyValueFactory<VerseTable, String>("verseDate1"));
        verseDuration.setCellValueFactory(new PropertyValueFactory<VerseTable, String>("verseDuration1"));
        tableVerses.setItems(verses);

        // menampilkan semua data
        FilteredList<VerseTable> filteredData = new FilteredList<>(verses, searching -> true);

        filterField.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(verse -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }
                String lowerCase = newValue.toLowerCase();

                // mencari kesesuaian ayat bedasarkan event, jika true maka akan ditampilkan
                if (verse.getAyatEvents1().toLowerCase().indexOf(lowerCase) != -1) {
                    return true;
                }
                // mencari kesesuaian nama bedasarkan event, jika true maka akan ditampilkan
                if (verse.getVerseEvent1().toLowerCase().indexOf(lowerCase) != -1) {
                    return true;
                }
                // mencari kesesuaian tahun bedasarkan event, jika true maka akan ditampilkan
                if (verse.getVerseDate1().toLowerCase().indexOf(lowerCase) != -1) {
                    return true;
                }
                // mencari kesesuaian durasi bedasarkan event, jika true maka akan ditampilkan
                if (verse.getVerseDuration1().toLowerCase().indexOf(lowerCase) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        // mengumpulkan filterlist untuk diurutkan
        SortedList<VerseTable> sortingData = new SortedList<>(filteredData);
        // mengurutkan list pada tabel dan dikomparasi
        sortingData.comparatorProperty().bind(tableVerses.comparatorProperty());
        // menambahkan data yang terurut pada tabel
        tableVerses.setItems(sortingData);
    }
}