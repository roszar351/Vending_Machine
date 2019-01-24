import java.util.ArrayList;
import java.io.*;

/**
   A vending machine.
*/
public class VendingMachine
{  
	private ArrayList<Item> products;
	public CoinSet coins;
	public CoinSet currentCoins;

	/**
	* Constructs a VendingMachine object.
	*/
	public VendingMachine() throws IOException
	{ 
		products = new ArrayList<Item>();
		coins = new CoinSet();
		currentCoins = new CoinSet();
		if(!(ReadFromFiles.loadMoneyIntoMemory(coins) && ReadFromFiles.loadStockIntoMemory(products)))
			System.exit(1);
	}
	/**
	* getProducts() is used to get the arrayList of Products
	* @return ArrayList of type Item
	*/
	public ArrayList<Item> getProducts()
	{
		return products;
	}
	
	/**
	* getProductTypes() converts an ArrayList to and Array of Items
	* @return Array of type Item
	*/
	public Item[] getProductTypes()
	{
		Item[] temp = new Item[products.size()];
		temp = products.toArray(temp);
	   return temp;
	}
	
	/**
	* addCoin() adds a coin to currentCoins CoinSet
	* @param object c of type Coin
	*/
	public void addCoin(Coin c)
	{
		currentCoins.getSet().add(c);
	}
   
	/**
	* removeMoney() Allows an operator to remove money from the machine
	* @return String with a message that includes total amount of money and the list of all the coins removed
	*/
	public String removeMoney()
	{
		String msg = coins.totalMoney() + "(" + coins.toString() + ")";
		coins.getSet().clear();
		
		return msg;
	}
	
	/**
	* addProduct() Allows an operator to add a product to the vending machine
	* @param Object p of type Product and quantity of the product in form of integer
	*/
	public void addProduct(Product p, int quant)
	{
		int index = -1;
		for(int i = 0; i < products.size() && index == -1; i++)
			if(products.get(i).getProduct().toString().equals(p.toString()))
				index = i;
		
		if(index == -1)
		{
			products.add(new Item(p, quant));
		}
		else
			products.get(index).addQuant(quant);
	}
	
	/**
	* buyProduct() allows to buy a product. Quantity of that product is decremented
	* @param object of type Item
	* @throws VendingException
	*/
	public void buyProduct(Item p) throws VendingException
	{
		int index = products.indexOf(p);
		if(products.get(index).getQuantity() > 0)
		{
			if(currentCoins.totalMoney() >= p.getProduct().getPrice())
			{
				coins.getSet().addAll(currentCoins.getSet());
				currentCoins.getSet().clear();
				products.get(index).setQuantity(products.get(index).getQuantity() - 1);
			}
			else
			{
				currentCoins.getSet().clear();
				throw new VendingException("Not enough money, price is: " + p.getProduct().getPrice() + ", money returned.");
			}
		}
		else
		{
			currentCoins.getSet().clear();
			throw new VendingException("Product: " + p.getProduct() + " is out of stock, money returned.");
		}
	}
	
	/**
	* Login() compares user input and actuall operator code from a file
	* @param String with user input
	* @return true if user code is same as the one in the file and false if they are not equal
	*/
	public boolean login(String loginCode) throws IOException
	{
		return loginCode.equals(ReadFromFiles.loadOperatorCodeIntoMemory());
	}
   
}