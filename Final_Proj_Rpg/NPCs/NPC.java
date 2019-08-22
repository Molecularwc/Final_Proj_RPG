//Base NPC class
public class NPC {
	String name;
	String id = "NPC";
	String desc;
	int hp;
	int accuracy;
	int dmg;
	int defense;
	int droppedGold;
	int droppedXP;
	int inRoom;

	public void look() {
		System.out.println(name);
		System.out.println("Hp: " + hp);
		System.out.println("Accuracy: " + accuracy);
		System.out.println("Damage: " + dmg);
		System.out.println("Defense: " + defense);
	}

}
