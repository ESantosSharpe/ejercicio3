package ejercicio3;

import Controlador.Controlador;
import Vista.PrincipalVista;
import java.sql.SQLException;
import Modelo.*;
public class Ejercicio3 {

    
    public static void main(String[] args) throws SQLException {
        new Modelo();
        PrincipalVista vista=new PrincipalVista();
        Controlador controlador=new Controlador(vista);
        vista.conectaControlador(controlador);
        vista.setVisible(true);
    }
    
}
