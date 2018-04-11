package sample;

import javafx.util.Pair;
import java.util.HashMap;
import java.util.Set;

public class CStats
{
    private HashMap<String, Integer> status;

    public boolean changeAllStats(HashMap<String, Pair<Character, Integer>> input)
    {
        boolean ret_val = true;
        if(input.size() > status.size())
            ret_val = false;
        else
        {
            Set<String> temp = input.keySet();
            for(String search : temp)
                if(!(ret_val = changeStats(new Pair<>(search, input.get(search)))))
                    break;
        }
        return ret_val;
    }

    private boolean changeStats(Pair<String, Pair<Character, Integer>> input)
    {
        boolean ret_val = true;
        if(status.containsKey(input.getKey()))
            switch (input.getValue().getKey())
            {
                case '+':
                    status.put(input.getKey(), status.get(input.getKey()) + input.getValue().getValue());
                    break;
                case '-':
                    status.put(input.getKey(), status.get(input.getKey()) - input.getValue().getValue());
                    break;
                case '*':
                    status.put(input.getKey(), status.get(input.getKey()) * input.getValue().getValue());
                    break;
                case '/':
                    status.put(input.getKey(), status.get(input.getKey()) / input.getValue().getValue());
                    break;
                case '%':
                    status.put(input.getKey(), status.get(input.getKey()) % input.getValue().getValue());
                    break;
                default:
                    ret_val = false;
                    break;
            }
        else
            ret_val = false;
        return ret_val;
    }



    public boolean addParam(String key)
    {
        return this.addParam(key, 0);
    }

    public boolean addParam(String key, Integer Value)
    {
        boolean ret_val = true;
    }


    public Set<String> getStats()
    {
        return status.keySet();
    }

    @Override
    public String toString() {
        String ret_val = "";
        if(!status.isEmpty())
        {
            Set<String> keyV = status.keySet();
            for(String outp : keyV)
                ret_val += outp + ": " + status.get(outp).toString();
        }
        return super.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        return ((CStats) other).getStats().containsAll(this.getStats());
    }
}
