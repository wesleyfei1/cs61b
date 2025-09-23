public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        //text.charAt(i);
        Deque<Character> d = new LinkedListDeque<Character>();
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            d.addLast(c);
        }
        return d;
    }
    public boolean isPalindrome(String word){
        System.out.print(1);
        Deque<Character> d=wordToDeque(word);
        Deque<Character> t=wordToDeque(word);
        Deque<Character> n=new LinkedListDeque<Character>();
        while(!d.isEmpty()){
            n.addLast(d.removeLast());
        }
        for(int i=0;i<n.size();i++){
            if(n.get(i)!=t.get(i))
            {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        //System.out.print(1);
        Deque<Character> d=wordToDeque(word);
        Deque<Character> t=wordToDeque(word);
        Deque<Character> n=new LinkedListDeque<Character>();
        while(!d.isEmpty()){
            n.addLast(d.removeLast());
        }
        for(int i=0;i<n.size();i++){
            if(cc.equalChars(n.get(i),t.get(i))==false)
            {
                return false;
            }
        }
        return true;
    }
}
