package id.ac.ukdw.fti.rpl.Gaskan.database;

import java.sql.Statement;

import id.ac.ukdw.fti.rpl.Gaskan.modal.Verse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
                verse.setVerseEvent1(result.getString("title"));
                verse.setVerseDate1(result.getInt("startDate"));
                verse.setDuration1(result.getString("duration"));
                verses.add(verse);
            }
            result.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return verses;
    }
}
