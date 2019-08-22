
public class Equipment_Handler {

	public void statModAdd(String[] x) { // This adds the stats when an item is
											// equipped
		for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
			int item_Acc = Game_Objects.pc.item.get(i).accuracy;
			int item_Dmg = Game_Objects.pc.item.get(i).damage;
			int item_Def = Game_Objects.pc.item.get(i).defense;
			if (x[1].equalsIgnoreCase(Game_Objects.pc.item.get(i).id)
					&& Game_Objects.pc.item.get(i).isEquipped) {
				Game_Objects.pc.accuracy = Game_Objects.pc.accuracy + item_Acc;
				Game_Objects.pc.dmg = Game_Objects.pc.dmg + item_Dmg;
				Game_Objects.pc.defense = Game_Objects.pc.defense + item_Def;
			}
		}
	}

	public void statModSub(String[] x) { // this removes the stats when an item
											// is un-equipped
		for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
			int item_Acc = Game_Objects.pc.item.get(i).accuracy;
			int item_Dmg = Game_Objects.pc.item.get(i).damage;
			int item_Def = Game_Objects.pc.item.get(i).defense;
			if (x[1].equalsIgnoreCase(Game_Objects.pc.item.get(i).id)
					&& !Game_Objects.pc.item.get(i).isEquipped) {
				Game_Objects.pc.accuracy = Game_Objects.pc.accuracy - item_Acc;
				Game_Objects.pc.dmg = Game_Objects.pc.dmg - item_Dmg;
				Game_Objects.pc.defense = Game_Objects.pc.defense - item_Def;
			}
		}
	}

}
