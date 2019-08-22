
public class Troll extends NPC {

	public Troll() {
		id = "Troll";
		hp = 15;
		accuracy = 20;
		dmg = 10;
		defense = 5;
		droppedGold = Game_Objects.rng.returnRandomGold(200);
		droppedXP = Game_Objects.rng.returnRandomXP(75, 180);
		desc = "A Troll wanders around, drooling.";
		name = "A Troll";
	}

}
