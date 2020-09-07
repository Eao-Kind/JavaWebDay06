package service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import vo.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StuService {
    /**
     * @param student
     * @throws DocumentException
     * @throws IOException
     */
    public static void addStu(Student student) throws DocumentException, IOException {
        /*
         * 创建解析器——得到document——获取根节点——添加标签——回写
         */
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/student.xml");
        Element root = document.getRootElement();
        Element stu = root.addElement("stu"); // 添加stu标签
        Element id = stu.addElement("id");
        Element name1 = stu.addElement("name");
        Element age1 = stu.addElement("age");
        id.setText(student.getId());
        name1.setText(student.getName());
        age1.setText(student.getAge());
        // 回写
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/student.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     * @param id
     * @throws DocumentException
     * @throws IOException
     */
    public static void delStu(String id) throws DocumentException, IOException {
        // 得到解析器——获取所有的id——得到相同id——使用父节点删除
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/student.xml");
        List<Node> list = document.selectNodes("//id");
        for (Node node : list) {
            String idv = node.getText();
            if (idv.equals(id)) { // 判断id是否相同
                Element stu = node.getParent();
                Element student = stu.getParent();
                student.remove(stu);
            }
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/student.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     * 查询
     *
     * @param id
     * @return
     * @throws DocumentException
     */
    public static Student getStu(String id) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/student.xml");
        List<Node> list = document.selectNodes("//id");
        Student student = new Student();
        for (Node node : list) {
            String idv = node.getText();
            if (idv.equals(id)) {
                Element stu = node.getParent();
                String name = stu.element("name").getText();
                String age = stu.element("age").getText();
                student.setId(idv);
                student.setName(name);
                student.setAge(age);
            }
        }
        return student;
    }
}
