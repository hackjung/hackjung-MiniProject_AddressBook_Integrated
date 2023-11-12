package MiniProject_AddressBook_Integrated;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Smartphonemain {
	
	public static void main(String[] args) {
		
		Smartphone sp = new Smartphone();
		Scanner sc = new Scanner(System.in);
				
		int num = 10;
		String name = "";
		
		/* 프로그램 시작 시 지금까지 입력 되었던 모든 파일을 ArrayList에 불러오기 */
		try {
			
			sp.ReadFile();
		} catch (NullPointerException e) {
			
		}
		
		while(true) {
			
			printmenu();
			
			try {
				
				System.out.print("원하는 메뉴를 선택 하세요"); num = sc.nextInt();
				
			} catch(InputMismatchException e) {
				
				System.out.println("번호만 입력 해야 됩니다.");
				sc.nextLine();
				continue;
			}
			
			
			switch(num){
				
				case 1:
					System.out.println("연락처를 등록(회사).");
					sp.create(sp.insertinto(num));					
					break;
					
				case 2:
					System.out.println("연락처를 등록(거래처).");
					sp.create(sp.insertinto(num));
					break;
				
				case 3:
					System.out.println("모든 연락처를 검색하겠습니다.\n"); 
					sp.search_all();
					break;
					
				case 4 :
					System.out.print("연락처 검색하기 위해 이름을 입력하세요 : "); name = sc.next();
					sp.search_cho(name);
					break;
					
				case 5:
					System.out.print("삭제할 연락처의 이름을 하세요 : "); name = sc.next();
					sp.delete(name);
					break;
					
				case 6:
					System.out.print("수정할 연락처를 확인하기 위해 이름을 입력하세요 : "); name = sc.next();
					sp.search_cho(name);
					System.out.println("연락처를 수정하겠습니다.");
					sp.edit(name);
					break;
				case 7:
					System.out.println("프로그램을 종료하겠습니다.");
					return;
				case 10 :
					break;
				default :
					System.out.println("잘못 선택 하셨습니다. 다시 선택 하세요");	
			}
		}
		
	}
	
	static void printmenu() {
		System.out.println("\n=====================");
		System.out.println(">>1. 연락처 등록(회사)\n>>2.연락처 등록(거래처)\n>>3. 모든 연락처 검색\n>>4. 연락처 검색\n>>5. 연락처 삭제\n>>6. 연락처 수정\n>>7. 프로그램 종료");
		
	}
}