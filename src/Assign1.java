import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Drive class program entry point;
 * @author Huijun_Bu
 *
 */
public class Assign1 {
	/**
	 * Program Entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Assign1 run = new Assign1();
		
		run.displayMenu(input);// Display Menu,passing scanner object to this method.

	}
	/**
	 * Display main menu of program;
	 * @param input accepting a scanner object to this method.
	 */
	public void displayMenu(Scanner input) {
		
		boolean quitPoint = true;
		
		Inventory inventory = new Inventory();//initiate the invetory object;
		
		do {
			System.out.println("1: Add Item to Inventory");
			System.out.println("2: Display Current Inventory");
			System.out.println("3: Buy Item(s)");
			System.out.println("4: Sell Item(s)");
			System.out.println("5: Search for Item");
			System.out.println("6: Save Inventory to File");
			System.out.println("7: Read Inventory from File");
			System.out.println("8: To Exit");
			
			try{int option = input.nextInt();
			input.nextLine();
			
			switch(option) {//processing user's option.
			case 1:  			
				
				if(inventory.addItem(input)) {
					
				}else {
					quitPoint = false;
				}
				break;//adding item to inventory
				
			case 2:
				System.out.print(inventory.toString());//print inventory
				System.out.println();
				break;
				
			case 3:

				boolean buy=true;//buy items 
				if(inventory.updateQuantity(input, buy)) {
				}else {
					System.out.println("Invalid quantity...");
					System.out.println("Error...could not buy item");
				}
				break;
				
			case 4:
				boolean sell=false;//sell items
				if(inventory.updateQuantity(input, sell)) {

				}else {
					System.out.println("Invalid quantity...");
					System.out.println("Error...could not sell item");
				}
				break;
				
			case 5:
				
				inventory.searchForItem(input);//Serch item
				break;
			case 6:
				inventory.saveToFile(input);
				break;
			case 7:
				inventory.readFromFile(input);
				break;
			case 8:
				System.out.println("Exiting...");//exit the program
				input.close(); // close the input.
				quitPoint = false;
				break;
			default :
				System.out.println("Please choose from 1-5 !");
				break;
			}
			}catch(InputMismatchException e) {
				System.out.println("Please choose from 1-5 !");
				input.nextLine();
			}

			
		}while(quitPoint);
		
	}

}
