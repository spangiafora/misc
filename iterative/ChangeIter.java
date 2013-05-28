import java.util.*;

public class ChangeIter
{
  public static void main(String [] args)
  {  
    ChangeIter t = new ChangeIter();
    t.test();
  }

  public void test()
  {
    int amount = 100;
    List<Integer> coins = new LinkedList<>();

    coins.add(1);
    coins.add(5);
    coins.add(10);
    coins.add(25);
    coins.add(50);

    List<List<Node>> t = mkTree(amount, coins);
    int count = 0;

    for(List<Node> ln : t)
      for(Node n : ln)
        if (n.amount == 0)
          count++;

    System.out.printf("%d\n", count);
  }

  /** 
   * Create the tree containing the values for the
   * change problem.  Return the tree as a list of levels.
   */
  private List<List<Node>> mkTree(int amount, List<Integer>coins)
  {
    Node root = new Node(amount, coins);

    List<Node> curlevel = new LinkedList<>();
    List<List<Node>> allLevels = new LinkedList<>();

    curlevel.add(root);
    do
    {
      allLevels.add(curlevel);
      curlevel = mklevel(curlevel);
    } while(curlevel.size() > 0);

    return allLevels;
  }

  /**
   * Get the next level of the tree
   */ 
  List<Node> mklevel(List<Node> ln)
  {
    List<Node> val = new LinkedList<>();

    for(Node n : ln)
    {
      if (testNode(n))
      {
        n.left  = new Node(n.amount, tail(n.coins));
        n.right = new Node(n.amount - head(n.coins), n.coins);
        val.add(n.left);
        val.add(n.right);
      }
    }
    return val;
  }

  /**
   * Is this an internal node?  (i.e. not a leaf)
   */ 
  boolean testNode(Node n)
  {
    return (n.amount > 0) && (n.coins.size() > 0);
  }
 
  /**
   * Get the tail of a list
   */
  public List<Integer> tail(List<Integer> l)
  {
    return l.subList(1, l.size());
  }

  /**
   * Get the head of a list
   */
  public Integer head(List<Integer> l)
  {
    return l.get(0);
  }
}

class Node
{
  int amount;
  List<Integer> coins;
  Node left;
  Node right;

  public Node(int a, List<Integer> c)
  {
    this(a, c, null, null);
  }

  public Node(int a, List<Integer> c, Node l, Node r)
  {
    amount = a;
    coins = c;
    left = l;
    right = r;
  }

  public String toString()
  {
    StringBuilder b = new StringBuilder();
    b.append("(Node " + amount + " " + coins + ")");
    if (left != null)
    {
      b.append(" [" + Integer.toString(left.amount));
      b.append(" "  + left.coins.toString());
      b.append("] ");
    }
    if (right != null)
    {
      b.append(" [" + Integer.toString(right.amount));
      b.append(" "  + right.coins.toString());
      b.append("] ");
    }
    b.append(")");
    return b.toString();
  }
}

