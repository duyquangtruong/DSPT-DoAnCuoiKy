package DAO;

import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("sec_table")
public class sec_table {
	@ColumnDB("heheh")
	private Boolean heheh;

	@ColumnDB("first")
	@ForeignKey("null")
	@Required()
	private String first;

}