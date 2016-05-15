
public class User {
	private String email;
	private String password;
	private String motto;
	private String gravatarURL;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getGravatarURL() {
		return gravatarURL;
	}
	public void setGravatarURL(String gravatarURL) {
		this.gravatarURL = gravatarURL;
	}
	public boolean isValidUser(){
		//look for user/email combination in db and return t/f
		return true;
	}
	public String gravatarURL(int imgSize){
		
		return util.MD5Util.GetGravatarURL(this.email, imgSize);
		
	}

}
