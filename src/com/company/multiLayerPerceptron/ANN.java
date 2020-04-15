package com.company.multiLayerPerceptron;

import com.company.tools.IO.log.Logger;
import com.company.tools.generator.IntGenerator;
import com.company.tools.math.IFunction;
import com.company.utils.doubleConverter.DoubleConverter;
import com.company.utils.exception.ExitStatus;
import com.company.utils.exception.GlobalExceptionHandler;

/**
 Created by: Felipe Lodes in 07/04/2020.
 Discipline: ACH2016 - "Inteligência Artificial"
 Professor: Sarajane

 *Copyright (c) 2020 FelipeLodes to Present. All rights reserved.*

 **/

public class ANN {

    /**
     * Eu criei a dependencia do arquivo de leitura dos dados de entrada e da função utilizada, aqui,
     * apenar para printá-los no logger
     */
    private String fileReference = "";
    private String functionTag = "";

    private int inputLayerNeuronNumber;
    private int outputLayerNeuronNumber;
    private int hiddenLayerNeuronNumber;

    private double learningRate;
    private int epochMaxNumber;

    private int currentEpoch;

    private Double[][] hiddenWeightMatrix;
    private Double[][] outputWeightMatrix;

    private Double[][] outputCorrectionTerm;
    private Double[][] hiddenCorrectionTerm;

    /**
     * Função de ativação utilizada
     */
    IFunction<Double, Double> activationFunction;

    /**
     * Os atributos inputDataSet e outputDataSet referem-se aos dados de entrada e os resultados esperados.
     * Servem exclusivamente para o treinamento da rede.
     *
     * Diferem-se do inputXVector e do expectedYVector apenas pelo fato de que esses dois são instancias dos dois de cima.
     * Enquanto os de cima referem-se ao dataset completo de treinamento, os de baixo referem-se a entrada e resultado esperado de
     * uma unica execução.
     */
    private Double inputDataSet[][];
    private Double outputDataSet[][];

    private Double inputXVector[];
    private Double expectedYvector[];
    private Double obtainedYVector[];
    private Double hiddenZVector[];

    private Double hiddenErrorInformation[];
    private Double outputErrorInformation[];

    /**
     * Aqui usamos Bias igual a 1 (usamos na camada de entrada e na escondida)
     */
    private final double bias = 1;

    /***
     * O injetor de dependencia (dependency injetor) é responsavel por instanciar os objetos de acordo com os parametros passados
     *
     */
    public ANN(int inputLayerNeuronNumber,
               int outputLayerNeuronNumber,
               int hiddenLayerNeuronNumber,
               double learningRate,
               int epochMaxNumber,
               IFunction<Double, Double> activationFunction,
               Double[][] inputXVectors,
               Double[][] expectedYVectors,
               Double[][] hiddenWeightMatrix,
               Double[][] outputWeightMatrix) {
        this.inputLayerNeuronNumber = inputLayerNeuronNumber;
        this.outputLayerNeuronNumber = outputLayerNeuronNumber;
        this.hiddenLayerNeuronNumber = hiddenLayerNeuronNumber;
        this.learningRate = learningRate;
        this.epochMaxNumber = epochMaxNumber;
        this.activationFunction = activationFunction;
        this.currentEpoch = 0;

        this.inputDataSet = inputXVectors;
        this.outputDataSet = expectedYVectors;

        this.inputXVector = inputXVectors[0];
        this.expectedYvector = expectedYVectors[0];

        this.hiddenWeightMatrix = hiddenWeightMatrix;
        this.outputWeightMatrix = outputWeightMatrix;

        this.hiddenZVector = DoubleConverter.toDouble(new double[hiddenLayerNeuronNumber]);
        this.obtainedYVector = DoubleConverter.toDouble(new double[outputLayerNeuronNumber]);

        this.outputErrorInformation = DoubleConverter.toDouble(new double[outputLayerNeuronNumber]);
        this.hiddenErrorInformation = DoubleConverter.toDouble(new double[hiddenLayerNeuronNumber - 1]); // not considering bias

        this.outputCorrectionTerm = DoubleConverter.toDouble(new double[outputWeightMatrix.length][outputWeightMatrix[0].length]);
        this.hiddenCorrectionTerm = DoubleConverter.toDouble(new double[hiddenWeightMatrix.length][hiddenWeightMatrix[0].length]);
        setBias();
    }

