
public class RequestException extends Exception {
	private static final long serialVersionUID = 1L;
	
	String message = "A error occurred, on specification avaliable, report it to the dev";
	public RequestException(int errorCode) {
		setMessage(errorCode);
	}
	
	public RequestException(String customErrorMessage) {
		message = customErrorMessage;
	}
	
	public void setMessage(int errorCode) {
		switch(errorCode) {
			case 400:
				message = UIStrings.ErrorMessages.keyChecks.Bad_Request;
				break;
			case 401:
				message = UIStrings.ErrorMessages.keyChecks.Unauthorized;
				break;
			case 403:
				message = UIStrings.ErrorMessages.keyChecks.Forbidden;
				break;
			case 404:
				message = UIStrings.ErrorMessages.keyChecks.Not_Found;
				break;
			case 429:
				message = UIStrings.ErrorMessages.keyChecks.Rate_Limit_Exceeded;
				break;
			default:
				message = UIStrings.ErrorMessages.keyChecks.Server_Error;
		}
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	// convenience method
	public void printMessage() {
		System.out.println(getMessage());
	}
}
