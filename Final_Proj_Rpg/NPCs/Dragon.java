
public class Dragon extends NPC {

	public Dragon() {
		id = "Dragon";
		hp = 100;
		accuracy = 50;
		dmg = 40;
		defense = 50;
		droppedGold = Game_Objects.rng.returnRandomGold(500);
		droppedXP = Game_Objects.rng.returnRandomXP(150, 375);
		desc = "A Dragon is sleeping, curled around its hoard.";
		name = "A Dragon";
	}

}
