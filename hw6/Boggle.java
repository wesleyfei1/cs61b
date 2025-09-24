import java.util.*;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;


public class Boggle {
    
    // File path of dictionary file
    public String dictPath = "words.txt";
    public char[][] board;
    public String[] dict;
    public int height,width;
    public TrieSet dict_save;
    public HashSet<String> save_word;
    public PriorityQueue<String> que;
    public int restrict;

    /**
     * Solves a Boggle puzzle.
     * 
     * param k The maximum number of words to return.
     * param boardFilePath The file path to Boggle board file.
     * return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public void read(String boardFilePath){
        In read_dict=new In(dictPath);

        String[] dict=read_dict.readAllLines();
        In read_board =new In(boardFilePath);
        String[] board_save=read_board.readAllLines();
        height=board_save.length;
        width=board_save[1].length();
        for(int i=1;i<height;i++)
        {
            if(board_save[i].length()!=width)
            {
                throw new IllegalArgumentException("Wrong board input!!!");
            }
        }
        board=new char[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++){
                board[i][j]=board_save[i].charAt(j);
            }
        }
        dict_save=new TrieSet(dict);
        save_word=new HashSet<>();
        que = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (a.length() != b.length()) {
                    return Integer.compare(a.length(), b.length()); // 短的在前
                } else {
                    return a.compareTo(b); // 字典序小的在前
                }
            }
        });

    }
    public int compare(String a, String b) {
        if (a.length() != b.length()) {
            return Integer.compare(a.length(), b.length()); // 短的在前
        } else {
            return a.compareTo(b); // 字典序小的在前
        }
    }
    public int transfer_1D(int row,int col){
        return (row*width+col);
    }
    public int get_row(int index){
        return index/width;
    }
    public int get_col(int index){
        return index%width;
    }
    public boolean within_index(int row,int col){
        if(row>=0 && row<height && col>=0 && col<width) return true;
        return false;
    }
    public void search(int x, int y, boolean[] visited, StringBuilder path) {
        int cur = transfer_1D(x, y);
        if (visited[cur]) return;
       // System.out.println(path.toString());
        // 1. 把当前点加入路径
        visited[cur] = true;
        path.append(board[x][y]);

        String now = path.toString();
        int num = dict_save.find(now);

        if (num != -1) {
            // 2. 如果是完整单词
            if (num == 1 && !save_word.contains(now)) {
                save_word.add(now);
                if(que.size()>=restrict){
                    String o2=que.peek();
                    if(compare(o2,now)<0){
                        que.poll();
                        que.add(now);
                    }
                }
                else{
                    que.add(now);
                }
            }

            // 3. 向四个方向继续搜索
            int[] dx = {1, -1, 0, 0,1,-1,-1,1};
            int[] dy = {0, 0, 1, -1,1,-1,1,-1};
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (within_index(nx, ny)) {
                    search(nx, ny, visited, path);
                }
            }
        }

        // 4. 回溯：撤销当前点
        path.deleteCharAt(path.length() - 1);
        visited[cur] = false;
    }
    public List<String> solve(int k, String boardFilePath) {
        // YOUR CODE HERE
        restrict=k;
        if(k<=0)
            throw new IllegalArgumentException("k must be greater than 0");
        read(boardFilePath);
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                boolean[] visited = new boolean[height * width];
                StringBuilder path = new StringBuilder();
                search(i,j,visited,path);
            }
        }
        List<String> result = new ArrayList<>(que);
        result.sort((a, b) -> {
            if (a.length() != b.length()) {
                return Integer.compare(b.length(), a.length()); // 长的在前
            } else {
                return a.compareTo(b); // 字典序升序
            }
        });
        return result;
    }
    public static void main(String[] args){
        long start = System.nanoTime();
        Boggle b=new Boggle();
        String boardFilePath="exampleBoard.txt";
        List<String> record=b.solve(7,boardFilePath);
        for(String s:record){
            System.out.println(s);
        }
        long end = System.nanoTime();
        long elapsed = end - start; // 纳秒
        System.out.println("耗时: " + (elapsed / 1_000_000.0) + " ms");
    }
}
