/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max=Integer.MIN_VALUE;
        for(String s:asciis){
            if(s.length()>max)
                max=s.length();
        }
        for(int i=max-1;i>=0;i--){
            sortHelperLSD(asciis,i);
            /**for(String s:asciis){
                System.out.print(s+" ");
            }
            System.out.println();*/
        }
        return asciis;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static char char_extract(String s,int index){
        if(s.length()-1<index)
            return s.charAt(s.length()-1);
        else{
            return s.charAt(index);
        }
    }
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] num=new int[300];
        for(int i=0;i<num.length;i++)
            num[i]=0;
        for(String s:asciis){
            num[char_extract(s,index)]++;
        }
        int[] start=new int[300];
        int pos=0;
        for(int i=0;i<num.length;i++){
            start[i]=pos;
            pos+=num[i];
        }
        String[] newAsciis=new String[asciis.length];
        for(int i=0;i<asciis.length;i++){
            String item=asciis[i];
            int place=start[char_extract(item,index)];
            start[char_extract(item,index)]++;
            newAsciis[place]=item;
        }
        for(int i=0;i<newAsciis.length;i++){
            asciis[i]=newAsciis[i];
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] args){
        String[] asciis = {
                "z3aB",
                "apple",
                "XyZ",
                "hello123",
                "CAT",
                "b2",
                "banana",
                "MANGO",
                "qwerty",
                "A1B2C3"
        };
        sort(asciis);
        for(String s:asciis){
            System.out.println(s);
        }
    }
}
