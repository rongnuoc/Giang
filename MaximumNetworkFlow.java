import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.LinkedList; 
  
class MaximumNetworkFlow 
{ 
    private int V;
    private int maxFlow_value;
    private int[][] graph;

    /**
     * @return the maxFlow_value
     */
    public int getMaxFlow_value() {
        return maxFlow_value;
    }

    private boolean bfs(int rGraph[][], int s, int t, int parent[]) 
    { 
        
        boolean visited[] = new boolean[V]; 
        for(int i=0; i<V; i++) 
            visited[i]=false; 
  
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        queue.add(s); 
        visited[s] = true; 
        parent[s]=-1; 
  
        while (queue.size()!=0) 
        { 
            int u = queue.poll(); 
  
            for (int v=0; v<V; v++) 
            { 
                if (visited[v]==false && rGraph[u][v] > 0) 
                { 
                    queue.add(v); 
                    parent[v] = u; 
                    visited[v] = true; 
                } 
            } 
        } 
        return (visited[t] == true); 
    } 
  
    
    public void run(int graph[][], int V) 
    { 
        this.V = V;
        this.graph = graph;
        int s = 0, t = V - 1; 
        int u, v; 
        
        int rGraph[][] = new int[V][V]; 
  
        for (u = 0; u < V; u++) 
            for (v = 0; v < V; v++) 
                rGraph[u][v] = graph[u][v]; 
  
        
        int parent[] = new int[V]; 
  
        int max_flow = 0;  
        while (bfs(rGraph, s, t, parent)) 
        { 
           
            int path_flow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]) 
            { 
                u = parent[v]; 
                path_flow = Math.min(path_flow, rGraph[u][v]); 
            } 
  
            
            for (v=t; v != s; v=parent[v]) 
            { 
                u = parent[v]; 
                rGraph[u][v] -= path_flow; 
                rGraph[v][u] += path_flow; 
            } 
  
            
            max_flow += path_flow;
            
        } 
   
        maxFlow_value = max_flow;
    } 
    
    public String findFlowOfMaxValue()
    {
        String flow = "0";
        boolean visited[] = new boolean[V]; 
        for(int i=0; i<V; i++) 
            visited[i]=false; 

        int current = 0;
        visited[current] = true;
        while(current < V - 1)
        {
            int max = graph[current][0];
            int v = 0;
            for(int i = 0; i < V;i++){
                if(graph[current][i] > max && visited[i] == false){
                    max = graph[current][i];
                    v = i;
                }
                
            }
            current = v;
            visited[current] = true;
            flow += " -> " + current;
        }

        return flow;
    }
    
    
    public static void main (String[] args) throws java.lang.Exception 
    { 
        //call run() before call another method  
        int graph[][] =new int[][] { {1, 6, 3, 2, 11, 9}, 
                                     {3, 5, 3, 1, 5, 8}, 
                                     {0, 0, 0, 0, 14, 0}, 
                                     {0, 0, 0, 0, 0, 20}, 
                                     {0, 0, 0, 7, 0, 20}, 
                                     {0, 0, 0, 0, 0, 0} 
                                    };
       
        MaximumNetworkFlow  m = new MaximumNetworkFlow(); 
        m.run(graph, 6);
        System.out.println("The maximum possible flow is " +m.getMaxFlow_value()); 
        System.out.println("The flow of maximum value is " +m.findFlowOfMaxValue()); 
  
    } 
} 