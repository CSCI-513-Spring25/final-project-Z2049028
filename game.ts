interface GameState {
  cells: Cell[];
}

interface Cell {
  text: string;
  x: number;
  y: number;
}

export type { GameState, Cell };