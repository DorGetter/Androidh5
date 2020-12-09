package com.example.androidseries;

import org.nd4j.linalg.io.ClassPathResource;

import java.io.IOException;

public class Model {
    public Model() throws IOException {
        String simpleMlp = new ClassPathResource("model.h5").getFile().getPath();
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
    }
}
