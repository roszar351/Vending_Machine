import java.io.*;
import java.util.*;

public class WriteToFiles{
	
	/**
	* writeStock() method writes the current stock into a text file
	* @param ArrayList of type Item
	*/
	public static void writeStock(ArrayList<Item> n) throws IOException{
		
		File stock = new File("DataFiles/Stock.txt");
		PrintWriter out = new PrintWriter(stock);
		
		for(int i = 0; i < n.size(); i++){
			
			out.println(n.get(i).toString());
			
		}
		out.close();
	}
	
	/**
	* writeCoins() method writes the current coins into a text file
	* @param ArrayList of type Coin
	*/
	public static void writeCoins(ArrayList<Coin> c) throws IOException{
		
		File money = new File("DataFiles/Money.txt");
		PrintWriter out = new PrintWriter(money);
		
		int count = 0;
		for(int i = 0; i<VendingMachineMenu.getCoins().length; i++){
			for(int j =0; j< c.size(); j++){
			
				if(VendingMachineMenu.getCoins()[i].toString().equals(c.get(j).toString()))// calls the method from VendingMachineMenu class to get the coins 
					count++;																	//array and compares the names of every coin with actual coins in the machine
			}
			out.println(VendingMachineMenu.getCoins()[i].toString() + ", " + VendingMachineMenu.getCoins()[i].getValue() + ", " + count); 
			count = 0;
		}
		out.close();
	}
	
}