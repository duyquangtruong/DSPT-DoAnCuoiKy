package DAO;

import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("lophoc")
public class lophoc {
	@ColumnDB("NumberOfStudent")
	private int NumberOfStudent;

	@ColumnDB("ClassName")
	private String ClassName;

	@ColumnDB("Teacher")
	private String Teacher;

	@ColumnDB("ClassId")
	@PrimaryKey()
	@Required()
	private String ClassId;

	public int getNumberOfStudent() { return this.NumberOfStudent; }

	public void setNumberOfStudent(int value) { this.NumberOfStudent = value; }

	public String getClassName() { return this.ClassName; }

	public void setClassName(String value) { this.ClassName = value; }

	public String getTeacher() { return this.Teacher; }

	public void setTeacher(String value) { this.Teacher = value; }

	public String getClassId() { return this.ClassId; }

	public void setClassId(String value) { this.ClassId = value; }

}