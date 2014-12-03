/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dioglez;

import java.awt.Point;

/**
 *
 * @author eduardo
 */
public class Node {
    private Node next;
    private final Node previous;
    private final Point p;
    public Node(Node previous,Point p){
        this.next = null;
        this.previous = previous;
        this.p = p;
    }
    public Node add(Point p){
        return new Node(this,p);
    }
    public void setNext(Node next){
        this.next = next;
    }
    public Node getNext(){
        return this.next;
    }
    public Node getPrevious(){
        return this.previous;
    }
    public Point getPoint(){
        return this.p;
    }
}
