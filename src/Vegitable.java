import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Extends FoodItem Class
 * @author Huijun Bu
 *
 */
public class Vegitable extends FoodItem{

	private String farmName;
	protected String itemType;
	
	/**
	 * Constructor
	 */
	public Vegitable() {
		
	}
	
	/**
	 * Parameterized constructor
	 * @param itemType
	 * @param itemCode
	 * @param itemName
	 * @param itemQuantityStock
	 * @param itemCost
	 * @param itemPrice
	 * @param farmName
	 */
	public Vegitable(String itemType,int itemCode,String itemName,int itemQuantityStock,float itemCost,float itemPrice,String farmName) {
		super(itemCode, itemName, itemQuantityStock,itemCost,itemPrice);
		this.farmName = farmName;
		this.itemType = itemType;
	}
	
	/**seting the supplier name.*/
	public boolean addItem(Scanner input) {
		
		super.addItem(input);
		try {
		System.out.print("Enter the name of  the farm supplier: ");
		farmName = input.nextLine();
		}catch (InputMismatchException e) {
			System.out.println("Invalid input");
			input.nextLine();
			return false;
		}
		return true;
	}
	/**inherit the super class String and concatenate the supplier name.*/
	public String toString() {
		
		String supplier = String.format("farm supplier: %s %n",farmName);
		String vegitable = super.toString() + supplier;
		return vegitable;
	}
	
	/**
	 * concatonate the supplier name to the FoodItem properties.
	 * @param writer adding the supplier name in the FoodItem format.
	 */
	public void outputItem(Formatter writer) {
		writer.format("%s%n",itemType);
		super.outputItem(writer);
		writer.format("%s%n",farmName);
	}



}
