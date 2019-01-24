import java.util.ArrayList;

/**
   A set of coins.
*/
public class CoinSet
{  
   private ArrayList<Coin> set;

   /**
    * Constructs a CoinSet object.
   */
   public CoinSet()
   {  
      set = new ArrayList<Coin>();
   }
   /**
   * getSet() returns an ArrayList of Coin
   * @returns set - the arraylist of Coin
   */
   public ArrayList<Coin> getSet()
   {
	   return set;
   }
   /**
   * totalMoney() - gets the total sum of the money
   * @returns double - value of the money
   */
   public double totalMoney()
   {
	   double total = 0;
	   for(Coin c: set)
		   total += c.getValue();
		  
		return total;
   }
   /**
   * toString() - returns a String of the coin array 
   * @returns String 
   */
   public String toString()
   {
		String msg = "";
		for(Coin c : set)
		   msg += c + ", ";
		
		if(msg.length() > 0)
			msg = msg.substring(0, msg.length() - 2); // remove the comma and space at end
		return msg;
   }
}