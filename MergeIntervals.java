/**
sort 排序
1. 把intervals按开始时间正序排序
2. 新建一个ArrayList resIntervals
3. 遍历intervals，把每一个interval的开始时间跟resIntervals里的最后一个数组的结束时间比
    如果小，则merge，用两个比较数组的max结束时间代替resIntervals最后一个数组的结束时间
    如果大，则无冲突，直接把interval加入resIntervals

时间复杂度 O(nlog(n)) 排序开销nlog(n), 遍历arraylist开销O(n),综合是O(nlog(n))
空间复杂度 O(n) 额外需要一个数组
**/
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return new int[0][2];
        }
        //对intervals根据开始时间正序排序
        Arrays.sort(intervals, (i,j)->(i[0] - j[0]));

        //创建一个arraylist来存储结果
        List<int[]> mergedIntervals = new ArrayList<int[]>();
        int[] comparison = new int[2];
        for(int[] interval :intervals){
            int start = interval[0]; 
            int end = interval[1];
            if(mergedIntervals.size() != 0 && mergedIntervals.get(mergedIntervals.size()-1)[1] >= start){
                mergedIntervals.get(mergedIntervals.size()-1)[1] = 
                Math.max(mergedIntervals.get(mergedIntervals.size()-1)[1],end);
            }else {
                mergedIntervals.add(interval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);

    }
}

/**
sort 排序
1. 把intervals按开始时间正序排序
2. 新建一个ArrayList resIntervals
3. 遍历intervals，把每一个interval的开始时间跟resIntervals里的最后一个数组的结束时间比
    如果小，则merge，用两个比较数组的max结束时间代替resIntervals最后一个数组的结束时间
    如果大，则无冲突，直接把interval加入resIntervals

时间复杂度 O(nlog(n)) 排序开销nlog(n), 遍历arraylist开销O(n),综合是O(nlog(n))
空间复杂度 O(n) 额外需要一个数组
**/
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return new int[0][2];
        }
        //对intervals根据开始时间正序排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2){
                return i1[0] - i2[0];
            }
        });

        //创建一个arraylist来存储结果
        List<int[]> mergedIntervals = new ArrayList<int[]>();
        int[] comparison = new int[2];
        for(int[] interval :intervals){
            int start = interval[0]; 
            int end = interval[1];
            if(mergedIntervals.size() != 0 && mergedIntervals.get(mergedIntervals.size()-1)[1] >= start){
                mergedIntervals.get(mergedIntervals.size()-1)[1] = 
                Math.max(mergedIntervals.get(mergedIntervals.size()-1)[1],end);
            }else {
                mergedIntervals.add(interval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);

    }
}