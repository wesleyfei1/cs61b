package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
    private class search_node implements Comparable<search_node>{
        public WorldState current;
        public int moves;
        search_node previous;
        public search_node(WorldState current){
            this.current=current;
            this.moves=0;
            this.previous=null;
        }
        public int value(){
            return moves+current.estimatedDistanceToGoal();
        }
        @Override
        public int compareTo(search_node other){
            return Integer.compare(this.value(),other.value());
        }
    }
    private search_node search_target;
    private WorldState Target;
    public Solver(WorldState initial){
        search_node p=new search_node(initial);
        MinPQ<search_node> pq=new MinPQ<>();
        pq.insert(p);
        Set<WorldState> visited=new HashSet<>();
        visited.add(initial);
        int pp=0;
        while(true){
            pp++;
            //System.out.println(pq.size());
            if(pq.isEmpty()){
                break;
            }

            search_node q=pq.delMin();
            //System.out.println(q.current.toString());
            visited.add(q.current);
            //System.out.println(pq.size());
            if(q.current.estimatedDistanceToGoal()==0){
                search_target=q;
                //System.out.println("111111111111111111111111111111");
                break;
            }
            else {
                //System.out.println(5);
                int sum=0;
                for(WorldState N:q.current.neighbors())
                {
                    sum++;
                    //System.out.println(visited.contains(N));
                    if(visited.contains(N)==false){
                        search_node q2=new search_node(N);
                        q2.moves=q.moves+1;
                        q2.previous=q;
                        //System.out.println(222);
                        pq.insert(q2);
                        //System.out.println(visited.contains(N));
                        visited.add(N);
                        //System.out.println(visited.contains(N));
                        //System.out.println(N.toString());
                    }
                }
            }
        }
        System.out.println(pp);
    }
    public int moves(){
        return search_target.moves;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> list=new ArrayList<WorldState>();
        search_node p=search_target;
        while(true){
            if(p==null) break;
            else {
                list.add(p.current);
                p=p.previous;
            }
        }
        return list;
    }
}
