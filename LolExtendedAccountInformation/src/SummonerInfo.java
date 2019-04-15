import java.util.ArrayList;

public class SummonerInfo {
	// Required to start
	public String key;
	public String name;
	// basics
	public String accountId;
	public String puuid;
	public int summonerLevel;
	// champions
	public ArrayList<String> champions;
	
	@Override
	public String toString() {
		// @formatter: off
		return String.format(""
				+ "key: %s, "
				+ "name: %s, "
				+ "accountId: %s, "
				+ "puuid: %s, "
				+ "summonderLeve: %s", 
				key, 
				name, 
				accountId, 
				puuid, 
				summonerLevel);	
		// @formatter: on
	}
}
