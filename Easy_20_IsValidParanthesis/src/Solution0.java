import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 栈

 遍历结束，栈空 true， 栈非空，false

 因为每一个括号必须与自己的另一半是对称存在的，所以只用两种情况 a a b b 或者 a b b a
 后遇到的左括号要先闭合，因此我们可以将这个左括号放入栈顶 -> 栈
 不管哪种情况,只要利用栈后进先出, 把栈顶跟当前一致的pop出去，当前后移，再把新peek和当前比较是否匹配isPaird。
 相比于手动枚举匹配规则，可以用一个map来存储左符号和右符号。
 因为要匹配，左括号必须在前，所以可以不用在map寸右括号，如果get返回null或者不相等符号则表示不匹配

 直到数组遍历结束，如果stack空说明是有效括号，true; 如果stack非空，说明有消不掉的，无效括号，返回false

 **特殊判断:因为要匹配必须成对，如果数组长度为奇数，直接返回false，省去遍历

 时间复杂度：O(n), 遍历O(n),比较为O(1)
 空间复杂度: O(n) stack需要额外空间

 **/

class Solution0 {
    public boolean isValid(String s) {
        //如果字符串非偶数长度，一定不是有效括号
        if((s.length() & 1) !=0){
            return false;
        }

        Stack<Character> notPaired = new Stack<>();
        Map<Character,Character> pairDict = new HashMap<Character,Character>(){{
            put('(',')');
            put('[',']');
            put('{','}');
        }};
        char[] sArray = s.toCharArray();

        for(char symbol: sArray){
            // notPaired.push(symbol);
            if(!notPaired.isEmpty() && pairDict.getOrDefault(notPaired.peek(),' ')==symbol){//isPaired参数注意顺序,左右不能替换
                notPaired.pop();
            }else{
                notPaired.push(symbol);
            }
        }
        return notPaired.isEmpty();
    }
}