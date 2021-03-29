package com.endava;

import com.endava.core.EngineImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Statement;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        EngineImpl engine = new EngineImpl();
        engine.start();
    }
}


