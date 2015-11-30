package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EnvironmentDetailsConfig {

    public enum NameOfEnvironment {DEV,LIVE}
    public final String WEBAPP_URL;


    private Connection conn = null;  //Create object of Connection object


    public EnvironmentDetailsConfig(NameOfEnvironment envName)
    {
        switch (envName) {
            case DEV:
                WEBAPP_URL = "http://www.lowcostholidays.com/";
                break;
            case LIVE:
                WEBAPP_URL = "";
                break;
            default:
                WEBAPP_URL = "";
                break;
        }
    }



    //returns Connection object
    public Connection getConnection(String oracleDriver, String url, String userName, String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Class.forName(oracleDriver).newInstance(); //create object of Driver class
        conn=DriverManager.getConnection(url,userName,password); //connection will be established from this line
        return conn;
    }
}





