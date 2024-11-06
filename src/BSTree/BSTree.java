/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import Model.Model;
import MyLinkedList.MyLinkedList;
import MyLinkedList.Node;

/**
 *
 * @author iamwu
 */
public class BSTree {

    TreeNode root;

    public BSTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getLeft(TreeNode p) {
        return p.left;
    }

    public TreeNode getRight(TreeNode p) {
        return p.right;
    }
    
    public TreeNode getNodeByRoomObject(Model targetObject) {
        return getNodeByRoomObject(root, targetObject);
    }

    // Helper method for recursive search
    private TreeNode getNodeByRoomObject(TreeNode node, Model targetObject) {
        if (node == null) {
            return null; // Node not found
        }

        // Compare based on RoomID or another unique property
        if (node.info.getKey().equals(targetObject.getKey())) {
            return node; // Found the node with the matching RoomID
        } else if (targetObject.getKey().compareTo(node.info.getKey()) < 0) {
            return getNodeByRoomObject(node.left, targetObject); // Search in left subtree
        } else {
            return getNodeByRoomObject(node.right, targetObject); // Search in right subtree
        }
    }

    public void insert(Model x) throws Exception {
        TreeNode p = new TreeNode(x);
        TreeNode f = null;
        TreeNode q = root;
        while (q != null) {
            Model current = (Model) q.getInfo();  // Lấy thông tin của TreeNode q
            int comparison = x.getKey().toLowerCase().compareToIgnoreCase(current.getKey());

            if (comparison == 0) {
                throw new Exception("Key cannot be duplicated!");
            }
            if (comparison > 0) {
                f = q;
                q = q.right;
            } else {
                f = q;
                q = q.left;
            }
        }
        if (f == null) {
            root = p;
        } else {
            Model fModel = (Model) f.getInfo();  // Lấy thông tin của TreeNode f
            if (x.getKey().toLowerCase().compareToIgnoreCase(fModel.getKey()) > 0) {
                f.right = p;
            } else {
                f.left = p;
            }
        }
    }

    public Model search(String key) {
        TreeNode q = root;
        while (q != null) {
            Model current = (Model) q.getInfo();  // Lấy đối tượng từ TreeNode
            int comparison = key.toLowerCase().compareToIgnoreCase(current.getKey());

            if (comparison == 0) {
                return current;  // Tìm thấy đối tượng có khóa phù hợp
            } else if (comparison < 0) {
                q = q.left;  // Khóa tìm kiếm nhỏ hơn, đi sang nhánh trái
            } else {
                q = q.right;  // Khóa tìm kiếm lớn hơn, đi sang nhánh phải
            }
        }
        return null;  // Không tìm thấy đối tượng phù hợp
    }

