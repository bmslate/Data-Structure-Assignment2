import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Extends FoodItem class
 * @author Huijun_Bu
 *
 */
public class Fruit extends FoodItem {
	
	private String orchardName;
	protected String itemType;
	
	/**
	 * Constructor
	 */
	public Fruit() {
		
	}
	/**
	 * Parameterized constructor
	 * @param itemType
	 * @param itemCode
	 * @param itemName
	 * @param itemQuantityStock
	 * @param itemCost
	 * @param itemPrice
	 * @param orchardName
	 */
	public Fruit(String itemType,int itemCode,String itemName,int itemQuantityStock,float itemCost,float itemPrice,String orchardName) {
		super(itemCode, itemName, itemQuantityStock,itemCost,itemPrice);
		this.orchardName = orchardName;
		this.itemType = itemType;
	}
	
	//seting the supplier name.
	public boolean addItem(Scanner input) {
		
		
		super.addItem(input);
		try {
		System.out.print("Enter the name of the orchard supplier: ");
		orchardName = input.nextLine();
		}catch (InputMismatchException e) {
			input.nextLine();
			return false;
		}
		return true;
	}
	//inherit the super class String and concatenate the supplier name.
	public String toString() {
		
		String supplier = String.format("orchard supplier: %s %n",orchardName);
		String fruit = super.toString() + supplier;
		return fruit;
	}
	
	/**
	 * concatonate the supplier name to the FoodItem properties.
	 * @param writer adding the supplier name in the FoodItem format.
	 */
	public void outputItem(Formatter writer) {
		writer.format("%s%n",itemType);
		super.outputItem(writer);
		writer.format("%s%n",orchardName);
	}
	
}
