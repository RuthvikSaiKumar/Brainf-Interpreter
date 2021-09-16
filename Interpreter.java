package com.company.brainf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(brainf.txt");
        Scanner reader = new Scanner(file);
        StringBuilder code = new StringBuilder();
        while (reader.hasNextLine())
            code.append(reader.nextLine());
        ArrayList<Character> legalCharacters = new ArrayList<>(Arrays.asList('+', '-', '<', '>', '.', ',', '[', ']', '='));
        ArrayList<String> tokens = new ArrayList<>();
        int looper = 0;
        for (int i = 0; i < code.length(); i++) {
            if (legalCharacters.contains(code.charAt(i)))
                tokens.add("" + code.charAt(i));
            if (code.charAt(i) == '[')
                looper++;
            if (code.charAt(i) == ']')
                looper--;
        }
        if (looper != 0)
            throw new IllegalStateException("The number of '[' is not equal to the number of ']'");
        interpret(tokens);
    }

    private static void interpret(ArrayList<String> headacheTokens) {

        int[] memory=new int[30000];
        int pointer=0;

        for (int i = 0; i < headacheTokens.size(); i++) {
            switch (headacheTokens.get(i)){
                case "+":
                    memory[pointer]++;
                    break;
                case "-":
                    memory[pointer]--;
                    break;
                case ">":
                    pointer++;
                    break;
                case "<":
                    pointer--;
                    if(pointer<0)
                        throw new IllegalStateException("Pointer value less than 0 is not possible");
                    break;
                case ",":
                    memory[pointer]=new Scanner(System.in).nextInt();
                    break;
                case ".":
                    System.out.print((char)memory[pointer]);
                    break;
                case "[":
                    break;
                case "]":
                    break;
            }
        }
    }
    private static void exec(char command){

    }
}
