package com.marc.languagemodel;

public class TextGenerator {
    private static final String BOS = "<bos>";
    private static final String EOS = "<eos>";

    private final LanguageModel model;

    public TextGenerator(LanguageModel model) {
        this.model = model;
    }

    public String generateSentence(int maxWords) {
        String current = BOS;
        String sentence = "";

        // keep predicting until the model says the sentence is done
        for (int i = 0; i < maxWords; i++) {
            String next = model.predictNext(current);
            if (next.equals(EOS)) {
                break;
            }

            if (!sentence.isEmpty()) {
                sentence += " ";
            }
            sentence += next;
            current = next;
        }

        return sentence;
    }
}
