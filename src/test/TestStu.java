package test;

import org.dom4j.DocumentException;
import service.StuService;
import vo.Student;

import java.io.IOException;

public class TestStu {
    public static void testAdd() throws IOException, DocumentException {
        Student student = new Student();
        student.setAge("30");
        student.setId("102");
        student.setName("liming");
        StuService.addStu(student);
    }

    public static void testDel() throws IOException, DocumentException {
        StuService.delStu("102");
    }

    public static void testSel() throws DocumentException {
        System.out.println(StuService.getStu("100"));
    }

    public static void main(String[] args) throws IOException, DocumentException {
//        testDel();
        //testAdd();
        testSel();
    }
}
