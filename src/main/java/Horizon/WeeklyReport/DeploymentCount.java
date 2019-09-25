package Horizon.WeeklyReport;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
public class DeploymentCount {
	static Logger log = Logger.getLogger(DeploymentCount.class.getName());
	
	
	public static void main(String args[]) {
		
		Properties prop = getProperties();
    					
		PropertyConfigurator.configure(prop.getProperty("log4jConfPath"));
		
		//String tableData=getDateTable(prop.getProperty("EXECUATION_DATE"));
		String devdeploymentCount[]=readDevRelease(prop.getProperty("DEV_XLSX_FILE_PATH"),prop.getProperty("EXECUATION_DATE"));
		String proddeploymentCount[][]=readProdRelease(prop.getProperty("PROD_XLSX_FILE_PATH"),prop.getProperty("EXECUATION_DATE"));
		
		String monthYear=getMonthYear(prop.getProperty("EXECUATION_DATE"));
		log.info("Dev week 1 count =>"+devdeploymentCount[0]);
		log.info("Dev week 2 count =>"+devdeploymentCount[1]);
		log.info("Dev week 3 count =>"+devdeploymentCount[2]);
		log.info("Dev week 4 count =>"+devdeploymentCount[3]);
		log.info("Dev week 5 count =>"+devdeploymentCount[4]);
		
		log.info("QA week 1 count =>"+proddeploymentCount[0][0] +"  PROD week 1 count =>"+proddeploymentCount[0][1]);
		log.info("QA week 2 count =>"+proddeploymentCount[1][0] +"  PROD week 2 count =>"+proddeploymentCount[1][1]);
		log.info("QA week 3 count =>"+proddeploymentCount[2][0] +"  PROD week 3 count =>"+proddeploymentCount[2][1]);
		log.info("QA week 4 count =>"+proddeploymentCount[3][0] +"  PROD week 4 count =>"+proddeploymentCount[3][1]);
		log.info("QA week 5 count =>"+proddeploymentCount[4][0] +"  PROD week 5 count =>"+proddeploymentCount[4][1]);
		
		
		String reportBody=" "
				+"<style>"
				+"table, th, td {"
				+"border: 1px solid black ;border-collapse: collapse;padding-left: 5px;"
				+"}"
		+"</style>"
		+"</head>"
		+"<body>"

		+"<table width=\"500\" bgcolor=\"#e6e6ff\" >"
		+"<tr>"
		 +"   <th  colspan=\"4\" bgcolor=#a8c2ed>"+monthYear+"</th>"
		    
		 +"  </tr>"
		  +" <tr>"
		   +" <th   rowspan=\"2\" bgcolor=#a8c2ed><font size=\\\"2\\\" color=\\\"black\\\" face=\\\"Calibri\\\">Count of Deployments:</th>"
		   +"   <th bgcolor=#a8c2ed><font size=\\\"2\\\" color=\\\"black\\\" face=\\\"Calibri\\\">DEV</th>"
		 +"   <th bgcolor=#a8c2ed><font size=\\\"2\\\" color=\\\"black\\\" face=\\\"Calibri\\\">QA</th>"
		   +" <th bgcolor=#a8c2ed><font size=\\\"2\\\" color=\\\"black\\\" face=\\\"Calibri\\\">PROD</th>"
		+"  </tr>"
		 +"  <tr>"
		    
		  +" <td cellpadding =\"-1\" bgcolor=#a8c2ed><font size=\"2\" color=\"black\" face=\"Calibri\">VSIT12c,WIT12c</th>"
		  +"  <td bgcolor=#a8c2ed><font size=\"2\" color=\"black\" face=\"Calibri\">STG12c,HMIG12c</th>"
		  +"   <td bgcolor=#a8c2ed><font size=\"2\" color=\"black\" face=\"Calibri\">PROD12c</th>"
		  +" </tr>"
		  +"<tr>" 
		    +"<td><font size=\"2\" color=\"black\" face=\"Calibri\">Week 1</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+devdeploymentCount[0]+"</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[0][0]+"</td>"
		   +"  <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[0][1]+"</td>"
		   +"</tr>"
		   +"<tr>" 
		    +"<td><font size=\"2\" color=\"black\" face=\"Calibri\">Week 2</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+devdeploymentCount[1]+"</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[1][0]+"</td>"
		   +"  <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[1][1]+"</td>"
		   +"</tr>"
		   +"<tr>" 
		    +"<td><font size=\"2\" color=\"black\" face=\"Calibri\">Week 3</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+devdeploymentCount[2]+"</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[2][0]+"</td>"
		   +"  <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[2][1]+"</td>"
		   +"</tr>"
		   +"<tr>" 
		    +"<td><font size=\"2\" color=\"black\" face=\"Calibri\">Week 4</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+devdeploymentCount[3]+"</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[3][0]+"</td>"
		   +"  <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[3][1]+"</td>"
		   +"</tr>"
		   +"<tr>" 
		    +"<td><font size=\"2\" color=\"black\" face=\"Calibri\">Week 5</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+devdeploymentCount[4]+"</td>"
		   +" <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[4][0]+"</td>"
		   +"  <td><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[4][1]+"</td>"
		   +"</tr>"
		   +"<tr>" 
		    +"<td bgcolor=#a8c2ed><font size=\"2\" color=\"black\" face=\"Calibri\">Total</td>"
		   +" <td bgcolor=#a8c2ed><font size=\"2\" color=\"black\" face=\"Calibri\">"+devdeploymentCount[5]+"</td>"
		   +" <td bgcolor=#a8c2ed><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[5][0]+"</td>"
		   +"  <td bgcolor=#a8c2ed><font size=\"2\" color=\"black\" face=\"Calibri\">"+proddeploymentCount[5][1]+"</td>"
		   +"</tr>"
		+"</table>"
		+"</body>"
		+"</html>"
				+"</div>";
		
		String mailContent[] = new String[2];
		mailContent[0]=monthYear;
		mailContent[1]=reportBody;
		SendMailDC.sendMail(mailContent);
		
		
	
	}
	
