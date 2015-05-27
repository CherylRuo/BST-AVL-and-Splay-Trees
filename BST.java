/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Reference 1
 * @author CherylRuo
 */
public class BST {

    private BSTNode root;
    int lowerLimit;
    int upperLimit;
    
    public BST(int lowerLimit, int upperLimit)
    {
        root = null;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
    
    public BSTNode getBSTRoot()
    {
        return root;
    }
    
    public void makeEmpty()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root == null;
    }
    
    public void add(Comparable obj)
    {
        BSTNode newNode = new BSTNode();
        newNode.data = obj;
        newNode.left = null;
        newNode.right = null;
        if(root == null)
            root = newNode;
        else
            root.addNode(newNode);
    }
    
    public boolean find(Comparable obj)
    {
        BSTNode current = root;
        while(current != null)
        {
            int d = current.data.compareTo(obj);
            if(d == 0)
                return true;
            else if(d > 0)
                current = current.left;
            else
                current = current.right;
        }
        return false;
    }
    // delete node
    public void remove(Comparable obj)
    {
        BSTNode toBeRemoved = root;
        BSTNode parent = null;
        boolean found = false;
        while(!found && toBeRemoved != null)
        {
            int d = toBeRemoved.data.compareTo(obj);
            if(d == 0)
                found = true;
            else
            {
                parent = toBeRemoved;
                if(d > 0)
                    toBeRemoved = toBeRemoved.left;
                else
                    toBeRemoved = toBeRemoved.right;
            }
        }
        if(!found)
        {
            if(obj.compareTo(upperLimit) > 0 || obj.compareTo(lowerLimit) < 0)
                System.out.println("--Key is out of range. ");
            return;
        }
        if(toBeRemoved.left == null || toBeRemoved.right == null)
        {
            BSTNode newChild;
            if(toBeRemoved.left == null)
                newChild = toBeRemoved.right;
            else
                newChild = toBeRemoved.left;
            if(parent == null)
                root = newChild;
            else if(parent.left == toBeRemoved)
                parent.left = newChild;
            else
                parent.right = newChild;
            return;
        }
        
        BSTNode smallestParent = toBeRemoved;
        BSTNode smallest = toBeRemoved.right;
        while(smallest.left != null)
        {
            smallestParent = smallest;
            smallest = smallest.left;
        }
        
        toBeRemoved.data = smallest.data;
        if(smallestParent == toBeRemoved)
            smallestParent.right = smallest.right;
        else
            smallestParent.left = smallest.right;
    }
}
