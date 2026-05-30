package com.marc.languagemodel; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set; 
// BIGRAM MODEL: simple bigram model that will calculate the probability of a target word GIVEN A SINGLE word of context.
public class BigramModel implements LanguageModel {
    private static final String BOS = "<bos>";
    private static final String EOS = "<eos>";
    // UNKNOWN TOKEN: <unk> is used to represent a word that wasn't seen during its training,
    //  allows model to handle any unseen words without crashing or giving zero probability.
    private static final String UNK = "<unk>";
    private static final double EPSILON = 1e-10; // THIS AVOID DIVISON BY ZERO IN PROBABILITY CALCULATIONS!!

    // where everything actually lives. outer key is the context (a List so we can scale bigram -> trigram later, like prof chow said)
    // inner map is every word weve seen after that context + how many times we saw it
    private final Map<List<String>, Map<String, Integer>> counts = new HashMap<>();
    // every unique word seen during training. used later for the <unk> handling
    private final Set<String> vocabulary = new HashSet<>();

    // TRAIN: counts up all the adjacent word pairs so we know what tends to follow what // 5/15/26: DONE
    @Override
    public void train(List<String> tokens) {
        // first pass, just grab every word we see so we know our vocab later
        for (String token : tokens) {
            vocabulary.add(token);
        }
        // now the actual counting. stop one before the end since the last token has no "next" word
        for (int i = 0; i < tokens.size() - 1; i++) {
            // single word of context for now, but its a List so bumping up to trigram later is easy
            List<String> context = List.of(tokens.get(i));
            String target = tokens.get(i + 1);
            // if we havent seen this context yet, give it a fresh inner map
            counts.putIfAbsent(context, new HashMap<>());
            Map<String, Integer> nextWords = counts.get(context);
            // add 1 to however many times target showed up after this context
            nextWords.put(target, nextWords.getOrDefault(target, 0) + 1);
        }
    }

    // PROBABILITY: how likely is target to show up right after the context word? // 5/28/26: DONE
    @Override
    public double probability(String target, String... context) {
        // bigram only needs one word of context, the word right before
        String prev = context[0];
        // if we never saw either word in training, swap it out for the unknown token
        if (!vocabulary.contains(prev)) {
            prev = UNK;
        }
        if (!vocabulary.contains(target)) {
            target = UNK;
        }
        // grab everything that followed prev when we trained
        Map<String, Integer> nextWords = counts.get(List.of(prev));
        // never seen this context at all? return a tiny number so log() doesnt blow up later
        if (nextWords == null) {
            return EPSILON;
        }
        // how many times target followed prev, over the total times prev was a context
        int targetCount = nextWords.getOrDefault(target, 0);
        int total = 0;
        for (int count : nextWords.values()) {
            total += count;
        }
        return (targetCount / (double) total) + EPSILON;
    }

    // TODO: still gotta finish these two next time
    @Override
    public String predictNext(String... context) {
        return null;
    }

    @Override
    public double negativeLogLikelihood(List<String> tokens) {
        return 0.0;
    }
}