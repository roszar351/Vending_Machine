/**
   A product in a vending machine.
*/
public class Product
{  
	private String description;
	private double price;

    /**
     *	Product() - Contrructs a product object
     * 	@param aDescription the description of the product
     * 	@param aPrice the price of the product
     */
	public Product(String aDescription, double aPrice)
    {  
		description = aDescription;
		price = aPrice;
    }
	/**
	*	getName() -  gets the name of product
	*	@return name Returns name of product
	**/
	public String getName()
	{
		return description;
	}
	/**
	*	getPrice() - gets the price
	*	@return price Returns the price of product
	**/
	public double getPrice()
	{
		return price;
	}
	/**
	*	toString() - return the description and price
	*	@return description, price This returns the description and price of product
	**/
	public String toString()
	{
		return description + ", " + price;
	}
}
