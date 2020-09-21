import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question1 {	
	
	public static void  main(String[] args) throws ParseException, IOException {	
		searchEmployee();
	}
	
	static void searchEmployee() throws ParseException, IOException {	  		
		Scanner scanner = new Scanner(System.in);  
		try {	
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy"); //used to format outputdate

			System.out.println("Enter employee search details:"); //input
			String search = scanner.nextLine(); 	 
			Person objPerson = new Person();
			List<Person> persons = objPerson.getPersons(); //get persons
			List<Person> personsFound = new ArrayList<Person>(); //lists of all persons found	
			for (Person person : persons) 
			{ 
				if(person.getName() != null && person.getName().equals(search)) //compare name entered to names in persons list
				{					
					personsFound.add(person); //add to persons found list
				}	
			} 
			if(personsFound.isEmpty()) {	//if no persons found return below
				System.out.println("------------------------------------");
				System.out.println("Employee not found. Please try again");
				System.out.println("------------------------------------");				
				searchEmployee();				
			}
			else{ //else persons found returned
				System.out.println("------------------------------------");
				System.out.println("Employee(s) found:");
				for (Person person : personsFound) //loop through and output all persons found
				{ 				
					System.out.println("------------------------------------");	
					System.out.println("(" + person.getEmployeeNumber() + ") " + person.getName() + " " + person.getSurname());
					System.out.println("Birth Date: " + simpleDateFormat.format(person.getBirthDate())); 	
				} 	
			}	
		}
		finally {
			scanner.close(); //close input leak
	    }
	}	
	
}


