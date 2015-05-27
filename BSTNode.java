/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CherylRuo
 */
public class BSTNode 
{
    public Comparable data;
    public BSTNode left;
    public BSTNode right;
    public void addNode(BSTNode newNode)
    {
        int comp = newNode.data.compareTo(data);
        if(comp < 0)
        {
            if(left == null)
                left = newNode;
            else
                left.addNode(newNode);     
        }
        else if(comp > 0)
        {
            if(right == null)
                right = newNode;
            else
                right.addNode(newNode);
        }
    }
}