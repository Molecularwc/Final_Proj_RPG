
import java.util.ArrayList;
import java.util.List;

public class PC {
	String name;
	int hp;
	int maxHP;
	int accuracy;
	int dmg;
	int defense;
	int gold;
	int xp;
	int currentXP;
	int maxExp;
	int xpToLvl;
	int Lvl;
	int inRoom = 0;
	private double goldMod = 0.85;
	private double xpMod = 0.75;
	List<Item> item = new ArrayList<Item>();
	List<Item> wornItems = new ArrayList<Item>();

	public void look() { // provides the ability to inspect yourself
		System.out.println("Name: " + name);
		System.out.println("Hp: " + hp);
		System.out.println("Max Hp: " + maxHP);
		System.out.println("Accuracy: " + accuracy);
		System.out.println("Damage: " + dmg);
		System.out.println("Defense: " + defense);
		System.out.println("Gold: " + gold);
		System.out.println("Exp: " + currentXP);
		System.out.println("Exp til Level: " + xpToLvl);
		System.out.println("Level: " + Lvl);
	}

	public void remove(String[] x) { // this is for un-equipping items
		if (wornItems.size() >= 0) {
			for (int i = 0; i < wornItems.size(); i++) {
				if (x[1].equalsIgnoreCase(wornItems.get(i).id)
						&& wornItems.get(i).isEquipped) {
					System.out.println("You removed a " + wornItems.get(i).id);
					item.add(wornItems.get(i));
					wornItems.get(i).isEquipped = false;
					wornItems.remove(i);
					Game_Objects.eh.statModSub(x);
				}
			}
		} else {
			System.out.println("You have no more equipped items.");
		}
	}

	public void equipped() { // this is to view which items are currently
								// equipped
		for (int i = 0; i < wornItems.size(); i++) {
			System.out.println(
					wornItems.get(i).name + ":" + wornItems.get(i).wearloc);
		}
	}

	public void checkInv() { // checks the inventory
		System.out.println("You are carrying: ");
		for (int i = 0; i < item.size(); i++) {
			System.out.println(item.get(i).name);
		}
	}

	public void wear(String[] x) { // this is for equipping items from the
									// inventory
		if (wornItems.size() == 0) {
			for (int i = 0; i < item.size(); i++) {
				if (x[1].equalsIgnoreCase(item.get(i).id)
						&& item.get(i).isWearable) {
					wornItems.add(item.get(i));
					System.out.println("You equipped a " + item.get(i).name);
					item.get(i).isEquipped = true;
					Game_Objects.eh.statModAdd(x);
					item.remove(i);
					break;
				}
			}
		} else {
			boolean isWearing = false;
			for (int i = 0; i < wornItems.size(); i++) {
				for (int z = 0; z < item.size(); z++) {
					if (x[1].equalsIgnoreCase(item.get(i).id)
							&& item.get(i).isWearable) {
						if (item.get(z).wearloc
								.equals(wornItems.get(i).wearloc)) {
							System.out.println(
									"You already have something equipped in that location");
							isWearing = true;
						}
					}
				}
				if (!isWearing) {
					if (x[1].equalsIgnoreCase(item.get(i).id)
							&& item.get(i).isWearable) {
						wornItems.add(item.get(i));
						System.out
								.println("You equipped a " + item.get(i).name);
						item.get(i).isEquipped = true;
						Game_Objects.eh.statModAdd(x);
						item.remove(i);
						break;

					}
				}
			}
		}
	}

	public void pcHeal() { // this is for the player to heal themselves
		if (hp > 0) {
			int hpDif = maxHP - hp;
			hp = maxHP;
			System.out.println(
					"You healed yourself for " + hpDif + " hitpoints!");
		} else {
			System.out.println("You cannot heal a corpse.");
		}
	}

	public void pcResurrect() { // the "oh shit" button that gets pushed when an
								// enemy npc hits you too hard
		hp = maxHP / 2;
		System.out.println(
				"The Pheonix Medallion glows brightly and lifts your corpse gently from the ground. Your eyes open, and you feel strength return to your body. The gods have restored you to life!");
	}

	public int updateGold(int dGold) { // updates player currency after a combat
										// encounter
		dGold = (int) (dGold + (dGold * goldMod));
		return dGold;
	}

	public int updateXP(int xGain) { // updates player experience points after a
										// combat encounter
		xGain = (int) (xGain + (xGain * xpMod));
		return xGain;
	}

	public void updateLvl() { // updates player level after a combat encounter
								// if enough exp has been gained
		if (Lvl <= 49) {
			currentXP = currentXP - maxExp;
			maxExp = (int) (maxExp + (maxExp * xpMod));
			Lvl++;
			maxHP += 50;
			hp = maxHP;
			accuracy += 5;
			dmg += 10;
			defense += 15;
			System.out.println("You earned a new level!");
		} else {
			System.out.println("You are already reached the max level.");
		}
	}

}