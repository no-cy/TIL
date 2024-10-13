/*
- 그래프(인접 리스트), BFS, 위상정렬 사용
*/

class Solution {
    private List<List<Integer>> adjacencyList = new ArrayList<>();

    public boolean topologicalSort(int numCourses, int[] degree) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> orderList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            orderList.add(current);

            for (int course : adjacencyList.get(current)) {
                degree[course]--;
                if (degree[course] == 0) {
                    queue.add(course);
                }
            }
        }
        
        return orderList.size() == numCourses;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] degree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preReq = prerequisites[i][1];
            adjacencyList.get(preReq).add(course);
            degree[course]++;
        }

        return topolgicalSort(numCourses, degree);
    }
}
