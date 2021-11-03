package id.ac.ukdw.fti.rpl.Gasken;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import id.ac.ukdw.fti.rpl.Gasken.database.Database;
import id.ac.ukdw.fti.rpl.Gasken.modal.Verse;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;

public class FXMLController implements Initializable{
    
    private ObservableList<Verse> verses = FXCollections.observableArrayList();

    @FXML
    private TableView<Verse> tableVerses;

    @FXML
    private TableColumn<Verse, String> AyatEvents;

    @FXML
    private TableColumn<Verse, String> verseEvent;

    @FXML
    private TableColumn<Verse, String> verseDate;

    @FXML
    private TableColumn<Verse, String> verseDuration;

    @FXML
    private TextField filterField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verses = Database.instance.getAllVerse();
        AyatEvents.setCellValueFactory(new PropertyValueFactory<Verse, String>("AyatEvents1"));
        verseEvent.setCellValueFactory(new PropertyValueFactory<Verse, String>("verseEvent1"));
        verseDate.setCellValueFactory(new PropertyValueFactory<Verse, String>("verseDate1"));
        verseDuration.setCellValueFactory(new PropertyValueFactory<Verse,String>("verseDuration1"));
        tableVerses.setItems(verses);

        //menampilkan semua data
        FilteredList<Verse> filteredData= new FilteredList<>(verses,searching->true);

        filterField.textProperty().addListener((Observable, oldValue, newValue) -> {
            filteredData.setPredicate(verse -> {
                if (newValue==null || newValue.isEmpty()) {
                    return true;
                    
                }
                String lowerCase=newValue.toLowerCase();

                // mencari kesesuaian ayat bedasarkan event, jika true maka akan ditampilkan
                if(verse.getAyatEvents1().toLowerCase().indexOf(lowerCase)!=-1){
                    return true; 
                }              
                // mencari kesesuaian nama bedasarkan event, jika true maka akan ditampilkan
                if(verse.getVerseEvent1().toLowerCase().indexOf(lowerCase)!=-1){
                    return true; 
                }
                // mencari kesesuaian tahun bedasarkan event, jika true maka akan ditampilkan
                if(verse.getVerseDate1().toLowerCase().indexOf(lowerCase)!=-1){
                    return true; 
                }
                // mencari kesesuaian durasi bedasarkan event, jika true maka akan ditampilkan
                if(verse.getVerseDuration1().toLowerCase().indexOf(lowerCase)!=-1){
                    return true; 
                }
                else{
                    return false;
                }
            });
        });
        // mengumpulkan filterlist untuk diurutkan 
        SortedList<Verse> sortingData=new SortedList<>(filteredData);
        // mengurutkan list pada tabel dan dikomparasi
        sortingData.comparatorProperty().bind(tableVerses.comparatorProperty());
        // menambahkan data yang terurut pada tabel
        tableVerses.setItems(sortingData);
    }   
}
    

