package com.example.androidseries;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.io.ClassPathResource;
import java.io.IOException;

public class Model {


    public Model() throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        String simpleMlp = new ClassPathResource("model.h5").getFile().getPath();
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
    }
}
