package in.ineuron.dto;

import java.io.Serializable;

public class Person implements Serializable {

private static final long serialVersionUID=1L;
	
	private Integer ano;
	private String pname;
	private String mname;
	private String fname;
	private Integer page;
	private String mno;
	private String pwd;
	private String paddr;
	private String atype;
	private double amount;
	private double balance;
	
	static
	{
		System.out.println("Person.class is loading.....");
	}
	public Person()
	{
		System.out.println("Person object is created.....");
	}
	public Integer getaNo() {
		return ano;
	}
	public void setaNo(Integer ano) {
		this.ano = ano;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPaddr() {
		return paddr;
	}
	public void setPaddr(String paddr) {
		this.paddr = paddr;
	}
	public String getAtype() {
		return atype;
	}
	
	public void setAtype(String atype) {
		this.atype = atype;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Person [ano=" + ano + ", pname=" + pname + ", mname=" + mname + ", fname=" + fname + ", page=" + page
				+ ", mno=" + mno + ", pwd=" + pwd + ", paddr=" + paddr + ", atype=" + atype + ", amount=" + amount
				+ ", balance=" + balance + "]";
	}
	
//	@Override
//	public String toString() {
//		return "Person [ano=" + ano + ", pname=" + pname + ", mname=" + mname + ", fname=" + fname + ", page=" + page
//				+ ", mno=" + mno + ", pwd=" + pwd + ", paddr=" + paddr + ", atype=" + atype + ", amount=" + amount
//				+ "]";
//	}
//	@Override
//	public String toString() {
//		return "Person [ano=" + ano + ", pname=" + pname + ", mname=" + mname + ", fname=" + fname + ", page=" + page
//				+ ", mno=" + mno + ", pwd=" + pwd + ", paddr=" + paddr + ", atype=" + atype + "]";
//	}
}
