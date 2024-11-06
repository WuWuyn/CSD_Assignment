/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import BSTree.BSTree;
import BSTree.TreeNode;
import Model.Model;
import Model.Room;
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
public class RoomManager {

    BSTree roomTree;

    public RoomManager() {
        this.roomTree = new BSTree();
    }

    public BSTree getRoomTree() {
        return roomTree;
    }

    //1. Load data from file
    public void readFile(String filepath) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader(filepath));
        String line = bf.readLine();
        String[] info;
        Room r = null;
        while (line != null) {
            info = line.split(",");
            r = new Room(info[0], info[1], info[2], info[3], info[4], Integer.parseInt(info[5]), Integer.parseInt(info[6]), Double.parseDouble(info[7]));
            if (r != null) {
                roomTree.insert(r);
            }
            line = bf.readLine();
        }
        bf.close();
    }

    //2. Input & add to the tree
    public void addRoom(Room newRoom) throws Exception {
        roomTree.insert((Model) newRoom);
    }

    //4. Save room tree to file by post-order traversal
    public void saveToFile(String filepath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
            saveNodeToFile(bw, roomTree.getRoot());
            bw.close();
        }
    }

    private void saveNodeToFile(BufferedWriter bw, TreeNode node) throws IOException {
        if (node == null) {
            return; // Nếu nút hiện tại là null, thoát
        }
        // Duyệt cây con bên trái
        saveNodeToFile(bw, roomTree.getLeft(node));

        // Duyệt cây con bên phải
        saveNodeToFile(bw, roomTree.getRight(node));
        
        // Ghi thông tin sinh viên vào tệp
        Room room = (Room) node.getInfo();
        bw.write(room.toString());
        bw.newLine();
    }
    
    //5. search room data by rcode
    public Room searchByRoomCode(String roomCode) {
        Model result = roomTree.search(roomCode);  // Gọi hàm search với roomCode

        if (result instanceof Room) {
            return (Room) result;  // Nếu kết quả là Room, trả về Room
        }
        return null;  // Nếu không phải Room hoặc không tìm thấy, trả về null
    }

    //6. Delete by copying
    public void deleteByCopying(String rcode) throws Exception{
        Room r = searchByRoomCode(rcode);
        if(r != null) roomTree.deleteByCopying((Model) r);
        else throw new Exception("This room does not exists! Cannot delete!");
    }
    
    //7. Delete by merging
    public void deleteByMerging(String rcode) throws Exception{
        Room r = searchByRoomCode(rcode);
        if(r != null) roomTree.deleteByMerging((Model) r);
        else throw new Exception("This room does not exists! Cannot delete!");
    }
    
    //8. Simply balancing
    public void simpleBalance() throws Exception{
        roomTree.balance(roomTree.getRoot());
    }
    
    
    //10. Count the number of rooms
    public int countRooms() {
        return roomTree.size();
    }
    
    //11. Search by name
    public MyLinkedList searchByroomName(String roomname) {
        MyLinkedList list = roomTree.toLinkedList();  
        MyLinkedList result = new MyLinkedList();  

        for (int i = 0; i < list.getSize(); i++) {  
            Model model = (Model) list.getByIndex(i).getInfo();  
            if (model instanceof Room) {  
                Room room = (Room) model;
                if (room.getName().equalsIgnoreCase(roomname)) {  
                    result.addLast(room); 
                }
            }
        }
        return result;  
    }
}
