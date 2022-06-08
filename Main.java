// Tera Parish
// txp200011

import java.util.Scanner;
import java.io.*;

public class Main
{
    public static void main (String [] args) throws IOException
    {
        // create a tree variable
        BinTree<DVD> tree= new BinTree<DVD>();
        // console in
        Scanner scnr= new Scanner(System.in);
        // prompt for file names
        System.out.print("Enter inventory file name: ");
        String inventory= scnr.nextLine();
        System.out.print("Enter transaction log name: ");
        String logFile= scnr.nextLine();
        // open inventory file
        File fInventory= new File(inventory);
        File fLog= new File(logFile);

        // check if file can be read
        if (fInventory.canRead())
        {
            Scanner inFile= new Scanner(fInventory);
            // read till end of the file
            while (inFile.hasNext())
            {
                // read in data line by line
                String s= inFile.nextLine();
                // build the tree
                insertTree(s, tree);
            }
            inFile.close();
        }

        if (fLog.canRead())
        {
            Scanner inLog= new Scanner(fLog);
            // read till end of the file
            while (inLog.hasNext())
            {
                // read in data line by line
                String log= inLog.nextLine();
                String log_name= log.substring(log.indexOf("\"")+1, log.indexOf("\"", 8));
                DVD temp= new DVD(log_name,0,0);

                // found in the tree
                if (tree.search(tree.getRoot(), temp)!= null)
                {
                    DVD target= tree.search(tree.getRoot(), temp).getPayload();
                    if (log.contains("add"))
                    {
                        int num= Integer.parseInt(log.substring(log.indexOf(",")+1));
                        target.setAvailable(target.getAvailable()+num);
                    }
                    else if (log.contains("rent"))
                    {
                        target.setAvailable(target.getAvailable()-1);
                        target.setRented(target.getRented()+1);
                    }
                    else if (log.contains("return"))
                    {
                        target.setAvailable(target.getAvailable()+1);
                        target.setRented(target.getRented()-1);
                    }
                    else if (log.contains("remove"))
                    {
                        int num= Integer.parseInt(log.substring(log.indexOf(",")+1));
                        target.setAvailable(target.getAvailable()-num);
                        if (target.getAvailable()==0 && target.getRented()==0)
                            tree.delete(target);
                    }
                }

                // not found, add to the tree
                else
                {
                    Node<DVD> add= new Node<>(temp);
                    int num= Integer.parseInt(log.substring(log.indexOf(",")+1));
                    tree.insert(add);
                    add.getPayload().setAvailable(num);
                }
            }
            inLog.close();
        }
        tree.inorder(tree.getRoot());
    }

    public static void insertTree(String s, BinTree t)
    {
        // string parsing
        int idx= s.indexOf(",");
        String name= s.substring(1,idx-1);
        int a= Integer.parseInt(s.substring(idx+1,s.indexOf(",", idx+1)));
        int r= Integer.parseInt(s.substring(s.indexOf(",", idx+1)+1));
        Node<DVD> n= new Node<>(new DVD(name, a, r));
        // add to the tree
        t.insert(n);
    }
}