    /**
     * Aqui setamos o bias na camada de entrada e na camada escondida.
     * Quando instaciamos o vetor de entrada e o de camada escondida no injetor de dependencia (Dependency Injector),
     * Já os instanciamos com o tamanho da entrada somada e o tamanho da camada escondida, passado como parametro, a 1.
     * Assim, se o número de neurônios na camada escondida é 4 o tamanho real da camada, para fins de execução, é 5.
     * Essa abordagem trouxe a necessidade de tomar cuidados especificos com as etapas de feedforward,
     * backpropagation e ajuste de pesos para não considerar pesos que não existiam
     * (como peso de bias pra bias, por exemplo, que não existe).
     */
    private void setBias() {
        this.inputXVector[0] = bias;
        this.hiddenZVector[0] = bias;
    }

    private void updateCurrentEpoch() {
        this.currentEpoch++;
    }

    /**
     * Aqui iniciamos o treinamento da rede com os parametros recebidos pelo injetor de dependencia (Dependency Injector).
     * Antes de efetivamente começar o processamento de feedforward geramos log da informações de configuração da rede
     * em logMLPInitialInfos() - criamos uma classe Logger que recebe a ANN como parametro para gerar os logs especificos
     * como pesos, erros, informações de configurações e resultados.
     *
     * Além disso, criamos a classe GlobalExceptionHandler para lidar com possiveis exceptions que ocorram durante a execução
     * (o único caso de exceptions que tivemos, foi para ler o csv de entrada, mas resolvemos esse problema criando uma pasta
     * "resources/input" dentro do projeto e lemos os arquivos de lá)"
     * Essa classe também gera um arquivo de log de erro. Esperamos que ela não venha a ser executada rsrsrsrs.
     * Além disso, interrompemos imediatamente a execução caso isso venha acontecer, para que não coletemos resultados errados.
     *
     * Ao final do treinamento, logamos os resultados com o método logResults().
     *
     */
    public void train() {
        try {
            logMLPInitialInfos();
            startTraining();
        } catch(Exception e) {
            GlobalExceptionHandler.handle(e);
             System.exit(ExitStatus.FINISHED_WITH_ERROR);
        } finally {
            logResults();
        }
    }

    private void logResults() {
        Logger.getInstance().logResults(this);
    }

    private void logMLPInitialInfos() {
        Logger.getInstance().logNeuralNetworkInfo(this);
    }

    /**
     * O método abaixo é utilizado para fazer predições após o processo de treinamento da rede,
     * Ele não é chamado durante a execução do treinamento. Aqui, recebemos a instancia do parametro
     * de entrada e aplicamos a multiplicação das entradas pelos pesos que o interligam com a camada escondida,
     * gerando os valores de entrada da camada escondida, que serão multiplicados pelos pesos que interligam
     * essa ultima com a camada de saida, gerando os valores na camada de saida, que são o resultado final de
     * predição da rede.
     */
    public Double[] predict(Double[] input) {
        this.inputXVector = input;
        feedForward();
        return this.obtainedYVector;
    }