	public static String [] readDevRelease(String DEV_XLSX_FILE_PATH,String EXECUATION_DATE) {
		//System.out.println("EXECUATION_DATE=>"+EXECUATION_DATE);
		
		Date weekArray[]=getFinalExecuationDate(EXECUATION_DATE);
		
		
		    Date firstWeekFDate=weekArray[0];
			Date firstWeekLDate= weekArray[1];
			Date secondWeekFDate= weekArray[2];
			Date secondWeekLDate= weekArray[3];
			Date thirdWeekFDate= weekArray[4];
			Date thirdWeekLDate= weekArray[5];
			Date forthWeekFDate= weekArray[6];
			Date forthWeekLDate= weekArray[7];
			Date fiftWeekFDate= weekArray[8];
			Date fiftWeekLDate= weekArray[9];
			
//			System.out.println(firstWeekFDate);
//			System.out.println(firstWeekLDate);
//			System.out.println(secondWeekFDate);
//			System.out.println(secondWeekLDate);
//			System.out.println(thirdWeekFDate);
//			System.out.println(thirdWeekLDate);
//			System.out.println(forthWeekFDate);
//			System.out.println(forthWeekLDate);
//			System.out.println(fiftWeekFDate);
//			System.out.println(fiftWeekLDate);
			
			
			
		
		Workbook workbook=null;
		try {
			workbook = WorkbookFactory.create(new File(DEV_XLSX_FILE_PATH));
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        //System..println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
           // System..println("=> " + sheet.getSheetName());
        }
		
        int week1Counter=0;
        int week2Counter=0;
        int week3Counter=0;
        int week4Counter=0;
        int week5Counter=0;
        int totalCounterD=0;
        
        for(int tabCounter=0;tabCounter<=1;tabCounter++) {
       	 Sheet sheet = workbook.getSheetAt(tabCounter);
       	 //Sheet sheet = workbook.getSheetAt(0);
        
     // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        
       // System..println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
       
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            if(null!=row.getCell(8)) {
            	if(!row.getCell(8).toString().trim().equals("") && !row.getCell(8).toString().trim().equals("N/A") ) {
            
            //System.out.println(row.getRowNum()+"==============>");
              Date cellValue = row.getCell(8).getDateCellValue();
              String verification_Status = dataFormatter.formatCellValue(row.getCell(10));
              //System.out.println(currentDate+"cellValue->"+cellValue+"==>"+row.getCell(10));
                // System.out.println("Date "+cellValue);
                 if((cellValue.equals(firstWeekFDate) || cellValue.after(firstWeekFDate)) && (cellValue.equals(firstWeekLDate) || cellValue.before(firstWeekLDate)) && (verification_Status.trim().equalsIgnoreCase("PASS") || verification_Status.trim().equalsIgnoreCase("FAIL") )) {
                	 week1Counter=week1Counter+1; 
                	//System.out.println("==============> 1"+ "Date "+cellValue +"==  "+ firstWeekFDate);
             	  }else
                 if((cellValue.equals(secondWeekFDate) || cellValue.after(secondWeekFDate)) && (cellValue.equals(secondWeekLDate) || cellValue.before(secondWeekLDate)) && (verification_Status.trim().equalsIgnoreCase("PASS") || verification_Status.trim().equalsIgnoreCase("FAIL") )) {
                	 week2Counter=week2Counter+1; 
                	//System.out.println("==============> 2"+ "Date "+cellValue +"==  "+ secondWeekFDate);
             	  }else
                 if((cellValue.equals(thirdWeekFDate) || cellValue.after(thirdWeekFDate)) && (cellValue.equals(thirdWeekLDate) || cellValue.before(thirdWeekLDate)) && (verification_Status.trim().equalsIgnoreCase("PASS") || verification_Status.trim().equalsIgnoreCase("FAIL") )) {
                	 week3Counter=week3Counter+1; 
                	 //System.out.println("==============> 3" +"Date "+cellValue +"==  "+ thirdWeekFDate);
             	  }else
                 if((cellValue.equals(forthWeekFDate) || cellValue.after(forthWeekFDate)) && (cellValue.equals(forthWeekLDate) || cellValue.before(forthWeekLDate)) && (verification_Status.trim().equalsIgnoreCase("PASS") || verification_Status.trim().equalsIgnoreCase("FAIL") )) {
                	 week4Counter=week4Counter+1; 
                	 //System.out.println("==============> 4"+ "Date "+cellValue +"==  "+ forthWeekFDate);
             	  }else
                 if((cellValue.equals(fiftWeekFDate) || cellValue.after(fiftWeekFDate)) && (cellValue.equals(fiftWeekLDate) || cellValue.before(fiftWeekLDate)) && (verification_Status.trim().equalsIgnoreCase("PASS") || verification_Status.trim().equalsIgnoreCase("FAIL") )) {
                	 week5Counter=week5Counter+1; 
                	//System.out.println("==============> 5"+ "Date "+cellValue +"==  "+ fiftWeekFDate);
             	  }
                 }
               }        
               
        }
        totalCounterD=week1Counter+week2Counter+week3Counter+week4Counter+week5Counter;
	} //ForEnds    
        
       
        //System.out.println("totalCounterD =============> "+ totalCounterD);
        String devDeploymentCounts[] = new String[6];
        devDeploymentCounts[0]=week1Counter+"";
		devDeploymentCounts[1]=week2Counter+"";
		devDeploymentCounts[2]=week3Counter+"";
		devDeploymentCounts[3]=week4Counter+"";
		devDeploymentCounts[4]=week5Counter+"";
		devDeploymentCounts[5]=totalCounterD+"";
   
