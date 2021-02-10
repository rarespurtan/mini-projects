#include <iostream>
#include "GameState.h"
#include <vector>
#include <string>

int main() {
    char board[] = {'L','L','L','L'};
    char board2[] = {'R','L','L','L'};
    std::string ab[5];
    std::cout << ab[0][2] << std::endl;
    GameState *a = new GameState(board);
//    std::vector<GameState> moves = a->possibleMoves();
//    GameState cur = moves.front();
//    char* b = cur.getBoard();

    GameState *b = new GameState(board2);
    std::cout << a->sameBoard(*b) << std::endl;

    return 0;
}
