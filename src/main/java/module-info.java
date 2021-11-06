module Gaskan{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens id.ac.ukdw.fti.rpl.Gaskan to javafx.fxml;
    exports id.ac.ukdw.fti.rpl.Gaskan.database;
    exports id.ac.ukdw.fti.rpl.Gaskan.modal;
    exports id.ac.ukdw.fti.rpl.Gaskan;
}