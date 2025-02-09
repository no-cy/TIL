class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rowVerification = new HashSet[9];
        Set<Character>[] colVerification = new HashSet[9];
        Set<Character>[] subBoxVerification = new HashSet[9];

        for (int i = 0; i < board.length; i++) {
            rowVerification[i] = new HashSet<>();
            colVerification[i] = new HashSet<>();
            subBoxVerification[i] = new HashSet<>();
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                char digit = board[row][col];
                
                if (digit == '.') continue;

                // 3 x 3 sub-boxed 인덱스 계산
                int idx = (row / 3) * 3 + (col / 3);

                if (!rowVerification[row].add(digit) || 
                    !colVerification[col].add(digit) ||
                    !subBoxVerification[idx].add(digit)) {
                    return false;
                }
            }
        }

        return true;
    }
}
