//Base Item class
public class Item {
	String id = "Item";
	String name;
	String desc;
	int accuracy;
	int damage;
	int defense;
	boolean isWearable = false;
	boolean isEquipped = false;
	String wearloc;
	String type;

	public void look() {
		System.out.println(name);
		System.out.println("Accuracy: " + accuracy);
		System.out.println("Damage: " + damage);
		System.out.println("Defense: " + defense);
	}

}
