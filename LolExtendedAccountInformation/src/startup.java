import java.util.ArrayList;

public class startup {
	static SummonerInfo summoner;
	public static void main(String[] args) {
		summoner = new SummonerInfo();
		
		summoner.key = "RGAPI-24919a09-d859-4886-a00e-adfb22b5be02";
		try {
			if (CheckKey.checkKey(summoner.key)) 
			{
				summoner.name = "DarkestVision";
				CheckSummoner.getSummonerBasics(summoner);
				
				summoner.champions = new ArrayList<>();
				CheckSummoner.getChampions(summoner);

				printSummary();
			}
		} catch (RequestException e) {
			e.printMessage();
		}
	}
	private static void printSummary() {
		System.out.println(summoner.champions);
		System.out.println(summoner.toString());
	}
}
