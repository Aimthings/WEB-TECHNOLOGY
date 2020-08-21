import java.io.*;
import java.util.*;

public class Main3{

    public static void main(String[] args) {
        int V,E;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of Vertices: ");
        V = scan.nextInt();
        System.out.print("Enter the number of edges: ");
        E = scan.nextInt();

        int graph[][]=new int[E][3];
        System.out.println("Enter the detail of each edge in format <start end weigth>: ");
        for (int i = 0; i < E; i++)
            for (int j = 0; j < 3; j++)
                graph[i][j] = scan.nextInt();


        int src=0;
        int []distance = new int[V];
        for (int i = 0; i < V; i++)
            distance[i] = Integer.MAX_VALUE;

        distance[src] = 0;
        for (int i = 0; i < V - 1; i++)
        {

            for (int j = 0; j < E; j++)
            {
                if (distance[graph[j][0]] + graph[j][2] <distance[graph[j][1]])
                    distance[graph[j][1]] =distance[graph[j][0]] + graph[j][2];
            }
        }
        boolean flag=false;
        for (int i = 0; i < E; i++)
        {
            int tem1 = graph[i][0];
            int tem2 = graph[i][1];
            int weight = graph[i][2];
            if (distance[tem1] != Integer.MAX_VALUE &&
                    distance[tem1] + weight < distance[tem2]){
                System.out.println("Negative cycles exist.");
                flag=true;
            }
        }
        if(flag==false){
            System.out.println("Distance of other vertices from Source(0): ");
            for (int i = 0; i < V; i++)
                System.out.println("for vertex "+i + "-> " + distance[i]);
        }
    }
}