        return devDeploymentCounts;
	}

	public static String [][] readProdRelease(String PROD_XLSX_FILE_PATH,String EXECUATION_DATE) {
		//System.out.println("PROD_XLSX_FILE_PATH=>"+PROD_XLSX_FILE_PATH);
		//System.out.println("EXECUATION_DATE=>"+EXECUATION_DATE);
		
		Date weekArray[]=getFinalExecuationDate(EXECUATION_DATE);
		
		
		    Date firstWeekFDate=weekArray[0];
			Date firstWeekLDate= weekArray[1];
			Date secondWeekFDate= weekArray[2];
			Date secondWeekLDate= weekArray[3];
			Date thirdWeekFDate= weekArray[4];
			Date thirdWeekLDate= weekArray[5];
			Date forthWeekFDate= weekArray[6];
			Date forthWeekLDate= weekArray[7];
			Date fiftWeekFDate= weekArray[8];
			Date fiftWeekLDate= weekArray[9];
		
		
		
		Workbook workbook=null;
		try {
			workbook = WorkbookFactory.create(new File(PROD_XLSX_FILE_PATH));
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        //System..println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
           // System..println("=> " + sheet.getSheetName());
        }
        int week1CounterP=0;
        int week2CounterP=0;
        int week3CounterP=0;
        int week4CounterP=0;
        int week5CounterP=0;
        int totalCounterP=0;
        
        int week1CounterQ=0;
        int week2CounterQ=0;
        int week3CounterQ=0;
        int week4CounterQ=0;
        int week5CounterQ=0;
        int totalCounterQ=0;
        
        Sheet sheet = workbook.getSheetAt(0);
        
     // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        
       // System..println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next(); //Skipping the header
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            //System.out.println("HOHOHOHOHHHHHHHHHHHHHHHHHHHHHHHH " +row.getCell(10));
           
            
            if(null!=row.getCell(10)) {
            	if(!row.getCell(10).toString().trim().equals("") && !row.getCell(10).toString().trim().equals("N/A") ) {
            
            
              Date cellValue = row.getCell(10).getDateCellValue();
 
              
              String environment = dataFormatter.formatCellValue(row.getCell(2));
             
                 if((cellValue.equals(firstWeekFDate) || cellValue.after(firstWeekFDate)) && (cellValue.equals(firstWeekLDate) || cellValue.before(firstWeekLDate)) ) {
                	   if(environment.trim().contains("PROD")) {
                		   week1CounterP=week1CounterP+1; 
                	   }else {
                		   week1CounterQ=week1CounterQ+1; 
                	   }               	
                	
             	  }else
                 if((cellValue.equals(secondWeekFDate) || cellValue.after(secondWeekFDate)) && (cellValue.equals(secondWeekLDate) || cellValue.before(secondWeekLDate)) ) {
                	 if(environment.trim().contains("PROD")) {
              		   week2CounterP=week2CounterP+1; 
              	   }else {
              		   week2CounterQ=week2CounterQ+1; 
              	   }
                	
             	  }else
                 if((cellValue.equals(thirdWeekFDate) || cellValue.after(thirdWeekFDate)) && (cellValue.equals(thirdWeekLDate) || cellValue.before(thirdWeekLDate))) {
                	 if(environment.trim().contains("PROD")) {
              		   week3CounterP=week3CounterP+1; 
              	   }else {
              		   week3CounterQ=week3CounterQ+1; 
              	   }
                	
             	  }else
                 if((cellValue.equals(forthWeekFDate) || cellValue.after(forthWeekFDate)) && (cellValue.equals(forthWeekLDate) || cellValue.before(forthWeekLDate))) {
                	 if(environment.trim().contains("PROD")) {
              		   week4CounterP=week4CounterP+1; 
              	   }else {
              		   week4CounterQ=week4CounterQ+1; 
              	   }
                	
             	  }else
                 if((cellValue.equals(fiftWeekFDate) || cellValue.after(fiftWeekFDate)) && (cellValue.equals(fiftWeekLDate) || cellValue.before(fiftWeekLDate))) {
                	 if(environment.trim().contains("PROD")) {
              		   week5CounterP=week5CounterP+1; 
              	   }else {
              		   week5CounterQ=week5CounterQ+1; 
              	   }
                	
             	  }
                 }
               }        
               
        }
        
         totalCounterP=week1CounterP+week2CounterP+week3CounterP+week4CounterP+week5CounterP;
         totalCounterQ=week1CounterQ+week2CounterQ+week3CounterQ+week4CounterQ+week5CounterQ;
         //System.out.println("totalCounterP =============> "+ totalCounterP);
         //System.out.println("totalCounterQ =============> "+ totalCounterQ);
         
         String prodDeploymentCounts[][] = new String[6][2];
        
        prodDeploymentCounts[0][0]=week1CounterQ+"";  prodDeploymentCounts[0][1]=week1CounterP+"";
        prodDeploymentCounts[1][0]=week2CounterQ+"";  prodDeploymentCounts[1][1]=week2CounterP+"";
		prodDeploymentCounts[2][0]=week3CounterQ+"";  prodDeploymentCounts[2][1]=week3CounterP+"";
		prodDeploymentCounts[3][0]=week4CounterQ+"";  prodDeploymentCounts[3][1]=week4CounterP+"";
		prodDeploymentCounts[4][0]=week5CounterQ+"";  prodDeploymentCounts[4][1]=week5CounterP+"";
		prodDeploymentCounts[5][0]=totalCounterQ+"";  prodDeploymentCounts[5][1]=totalCounterP+"";
  
        return prodDeploymentCounts;
	}
	


