// TOKENIZER: The Tokenizer converts raw text into a clean, flat list of lowercase 
// word tokens wrapped in sentence boundary markers, which gives the BigramModel a consistent format to learn from.

// PSEUDOCODE: 
// tokenize(String text):
//     lowercase the entire text
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
