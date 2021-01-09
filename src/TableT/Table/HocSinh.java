package TableT.Table;

import TableT.Annotation.Column.ColumnDB;
import TableT.Annotation.Column.ForeignKey;
import TableT.Annotation.Column.PrimaryKey;
import TableT.Annotation.TableDB;

import java.lang.annotation.Annotation;

@TableDB("HocSinh2")
public class HocSinh {

    // Khởi tạo các phần tử của Annotation theo cách thông thường.
    @ColumnDB("id")
    @PrimaryKey()
    int id;

    @ColumnDB("ten")
    String name;

    @ForeignKey("lophoc")
    int classId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClassId() {
        return classId;
    }

    public static void main(String[] args) {
        Class<HocSinh> girlClass = HocSinh.class;
        System.out.println("Class: "+girlClass.getSimpleName()); // Lấy ra tên Class
        for(Annotation annotation : girlClass.getDeclaredAnnotations()){
            System.out.println("Annotation: " + annotation.annotationType()); // Lấy ra tên các Annatation trên class này
        }


        }
    }
