# Bigram Language Model

A bigram language model built in Java from scratch for my CISC 191 Honors Contract at Mesa College.

## What it does

Reads a large text corpus, learns how often each word follows every other word, and uses those patterns to generate new text and score how natural a sequence of words sounds.

## How it works

1. A **Tokenizer** cleans raw text and splits it into word tokens
2. A **BigramModel** counts how often word B follows word A across the entire corpus
3. A **TextGenerator** uses those counts to generate new sentences word by word
4. A **Negative Log Likelihood** score measures how well the model fits unseen text — lower is better

## Tech Stack

- Java 17, Maven 3.9, JUnit 5
- No external ML libraries — everything is built from scratch using HashMaps

## Project Structure

```
src/
├── main/java/com/marc/languagemodel/
│   ├── LanguageModel.java     # interface defining the model contract
│   ├── Tokenizer.java         # cleans and tokenizes raw text
│   ├── BigramModel.java       # trains on token sequences and computes probabilities
│   ├── TextGenerator.java     # generates text using a trained model
│   └── Main.java              # loads corpus, trains model, generates text, prints NLL
└── test/java/com/marc/languagemodel/
    └── TokenizerTest.java     # unit tests for the tokenizer
```
