
/**
 * Reference 3
 * @author CherylRuo
 */
public class SplayTree
{
    private SplayNode root;
    private int count = 0;
    TreePrint tp = new TreePrint();
    int lowerLimit;
    int upperLimit;
    
    /** Constructor **/
    public SplayTree(int lowerLimit, int upperLimit)
    {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        root = null;
    }
    public SplayNode getSplayRoot()
    {
        return root;
    }
    
    /** Function to check if tree is empty **/
    public boolean isEmpty()
    {
        return root == null;
    }
 
    /** clear tree **/
    public void clear()
    {
        root = null;
    }
 
    /** function to insert element */
    public void insert(int ele)
    {
        SplayNode z = root;
        SplayNode p = null;
        while (z != null)
        {
            p = z;
            if (ele > p.element)
                z = z.right;
            else
                z = z.left;
        }
        z = new SplayNode();
        z.element = ele;
        z.parent = p;
        if (p == null)
            root = z;
        else if (ele > p.element)
            p.right = z;
        else
            p.left = z;
        Splay(z);
        count++;
    }
    /** rotate **/
    public void makeLeftChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
 
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else 
                p.parent.right = c;
        }
        if (c.right != null)
            c.right.parent = p;
 
        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
        System.out.println("\n*Make Left Child Parent: ");
        tp.printSplayLevelOrder(6, root);
    }
 
    /** rotate **/
    public void makeRightChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
        System.out.println("\n*Make Right Child Parent: ");
        tp.printSplayLevelOrder(6, root);
    }
 
    /** function splay **/
    private void Splay(SplayNode x)
    {
        while (x.parent != null)
        {
            SplayNode Parent = x.parent;
            SplayNode GrandParent = Parent.parent;             
            if (GrandParent == null)
            {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);                 
            } 
            else
            {
                if (x == Parent.left)
                {
                    if (Parent == GrandParent.left)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else 
                    {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else 
                {
                    if (Parent == GrandParent.left)
                    {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    } 
                    else 
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
        System.out.println("\n*After splaying node " + x.element + ": ");
        tp.printSplayLevelOrder(6, root);
    }
 
    /** function to remove element **/
    public void remove(int ele)
    {
       if(ele > upperLimit || ele < lowerLimit)
            System.out.println("--Key is out of range. ");
       SplayNode node = findNode(ele);
       remove(node);
    }
 
    /** function to remove node **/
    private void remove(SplayNode node)
    {
        if (node == null)
            return;
 
        Splay(node);
        if( (node.left != null) && (node.right !=null))
        { 
            SplayNode min = node.left;
            while(min.right!=null)
                min = min.right;

            min.right = node.right;
            node.right.parent = min;
            node.left.parent = null;
            root = node.left;
        }
        else if (node.right != null)
        {
            node.right.parent = null;
            root = node.right;
        } 
        else if( node.left !=null)
        {
            node.left.parent = null;
            root = node.left;
        }
        else
        {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
        node = null;
        count--;
    }
 
    /** Functions to count number of nodes **/
    public int countNodes()
    {
        return count;
    }
 
    /** Functions to search for an element **/
    public boolean search(int val)
    {
        return findNode(val) != null;
    }
    private SplayNode findNode(int ele)
    {
        SplayNode z = root;
        while (z != null)
        {
            if (ele < z.element)
                z = z.right;
            else if (ele > z.element)
                z = z.left;
            else
                return z;
        }
        return null;
    }
 }