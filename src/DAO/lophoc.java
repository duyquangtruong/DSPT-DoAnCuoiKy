package DAO;

import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("lophoc")
public class lophoc {
	@ColumnDB("NumberOfStudent")
	@Required()
	private int NumberOfStudent;

	@ColumnDB("ClassName")
	@Required()
	private String ClassName;

	@ColumnDB("Teacher")
	@Required()
	private String Teacher;

	@ColumnDB("id")
	@PrimaryKey()
	private int id;

	public int setNumberOfStudent() { return this.NumberOfStudent; }

	public void setNumberOfStudent(int value) { this.NumberOfStudent = value; }

	public String setClassName() { return this.ClassName; }

	public void setClassName(String value) { this.ClassName = value; }

	public String setTeacher() { return this.Teacher; }

	public void setTeacher(String value) { this.Teacher = value; }

	public int setId() { return this.id; }

	public void setId(int value) { this.id = value; }

}