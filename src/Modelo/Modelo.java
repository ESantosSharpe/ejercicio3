package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Modelo {
    public Connection con=null;

    public Modelo(){
    }
    
    public Connection conectarse(){
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
        return con;
    }
    
    public void desconectarse(){
        if (con != null) 
        {
            try 
            { 
                con.close(); 
            }
          catch (SQLException e) {
            System.out.println("Enable to close DB connection");
            e.printStackTrace(); }
        }
    }

}
