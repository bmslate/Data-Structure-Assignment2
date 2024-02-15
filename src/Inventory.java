import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

/**
 * Extends FoodItem class to store item in inventory.
 * @author Huijun Bu
 *
 */

public class Inventory extends FoodItem{
	
	private ArrayList<FoodItem> inventory = new ArrayList<>();
	private String filePath="C:\\Users\\BU1\\Desktop\\laptop\\大学课程\\Level3\\8130\\";
	/**constructor*/
	public Inventory() {
		
	}
	/**
	 * Use Quic sort algorithm to sort the array.
	 * @param lowIndex set to 0
	 * @param highIndex set to array size minus 1
	 */
	private void sortArray(int lowIndex,int highIndex) {

		if(lowIndex >= highIndex) {
			return;
		}
		int leftPointer = lowIndex; 
		int rightPointer = highIndex;
		int mid = (lowIndex + highIndex)/2;//Find the middle point
		FoodItem temp1;
		temp1 = inventory.get(mid);
		inventory.set(mid, inventory.get(highIndex));//swap last index element to middle element
		inventory.set(highIndex, temp1);//swap mid index element to last element
		int pivot = inventory.get(highIndex).getItemCode();//set pivot to the last element of the array.
		
			while(leftPointer < rightPointer) {
				while( inventory.get(leftPointer).getItemCode() <= pivot && leftPointer < rightPointer) {
					leftPointer++;
				}
				while( inventory.get(rightPointer).getItemCode() >= pivot && leftPointer < rightPointer) {
					rightPointer--;
				}
				FoodItem temp;
				temp = inventory.get(leftPointer);
				inventory.set(leftPointer, inventory.get(rightPointer));//swap the value which greater than the pivot to right.
				inventory.set(rightPointer, temp);//swap the value which less than the pivot to left.
			}
			FoodItem temp;
			temp = inventory.get(leftPointer);
			inventory.set(leftPointer, inventory.get(highIndex));//swap the pivot to middle
			inventory.set(highIndex, temp);
			
			this.sortArray(lowIndex,leftPointer-1);//sort the left part of the array
			this.sortArray(leftPointer+1,highIndex);//sort the right part of the array.

		
	}
	/**
	 * adding Item to inventory.
	 */
	public boolean addItem(Scanner input) {
		
		try {
		System.out.print("Do you wish to add a fruit(f), vegetable(v), a preserve(p) or baked goods(b)?");
		String option = input.nextLine();
		
		FoodItem item = null;//Declare a variable of FoodItem object;
		switch(option) {
		case "f":
			item = new Fruit();
			((Fruit)item).itemType = "f";//convert it to Fruit type so that it can access the itemType property.
			break;
		case "v":
			item = new Vegitable();
			((Vegitable)item).itemType = "v";//convert it to Vegitable type so that it can access the itemType property.
			break;
		case "p":
			item = new Preserve();
			((Preserve)item).itemType = "p";//convert it to Preserve type so that it can access the itemType property.
			break;
		case "b":
			item = new Bake();
			((Bake)item).itemType = "b";//convert it to Bake type so that it can access the itemType property.
			break;
		}
		
		
		if(item.inputCode(input)) {
				
				 if(this.alreadyExists(item) == -1) {//the user's inputed code is not in the inventory array.
					 
						if(item.addItem(input)) {
							inventory.add(item);//adding item to inventory.
						}else {
							System.out.println("Invalid input");
							input.nextLine();
							return false;
						}
				 }else {
					 System.out.println("Item Code already exist");
				 }
		   }
		}catch (NullPointerException e) {
			System.out.println("Invalid input...");
			return true;
		}
		return true;
	}
	/**	
	 * Checking the user's code if it's exists in the inventory.
	 * @param item accepting a FoodItem object
	 * @return return the indext of matched item in array.
	 */
	public int alreadyExists(FoodItem item) {
		
		for(int i = 0; i<inventory.size();i++) {

			if(inventory.get(i)!=null && inventory.get(i).isEqual(item)) {	/*isEqual method allow program access private member of FoodItem class
				without using getter and seter.*/
				return i;
			}
		}		
		return -1;
	}
	/**
	 * print out the String
	 */
	public String toString() {
		System.out.println("Inventory:");
		String print = "";
		
		this.sortArray(0, inventory.size()-1);//sort Array
		for(FoodItem s: inventory) {
			if(s != null) {
			print+=s.toString();
			}
		}
		
		return print;// return the formated String
	}
	/**
	 * Update the quanitty 
	 * @param input scanner object
	 * @param buy  variable for buy or sell an item.
	 * @return if quantity has been updated, return true;
	 */
	public boolean updateQuantity(Scanner input,boolean buy) {
		
		if (buy) {
			if(inventory.size() != 0) {//check inventory status
			
				FoodItem update = new Fruit();// temperary use for storing itemCode;
				
					if(update.inputCode(input) && this.alreadyExists(update) != -1){//if inputed code Number exists in the inventory,allow user to update quantity.
						try {
							System.out.print("Enter valid quantity to buy: ");
							int updateQuantity = input.nextInt();
							input.nextLine();
							if(updateQuantity >=0) {
								
								if(inventory.get(this.alreadyExists(update)).updateItem(updateQuantity)) {/*areadyExists method return the index of the matched item in inventory array.
									use the updateItem method to update the quantity of matched item.*/
									System.out.println("The inventory has been updated successfuly");
									return true;
								}else {
									return false;}
							}else{
								return false;
								}
						}catch(InputMismatchException e) {
							input.nextLine();
							return false;
						}
						
					}else {
						System.out.println("Code not found in inventory...");
						System.out.println("Error...could not buy item");
						return true;
					}
			}else {
				System.out.println("Error...could not buy item");
				return true;
			}
		}else {//"buy" is set to false; That means Sell products;	
			
			if(inventory.size() != 0) {//check the invetory status
				FoodItem update = new Fruit();
				if(update.inputCode(input) && this.alreadyExists(update) != -1){
					try {
						System.out.print("Enter valid quantity to sell: ");
						int updateQuantity = input.nextInt();
							if(updateQuantity >= 0) {
								
								updateQuantity = -updateQuantity;//change the inputed quantity to negative value;
								input.nextLine();
								if(inventory.get(this.alreadyExists(update)).updateItem(updateQuantity)) {
									System.out.println("The inventory has been updated successfuly");
									return true;
								}else {
									System.out.println("Insufficient stock in inventory...\r\n"
											+ "Error...could not sell item %n");
									return true;
								}
							}else {
								return false;
								}
					}catch(InputMismatchException e) {
							input.nextLine();
							return false;
					}
					
				 }else {
						System.out.println("Code not found in inventory...");
						System.out.println("Error...could not sell item");
						return true;
				 }
			}else {
				System.out.println("Error...could not sell item");
				return true;
			}
		}		
	}

