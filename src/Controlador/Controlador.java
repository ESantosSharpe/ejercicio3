package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.*;
import java.sql.Connection;
import java.sql.SQLException;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Controlador implements ActionListener, MouseListener{
    public PrincipalVista vista=new PrincipalVista();
    private Modelo modelo;
    ResultSet rs=null;
    PreparedStatement st=null;        
    Connection con;
    
    public Controlador(PrincipalVista vista) throws SQLException {
        this.vista=vista;
        modelo=new Modelo();
        con=modelo.getCon();
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        String comando  = arg0.getActionCommand();
        PreparedStatement st;
         switch (comando) {
             case "agregar":
                String agregar2=vista.txtNombre.getText();
                String agregar3=vista.txtApellido.getText();
                int agregar4=vista.txtIdtipodocumento.getSelectedIndex()+1;
                int agregar5=0;
                try {
                    agregar5 = Integer.parseInt(this.vista.txtDni.getText());
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "Debe poner un número de documento válido", "ERROR", JOptionPane.ERROR_MESSAGE);
                    numberFormatException.printStackTrace();
                }
                String agregar6=vista.txtEmail.getText();
                String agregar7=vista.txtCelular.getText();
                int agregar8=vista.txtNacionalidad.getSelectedIndex()+1;
                String query="insert into ALUMNOS (nombre, apellido, idtipodocumento, dni, email, celular, idtiponacionalidad) values (?,?,?,?,?,?,?)";        
        try {
            st = con.prepareStatement(query);
            st.setString(1, agregar2);
            st.setString(2, agregar3);
            st.setInt(3, agregar4);
            st.setInt(4, agregar5);
            st.setString(5, agregar6);
            st.setString(6, agregar7);
            st.setInt(7, agregar8);
            st.executeUpdate();        
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        vista.tablaModelo.fireTableDataChanged();
                        vista.iniTabla();
        break;
             case "modificar":
                int modificar1=vista.idalumnos;
                String modificar2=vista.txtNombre.getText();
                String modificar3=vista.txtApellido.getText();
                int modificar4=vista.txtIdtipodocumento.getSelectedIndex()+1;
                int modificar5=0;
                try {
                    modificar5 = Integer.parseInt(this.vista.txtDni.getText());
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "Debe poner un número de documento válido", "ERROR", JOptionPane.ERROR_MESSAGE);
                    numberFormatException.printStackTrace();
                }
                String modificar6=vista.txtEmail.getText();
                String modificar7=vista.txtCelular.getText();
                int modificar8=vista.txtNacionalidad.getSelectedIndex()+1;
                String query1="update ALUMNOS set nombre=?, apellido=?, idtipodocumento=?, dni=?, email=?, celular=?, idtiponacionalidad=? where idalumnos=?"; 
                try {
                    st = con.prepareStatement(query1);
                    st.setString(1, modificar2);
                    st.setString(2, modificar3);
                    st.setInt(3, modificar4);
                    st.setInt(4, modificar5);
                    st.setString(5, modificar6);
                    st.setString(6, modificar7);
                    st.setInt(7, modificar8);
                    st.setInt(8, modificar1);
                    st.executeUpdate();        
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                vista.tablaModelo.fireTableDataChanged();
                vista.iniTabla();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila=vista.tabla.getSelectedRow();
        vista.txtNombre.setText(vista.tabla.getValueAt(fila, 1).toString());
        vista.txtApellido.setText(vista.tabla.getValueAt(fila, 2).toString());
        vista.txtIdtipodocumento.setSelectedItem(vista.tabla.getValueAt(fila, 3));
        vista.txtDni.setText(vista.tabla.getValueAt(fila, 4).toString());
        vista.txtEmail.setText(vista.tabla.getValueAt(fila, 5).toString());
        vista.txtCelular.setText(vista.tabla.getValueAt(fila, 6).toString());
        vista.txtNacionalidad.setSelectedItem(vista.tabla.getValueAt(fila, 7));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    }; 




    

