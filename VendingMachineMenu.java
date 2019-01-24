import java.util.*;
import java.io.IOException;


/**
   A menu from the vending machine.
*/
public class VendingMachineMenu
{    
	private Scanner in;
	private static Coin[] coins;

	/**
		Constructs a VendingMachineMenu object
	*/
	public VendingMachineMenu()
	{
		in = new Scanner(System.in);
	}
   
	/**
	* Runs the vending machine system.
	* @param machine the vending machine
	* @throws IOException
	*/
	public void run(VendingMachine machine)
        throws IOException
	{
		boolean more = true;
      
		while (more)
		{ 
			System.out.println("S)how products  I)nsert coin  B)uy  A)dmin Menu  Q)uit");
			String command = in.nextLine().toUpperCase();

			if (command.equals("S"))
			{  /*
				getProductTypes() returns an array of products that doesn't contain duplicates
				*/
				for (Item p : machine.getProductTypes())
				System.out.println(p.getProduct());
			}
			else if (command.equals("I")) //allows one coin be inserted at a time
			{ 
				machine.addCoin((Coin) getChoice(coins));
			}
         
			else if (command.equals("B"))
			{              
				try
				{
					Item p = (Item) getChoice(machine.getProductTypes());
					machine.buyProduct(p);
					System.out.println("Purchased: " + p.getProduct().getName());
				}
				catch (VendingException ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		 
			else if(command.equals("A")){
			
				boolean fin = true;
				System.out.print("Please enter your password\n");
				String password = in.nextLine();
				
				if(machine.login(password)){
					while(fin){
				
						System.out.println("A)dd Product  R)emove coins  B)ack");
						command = in.nextLine().toUpperCase();
						if (command.equals("A")){  
							System.out.println("Description:");
							String description = in.nextLine();
							System.out.println("Price:");
							double price = in.nextDouble();
							System.out.println("Quantity:");
							int quantity = in.nextInt();
							in.nextLine(); // read the new-line character
							machine.addProduct(new Product(description, price), quantity);
						}
					
						else if(command.equals("R")){  
							System.out.println("Removed: " + machine.removeMoney());
						}
					
						else if(command.equals("B")){
							fin = false;
						}
					}
				}else
					System.out.println("Wrong password");
			}
         
			else if (command.equals("Q"))
			{ 
				more = false;
			}
		}
		WriteToFiles.writeStock(machine.getProducts());
		WriteToFiles.writeCoins(machine.coins.getSet());
	}
	
	/**
	* getChoice() shows you a list of objects with Letters as index
	* @param Object Array
	* @return Object picked by user
	*/
	private Object getChoice(Object[] choices)
	{
		while (true)
		{
			char c = 'A';
			for (Object choice : choices)
			{
				System.out.println(c + ") " + choice); 
				c++;
			}
			String input = in.nextLine();
			int n = input.toUpperCase().charAt(0) - 'A';
			if (0 <= n && n < choices.length)
				return choices[n];
		}      
	}
   
	/**
	* setCoinArray() sets the values of coins array with the values from the ArrayList
	* @param ArrayList of type Coin
	*/
	public static void setCoinArray(ArrayList<Coin> c)
	{
		coins = new Coin[c.size()];
		for(int i = 0;i < c.size();i++)
			coins[i] = c.get(i);
	}
   
	public static Coin [] getCoins(){
		return coins;
	}
}
