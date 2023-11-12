package MiniProject_AddressBook_Integrated;

public class CustomerAddr extends Addr{

	private String AccountName;		// 거래처 이름.
	private String Item;			// 아이템
	private String Rank;			// 직급
	
	public CustomerAddr(String name, String no, String email, String address, String Birthday, String group, String AccountName, String Item, String Rank) {
		super(name, no, email, address, Birthday, group);
		
		this.AccountName = AccountName;
		this.Item = Item;
		this.Rank = Rank;
	}
	
	void setAccountName(String AccountName) {
		
		this.AccountName = AccountName;
	}
	
	String getAccountName() {
		
		return this.AccountName;
	}
	
	void setItem(String Item) {
		
		this.Item = Item;
	}
	
	String getItem() {
		
		return this.Item;
	}
	
	void setRank(String Rank) {
		
		this.Rank = Rank;
	}
	
	String getRank() {
		
		return this.Rank;
	}

}
