package BattagliaNavale;

import java.util.Scanner;

public class BattagliaNavale {

	// Grandezza tabella
	static final int BOARD_SIZE = 11;

	//Tabella CPU e Giocatore
	static char[][] playerBoard = new char[BOARD_SIZE][BOARD_SIZE];
	static char[][] cpuBoard = new char[BOARD_SIZE][BOARD_SIZE];

	// Navi dei giocatori
	static int playerShips = 10;
	static int cpuShips = 10;

	public static void main(String[] args) {

		initBoards();			//crea tabella
		
		setupPlayerShips();		//metti navi Giocatore
		
		setupCpuShips();		//metti navi CPU
		
		playGame();				//inizio gioco
	}

	// Crea bordo con l'acqua ('~')
	private static void initBoards() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				playerBoard[i][j] = '~';
				cpuBoard[i][j] = '~';
			}
		}
		System.out.println((playerBoard));
	}

	// Sistemazione navi Giocatore                                              *****FARE CHECK STESSE COORDINATE*****
	private static void setupPlayerShips() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Inserisci coordinate delle tue 5 navi: ");
		// Coordinate input navi 
		for (int i = 0; i < playerShips; i++) {
			System.out.println("Nave " + (i + 1) + ": ");
			System.out.print("X: ");
			int x = input.nextInt();
			System.out.print("Y: ");
			int y = input.nextInt();

			// Posiziona navi
			playerBoard[y][x] = 'S';
		}
	}

	// Sistemazione navi CPU
	private static void setupCpuShips() {
		for (int i = 0; i < cpuShips; i++) {
			// Coordinate random navi
			int x = (int)(Math.random() * BOARD_SIZE);
			int y = (int)(Math.random() * BOARD_SIZE);

			// Posiziona navi
			cpuBoard[y][x] = 'S';
		}
	}

	// Gioco
	private static void playGame() {
		Scanner input = new Scanner(System.in);
		boolean gameOver = false;

		while (!gameOver) {
			// Turno giocatore
			System.out.println("Inserisci le coordinate per attaccare: ");
			int x = input.nextInt();
			System.out.println(",");
			int y = input.nextInt();

			// Check nave colpita
			if (cpuBoard[y][x] == 'S') {
				System.out.println("Hai colpito una nave!");
				cpuBoard[y][x] = 'X';
				cpuShips--;
				System.out.println(cpuBoard);

				// Check tutte le navi distrutte
				if (cpuShips == 0) {
					System.out.println("Hai vinto!");
					gameOver = true;
					break;
				}
			} else {
				System.out.println("Mancato!");
			}

			// Turno CPU
			x = (int)(Math.random() * BOARD_SIZE);
			y = (int)(Math.random() * BOARD_SIZE);

			// Check nave colpita
			if (playerBoard[y][x] == 'S') {
				System.out.println("L'avversario ha colpito una nave!");
				playerBoard[y][x] = 'X';
				playerShips--;
				System.out.println(playerBoard);

				// Check tutte le navi distrutte
				if (playerShips == 0) {
					System.out.println("Hai perso!");
					gameOver = true;
					break;
				}
			} else {
				System.out.println("L'avversario ti ha mancato!");
			}
		}
	}
}