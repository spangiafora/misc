import java.util.*;

public class CountChange
{
  public static void main(String [] args)
  {
    int amount = 100;

    if (args.length == 1)
      amount = Integer.parseInt(args[0]);

    System.out.println(countChange(amount, mkCoinList()));
  }

  public static int head(List<Integer> l)
  {
    return l.get(0);
  }

  public static List<Integer> tail(List<Integer> l)
  {
    return l.subList(1, l.size());
  }

  public static List<Integer> mkCoinList()
  {
    List<Integer> coins = new ArrayList<Integer>();

    coins.add(1);
    coins.add(5);
    coins.add(10);
    coins.add(25);
    coins.add(50);

   return coins;
  }

  public static int countChange(int money, List<Integer> coins)
  {
    if (money == 0)
      return 1;
    else if ((money < 0) || (coins.size() == 0))
      return 0;
    else return countChange(money, tail(coins))
              + countChange(money - head(coins), coins);
  }
}
