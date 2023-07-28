package in.ineuron.factory;

import in.ineuron.service.IPersonService;
import in.ineuron.service.PersonServiceImpl;

public class PersonServiceFactory {

	private static IPersonService personServiceImpl;
	
	public PersonServiceFactory()
	{
		
	}
	public static IPersonService getPersonService()
	{
		if(personServiceImpl==null)
		
		personServiceImpl=new PersonServiceImpl();
		return personServiceImpl;
	}

}
