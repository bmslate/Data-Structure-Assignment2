import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Extends FoodItem Class
 * @author Huijun_Bu
 *
 */
public class Preserve extends FoodItem {

	private String size;
	protected String itemType;

	/**
	 * Constructor
	 */
	public Preserve() {
		
	}
	
	/**
	 * Parameterized constructor
	 * @param itemType
	 * @param itemCode
	 * @param itemName
	 * @param itemQuantityStock
	 * @param itemCost
	 * @param itemPrice
	 * @param size
	 */
	public Preserve(String itemType,int itemCode,String itemName,int itemQuantityStock,float itemCost,float itemPrice,String size) {
		super(itemCode, itemName, itemQuantityStock,itemCost,itemPrice);
		this.size = size;
		this.itemType = itemType;
	}
	
	/**seting the size.*/
	public boolean addItem(Scanner input) {
		boolean milli;
		
		super.addItem(input);
		do {
		try {
			milli = false;
		System.out.print("Enter the size of the jar in millilitres : ");
		size = input.nextLine();
		}catch (InputMismatchException e) {
			System.out.println("Invalid input");
			input.nextLine();
			milli = true;
		}
		}while(milli);
		return true;
	}
	/**inherit the super class String and concatenate the size string.*/
	public String toString() {
		
		String supplierSize = String.format("size: %smL %n",size);
		String preserve = super.toString() + supplierSize;
		return preserve;
	}

	/**
	 * concatonate the supplier name to the FoodItem properties.
	 * @param writer adding the size in the FoodItem format.
	 */
	public void outputItem(Formatter writer) {
		writer.format("%s%n",itemType);
		super.outputItem(writer);
		writer.format("%s%n",size);
	}
}
