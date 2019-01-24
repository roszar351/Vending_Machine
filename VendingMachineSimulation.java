import java.io.IOException;
import javafx.application.Application;

/* Made by: 
 * 
 * Darragh Kelly   - 17235545
 * Pawel Ostach    - 17214211
 * Eoghan Russell  - 17202124
 * Damian Skrzypek - 17217679
 *
 */

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation
{ 
	/*
	*	This creates the menu for the vending machine
	*	@param args This passes the argument for which menu to choose
	*/
	public static void main(String[] args) throws IOException
	{ 
		if(args.length == 1)
		{
			if(args[0].equals("2"))
			{
				Application.launch(GUIMenu.class);
			}
			else if(args[0].equals("1"))
			{
				VendingMachine machine = new VendingMachine();
				VendingMachineMenu menu = new VendingMachineMenu();
				menu.run(machine);
			}
			else
				System.out.println("USAGE: java VendingMachineSimulation 1(CMD) or 2(GUI)");
		}
		else
		{
			System.out.println("USAGE: java VendingMachineSimulation 1(CMD) or 2(GUI)");
		}
		
	}
}
