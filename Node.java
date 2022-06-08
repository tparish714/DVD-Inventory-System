// Tera Parish
// txp200011

public class Node<G extends Comparable<G>> implements Comparable<Node<G>>
{
    // members
    private Node<G> left = null;
    private Node<G> right = null;
    private G payload;

    // constructors
    public Node(){}
    public Node(G p){payload= p;}

    // setters
    public void setLeft(Node<G> Left) {left = Left;}
    public void setRight(Node<G> Right) {right = Right;}
    public void setPayload(G Payload) {payload = Payload;}

    // getters
    public Node<G> getLeft() {return left;}
    public Node<G> getRight() {return right;}
    public G getPayload() {return payload;}

    // toString
    @Override
    public String toString()
    {
        return payload.toString();
    }

    @Override
    public int compareTo(Node<G> o)
    {
        return this.getPayload().compareTo(o.getPayload());
    }
}
