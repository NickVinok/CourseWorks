package Mathematics.RiskCalculationModule;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class ProbitFunctionsToExposureProbability {
    private static List<Double> limitValues = Stream.of(2.67, 2.95, 3.12, 3.25, 3.36, 3.45, 3.52, 3.59, 3.66,
            3.72, 3.77, 3.82, 3.90, 3.92, 3.96, 4.01, 4.05,  4.08, 4.12, 4.16, 4.19, 4.23, 4.26, 4.29,
            4.33, 4.36, 4.39, 4.42, 4.45, 4.48, 4.50, 4.53, 4.56, 4.59, 4.61, 4.64, 4.67, 4.69, 4.72,
            4.75, 4.77, 4.80, 4.82, 4.85, 4.87, 4.90, 4.92, 4.95, 4.97, 5.00, 5.03, 5.05, 5.08, 5.10, 5.13,
            5.15, 5.18, 5.20, 5.23, 5.25, 5.28, 5.31, 5.33, 5.36, 5.39, 5.41, 5.44, 5.47, 5.5, 5.52, 5.55,
            5.58, 5.61, 5.64, 5.67, 5.71, 5.74, 5.77, 5.81, 5.84, 5.88, 5.92, 5.95, 5.99, 6.04, 6.08, 6.13,
            6.18, 6.23, 6.28, 6.34, 6.41, 6.48, 6.55, 6.64, 6.75, 6.88, 7.05, 7.33, 7.37, 7.41, 7.46, 7.51,
            7.58, 7.65, 7.75, 7.88, 8.09
            )
            .collect(Collectors.toList());

    public List<Double> convert(List<Double> probitValues){
        List<Double> probabilities = new ArrayList<>();
        for(Double probit: probitValues){
            int index = find(probit);
            if(index == -1){
                probabilities.add(1d);
            }
            else if(index == 0){
                probabilities.add(0d);
            }
            else {
                double upperLimit = limitValues.get(index);
                double lowerLimit = limitValues.get(index-1);
                double exactProbabilityValue = index+(probit-lowerLimit)/(upperLimit-lowerLimit);
                probabilities.add(exactProbabilityValue);
            }
        }
        return probabilities;
    }

    private int find(Double probit){
        int index =  -1;
        for(int i = 0; i<limitValues.size();i++){
            if(probit<limitValues.get(i)){
                index=i;
                break;
            }
        }
        return index;
    }
}
