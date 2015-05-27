
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author CherylRuo
 */
public class TreePrint {
    
    ArrayList<Integer> randomNums = new ArrayList<Integer>();
    
    public ArrayList<Integer> randomNo(int lowerLimit, int upperLimit, int numOfNodes)
    {
        int randomNo;
        while(randomNums.size() < numOfNodes)
        {
            randomNo = lowerLimit + (int)(Math.random() * (upperLimit - lowerLimit));
            if(!randomNums.contains(randomNo))
            randomNums.add(randomNo);
        }
        return randomNums;        
    }
     
    public void printBSTLevelOrder(int depth, BSTNode root) 
    {
        for (int i = 1; i <= depth; i++) 
        {
            System.out.print("Level " + (i-1) + ": ");
            String levelNodes = printBSTLevel(root, i);
            System.out.print(levelNodes + "\n");
        }
    }

    public String printBSTLevel(BSTNode node, int level) {
        if (node == null)
            return "";
        if (level == 1)
            return node.data + " ";
        else if (level > 1) 
        {
            String leftStr = printBSTLevel(node.left, level - 1);
            String rightStr = printBSTLevel(node.right, level - 1);
            return leftStr + rightStr;
        }
        else
          return "";
    }
    
    public void printAVLLevelOrder(int depth, AvlNode root) 
    {
        for (int i = 1; i <= depth; i++) 
        {
            System.out.print("Level " + (i-1) + ": ");
            String levelNodes = printAVLLevel(root, i);
            System.out.print(levelNodes + "\n");
        }
    }

    public String printAVLLevel(AvlNode node, int level) {
        if (node == null)
            return "";
        if (level == 1)
            return node.element + " ";
        else if (level > 1) 
        {
            String leftStr = printAVLLevel(node.left, level - 1);
            String rightStr = printAVLLevel(node.right, level - 1);
            return leftStr + rightStr;
        }
        else
          return "";
    }
    
    public void printSplayLevelOrder(int depth, SplayNode root) 
    {
        for (int i = 1; i <= depth; i++) 
        {
            System.out.print("Level " + (i-1) + ": ");
            String levelNodes = printSplayLevel(root, i);
            System.out.print(levelNodes + "\n");
        }
    }

