import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class Question4 {

    public static void main(String[] args) throws ParseException, IOException {
    	getHighestSalary();
    }

    static void getHighestSalary() throws ParseException, IOException { 
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy"); //used to format date
	    
	    
		Person objEmployee = new Employee();
		List<Person> employees = objEmployee.getPersons(); //(polymorphism) get persons type employees into list
		Person objManager = new Manager();
		List<Person> managers = objManager.getPersons(); //(polymorphism) get persons type managers into list
		Person objTrainee = new Trainee();
		List<Person> trainees = objTrainee.getPersons(); //(polymorphism) get persons type trainees	into list 

		
		if (!managers.isEmpty()) {
			 Collections.sort(managers); //uses the comparable to sort list of employees - highest salary
		        System.out.println("------------------------------------");	
				System.out.println("(" + managers.get(0).getEmployeeNumber() + ") " + managers.get(0).getName() + " " + managers.get(0).getSurname());
				System.out.println("   -Birth Date: " + simpleDateFormat.format(managers.get(0).getBirthDate())); 		
				System.out.println("   -Salary (highest): " + managers.get(0).getSalary()); 	
		  		System.out.println("   -Role: " + managers.get(0).getRole()); 	
		}
		if (!employees.isEmpty()) {
			 Collections.sort(employees); //uses the comparable to sort list of managers - highest salary
	  	     System.out.println("------------------------------------");	
	  		System.out.println("(" + employees.get(0).getEmployeeNumber() + ") " + employees.get(0).getName() + " " + employees.get(0).getSurname());
	  		System.out.println("   -Birth Date: " + simpleDateFormat.format(employees.get(0).getBirthDate())); 		
	  		System.out.println("   -Salary (highest): " + employees.get(0).getSalary()); 	
	    		System.out.println("   -Role: " + employees.get(0).getRole()); 	
		}
		if (!trainees.isEmpty()) {
		       Collections.sort(trainees); //uses the comparable to sort list of trainees - highest salary
		        System.out.println("------------------------------------");	
		   		System.out.println("(" + trainees.get(0).getEmployeeNumber() + ") " + trainees.get(0).getName() + " " + trainees.get(0).getSurname());
		   		System.out.println("   -Birth Date: " + simpleDateFormat.format(trainees.get(0).getBirthDate())); 		
		   		System.out.println("   -Salary (highest): " + trainees.get(0).getSalary()); 	
		 		System.out.println("   -Role: " + trainees.get(0).getRole()); 
		}   
    }
}