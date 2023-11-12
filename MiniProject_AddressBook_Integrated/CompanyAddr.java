package MiniProject_AddressBook_Integrated;

public class CompanyAddr extends Addr {

	private String CompanyName;		// 회사 이름
	private String DepartName;		// 부서 이름
	private String Rank;			// 직급
	
	public CompanyAddr(String name, String no, String email, String address, String Birthday, String group, String CompanyName, String DepartName, String Rank) {
		super(name, no, email, address, Birthday, group);
		
		this.CompanyName = CompanyName;
		this.DepartName = DepartName;
		this.Rank = Rank;
	}

	void setCompanyName(String CompanyName) {
		
		this.CompanyName = CompanyName;
	}
	
	public String getCompanyName() {
		
		return this.CompanyName;
	}
	
	void setDepartname(String DepartName) {
		
		this.DepartName = DepartName;
	}
	
	String getDepartName() {
		
		return this.DepartName;
	}
	
	void setRank(String Rank) {
		
		this.Rank = Rank;
	}
	
	String getRank() {
		
		return this.Rank;
	}
		
	
}
