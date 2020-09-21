import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Person implements Comparable<Person> {
	//Comparable
	 @Override
    public int compareTo(Person person) {
        if(this.Salary < person.Salary) 
            return 1;
        else
            return -1;
    }
	
	//Constants
    String line ="";
    String path = "C:/Users/Alex/Desktop/Java_EPI-USE/Persons.txt"; 

     
	//Declarations
    private int empNumber;
    private String Name;
    private String Surname;
    private Date birthDate;
    private String Role;
    private int reportsTo;
    private double Salary;  
    private List<Person> Subordinates; 
    
	Map<Integer, Person> hashPersons = new HashMap<>(); 
	public Person superior;
    
    //Constructor
    public Person(
    		int EmpNumber,
    		String Name, 
    		String Surname,     		
    		Date BirthDate,  
    		String Role, 
    		int ReportsTo,    
    		double Salary) {
    	this.Name = Name; 
    	this.Surname = Surname;    		
    	this.birthDate = BirthDate;
    	this.empNumber = EmpNumber;
    	this.Salary = Salary;
    	this.Role = Role;
		this.reportsTo = ReportsTo;
      } 
    
    public Person() {    
      } 

    //Encapsulation GET
    public String getName() {
        return Name;
    }
    
    public String getSurname() {
        return Surname;
    }
    
    public Date getBirthDate() throws ParseException {    	
        return birthDate;
    }

    public int getEmployeeNumber() {
        return empNumber;
    }
    
    public double getSalary() {
        return Salary;
    }
    
    public String getRole() {
        return Role;
    }
    
    public int getReportsTo() {
    	
        return reportsTo;
    }
    
    //Encapsulation SET
    public void setName(String name) {
        this.Name = name;
    }
    
    public void setSurname(String surname) {
    	this.Surname = surname;
    }    
    
    public void setBirthDate(Date birthDate) {
    	this.birthDate = birthDate;
    }

    public void setEmployeeNumber(int empNumber) {
    	this.empNumber = empNumber;
    }

    public void setSalary(double salary) {
        this.Salary = salary;
    }

    public void setRole(String role) {
        this.Role = role;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }
    
    /*--------------------------Functions---------------------------------*/
    
    // return a list of all persons in the .txt file
    public List<Person> getPersons() throws ParseException, IOException {    	
        List<Person> persons = new ArrayList<Person>();   
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        try {            
            while((line = bufferedReader.readLine()) != null) 
            {     
                String[] values = line.split("\\|");
                Date date =new SimpleDateFormat("dd-MM-yyyy").parse(values[3]);
                Person person = new Person(Integer.parseInt(values[0]), values[1], values[2], date, values[4], Integer.parseInt(values[5]), Double.parseDouble(values[6]));
                persons.add(person);
            }   	
        }    
        finally {        	
        	bufferedReader.close();
        }
        return persons;
    }          

    
	// return a hashmaping of all persons in the .txt file and set root to person who does not report to anyone
    public Map<Integer, Person> getHashMap() throws ParseException, IOException {        
		Person Person = null;     
     	
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));        
        try {            
            while((line = bufferedReader.readLine()) != null) 
            {     
                String[] values = line.split("\\|");
                Date date =new SimpleDateFormat("dd-MM-yyyy").parse(values[3]);
                if (values.length >= 4)  						
    				Person = new Person(Integer.parseInt(values[0]), values[1], values[2], date, values[4], Integer.parseInt(values[5]), Double.parseDouble(values[6]));          
    			else 
    				Person = new Person(Integer.parseInt(values[0]), values[1], values[2], date, values[4], Integer.parseInt(values[5]), Double.parseDouble(values[6]));	
                hashPersons.put(Person.empNumber, Person);
    			if (Person.reportsTo == 0) 
				superior = Person;
            }	
        }    
        finally {        	
        	bufferedReader.close();
        }
        return hashPersons;
    }   
    
	//creates the organizational structure by getting subordinates per each persons (starting with superior)  
    public void createOrganizationalStructure(Person superior) {
		 Person objPerson = superior;
		 List<Person> subordinates = getSubordinatesBySuperior(objPerson.empNumber);
		 objPerson.Subordinates = subordinates;
		 if (subordinates.size() == 0)
			 return;
		 for (Person person : subordinates) 
			 createOrganizationalStructure(person);
	 }	 
	 
	//return persons who are subordinates to superior (id)
	private List<Person> getSubordinatesBySuperior(int superiorID) {
		 List<Person> subordinates = new ArrayList<Person>();
		 for (Person person : hashPersons.values()) {
			 if (person.reportsTo == superiorID) 
				 subordinates.add(person);
		 }
		 return subordinates;
	}
		 
	//print the organizational structure
	public void printOrganizationalStructure(Person superior, int level) {
		for (int i = 0; i < level; i++) 
			System.out.print("\t");	 //add new tab
			System.out.println(superior.Name + " " + superior.Surname  + " (" + superior.Role  + ")");		 
			List<Person> subordinates = superior.Subordinates;
			for (Person persons : subordinates) 
				printOrganizationalStructure(persons, level+1);
		}
	 
	
}