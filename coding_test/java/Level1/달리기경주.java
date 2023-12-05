import java.util.*;

// players���� �÷��̾���� ����Ǿ� ����. �÷��̾���� ��� ������ �����. (ex. 0�� �ε����� �ִ� �÷��̾ 1��) 
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        Map<String, Integer> rankings = new HashMap<>(); // hashMap ����.

        for (int i = 0; i < players.length; i++) {
            rankings.put(players[i], i + 1);                   // �÷��̾�(key) : ���(value)�� hashMap�� ����.
        }

        for (String calling : callings) {
             
            int index = rankings.get(calling) - 1;             // calling�� �ش��ϴ� �÷��̾��� ���(value = index)�� ������
            if (index > 0) {
                String tmp = players[index];                   
                players[index] = players[index - 1];
                players[index - 1] = tmp;
                rankings.put(players[index], index + 1);       // �߿����� ������ ����� +1 ���� ���� ������.
                rankings.put(players[index - 1], index);       // �߿��� ������ ����� ������.
            }
        }
        
        answer = players;

        return answer;
    }
}