package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] x;
    private int times;
    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N<=0 || T<=0){
            throw new IllegalArgumentException("Illegal argument exception");
        }
        times=T;
        x=new double[T];
        for(int i=0;i<T;i++) {
            Percolation f=pf.make(N);
            double xt;
            StdRandom.setSeed(1);
            while (true) {
                int choose_row = StdRandom.uniform(0,N);
                int choose_col = StdRandom.uniform(0,N);
                if (!f.isOpen(choose_row, choose_col)) {
                    f.open(choose_row, choose_col);
                    if (f.percolates()) {
                        xt = N * N *1.0/ f.numberOfOpenSites();
                        break;
                    }
                }
            }
            x[i]=xt;
        }
    }
    public double mean(){
        double mean= StdStats.mean(x);
        return mean;
    }
    public double stddev(){
        double dev=StdStats.stddev(x);
        return dev;
    }
    public double confidenceLow(){
        return mean()-1.96*stddev()/Math.sqrt(times);
    }
    public double confidenceHigh(){
        return mean()+1.96*stddev()/Math.sqrt(times);
    }
}
