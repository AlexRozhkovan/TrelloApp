package spd.trello.service;

import spd.trello.domain.Board;
import spd.trello.domain.Card;
import spd.trello.repository.BoardRepository;
import spd.trello.repository.CardRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardService extends AbstractService<Board> {
    public BoardService(BoardRepository repository) {
        super(repository);
    }

    @Override
    public Board create(Scanner scanner) {
        Board board = new Board();
        System.out.println("Enter board name");
        board.setName(scanner.nextLine());
        System.out.println("Enter board description");
        board.setDescription(scanner.nextLine());
        repository.create(board);
        return board;
    }

    @Override
    public List<Board> findAll() {
        return repository.findAll();
    }

    @Override
    public void print(Board entity) {
        System.out.println(entity);
    }
    /*static List<Board> storage = new ArrayList();
    @Override
    public Board create() {
        Board board = new Board();
        Scanner sc = new Scanner(System.in);
        System.out.print("Input name: ");
        String name = sc.nextLine();
        board.setName(name);
        storage.add(board);
        return board;
    }

    @Override
    public void update(int index, Board board) {
        Board board1 = storage.get(index);
        Scanner sc = new Scanner(System.in);
        System.out.print("Input name: ");
        String newName = sc.nextLine();
        board1.setName(newName);
        board1.setUpdatedDate(LocalDateTime.now());
*/

}
