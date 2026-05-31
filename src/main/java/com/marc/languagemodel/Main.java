package com.marc.languagemodel;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "The dog ran home. The dog ate food. The cat ran home. "
                + "The cat ate food. The dog slept. The cat slept. "
                + "A student wrote code. A student tested code. "
                + "The model learned words. The model generated words.";

        Tokenizer tokenizer = new Tokenizer();
        List<String> tokens = tokenizer.tokenize(text);

        int splitIndex = (int) (tokens.size() * 0.8);
        List<String> trainTokens = tokens.subList(0, splitIndex);
        List<String> validationTokens = tokens.subList(splitIndex, tokens.size());

        BigramModel model = new BigramModel();
        model.train(trainTokens);

        TextGenerator generator = new TextGenerator(model);

        // basic demo for now, just to prove the whole pipeline works
        System.out.println("total tokens: " + tokens.size());
        System.out.println("training tokens: " + trainTokens.size());
        System.out.println("validation tokens: " + validationTokens.size());
        System.out.println("generated text: " + generator.generateSentence(20));
        System.out.println("validation nll: " + model.negativeLogLikelihood(validationTokens));
    }
}
