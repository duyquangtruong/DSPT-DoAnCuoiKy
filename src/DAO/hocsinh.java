package DAO;

import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("hocsinh")
public class hocsinh {
	@ColumnDB("Address")
	@Required()
	private String Address;

	@ColumnDB("FirstName")
	@Required()
	private String FirstName;

	@ColumnDB("ClassId")
	@ForeignKey("null")
	@Required()
	private int ClassId;

	@ColumnDB("Id")
	private int Id;

	@ColumnDB("LastName")
	@Required()
	private String LastName;

	@ColumnDB("City")
	@Required()
	private String City;

	public String setAddress() { return this.Address; }

	public void setAddress(String value) { this.Address = value; }

	public String setFirstName() { return this.FirstName; }

	public void setFirstName(String value) { this.FirstName = value; }

	public int setClassId() { return this.ClassId; }

	public void setClassId(int value) { this.ClassId = value; }

	public int setId() { return this.Id; }

	public void setId(int value) { this.Id = value; }

	public String setLastName() { return this.LastName; }

	public void setLastName(String value) { this.LastName = value; }

	public String setCity() { return this.City; }

	public void setCity(String value) { this.City = value; }

}