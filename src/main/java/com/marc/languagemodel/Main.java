package com.marc.languagemodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Path.of("src/main/resources/frankenstein.txt"));
        text = cleanGutenbergText(text);

        Tokenizer tokenizer = new Tokenizer();
        List<String> tokens = tokenizer.tokenize(text);

        int splitIndex = (int) (tokens.size() * 0.8);
        List<String> trainTokens = tokens.subList(0, splitIndex);
        List<String> validationTokens = tokens.subList(splitIndex, tokens.size());

        BigramModel model = new BigramModel();
        model.train(trainTokens);

        TextGenerator generator = new TextGenerator(model);

        // trained on frankenstein, then tested on the last chunk of tokens
        System.out.println("corpus: Frankenstein by Mary Shelley");
        System.out.println("total tokens: " + tokens.size());
        System.out.println("training tokens: " + trainTokens.size());
        System.out.println("validation tokens: " + validationTokens.size());
        System.out.println("generated text: " + generator.generateSentence(20));
        System.out.println("validation nll: " + model.negativeLogLikelihood(validationTokens));
    }

    private static String cleanGutenbergText(String text) {
        int start = text.indexOf("*** START OF THE PROJECT GUTENBERG EBOOK");
        int end = text.indexOf("*** END OF THE PROJECT GUTENBERG EBOOK");

        if (start != -1) {
            start = text.indexOf("***", start + 3);
            if (start != -1) {
                text = text.substring(start + 3);
            }
        }

        if (end != -1) {
            text = text.substring(0, end);
        }

        return text;
    }
}
