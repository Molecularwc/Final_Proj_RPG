
public class Goblin extends NPC {
	public Goblin() {
		id = "Goblin";
		hp = 30;
		accuracy = 30;
		dmg = 15;
		defense = 25;
		droppedGold = Game_Objects.rng.returnRandomGold(375);
		droppedXP = Game_Objects.rng.returnRandomXP(165, 270);
		desc = "A Goblin roams the area.";
		name = "A Goblin";
	}

}
