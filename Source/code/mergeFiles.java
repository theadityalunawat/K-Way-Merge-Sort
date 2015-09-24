import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mergeFiles {

    static void openFilesandMerge(Integer fileCount, Integer byteCount) {
     try
     {
       String st;
        try {
            int i;
            PrintWriter wrt = new PrintWriter(TwoWaySort.oupt);
            if(fileCount > 100000){
                System.out.println("Cannot sort files in this memory");
            }
            else{            
                List <BufferedReader> finalBr = new ArrayList();
                List <Boolean> fileStatus= new ArrayList();
                                
                Comparator<container> com = new Comp();
                PriorityQueue<container> queue = new PriorityQueue<container>(fileCount, com) ;
                int j,len=0,end=0;
                for(i = 0 ;i<fileCount ;i++){
                    String fileName = "temp/"+i+".txt";
                    try
                    {
                    finalBr.add( new BufferedReader(new FileReader(fileName)) );
                    }
                    catch(Error e)
                    {
                        System.out.println("Cannot sort files in this memory");
                        return;}
                    fileStatus.add(Boolean.FALSE);          
                    try 
                    {
                        container C= new container();
                        st=finalBr.get(i).readLine();
                        C.fullString=st.substring(0,byteCount);
                        C.sortString=st.substring(byteCount,st.length());
                        if(i == 0)
                            len=C.sortString.length()+1;
                        C.sortString+=" "+i;
                        queue.add(C);
                    } catch (IOException ex) {
                        //return;
                        Logger.getLogger(mergeFiles.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             container C1=new container();
             String tem;
             int x;
             while(queue.size() != 0)
             {
                 C1=queue.remove();
                 wrt.println(C1.fullString);
                 tem=C1.sortString.substring(len,C1.sortString.length());
                 x=Integer.parseInt(tem);
                    try {
                        if(fileStatus.get(x).equals(Boolean.TRUE))
                        {
                            continue;
                        }
                        
                        if((st=finalBr.get(x).readLine()) == null)
                        {
                           finalBr.get(x).close();
                           fileStatus.set(x, Boolean.TRUE);
                           continue;
                        }
                        else
                        {
                        container C= new container();
                        C.fullString=st.substring(0,byteCount);
                        C.sortString=st.substring(byteCount,st.length()); 
                        C.sortString+=" "+x;
                        queue.add(C);
                        }
                        } catch (IOException ex) {
                        return;
                        //Logger.getLogger(mergeFiles.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                
             wrt.close();
            }
        } catch (FileNotFoundException ex) {
              return;
              //Logger.getLogger(mergeFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
      catch(Exception e)
      {System.out.println("Cannot handle this file Please enter some other configuration");
      return;}

    }
   
}
