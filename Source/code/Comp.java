import java.util.Comparator;

    public class Comp implements Comparator<container>
{
    public int compare(container x, container y)
    {
        if(TwoWaySort.sortFlag == 1)
            return -x.sortString.compareTo(y.sortString);
        else
            return x.sortString.compareTo(y.sortString);
    }

}
    

