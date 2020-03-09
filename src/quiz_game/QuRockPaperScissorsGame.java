package quiz_game;

import java.util.Random;
import java.util.Scanner;

public class QuRockPaperScissorsGame
{

	static Scanner scanner = new Scanner(System.in);
	static int gameCount = 0; // 게임의 횟수를 카운트하기 위한 변수

	public static int initUser()
	{
		int user = 0;
		System.out.println("가위바위보를 입력하세요");
		System.out.print("가위(1),바위(2),보(3)=>");
		user = scanner.nextInt();
		return user;
	}

	public static int initCom()
	{
		Random random = new Random();
		// 난수생성 - 컴퓨터입장의 가위/바위/보
		int com = random.nextInt(10000) % 3 + 1;// 1~3사이의난수생성
		System.out.println("컴퓨터:" + com);
		return com;
	}

	public static int reAnswer()
	{
		int restart;
		System.out.print("게임재시작(1), 종료(0):");
		restart = scanner.nextInt();
		return restart;

	}

	public static void reGame()
	{
		// 5번의 게임이 되었는지 판단후 재시작/종료 확인
		if (gameCount >= 5)
		{
			System.out.println("게임을 또 하시겠습니까?");
			int restart;

			while (true)
			{
				restart = reAnswer();

				if (restart == 0 || restart == 1)
				{
					// 정상입력이면 루프탈출
					break;
				} else
				{
					System.out.println("잘못입력해씸ㅜㅜ\n");
				}
			}
			if (restart == 0)
			{
				System.exit(0);
			} else
			{
				System.out.println("게임 재시작^^");
				gameCount = 0;
				rpsGameStart();

			}
		}
	}

	public static void rpsGameStart()
	{
		while (true)
		{
			int user = initUser();
			int com = initCom();
			// 3.승부판단 및 결과출력
			if (!(user < 1 || user > 3))
			{
				// 정확하게 입력했을때 승부를 판단
				System.out.printf("사용자:%s, 컴퓨터:%s ", displayRPS(user), displayRPS(com));

				// 승부판단로직
				switch (user - com)
				{
				case 0:
					System.out.println("비겼습니다");
					break;
				case 1:
				case -2:
					System.out.println("이겼습니다");
					break;
				case -1:
				case 2:
					System.out.println("졌습니다");
					break;
				}

				// 게임카운트 증가
				gameCount++;
				reGame();
			} else
			{
				System.out.println("가위바위보 할줄 모름??븅신~");
			}
		}
	}

	// 가위 바위 보를 출력하는 메소드
	public static String displayRPS(int n)
	{
		String str = "";
		switch (n)
		{
		case 1:
			str = "가위";
			break;
		case 2:
			str = "바위";
			break;
		case 3:
			str = "보";
			break;
		}
		return str;
	}

	public static void main(String[] args)
	{
		// 가위바위보 게임 메소드 호출
		rpsGameStart();
	}
}