public static Date[] getFinalExecuationDate(String execuationDate) {
	//String execuationDate="01/05/2018";
	Calendar cal = Calendar.getInstance();
	int month=0;
	int year=0;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
	
	
	if("".equals(execuationDate.toString().trim())) {
		Calendar cal2 = Calendar.getInstance();
		Date todayDate = new Date();
		cal2.setTime(todayDate);
		
		month=cal2.get(Calendar.MONTH);
		year=cal2.get(Calendar.YEAR);
		//System.out.println("Y->"+cal2.get(Calendar.YEAR) +" M->"+cal2.get(Calendar.MONTH));
		
	}else {
		String [] dateBreakUp=execuationDate.split("/");
		month= Integer.parseInt(dateBreakUp[1]);
		year= Integer.parseInt(dateBreakUp[2]);
	    month=month-1;
	    
	}
	
	
	cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH,month);
    cal.set(Calendar.DAY_OF_MONTH, 1);	
    
    //System.out.println("Calander Date"+cal.getTime());
	
	//System.out.println("Y->"+cal.get(Calendar.YEAR) +" M->"+cal.get(Calendar.MONTH));
   	
	int firstWeekFDay=1;
    int firstWeekLDay= 8-cal.get(Calendar.DAY_OF_WEEK);
    
    int secondWeekFDay=firstWeekLDay+1;
    int secondWeekLDay=secondWeekFDay+6;
    
    int thirdWeekFDay=secondWeekLDay+1;
    int thirdtWeekLDay=thirdWeekFDay+6;
    
    int forthWeekFDay=thirdtWeekLDay+1;
    int forthWeekLDay=forthWeekFDay+6;
    
    int fifthWeekFDay=forthWeekLDay+1;
    int fifthWeekLDay=0;
    
    
  
   
   //System.out.println("=======================");
     // System.out.println(cal.get(Calendar.MONTH));
    //System.out.println(Calendar.FEBRUARY);    
    //System.out.println("=======================");
    
   if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY) {
    	
	  
    	if(year %4 ==0 ) {    		
    			//System.out.println("LEAP");
    			fifthWeekLDay=29; 		
    		
    	}else {
    		//System.out.println("NO LEAP");
    		fifthWeekLDay=28;
		}
        	
    }else if (cal.get(Calendar.MONTH) == Calendar.JANUARY || cal.get(Calendar.MONTH) == Calendar.MARCH  || cal.get(Calendar.MONTH) == Calendar.MAY || cal.get(Calendar.MONTH) == Calendar.JULY  || cal.get(Calendar.MONTH) == Calendar.AUGUST || cal.get(Calendar.MONTH) == Calendar.OCTOBER || cal.get(Calendar.MONTH) == Calendar.DECEMBER  ) {
    	fifthWeekLDay=31;
    }else {
    	fifthWeekLDay=30;
    } 
   // System.out.println(fifthWeekLDay);
   //System.out.println(cal.get(Calendar.MONTH) == Calendar.MAY);
   Date firstWeekFDate=null;
   Date firstWeekLDate=null;
   
   Date secondWeekFDate=null;
   Date secondWeekLDate=null;
   
   Date thirdWeekFDate=null;
   Date thirdWeekLDate=null;
   
   Date forthWeekFDate=null;
   Date forthWeekLDate=null;
   
   Date fiftWeekFDate=null;
   Date fiftWeekLDate=null;
   
   Date weekArray[] = new Date[10];
   month=month+1;
  	try {
  		firstWeekFDate = dateFormat.parse(firstWeekFDay+"/"+month+"/"+year);
  		firstWeekLDate= dateFormat.parse(firstWeekLDay+"/"+month+"/"+year);
  	   
  	    secondWeekFDate=dateFormat.parse(secondWeekFDay+"/"+month+"/"+year);
  	    secondWeekLDate=dateFormat.parse(secondWeekLDay+"/"+month+"/"+year);
  	   
  	    thirdWeekFDate=dateFormat.parse(thirdWeekFDay+"/"+month+"/"+year);
  	    thirdWeekLDate=dateFormat.parse(thirdtWeekLDay+"/"+month+"/"+year);
  	   
  	    forthWeekFDate=dateFormat.parse(forthWeekFDay+"/"+month+"/"+year);
  	    forthWeekLDate=dateFormat.parse(forthWeekLDay+"/"+month+"/"+year);
  	   
  	    fiftWeekFDate=dateFormat.parse(fifthWeekFDay+"/"+month+"/"+year);
  	    fiftWeekLDate=dateFormat.parse(fifthWeekLDay+"/"+month+"/"+year);
  	    
  	  //  log.info("firstWeekFDate=>"+firstWeekFDate );log.info("firstWeekLDate=>"+firstWeekLDate );
	  //	log.info("secondWeekFDate=>"+secondWeekFDate );log.info("secondWeekLDate=>"+secondWeekLDate );
	  //	log.info("thirdWeekFDate=>"+thirdWeekFDate );log.info("thirdWeekLDate=>"+thirdWeekLDate );
	  //	log.info("forthWeekFDate=>"+forthWeekFDate );log.info("forthWeekLDate=>"+forthWeekLDate );
	  //	log.info("fiftWeekFDate=>"+fiftWeekFDate );log.info("fiftWeekLDate=>"+fiftWeekLDate );
  	 
  	        weekArray[0]=firstWeekFDate;
  			weekArray[1]=firstWeekLDate;
  			weekArray[2]=secondWeekFDate;
  			weekArray[3]=secondWeekLDate;
  			weekArray[4]=thirdWeekFDate;
  			weekArray[5]=thirdWeekLDate;
  			weekArray[6]=forthWeekFDate;
  			weekArray[7]=forthWeekLDate;
  			weekArray[8]=fiftWeekFDate;
  			weekArray[9]=fiftWeekLDate;
  	    
  	   
  	} catch (ParseException e1) {
  		// TODO Auto-generated catch block
  		e1.printStackTrace();
  	}
   
   
