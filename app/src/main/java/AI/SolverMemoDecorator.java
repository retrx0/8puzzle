package AI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import model.Board;
import model.Path;

public class SolverMemoDecorator implements Solver {

    private Solver solver;
    private Map<Board, Path> cache;

    public SolverMemoDecorator(Solver solver) {
        this.solver = solver;
        cache = new HashMap<Board, Path>();
    }

    @Override
    public Path shortestPath(Board start) {
        Path path = cache.get(start);
        if (path != null) return path;
        path = solver.shortestPath(start);
        cache.put(start, path); // TODO: Put other boards of path in cache too?
        return path;
    }

    @Override
    public Board generateBoard() {
        return solver.generateBoard();
    }

    @Override
    public List<Board> generateBoards(int numBoards) {
        return solver.generateBoards(numBoards);
    }

}
