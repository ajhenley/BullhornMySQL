
public class User {
	String email;
	String userName;
	String gravatarURL;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGravatarURL(int size) {
		return util.MD5Util.GetGravatarURL(email, size);
	}

}
