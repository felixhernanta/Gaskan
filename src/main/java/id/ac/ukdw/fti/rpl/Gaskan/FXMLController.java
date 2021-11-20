package id.ac.ukdw.fti.rpl.Gaskan;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import id.ac.ukdw.fti.rpl.Gaskan.database.Database;
import id.ac.ukdw.fti.rpl.Gaskan.modal.Verse;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.collections.FXCollections;
import javafx.scene.control.ScrollPane;

public class FXMLController implements Initializable{
    
    private ObservableList<Verse> verses = FXCollections.observableArrayList();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text Judul;
    
    @FXML
    private TextField filterField;

    @FXML
    private BarChart<Number,String> barChart;
    
    @FXML
    private NumberAxis xAxis;

    @FXML
    private CategoryAxis yAxis;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verses = Database.instance.getAllVerse();
        XYChart.Series<Number,String> dataseries= new XYChart.Series<Number,String>();
        barChart.setLegendVisible(false);

        int size=verses.size();
        Map<String, String> map = new HashMap<String, String>();
        

        for(int i=size-1; i>1;i--){
            dataseries.getData().add(new XYChart.Data<Number,String>(verses.get(i).getVerseDate1(),verses.get(i).getVerseEvent1()));
            map.put(verses.get(i).getVerseEvent1(), verses.get(i).getDuration1());

        }
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(500, 300);
        scrollPane.setContent(barChart);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds(); 
        barChart.setMinSize(screenBounds.getWidth()-300,screenBounds.getHeight()-200);
        barChart.getData().add(dataseries);

        for (XYChart.Series<Number, String> hoover : barChart.getData()) {
            for (XYChart.Data<Number,String> item: hoover.getData()) {
                if(item.getXValue().toString().contains("-")){
                    Tooltip.install(item.getNode(), new Tooltip(String.format("Start: %s"+" BC"+" , Event: %s , Duration: %s", item.getXValue().toString().substring(1,item.getXValue().toString().length()), item.getYValue(), map.get(item.getYValue())))); 
                }else if(item.getXValue().equals(0)){
                    Tooltip.install(item.getNode(), new Tooltip(String.format("Start: %s , Event: %s , Duration: %s", "unknown", item.getYValue(), map.get(item.getYValue())))); 
                }
                else{
                    Tooltip.install(item.getNode(), new Tooltip(String.format("Start: %s"+" AD"+" , Event: %s , Duration: %s", item.getXValue(), item.getYValue(), map.get(item.getYValue()))));  
                }  
            }
        }

        
    }   
}
    

