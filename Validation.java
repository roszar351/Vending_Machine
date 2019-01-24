import java.io.*;
public class Validation
{
	String message = "";
	
	/**
	* fileExists() checks if the file exists 
	* @param File f - inputs f which is of type file
	* @returns boolean true if file exists, false if file doesn't exist
	*/
	public static boolean fileExists(File f)
	{
		return f.exists();
	}
	
	/**
	* checkOperator() checks if operator is in correct format
	* @param String id - inputs the id of the operator as a string
	* @returns boolean true if in correct format, false if in wrong format 
	*/
	public static boolean checkOperator(String id)
	{
		String pattern = "[0-9]{6,}";
		return id.matches(pattern);
	}
	
	/**
	* checkFileFormat() checks if the file is in the correct format
	* @param line - inserts a line from the txt file
	* @returns boolean returns true or false if the file is in the correct format
	**/
	public static boolean checkFileFormat(String line)
	{
		String pattern = ".*,\\s[0-9]{0,}\\.?[0-9]{0,2},\\s[0-9]{1,}";
		return line.matches(pattern);
	}
}