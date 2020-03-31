package com.zufar.socialpaymentparcer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Map<String, Double> outputSocialPayments = new HashMap<>();
        String separator = args[2];
        try {
            getSocialPaymentStream(args[0]).forEach((currentPayment) -> handlePayment(outputSocialPayments, currentPayment, separator));
            writeSocialPaymentsToFile(outputSocialPayments, args[1], separator);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private static void handlePayment(Map<String, Double> outputSocialPayments, String currentPayment, String separator) {
        String[] splittedPayment = currentPayment.split(separator);
        String socialNumber = splittedPayment[0];
        Double paymentSum = Double.parseDouble(splittedPayment[1]);
        outputSocialPayments.merge(socialNumber, paymentSum, Double::sum);
    }

    private static void writeSocialPaymentsToFile(Map<String, Double> outputSocialPayments, String filePath, String separator) throws IOException {
        Files.write(Paths.get(filePath), () -> outputSocialPayments
                .entrySet()
                .stream()
                .<CharSequence>map(e -> e.getKey() + separator + e.getValue())
                .iterator()
        );
    }

    private static Stream<String> getSocialPaymentStream(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath));
    }
}
