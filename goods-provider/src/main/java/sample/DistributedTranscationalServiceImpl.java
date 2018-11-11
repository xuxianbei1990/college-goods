package sample;

import domain.Sample;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-11-11
 * VersionV1.0
 * @description
 */
public class DistributedTranscationalServiceImpl implements DistributedTranscationalService {
    @Override
    public Sample testSample() {
        Sample result = new Sample();
        result.setId("123");
        return result;
    }
}
