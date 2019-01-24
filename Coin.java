/**
   A coin with a monetary value.
*/
public class Coin
{
   private double value;
   private String name;

   /**
      Constructs a coin.
      @param aValue the monetary value of the coin.
      @param aName the name of the coin
   */   
   public Coin(double aValue, String aName) 
   { 
      value = aValue; 
      name = aName;
   }
	/**
	* getValue() returns value of coin
	* @returns double 
	*/
   public double getValue()
   {
	   return value;
   }
   /**
   * toString() returns the name of the coin
   * @returns String
   */
   public String toString()
   {
	   return name;
   }
}