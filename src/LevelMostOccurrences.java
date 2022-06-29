 import  java.util.ArrayDeque;
import java.util.Queue;

 /**
  * class represents the level with most occurrences of a number
  */
 public class LevelMostOccurrences {
     /**
      * gets the level with the most occurrences of a number in a specific binary tree.
      * @param node - the root node of the binary tree.
      * @param num - the number we want to get the level with the most occurrences of it.
      * @return the level with most occurrences of num.
      */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num)
    {
        Queue<BinNode<Integer>> parents = new ArrayDeque<>();// ArrayDeque represent the numbers in current level
        Queue<BinNode<Integer>>  kids = new ArrayDeque<>();// ArrayDeque represent the numbers in the next level
        int levelMostOccurrences = -1;
        int currentLevel = 0;// start with level 0
        int maxOccurrences = 0;
        int levelOccurrences = 0;

        parents.add(node);
        BinNode<Integer> data;//the current node
        while(!parents.isEmpty())// while there are more numbers in this level
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
            if(parents.isEmpty())// we scanned all the nodes in the current level
            {
                if(levelOccurrences > maxOccurrences)
                {
                    maxOccurrences = levelOccurrences;
                    levelMostOccurrences = currentLevel;
                }
                currentLevel++;
                levelOccurrences = 0;
                while (!kids.isEmpty())// transfer all the nodes in kids to parents
                {
                    parents.add(kids.poll());
                }
            }
        }
        return levelMostOccurrences;
    }
}
