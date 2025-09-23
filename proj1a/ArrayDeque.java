public class ArrayDeque<T> {
    private T[] items;
    private int head;
    private int tail;
    private int size;
    public ArrayDeque(){
        items=(T[]) new Object[8];
        head=0;
        tail=1;
        size=0;
    }
    private void resize(){
        T[] newItems=(T[]) new Object[items.length+1];
        if(tail<=head){
            System.arraycopy(items,head,newItems,0,(items.length-head));
            System.arraycopy(items,0,newItems,items.length-head,tail);
        }
        else{
            System.arraycopy(items,head,newItems,0,(tail-head));
        }
        head=0;
        tail=size;
        items=newItems;
    }
    public void addFirst(T item){
        if(size==items.length){
            resize();
        }
        head=head-1;
        head=head%items.length;
        items[head-1]=item;
        size+=1;
    }
    public void addLast(T item){
        if(size==items.length){
            resize();
        }
        items[tail]=item;
        tail=tail+1;
        tail=tail%items.length;
        size+=1;
    }
    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int index=head;
        while(true){
            System.out.print(items[index]+" ");
            if(index==tail-1)
                break;
            index+=1;
            index=index%items.length;
        }
    }
    public T removeFirst(){
        T a=items[head];
        head+=1;
        size-=1;
        head=head%items.length;
        return a;
    }
    public T removeLast(){
        T a=items[tail-1];
        tail-=1;
        size-=1;
        tail=tail%items.length;
        return a;
    }
    public T get(int index){
        T a=items[((head+index)%items.length)];
        return a;
    }
}
