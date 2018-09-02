package com.tree;

/**
 * Name: Node
 * Author: tunsh
 * Date: 2018-03-14 13:57
 */
public class Node<T> {
    public T data;
    public Node left;
    public Node right;
    public int state;

    public Node(T d) {
        this.data = d;
    }
}
