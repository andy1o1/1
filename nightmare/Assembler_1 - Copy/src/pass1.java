import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;import java.util.Map;

public class pass1 {
	
	public static void main(String[] args) {
		
		BufferedReader br=null;
		FileReader fr=null;
		
		BufferedWriter bw=null;
		FileWriter fw=null;
		
		try {
			
			String inputfilename="/home/onkar/eclipse-workspace/Assembler_1/input.txt";
			fr=new FileReader(inputfilename);
			br=new BufferedReader(fr);
			
			String outputfilename="/home/onkar/eclipse-workspace/Assembler_1/ic.txt";
			fw=new FileWriter(outputfilename);
			bw=new BufferedWriter(fw);
			
			Hashtable<String, String> is =new Hashtable<String, String>();
			is.put("STOP", "00");
			is.put("ADD", "01");
			is.put("SUB", "02");
			is.put("MULT", "03");
			is.put("MOVER", "04");
			is.put("MOVEM", "05");
			is.put("COMP", "06");
			is.put("BC", "07");
			is.put("DIV", "08");
			is.put("READ", "09");
			is.put("PRINT", "10");
			
			Hashtable<String, String> ad =new Hashtable<String, String>();
			ad.put("START", "01");
			ad.put("END", "02");
			ad.put("ORIGIN", "03");
			ad.put("EQU", "04");
			ad.put("LTORG", "05");
			
			Hashtable<String, String> dl =new Hashtable<String, String>();
			dl.put("DC", "01");
			dl.put("DS", "02");
			
			Hashtable<String, String> symtab =new Hashtable<String, String>();
			Hashtable<String, String> littab =new Hashtable<String, String>();
			ArrayList<Integer> pooltab=new ArrayList<Integer>();
			
			String sCurrentLine=br.readLine();
			int locptr=0;
			int litptr=1;
			int symptr=1;
			int poolptr=1;
			
			//START
			String s1=sCurrentLine.split(" ")[1];
			if(s1.equals("START"))
			{
				bw.write("AD\t"+"01\t");
				String s2=sCurrentLine.split(" ")[2];
				bw.write("C\t"+s2+"\n");
				locptr=Integer.parseInt(s2);
			}
			while((sCurrentLine=br.readLine())!=null)
			{
				int mind_the_LC=0;
				String type=null;
				int flag2 =0;        //address assigning for symbols
				
				//for 0th word
				
				String s=sCurrentLine.split(" |\\,")[0];
				
				//for ds and dl symbols

				for(Map.Entry m:symtab.entrySet())
				{
					if(s.equals(m.getKey()))
					{
						m.setValue(locptr);
						flag2=1;
					}
				}
				
				//newly arrived symbol/label
				
				if(s.length()!=0&&flag2==0)
				{
					symtab.put(s, String.valueOf(locptr));
					symptr++;
				}
				
				//for 1st word
				int isOpcode=0;
				s=sCurrentLine.split(" |\\,")[1];
				
				//IS statement
				for(Map.Entry m:is.entrySet())
				{
					if(s.equals(m.getKey()))
					{
						bw.write("IS\t"+m.getValue()+"\t");
						isOpcode=1;
						type="is";
					}
				}


				//AD statement
				for(Map.Entry m:ad.entrySet())
				{
					if(s.equals(m.getKey()))
					{
						bw.write("AD\t"+m.getValue()+"\t");
						isOpcode=1;
						type="ad";
					}
				}


				//DL statement
				for(Map.Entry m:dl.entrySet())
				{
					if(s.equals(m.getKey()))
					{
						bw.write("DL\t"+m.getValue()+"\t");
						isOpcode=1;
						type="dl";
					}
				}
				
				if(s.equals("LTORG"))
				{
					pooltab.add(poolptr);
					for(Map.Entry m:littab.entrySet())
					{
						if(m.getValue()=="")
						{
							m.setValue(locptr);
							locptr++;
							poolptr++;
							isOpcode=1;
							mind_the_LC=1;
						}
					}
				}
				
				if(s.equals("END"))
				{
					pooltab.add(poolptr);
					for(Map.Entry m:littab.entrySet())
					{
						if(m.getValue()=="")
						{
							m.setValue(locptr);
							locptr++;
							mind_the_LC=1;
						}
					}
				}
				
				//for the 3rd word
				if(sCurrentLine.split(" |\\,").length>2)
				{
					s=sCurrentLine.split(" |\\,")[2];
					
					if(s.equals("AREG")) {
					bw.write("1\t");
					isOpcode=1;}
					else if(s.equals("BREG")) {
						bw.write("2\t");
						isOpcode=1;
						}
					else if(s.equals("CREG")) {
						bw.write("3\t");
						isOpcode=1;
						}
					else if(s.equals("DREG")) {
						bw.write("4\t");
						isOpcode=1;
						}
					else if(type=="dl")
						bw.write("C\t"+s+"\t");
					else
					{
						symtab.put(s, "");
					}
					
				}
				
				//for the 4th word
				if(sCurrentLine.split(" |\\,").length>3)
				{
					s=sCurrentLine.split(" |\\,")[3];
					
					if(s.contains("="))
					{
						littab.put(s, "");
						bw.write("L\t"+litptr+"\t");
						litptr++;
						isOpcode=1;
					}
					else
					{
						symtab.put(s, "");
						bw.write("S\t"+symptr+"\t");
						symptr++;
						isOpcode=1;
					}
					
				}
				
				bw.write("\n");

				if(mind_the_LC==0)
				{
					locptr++;
				}
				
			}
			String f1="/home/onkar/eclipse-workspace/Assembler_1/symtab.txt";
			FileWriter fr1=new FileWriter(f1);
			BufferedWriter bw1=new BufferedWriter(fr1);
			for(Map.Entry m:symtab.entrySet())
			{
				bw1.write(m.getKey()+"\t"+m.getValue()+"\n");
				System.out.println(m.getKey() + " " + m.getValue());
			}
			
			String f2="/home/onkar/eclipse-workspace/Assembler_1/littab.txt";
			FileWriter fr2=new FileWriter(f2);
			BufferedWriter bw2=new BufferedWriter(fr2);
			for(Map.Entry m:littab.entrySet())
			{
				bw2.write(m.getKey()+"\t"+m.getValue()+"\n");
				System.out.println(m.getKey() + " " + m.getValue());
			}
			
			String f3="/home/onkar/eclipse-workspace/Assembler_1/pooltab.txt";
			FileWriter fr3=new FileWriter(f3);
			BufferedWriter bw3=new BufferedWriter(fr3);
			for(Integer i:pooltab)
			{
				bw3.write(i+"\n");
				System.out.println(i);
			}
			
			bw.close();
			bw1.close();
			bw2.close();
			bw3.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}