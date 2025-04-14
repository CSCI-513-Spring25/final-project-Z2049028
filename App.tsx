import React from 'react';
import './App.css';
import BoardCell from './Cell';

interface Cell {
  x: number;
  y: number;
  text: string;
}

interface AppState {
  cells: Cell[];
}

class App extends React.Component<{}, AppState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      cells: Array(400).fill(null).map((_, i) => ({
        x: Math.floor(i / 20),
        y: i % 20,
        text: ''
      }))
    };
  }

  componentDidMount() {
    this.newGame();
    window.addEventListener('keydown', this.handleKeyDown);
  }

  componentWillUnmount() {
    window.removeEventListener('keydown', this.handleKeyDown);
  }

  newGame = async () => {
    try {
      const response = await fetch('/newgame');
      const data = await response.json();
      this.setState({ cells: data.cells });
      console.log('New game started:', data); // Debug log
    } catch (error) {
      console.error('Failed to start new game:', error);
    }
  };

  handleKeyDown = async (e: KeyboardEvent) => {
    if ([37, 38, 39, 40].includes(e.keyCode)) {
      try {
        const response = await fetch(`/play?keyEvent=${e.keyCode}`);
        const data = await response.json();
        this.setState({ cells: data.cells });
        console.log('Movement processed:', data); // Debug log
      } catch (error) {
        console.error('Failed to handle movement:', error);
      }
    }
  };

  render() {
    return (
      <div className="app-container" tabIndex={0}>
        <div id="board">
          {this.state.cells.map((cell, index) => (
            <div key={`${cell.x}-${cell.y}`} className="cell">
              <BoardCell cell={cell} />
            </div>
          ))}
        </div>
        <div id="bottombar">
          <button onClick={this.newGame}>New Game</button>
        </div>
      </div>
    );
  }
}

export default App;