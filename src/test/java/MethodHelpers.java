
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;

import org.testng.annotations.Test;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class MethodHelpers {
	public static String pathToScripts ="\\\\mgsops\\data\\Public\\EventInsight\\Lungile\\Test Scripts\\";
	public static String passing =pathToScripts+"connectionString.txt";
	public static String path ="\\\\mgsops\\data\\Public\\EventInsight\\Lungile\\Logs\\";
	public static String pathSi1 ="\\\\eventinsight1\\c$\\MGSLog\\";
	public static String pathSi2 ="\\\\eventinsight2\\c$\\MGSLog\\";
	public static String conEventinsight;
	public static String fileName = "\\\\mgsops\\data\\mgs_server\\Testing_Dropoff\\Environments\\";
	public final static String time = timestamping();
	public static String fileNameLog =MethodHelpers.path+timestamping()+"\\";

	public static boolean deleteFile(String path)
	{
		File fin = new File(path);
		try {
			FileDeleteStrategy.FORCE.delete(fin);
			return true;
		} catch (IOException e) {
			System.out.println("I am unable to delete this file, I am soo sorry");
			return false;
		}
	}
	//Derivco API string
	public static String securityV2 = "http://webserver4/system/security/v2/";
	static {
		try {
			conEventinsight = getString(passing,"gamingdb7");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static  String userGroup;
	static {
		try {
			userGroup = getString(passing,"userid");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static  String conCasino;
	static {
		try {
			conCasino = getString(passing,"casino");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getString(String fileName, String searchTerm) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line =null;
		while ((line=in.readLine()) != null)
		{
			if (line.contains(searchTerm))
			{
				//System.out.println("Name_______________::::::"+line);
				return line;
			}
				else {continue;}

		}

		return "NotAvailable";
	}
	public static String findList(String path, String searchName) throws IOException {	String line;
		BufferedReader in = new BufferedReader(new FileReader(path));
		while ((line=in.readLine()) != null)
		{
			if (line.contains(searchName))
			{	
				 return line;
			}
		}
		return "ayikho";
	}
	public static String dbCon(String insertSql, int column, String connectionUrl) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
		}


		ResultSet resultSet = null;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException throwables) {
			System.out.println("Could not find the connectionURL provided");
		}
		// PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS );
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException throwables) {
			System.out.println("Could not create a statement");
		}
		try {
			resultSet = statement.executeQuery(insertSql);
		} catch (SQLException throwables) {
			System.out.println("Could execute the query");
		}
		// prepsInsertProduct.execute();
		// Retrieve the generated key from the insert.
		// resultSet = prepsInsertProduct.getGeneratedKeys();
		String name =null;
		// Print the ID of the inserted row.
		while (true) {
			try {
				if (!resultSet.next()) break;
			} catch (SQLException throwables) {
				System.out.println("There is no result set");
			}
			try {
				name = resultSet.getString(column);
			} catch (SQLException throwables) {
				System.out.println("Could not return with the column");
			}

		}

		return name;
	}
	@Test(priority =1)
	public static void testCon () throws SQLException {
		String it =dbCon("SELECT * FROM [EventInsight].[dbo].[tb_StandingQuery]",
				 6,
				conEventinsight);
		System.out.println("In the test_______________::::::"+it);

	}
	public static boolean finder( String dirName, String filed) {
	  		File file;
	  		String fileContext =null;
	    	file = new File(dirName+filed)
	    			;
	    	if(!new File(dirName, filed).exists())
	    	{
	    		return false;
	    	}
	  	  
	    	else {
	  	       
	  	    	  
	  			try {
	  	   			fileContext = FileUtils.readFileToString(file,"UTF-8");
	  	   			
	  	   		} catch (IOException e) {
	  	   			
	  	   			e.printStackTrace();
	  	   		}	
	  	   		
	  	       	if( fileContext.contains("}"))
	  	       	{
	  	       		return true;
	  	       	}
	  	       	else { return false;}
	  	   		
	  	       }
	    	
	    	}
	    	public static void writeCSVLine(String fileDir, String result,boolean append) {


	          FileWriter file = null;


	          try {
	              file = new FileWriter(fileDir, append);
	          } catch (IOException e) {
	              System.out.println("Could not create file with directory name" + fileDir + ";");
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	          }

	          try {
	              file.write(result+"\n");
	          } catch (IOException e1) {
	              // TODO Auto-generated catch block
	              e1.printStackTrace();
	          }


	          try {
	              file.close();
	          } catch (IOException e) {
	              // TODO Auto-generated catch block
	              System.out.println("Could not close the file " + fileDir + " ;");
	              e.printStackTrace();
	          }


	      }


	public static String getGuid( )//copied
	{
		UUID uuid = UUID.randomUUID();
		String guid = uuid.toString();
		return guid;
	}

	public static String getDate()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String formattedDate = formatter.format(LocalDate.now());
		return formattedDate;
	}



	public static BufferedReader getinputFile(String path)
	{		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("The log file is not present or the server eventinsight is not working");
			e.printStackTrace();
		}

		return in;
	}
	public static String timestamping ( )
	{
		String timestamp  =new Timestamp(System.currentTimeMillis())+"";
		timestamp =timestamp.replace('-','.')
				.replace(':','.')
				.replace(' ','.');
		return timestamp+"";
	}
	public static String cleanName (String line, int type ) throws SQLException { 	String sql;
		if (type==1) {
			String temp = line.substring(line.indexOf("QueryID:")-1 + 10, line.indexOf(","));
			System.out.println("This is the name of the SQ: " +temp);
			sql = "SELECT * FROM [EventInsight].[dbo].[tb_StandingQuery] where QueryID ='"+temp+"'";
			String url =MethodHelpers.conEventinsight;
			System.out.println("This is the name of the url: " + url);
			String fname =  MethodHelpers.dbCon(sql,2,url);

			return fname;
		}

		if (type==2) {

			String temp=line.substring( line.indexOf("StreamName: ")+12, line.indexOf("StreamName: ")+70);
			String name = temp.substring(0, temp.indexOf(","));
			//System.out.println("This is the my heart: " + name);
			return name;
		}
		return null;
	}
	public static void writeToFile(String direct, String fileName, String line)
	{
		line = line.replaceAll(",", ",\r\n")+"\r\n\t";
		writeCSVLine (direct+"\\"+timestamping()+" "+fileName+".txt", line,true);
	}
	public static void saveStream (String line, String pathtoFile ) throws SQLException {
		String Name =cleanName (line,2);
		System.out.println("The path ___________________"+pathtoFile);

		writeToFile(pathtoFile,Name,line);

		return;
	}
	public static String makeDirectory(String pathToFile)
	{
		File file = new File(pathToFile);
		//  System.out.println("This is the time " + timestamping());

		if (!file.exists())
		{
			file.mkdir();
			System.out.println("The log file is not prese "+file.getAbsolutePath());
			return file.getAbsolutePath() ;
		}
		return file.getAbsolutePath();
	}

}
