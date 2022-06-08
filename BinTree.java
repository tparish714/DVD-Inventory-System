// Tera Parish
// txp200011

public class BinTree <T extends Comparable<T>>
{
    // member
    private Node<T> root;

    // constructors
    public BinTree(){}
    public BinTree(Node<T> t){root= t;}

    // getter
    public Node<T> getRoot(){return root;}

    // setter
    public void setRoot(Node<T> t){root= t;}


    // insert helper
    public void insert(Node<T> n){root= insertR(root, n);}
    // insert recursively
    public Node<T> insertR(Node<T> r, Node<T> newNode)
    {
        // empty tree: return a new node
        if (r==null)
            return newNode;

        // compare the nodes
        int comp= newNode.compareTo(r);
        if (comp<0)
            r.setLeft(insertR(r.getLeft(), newNode));
        else if (comp>0)
            r.setRight(insertR(r.getRight(), newNode));

        return r;
    }

    // search
    public Node<T> search(Node<T> r, T find)
    {
        // empty tree
        if (r== null)
            return null;

        int comp= r.getPayload().compareTo(find);
        // found at root
        if (comp==0)
            return r;
        // value is smaller; move to left
        else if (comp>0)
            return search(r.getLeft(),find);
        // value is bigger; move to right
        else
            return search(r.getRight(), find);
    }

    // get the smallest value in the right subtree
    public Node<T> findSuccessor(Node<T> rr)
    {
        Node<T> successor= rr;
        if (rr.getLeft()==null)
            return successor;

        return findSuccessor(rr.getLeft());
    }

    //delete helper
    public void delete(T dvd){root= deleteR(root, dvd);}
    // delete recursively
    public Node<T> deleteR(Node<T> r, T find)
    {
        // empty tree
        if (r== null)
            return null;

            // go down the tree recursively
            // search for the node
        else
        {
            // compare values
            int comp= find.compareTo(r.getPayload());
            // find< node, check left subtree
            if (comp<0)
                r.setLeft(deleteR(r.getLeft(), find));
                // find> node, check right subtree
            else if (comp>0)
                r.setRight(deleteR(r.getRight(), find));
                // matched
            else
            {
                // a leaf node
                if (r.getRight()==null && r.getLeft()==null)
                    r= null;

                    // has two children
                else if (r.getRight()!=null && r.getLeft()!=null)
                {
                    Node<T> min= findSuccessor(r.getRight());
                    r.setPayload(min.getPayload());
                    r.setRight(deleteR(r.getRight(), min.getPayload()));
                }

                // has one child
                else
                {
                    // has right child, r= r.right
                    // has left child, r= r.left
                    r= (r.getRight()!=null)? r.getRight(): r.getLeft();
                }
            }
        }
        return r;
    }

    // display the tree in order (A-Z)
    public void inorder(Node<T> r)
    {
        if (r== null)
            return;

        inorder(r.getLeft());
        System.out.println(r.getPayload().toString());
        inorder(r.getRight());
    }
}
