package task3;

import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DataEntery {

	private String Comment;
	private int RowId;

	public DataEntery(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()){
			Cell cell=cellIterator.next();
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				 Comment=cell.getStringCellValue();
				 Comment=Comment.replace("\n", " ");
				 break;
			case Cell.CELL_TYPE_NUMERIC:
				RowId=(int) cell.getNumericCellValue();
				break;
				}
			}
		}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
		public int getRowId() {
		return RowId;
	}

	public void setRowId(int rowId) {
		RowId = rowId;
	}
	
	public int sentenceCount(){
		int count=0;
		for( int i=0; i<Comment.length(); i++ ) {
		    if( Comment.charAt(i) == '.'||Comment.charAt(i) == '?') {
		        count++;
		    }} 
		return count;
	}
	
	public String[] getSentences(){
		Comment=Comment.replace(".", ".|");
		Comment=Comment.replace("?", "?|");
		String Sentences[]=Comment.split(Pattern.quote("|"));
		return Sentences;
	}
}