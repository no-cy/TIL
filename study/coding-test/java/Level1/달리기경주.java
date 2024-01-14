import java.util.*;

// players에는 플레이어들이 저장되어 있음. 플레이어들이 담긴 순서가 등수임. (ex. 0번 인덱스에 있는 플레이어가 1등) 
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        Map<String, Integer> rankings = new HashMap<>(); // hashMap 생성.

        for (int i = 0; i < players.length; i++) {
            rankings.put(players[i], i + 1);                   // 플레이어(key) : 등수(value)를 hashMap에 저장.
        }

        for (String calling : callings) {
             
            int index = rankings.get(calling) - 1;             // calling에 해당하는 플레이어의 등수(value = index)를 가져옴
            if (index > 0) {
                String tmp = players[index];                   
                players[index] = players[index - 1];
                players[index - 1] = tmp;
                rankings.put(players[index], index + 1);       // 추월당한 선수의 등수를 +1 증가 시켜 저장함.
                rankings.put(players[index - 1], index);       // 추월한 선수의 등수를 저장함.
            }
        }
        
        answer = players;

        return answer;
    }
}