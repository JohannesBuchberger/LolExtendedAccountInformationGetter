public interface UIStrings {
	interface ErrorMessages {
		interface keyChecks {			
			String Unauthorized = "No API-Key, please insert one";
			String Bad_Request = "Error 400, report it to the dev";
			String Forbidden = "Invalidkey - Maybe misspelling? If not get a new key (You find one on https://developer.riotgames.com/ after sign up on the dashboard)";
			String Not_Found = "There was no summoner with this ID";
			String Rate_Limit_Exceeded = "The key's rate limit exceeded, use a new one";
			String Server_Error = "A Server error (Riot's server), wait and hope";
		}
		interface summonerSpecs {
			String Not_Found = "This summoner does not exist";
		}
	}
	interface NonRandomGameModes {
		String[] Gamemodes = {"CLASSIC", "TUTORIAL"};
	}
}
