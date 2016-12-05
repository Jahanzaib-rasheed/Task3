package task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Task1 {
	
	public Workbook getinputwb  (File x){
		FileInputStream file;
		Workbook wbin=null;
		try{
		x =new File("Input.xlsx");
		file = new FileInputStream(x);
		wbin= new XSSFWorkbook(file);
		file.close();
		}catch (Exception e1){
		try{
			x =new File("Input.xls");
			file = new FileInputStream(x);
			wbin= new HSSFWorkbook(file);
			file.close();
		}catch (Exception e2){
			e2.printStackTrace();
			System.out.println("Neither Input.xlsx nor Input.xls exist in project folder");
		}}
		return wbin;	
	}
	
	public void ExecuteTask1 () {
		File inputfile=null;		
		Workbook wbin=null,wbout=null;
		Sheet inputsheet=null,outputsheet=null;
		List<DataEntery> RowEntries = new ArrayList<>();
				
		Task1 ReadExel=new Task1();
		wbin=ReadExel.getinputwb(inputfile);
		inputsheet = wbin.getSheetAt(0);
		Iterator<Row> iterator = inputsheet.iterator();iterator.next();
		while (iterator.hasNext()) {
			Row r=iterator.next();
			if(r.getCell(0)!=null)
			RowEntries.add(new DataEntery(r));
		}
		
		for (int i=0; i<RowEntries.size();i++){
			String C=RowEntries.get(i).getComment();
			C=C.replace("..", ".");
			C=C.replace("..", ".");
			C=C.replace("..", ".");
			C=C.replace("..", ".");
			C=C.replace("??", "?");
			C=C.replace("??", "?");
			C=C.replace("??", "?");
			C=C.replace("??", "?");
			if (!C.contains(".")&&!C.contains("?")){
				C=C.concat(".");
			}
			RowEntries.get(i).setComment(C);
		}
		
		wbout = new XSSFWorkbook();
	    CreationHelper createHelper = wbout.getCreationHelper();
	    outputsheet = wbout.createSheet("Output Task 1");
	    Row Outputrow = outputsheet.createRow((short)0);
	    Outputrow.createCell(0).setCellValue(
	         createHelper.createRichTextString("sentence_id"));
	    Outputrow.createCell(1).setCellValue(
		         createHelper.createRichTextString("row_id"));
	    Outputrow.createCell(2).setCellValue(
		         createHelper.createRichTextString("Sentence"));
	    int sentence_id=0;
	    for (int i=0; i<RowEntries.size();i++){
			int sentenceCount=RowEntries.get(i).sentenceCount();
			String Sentences[] =RowEntries.get(i).getSentences();
			for(int j=0;j<sentenceCount;j++){
				Outputrow = outputsheet.createRow((short)++sentence_id);
			    Outputrow.createCell(0).setCellValue(sentence_id);
			    Outputrow.createCell(1).setCellValue(RowEntries.get(i).getRowId());
			    Outputrow.createCell(2).setCellValue(
				         createHelper.createRichTextString(Sentences[j]));				
			}			
		}
		try{
	    FileOutputStream fileOut = new FileOutputStream("Output.xlsx");
	    wbout.write(fileOut);
	    fileOut.close();
	    wbout.close();
		}catch (Exception e1){
			try{
			    FileOutputStream fileOut = new FileOutputStream("Output.xls");
			    wbout.write(fileOut);
			    fileOut.close();
				}catch (Exception e2){
					e2.printStackTrace();
					System.out.println("Unable to write on Excel file in Project Directory.");
				}
		}
	}
}
