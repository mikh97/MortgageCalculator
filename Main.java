package com.mikkhail;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args){

        int principal = (int) readNumber("Principal: ", 1000, 1000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte period = (byte) readNumber("Period (Years): ",1, 30);

        printMortgage(principal, annualInterest, period);
        printPaymentSchedule(principal, annualInterest, period);
    }

    private static void printMortgage(int principal, float annualInterest, byte period) {
        double mortgage = calculateMortage(principal, annualInterest, period);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("________");
        System.out.println("Mortgage: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte period) {
        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("________________");
        for (short month = 1; month <= period * MONTHS; month++) {
            double balance = calculateBalance(principal, annualInterest, period, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a VALID VALUE between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateBalance(int principal, float annualInterest, byte period, short numbersOfPaymentsMade) {

        float numberOfPayments = (period * MONTHS);
        float monthlyInterest = annualInterest / PERCENT / MONTHS;

        double balance = principal * (Math.pow((1 + monthlyInterest), numberOfPayments) - Math.pow(1+ monthlyInterest, numbersOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return balance;
    }

    public static double calculateMortage(int principal, float annualInterest, byte period){

        float numberOfPayments = (period * MONTHS);
        float monthlyInterest = annualInterest / PERCENT / MONTHS;

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return mortgage;
        }
    }
