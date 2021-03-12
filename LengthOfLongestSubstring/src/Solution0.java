/**
 滑动窗口
 1. 遍历字符串，对于每一个字符开始非重复字串来说，由于子串起始index是递增，子串的结束index也是递增(或者不变的)
 2. 使用滑动窗口, 启用左右指针，左指针遍历起始字符。找到起始字符对应的非重复子串对应的结束为Rk。
 左指针往后, left+1, 因为left+1至Rk间也不重复，所以对于left+1来说，从Rk+1开始判断，即right+1;
 对于每一个起始左指针，记录最大长度
 3. 判断是否重复使用hashset，如果右指针的值在hashset出现，则记录len，以及左指针后移。
 每次左指针后移前，先从hashset剔除当前左指针对应的值，再尝试加入Rk+1看是否重复(不可加入)

 时间复杂度：O(n)  N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次，但是同时的。所以相当于左指针遍历一遍
 空间复杂度：O(|\Sigma|)O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0, 128)[0,128) 内的字符，即 ∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，因此空间复杂度为O(∣Σ∣)。

 **/
class Solution0 {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0 || s == null){
            return 0;
        }

        //hashset记录出现过的字符
        Set<Character> charSet = new HashSet<Character>();
        //建一个右指针,初始位置为-1,即还未开始移动，一遍可以加入第一个字符。 res记录最长不重复字串长度
        int right = -1, res = 0;

        for(int i=0; i<s.length(); i++){
            //一个左指针的子串寻找结束后右移动左指针(for loop已实现)，只需要把前一个左指针对应的值从charSet中去除
            //但要注意，如果是第一个左指针,则不用这两步
            if(i != 0){
                charSet.remove(s.charAt(i-1));
            }

            //若移动右指针，判断不重复,则加入集合且右指针后移
            while(right+1 <s.length() && !charSet.contains(s.charAt(right+1))){
                charSet.add(s.charAt(right+1));
                right++;
            }
            res = Math.max(right-i+1, res);
        }
        return res;

    }
}