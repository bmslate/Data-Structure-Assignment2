
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Super class FoodItem.
 * @author Huijun_Bu.
 *
 */
public abstract class FoodItem implements Comparable<FoodItem> {

	
	/*private vaiable of FoodItem object*/
	private int itemCode;
	private String itemName;
	private int itemQuantityStock;
	private float itemCost;
	private float itemPrice;
	
	/**
	 * constructor 
	 */
	public FoodItem() {
		this.itemCode=0;
		this.itemName=null;
		this.itemQuantityStock=0;
		this.itemCost=0;
		this.itemPrice=0;
	}
	/**
	 * parameterized constructor
	 * @param itemCode
	 * @param itemName
	 * @param itemQuantityStock
	 * @param itemCost
	 * @param itemPrice
	 */
	public FoodItem(int itemCode,String itemName,int itemQuantityStock,float itemCost,float itemPrice) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemQuantityStock = itemQuantityStock;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;		
	}
	
	/**
	 * Get item Code
	 * @return itemCode
	 */
	public int getItemCode() {
		return itemCode;
	}
	
	/**
	 * Setting item code
	 * @param input receiving a scanner object
	 * @return true if the code has been set successfuly
	 */
	public boolean inputCode(Scanner input) {
		boolean quitPoint;
		do {
			quitPoint = false;
			try {
				System.out.print("Enter the code for the item: ");
				itemCode = input.nextInt();
				input.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("Invalid code...");
				input.nextLine();
				quitPoint = true;
			}
		}while(quitPoint);
		return true;
	}
	/**
	 * Setting the other properties of FoodItem object.
	 * @param input received a scanner object;
	 * @return true if the properties have been set successfuly.
	 */
	public boolean addItem(Scanner input) {
		boolean cost;
		boolean quantity;
		boolean price = false;
		System.out.print("Enter the name for the item: ");
		itemName = input.nextLine();
		
		do {
			quantity = false;
			try {
				System.out.print("Enter the quantity for the item: ");
				itemQuantityStock = input.nextInt();
				input.nextLine();
				if(itemQuantityStock < 0) {
					System.out.println("Invalid entry");
					quantity = true;
				}
			}catch(InputMismatchException e){
				System.out.println("Invalid entry");
				input.nextLine();
				quantity = true;				
			}
		}while(quantity);
		
		do {	
			try {
				cost = false;
				System.out.print("Enter the cost of the item: ");
				itemCost = input.nextFloat();
				input.nextLine();
				if(itemCost < 0) {
					System.out.println("Invalid entry");
					cost = true;
				}
			}catch(InputMismatchException e){
				System.out.println("Invalid entry");
				input.nextLine();
				cost = true;				
			}
		}while(cost);
		
		do {
			try {	
				price = false;
				System.out.print("Enter the sales price of the item: ");
				itemPrice = input.nextFloat();
				input.nextLine();
				if(itemPrice < 0) {
					System.out.println("Invalid entry");
					price = true;
				}
			
			}catch(InputMismatchException e){
				System.out.println("Invalid entry");
				input.nextLine();
				price = true;				
			}		
		}while(price);
		
		return true;
	}
	
	/**
	 * Updateing the inventory quantity
	 * @param  amount receiving an integer 
	 * @return true if the quantity is valid.
	 */
	public boolean updateItem(int amount) {
			int update = 0;
			update = itemQuantityStock + amount;
			if(update >= 0) {
				itemQuantityStock = update;
			}else {
				return false;
			}			
		return true;			
}
	
	/**compare the user inputed item code with the inventory item code.
	 *@param item receiving a FoodItem Object. 
	 *@return true if the user's inputed code matched with the FoodItem code in inventory array.
	 */
	public boolean isEqual(FoodItem item) {
		if(item == null) {
			return false;
		}
		if(this.compareTo(item) == 0) {
			return true;
		}
		return false;
		
	}
	// build the inventory details String.
	public String toString() {
		String output;
		output = String.format("Item: %d %s %d price: $%.2f cost: $%.2f ",itemCode,itemName,itemQuantityStock,itemPrice,itemCost);
		return output;
	}
	/**
	 * create a formatted string format for storing the data in proper format.
	 * @param writer write a output string format
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d%n%s%n%d%n%.2f%n%.2f%n",itemCode,itemName,itemQuantityStock,itemPrice,itemCost);//every property of the item will occupied a whole lien.
	}
	
	/**
	 *
	 *@param FoodItem o  passing a FoodItem Object.
	 * 
	 */
	@Override
	public int compareTo(FoodItem o) {		
		return this.itemCode - o.itemCode;
	}
	
}
