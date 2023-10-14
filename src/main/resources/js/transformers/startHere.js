function vTransform(inputString) {
    var inputMethod = 1;  // RTS
    var outputMethod = 0; // Unicode
    var transformer = Transformer.createTransformer(inputMethod, outputMethod);
    var output = transformer.convert(inputString);
    return output;
}