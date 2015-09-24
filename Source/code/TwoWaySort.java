import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwoWaySort {
    static int sortFlag=0;
    static String oupt=new String();
    public static void main(String[] args) {
        try
        {
        if(args[3].equals("desc"))
        {
            sortFlag=1;
        }
        oupt=args[1];
            List<Integer> colsToSort=new ArrayList<>();
            List<Integer> memOfCols=new ArrayList<>();
            List<Integer> startReadingfromHere = new ArrayList<>();
        try {
            int i;
            for(i=4;i<args.length;i++)
            {
                args[i]=args[i].replace("col","");
                colsToSort.add(Integer.parseInt(args[i]));
            }
            int temp1=0;
            int tempMem;
            int colCountSort=0;
            long total = Runtime.getRuntime().totalMemory();
            long mem=(long) (total*0.1);
            Integer byteCount=1;
             FileReader fr = new FileReader("metadata.txt");
             BufferedReader br=new BufferedReader(fr);
            String st;
            String[] meta=null;
            try {
                while((st=br.readLine()) != null)
                {
                    meta=st.split(",");
                    tempMem=Integer.parseInt(meta[1]);
                    memOfCols.add(tempMem);
                    if(temp1 == 0)
                    startReadingfromHere.add(0);
                    else
                    {
                        startReadingfromHere.add(memOfCols.get(temp1-1)+startReadingfromHere.get(temp1-1)+1);
                    }
                    byteCount+=tempMem;
                    temp1++;
                    meta=null;
                }
                startReadingfromHere.add(memOfCols.get(temp1-1)+startReadingfromHere.get(temp1-1)+1);
               long noOfEntriesToRead=mem/byteCount;
                if(noOfEntriesToRead > 50000)
                    noOfEntriesToRead=50000;
                fr.close();
                FileReader fr1 = new FileReader(args[0]);
                BufferedReader br1=new BufferedReader(fr1);
                String st1;
                int Linescount=0;
                int fileCount=0;
                int startByte;
                int endByte,p,j,indexOfColtoSort;
                List<container> splitSort=new ArrayList<>();
               
                while((st1=br1.readLine()) != null)
                {
                   
                    if(Linescount == noOfEntriesToRead)
                    {
                       Collections.sort(splitSort,myOwn.compareThem);
                       File fl = new File("temp/"+fileCount+".txt");
                        fl.getParentFile().mkdirs();
                        PrintWriter pw=new PrintWriter(fl);
                        for(p=0;p<splitSort.size();p++)
                        {
                            if(i != (splitSort.size()-1))
                                pw.println(splitSort.get(p).fullString+" "+splitSort.get(p).sortString);
                            else
                                  pw.print(splitSort.get(p).fullString+" "+splitSort.get(p).sortString);
                        }
                       pw.close();
                       splitSort.clear();
                       Linescount=0;
                       fileCount++;
                    }
                    container C= new container();
                    C.fullString+=st1;
                    for(i=0;i<colsToSort.size();i++)
                    {
                        indexOfColtoSort=colsToSort.get(i);
                        startByte=startReadingfromHere.get(indexOfColtoSort);
                        endByte=startReadingfromHere.get(indexOfColtoSort+1)-1;
                        C.sortString+=st1.substring(startByte,endByte);
                    }
                    splitSort.add(C);
                    Linescount++;
                }
                 Collections.sort(splitSort,myOwn.compareThem);
                       File fl = new File("temp/"+fileCount+".txt");
                        fl.getParentFile().mkdirs();
                        PrintWriter pw=new PrintWriter(fl);
                        for(p=0;p<splitSort.size();p++)
                        {
                            if(i != (splitSort.size()-1))
                                pw.println(splitSort.get(p).fullString+" "+splitSort.get(p).sortString);
                            else
                                  pw.print(splitSort.get(p).fullString+" "+splitSort.get(p).sortString);
                        }
                       pw.close();
                       splitSort.clear();
                       Linescount=0;
                       fileCount++;
                fr.close();
                fr1.close();
                Integer cnt=byteCount+memOfCols.size()-2;
                try{
                mergeFiles.openFilesandMerge(fileCount,cnt);  
                }
                catch(Exception e)
                {
                     System.out.println("Cannot handle this file Please enter some other configuration");
                    return;
                }
            } catch (IOException ex) {
                System.out.println("Cannot handle this file Please enter some other configuration");
               return;
              //  Logger.getLogger(TwoWaySort.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot handle this file Please enter some other configuration");
              return;
           // Logger.getLogger(TwoWaySort.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        }
        catch(Exception e)
        {
             System.out.println("Cannot handle this file Please enter some other configuration");
             return;
        }
      }
    
}
