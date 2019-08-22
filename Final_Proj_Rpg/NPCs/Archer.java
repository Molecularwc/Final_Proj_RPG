
public class Archer extends NPC {

	public Archer() {
		id = "Archer";
		hp = 75;
		accuracy = 40;
		dmg = 35;
		defense = 35;
		droppedGold = Game_Objects.rng.returnRandomGold(275);
		droppedXP = Game_Objects.rng.returnRandomXP(200, 300);
		desc = "A Skeletal Archer is looming near-by.";
		name = "A Skeletal Archer";
	}

}
