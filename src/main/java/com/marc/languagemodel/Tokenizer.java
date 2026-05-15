package com.marc.languagemodel;
// TOKENIZER: The Tokenizer converts raw text into a clean, flat list of lowercase 
// word tokens wrapped in sentence boundary markers, which gives the BigramModel a consistent format to learn from.

// PSEUDOCODE: 
// tokenize(String text):
//     lowercase the entire text // 5/12/26: DONE 
//     split into sentences by . ! ?
    
//     for each sentence:
//         split into words by whitespace
//         for each word:
//             strip anything that's not a letter (a-z)
//             if word is not empty:
//                 add it to the sentence token list
        
//         if sentence has at least one word:
//             prepend <bos>
//             append <eos>
//             add all tokens to the final list
    
//     return final list

// EXMPLE:
// Input: "Hello world! This is a test."
// Output: ["<bos>", "hello", "world", "<eos>", "<bos>", "this", "is", "a", "test", "<eos>"]

import java.util.ArrayList;
import java.util.List;          

public class Tokenizer { 
    public List<String> tokenize(String text) {
        ArrayList<String> tokens = new ArrayList<>();
        // LOWERCASES THE ENTIRE TEXT // 5/12/26: DONE
        text = text.toLowerCase();
        // SPLITS INTO SENTENCES BY . ! ? // 5/12/26: DONE 
        String[] sentences = text.split("[.!?]+");
            // for each sentence:
        for (String sentence : sentences) {
            // split into words by whitespace
            String[] words = sentence.trim().split("\\s+");
            ArrayList<String> sentenceTokens = new ArrayList<>();
            // for each word:
            for (String word : words) {
                // we want to strip anything that's not a letter (a-z)
                word = word.replaceAll("[^a-z]", "");
                // if word is not empty:
                if (!word.isEmpty()) {
                    // want to add it to the sentence token list
                    sentenceTokens.add(word);
                }
            }
            // if the sentence has at least one word:
            if (!sentenceTokens.isEmpty()){
                // we want to add <bos> to the beginning of the sentence
                sentenceTokens.add(0, "<bos>");
                // we want to add <eos> to the end of the sentence
                sentenceTokens.add("<eos>");
                // then, we want to add all the tokens to the final list
                tokens.addAll(sentenceTokens);
            }
        }
        return tokens;
    }
}



