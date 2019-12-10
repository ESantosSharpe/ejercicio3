package Vista;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Modelo.*;
import java.awt.Container;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controlador.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ListSelectionModel;

public class PrincipalVista extends JFrame implements ActionListener{
    
    Controlador controlador;
    private Modelo modelo;
    Container contenedor;
    public ResultSetModeloTabla tablaModelo;
    Connection con;
    ResultSet rs;
    Statement sentencia;
    JButton agregar, modificar, eliminar;
    public int idalumnos;
    public JTextField txtNombre, txtApellido,  txtDni, txtEmail, txtCelular;
    public JComboBox txtIdtipodocumento,txtNacionalidad;
    public JTable tabla;
    DatabaseMetaData DatosBBDD;
    
    public PrincipalVista(){
        contenedor=new Container();
        contenedor=getContentPane();
        contenedor.setLayout (null);
        Modelo modelo=new Modelo();    
        con=modelo.getCon();
        confVentana();
        iniTabla();
        iniBotones();    
    }
    
    private void confVentana(){
        this.setLocationRelativeTo(null);
        this.setBounds(500,100,1000,400);
        this.setDefaultCloseOperation(3);
        this.setTitle("Ver alumnos");
       
    }
    
    public void iniTabla(){
        String consulta="select alumnos.idalumnos, alumnos.nombre, alumnos.apellido, tipodocumentos.tipodocumento, alumnos.dni, alumnos.email, alumnos.celular, paises.nombre as 'Nacionalidad' from alumnos \n" +
" inner join paises on alumnos.idtiponacionalidad=paises.idpais \n" +
" inner join tipodocumentos on alumnos.idtipodocumento=tipodocumentos.idtipodocumento";
        try {
            sentencia=con.createStatement();        
            rs=sentencia.executeQuery(consulta);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tablaModelo=new ResultSetModeloTabla(rs);
        tabla=new JTable(tablaModelo);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
        JScrollPane scroll=new JScrollPane(tabla);
        scroll.setBounds(0, 0, 600, 500);
        this.add(scroll);
        validate();
        
    }
    
    public void iniBotones(){
        JPanel botones=new JPanel();
        botones.setBounds(600, 0, 400, 400);
        botones.setLayout(null);
        this.add(botones);
        
        
        //JComboBoxes
        String consulta="select tipodocumento from tipodocumentos";
        try {
            sentencia=con.createStatement();
            rs=sentencia.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtIdtipodocumento=new JComboBox();
        txtIdtipodocumento.setBounds(20, 90, 200, 20);
        try {
            while(rs.next()){
                txtIdtipodocumento.addItem(rs.getString("tipodocumento"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        botones.add(txtIdtipodocumento);
        
        String consulta1="select nombre from paises";
        try {
            sentencia=con.createStatement();
            rs=sentencia.executeQuery(consulta1);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtNacionalidad=new JComboBox();
        txtNacionalidad.setBounds(20, 210, 200, 20);
        try {
            while(rs.next()){
                txtNacionalidad.addItem(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        botones.add(txtNacionalidad);

        //JTextFields
        
        txtNombre=new JTextField();
        txtNombre.setBounds(20, 30, 200, 20);
        botones.add(txtNombre);
        
        txtApellido=new JTextField();
        txtApellido.setBounds(20, 60, 200, 20);
        botones.add(txtApellido);
        
        txtDni=new JTextField();
        txtDni.setBounds(20, 120, 200, 20);
        botones.add(txtDni);
        
        txtEmail=new JTextField();
        txtEmail.setBounds(20, 150, 200, 20);
        botones.add(txtEmail);
        
        txtCelular=new JTextField();
        txtCelular.setBounds(20, 180, 200, 20);
        botones.add(txtCelular);
        

        //Botones
        agregar=new JButton("Agregar");
        agregar.setBounds(30, 250, 100, 30);
        botones.add(agregar);
        
        modificar=new JButton("Modificar");
        modificar.setBounds(140, 250, 100, 30);
        botones.add(modificar);
        
        eliminar=new JButton("Eliminar");
        eliminar.setBounds(250, 250, 100, 30);
        botones.add(eliminar);
    } 
    
    public void conectaControlador(Controlador c){
        tabla.addMouseListener(c);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        agregar.addActionListener(c);
        agregar.setActionCommand("agregar");
        modificar.addActionListener(c);
        modificar.setActionCommand("modificar");
    }
public void actualizarTabla(){
    tablaModelo.fireTableDataChanged();
}
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            

   
    
}
