import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager extends Person {

	public Manager(String Name, String Surname, Date BirthDate, int employeeNumber, double salary, String role,
			int reportsTo) {
		super(employeeNumber, Name, Surname, BirthDate, role, reportsTo, salary);
	}

	public Manager() {
	}
	
	//override the parent function getPersons (Polymorphism)
	 public List<Person> getPersons() throws ParseException, IOException {
		   
	        List<Person> persons = new ArrayList<Person>();       
	        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
	        try {            
	            while((line = bufferedReader.readLine()) != null) 
	            {
	                String[] values = line.split("\\|");
	                Date date =new SimpleDateFormat("dd-MM-yyyy").parse(values[3]);
	                Person person = new Person(Integer.parseInt(values[0]), values[1], values[2], date, values[4], Integer.parseInt(values[5]), Double.parseDouble(values[6]));          
	                if (person.getRole().equals("Manager")) {              
	                	 persons.add(person);
	                }    
	            }
	        }    
	        finally {        	
	        	bufferedReader.close();
	        }
	        return persons;
	    }

}