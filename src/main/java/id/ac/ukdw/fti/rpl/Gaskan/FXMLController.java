package id.ac.ukdw.fti.rpl.Gaskan;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.Gaskan.database.Database;
import id.ac.ukdw.fti.rpl.Gaskan.modal.Verse;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.Node;

public class FXMLController implements Initializable {

    private ObservableList<Verse> verses = FXCollections.observableArrayList();
    private Scene scene;
    private Stage stage;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text Event;

    @FXML
    private Text Period;

    @FXML
    private Text Duration;

    @FXML
    private TextArea Ayat;

    @FXML
    private Text Judul;

    @FXML
    private TextField filterField;

    @FXML
    private BarChart<Number, String> barChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private CategoryAxis yAxis;

    @FXML
    private Button kembaliKeTable;

    @FXML
    void kembaliKeTable(ActionEvent event) throws IOException {
        verses.clear();
        barChart.getData().clear();
        Parent root = FXMLLoader.load(getClass().getResource("tableevents.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { 
        try {
            String title=FXMLControllerTable.getEvent();
            verses = Database.getAllVerse(title);
            XYChart.Series<Number, String> dataseries = new XYChart.Series<Number, String>();
            barChart.setLegendVisible(false);

            Map<String, String> map = new HashMap<String, String>();

            dataseries.getData().add(new XYChart.Data<Number, String>(verses.get(0).getVerseDate1(), verses.get(0).getVerseEvent1()));
            map.put(verses.get(0).getVerseEvent1(), verses.get(0).getDuration1());
            Event.setText("Event : "+verses.get(0).getVerseEvent1());
            if(verses.get(0).getVerseDate1()<0){
                Period.setText("Periode : "+Integer.toString(verses.get(0).getVerseDate1()).substring(1,Integer.toString(verses.get(0).getVerseDate1()).length())+" BC");
            }
            else if(verses.get(0).getVerseDate1()==0){
                Period.setText("Periode : unknown");
            }
            else {
                Period.setText("Periode : "+Integer.toString(verses.get(0).getVerseDate1()).substring(1,Integer.toString(verses.get(0).getVerseDate1()).length())+" AD");
            }
            Duration.setText("Durasi  : "+verses.get(0).getDuration1());
            Ayat.setText(verses.get(0).getAyatEvents1());
            barChart.getData().add(dataseries);

            for (XYChart.Series<Number, String> hoover : barChart.getData()) {
                for (XYChart.Data<Number, String> item : hoover.getData()) {
                    if (item.getXValue().toString().contains("-")) {
                        Tooltip.install(item.getNode(),
                                new Tooltip(String.format("Start: %s" + " BC" + " , Event: %s , Duration: %s",
                                        item.getXValue().toString().substring(1, item.getXValue().toString().length()),
                                        item.getYValue(), map.get(item.getYValue()))));
                    } else if (item.getXValue().equals(0)) {
                        Tooltip.install(item.getNode(), new Tooltip(String.format("Start: %s , Event: %s , Duration: %s",
                                "unknown", item.getYValue(), map.get(item.getYValue()))));
                    } else {
                        Tooltip.install(item.getNode(),
                                new Tooltip(String.format("Start: %s" + " AD" + " , Event: %s , Duration: %s",
                                        item.getXValue(), item.getYValue(), map.get(item.getYValue()))));
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}