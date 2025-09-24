public class TrieSet {
    private static final int R=30;
    public class Node{
        boolean exists;
        Node[] links;
        public Node(){
            links=new Node[R];
            exists=false;
        }
    }
    public TrieSet(String[] dict){
        for(String str:dict){
            put(str);
        }
    }
    private Node root =new Node();
    public Node getRoot(){
        return root;
    }
    public void put(String key){
        put(root,key,0);
    }
    private int transfer(char c){
        if(c=='\'') return 0;
        else if(c>='a'&& c<='z') return (c-'a'+1);
        else return ('z'-'a'+2);
    }
    private Node put(Node x,String key,int d){
        if(x==null){
            x=new Node();
        }
        if(d==key.length()){
            x.exists=true;
            return x;
        }
        char c = key.charAt(d);
        int idx = transfer(c);
        x.links[idx]=put(x.links[idx],key,d+1);
        return x;
    }
    public int find(String key){
        Node x = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = transfer(key.charAt(i));
            if (x.links[idx] == null) {
                return -1;
            }
            x = x.links[idx];
        }
        if(x.exists==true) return 1;
        else return 0;
    }
    //1.首先可以从上一个父母的地方开始继续，这样可以节省一点点的时间
}