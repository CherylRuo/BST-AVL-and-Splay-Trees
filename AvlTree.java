
/**
 * Reference 2
 * @author CherylRuo
 */
public class AvlTree
{   
    private AvlNode root;
    TreePrint tp = new TreePrint();
    int lowerLimit;
    int upperLimit;
    
    public AvlTree(int lowerLimit, int upperLimit)
    {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        root = null;
    }
    
    public AvlNode getAvlRoot()
    {
        return root;
    }
    
    public void insert(int x)
    {
        root = insert(x, root);
    }

    public void remove(int x)
    {
        if(x > upperLimit || x < lowerLimit)
            System.out.println("--Key is out of range. ");
        root = remove(x, root);
    }

    private AvlNode remove(int x, AvlNode t)
    {
        if(t == null)
            return t;   // Item not found; do nothing            
        if(x < t.element)
            t.left = remove(x, t.left);
        else if(x > t.element)
            t.right = remove(x, t.right);
        else if(t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else
            t = (t.left != null) ? t.left : t.right;
        return balance(t);
    }
    
    public int findMin() throws UnderflowException
    {
        if(isEmpty( ))
            throw new UnderflowException();
        return findMin(root).element;
    }

    public int findMax() throws UnderflowException
    {
        if(isEmpty())
            throw new UnderflowException();
        return findMax(root).element;
    }

    public boolean contains(int x)
    {
        return contains(x, root);
    }

    public void makeEmpty()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    private static final int ALLOWED_IMBALANCE = 1;
    
    // Assume t is either balanced or within one of being balanced
    private AvlNode balance(AvlNode t)
    {
        if(t == null)
            return t;
        
        if( height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if( height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }
    
    public void checkBalance()
    {
        checkBalance(root);
    }
    
    private int checkBalance(AvlNode t)
    {
        if(t == null)
            return -1;
        
        if(t != null)
        {
            int hl = checkBalance(t.left);
            int hr = checkBalance(t.right);
            if(Math.abs(height(t.left) - height(t.right )) > 1 ||
                    height(t.left) != hl || height(t.right) != hr)
                System.out.println("OOPS!!");
        }
        
        return height(t);
    }
    
    private AvlNode insert(int x, AvlNode t)
    {
        if(t == null)
            return new AvlNode(x, null, null);        
        if(x < t.element)
            t.left = insert(x, t.left);
        else if(x > t.element)
            t.right = insert(x, t.right);
        else
            ;  // Duplicate; do nothing
        return balance(t);
    }

    private AvlNode findMin(AvlNode t)
    {
        if(t == null)
            return t;

        while(t.left != null)
            t = t.left;
        return t;
    }

    private AvlNode findMax(AvlNode t)
    {
        if(t == null)
            return t;

        while(t.right != null)
            t = t.right;
        return t;
    }

    private boolean contains(int x, AvlNode t)
    {
        while(t != null)
        {            
            if(x < t.element)
                t = t.left;
            else if(x > t.element)
                t = t.right;
            else
                return true;    // Match
        }       
        if(x > upperLimit || x < lowerLimit)
            System.out.println("--Key is out of range. ");
        return false;   // No match
    }

    private int height(AvlNode t)
    {
        return t == null ? -1 : t.height;
    }
   
    private AvlNode rotateWithLeftChild(AvlNode k2)
    {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        System.out.println("\n*Rotate with Left Child: ");
        tp.printAVLLevelOrder(6, root); 
        return k1;
    }

    private AvlNode rotateWithRightChild(AvlNode k1)
    {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        System.out.println("\n*Rotate with Right Child: ");
        tp.printAVLLevelOrder(6, root);    
        return k2;
    }

    private AvlNode doubleWithLeftChild(AvlNode k3)
    {
        k3.left = rotateWithRightChild(k3.left);
        System.out.println("\n*Double with Left Child: ");
        tp.printAVLLevelOrder(6, root); 
        return rotateWithLeftChild(k3);
    }

    private AvlNode doubleWithRightChild(AvlNode k1)
    {
        k1.right = rotateWithLeftChild(k1.right);
        System.out.println("\n*Double with Right Child: ");
        tp.printAVLLevelOrder(6, root); 
        return rotateWithRightChild(k1);
    }
    
    class UnderflowException extends Exception 
    {
        public String UnderFlowException(String message)
        {
            return "UnderFlowException occurs!";
        }
    }
}