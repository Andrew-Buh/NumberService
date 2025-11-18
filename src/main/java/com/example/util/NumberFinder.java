package com.example.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.PriorityQueue;

@Slf4j
@Component
public class NumberFinder {

    public int findNthMinimum(int[] numbers, int n) {
        validateInput(numbers, n);

        log.debug("Поиск {}-го минимального числа в массиве из {} чисел", n, numbers.length);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int number : numbers) {
            if (maxHeap.size() < n) {
                maxHeap.offer(number);
            } else if (number < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(number);
            }
        }

        if (maxHeap.size() < n) {
            throw new IllegalArgumentException(
                    String.format("В файле только %d чисел, но запрошено %d-е минимальное", maxHeap.size(), n)
            );
        }

        int result = maxHeap.peek();
        log.debug("Найдено {}-е минимальное число: {}", n, result);

        return result;
    }

    private void validateInput(int[] numbers, int n) {
        if (numbers == null) {
            throw new IllegalArgumentException("Массив чисел не может быть null");
        }

        if (numbers.length == 0) {
            throw new IllegalArgumentException("Массив чисел не может быть пустым");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("N должно быть положительным числом");
        }

        if (n > numbers.length) {
            throw new IllegalArgumentException(
                    String.format("N (%d) не может быть больше количества чисел в файле (%d)", n, numbers.length)
            );
        }
    }
}
