package org.example.common;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.example.connect.ConnectionBuilder;
import org.example.connect.ConnectionBuilderFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateDB {
    public static void createDB() {
        ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();
        try(
                Connection connection = builder.getConnection()) {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Reader readerTable = new BufferedReader(new FileReader("src/main/resources/create_table.sql"));
            scriptRunner.runScript(readerTable);
            Reader readerInsert = new BufferedReader(new FileReader("src/main/resources/insert_data.sql"));
            scriptRunner.runScript(readerInsert);
            readerTable.close();
            readerInsert.close();
            scriptRunner.closeConnection();
            System.out.println("SQL script runner");
        } catch (
                FileNotFoundException e) {
            System.out.println("File SQL script not found: "+e);
        }  catch (
                SQLException throwables) {
            System.out.println("Error SQL :"+throwables);
        }catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
