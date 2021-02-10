#ifndef GOOSECORNFOX_GAMESTATE_H
#define GOOSECORNFOX_GAMESTATE_H

#include <vector>

class GameState {

public:
    GameState(char board[]);
    char getFarmerPlace() const;
    char* getBoard() const;
    bool isGoal() const;
    bool sameBoard(GameState gs) const;
    GameState clone();
    int getSize() const;
    std::vector<GameState> possibleMoves();
    void printBoard() const;

private:
    char m_FarmerPlace;
    char *m_board;

};

#endif //GOOSECORNFOX_GAMESTATE_H