	/**
	 * Use binary search algorithm to find the element which matched the searchKey value in the array.
	 * @param input read user's searchKey
	 */
	public void searchForItem(Scanner input) {
		
		int low = 0;
		int high = inventory.size()-1;
		
		try {//handle mis-input exception
			System.out.print("Enter the code for the Item: ");
			int searchKey = input.nextInt();
			
			while(low<=high) {
				int mid = (low + high)/2;
				if(searchKey == inventory.get(mid).getItemCode()) {
					System.out.println(inventory.get(mid).toString());
					return;
				}else if (searchKey > inventory.get(mid).getItemCode()){
					low = mid+1;//narrow the searching range to right part of the array.
				}else if(searchKey < inventory.get(mid).getItemCode()) {
					high = mid-1;//narrow the searching range to left part of the array.
				}
			}
			System.out.println("Code not found in inventory...");
		}catch(InputMismatchException e) {
			System.out.println("Invalid input...");//tackle mis-inputed value
			input.nextLine();
		}
	}
	/**
	 * Save FoodItem to file
	 * @param scanner receiving a Scanner object
	 */
	public void saveToFile(Scanner input) {
		
		System.out.print("Enter the filename to save to: ");
		String fileName = input.nextLine();
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+fileName,true))){//use try with resource to close the resource properly.
			Formatter formatter = new Formatter(writer);//pass bufferedWriter object to formatter.
			for(FoodItem s: inventory) {
				s.outputItem(formatter);//passing formatter to outputItem as a parameter.
			}
	        formatter.close(); // close Formatter
	        System.out.println("Inventory saved to " + fileName);
		}catch(IOException e) {
			System.out.println("No such file..");
		}
	}
	
	/**
	 * Read inventory to a file
	 * @param input
	 */
	
	 public void readFromFile(Scanner input) {
		 boolean abort = false;
		 int existCode = 0;
		 System.out.print("Enter the filename to read from: ");
		 String readFileName = input.nextLine();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath+readFileName))){// use try with resource to close resource properly.
			String line;
			while((line = reader.readLine()) != null && abort == false) {//assign the read value to a string variable
				String itemType = line; //first line
				int itemCode = Integer.parseInt(reader.readLine());//read second line
				String itemName = reader.readLine();//read third line
				int itemQuantityStock = Integer.parseInt(reader.readLine());//read forth line
				float itemCost = Float.parseFloat(reader.readLine());//read fifth line
				float itemPrice = Float.parseFloat(reader.readLine());//read sisth line
				String supplierName = reader.readLine();//read seventh line.
				
				FoodItem item = null;
				
				switch(itemType) {
					case "f":
						item = new Fruit(itemType, itemCode, itemName, itemQuantityStock, itemCost, itemPrice, supplierName);
						break;
					case "v":
						item = new Vegitable(itemType, itemCode, itemName, itemQuantityStock, itemCost, itemPrice, supplierName);
						break;
					case "p":
						item = new Preserve(itemType, itemCode, itemName, itemQuantityStock, itemCost, itemPrice, supplierName);
						break;
					case "b":
						item = new Bake(itemType, itemCode, itemName, itemQuantityStock, itemCost, itemPrice, supplierName);
						break;
				}
				if(item != null && this.alreadyExists(item)== -1) {
					inventory.add(item);
				}else {
					existCode = item.getItemCode();
					abort = true;
					
				}				
			}						
		}catch(IOException e) {
			System.out.println("File Not Found, ignoring..");
		}
			
		if(abort) {//check the aborting point so that can prompt user to solve the problem.
			System.out.printf("Item code %d already exists%n",existCode);//print out existed item code.
			System.out.println("Error Encountered while reading the file, aborting...");
		}else {
		System.out.printf("Inventory data have been loaded from File %s%n",readFileName);}
	 }
	
}
