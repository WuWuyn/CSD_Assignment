/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import MyLinkedList.MyLinkedList;

/**
 *
 * @author iamwu
 */
public class MyQueue {
    MyLinkedList queues;

    public MyQueue() {
        this.queues = new MyLinkedList();
    }


    public boolean isEmpty(){
        return(this.queues.isEmpty());
    }
    
    public void clear(){
        this.queues.clear();
    }

    Object front() throws Exception{
        if(isEmpty()) throw new Exception();
        return(this.queues.getFirst());
    }
    
    public Object dequeue(){
        return this.queues.removeFirst();
    }

    public void enqueue(Object x){
        this.queues.addLast(x);
    }
}
