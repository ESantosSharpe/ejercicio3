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
    
    public Controlador(PrincipalVista vista){
        this.vista=vista;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        modelo=new Modelo();
        modelo.conectarse();
        try {
            String comando  = arg0.getActionCommand();
            PreparedStatement st;
            switch (comando) {
                case "agregar":
                    String agregar2=this.vista.txtNombre.getText();
                    String agregar3=this.vista.txtApellido.getText();
                    int agregar4=this.vista.txtIdtipodocumento.getSelectedIndex()+1;
                    int agregar5=0;
                    try {        
                        agregar5 = Integer.parseInt(this.vista.txtDni.getText());
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "Debe poner un número de documento válido", "ERROR", JOptionPane.ERROR_MESSAGE);
                        numberFormatException.printStackTrace();
                    }
                    String agregar6=this.vista.txtEmail.getText();
                    String agregar7=this.vista.txtCelular.getText();
                    int agregar8=this.vista.txtNacionalidad.getSelectedIndex()+1;
                    String query="insert into ALUMNOS (nombre, apellido, idtipodocumento, dni, email, celular, idtiponacionalidad) values (?,?,?,?,?,?,?)";
                    try {
                        st = modelo.conectarse().prepareStatement(query);
                        st.setString(1, agregar2);
                        st.setString(2, agregar3);
                        st.setInt(3, agregar4);
                        st.setInt(4, agregar5);
                        st.setString(5, agregar6);
                        st.setString(6, agregar7);
                        st.setInt(7, agregar8);
                        st.executeUpdate(); 
                        st.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    actualizar();
                    break;
                case "modificar":
                    int fila=this.vista.tabla.getSelectedRow();
                    int modificar1=Integer.parseInt(this.vista.tabla.getValueAt(fila, 0).toString());
                    String modificar2=this.vista.txtNombre.getText();
                    String modificar3=this.vista.txtApellido.getText();
                    int modificar4=this.vista.txtIdtipodocumento.getSelectedIndex()+1;
                    int modificar5=0;
                    try {
                        modificar5 = Integer.parseInt(this.vista.txtDni.getText());
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "Debe poner un número de documento válido", "ERROR", JOptionPane.ERROR_MESSAGE);
                        numberFormatException.printStackTrace();
                    }
                    String modificar6=this.vista.txtEmail.getText();
                    String modificar7=this.vista.txtCelular.getText();
                    int modificar8=this.vista.txtNacionalidad.getSelectedIndex()+1;
                    String query1="update ALUMNOS set nombre=?, apellido=?, idtipodocumento=?, dni=?, email=?, celular=?, idtiponacionalidad=? where idalumnos=?";
                    try {
                        st = modelo.conectarse().prepareStatement(query1);
                        st.setString(1, modificar2);
                        st.setString(2, modificar3);
                        st.setInt(3, modificar4);
                        st.setInt(4, modificar5);
                        st.setString(5, modificar6);
                        st.setString(6, modificar7);
                        st.setInt(7, modificar8);
                        st.setInt(8, modificar1);
                        st.executeUpdate();
                        st.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    actualizar();
                    break;
                case "eliminar":
                    int fila1=this.vista.tabla.getSelectedRow();
                    int eliminar=Integer.parseInt(this.vista.tabla.getValueAt(fila1, 0).toString());
                    String query2="delete from alumnos where idalumnos=?";
                    st = modelo.conectarse().prepareStatement(query2);
                    st.setInt(1, eliminar);
                    st.executeUpdate();
                    st.close();
                    actualizar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizar(){
        this.vista.remove(vista.scroll);
        this.vista.remove(vista.botones);
        this.vista.actualizarTabla();
        this.vista.tabla.addMouseListener(this);
        this.vista.agregar.addActionListener(this);
        this.vista.agregar.setActionCommand("agregar");
        this.vista.modificar.addActionListener(this);
        this.vista.modificar.setActionCommand("modificar");
        this.vista.eliminar.addActionListener(this);
        this.vista.eliminar.setActionCommand("eliminar");
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int fila=this.vista.tabla.getSelectedRow();
        this.vista.txtNombre.setText(this.vista.tabla.getValueAt(fila, 1).toString());
        this.vista.txtApellido.setText(this.vista.tabla.getValueAt(fila, 2).toString());
        this.vista.txtIdtipodocumento.setSelectedItem(this.vista.tabla.getValueAt(fila, 3));
        this.vista.txtDni.setText(this.vista.tabla.getValueAt(fila, 4).toString());
        this.vista.txtEmail.setText(this.vista.tabla.getValueAt(fila, 5).toString());
        this.vista.txtCelular.setText(this.vista.tabla.getValueAt(fila, 6).toString());
        this.vista.txtNacionalidad.setSelectedItem(this.vista.tabla.getValueAt(fila, 7));
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


    } 




    

