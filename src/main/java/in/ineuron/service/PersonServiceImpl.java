package in.ineuron.service;

import in.ineuron.dao.IPersonDao;
import in.ineuron.dto.Person;
import in.ineuron.factory.PersonDaoFactory;

public class PersonServiceImpl implements IPersonService {

	IPersonDao personDao;
	@Override
	public String register(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.register(person);
	}
	@Override
	public String login(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.login(person);
	}
	@Override
	public String deposit(Person person, Double updatedAmount) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.deposit(person, updatedAmount);
	}
	
	@Override
	public Person getPerson(Integer ano) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.getPerson(ano);
	}
	@Override
	public String withdraw(Person person, Double updatedAmount) {
		personDao=PersonDaoFactory.getPersonDao();
		
		return personDao.withdraw(person, updatedAmount);
	}
	@Override
	public String deleteByaNo(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.deleteByaNo(person);
	}
//	@Override
//	public Person findByaNo(Integer ano) {
//		personDao=PersonDaoFactory.getPersonDao();
//
//		return personDao.findByaNo(ano);
//	}
	@Override
	public String updateByNo(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.updateByNo(person);
	}
	@Override
	public Person findByaNo(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.findByaNo(person);
	}
	@Override
	public Person balance(Person person) {
		personDao=PersonDaoFactory.getPersonDao();
		return personDao.balance(person);
	}


	
}
