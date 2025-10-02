import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlgorithmicStreams {

    // Method to check if a number is prime
    private static boolean isPrime(int number) {
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(i -> number % i == 0);
    }

    // Method to generate a list of prime numbers up to a given limit
    private static List<Integer> generatePrimes(int limit) {
        return IntStream.rangeClosed(2, limit)
                .filter(AlgorithmicStreams::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }

    // Method to generate Fibonacci numbers up to a given count
    private static List<Integer> generateFibonacci(int count) {
        return IntStream.range(0, count)
                .map(n -> {
                    if (n == 0) return 0;
                    if (n == 1) return 1;
                    return generateFibonacci(n - 1).get(n - 1) + generateFibonacci(n - 2).get(n - 2);
                })
                .boxed()
                .collect(Collectors.toList());
    }

    // Method to calculate the average of a list of integers
    private static double calculateAverage(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    // Method to calculate the sum of squares of a list of integers
    private static int sumOfSquares(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> n * n)
                .sum();
    }

    // Method to find the maximum number in a list
    private static Optional<Integer> findMax(List<Integer> numbers) {
        return numbers.stream()
                .max(Integer::compareTo);
    }

    public static void main(String[] args) {
        // Generate prime numbers up to 100
        List<Integer> primes = generatePrimes(100);
        System.out.println("Prime numbers up to 100: " + primes);

        // Generate the first 10 Fibonacci numbers
        List<Integer> fibonacci = generateFibonacci(10);
        System.out.println("First 10 Fibonacci numbers: " + fibonacci);

        // Calculate average of prime numbers
        double averagePrimes = calculateAverage(primes);
        System.out.printf("Average of prime numbers: %.2f%n", averagePrimes);

        // Calculate sum of squares of Fibonacci numbers
        int sumSquaresFibonacci = sumOfSquares(fibonacci);
        System.out.println("Sum of squares of Fibonacci numbers: " + sumSquaresFibonacci);

        // Find the maximum prime number
        Optional<Integer> maxPrime = findMax(primes);
        maxPrime.ifPresent(max -> System.out.println("Maximum prime number: " + max));
    }
}
