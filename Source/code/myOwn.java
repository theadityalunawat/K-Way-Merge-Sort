import java.util.Comparator;

public class myOwn implements Comparator<container> {
    
    public static final Comparator<container> compareThem = new Comparator<container>(){
        public int compare(container o1, container o2) {
            if(TwoWaySort.sortFlag == 1)
                return -o1.sortString.compareTo(o2.sortString);
            else
                return o1.sortString.compareTo(o2.sortString);
        }
      
    };

    @Override
    public int compare(container o1, container o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
