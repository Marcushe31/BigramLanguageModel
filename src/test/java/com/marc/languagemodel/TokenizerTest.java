package com.marc.languagemodel;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenizerTest {
    @Test
    public void tokenizesSimpleSentences() {
        Tokenizer tokenizer = new Tokenizer();

        List<String> tokens = tokenizer.tokenize("Hello world! This is a test.");

        assertEquals(List.of(
                "<bos>", "hello", "world", "<eos>",
                "<bos>", "this", "is", "a", "test", "<eos>"
        ), tokens);
    }

    @Test
    public void removesPunctuationAndNumbers() {
        Tokenizer tokenizer = new Tokenizer();

        List<String> tokens = tokenizer.tokenize("Code123 works, maybe?");

        assertEquals(List.of("<bos>", "code", "works", "maybe", "<eos>"), tokens);
    }

    @Test
    public void ignoresEmptyInput() {
        Tokenizer tokenizer = new Tokenizer();

        List<String> tokens = tokenizer.tokenize("!!! 123 ???");

        assertTrue(tokens.isEmpty());
    }
}
