package gui_indexathor;


import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alvar
 */
public class IndexathorTableModel extends DefaultTableModel {
    
    /*
        This class is for setting a Table Model that is not possible for the user to modify the table elements but selectable.
    */
    
    IndexathorTableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
