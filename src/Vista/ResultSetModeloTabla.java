package Vista;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class ResultSetModeloTabla extends AbstractTableModel{
    
    public ResultSet rsRegistros;
    public ResultSetMetaData resmd;
    
    public ResultSetModeloTabla(ResultSet unResultSet){
        try {
            rsRegistros=unResultSet;
            resmd=rsRegistros.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(ResultSetModeloTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public int getColumnCount() {
        try {
            return resmd.getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        return 0;
        }
    }
    @Override
    public int getRowCount() {
        try {
            rsRegistros.last();
            return rsRegistros.getRow();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        
    }
    @Override
    public Object getValueAt(int row, int column) {
        try {
            rsRegistros.absolute(row+1);
            return rsRegistros.getObject(column+1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    @Override
    public String getColumnName(int c){
        String nombres[]={"ID", "Nombre", "Apellido", "Tipo", "Documento", "Email", "Celular", "Nacionalidad"};
        return nombres[c];
    }

}
