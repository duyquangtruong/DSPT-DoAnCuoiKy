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

}