    /**
     * Nesse método o treinamento é efetivamente realizado, enquanto o código
     * não está terminado (número de épocas atual < número máximo de épocas).
     * - A primeira parte do código (feedforward) refere-se ao movimento dos dados para frente.
     * Nela, geramos os valores da camada escondida através da multiplacação dos inputs pela
     * primeira matriz de pesos, aplicando a função de ativação (Sigmoid) na soma desses valores.
     * Em seguida, geramos os valores resultantes da camada de saída pela
     * multiplicação da camada escondida pela segunda matriz de pesos aplicando a função de ativação (sigmoid)
     * na soma desses valores.
     *
     * - A segunda parte do código (backpropagation) refere-se à computaçõa dos erros gerados
     *   e conta com 4 etapas:
     *
     *  (1) Calcula a informação de erro de saída para cada neuronio de saida:
     *   A diferença do valor esperado pelo obtido é multiplicado pela derivada da função de
     *  Ativação (Sigmoid) que recebe como parâmetro a camada escondida multiplicada pela matriz
     *  da segunda matriz de pesos (matriz de pesos de saída) (valores somados).
     *
     *  (2) Calcula os termos de correção da camada dos pesos da camada de saída:
     *   Cada termo de correção dos pesos aqui é o resultado da multiplicação do
     *   neuronio da camada escondida pelo pela informação de erro à ele associada e
     *   multiplicado à taxa de aprendizagem
     *
     *  (3) Calcula a informação de erro na camada escondida:
     *   Cada informação de erro da camada escondida é calculada pela derivada da
     *   função de ativação da soma dos neuronios de entrada multiplicados
     *   pelos seus pesos, multiplicada pelos pesos da camada de saída multiplicados
     *   pelas informações de erro na camada de saída
     *
     *  (4) Calcula os termos de correção da camada escondida:
     *    Aqui, cada termo de correção dos pesos é calculado pela multiplicação de
     *    cada neuronio de entrada pela informação de erro da camada escondida associada a ele
     *    e pela taxa de aprendizagem.
     *    Agora temos os valores que seram usados para gerar os novos pesos.
     *
     *  - A terceira parte do código (updateWeightMatrices) refere-se aos ajustes dos
     *  pesos.
     *   Cada peso da camada de saída se soma aos termos de correção da camada de saída.
     *   Cada peso da camada escondida se soma aos termos de correção da camada escondida.
     *
     *  - A quarta parte incrementa o contador de épocas (udpateCurrentEpoch) e troca as instancias de treino (changeTrainingData).
     *  Pegamos aleatóriamente um novo dado de entrada e sua respectiva saída esperada,
     *  para tentarmos promover um pouco mais de indeterminismo e aleatoriedade no
     *  aprendizado da rede.
     *  Em seguida, logamos os resultados através do método logIteration e continuamos o loop do
     *  processo de aprendizagem enquanto o código não identifica o fim da execução.
     */

    private void startTraining() {
        while (!isTerminated()) {
            feedForward();
            backPropagation();
            updateWeightMatrices();
            updateCurrentEpoch();
            changeTrainingData();
            logIteration();
        }
    }

    /**
     *  Troca as instancias de treino.
     *  Pegamos aleatóriamente um novo dado de entrada e sua respectiva saída esperada,
     *  para tentarmos promover um pouco mais de indeterminismo e aleatoriedade no
     *  aprendizado da rede
     */
    private void changeTrainingData() {
        int dataSetInstancesNumber = inputDataSet.length;
        int randomIndex = new IntGenerator().generate(dataSetInstancesNumber);
        setNewTrainingInstance(Math.abs(randomIndex));
    }

    private void setNewTrainingInstance(int index) {
        this.inputXVector = inputDataSet[index];
        this.expectedYvector = outputDataSet[index % outputDataSet.length];
    }

    /**
     * Atualiza as matrizes de pesos da camada de saída e da escondida, utilizando
     * os termos de correção de cada camada.
     */
    private void updateWeightMatrices() {
        for (int i = 0; i < outputWeightMatrix.length; i++) {
            for (int j = 0; j < outputWeightMatrix[i].length; j++) {
                outputWeightMatrix[i][j] += outputCorrectionTerm[i][j];
            }
        }

        for (int i = 0; i < hiddenWeightMatrix.length; i++) {
            for (int j = 0; j < hiddenWeightMatrix[i].length; j++) {
                hiddenWeightMatrix[i][j] += hiddenCorrectionTerm[i][j];
            }
        }
    }

