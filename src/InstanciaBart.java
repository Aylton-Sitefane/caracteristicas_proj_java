import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class InstanciaBart {
    public static void main(String[] args) {
        try {
            DataSource ds = new DataSource("./caracteristicas.arff");
            Instances instances = ds.getDataSet();
            instances.setClassIndex(instances.numAttributes() -1 );

            //Carregar uma instancia especifica
            int instanciaEspecifica = 0;
            if (instanciaEspecifica >=0 && instanciaEspecifica < instances.numInstances()) {
                Instance barInstances = instances.instance(instanciaEspecifica);

                System.out.println("Instancia carregada > "+ barInstances);
                System.out.println("Classe da instancia carregada > "+ barInstances.classAttribute().value((int)barInstances.classValue()));
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
