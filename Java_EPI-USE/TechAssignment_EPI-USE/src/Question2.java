import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Question2 {

	public static void main(String[] args) throws Exception {
		getEmployeeBeforeDate();
	}
	
	
	static void getEmployeeBeforeDate()throws Exception {	
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter date:"); 
			String objDate = scanner.nextLine(); 	
			Person objPerson = new Person();			
			List<Person> persons = objPerson.getPersons(); //get persons			
			boolean noPersonFound = true;	
			
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy"); //define date format
		    simpleDateFormat.setLenient(false); //allows for throw exception if not in correct format

		    try
		    {
		    	Date dateExist = simpleDateFormat.parse(objDate); 
				System.out.println("------------------------------------");
				System.out.println("Results:");					  
		        
		        for (Person person : persons) 
				{ 
		            Date dateNew = person.getBirthDate();
		        	if(dateNew.before(dateExist)) 
					{
		        		System.out.println("------------------------------------");	
		        		System.out.println("(" + person.getEmployeeNumber() + ") " + person.getName() + " " + person.getSurname());
		        		System.out.println("   -Birth Date: " + simpleDateFormat.format(person.getBirthDate())); 		
		        		System.out.println("   -Salary: R" + person.getSalary()); 	
		          		System.out.println("   -Role: " + person.getRole()); 
		          		noPersonFound = false; //persons was born before date entered
					}
				} 
		        if(noPersonFound) {
		        	System.out.println("------------------------------------");	
					System.out.println("No employees were born before the entered date.");						
				}			     
		    }
		    catch (ParseException e)
		    {
		    	System.out.println("------------------------------------");	
		        System.out.println("Date formate invalid. Ensure format is dd-MM-yyyy");
		        getEmployeeBeforeDate(); //recall function
		    }
			
		}
		finally {
			scanner.close(); //close input leak
		}					
	}
	
}