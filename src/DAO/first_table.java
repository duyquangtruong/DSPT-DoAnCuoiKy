package DAO;

import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("first_table")
public class first_table {
	@ColumnDB("field_2")
	@Required()
	private int field_2;

	@ColumnDB("testrequired")
	private String testrequired;

	@ColumnDB("hah")
	@PrimaryKey()
	private String hah;

	public int getField_2() {
		return field_2;
	}

	public void setField_2(int field_2) {
		this.field_2 = field_2;
	}

	public String getTestrequired() {
		return testrequired;
	}

	public void setTestrequired(String testrequired) {
		this.testrequired = testrequired;
	}

	public String getHah() {
		return hah;
	}

	public void setHah(String hah) {
		this.hah = hah;
	}
}