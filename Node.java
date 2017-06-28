public class Node {
	long freq;
    int key;
	public Node right;
	public Node left;

    public Node(int key, Long val){
        this.key=key;
        this.freq=val;
    }
    public Node(Long val){
        this.freq=val;
        this.key=-1;
    }

}
