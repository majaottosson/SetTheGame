package SetLogic;

import java.util.ArrayList;
import java.util.List;


public class SetFinder {
	
	public ArrayList<Cards> SetFinders(List<Cards> cards) {
		ArrayList<Cards> copycards = new ArrayList<>(cards);
		
		return SetFinderhelper(copycards, copycards.remove(0), copycards.remove(0), copycards.remove(0));
	}
	
	public ArrayList<Cards> SetFinderhelper(ArrayList<Cards> cards, Cards one, Cards two, Cards three) {
		ArrayList<Cards> set = new ArrayList<>();
		
		Rules rules = new Rules();
		
		
		if (one == null || two == null || three == null) {
			return null;
			
		} else if (rules.AllRules(one, two, three)){
			set.add(one);
			set.add(two);
			set.add(three);
			return set;
			
		} else if (!cards.isEmpty()){
			ArrayList<Cards> result;
			
			ArrayList<Cards> copyone = new ArrayList<Cards>(cards);
			result = SetFinderhelper(copyone, one, two, copyone.remove(0));
		    if (result != null) return result;
		    
		    ArrayList<Cards> copytwo = new ArrayList<Cards>(cards);
		    result = SetFinderhelper(copytwo, one, copytwo.remove(0), three);
		    if (result != null) return result;

		    ArrayList<Cards> copythree = new ArrayList<Cards>(cards);
		    result = SetFinderhelper(copythree, copythree.remove(0), two, three);
		    if (result != null) return result;
			
		}
		return null;
		
	}
}