    /**
     * A segunda parte do código (backpropagation) refere-se à computaçõa dos erros gerados
     *   e conta com 4 etapas:
     *
     *  (1) Calcula a informação de erro de saída para cada neuronio de saida:
     *   A diferença do valor esperado pelo obtido é multiplicado pela derivada da função de
     *  Ativação (Sigmoid) que recebe como parâmetro a camada escondida multiplicada pela matriz
     *  da segunda matriz de pesos (matriz de pesos de saída) (valores somados).
     *
     *  (2) Calcula os termos de correção da camada dos pesos da camada de saída:
     *   Cada termo de correção dos pesos aqui é o resultado da multiplicação do
     *   neuronio da camada escondida pelo pela informação de erro à ele associada e
     *   multiplicado à taxa de aprendizagem
     *
     *  (3) Calcula a informação de erro na camada escondida:
     *   Cada informação de erro da camada escondida é calculada pela derivada da
     *   função de ativação da soma dos neuronios de entrada multiplicados
     *   pelos seus pesos, multiplicada pelos pesos da camada de saída multiplicados
     *   pelas informações de erro na camada de saída
     *
     *  (4) Calcula os termos de correção da camada escondida:
     *    Aqui, cada termo de correção dos pesos é calculado pela multiplicação de
     *    cada neuronio de entrada pela informação de erro da camada escondida associada a ele
     *    e pela taxa de aprendizagem.
     *    Agora temos os valores que seram usados para gerar os novos pesos.
     */
    private void backPropagation() {
        calculateOutputErrorInformation();
        calculateOutputCorrectionTerms();
        calculateHiddenErrorInformation();
        calculateHiddenCorrectionTerms();
    }

    /**
     *  Calcula os termos de correção da camada escondida:
     *    Aqui, cada termo de correção dos pesos é calculado pela multiplicação de
     *    cada neuronio de entrada pela informação de erro da camada escondida associada a ele
     *    e pela taxa de aprendizagem.
     *    Agora temos os valores que seram usados para gerar os novos pesos.
     */
    private void calculateHiddenCorrectionTerms() {
        Double[] reducedAdjustRate = DoubleConverter.toDouble(new double[hiddenErrorInformation.length]);
        for (int i = 0; i < reducedAdjustRate.length; i++) {
            reducedAdjustRate[i] = learningRate * hiddenErrorInformation[i];
        }

        for (int i = 0; i < hiddenCorrectionTerm.length; i++) {
            for (int j = 1; j < hiddenCorrectionTerm[i].length; j++) {
                hiddenCorrectionTerm[i][j] = inputXVector[i] * reducedAdjustRate[j - 1];
            }
        }
    }

    /**
     *  Calcula a informação de erro na camada escondida:
     *   Cada informação de erro da camada escondida é calculada pela derivada da
     *   função de ativação da soma dos neuronios de entrada multiplicados
     *   pelos seus pesos, multiplicada pelos pesos da camada de saída multiplicados
     *   pelas informações de erro na camada de saída
     */
    //Here we mustn't  consider bias, we use this offset to perform this step without consider error in bias
    private void calculateHiddenErrorInformation() {
        for (int i = 0; i < hiddenLayerNeuronNumber - 1; i++) {
            hiddenErrorInformation[i] = (getReformattedWeights(i + 1)) * activationFunction.derivative(sumInputLayer(i + 1));
        }
    }

    /**
     * Obtem o resultado da multiplicação da matriz de pesos da camada de saida
     * pela informação de erro de saida
     */
    private Double getReformattedWeights(int index) {
        Double result = 0.0;
        for (int i = 0; i < outputLayerNeuronNumber; i++) {
            result += outputWeightMatrix[index][i] * outputErrorInformation[i];
        }
        return result;
    }

    /**
     * Calcula os termos de correção da camada dos pesos da camada de saída:
     *   Cada termo de correção dos pesos aqui é o resultado da multiplicação do
     *   neuronio da camada escondida pelo pela informação de erro à ele associada e
     *   multiplicado à taxa de aprendizagem
     */
    private void calculateOutputCorrectionTerms() {
        Double[] reducedAdjustRate = DoubleConverter.toDouble(new double[outputErrorInformation.length]);
        for (int i = 0; i < reducedAdjustRate.length; i++) {
            reducedAdjustRate[i] = learningRate * outputErrorInformation[i];
        }

        for (int i = 0; i < outputCorrectionTerm.length; i++) {
            for (int j = 0; j < outputCorrectionTerm[i].length; j++) {
                outputCorrectionTerm[i][j] = reducedAdjustRate[j] * hiddenZVector[i];
            }
        }
    }

    /**
     *  Calcula a informação de erro de saída para cada neuronio de saida:
     *   A diferença do valor esperado pelo obtido é multiplicado pela derivada da função de
     *  Ativação (Sigmoid) que recebe como parâmetro a camada escondida multiplicada pela matriz
     *  da segunda matriz de pesos (matriz de pesos de saída) (valores somados).
     */
    private void calculateOutputErrorInformation() {
        for (int i = 0; i < outputLayerNeuronNumber; i++) {
            outputErrorInformation[i] = (expectedYvector[i] - obtainedYVector[i]) * activationFunction.derivative(sumHiddenLayer(i));
        }
    }

