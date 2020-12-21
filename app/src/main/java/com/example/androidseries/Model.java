package com.example.androidseries;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;
import org.opencv.android.CameraBridgeViewBase;

import java.io.IOException;

public class Model {
    String simpleMlp;
    MultiLayerNetwork model;
    INDArray input;
    INDArray output;

    public Model() throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException {
        simpleMlp = new ClassPathResource("model.h5").getFile().getPath();
        model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);

        input = Nd4j.create(DataType.FLOAT, 256, 100);
        output = model.output(input);

        //model.fit(input, output);
    }
    public void fit(MultiLayerNetwork mod){
        mod.fit(input,output);
    }
}
