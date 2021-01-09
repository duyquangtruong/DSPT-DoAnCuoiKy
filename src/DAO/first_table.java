package DAO;

import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("first_table")
public class first_table {
	@ColumnDB("field 2")
	@Required()
	private int field_2;

	@ColumnDB("testrequired")
	private int testrequired;

	@ColumnDB("hah")
	private int hah;

}