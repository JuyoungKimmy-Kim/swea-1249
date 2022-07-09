package org.swea.eclipse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;


class Pos implements Comparable<Pos> {
	int time;
	int y,x;
	
	Pos (int time, int y, int x) {
		this.time=time;
		this.y=y;
		this.x=x;	
	}
	
	public int compareTo (Pos p) {
		if (this.time>p.time)
			return 1;
		else if (this.time<p.time)
			return -1;
		return 0;
	}
	
}

public class Solution {

	public static final int[] dy= {0,0,1,-1};
	public static final int[] dx= {1,-1,0,0};
	
	static int N;
	static int[][] map;
	static int min;

	static int[][] dist;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
		//Scanner sc=new Scanner (System.in);
		int T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			N=Integer.parseInt(br.readLine());
			
			map=new int[N][N];
			dist=new int[N][N];
			
			for (int i=0; i<N; i++)
				Arrays.fill(dist[i], -1);
			
			for (int i=0; i<N; i++) {
				String[] line=br.readLine().split("");
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(line[j]);
				}
			}
			
			bfs();
			System.out.printf("#%d %d\n", tc, dist[N-1][N-1]);
		}
	}
	
	private static void bfs () {
		PriorityQueue<Pos> pq=new PriorityQueue<>();
		pq.offer(new Pos(0,0,0));
		dist[0][0]=0;
		
		while (!pq.isEmpty()) {
			Pos p=pq.poll();
			int time=p.time;
			int y=p.y;
			int x=p.x;
			
			for (int i=0; i<4; i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N) continue;
				
				if (dist[ny][nx]==-1) {
					dist[ny][nx]=time+map[ny][nx];
					pq.offer(new Pos(dist[ny][nx], ny, nx));
				}
				
				if (dist[ny][nx]>time+map[ny][nx])
					dist[ny][nx]=time+map[ny][nx];
			}
		}
	}

}
