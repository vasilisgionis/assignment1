import java.util.Random;

class Node {
	public Node next;
	public Node prev;
	private int key;

	public Node(int key) {
		this.key = key;
	}

	public int key() {
		return this.key;
	}

	public void print(String pre, String post) {
		System.out.print(pre + this.key + post);
	}
}

class List {
	private Node head;
	private Node tail;

	public List() {
		head = new Node(0);
		tail = new Node(0);
		head.next = tail;
		tail.prev = head;
	}

	public void insertFirst(int key) {
		Node tmp = new Node(key);

		tmp.prev = head;
		tmp.next = head.next;

		head.next = tmp;
		tmp.next.prev = tmp;
	}

	public List copy() {
		List list = new List();
		Node node = this.tail.prev;
		while(node != this.head) {
			list.insertFirst(node.key());
			node = node.prev;
		}
		return list;
	}

	public void print() {
		Node current = this.head.next;
		System.out.print("[");
		while(current != this.tail) {
		       	current.print(" ", ",");
			current = current.next;
		}
		System.out.println(" ]");
	}

	public List filter(List list) {
		List filterlist = new List();
		Node thisnode=this.head.next;
		while (thisnode!=this.tail){
			Node listnode=list.head.next;
			while (listnode!=list.tail) {
				if (thisnode.key()==listnode.key()) {
					filterlist.insertFirst(thisnode.key());
					break;
				}else{
					listnode = listnode.next;
				}
			}
			thisnode = thisnode.next;
		}
		return filterlist;
	}

	//public List mergeWith(List list) {
		/* your code here */
	//}

	//public List largest(int k) {
		/* your code here */
	//}

	//public static void insertionSort(List list) {
		/* your code here */
	//}

	public static void main(String[] args) {
		    List list2 = new List();
	        List list = new List();
	        List fl = new List();
	    	Random rand=new Random();

	        // Insert elements into the list
	       for (int i=0; i<=4; i++) {
			int random = rand.nextInt(10);
	    	   list.insertFirst(random);
	       }
	       
	       //Insert elements into the list2
	       for (int i=0; i<=4; i++) {
				int random = rand.nextInt(10);
		    	   list2.insertFirst(random);
		       }
	  
	        // Print the contents of both lists
	        System.out.println("list:");
	        list.print();

	        System.out.println("list2:");
	        list2.print();
	        
	        System.out.println("Filtered list:");
	        fl=list.filter(list2);
	        fl.print();
	        
	}
}

