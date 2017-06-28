
import java.util.*;
import java.util.Map.Entry;
public class BinaryHeap{


    public ArrayList<Node> myHeap;
    public int leftChild(int current)
    {
        return 2*current+1;
    }

    public int RightChild(int current)
    {
        return 2*current+2;
    }

    public int parent(int current){
        return (current-1)/2;
    }

    public int getHeapSize(){
        return myHeap.size();
    }



    public void heapify(int current)
    {
        int i=current;
        int left=leftChild(i);
        int right=RightChild(i);


        if(left<getHeapSize() && myHeap.get(left).freq<=myHeap.get(i).freq){
            i=left;
        }

        if(right<getHeapSize() && myHeap.get(right).freq<=myHeap.get(i).freq){
            i=right;
        }
        if(i!=current)
        {
            Collections.swap(myHeap,i,current);
            heapify(i);
        }
    }
public void insert(Node node){
	 myHeap.add(node);
     int curr=getHeapSize()-1;
     int Current_parent=parent(curr);
     while(curr>0 && myHeap.get(curr).freq<myHeap.get(Current_parent).freq)
     {
         Collections.swap(myHeap,curr,Current_parent);
         curr=Current_parent;
         Current_parent=parent(curr);

     }
}


    public Node binaryRemove()
    {
        Node d=null;
        if(getHeapSize()>0)
        {
            d=myHeap.get(0);
            if(getHeapSize()>1)
            {
            	int temp=getHeapSize()-1;
                myHeap.set(0,myHeap.remove(temp));
                heapify(0);
            }
            else{
            	myHeap.remove(0);
            }

        }
        return d;

    }

    public void Binary_Huffman_Tree(HashMap<String,Long> hm){
        Node left;
        Node right;
        Node newNode;
        myHeap=new ArrayList<Node>();
        for(Entry<String,Long>entry: hm.entrySet()){
            int x=Integer.parseInt(entry.getKey());
            Long b=entry.getValue();
            Node d=new Node(x,b);
                myHeap.add(d);

        }
        for(int i=(getHeapSize()/2)-1;i>=0;i--)
            heapify(i);
        while(myHeap.size()!=1){
            left=binaryRemove();
            right=binaryRemove();
            newNode=new Node(left.freq+right.freq);
            newNode.left=left;
            newNode.right=right;
            insert(newNode);
    }
    }
}



