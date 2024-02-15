import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Exteds FoodItem
 * @author Huijun_Bu
 *
 */
public class Bake extends FoodItem{
	
	private String bakeStore;
	protected String itemType;
	
	/**
	 * Constructor
	 */
	public Bake() {
		
	}
	/**
	 * Parameterized constructor
	 * @param itemType
	 * @param itemCode
	 * @param itemName
	 * @param itemQuantityStock
	 * @param itemCost
	 * @param itemPrice
	 * @param bakeStore
	 */
	public Bake(String itemType,int itemCode,String itemName,int itemQuantityStock,float itemCost,float itemPrice,String bakeStore) {
		super(itemCode, itemName, itemQuantityStock,itemCost,itemPrice);
		this.bakeStore = bakeStore;
		this.itemType = itemType;
	}
	
	//seting the supplier name.
	public boolean addItem(Scanner input) {
		
		super.addItem(input);
		try {
		System.out.print("Enter the name of the bake goods supplier: ");
		bakeStore = input.nextLine();
		}catch (InputMismatchException e) {
			System.out.println("Invalid input");
			input.nextLine();
			return false;
		}
		return true;
	}
	//inherit the super class String and concatenate the supplier name.
	public String toString() {
		
		String supplier = String.format("bake goods supplier: %s %n",bakeStore);
		String bake = super.toString() + supplier;
		return bake;
	}
	
	/**
	 * concatonate the supplier name to the FoodItem properties.
	 * @param writer adding the supplier name in the FoodItem format.
	 */
	public void outputItem(Formatter writer) {
		writer.format("%s%n",itemType);
		super.outputItem(writer);
		writer.format("%s%n",bakeStore);
	}
	
}
