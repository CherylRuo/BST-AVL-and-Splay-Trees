/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CherylRuo
 */
public class AvlNode
{   
    int      element;
    AvlNode  left;
    AvlNode  right;
    int      height;
        
    AvlNode(int theElement)
    {
        this(theElement, null, null);
    }

    AvlNode(int theElement, AvlNode lt, AvlNode rt)
    {
        element  = theElement;
        left     = lt;
        right    = rt;
        height   = 0;
    }
}