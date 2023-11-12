package MiniProject_AddressBook_Integrated;

public class Addr {

	private String name;
	private String no;
	private String email;
	private String address;
	private String Birthday;
	private String group;
	
	public Addr(String name, String no, String email, String address, String Birthday, String group) {
		
		this.name = name;
		this.no = no;
		this.email = email;
		this.address = address;
		this.Birthday = Birthday;
		this.group = group;
	}
	
	public Addr(String PhoneNumber) {
		
		this.no = PhoneNumber;
	}
	
	void setname(String name) {
		
		this.name = name;
	}
	
	public String getname() {
		
		return this.name;
	}
	
	void setno(String no) {
		
		this.no = no;
	}
	
	public String getno() {
		
		return this.no;
	}
	
	public void setemail(String email) {
		
		this.email = email;
	}
	
	public String getemail() {
		
		return this.email;
	}
	
	public void setaddress(String address) {
		
		this.address = address;
	}
	
	public String getaddress() {
		
		return this.address;
	}
	
	
	void setBirthday(String Birthday) {
		
		this.Birthday = Birthday;
	}
	
	public String getBirthday() {
		
		return this.Birthday;
	}
	
	void setgroup(String group) {
		
		this.group = group;
	}
	
	public String getgorup() {
		
		return this.group;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Addr) {
			
			Addr Addr = (Addr) obj;
			return this.no.contentEquals(Addr.no);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {		// 오버라이딩을 하지 않았을 경우 기본적으로 객체의 주소 값을 가지고 해쉬값을 가짐.
		
		return no.hashCode();
	}
}
