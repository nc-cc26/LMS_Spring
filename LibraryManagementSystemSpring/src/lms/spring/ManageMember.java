package lms.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManageMember {

	private Map<Member, String> members = new HashMap<Member, String>();
	Scanner scanner = new Scanner(System.in);
	Member member;
	
	public void addMember(Member member, String available) {
		members.put(member, available);
	}
	
	public Map<Member, String> getAllMembersQuantity() {
		return members;
	}
	
	public void removeMember(Member member) {
		members.remove(member);
	}
	
	// TO DISPLAY ALL THE MEMBERS
		public void displayAllMembers() {
			Map <Member, String> members = getAllMembersQuantity();
			
			if(!members.isEmpty()) {
				System.out.println("\n------------------");
				System.out.println(" ALL MEMBERS");
				System.out.println("-------------------\n");
				System.out
					.println("-------------------------------------------------");
				System.out.println("No.\t\tNAME");
				System.out
					.println("-----------------------------------------------\n");
				int memberIndex = 0;
				for (Member member : members.keySet()) {
					memberIndex++;
					System.out.printf("%1$d.\t\t%2$S\n",
						memberIndex, member.getName());
				}
			} else {
				System.out.println("NO MEMBER IN DATABASE!");
			}
		}
		
		// TO GET ALL THE MEMBERS AND STORE IN AN ARRAY
		public Member[] getMemberList() {
			Map<Member, String> members = getAllMembersQuantity();
			int memberIndex = 0;
			Member[] membersArr = new Member[members.size()];
			for (Member member : members.keySet()) {
				membersArr[memberIndex] = member;
				memberIndex++;
			}
			return membersArr;
		}
		
		// ADD MEMBER
		public void addMemberToLMS() {
			member = new Member();
			System.out.println("\nInsert member name:");
			String name = scanner.nextLine();
			
			member.setName(name);

			addMember(member, "available");
			System.out.println("Member is added successfully.\n");
			System.out.println();
		}
		
		// SELECT WHICH MEMBER TO EDIT
		public Member selectEditMember() {
			displayAllMembers();
			Member[] members = getMemberList();
		
			System.out.println("\nPlease select member no. to edit:");

			int selectedIndex = Integer.parseInt(scanner.nextLine());
		
			if (selectedIndex > 0 && selectedIndex <= members.length) {
				Member member = members[selectedIndex - 1];
				return member;
			}
			return null;
		}
		
		// EDIT MEMBER
		public void editMemberFromLMS(Member member) {
			System.out.println("\nUpdate new name: ");
			String newName = scanner.nextLine();
			member.setName(newName);
			
			System.out.println("Member is edited successfully.\n");
		}
		
		// SELECT WHICH MEMBER TO REMOVE
		public Member selectRemoveMember() {
			displayAllMembers();
			Member[] members = getMemberList();
			
			System.out.println("\nPlease select member no. to remove:");

			int selectedIndex = Integer.parseInt(scanner.nextLine());
			
			if (selectedIndex > 0 && selectedIndex <= members.length) {
				Member member = members[selectedIndex - 1];
				return member;
			}
			return null;
		}
		
		// REMOVE MEMBER
		public void removeMemberFromLMS(Member member) {
			removeMember(member);
			System.out.println("Member is removed successfully.\n");
			System.out.println();
		}
		
		public Member getBorrower() {
			displayAllMembers();
			Member[] members = getMemberList();
			System.out.println("\nSelect member No. of borrower:");
			int selectedIndex = Integer.parseInt(scanner.nextLine());
			
			if (selectedIndex > 0 && selectedIndex <= members.length) {
				Member member = members[selectedIndex - 1];
				return member;
			}
			return null;
		}
}
