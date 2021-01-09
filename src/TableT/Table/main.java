package TableT.Table;

import main.jdbc.Session;

public class main {
    public static void main(String[] args) throws Exception {
        Table<HocSinh> hs = new Table<HocSinh>(HocSinh.class);
        System.out.println(hs.getPrimaryKey());
    }
}
