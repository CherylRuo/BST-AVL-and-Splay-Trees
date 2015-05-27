/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CherylRuo
 */
public class SplayNode {
    
    SplayNode left, right, parent;
    int element;
    
    public SplayNode()
    {
        this(0, null, null, null);
    }
    
    public SplayNode(int element)
    {
        this(element, null, null, null);
    }
    
    public SplayNode(int element, SplayNode left, SplayNode right, SplayNode parent)
    {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.element = element;
    }
}