    /**
     * Feedforward refere-se ao movimento dos dados para frente.
     * Nela, geramos os valores da camada escondida através da multiplacação dos inputs pela
     * primeira matriz de pesos, aplicando a função de ativação (Sigmoid) na soma desses valores.
     * Em seguida, geramos os valores resultantes da camada de saída pela
     * multiplicação da camada escondida pela segunda matriz de pesos aplicando a função de ativação (sigmoid)
     * na soma desses valores.
     */
    //We use this offset, starting with i = 1, not considering hidden layer bias. It do not must to be updated.
    private void feedForward() {
        for (int i = 1; i < hiddenLayerNeuronNumber; i++) {
            hiddenZVector[i] = activationFunction.execute(sumInputLayer(i));
        }

        for (int i = 0; i < outputLayerNeuronNumber; i++) {
            obtainedYVector[i] = activationFunction.execute(sumHiddenLayer(i));
        }
    }

    /**
     * Soma os neuronios da camada escondida multiplicados pelos
     * pesos da camada de saida.
     */
    private Double sumHiddenLayer(int index) {
        double result = 0;
        for (int i = 0; i < hiddenLayerNeuronNumber; i++) {
            result += (hiddenZVector[i] * outputWeightMatrix[i][index]);
        }
        return result;
    }

    /**
     *Soma os neuronios da camada de saida multiplicados pelos
     * pesos da camada de saída.
     */
    private double sumInputLayer(int index) {
        double result = 0;
        for (int i = 0; i < inputLayerNeuronNumber; i++) {
            result += (inputXVector[i] * hiddenWeightMatrix[i][index]);
        }
        return result;
    }

    /**
     * Verifica se o número de interações já superou o limite.
     */
    private boolean isTerminated() {
        return (currentEpoch >= epochMaxNumber);
    }

    /**
     * Loga cada iteração da execução
     */
    private void logIteration() {
        Logger.getInstance().logIteration(this);
        //TODO: Esse plotter serviria pra plotar o gráfico em tempo real,
        // chegamos perto de terminar mas ainda não terminamos. Mas está proximo,
        // a ideia era plotar as retas cortando o plano contendo os pontos do
        // XOR para acompanharmos o processo de aprendizagem da rede de forma
        // mais visual.
        //Plotter.getInstance().logIteration(this);
    }

    /**
     * Getters da rede abaixo, usados pelo Logger e pelo Plotter.
     */
    public int getInputLayerNeuronNumber() {
        return inputLayerNeuronNumber;
    }

    public int getOutputLayerNeuronNumber() {
        return outputLayerNeuronNumber;
    }

    public int getHiddenLayerNeuronNumber() {
        return hiddenLayerNeuronNumber;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public int getEpochMaxNumber() {
        return epochMaxNumber;
    }

    public int getCurrentEpoch() {
        return currentEpoch;
    }

    public Double[][] getHiddenWeightMatrix() {
        return hiddenWeightMatrix;
    }

    public Double[][] getOutputWeightMatrix() {
        return outputWeightMatrix;
    }

    public Double[][] getOutputCorrectionTerm() {
        return outputCorrectionTerm;
    }

    public Double[][] getHiddenCorrectionTerm() {
        return hiddenCorrectionTerm;
    }

    public Double[] getInputXVector() {
        return inputXVector;
    }

    public Double[] getExpectedYvector() {
        return expectedYvector;
    }

    public Double[] getObtainedYVector() {
        return obtainedYVector;
    }

    public Double[] getHiddenZVector() {
        return hiddenZVector;
    }

    public Double[] getHiddenErrorInformation() {
        return hiddenErrorInformation;
    }

    public Double[] getOutputErrorInformation() {
        return outputErrorInformation;
    }

    public void setFunctionTag(String function) {
        this.functionTag = function;
    }

    public void setFileNameReference(String fileName) {
        this.fileReference = fileName;
    }

    public String getFunctionTag() {
        return functionTag;
    }

    public String getFileReference() {
        return fileReference;
    }
}
