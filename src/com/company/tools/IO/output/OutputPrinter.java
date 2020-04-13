package com.company.tools.IO.output;

import com.company.tools.IO.BaseIOHandler;
import com.company.tools.IO.FileURIComponents;
import com.company.tools.math.Sigmoid;
import com.company.utils.exception.UnableToWriteFileException;
import com.company.utils.io.FolderUtils;

import java.io.FileWriter;
import java.io.IOException;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class OutputPrinter extends BaseIOHandler {
    private final String initialInformationFileName = "MLP_initial_settings";

    public OutputPrinter(String fileName, String fileExtension) {
        super(FileURIComponents.OUTPUT_FOLDER_NAME + "\\" + fileName, fileExtension);
    }

    public OutputPrinter(String path) {
        super(FileURIComponents.OUTPUT_FOLDER_NAME + "\\" + path);
    }

    public OutputPrinter() {
        super(FileURIComponents.OUTPUT_FOLDER_NAME);
    }

    public void printMLPInitialInformation(String function, double learningRate, int epochNumber, int hiddenLayerSize, String inputFile, int inputLayerSize, int outputLayerSize) {
        String fileName = path + "\\" + initialInformationFileName + FileURIComponents.TXT_EXT;

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.append("MLP Execution Informations:");
            writer.append('\n');

            writer.append("Problem input read from: ");
            writer.append(inputFile);
            writer.append('\n');

            writer.append("Number of Neurons in Input Layer (with bias): ");
            writer.append(String.valueOf(inputLayerSize));
            writer.append('\n');

            writer.append("Number of Neurons in Output Layer: ");
            writer.append(String.valueOf(outputLayerSize));
            writer.append('\n');

            writer.append("Number of Neurons in Hidden Layer (with bias): ");
            writer.append(String.valueOf(hiddenLayerSize));
            writer.append('\n');

            writer.append("Max Epochs: ");
            writer.append(String.valueOf(epochNumber));
            writer.append('\n');

            writer.append("Learning Rate: ");
            writer.append(String.valueOf(learningRate));
            writer.append('\n');

            writer.append("Function: ");
            writer.append(function);
            writer.append('\n');

            writer.flush();
            writer.close();

        } catch (IOException e) {
            throw new UnableToWriteFileException(e.getMessage());
        }
    }

    public void printMLPInitialInformation() {
        String fileName = path + "\\" + initialInformationFileName + FileURIComponents.TXT_EXT;

        try {
            FileWriter writer = new FileWriter(fileName);



            writer.flush();
            writer.close();

        } catch (IOException e) {
            throw new UnableToWriteFileException(e.getMessage());
        }
    }
}
