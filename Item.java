public class Item
{
	private int quantity;
	private Product product;
	
	/*
	*	Item() - Contructs a Item object
	*	@param p - product object
	*	@param q - this is the quantity 
	*/
	public Item(Product p, int q)
	{
		quantity = q;
		product = p;
	}
	/**
	*	addQuant() - adds quantity of a product bu incrementing it
	*	@param q This is the amount of quantity that is being added
	**/
	public void addQuant(int q)
	{
		quantity += q;
	}
	/**
	*	setQuantity() - sets the quantity
	*	@param q This is the amount of quantity
	**/
	public void setQuantity(int q)
	{
		quantity = q;
	}
	/**
	*	getProduct() - gets the Prroduct 
	*	@return product This is returns the product
	**/
	public Product getProduct()
	{
		return product;
	}
	/**
	*	getQuantity() - gets the quantity of the product
	*	@return quantity This return the quantity
	**/
	public int getQuantity()
	{
		return quantity;
	}
	/**
	*	toString() - return the name of product, price and quantity
	*	@return product, quantity 
	**/
	public String toString()
	{
		return product.toString() + ", " + quantity;
	}
}