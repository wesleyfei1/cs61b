package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int sum=0,n=0;
        int[] hashes=new int[M];
        for(Oomage omage : oomages){
            int bucketNum=(omage.hashCode() & 0x7FFFFFFF) % M;
            sum++;
            hashes[bucketNum]++;
        }
        double up=(double)sum*1.0/2.5;
        double down=(double)sum*1.0/50;
        for(int i=0;i<M;i++){
            if(hashes[i]<down || hashes[i]>up){
                return false;
            }
        }
        System.out.println("sum="+sum);
        System.out.println("M:"+M);
        return true;
    }
}
