package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Person;
import in.ineuron.util.JdbcUtil;

public class PersonDaoImpl implements IPersonDao {

	Connection connection=null;
	@Override
	public String register(Person person) {
		String SqlInsertQuery="insert into Persons(`pname`,`mname`,`fname`,`page`,`mno`,`atype`,`paddr`,`pwd`) values(?,?,?,?,?,?,?,?)";
		PreparedStatement psmt=null;
		String status=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			psmt=connection.prepareStatement(SqlInsertQuery);
			if(psmt!=null)	
			{
				psmt.setString(1, person.getPname());
				psmt.setString(2, person.getMname());
				psmt.setString(3, person.getFname());
				psmt.setInt(4, person.getPage());
				psmt.setString(5, person.getMno());
				psmt.setString(6, person.getAtype());
				psmt.setString(7, person.getPaddr());
				psmt.setString(8, person.getPwd());
			}	
			if(psmt!=null)
			{
				int rowAffected=psmt.executeUpdate();
				if(rowAffected==1)
				{
					status="success";
				}
				else
				{
					status="failure";
				}
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			status="failure";
		}
		catch(IOException e)
		{
			e.printStackTrace();
			status="failure";
		}
		return status;
	}


	
//	@Override
//	public String login(Person person) {
//		
//		//String status=null;
//		Integer anouser=person.getaNo();
//		System.out.println("DAO11"+anouser);
//		String pnameuser=person.getPname();
//		System.out.println("DAO11"+pnameuser);
//		String pwduser=person.getPwd();
//		System.out.println("DAO11"+pwduser);
//		
//		String sqlSelectQuery="select ano,pname,pwd from persons where ano=?";
//		PreparedStatement psmt=null;
//		
//		try
//		{
//			connection=JdbcUtil.getJdbcConnection();
//			if(connection!=null)
//			{
//				psmt=connection.prepareStatement(sqlSelectQuery);
//				if(psmt!=null)
//				{
//					psmt.setInt(1, anouser);
//				}
//				if(psmt!=null)
//				{
//					ResultSet resultSet=psmt.executeQuery();
//					if(resultSet.next())
//					{
//						String pname=resultSet.getString(2);
//						System.out.println("DAO"+pname);
//						String pwd=resultSet.getString(3);
//						System.out.println("DAO"+pwd);
//						
////						if(pnameuser.equals(pname) && pwduser.equals(pwd))
////						{
////							status="success";
////						}
////						else
////						{
////							status="failure";
////						}
//					}
//				}
//			}
//		}
//		
//			catch(SQLException se)
//			{
//				se.printStackTrace();
//			}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//			
//		return "";
//	}

	@Override
	public String deposit(Person person, Double updatedAmount) {
		String status=null;
		Integer aNo=person.getaNo();
		System.out.println("DAO11--"+aNo);
		String sqlUpdateQuery="update persons SET balance=? where ano=?";
		
		PreparedStatement psmt=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
				psmt=connection.prepareStatement(sqlUpdateQuery);
				if(psmt!=null)
				{
					psmt.setDouble(1, updatedAmount);
					psmt.setInt(2, person.getaNo());
				}
				if(psmt!=null)
				{
					int rowAffected=psmt.executeUpdate();
					if(rowAffected==1)
					{
						status="success";
					}
					else
					{
						status="failure";
					}
				}
		}
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
			status="failure";
		}
		
		return status;
	}



	


	@Override
	public String withdraw(Person person, Double updatedAmount) {
		
		String status=null;
		Integer aNo=person.getaNo();
		System.out.println("DAO11--"+aNo);
		String sqlUpdateQuery="update persons SET balance=? where ano=?";
		
		PreparedStatement psmt=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				psmt.setDouble(1, updatedAmount);
				psmt.setInt(2, person.getaNo());
			}
			if(psmt!=null)
			{
				int rowAffected=psmt.executeUpdate();
				if(rowAffected==1)
				{
					status="success";
				}
				else
				{
					status="failure";
				}
			}
		}
	catch(SQLException | IOException e)
	{
		e.printStackTrace();
		status="failure";
	}
	return status;
				
			}



	@Override
	public String deleteByaNo(Person person) {
		String status=null;
	    Integer ano=person.getaNo();
		System.out.println("DAO11--"+ano);
		
		String sqlDeleteQuery="delete from persons where ano=?";
		PreparedStatement psmt=null;
		try
		{
//			person=getPerson(ano);
			if(person!=null)
			{
				connection=JdbcUtil.getJdbcConnection();
				if(connection!=null)
					psmt=connection.prepareStatement(sqlDeleteQuery);
				if(psmt!=null)
				psmt.setInt(1, ano);
				if(psmt!=null)
				{
					int rowAffected=psmt.executeUpdate();
					if(rowAffected==1)
						status="success";
				}
			}
//			else
//			{
//				status="not available";
//			}
				
	}
	catch(NullPointerException ne)
		{
		ne.printStackTrace();
		status="failure";
		}
	catch(SQLException | IOException e)
	{
		e.printStackTrace();
		status="failure";
		
	}
	return status;
	
	}

	@Override
	public Person getPerson(Integer ano) {
		Person person=null;
		String sqlSelectQuery="Select * from persons where ano=?";
		
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			psmt=connection.prepareStatement(sqlSelectQuery);
			if(psmt!=null)
			{
				psmt.setInt(1, ano);
				resultSet=psmt.executeQuery();
				System.out.println("Ano is: "+ano);
			}	
			if(resultSet!=null)
			{
				if(resultSet.next())
				{
					person=new Person();
					System.out.println("Coping resultset data to person object");
					System.out.println(resultSet.getString(2));
					
					person.setaNo(resultSet.getInt(1));
					person.setPname(resultSet.getString(2));
					person.setFname(resultSet.getString(3));
					person.setMname(resultSet.getString(4));
					person.setPage(resultSet.getInt(5));
					//person.setMno(Double.valueOf(resultSet.getDouble(6)));
					person.setMno(resultSet.getString(6));
		    	    person.setAtype(resultSet.getString(7));
				    person.setPaddr(resultSet.getString(8));
					person.setPwd(resultSet.getString(9));
					//System.out.println();
					person.setAmount(resultSet.getDouble(10));
					
					System.out.println("Person ready to sent..");
					
					return person;
				}
			}
			else
			{
				System.out.println("ResultSet is null");
			}
		}
			catch(SQLException |IOException e)
			{
				e.printStackTrace();
			}
		
		
		return person;
		
	}


