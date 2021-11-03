package id.ac.ukdw.fti.rpl.Gasken.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import id.ac.ukdw.fti.rpl.Gasken.modal.Verse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
    final private String url="jdbc:sqlite:vizbible.sqlite";
    final private String querySelect="SELECT verses, title, startDate, duration FROM events";
    ObservableList<Verse> verses=FXCollections.observableArrayList();
    private Connection connection = null;
    public static Database instance= new Database();

    public Database(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Connection openConnection() {
        return connection;
    }

    public ObservableList<Verse> getAllVerse(){
        try {  
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(querySelect);
            while (result.next()){
                Verse verse= new Verse();
                verse.setAyatEvents1(result.getString("verses"));
                verse.setVerseEvent1(result.getString("title"));
                verse.setVerseDate1(result.getString("startDate"));
                verse.setVerseDuration1(result.getString("duration"));
                verses.add(verse);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return verses;
    }
}
