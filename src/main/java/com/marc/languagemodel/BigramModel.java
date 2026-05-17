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
}