//	
//	if(finalexecuationDate.startsWith("0")) {
//		finalexecuationDate=finalexecuationDate.replaceFirst("0", "");
//	}
	return weekArray;	
 }

public static Properties getProperties() {
	
	Properties prop = new Properties();
	InputStream input = null;
	try {
		//input = new FileInputStream("./config/config.properties");
		input = new FileInputStream("D:\\Test\\config\\config.properties");
		prop.load(input);
					
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return prop;
}

public static String getMonthYear(String execuationDate) {
	
	Calendar cal = Calendar.getInstance();
	int month=0;
	int year=0;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
	String monthYear="";
	
	if("".equals(execuationDate.toString().trim())) {
		
		Date todayDate = new Date();
		month=todayDate.getMonth();
		year=todayDate.getYear()+1900;
		//log.info("Y->"+(todayDate.getYear()+1900) +" M->"+todayDate.getMonth());
		
	}else {
		String [] dateBreakUp=execuationDate.split("/");
		month= Integer.parseInt(dateBreakUp[1]);
		year= Integer.parseInt(dateBreakUp[2]);
	    month=month-1;
	    
	}
	
	
	cal.set(Calendar.YEAR, year);
    cal.set(Calendar.MONTH,month);
    //cal.set(Calendar.DAY_OF_MONTH, 1);
    switch (month) {
    
	    case 0:  monthYear = "January";
	    break;
	    case 1:  monthYear = "February";
	    break;
	    case 2:  monthYear = "March";
	    break;
	    case 3:  monthYear = "April";
	    break;
	    case 4:  monthYear = "May";
	    break;
	    case 5:  monthYear = "June";
	    break;
	    case 6:  monthYear = "July";
	    break;
	    case 7:  monthYear = "August";
	    break;
	    case 8:  monthYear = "September";
	    break;
	    case 9:  monthYear = "October";
	    break;
	    case 10:  monthYear = "November";
	    break;
	    case 11:  monthYear = "December";
	    break;    
    
    }
    
    monthYear=monthYear+"-"+year;
	
	return monthYear;
}
}
