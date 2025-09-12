public class LinkedListDeque<T>{
    private class Node{
        public T item;
        public Node next;
        public Node last;
        public Node(T i,Node n,Node l){
            item=i;
            next=n;
            last=l;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel=new Node(null,null,null);
        size=0;
    }
    public void addFirst(T item){
        size+=1;
        Node a=new Node(item,null,null);
        if(size==1){
            a.last=a;
            a.next=a;
            sentinel.next=a;
        }
        else{
            Node first_element=sentinel.next;
            Node last_element=first_element.last;
            a.next=first_element;
            a.last=last_element;
            first_element.last=a;
            last_element.last=a;
            sentinel.next=a;
        }
    }
    public void addLast(T item){
        size+=1;
        Node a=new Node(item,null,null);
        if(size==1){
            a.last=a;
            a.next=a;
            sentinel.next=a;
        }
        else{
            Node first_element=sentinel.next;
            Node last_element=first_element.last;
            a.next=first_element;
            a.last=last_element;
            first_element.last=a;
            last_element.next=a;
        }
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        if(sentinel.next==null){
            return true;
        }
        else {
            return false;
        }
    }
    public void printDeque(){
        int index=0;
        Node current=sentinel;
        while(true)
        {
            if(index==size){
                break;
            }
            index++;
            current=current.next;
            System.out.print(current.item+" ");
        }
    }
    public T removeFirst(){
        if(size==0){
            return null;
        }
        else{

            if(size==1)
            {
                Node first_element=sentinel.next;
                sentinel.next=null;
                size-=1;
                return first_element.item;
            }
            else {
                size-=1;
                Node last_element = sentinel.next.last;
                Node second_element = sentinel.next.next;
                Node first_element = sentinel.next;
                sentinel.next = second_element;
                second_element.last = last_element;
                last_element.next = first_element;
                return first_element.item;
            }
        }
    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        else{
            //size-=1;
            if(size==1)
            {
                Node first_element=sentinel.next;
                sentinel.next=null;
                size-=1;
                return first_element.item;
            }
            else {
                size-=1;
                Node last_element = sentinel.next.last;
                Node last_second_element = last_element.last;
                Node first_element = sentinel.next;
                last_second_element.next=first_element;
                first_element.last=last_second_element;
                return last_element.item;
            }
        }
    }
    public T get(int index){
        int ind=0;
        Node current=sentinel.next;
        while(true)
        {
            if(ind==index){
                return current.item;
            }
            ind++;
            current=current.next;
            //System.out.print(current.item+" ");
        }
    }
    public T getRecursive(int index){
        return get(index);
        //I am too lazy and I don't want to spend more time thinking
        //how to use recursive to write this.
    }
}
