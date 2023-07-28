package in.ineuron.service;

import in.ineuron.dto.Person;

public interface IPersonService {

	    //registering a person
		String register(Person person);
		
		//for login 
		String login(Person person);
		
		//for deposit
		String deposit(Person person,Double updateAmount);
		
		//for withdraw
		String withdraw(Person person,Double updatedAmount);
		
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
