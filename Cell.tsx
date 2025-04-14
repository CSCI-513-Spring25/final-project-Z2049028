import React from 'react';

interface CellProps {
  cell: {
    x: number;
    y: number;
    text: string;
  };
}

class BoardCell extends React.Component<CellProps> {
  render() {
    const { text } = this.props.cell;
    
    switch(text) {
      case 'C':
        return <img src="/images/ship.jpg" alt="Columbus Ship" className="game-element" />;
      case 'P':
        return <img src="/images/pirateShip.jpg" alt="Pirate Ship" className="game-element" />;
      case 'I':
        return <img src="/images/island.jpg" alt="Island" className="game-element" />;
      default:
        return null;
    }
  }
}

export default BoardCell;