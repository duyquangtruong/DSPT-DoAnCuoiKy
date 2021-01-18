package DAO;

import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("hocsinh")
public class hocsinh {
	@ColumnDB("FirstName")
	private String FirstName;

	@ColumnDB("ClassId")
	@ForeignKey("lophoc")
	private String ClassId;

	@ColumnDB("Id")
	@PrimaryKey()
	@Required()
	private String Id;

	@ColumnDB("LastName")
	private String LastName;

	@ColumnDB("City")
	private String City;

	public String getFirstName() { return this.FirstName; }

	public void setFirstName(String value) { this.FirstName = value; }

	public String getClassId() { return this.ClassId; }

	public void setClassId(String value) { this.ClassId = value; }

	public String getId() { return this.Id; }

	public void setId(String value) { this.Id = value; }

	public String getLastName() { return this.LastName; }

	public void setLastName(String value) { this.LastName = value; }

	public String getCity() { return this.City; }

	public void setCity(String value) { this.City = value; }

}