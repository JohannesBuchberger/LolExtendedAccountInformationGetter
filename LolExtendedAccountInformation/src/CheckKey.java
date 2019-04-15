import java.io.IOException;

public class CheckKey extends AbstractRequest {
	public static boolean checkKey(String key) throws RequestException {
		String developmentKey = key;
		int response = 404;
		try {
			response = getResponsecode(developmentKey);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (response != 200)
		{
			throw new RequestException(response);
		}
		return response == 200?true:false;
	}

	private static int getResponsecode(String developmentKey) throws IOException {
		return getResponseCodeRequest(RequestUrls.checkKey, developmentKey, "GET");
	}
}
