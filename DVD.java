// Tera Parish
// txp200011

public class DVD implements Comparable<DVD>
{
    // members
    private String title;
    private int available;
    private int rented;

    // constructors
    public DVD(){}
    public DVD(String t, int a, int r)
    {
        title= t;
        available= a;
        rented= r;
    }

    // getters
    public String getTitle(){return title;}
    public int getAvailable(){return available;}
    public int getRented(){return rented;}

    // setters
    public void setTitle(String title_){title= title_;}
    public void setAvailable(int available_){available= available_;}
    public void setRented(int rented_){rented= rented_;}

    // toString
    @Override
    public String toString()
    {
        return title+ " "+ available+ " "+ rented;
    }

    @Override
    public int compareTo(DVD o)
    {
        //System.out.println(this.title.compareTo(o.getTitle()));
        return this.title.compareTo(o.getTitle());
    }
}
