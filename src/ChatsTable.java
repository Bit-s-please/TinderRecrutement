import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ChatsTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private static final int COLUMN_DESCRIPTION = 0;
	
	private String[] columnNames = { "Description" };
	private List<Profil> listProfils;
	
	public void setFilesList(List<Profil> fList) {
		listProfils = fList;
	}

	public ChatsTable(List<Profil> fList) {
		listProfils = fList;
	}

	@Override
	public int getRowCount() {
		if(listProfils == null){
			return 0;
		}
		return listProfils.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(listProfils==null || listProfils.size()==0){
			return null;
		}
		
		Profil profil = listProfils.get(rowIndex);
		
        Object returnValue = null;
        
        switch (columnIndex) {

	        case COLUMN_DESCRIPTION :
	        	returnValue = "Vous avez aimé l'offre de \"" + profil.getNom() + "\", contactez le ici : " + profil.getContact();
	            break;
	            
	        default:
	            throw new IllegalArgumentException("Invalid column index");
	            
        }
        return returnValue;
	}
}