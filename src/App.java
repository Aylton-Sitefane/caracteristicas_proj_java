import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class App {
    public static void main(String[] args) throws Exception {

        try {
            // Carregar os dados
            DataSource ds = new DataSource("./caracteristicas.arff");
            Instances instacia = ds.getDataSet();
            // Definicao do indice do atributo classe
            instacia.setClassIndex(instacia.numAttributes() - 1);

            // Exibicao dos dados carregados
            System.out.println(instacia.toSummaryString());

            //Parametros para o J48
            String[] options = {"-C", "0.25", "-M", "30"};

            // Treinar um modelo bart usando o clasificador j48
            J48 classificador = new J48();
            classificador.setOptions(options);
            classificador.buildClassifier(instacia);

            Evaluation eval = new Evaluation(instacia);

            eval.crossValidateModel(classificador, instacia, 10, new Random(1));
            System.out.println(eval.toSummaryString("\nJ48 Algoritm\n", true));
            System.out.println("Precisao: "+ eval.precision(1));
            System.out.println("Recal: "+ eval.recall(1));
            System.out.println("F1 Measure: "+ eval.fMeasure(1));
            System.out.println("TPR: "+ eval.truePositiveRate(1));
            System.out.println("TNR: "+ eval.trueNegativeRate(1));
            System.out.println("FPR: "+ eval.falsePositiveRate(1));
            System.out.println("FNR: "+ eval.falseNegativeRate(1));

            //Decisao do algoritmo
            System.out.println("\n\n"+classificador.graph());

        } catch (Exception e) {
            System.out.println("Ocorreu uma excecao > "+ e);
        }
    }
}
