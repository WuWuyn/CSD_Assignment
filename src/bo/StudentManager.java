/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import BSTree.BSTree;
import BSTree.TreeNode;
import Model.Model;
import Model.Student;
import MyLinkedList.MyLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author iamwu
 */
public class StudentManager {

    BSTree studentTree;

    public StudentManager() {
        this.studentTree = new BSTree();
    }

    public BSTree getStudentTree() {
        return studentTree;
    }

    //1. Load data from file
    public void readFile(String filepath) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader(filepath));
        String line = bf.readLine();
        String[] info;
        Student s = null;
        while (line != null) {
            info = line.split(",");
            s = new Student(info[0], info[1], Integer.parseInt(info[2]));
            if (s != null) {
                studentTree.insert(s);
            }
            line = bf.readLine();
        }
        bf.close();
    }

    //2. Add student to the tree
    public void addStudent(Student student) throws Exception {
        studentTree.insert(student);  // Insert student into the binary search tree
    }

    //4. Save student tree to file by in-order traversal
    public void saveToFile(String filepath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
            saveNodeToFile(bw, studentTree.getRoot());
            bw.close();
        }
    }

    private void saveNodeToFile(BufferedWriter bw, TreeNode node) throws IOException {
        if (node == null) {
            return; // Nếu nút hiện tại là null, thoát
        }
        // Duyệt cây con bên trái
        saveNodeToFile(bw, studentTree.getLeft(node));

        // Ghi thông tin sinh viên vào tệp
        Student student = (Student) node.getInfo();
        bw.write(student.toString());
        bw.newLine();

        // Duyệt cây con bên phải
        saveNodeToFile(bw, studentTree.getRight(node));
    }

    //5. Search by scode
    public Student searchByStudentCode(String studentCode) {
        Model result = studentTree.search(studentCode);  // Gọi hàm search với roomCode

        if (result instanceof Student) {
            return (Student) result;  // Nếu kết quả là Room, trả về Room
        }
        return null;  // Nếu không phải Room hoặc không tìm thấy, trả về null
    }
    
    //6. Delete by scode by copying
    public void deleteByCopying(String scode) throws Exception{
        Student s = searchByStudentCode(scode);
        if(s != null) studentTree.deleteByCopying((Model) s);
        else throw new Exception("This student does not exists! Cannot delete!");
    }
    
    //7. Search by name
    public MyLinkedList searchByStudentName(String studentName) {
        MyLinkedList list = studentTree.toLinkedList();  
        MyLinkedList result = new MyLinkedList();  

        for (int i = 0; i < list.getSize(); i++) {  
            Model model = (Model) list.getByIndex(i).getInfo();  
            if (model instanceof Student) {  
                Student student = (Student) model;
                if (student.getName().equalsIgnoreCase(studentName)) {  
                    result.addLast(student); 
                }
            }
        }
        return result;  
    }
    
}
    
