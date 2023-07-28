package in.ineuron.dao;

import in.ineuron.dto.Person;

public interface IPersonDao {


	//registering a record
	String register(Person person);
	
	//for login 
	String login(Person person);
	
	//for deposit
	String deposit(Person person, Double updatedAmount);
	
	//for withdraw
	String withdraw(Person person, Double updatedAmount);
	
	//for account deletion
	String deleteByaNo(Person person);
	
	//for updation
	String updateByNo(Person person);
	
	//For AccountDetails
	Person findByaNo(Person person);
	
	//for balance
	Person balance(Person person);
			
	
	Person getPerson(Integer ano);
}
