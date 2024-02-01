package Project_solo.가중치부여;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DynamicWeight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> values = new ArrayList<>();
        List<Double> weights = new ArrayList<>();

        System.out.println("장르 입력: ");
        String inputGenre = scanner.nextLine();
        values.add(inputGenre);
        weights.add(0.1); // 초기 가중치 설정

        // 해당 언어가 선택된 횟수 저장 변수
        int count = 0;

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String selectedValue = dynamicWeightValues(values, weights, count, random);

            if (selectedValue.equals(inputGenre)) {
                count++;
            }
            System.out.println("골라진 값: " + selectedValue);
        }
        System.out.println("골라진 값이 나온 횟수: " + count);
    }

    public static String dynamicWeightValues(List<String> values, List<Double> weights, int count, Random random) {
        double totalWeight = 0.0;

        // 선택된 횟수에 따라 가중치 조절
        weights.set(0, weights.get(0) + count * 0.01);

        // 전체 가중치 더하기
        for (double w : weights) {
            totalWeight += w;
        }

        double randomValue = random.nextDouble();

        double cumulativeWeight = 0.0;
        for (int i = 0; i < values.size(); i++) {
            cumulativeWeight += weights.get(i);
            if (randomValue < cumulativeWeight) {
                return values.get(i);
            }
        }

        throw new RuntimeException("오류!!");
    }
}