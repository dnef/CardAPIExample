package org.example.common;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.example.connect.ConnectionBuilder;
import org.example.connect.ConnectionBuilderFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateDB {
    public static void createDB() {
        Log logger = LogFactory.getLog(CreateDB.class);
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
            logger.debug("SQL script runner");
        } catch (
                FileNotFoundException e) {
            logger.error("File SQL script not found: "+e);
        }  catch (
                SQLException throwables) {
            logger.error("Error SQL :"+throwables);
        }catch (
                IOException e) {
            logger.error("Error :"+e);
        }
    }
}
