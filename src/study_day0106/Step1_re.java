package study_day0106;

import java.util.Random;
import java.util.Scanner;

public class Step1_re {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int playerWin = 0;
        int comWin = 0 ;

        while (true){
            System.out.println("가위바위보를 시작합니다.!! 하기 싫으시면 X를 눌러주세요!");
            String playerSelect = scanner.nextLine();
            String com = String.valueOf(random.nextInt(3));
            int comNum =0;
            int playerNum =0;

            if(playerSelect.equals("X")){
                System.out.println("player 승리 : " +playerWin);
                System.out.println("com 승리 : "+comWin);
                playerWin =0;
                comWin =0;
                break;
            } else if (playerSelect.equals("바위")||playerSelect.equals("가위")||playerSelect.equals("보")) {
                int computerSelect;
                System.out.println("난이도를 입력하세요 : easy, medium, hard");
                String difficultLevel = scanner.nextLine().toLowerCase();

                switch (difficultLevel){
                    case "easy":
                        computerSelect = random.nextInt(3);
                        break;
                    case "medium":
                        computerSelect = (random.nextInt(3)+1)%3;
                        break;
                    case "hard":
                        computerSelect = (random.nextInt(3)+2)%3;
                        break;
                    default:
                        System.out.println("잘못된 난이도 입니다. 다시 선택하세요");
                        continue;
                }
                int playerMoveIndex = getPlayerMoveIndex(playerSelect);

                System.out.println("플레이어 : "+playerSelect);
                System.out.println("com : "+getMoveName(computerSelect));

                if (playerMoveIndex == (computerSelect + 1) % 3) {
                    System.out.println("플레이어 승리!");
                    playerWin++;
                } else if (playerMoveIndex == computerSelect) {
                    System.out.println("비겼습니다!");
                } else {
                    System.out.println("컴퓨터 승리!");
                    comWin++;
                }

                System.out.println("플레이어 승리: " + playerWin);
                System.out.println("컴퓨터 승리: " + comWin);
            } else {
                System.out.println("잘못된 입력입니다. 가위 ,바위 , 보 를 입력하십시오.");
            }



            }

        }

    private static int getPlayerMoveIndex(String playerSelect) {
        switch (playerSelect){
            case "바위" :
                return 0;
            case "가위":
                return 1;
            case "보":
                return 2;
            default:
                return -1;
        }
    }
    private static String getMoveName(int playerSelect){
        switch(playerSelect){
            case 0:
                return "바위";
            case 1:
                return "가위";
            case 2:
                return "보";
            default:
                return "잘못!";
        }
    }

}
//    private static int getPlayerMoveIndex(String move){
//        switch (move){
//            case "바위" :
//                return 0;
//            case "가위":
//                return 1;
//            case "보":
//                return 2;
//            default:
//                return -1;
//        }
//    }
//
//    private  static String getMoveName(int move){
//        switch(move){
//            case 0:
//                return "바위";
//            case 1:
//                return "가위";
//            case 2:
//                return "보";
//            default:
//                return "잘못!";
//        }
//    }
//}
