import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import java.util.Scanner;


public class JavaIODemo {

	static boolean createFile(String storeName)
	{
		File file = new File(storeName+".txt");
		if(file.exists())
		{
			System.out.println(storeName+" already exists");
			return false;
		}
		try
		{
			
			FileOutputStream fileOutput = new FileOutputStream(file);
			
			System.out.println("Store "+ storeName+" created");
			fileOutput.close();
			return true;
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e){e.printStackTrace();}
		
		return false;
	}
	
	static boolean deleteFile(String storeName)
	{
		File file = new File(storeName+".txt");
		
		if(file.exists()) 
		{	
			try
			{
			file.delete();
			System.out.println("Store "+ storeName+" deleted");
			return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		else {System.out.println(storeName +" does not exists");}
		
		
		
		
		return false;
	}
	
	static boolean insert(String storeName, String key, String value)
	{
		//using java's Properties class, to store key value data 
		File file = new File(storeName+".txt");
		FileOutputStream fileOutput = null;
		Properties properties = new Properties();
		
		if(!file.exists())
		{
			try
			{		
				file.createNewFile();
			fileOutput = new FileOutputStream(file,true);
			properties.setProperty(key, value);
			properties.store(fileOutput,"wirestorm"); //wirestorm is for comment ...
			fileOutput.close();
			
			System.out.println(key+" "+ value+" inserted into "+storeName);
			return true;
					
			}
			catch(IOException e){e.printStackTrace();}
			
		}
		
		else
		{
		try
		{
		fileOutput = new FileOutputStream(file,true);
		properties.setProperty(key, value);
		properties.store(fileOutput,"wirestorm"); //wirestorm is for comment ...
		fileOutput.close();
		
		System.out.println(key+" "+ value+" inserted into "+storeName);
		
		return true;
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		}
		return false;
	}

	static String get(String key,String storeName)
	{
		String value = null;
		File file = new File(storeName+".txt");
		if(!file.exists())
		{
			System.out.println(storeName+" does not exists");
			return null;
		}
		
		try
		{
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			
			 value = properties.getProperty(key);
			 fileInput.close();
		
		}
		
		catch(FileNotFoundException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
		
		return value;
	}
	
	static boolean exists(String key,String storeName)
	{
		File file = new File(storeName+".txt");
		
		if(!file.exists())
		{
			System.out.println(storeName+" does not exists");
			return false;
		}
		
		try
		{
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			
			return properties.containsKey(key);
		}
		
		catch(FileNotFoundException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
		
		return false;
	}
	
	
	public static void main(String args[])
	{
		
		System.out.println("Please write any method name with its arguments");
		
		Scanner userInput = new Scanner(System.in);
		String userCommand = userInput.nextLine();
		String parts[] = userCommand.split(" ");
		
		/*for(String part:parts ) //for debuging
			System.out.println(part);
		*/
		//System.out.println("parts[0] "+parts[0]); //for debuging
		
		switch (parts[0])
		{
		case "create":
			{
				String storeName ="";
				for(int i=1; i<parts.length; i++)
					storeName += parts[i]+" ";
				
				createFile(storeName);
			break;
			}
			
		case "delete":
		{
			String storeName ="";
			for(int i=1; i<parts.length; i++)
				storeName += parts[i]+" ";
			
			
			deleteFile(storeName);
			
			break;
		}
		
		case "insert":
			
		{
			if(!(parts.length>4))
			{
				System.out.println("In valid arguments");
				break;
			}
			String storeName = "";
			String key = "";
			String value="";
			
			for(int i=4; i<parts.length; i++)
				storeName += parts[i]+" ";
			key = parts[1];
			value = parts[2];
			
			insert(storeName, key, value);
			
			break;
		}
		
		case "get":
		{
			if(!(parts.length>3))
				System.out.println("Invalid Arguments");
			
			String storeName ="";
			String key ="";
			
			for(int i=3; i<parts.length; i++)
				storeName += parts[i]+" ";
			
			key = parts[1];
			String result = get(key, storeName);
			if(result == null)
				System.out.println(key+" not found");
			else 
			{
					System.out.println(result);
			}
			break;
		}
		
		case "exists":
		{
			if(!(parts.length>3))
				System.out.println("Invalid Arguments");
			
			String storeName ="";
			String key ="";
			
			for(int i=3; i<parts.length; i++)
				storeName += parts[i]+" ";
			key = parts[1];
			
			System.out.println(exists(key, storeName));
			
			break;
		}
		
		
		default:
		{
			System.out.println("Invalid Arguments");
			break;
		}
		
		
		}
		
		
		
		
		
		
	}
}
