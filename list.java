
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
				}

				listnode = listnode.next;
			
			}
			thisnode = thisnode.next;
		}
		return filterlist;
	}

	public List mergeWith(List list) {
		List copiedthis, copiedlist = new List();
		copiedthis=this.copy(); copiedlist=list.copy(); //αντιγραφω τις 2 λιστες για να συγκρινω τα nodes τους και αν τα βαλω σε αυξουσα σειρα σε μια νεα λιστα με ονομα mergedlist
		insertionSort(copiedlist); //επειτα χρησιμοποιω την insertionSort που υλοποιησα ως μεθοδο για να ταξινομησω τα αντιγραφα των λιστων σε αυξουσα σειρα για να κανω την συγκριση πιο ευκολη 
		insertionSort(copiedthis);
		copiedthis.print();
		copiedlist.print();
		List mergedlist = new List();
		Node current = copiedthis.head.next;
		Node currentlist = copiedlist.head.next;
		while (current != null && currentlist!=null){
			if (current.key() <= currentlist.key()){ //συγκρινω τα nodes μεταξυ τους 
				
				mergedlist.insertFirst(current.key()); //εισχωρω στην mergedlist το κλειδι του current αν ο current ειναι οντως μικροτερος
				current.prev.next=current.next;
				if (current.next != null){ //αλλαζουμε το pointer του επομενου απο τον προηγουμενο selected node να ειναι ο επόμενος node απο τον selected (selected = current)
					current.next.prev=current.prev;
				}
			
				current=current.next;
			}else{
				
				mergedlist.insertFirst(currentlist.key());
				currentlist.prev.next=currentlist.next;
				if (currentlist.next!=null){
					currentlist.next.prev=currentlist.prev;
				}
				
				currentlist=currentlist.next;
			}
		}
		while(current!=null){
			mergedlist.insertFirst(current.key()); //εισχωρω στην mergedlist το κλειδι του current αν ο current ειναι οντως μικροτερος
				current.prev.next=current.next;
				if (current.next != null){ //αλλαζουμε το pointer του επομενου απο τον προηγουμενο selected node να ειναι ο επόμενος node απο τον selected (selected = current)
					current.next.prev=current.prev;
				}
				
				current=current.next;
			
		}
		while(currentlist!=null){
			mergedlist.insertFirst(currentlist.key());
				currentlist.prev.next=currentlist.next;
				if (currentlist.next!=null){
					currentlist.next.prev=currentlist.prev;
				}
				
				currentlist=currentlist.next;
		}
		insertionSort(mergedlist);
		return mergedlist;
	}

	public List largest(int k) {
		List largest = new List();
		Node current = this.head.next;
		while(current!=null){
			if (current.key() >= k){
				largest.insertFirst(current.key());
				current=current.next;
			}else{
				current=current.next;
			}
		}
		return largest;
	}

	public static void insertionSort(List list) {	
		Node currentInsertion = list.head.next;
		while(currentInsertion!=list.tail){ //με αυτην την λουπα θα κανω οσες συγκρισεις οσα ειναι και τα nodes της λιστας για να ταξινομηθουν ολα 
			Node temp = currentInsertion;
			Node checking = currentInsertion.prev; //φτιαχνω αυτο το node για συγκρισεις με τα προηγουμενα node keys απο αυτον που συγκρινω
			while(checking!=list.head && currentInsertion!=null && temp.key() < checking.key()){  //συγκρινω αμα το παρων node ειναι μικροτερο απο το πορηγουμενο του 
				temp.prev.next=temp.next;
				if (temp.next != null) {
					temp.next.prev = temp.prev;
				}
				temp.prev=checking.prev;
				checking.prev.next=temp;
				temp.next=checking;
				checking.prev=temp;
				checking=temp.prev;//κανω τις απαραιτητες πραξεις για να ολισθησω την λιστα καθε φορα που ο κομβος που ελεγχω ειναι μικροτερος απο τον προηγμουμενο 
				
			}
				currentInsertion=currentInsertion.next;
		}
	}

	public static void main(String[] args) {
		    List list2 = new List();
	        List list = new List();
	        List fl = new List();
	    	Random rand=new Random();

	        // Insert elements into the list
	       for (int i=0; i<=4; i++) {
			int random = rand.nextInt(15);
	    	   list.insertFirst(random);
	       }
	       
	       //Insert elements into the list2
	       for (int i=0; i<=4; i++) {
				int random = rand.nextInt(15);
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

			System.out.println("Insertion Sorted list:");
			insertionSort(list);
			list.print();

			System.out.println("The merged list one;");
			list.mergeWith(list2).print();

			System.out.println("The list with the bigger k elements is :");
			list.print();
			list.largest(5).print();
			}
}
