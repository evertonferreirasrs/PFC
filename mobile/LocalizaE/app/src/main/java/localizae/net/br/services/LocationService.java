package localizae.net.br.services;

import android.util.Log;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class LocationService {

    private static String TAG = "LOCATION_SERVICE";

    public static double[] detectUserPosition(double[][] positions, double[] distances) {
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances),
                new LevenbergMarquardtOptimizer());
        Optimum optimum = solver.solve();

        // TODO: Print both values below and look their meaning
        // NOT NEEDED
        //RealVector standardDeviation = optimum.getSigma(0);
        //RealMatrix covarianceMatrix = optimum.getCovariances(0);

        return optimum.getPoint().toArray();
    }

    public static double getDistance(int rssi, int txPower) {
        /*
         * RSSI = TxPower - 10 * n * lg(d)
         * n = 2 (in free space)
         *
         * d = 10 ^ ((TxPower - RSSI) / (10 * n))
         */

        return Math.pow(10d, ((double) txPower - rssi) / (10 * 2));
    }

}
