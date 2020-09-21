import java.io.IOException;
import java.text.ParseException;

public class Question3 {
	
	public static void main(String[] args) throws ParseException, IOException {
		getOrganizationalStructure();
    }
	
	static void getOrganizationalStructure() throws ParseException, IOException { 
		 Person objPerson = new Person();
		 objPerson.getHashMap(); //get persons in hashmap & find root person
		 objPerson.createOrganizationalStructure(objPerson.superior); //send as param the root person with no one to report to
		 objPerson.printOrganizationalStructure(objPerson.superior, 0); //send as param the root person with no one to report to and initialize level to 0 (top of structure)
	}	
}

