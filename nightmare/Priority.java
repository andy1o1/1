import java.util.Scanner; 

public class Priority 
	{
	public static void main(String args[]) 
	{ 
		Scanner s = new Scanner(System.in);
		int x,n,p[],pp[],bt[],w[],t[],awt,atat,i; 
		p = new int[10];
		pp = new int[10]; 
		bt = new int[10]; 
		w = new int[10]; 
		t = new int[10];

		System.out.print("Enter the number of process : "); 
		n = s.nextInt();
		System.out.print("\n\t Enter burst time : time priorities \n");
		
		for(i=0;i<n;i++)
		{
			System.out.print("\nProcess["+(i+1)+"]:"); 
			bt[i] = s.nextInt();
			pp[i] = s.nextInt();
			p[i]=i+1;
		}
		
		//sorting on the basis of priority 
		for(i=0;i<n-1;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if(pp[i]>pp[j])
				{
					x=pp[i]; 
					pp[i]=pp[j]; 
					pp[j]=x;
					
					x=bt[i]; 
					bt[i]=bt[j]; 
					bt[j]=x;
					
					x=p[i]; 
					p[i]=p[j]; 
					p[j]=x;
				}
			}
		}
		w[0]=0;
		awt=0; 
		t[0]=bt[0];
		atat=t[0]; 
		for(i=1;i<n;i++)
		{
			w[i]=t[i-1]; 
			awt+=w[i]; 
			t[i]=w[i]+bt[i]; 
			atat+=t[i];
		}
		 
		//Displaying the process
		
		System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time	Priority \n");
		for(i=0;i<n;i++)
			System.out.print("\n"+p[i]+"\t	"+bt[i]+"\t\t	"+w[i]+"\t\t "+t[i]+"\t\t	"+pp[i]+"\n");
		awt/=n; 
		atat/=n;
		System.out.print("\n Average Wait Time : "+awt); 
		System.out.print("\n Average Turn Around Time : "+atat);
		
	}
}
/*
Enter the number of process : 5

Enter burst time : time priorities 

Process[1]:9 5

Process[2]:4 3

Process[3]:5 1

Process[4]:7 2

Process[5]:3 4


Process 	Burst Time 	 Wait Time		Turn Around Time	Priority 

3			5				0		 		5				1

4			7				5				12				2

2			4				12		 		16				3

5			3				16		 		19				4
	
1			9				19		 		28				5

Average Wait Time : 10
Average Turn Around Time : 16
*/