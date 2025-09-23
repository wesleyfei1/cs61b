public class OffByN implements CharacterComparator {
    private int nu;
    public OffByN(int n){
        nu=n;
    }
    public boolean equalChars(char x,char y) {
        int diff=Math.abs(x-y);
        if(diff==nu) return true;
        return false;
    }
}
