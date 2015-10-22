// Copyright © 2015 HSL <https://www.hsl.fi>
// This program is dual-licensed under the EUPL v1.2 and AGPLv3 licenses.

package fi.hsl.parkandride.util;

import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public final class MapUtils {
    private MapUtils() { /** prevent instantiation */}


    public static <F, T> Set<T> extractFromKeys(Map<F, ?> map, Function<F, T> fn) {
        return map.keySet().stream().map(fn).collect(toSet());
    }

    public static <T> Collector<T, ?, Map<T, Long>> countingOccurrences() {
        return groupingBy(identity(), counting());
    }

    /**
     * NOTE: will throw exception on duplicate keys. See {@link #entriesToMap(BinaryOperator)} to
     * cope with this.
     */
    public static <K,V> Collector<Map.Entry<K,V>, ?, Map<K, V>> entriesToMap() {
        return toMap(
                entry -> entry.getKey(),
                entry -> entry.getValue()
        );
    }

    public static <K,V> Collector<Map.Entry<K,V>, ?, Map<K, V>> entriesToMap(BinaryOperator<V> mergeFn) {
        return toMap(
                entry -> entry.getKey(),
                entry -> entry.getValue(),
                mergeFn
        );
    }
}