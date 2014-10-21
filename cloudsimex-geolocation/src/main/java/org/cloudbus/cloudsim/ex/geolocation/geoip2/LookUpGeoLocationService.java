package org.cloudbus.cloudsim.ex.geolocation.geoip2;

import java.util.Map;

import org.cloudbus.cloudsim.ex.geolocation.BaseGeolocationService;
import org.cloudbus.cloudsim.ex.geolocation.IPMetadata;

/**
 * A geo-location service that just looks-up the values in Maps, specified in
 * the constructor. Used mostly for <strong>test</strong> purposes.
 * 
 * @author nikolay.grozev
 *
 */
public class LookUpGeoLocationService extends BaseGeolocationService {

    final private Map<String, IPMetadata> metadataCache;
    final private Map<String, Double> latencyCache;
    final private Map<String, double[]> coordinatesCache;

    /**
     * Constr.
     * 
     * @param metadataCache
     *            - a map of IP addresses to metadata. If null, the respective
     *            methods will not work.
     * @param latencyCache
     *            - a map of concatenated pairs of IP addresses to latency. If
     *            null, the respective methods will not work.
     * @param coordinatesCache
     *            - a map of IP addresses and coordinates. If null, the
     *            respective methods will not work.
     */
    public LookUpGeoLocationService(final Map<String, IPMetadata> metadataCache,
            final Map<String, Double> latencyCache,
            final Map<String, double[]> coordinatesCache) {
        this.metadataCache = metadataCache;
        this.latencyCache = latencyCache;
        this.coordinatesCache = coordinatesCache;
    }

    @Override
    public double[] getCoordinates(final String ip) {
        return coordinatesCache.get(ip);
    }

    @Override
    public IPMetadata getMetaData(final String ip) {
        return metadataCache.get(ip);
    }

    @Override
    public double latency(final String ip1, final String ip2) {
        if (latencyCache.containsKey(ip1 + ip2)) {
            return latencyCache.get(ip1 + ip2);
        } else {
            return latencyCache.get(ip2 + ip1);
        }
    }
}