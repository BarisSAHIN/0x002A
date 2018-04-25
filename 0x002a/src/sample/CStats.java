package sample;

import javafx.util.Pair;
import java.util.HashMap;
import java.util.Set;

public class CStats
{
    private HashMap<String, Integer> status;
    private static final int MAX_VALUE = 4;
    private static final int DEFAULT = 0;

    public CStats()
    {
        this(new HashMap<>());
    }
    public CStats(String input)
    {
        status = new HashMap<>();
        String[] array = input.split("<");
        array = array[1].split(">");
        if(!array[1].isEmpty())
        {
            array = array[1].split(",");
            for (String parser : array)
                status.put(parser.split(":")[0], Integer.valueOf(parser.split(":")[1]));
        }
    }

    public CStats(CStats other)
    {
        this(other.status);
    }

    public CStats(HashMap<String, Integer> input)
    {
        status = new HashMap<>();
        Set<String> other = input.keySet();
        for(String temp : other)
            addParam(temp, input.get(temp));
    }


    public boolean updateAllStats(HashMap<String, Pair<Character, Integer>> input)
    {
        boolean ret_val = true;
        if(input.size() > status.size())
            ret_val = false;
        else
        {
            Set<String> temp = input.keySet();
            for(String search : temp)
                if(!(ret_val = updateStats(new Pair<>(search, input.get(search)))))
                    break;
        }
        return ret_val;
    }

    public boolean updateStats(String name, Character opt, Integer number)
    {
        return updateStats(new Pair<>(name, new Pair<>(opt, number)));
    }

    private boolean updateStats(Pair<String, Pair<Character, Integer>> input)
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
        return this.addParam(key, DEFAULT);
    }

    public boolean addParam(String key, Integer Value)
    {
        boolean ret_val = true;
        if(status.size() < MAX_VALUE)
        {
            if(status.containsKey(key))
                System.err.println("This key is already exist. It assigned to default value");
            status.put(key, Value);
        }
        else
            ret_val = false;
        return ret_val;
    }

    /**
     * Oyuncu karakteri verilen ön şartı karşılayıp, bir sonraki node'a erişebiliyor mu diye kontrol eder.
     * Eğer gerekli ön şartları sağlıyorsa true, sağlamıyorsa false döner.
     * @param preRequisite Ön şart
     * @return  Ön şart sağlanıyorsa true, sağlanmıyorsa false
     */
    public boolean canAccess(HashMap<String, Pair<Character, Integer>> preRequisite){
        Set<String> keyVal = preRequisite.keySet();

        for(String cpr : keyVal) {
            if(status.containsKey(cpr)) {
                Integer currStat = status.get(cpr);
                switch (preRequisite.get(cpr).getKey()) {
                    case '<':
                        if (currStat < preRequisite.get(cpr).getValue());
                        else return false;
                    case '>':
                        if (currStat > preRequisite.get(cpr).getValue());
                        else return false;
                    case '=':
                        if (currStat.equals(preRequisite.get(cpr).getValue()));
                        else return false;
                }
            }
            else return false;
        }

        return true;
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
        return ret_val;
    }

    @Override
    public boolean equals(Object other)
    {
        return ((CStats) other).getStats().containsAll(this.getStats());
    }

    public String toSave()
    {
        String ret_val = "<";
        if(!status.isEmpty())
        {
            Set<String> keyV = status.keySet();
            for(String outp : keyV)
                ret_val += outp + ":" + status.get(outp).toString() + ",";
        }
        ret_val += ">";
        return ret_val;
    }
}