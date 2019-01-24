import java.io.*;
import java.util.*;
// Reads data from files into correct arrays, objects etc
public class ReadFromFiles 
{
	private static File file_1 = new File("DataFiles/Money.txt");
	private static File file_2 = new File("DataFiles/OperatorCode.txt");
	private static File file_3 = new File("DataFiles/Stock.txt");
	private static String[] fileElements;
	
	/**
	* loadMoneyIntoMemory() loads the money from the Money.txt file into memory
	* @param CoinSet coins inputs a coin of type CoinSet
	* @returns boolean true if successful and false if not
	*/
	public static boolean loadMoneyIntoMemory(CoinSet coins) throws IOException
	{
		if(Validation.fileExists(file_1))
		{
			ArrayList<Coin> temp = new ArrayList<>();
			ArrayList<Coin> typeOfCoins = new ArrayList<>();
			temp = coins.getSet();
			Scanner in = new Scanner(file_1);
			String t = "";

			while(in.hasNext())
			{
				t = in.nextLine();
				if(Validation.checkFileFormat(t))
				{
					fileElements = t.split(", ");
					typeOfCoins.add(new Coin(Double.parseDouble(fileElements[1]),fileElements[0]));
					for(int i = 0; i < Double.parseDouble(fileElements[2]);i++)
					{
						temp.add(new Coin(Double.parseDouble(fileElements[1]),fileElements[0]));
					}
					
				}
				else
				{
					System.out.println("File lines not in correct Format!");
					return false;
				}
			}
			VendingMachineMenu.setCoinArray(typeOfCoins);
			in.close();
			return true;
		}
		
		System.out.println("Error: Money.txt not Found");
		return false;
	}
	
	/**
	* loadOperatorCodeIntoMemory() loads the operator id into memory
	* @return String as the id of the operator 
	*/
	public static String loadOperatorCodeIntoMemory() throws IOException
	{
		if(Validation.fileExists(file_2))
		{
			Scanner in = new Scanner(file_2);
			String temp = "";
			while(in.hasNext())
			{
				temp = in.nextLine();
					if(Validation.checkOperator(temp))
						return temp;
					else
						System.out.println("Error: Operator ID not in correct Format");
					return null;
			}
			in.close();
		}
		else
		{
			System.out.println("Error: File OperatorCode.txt not Found");
		}
		return null;
	}
	
	/**
	* loadStockIntoMemory()  loads the stock into memory
	* @param ArrayList<Item> items inputs an arraylist of tpye Item 
	* @returns boolean true if stock is loaded successfully and false if failed
	*/
	public static boolean loadStockIntoMemory(ArrayList<Item> items) throws IOException
	{
		if(Validation.fileExists(file_3))
		{
			Scanner in = new Scanner(file_3);
			String temp = "";
			while(in.hasNext())
			{
				temp = in.nextLine();
				if(Validation.checkFileFormat(temp))
				{
					fileElements = temp.split(", ");
					items.add(new Item(new Product(fileElements[0], Double.parseDouble(fileElements[1])),Integer.parseInt(fileElements[2])));
				}
			}
			in.close();
			return true;
		}
		return false;
	}
}