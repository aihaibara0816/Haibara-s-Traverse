package ai.haibara.measurement.core;

import java.util.*;
import ai.haibara.measurement.core.SurveyData.Point;

/** One server-side pen selection session. Does not mutate the world or consume stakes. */
public final class SurveySelection {
    public static final int MAX_POINTS = 256;
    public record Stake(String dimension, int x, int y, int z) {
        public Stake { Objects.requireNonNull(dimension); }
        public Point point() { return new Point(x, z); }
    }
    public enum AddResult { ADDED, DUPLICATE, WRONG_DIMENSION, LIMIT_REACHED }
    private final List<Stake> stakes = new ArrayList<>();

    public AddResult add(Stake stake) {
        Objects.requireNonNull(stake);
        if (!stakes.isEmpty() && !stakes.get(0).dimension().equals(stake.dimension()))
            return AddResult.WRONG_DIMENSION;
        if (stakes.stream().anyMatch(s -> s.point().equals(stake.point()))) return AddResult.DUPLICATE;
        if (stakes.size() >= MAX_POINTS) return AddResult.LIMIT_REACHED;
        stakes.add(stake);
        return AddResult.ADDED;
    }
    public Optional<Stake> undo() {
        return stakes.isEmpty() ? Optional.empty() : Optional.of(stakes.remove(stakes.size() - 1));
    }
    public void cancel() { stakes.clear(); }
    public List<Stake> selectedStakes() { return List.copyOf(stakes); }
    /** Non-destructive preview; caller persists before consuming stakes and clearing the session. */
    public List<Point> previewBoundary() {
        var points = stakes.stream().map(Stake::point).toList();
        Geometry.validate(points);
        return points;
    }
}
