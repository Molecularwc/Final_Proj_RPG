import java.util.Random;

public class RNG {
	Random rand = new Random();
	public int returnRandom(int x) {
		int y = rand.nextInt(x);
		return y;
	}

	public int returnRandomXP(int x, int y) {
		int z = rand.nextInt(((y - x) + 1) + x);
		return z;
	}

	public int returnRandomGold(int x) {
		int y = rand.nextInt(x);
		return y;
	}

}
