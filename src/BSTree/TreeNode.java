/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import Model.Model;

/**
 *
 * @author iamwu
 */
public class TreeNode {
    Model info;
    TreeNode left;
    TreeNode right;

    TreeNode(Model info, TreeNode left, TreeNode right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
    
    TreeNode(Model info) {
        this(info,null,null);
    }

    public Model getInfo() {
        return info;
    }
    
    
}
