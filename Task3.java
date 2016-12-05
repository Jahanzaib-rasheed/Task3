package task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Task3 {
	private static Workbook workbook;
	private static Sheet outputsheet;
	private static int i=0;
	
public Workbook getTask1wb  (){
	File x;	
	FileInputStream file;
		Workbook wbin=null;
		try{
		x =new File("Output.xlsx");
		file = new FileInputStream(x);
		wbin= new XSSFWorkbook(file);
		file.close();
		}catch (Exception e1){
		try{
			x =new File("Output.xls");
			file = new FileInputStream(x);
			wbin= new HSSFWorkbook(file);
			file.close();
		}catch (Exception e2){
		try{	
			new Task1().ExecuteTask1();
			return new Task3().getTask1wb();
		}catch(Exception e3){
			e3.printStackTrace();
			}
		}
		}
		return wbin;	
	}

public void CreatOutputSheet(){
	CreationHelper createHelper = workbook.getCreationHelper();
	try{
		outputsheet= workbook.createSheet("Output Task 3");
	}catch (Exception e){
		outputsheet=workbook.getSheetAt(1);
	}
    Row Outputrow = outputsheet.createRow((short)0);
    Outputrow.createCell(0).setCellValue(
            createHelper.createRichTextString("Response_Id"));
    Outputrow.createCell(1).setCellValue(
   	        createHelper.createRichTextString("Sentence_Id"));
    Outputrow.createCell(2).setCellValue(
   	        createHelper.createRichTextString("Row_Id"));
    Outputrow.createCell(3).setCellValue(
            createHelper.createRichTextString("Sentence"));
    Outputrow.createCell(4).setCellValue(
   	        createHelper.createRichTextString("Subject"));
    Outputrow.createCell(5).setCellValue(
   	        createHelper.createRichTextString("Pridicate"));
    Outputrow.createCell(6).setCellValue(
            createHelper.createRichTextString("Skip Count"));
    Outputrow.createCell(7).setCellValue(
   	        createHelper.createRichTextString("Score"));
}

public List<Sentence> ReadFromFile(){
	List<Sentence> ListSentence=new ArrayList<>();
	workbook = getTask1wb();
	Sheet OutputSheet = workbook.getSheetAt(0);
	Iterator<Row> iterator = OutputSheet.iterator();iterator.next();
	
	while (iterator.hasNext()) {
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		Sentence aSentence = new Sentence();
		
		while (cellIterator.hasNext()) {
			Cell nextCell = cellIterator.next();
			int columnIndex = nextCell.getColumnIndex();
			switch (columnIndex) {
			case 0:
				aSentence.setSentenceID((int) nextCell.getNumericCellValue());
				break;
			case 1:
				aSentence.setRowId((int) nextCell.getNumericCellValue());
				break;
			case 2:
				aSentence.setSentence((String)nextCell.getStringCellValue());
				break;
			}		
		}
		ListSentence.add(aSentence);
	}
	Shuffle(ListSentence);
	return ListSentence;
}

public void Shuffle(List<Sentence>l){
	Collections.shuffle(l);
}


public void WriteInFile(Sentence s){
//	String a=s.getSubject();
//	String b=s.getPridicate();
//		if (a.contains(",")){
//			String Arr[]=a.split(",");
//			Sentence tmp=new Sentence();
//			tmp.setSubject(Arr[0]);
//			tmp.setPridicate(s.getPridicate());
//			tmp.setRowId(s.getRowId());
//			tmp.setScore(s.getScore());
//			tmp.setSentence(s.getSentence());
//			tmp.setSentenceID(s.getSentenceID());
//			tmp.setSkip(s.getSkip());
//			WriteInFile(tmp);
//			s.setSubject(Arr[1]);			
//		}
//		if(b.contains(",")){
//			String Brr[]=b.split(",");
//			Sentence tmp=new Sentence();
//			tmp.setPridicate(Brr[0]);
//			tmp.setSubject(s.getSubject());
//			tmp.setRowId(s.getRowId());
//			tmp.setScore(s.getScore());
//			tmp.setSentence(s.getSentence());
//			tmp.setSentenceID(s.getSentenceID());
//			tmp.setSkip(s.getSkip());
//			WriteInFile(tmp);
//			s.setPridicate(Brr[1]);			
//		}
	CreatOutputSheet();
	CreationHelper createHelper = workbook.getCreationHelper();	
	Row Outputrow = outputsheet.createRow((short)++i);
	Outputrow.createCell(0).setCellValue(i);
	Outputrow.createCell(1).setCellValue(s.getSentenceID());
    Outputrow.createCell(2).setCellValue(s.getRowId());
    Outputrow.createCell(3).setCellValue(
	         createHelper.createRichTextString(s.getSentence()));				
    Outputrow.createCell(4).setCellValue(
	         createHelper.createRichTextString(s.getSubject()));				
    Outputrow.createCell(5).setCellValue(
	         createHelper.createRichTextString(s.getPridicate()));				
    Outputrow.createCell(6).setCellValue(s.getSkip());
    Outputrow.createCell(7).setCellValue(s.getScore());
    try{
    FileOutputStream fileOut = new FileOutputStream("Output.xlsx");
    workbook.write(fileOut);
    fileOut.close();
    }catch (Exception e1){
		try{
		    FileOutputStream fileOut = new FileOutputStream("Output.xls");
		    workbook.write(fileOut);
		    fileOut.close();
			}catch (Exception e2){
				e2.printStackTrace();
				System.out.println("Unable to write on Excel file in Project Directory.");
			}
		}
	}
}
