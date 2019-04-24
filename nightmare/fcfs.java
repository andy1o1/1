import java.util.Scanner;
class Process
{
    int wait ,arrival, bursts,turnAround, completionTime = 0;
    Process(int arr,int bur)
    {
        arrival = arr;
        bursts = bur;
    }
}
class Processmain
{
    public static void main(String[] args)
    {
        int x=0,wt=0,tt=0;
        float avgt,avgw;
		Scanner s = new Scanner(System.in);
		System.out.print("Enter the number of processes : ");
		int n = s.nextInt();
        Process[] myProcess = new Process[n];
        System.out.println("Enter Arrival time and bursts : ");		
        for(int i=0;i<n;i++)
        {
			int arr  = s.nextInt();
			int bur = s.nextInt();
			myProcess[i] = new Process(arr,bur);
		}
        for(int i=0;i<myProcess.length;i++)
        {
            x = x+myProcess[i].bursts;
			myProcess[i].completionTime = x;
			myProcess[i].turnAround = myProcess[i].completionTime - myProcess[i].arrival;
			tt=tt+myProcess[i].turnAround;
			myProcess[i].wait = myProcess[i].turnAround - myProcess[i].bursts;
			wt=wt+myProcess[i].wait;
			System.out.println("Process["+i+"]:");
			System.out.println("Turnaround\tCompletion\twaiting");
			System.out.println(myProcess[i].turnAround+"\t\t"+myProcess[i].completionTime+"\t\t"+myProcess[i].wait);
        }
        avgt = (float)tt/n;
        avgw = (float)wt/n;
        System.out.println("\nAverage TurnAround Time : "+avgt);
        System.out.println("Average Waiting Time : "+avgw);
    }
}
/*
Enter the number of processes : 5
Enter Arrival time and bursts : 
0 3
1 5
3 2
9 5
12 5
Process 0:
Turnaround	Completion	waiting
3			3			0
Process 1:
Turnaround	Completion	waiting
7			8			2
Process 2:
Turnaround	Completion	waiting
7			10			5
Process 3:
Turnaround	Completion	waiting
6			15			1
Process 4:
Turnaround	Completion	waiting
8			20			3

Average TurnAround Time : 6.2
Average Waiting Time : 2.2

*/
