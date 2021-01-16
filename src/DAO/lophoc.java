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

	@ColumnDB("ClassId")
	private int ClassId;

	public int setNumberOfStudent() { return this.NumberOfStudent; }

	public void setNumberOfStudent(int value) { this.NumberOfStudent = value; }

	public String setClassName() { return this.ClassName; }

	public void setClassName(String value) { this.ClassName = value; }

	public String setTeacher() { return this.Teacher; }

	public void setTeacher(String value) { this.Teacher = value; }

	public int setClassId() { return this.ClassId; }

	public void setClassId(int value) { this.ClassId = value; }

}