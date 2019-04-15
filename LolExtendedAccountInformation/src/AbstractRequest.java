import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AbstractRequest {
	
	protected static String getRequest(String path, SummonerInfo summoner) throws RequestException {
		try {
			HttpsURLConnection con = makeRequest(path + summoner.name, summoner.key, "GET");
			if (con.getResponseCode() != 200)
			{
				throw new RequestException(con.getResponseCode());
			}
			return getMessage(con);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "";
	}

	protected static String getRequest(String path, String key) throws RequestException {
		try {
			HttpsURLConnection con = makeRequest(path, key, "GET");
			if (con.getResponseCode() != 200)
			{
				throw new RequestException(con.getResponseCode());
			}
			return getMessage(con);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "";
	}
	
	protected static int getResponseCodeRequest(String requestPath, String key, String requestMethod) throws IOException
	{
		return makeRequest(requestPath, key, requestMethod).getResponseCode();
	}
	
	protected static HttpsURLConnection makeRequest(String requestPath, String key, String requestMethod) throws IOException {
		URL url = new URL(requestPath + RequestUrls.suffix + key);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod(requestMethod);
		return con;
	}
	
	protected static String getMessage(HttpsURLConnection con) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
