
public class Skeleton extends NPC {
	public Skeleton() {
		id = "Skeleton";
		hp = 75;
		accuracy = 40;
		dmg = 35;
		defense = 35;
		droppedGold = Game_Objects.rng.returnRandomGold(275);
		droppedXP = Game_Objects.rng.returnRandomXP(200, 300);
		desc = "A re-animated Skeleton is lumbering about.";
		name = "A re-animated Skeleton";
	}

}
