
public class Khajil extends NPC {

	public Khajil() {
		id = "Khajil";
		hp = 1000;
		accuracy = 150;
		dmg = 120;
		defense = 150;
		droppedGold = Game_Objects.rng.returnRandomGold(1500);
		droppedXP = Game_Objects.rng.returnRandomXP(1800, 2500);
		desc = "Khajil laughs menacingly. Gesturing for you to attack.";
		name = "Khajil the Dark One";
	}

}
