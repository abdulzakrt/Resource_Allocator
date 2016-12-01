import java.io.Serializable;
public class Member implements Serializable {
	private int Log_in_ID;
	private int Password;
	public Member() {
                //Hello
		
	}
	public int getLog_in_ID() {
		return Log_in_ID;
	}
	public void setLog_in_ID(int log_in_ID) {
		Log_in_ID = log_in_ID;
	}
	public int getPassword() {
		return Password;
	}
	public void setPassword(int password) {
		Password = password;
	}
	
}