    public String printSplayLevel(SplayNode node, int level) {
        if (node == null)
            return "";
        if (level == 1)
            return node.element + " ";
        else if (level > 1) 
        {
            String leftStr = printSplayLevel(node.left, level - 1);
            String rightStr = printSplayLevel(node.right, level - 1);
            return leftStr + rightStr;
        }
        else
          return "";
    }
    public static void main(String[] args) throws FileNotFoundException
    {
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        TreePrint tp = new TreePrint();

        Scanner in = null;
        File file = new File("src/input.txt");
        try 
        {
            in = new Scanner(file);
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(TreePrint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<String> inArray = new ArrayList<>();
        while(in.hasNextLine())
        {
            inArray.add(in.nextLine());
        }
        
        String[] line1 = inArray.get(0).split(":");
        int lowerLimit = Integer.valueOf(line1[1].split(" ")[1]);
        
        String[] line2 = inArray.get(1).split(":");
        int upperLimit = Integer.valueOf(line2[1].split(" ")[1]);
        
        String[] line3 = inArray.get(2).split(":");
        int numOfNodes = Integer.valueOf(line3[1].split(" ")[1]);
        
        String[] line4 = inArray.get(3).split(":");
        String[] line4Split = line4[1].split(" ");
        
        String[] line5 = inArray.get(4).split(":");
        String[] line5Split = line5[1].split(" ");
        
        String[] line6 = inArray.get(5).split(":");
        String[] line6Split = line6[1].split(" ");
        
        BST bst = new BST(lowerLimit, upperLimit);
        AvlTree avlt = new AvlTree(lowerLimit, upperLimit);
        SplayTree splayt = new SplayTree(lowerLimit, upperLimit);
        
        tp.randomNo(lowerLimit, upperLimit, numOfNodes);
        
        System.out.println("Random numbers: ");        
        System.out.println(tp.randomNums);

        for(int i=0; i<tp.randomNums.size(); i++)
        {
            System.out.println("\n**Binary search tree insertion chart: ");
            bst.add(tp.randomNums.get(i));
            //System.out.println("\n");
            tp.printBSTLevelOrder(6, bst.getBSTRoot());
            
            System.out.println("\n**AVL tree insertion chart: ");
            avlt.insert(tp.randomNums.get(i));
            //System.out.println("\n");
            tp.printAVLLevelOrder(6, avlt.getAvlRoot());

            System.out.println("\n**Splay tree insertion chart: ");            
            splayt.insert(tp.randomNums.get(i));
            //System.out.println("\n");
            tp.printSplayLevelOrder(6, splayt.getSplayRoot());
        }

        
        System.out.println("\n***Binary Search Tree Chart: ");
        tp.printBSTLevelOrder(6, bst.getBSTRoot());

        System.out.println("\n*BST chart after deleting 22 (in range): ");
        int bstRemove1 = Integer.valueOf(line4Split[1]);
        bst.remove(bstRemove1);
        tp.printBSTLevelOrder(6, bst.getBSTRoot());

        System.out.println("\n*BST chart after deleting 51 (in range): ");
        int bstRemove2 = Integer.valueOf(line4Split[2]);
        bst.remove(bstRemove2);
        tp.printBSTLevelOrder(6, bst.getBSTRoot());
        
        System.out.println("\n*BST chart after deleting 120 (out of range): ");        
        int bstRemove3 = Integer.valueOf(line4Split[3]);
        bst.remove(bstRemove3);
        tp.printBSTLevelOrder(6, bst.getBSTRoot());
        
        System.out.println("\n*BST chart after deleting 9 (out of range): ");      
        int bstRemove4 = Integer.valueOf(line4Split[4]);
        bst.remove(bstRemove4);
        tp.printBSTLevelOrder(6, bst.getBSTRoot());
        
        
        System.out.println("\n***AVL tree Chart:");
        tp.printAVLLevelOrder(6, avlt.getAvlRoot());

        System.out.println("\n*AVL tree chart after deleting 22 (in range): ");        
        int avlRemove1 = Integer.valueOf(line5Split[1]);
        avlt.remove(avlRemove1);
        tp.printAVLLevelOrder(6, avlt.getAvlRoot());

        System.out.println("\n*AVL tree chart after deleting 51 (in range): ");        
        int avlRemove2 = Integer.valueOf(line5Split[2]);
        avlt.remove(avlRemove2);
        tp.printAVLLevelOrder(6, avlt.getAvlRoot());

        System.out.println("\n*AVL tree chart after deleting 120 (out of range): ");        
        int avlRemove3 = Integer.valueOf(line5Split[3]);
        avlt.remove(avlRemove3);
        tp.printAVLLevelOrder(6, avlt.getAvlRoot());

        System.out.println("\n*AVL tree chart after deleting 9 (out of range): ");       
        int avlRemove4 = Integer.valueOf(line5Split[4]);
        avlt.remove(avlRemove4);        
        tp.printAVLLevelOrder(6, avlt.getAvlRoot());
        
        
        System.out.println("\n***Splay tree Chart:");
        tp.printSplayLevelOrder(6, splayt.getSplayRoot());

        System.out.println("\n*Splay tree chart after deleting 22 (in range): ");        
        int splayRemove1 = Integer.valueOf(line6Split[1]);
        splayt.remove(splayRemove1);
        tp.printSplayLevelOrder(6, splayt.getSplayRoot());
        System.out.println("\n*Splay tree chart after deleting 51 (in range): ");        
        int splayRemove2 = Integer.valueOf(line6Split[2]);
        splayt.remove(splayRemove2);        
        tp.printSplayLevelOrder(6, splayt.getSplayRoot());

        System.out.println("\n*Splay tree chart after deleting 120 (out of range): ");        
        int splayRemove3 = Integer.valueOf(line6Split[3]);
        splayt.remove(splayRemove3);        
        tp.printSplayLevelOrder(6, splayt.getSplayRoot());

        System.out.println("\n*Splay tree chart after deleting 9 (out of range)): ");        
        int splayRemove4 = Integer.valueOf(line6Split[4]);
        splayt.remove(splayRemove4);        
        tp.printSplayLevelOrder(6, splayt.getSplayRoot());
    }
}