//	@Override
//	public Person findByaNo(Integer ano) {
//		
//		return null;
//	}



	@Override
	public String login(Person person) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String updateByNo(Person person) {
		String sqlUpdateQuery="update persons set pname=?,mname=?,fname=?,page=?,mno=?,atype=?,paddr=?,pwd=? where ano=?";
		PreparedStatement psmt=null;
		String status=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			{
				psmt=connection.prepareStatement(sqlUpdateQuery);
				if(psmt!=null)
				{
					//person.setaNo(resultSet.getInt(1));
					psmt.setString(1, person.getPname());
					psmt.setString(2, person.getMname());
					psmt.setString(3, person.getFname());
					psmt.setInt(4, person.getPage());
					psmt.setString(5, person.getMno());
					psmt.setString(6, person.getAtype());
					psmt.setString(7, person.getPaddr());
					psmt.setString(8, person.getPwd());
					psmt.setInt(9, person.getaNo());
					
				}
				if(psmt!=null)
				{
					int rowAffected=psmt.executeUpdate();
					if(rowAffected==1)
					{
						status="success";
					}
					else
					{
						status="failure";					}
				}
			}
		}
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
			status="failure";
		}
		return status;
	}



	@Override
	public Person findByaNo(Person person) {
		// person=null;
		  Integer ano=person.getaNo();
		String sqlSelectQuery="Select * from persons where ano=?";
		
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			psmt=connection.prepareStatement(sqlSelectQuery);
			if(psmt!=null)
			{
				psmt.setInt(1, ano);
				resultSet=psmt.executeQuery();
				System.out.println("Ano is: "+ano);
			}	
			if(resultSet!=null)
			{
				if(resultSet.next())
				{
					person=new Person();
					System.out.println("Coping resultset data to person object");
					System.out.println(resultSet.getString(2));
					
					person.setaNo(resultSet.getInt(1));
					person.setPname(resultSet.getString(2));
					person.setFname(resultSet.getString(3));
					person.setMname(resultSet.getString(4));
					person.setPage(resultSet.getInt(5));
					//person.setMno(Double.valueOf(resultSet.getDouble(6)));
					person.setMno(resultSet.getString(6));
		    	    person.setAtype(resultSet.getString(7));
				    person.setPaddr(resultSet.getString(8));
					person.setPwd(resultSet.getString(9));
					//System.out.println();
					person.setAmount(resultSet.getDouble(10));
					
					System.out.println("Person ready to sent..");
					
					return person;
				}
			}
			else
			{
				System.out.println("ResultSet is null");
			}
		}
			catch(SQLException |IOException e)
			{
				e.printStackTrace();
			}
		
		
		return person;
		
	
	}



	@Override
	public Person balance(Person person) {
		// person=null;
		  Integer ano=person.getaNo();
		String sqlSelectQuery="Select balance from persons where ano=?";
		
		PreparedStatement psmt=null;
		ResultSet resultSet=null;
		try
		{
			connection=JdbcUtil.getJdbcConnection();
			if(connection!=null)
			psmt=connection.prepareStatement(sqlSelectQuery);
			if(psmt!=null)
			{
				psmt.setInt(1, ano);
				resultSet=psmt.executeQuery();
				System.out.println("Ano is: "+ano);
			}	
			if(resultSet!=null)
			{
				if(resultSet.next())
				{
					person=new Person();
					System.out.println("Coping resultset data to person object");
					//System.out.println(resultSet.getString(2));
					
//					person.setaNo(resultSet.getInt(1));
					person.setBalance(resultSet.getDouble(1));
					
					System.out.println("Person ready to sent..");
					
					return person;
				}
			}
			else
			{
				System.out.println("ResultSet is null");
			}
		}
			catch(SQLException |IOException e)
			{
				e.printStackTrace();
			}
		
		
		return person;
		
	
	}
		
}
		
		
		