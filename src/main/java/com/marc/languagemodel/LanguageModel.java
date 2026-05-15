package com.marc.languagemodel;
import java.util.List;

public interface LanguageModel {
    void train(List<String> tokens);
    double probability(String target, String... context);
    String predictNext(String... context);
    double negativeLogLikelihood(List<String> tokens); 
}