package boot;

import com.google.inject.AbstractModule;
import commandline.AlgorithmSelector;
import domain.algorithm.Algorithm;

/**
 * Created by mzeus on 28/07/16.
 */
public class AlgorithmModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Algorithm.class).toProvider(AlgorithmSelector.class);
    }
}
