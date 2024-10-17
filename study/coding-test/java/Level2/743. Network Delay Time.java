class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int[] nodeDist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
            nodeDist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < times.length; i++) {
            int from = times[i][0];
            int to = times[i][1];
            int distance = times[i][2];

            map.get(from).add(new int[] {to, distance});    
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {k, 0});
        nodeDist[k] = 0;

        while (!pq.isEmpty()) {
           int[] element = pq.poll();
           int node = element[0];
           int dist = element[1];
        
            // System.out.println("1 node: " + node + ", dist: " + dist);
            if (map.get(node) != null) {
                // System.out.println("in if");
                for (int[] nextElement : map.get(node)) {
                    int nextNode = nextElement[0];
                    int nextDist = nextElement[1] + dist;

                    if (nextDist >= nodeDist[nextNode]) continue;

                    // System.out.println("node: " + nextNode + ", dist: " + nextDist);
                    nodeDist[nextNode] = nextDist;
                    pq.add(new int[] {nextNode, nextDist});
                }
           }           
        }

        int minTime = 0;
        for (int i = 1; i <= n; i++) {
            // System.out.println("i: " + i + ", time: " + nodeDist[i]);
            if (nodeDist[i] == Integer.MAX_VALUE) {
                return -1;
            } else if (nodeDist[i] > minTime) {
                minTime = nodeDist[i];
            }
        }

        return minTime;
    }
}
