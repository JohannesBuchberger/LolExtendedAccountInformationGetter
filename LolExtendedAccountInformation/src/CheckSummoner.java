import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
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
		JSONArray objectArray = getNonRandomPickMatches(matches.getJSONArray("matches"), summoner);
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
	
	private static JSONArray getNonRandomPickMatches(JSONArray matches, SummonerInfo summoner) throws JSONException, RequestException {
		System.out.println("All Matches " + matches + " thats it with the matches");
		JSONArray nonRandomPickMatches = new JSONArray();
		JSONObject currentMatch;
		for (int i = 0; i < matches.length(); i++) {
			System.out.println("Whole match before cast " + matches.get(i));
			currentMatch = (JSONObject) matches.get(i);
			if (isMatchWithoutRandompick(currentMatch.getInt("gameId"), summoner))
			{
				nonRandomPickMatches.put(matches.get(i));
			}
		}
		return nonRandomPickMatches;
	}


	private static boolean isMatchWithoutRandompick(int matchID, SummonerInfo summoner) throws RequestException {
	    System.out.println("Matchid before getMatchcall " + matchID);
		JSONObject match = new JSONObject(getMatch(matchID, summoner));
	    System.out.println(match);
	    boolean isRandompick = true;
	    for (String nonRandompickmatch : UIStrings.NonRandomGameModes.Gamemodes) {
	    	System.out.println("Non randompick: " + nonRandompickmatch);
	    	if (match.get("gameMode") == nonRandompickmatch)
	    	{
	    		isRandompick = false;
	    	}
	    }
		return isRandompick;
	}
	
	private static String getMatch(int matchID, SummonerInfo summoner) throws RequestException {
		String url = RequestUrls.matchSingle + matchID;
		System.out.println(url);
		return getRequest(url, summoner.key);
	}


	private static String getMatches(SummonerInfo summoner) throws RequestException {
		return getRequest(RequestUrls.matchHistory + summoner.accountId, summoner.key);
	}

	private static String getSummoner(SummonerInfo summoner) throws RequestException {
		return getRequest(RequestUrls.summonerBasics, summoner);
	}
}
