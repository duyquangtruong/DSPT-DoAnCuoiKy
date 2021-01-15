package DAO;

import Generator.DBContext.DomainObj;
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

	public sec_table(Boolean heheh, String first) {
		this.heheh = heheh;
		this.first = first;
	}

	public Boolean getHeheh() {
		return heheh;
	}

	public void setHeheh(Boolean heheh) {
		this.heheh = heheh;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}
}