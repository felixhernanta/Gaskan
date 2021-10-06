module Gasken{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens id.ac.ukdw.fti.rpl.Gasken to javafx.fxml;
    exports id.ac.ukdw.fti.rpl.Gasken.database;
    exports id.ac.ukdw.fti.rpl.Gasken.modal;
    exports id.ac.ukdw.fti.rpl.Gasken;
}