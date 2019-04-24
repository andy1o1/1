import java.io.*;
import java.util.*;

public class LRU 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int reference[], mem_layout[][], buffer[];
        int i,j,frames, pointer=0, hit=0, fault=0,ref_len;
        Boolean isFull = false;
        ArrayList<Integer> stack = new ArrayList<Integer>();
        
        System.out.print("Please enter the number of Frames: ");
        frames = Integer.parseInt(br.readLine());
        
        System.out.print("\nPlease enter the length of the Reference string: ");
        ref_len = Integer.parseInt(br.readLine());
        
        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for(j = 0; j < frames; j++)
                buffer[j] = -1;
        
        System.out.println("Please enter the reference string: ");
        for(i = 0; i < ref_len; i++)
            reference[i] = Integer.parseInt(br.readLine());
        
        System.out.println();
        for(i = 0; i < ref_len; i++)
        {
            if(stack.contains(reference[i]))
            {
                stack.remove(stack.indexOf(reference[i]));
            }
            stack.add(reference[i]);
            int search = -1;
                {
                    search = j;
                    hit++;
                    break;
                }
            for(j = 0; j < frames; j++)
            {
                if(buffer[j] == reference[i])
            }
            if(search == -1)
            {
            	if(isFull)
            	{
            		int min_loc = ref_len;
                    for(j = 0; j < frames; j++)
                    {
                    	if(stack.contains(buffer[j]))
                        {
                            int temp = stack.indexOf(buffer[j]);
                            if(temp < min_loc)
                            {
                                min_loc = temp;
                                pointer = j;
                            }
                        }
                    }	
            	}
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                if(pointer == frames)
                {
	                 pointer = 0;
	                 isFull = true;
                }
            }
            for(j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        for(i = 0; i < frames; i++)
        {
            for(j = 0; j < ref_len; j++)
                System.out.printf("%3d ",mem_layout[j][i]);
            System.out.println();
        }
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
        System.out.println("The number of Faults: " + fault);
    } 
}
/*
Output:

Please enter the number of Frames: 
3
Please enter the length of the Reference string: 
12
Please enter the reference string: 
  2   3   2   1   5   2   4   5   3   2   5   2

  2   2   2   2   2   2   2   2   3   3   3   3 
 -1   3   3   3   5   5   5   5   5   5   5   5 
 -1  -1  -1   1   1   1   4   4   4   2   2   2 
The number of Hits: 5
Hit Ratio: 0.41666666
The number of Faults: 7

*/