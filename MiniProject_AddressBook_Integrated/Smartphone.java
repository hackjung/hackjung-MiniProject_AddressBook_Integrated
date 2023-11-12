package MiniProject_AddressBook_Integrated;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Smartphone {

	Scanner sc = new Scanner(System.in);
	
	List <Addr> AddrArry = new ArrayList<Addr>();
	List <String> CompanyAddr = new ArrayList<String>();
	List <String> CustomerAddr = new ArrayList<String>();
	
	/* 전화번호 등록 시 중복 값 검사 위한 Map 컬렉션. */
	Map<Addr, String> MapPhone = new HashMap<>();
	
	/* ArrayList에 입력된 데이터를 파일의 입/출력 위한 참조 변수들*/
	CompanyAddr ObjCom;			
	CustomerAddr ObjCus;
	
	Writer Writer;
	Reader Reader;
	
	
	/* 부팅 직후 후 파일에 저장되어 있던 정보를 모두 ArrayList에 담는 부분 */
	void ReadFile() throws NullPointerException{
		
		File ComDir = new File("C:/Temp/CompanyAddr/");
		File CusDir = new File("C:/Temp/CustomerAddr/");
		
		File [] ComFiles = ComDir.listFiles();
		File [] CusFiles = CusDir.listFiles();
		
		/* 파일 내 데이터를 찾아 낸 후 객체를 생성할 때 생성자 내 매개변수로 주기 위한 ArrayList */
		List<String> FileContents = new ArrayList<String>(); ;
		
		/* CompanyAddr 내용을 담고 있는 파일 목록을 전부 출력하고 내용을 ArrayList인 CompanyAddr에 담기 */
		for(File file : ComFiles) {
			
			try {
				
				/* FileContents.get(1) : 이름...등등 */
				FileContents = Files.readAllLines(file.toPath());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* 파일로부터 전달 받았던 데이터를 가지고 객체 생성 */
			create(insertinto(FileContents, 1));
			FileContents.clear();	// 다음 파일 데이터 받기 위해 깔끔히 데이터 리셋.
		}
		
		/* CustomerAddr 진행 */
		for(File file : CusFiles) {
			
			try {
				
				/* FileContents.get(1) : 이름...등등 */
				FileContents = Files.readAllLines(file.toPath());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* 파일로부터 전달 받았던 데이터를 가지고 객체 생성 */
			create(insertinto(FileContents, 2));
			
			FileContents.clear();	// 다음 파일 데이터 받기 위해 깔끔히 데이터 리셋.
		}
	}
	
	/* 기존 "Addr insertinto(int num)"을 바꾸기에는 너무나 대공사이기 때문에 그냥 메소드 오버로딩함. */
	Addr insertinto(List <String> Input, int num) {
		
		Addr Addr = null;
		
		if(num == 1) {
		
			return (Addr) new CompanyAddr(Input.get(0), Input.get(1), Input.get(2), Input.get(3), Input.get(4), Input.get(5), Input.get(6), Input.get(7), Input.get(8));
		
		} else {
			
			return (Addr) new CustomerAddr(Input.get(0), Input.get(1), Input.get(2), Input.get(3), Input.get(4), Input.get(5), Input.get(6), Input.get(7), Input.get(8));
		}
	}
	
	Addr insertinto(int num) {
				
		String name = InputName();
		String no = InputNumber();
		System.out.println("이메일을 입력하세요 : ");  String email = sc.nextLine();
		System.out.println("주소를 입력하세요 : ");   String address = sc.nextLine();
		System.out.println("생일를 입력하세요 : ");   String Birthday = sc.nextLine();
		System.out.println("그룹을 입력하세요 : ");   String group = sc.nextLine();
		
		if(num == 1) {
			
			insertCompany();
			return (Addr) new CompanyAddr(name, no, email, address, Birthday, group, CompanyAddr.get(0),CompanyAddr.get(1), CompanyAddr.get(2));
			
		} else {
			
			insertCustomer();
			return (Addr) new CustomerAddr(name, no, email, address, Birthday, group, CustomerAddr.get(0),CustomerAddr.get(1), CustomerAddr.get(2));
		}
	}
	
	String InputName() {
		
		String Name;
		Boolean bol1;
		Boolean bol2;
		
		while(true) {
			
			try {
				
				System.out.print("이름을 입력하세요 : "); Name = sc.nextLine();
				bol1 = BlankTest(Name);		// 공백 여부 검사
				bol2 = SpecialChar(Name);	// 이름 내 한글과 영문 외 다른 내용 검사.
				
				if(bol1 == true && bol2 == true) 
					return Name;
					
			} catch (BlankException e) {
				System.out.println(e.getMessage());
			} catch (SpecialChar e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	Boolean BlankTest(String Input) throws BlankException {
		
		char[] InputArry = Input.toCharArray();
		
		for(char t : InputArry) {
			
			if (t == ' ') 
				throw new BlankException("공백이 발생하였습니다.");
		}
		return true;
	}
	
	Boolean SpecialChar(String Input) throws SpecialChar {
		
		char[] InputArry = Input.toCharArray();
		String temp;
		
		for(char t : InputArry) {
			
			if(!(t >= 'A' && t <= 'Z') && !(t >= 'a' && t <= 'z')) {		// 해당 글자가 영어이거나
				temp = Character.toString(t);								// 해당 글자가 한글이거나
				if(temp.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {}
					else
						throw new SpecialChar("한글과 영문 외 다른 값이 입력되었습니다.");
			}
		}
		return true;
	}
	
	/* 전화번호 입력 시 정상 입력 여부 확인, 기준은 패턴 일치 이후 중복 값 입력 여부 확인. */
	String InputNumber() {
		
		Boolean Pattern = null, Duplication = null;
		
		while(true) {
			
			System.out.print("전화번호를 입력하세요 : "); String PhoneNumber = sc.nextLine();
			
			try {
				
				Pattern = PatternPhone(PhoneNumber);	// 패턴 및 공백 여부 검사
				Duplication = DuplicationPhone(PhoneNumber, new Addr(PhoneNumber));		// HashMap 통한 키 중복 여부 검사.
				
				if(Pattern == true && Duplication == true)
					return PhoneNumber;
				
			} catch (NumberException e) {
				
				System.out.println(e);
				sc.nextLine();
			}
		}
	}
	
	Boolean PatternPhone(String PhoneNumber) throws NumberException{
		
		String Pattern = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
		
		/* 입력한 값이 패턴이 아니거나 공백이 아니거나 둘중 하나라도 적용되면 사용자 예외 NumberException 실행. */
		if(!(PhoneNumber.matches(Pattern)) || PhoneNumber.equals(" "))
			throw new NumberException("휴대전화번호를 형식에 맞게 다시 입력하세요");
		else
			return true;
	}
	
	/* 매개변수로 입력받은 전화번호와  입력한 번호를 바탕으로 생성한 객체를 전달 받아 동일한 키 값이 아니라면 MapPhone 해쉬 맵 생성한다.*/
	Boolean DuplicationPhone(String PhoneNumber, Object Objects) {
		
		if(MapPhone.containsKey(Objects)) {
			
			System.out.println("입력하신 전화번호는 이미 입력되어 있는 번호이니 다시 입력 바랍니다.");
			return false;
		} else {
			
			MapPhone.put(new Addr(PhoneNumber), PhoneNumber);
			return true;
		}
	}
	
	void insertCompany() {
		
		System.out.print("회사이름을 입력하세요 : ");	CompanyAddr.add(sc.nextLine());
		System.out.print("부서이름을 입력하세요 : ");	CompanyAddr.add(sc.nextLine());
		System.out.print("직급을 입력하세요 : ");		CompanyAddr.add(sc.nextLine());
		
	}
	
	void insertCustomer() {
		
		System.out.print("거래처 이름을 입력하세요 : ");	CustomerAddr.add(sc.nextLine());
		System.out.print("품목 이름을 입력하세요 : ");	CustomerAddr.add(sc.nextLine());
		System.out.print("직급을 입력하세요 : ");		CustomerAddr.add(sc.nextLine());
		
	}
	
	void create(Addr objects) {		// 데이터 관리 편하게 하기 위해 CompanyAddr와 CustomerAddr 모두 Addr 참조변수에 저장.
		
		AddrArry.add(objects);
		CompanyAddr.clear(); CustomerAddr.clear();
		
		/* == 여기에 파일로 데이터 생성 실습 == */
		
		try {
			
			if(objects instanceof CompanyAddr) {
				
				ObjCom = (CompanyAddr)objects;
				
				try {
					
					Writer = new FileWriter("C:/Temp/CompanyAddr/" + ObjCom.getname() + ".txt");
				} catch(FileNotFoundException e) {
					
					Path directoryPath = Paths.get("C:/Temp/CompanyAddr/");
					Files.createDirectories(directoryPath);
					Writer = new FileWriter("C:/Temp/CompanyAddr/" + ObjCom.getname() + ".txt");
				}
				
				Writer.write(ObjCom.getname()+"\n");
				Writer.write(ObjCom.getno()+"\n");
				Writer.write(ObjCom.getemail()+"\n");
				Writer.write(ObjCom.getaddress()+"\n");
				Writer.write(ObjCom.getBirthday()+"\n");
				Writer.write(ObjCom.getgorup()+"\n");
				Writer.write(ObjCom.getCompanyName()+"\n");
				Writer.write(ObjCom.getDepartName()+"\n");
				Writer.write(ObjCom.getRank()+"\n");
				
				Writer.flush();
				Writer.close();
				
			} else {
				
				ObjCus = (CustomerAddr)objects;
				
				try {
					
					Writer = new FileWriter("C:/Temp/CustomerAddr/" + ObjCus.getname() + ".txt");
				} catch(FileNotFoundException e) {
					
					Path directoryPath = Paths.get("C:/Temp/CustomerAddr/");
					Files.createDirectories(directoryPath);
					Writer = new FileWriter("C:/Temp/CustomerAddr/" + ObjCus.getname() + ".txt");
				}
				
				
				
				Writer.write(ObjCus.getname()+"\n");
				Writer.write(ObjCus.getno()+"\n");
				Writer.write(ObjCus.getemail()+"\n");
				Writer.write(ObjCus.getaddress()+"\n");
				Writer.write(ObjCus.getBirthday()+"\n");
				Writer.write(ObjCus.getgorup()+"\n");
				Writer.write(ObjCus.getAccountName()+ "\n");
				Writer.write(ObjCus.getItem()+ "\n");
				Writer.write(ObjCus.getRank()+ "\n");
				
				Writer.flush();
				Writer.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* == 여기에 파일로 데이터 생성 실습 == */
		
		System.out.println("객체 " + AddrArry.size() + "개가 생성이 완료 되었습니다.");
	}
	
	void search_all() {
		
		for(int i = 0; i<AddrArry.size(); i++) {
			
			System.out.println("----------------\n");
			printinfo(AddrArry.get(i));
		}
	}
	
	void search_cho(String name) {
		
		System.out.println("================\n");
		
		for(int i = 0; i<AddrArry.size(); i++) {
			
			if(AddrArry.get(i).getname().equals(name)) {
				
				printinfo(AddrArry.get(i));
				System.out.println("검색이 완료되었습니다.");
				return;
			}
		} System.out.println("검색 결과가 없습니다.");
	}
	
	void delete(String name) {
		
		for(int i = 0; i < AddrArry.size(); i++) {
			
			if(AddrArry.get(i).getname().equals(name)) {
				
				AddrArry.remove(i);
				System.out.println("연락처를 삭제하였습니다.");
				return;
			}
		}
		System.out.println("찾으시는 이름이 없습니다.");
	}
	
	void edit(String name) {
		
		for(int i=0; i<AddrArry.size(); i++) {
			
			if(AddrArry.get(i).getname().equals(name)) {
				
				AddrArry.add(i, insertinto(0));
				AddrArry.remove(i+1);
				
				System.out.println("수정이 완료되었습니다.");
				return;
			}
		}
	}
	
	
	void printinfo(Addr object) {
		
		System.out.println("이름 : " + object.getname());
		System.out.println("번호 : " + object.getno());
		System.out.println("이메일 : " + object.getemail());
		System.out.println("주소 : " + object.getaddress());
		System.out.println("생일 : " + object.getBirthday());
		System.out.println("그룹 : " + object.getgorup());
		
		if(object instanceof CompanyAddr) {
			
			CompanyAddrInfo((CompanyAddr) object);
		} else {
			
			CustomerAddrInfo((CustomerAddr) object);
		}
		
		System.out.println();
	}
	
	void CompanyAddrInfo(CompanyAddr object) {	
		
		System.out.println("회사 이름 : " + object.getCompanyName());
		System.out.println("부서 이름 : " + object.getDepartName());
		System.out.println("직급 명 : " + object.getRank());
	}
	
	void CustomerAddrInfo(CustomerAddr object) {
		
		System.out.println("거래처 이름 : " + object.getAccountName());
		System.out.println("거래품목 이름 : " + object.getItem());
		System.out.println("직급 명 : " + object.getRank());
	}
}
/*  */