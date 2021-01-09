import TableT.Annotation.Column.*;
import TableT.Annotation.*;

@TableDB("sec_table")
public class sec_table {
	@ColumnDB("heheh")
	private BIT heheh;

	@ColumnDB("first")
	@ForeignKey("null")
	@Required()
	private VARCHAR first;

}