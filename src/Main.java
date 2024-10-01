import service.Board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        System.out.println(board.toString());

        board.update("start");
        System.out.println(board.toString());

        while (true) {
            System.out.println("Input move other side:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit"))
                break;
            board.update(input);
            System.out.println(board.toString());
        }
        System.out.println("Game over");
    }
}