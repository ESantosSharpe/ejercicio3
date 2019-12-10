package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Modelo {
    public Connection con=null;

    public Modelo(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluacion", "root", "qpwsyv18");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN", "ERROR", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN", "ERROR", JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }
 
}
