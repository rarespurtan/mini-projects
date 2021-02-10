#include "GameState.h"
#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

static const int SIZE = 4;
static const char INITIAL_BOARD[] = {'L','L','L','L'};
static const char GOAL_BOARD[] = {'R','R','R','R'};

GameState::GameState(char board[])
{
    m_board = board;
    m_FarmerPlace = m_board[SIZE - 1];
}

char GameState::getFarmerPlace() const
{
    return m_FarmerPlace;
}

char* GameState::getBoard() const
{
    return m_board;
}

bool GameState::isGoal() const
{
    for (int j = 0; j < SIZE; j++)
    {
        if(m_board[j] != GOAL_BOARD[j])
        {
            return false;
        }
    }
    return true;
}

bool GameState::sameBoard(GameState gs) const
{
    char* temp_board = gs.getBoard();
    for (int j = 0; j < SIZE; j++)
    {
        if(m_board[j] != temp_board[j])
        {
            return false;
        }
    }
    return true;
}

int GameState::getSize() const{
    return SIZE;
}

void GameState::printBoard() const
{
    for (int i = 0; i < SIZE; i++)
    {
        std::cout << m_board[i] << ' ';
    }
    std::cout << endl;
}


GameState GameState::clone()
{
    char clonedBoard[SIZE];
    for (int i = 0; i < SIZE; i++)
    {
        clonedBoard[i] = m_board[i];
    }

    return GameState(clonedBoard);

}

vector<GameState> GameState::possibleMoves()
{
    vector<GameState> moves;
    for (int start = 0; start < SIZE-1; start++)
    {
        if(m_board[start] == m_FarmerPlace)
        {
            GameState newState = clone();
            if (m_FarmerPlace == 'L')
            {
                newState.m_board[start] = 'R';
                newState.m_board[SIZE-1] = 'R';
                newState.m_FarmerPlace = 'R';

            }
            else
            {
                newState.m_board[start] = 'L';
                newState.m_board[SIZE-1] = 'L';
                newState.m_FarmerPlace = 'L';
            }
            moves.insert(moves.end(), newState);
        }
    }
    GameState newState = clone();
    if (m_FarmerPlace == 'L')
    {
        newState.m_board[SIZE-1] = 'R';
        newState.m_FarmerPlace = 'R';
    }
    else
    {
        newState.m_board[SIZE-1] = 'L';
        newState.m_FarmerPlace = 'L';
    }
    moves.insert(moves.end(), newState);
    cout << newState.getBoard() << endl;
    return moves;
}


