package Project_solo.가중치부여;
import java.util.Random;

public class DynamicWeightAdjustment {

    public static void main(String[] args) {
        // 1, 2, 3, 4, 5 값들과 각 값에 부여된 가중치
        int[] values = {1, 2, 3, 4, 5};
        double[] weights = {0.1, 0.1, 0.1, 0.1, 0.1};

        // 3번이 선택된 횟수를 저장하는 변수
        int countOfThree = 0;

        // 랜덤 객체 생성
        Random random = new Random();

        // 가중치를 기반으로 선택
        for (int i = 0; i < 100; i++) { // 예제로 100번 반복
            int selectedValue = selectValueWithDynamicWeight(values, weights, countOfThree, random);

            // 3번이 선택된 경우 countOfThree 증가
            if (selectedValue == 3) {
                countOfThree++;
            }

            System.out.println("선택된 값: " + selectedValue);
        }
        System.out.println("3이 나온 횟수 : "+countOfThree);
    }

    private static int selectValueWithDynamicWeight(int[] values, double[] weights, int countOfThree, Random random) {
        double totalWeight = 0.0;

        // 3번이 선택된 횟수에 따라 가중치 동적 조절
        weights[2] += countOfThree * 0.01;

        // 전체 가중치 계산
        for (double weight : weights) {
            totalWeight += weight;
        }

        // 0부터 전체 가중치까지의 난수 생성
        double randomValue = random.nextDouble() * totalWeight;

        // 각 값에 대한 가중치를 더하면서 난수와 비교하여 선택
        double cumulativeWeight = 0.0;
        for (int i = 0; i < values.length; i++) {
            cumulativeWeight += weights[i];
            if (randomValue < cumulativeWeight) {
                return values[i];
            }
        }

        // 여기까지 왔다면 오류 상황, 예외 처리 필요
        throw new RuntimeException("선택 중 오류가 발생했습니다.");
    }
}