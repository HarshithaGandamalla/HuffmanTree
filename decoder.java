import java.io.*;
import java.util.*;

class decoderNode{
	int data;
	String code;
	decoderNode left;
	decoderNode right;
	public decoderNode() {
		data=-1;
		code="";
		left=null;
		right=null;
	}
}

class Huffman{
	decoderNode root;

	public Huffman() {
		root=new decoderNode();
	}

	void build_decoder_tree(ArrayList<decoderNode> decoderNode){
		for(int i=0;i<decoderNode.size();i++){
			decoderNode node=this.root;
			String code=decoderNode.get(i).code;
			for(int j=0;j<code.length();j++){
				if(code.charAt(j)=='0'){
					if(node.left == null)  
						node.left = new decoderNode();
					node=node.left;
				}
				else {
					if(node.right == null) 
						node.right=new decoderNode();
					node=node.right;
				}
			}
			node.data=decoderNode.get(i).data;
		}
	}

	void decoder(String file) throws IOException{
		FileInputStream fis=new FileInputStream(file);
		FileWriter fw = new FileWriter("decoded.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		byte[] buffer=null;
		buffer=new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<buffer.length;i++){
			byte b1 = buffer[i];
			String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
			sb.append(s1);
		}
		
		decoderNode temp=this.root;
		long l=sb.length();
		for(int i = 0; i <= l; i++){
			if(temp.data != -1){
				i--;
				StringBuilder st = new StringBuilder();
				st.append(temp.data+"\n");
				bw.write(st.toString());
				temp=this.root;
				
			}
			else if (i!=l){
				if(sb.charAt(i)=='0')
					temp=temp.left;
				else temp=temp.right;
			}
		}
	bw.close();
	}
}



public class decoder {

	  public static void main(String args[]) throws IOException{
		   long startTime = System.currentTimeMillis();
	  	   Huffman h=new Huffman();
		   String binary=args[0];
		   FileInputStream fis = null;
		   fis = new FileInputStream(args[1]);
           BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(fis)));
           ArrayList<decoderNode> decoderNodes=new ArrayList<>();
		   String str;
	        
				while ((str= br.readLine()) != null)   {
				         String[] sub = str.split(" ");
						 decoderNode root=new decoderNode();
						 root.code=sub[1];
						 root.data=Integer.parseInt(sub[0]);
						 decoderNodes.add(root);
                   }
				
				br.close();	

			h.build_decoder_tree(decoderNodes);
			h.decoder(binary);
			
			long stopTime = System.currentTimeMillis();
			System.out.println("The time taken to decode using fourway heap structure"+(stopTime-startTime)+" MilliSec");


	}

}



