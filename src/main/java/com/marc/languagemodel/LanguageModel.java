package com.marc.languagemodel;
import java.util.List;

// interface placeholder for the LanguageModel, which will be implemented by BigramModel and TrigramModel.
public interface LanguageModel {
    void train(List<String> tokens);
    double probability(String target, String... context);
    String predictNext(String... context);
    double negativeLogLikelihood(List<String> tokens); 
}