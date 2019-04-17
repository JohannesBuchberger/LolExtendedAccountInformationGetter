
public interface RequestUrls {
	String suffix = "?api_key=";
	String prefix = "https://euw1.api.riotgames.com/lol/";
	String checkKey = prefix + "summoner/v4/summoners/by-name/RiotSchmick";
	String summonerBasics = prefix + "summoner/v4/summoners/by-name/";
	String matchHistory = prefix + "match/v4/matchlists/by-account/";
	String matchSingle = prefix + "match/v4/matches/";
}
