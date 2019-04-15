import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class CheckSummoner extends AbstractRequest {
	/**
	 * Basics are: Name, puuid, accountId, summonerLevel
	 */
	public static void getSummonerBasics(SummonerInfo summoner) throws RequestException {
		String newSummoner = getSummoner(summoner);
		if (newSummoner != "")
		{
			JSONObject obj = new JSONObject(newSummoner);
			summoner.accountId = obj.getString("accountId");
			summoner.puuid = obj.getString("puuid");
			summoner.summonerLevel = obj.getInt("summonerLevel");
		}
	}
	
	
	// Check if != 5v5, 3v3 & --!!-- Ranked (weil ja sonst random champs dabei sind)
	
	public static void getChampions(SummonerInfo summoner) throws RequestException {
		JSONObject matches = new JSONObject(getMatches(summoner));
		JSONArray objectArray = matches.getJSONArray("matches");
		ChampionIdToName championTools = new ChampionIdToName();
		ArrayList<Integer> championIds = new ArrayList<>();
		int currentChampId;
		for (int i = 0; i<objectArray.length(); i++)
		{
			currentChampId = objectArray.getJSONObject(i).getInt("champion");
			if (championIds.contains(currentChampId) == false)
			{
				summoner.champions.add(championTools.getChampion(currentChampId));				
				championIds.add(currentChampId);
			}
		}
	}
	
	private static String getMatches(SummonerInfo summoner) throws RequestException {
		return getRequest(RequestUrls.matchHistory + summoner.accountId, summoner.key);
	}

	private static String getSummoner(SummonerInfo summoner) throws RequestException {
		return getRequest(RequestUrls.summonerBasics, summoner);
	}
}
