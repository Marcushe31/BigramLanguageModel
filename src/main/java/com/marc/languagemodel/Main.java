package com.marc.languagemodel;

public class Main {
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        System.out.println(tokenizer.tokenize("Hello world! This is a test."));
    }
}
