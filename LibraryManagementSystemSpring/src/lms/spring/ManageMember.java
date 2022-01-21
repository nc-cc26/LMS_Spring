package lms.spring;

import java.util.HashMap;
import java.util.Map;

public class ManageMember {

	private Map<Member, String> members = new HashMap<Member, String>();
	
	public void addMember(Member member, String available) {
		members.put(member, available);
	}
	
	public Map<Member, String> getAllMembersQuantity() {
		return members;
	}
	
	public void removeMember(Member member) {
		members.remove(member);
}
}