    public void inOrder(TreeNode p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    private void visit(TreeNode p) {
        Model m = (Model) p.info;
        System.out.println(m.toString());
    }

    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public int size() {
        if (root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }

    // Sử dụng linked list để lưu các nút
    public void balance(TreeNode root) throws Exception {
        MyLinkedList nodeList = new MyLinkedList();
        buildLinkedList(nodeList, root);

        BSTree balancedTree = new BSTree();
        balancedTree.balance(nodeList, 0, nodeList.getSize()-1);
        
        this.root = balancedTree.root;
    }

    private void balance(MyLinkedList nodeList, int start, int end) throws Exception {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        Node middNode = nodeList.getByIndex(mid);
        
        insert((Model) middNode.getInfo());
        
        balance(nodeList, start, mid-1);
        balance(nodeList, mid+1, end);
    }
    
    private void buildLinkedList(MyLinkedList nodeList, TreeNode current) {
        if (current == null) {
            return;
        }
        buildLinkedList(nodeList, current.left);
        nodeList.addLast(current.info); // Giả sử add() thêm vào cuối danh sách
        buildLinkedList(nodeList, current.right);
    }
    
    public MyLinkedList toLinkedList(){
        MyLinkedList result = new MyLinkedList();
        buildLinkedList(result, root);
        if(result.isEmpty()) return null;
        return result;
    }

    public void deleteByCopying(Model o) throws Exception {
        //Check if BSTree is emptys
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion");
            return;
        }

        //Search node to be delete
        TreeNode deleteNode;
        TreeNode parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info.getKey().equals(o.getKey())) {
                break; //found x
            }
            //continue search
            if (o.getKey().compareTo(deleteNode.info.getKey()) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }

        //check if found x 
        if (deleteNode == null) {
            throw new Exception("The key does not exist, no deletion");
        }

        //Case 1: delete leaf node
        if (deleteNode.left == null && deleteNode.right == null) {
            //check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = null;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = null;
                } else {
                    parentOfDeleteNode.right = null;
                }
            }
            return;
        }

        //Case 2: Delete node having only left child
        if (deleteNode.left != null && deleteNode.right == null) {
            //check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }

        //Case 3: Delete node having only right child
        if (deleteNode.left == null && deleteNode.right != null) {
            //check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.right;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.right;
                } else {
                    parentOfDeleteNode.right = deleteNode.right;
                }
            }
            deleteNode.right = null;
            return;
        }

        //Case 4: Delete node having both left and right children
        if (deleteNode.left != null && deleteNode.right != null) {
            TreeNode parentReplaceNode; //parentNode of replaceNode
            TreeNode replaceNode; //the right most node - this will replace deleteNode

            //find the right most node on the left sub-tree of deleteNode
            parentReplaceNode = null;
            replaceNode = deleteNode.left;
            while (replaceNode.right != null) {
                parentReplaceNode = replaceNode;
                replaceNode = replaceNode.right;
            }

            //copy info of replaceNode to deleteNode
            deleteNode.info = replaceNode.info;

            if (parentReplaceNode == null) {
                // replaceNode is left child of deleteNode
                deleteNode.left = replaceNode.left;
            } else {
                //replaceNode is not left child of deleteNode
                parentReplaceNode.right = replaceNode.left;
            }

            replaceNode.left = null;

        }
    }
    
    public void deleteByMerging(Model o) throws Exception {
        //check if BSTree is emptys
        if (isEmpty()) {
            System.out.println("BSTree is empty, no deletion");
            return;
        }

        //search node to be delete
        TreeNode deleteNode;
        TreeNode parentOfDeleteNode;
        deleteNode = root;
        parentOfDeleteNode = null;
        while (deleteNode != null) {
            if (deleteNode.info.getKey().equals(o.getKey())) {
                break;     //found x
            }
            //continue search
            if (o.getKey().compareTo(deleteNode.info.getKey()) < 0) {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.left;
            } else {
                parentOfDeleteNode = deleteNode;
                deleteNode = deleteNode.right;
            }
        }

        //check if found x
        if (deleteNode == null) {
            throw new Exception("The key does not exist, no deletion");
        }

        //Case 1: Delete leaf node
        if (deleteNode.left == null && deleteNode.right == null) {
            //check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = null;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = null;
                } else {
                    parentOfDeleteNode.right = null;
                }
            }
            return;
        }

        //Case 2: Delete node having only left child
        if (deleteNode.left != null && deleteNode.right == null) {
            //check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;
            return;
        }

        //Case 3: Delete node having only right child
        if (deleteNode.left == null && deleteNode.right != null) {
            //check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.right;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.right;
                } else {
                    parentOfDeleteNode.right = deleteNode.right;
                }
            }
            deleteNode.right = null;
            return;
        }

        //Case 4: Delete node having both left and right children
        if (deleteNode.left != null && deleteNode.right != null) {
            TreeNode rightOfDeleteNode;
            TreeNode replaceNode;   // the right most node - this will replace deleteNode

            //find the right most node on the left sub-tree of deleteNode
            rightOfDeleteNode = deleteNode.right;
            replaceNode = deleteNode.left;
            while (replaceNode.right != null) {
                replaceNode = replaceNode.right;
            }

            replaceNode.right = rightOfDeleteNode;
            deleteNode.right = null;
            //now deleteNode has only left child
            //check if deleteNode is root
            if (parentOfDeleteNode == null) {
                root = deleteNode.left;
            } else {
                if (parentOfDeleteNode.left == deleteNode) {
                    parentOfDeleteNode.left = deleteNode.left;
                } else {
                    parentOfDeleteNode.right = deleteNode.left;
                }
            }
            deleteNode.left = null;

        }
    }
}
