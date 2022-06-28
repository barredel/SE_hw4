 import  java.util.ArrayDeque;
import java.util.Queue;

 public class LevelMostOccurrences {
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num)
    {
        Queue<BinNode<Integer>> parents = new ArrayDeque<>();
        Queue<BinNode<Integer>>  kids = new ArrayDeque<>();
        int levelMostOccurrences = -1;
        int currentLevel = 0;
        int maxOccurrences=0;
        int levelOccurrences = 0;

        parents.add(node);
        BinNode<Integer> data;
        while(!parents.isEmpty())
        {
            data = parents.poll();
            if(data.getData() == num)
            {
                levelOccurrences++;
            }
            if (data.getRight()!=null)
            {
                kids.add(data.getRight());
            }
            if(data.getLeft()!=null)
            {
                kids.add(data.getLeft());
            }
            if(levelOccurrences>maxOccurrences)
            {
                maxOccurrences = levelOccurrences;
                levelMostOccurrences = currentLevel;
            }
            if(parents.isEmpty())
            {
                currentLevel++;
                levelOccurrences = 0;
                while (!kids.isEmpty())
                {
                    parents.add(kids.poll());
                }
            }
        }

        return levelMostOccurrences;
    }
}
