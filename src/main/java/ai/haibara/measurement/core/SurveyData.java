package ai.haibara.measurement.core;
import java.time.Instant;
import java.util.*;
/** Immutable, Minecraft/AI/Create-independent contracts. All coordinates are world blocks. */
public final class SurveyData {
    private SurveyData() {}
    public static final int SCHEMA_VERSION = 1;
    public record Point(int x, int z) {}
    public enum Policy { NORMAL, DEVELOPABLE, CAUTIOUS, PROTECTED, NO_BUILD }
    public record Area(UUID id, UUID owner, String dimension, long revision, String name,
                       String type, Policy policy, boolean allowTunnel, boolean allowViaduct,
                       List<Point> boundary) {
        public Area {
            Objects.requireNonNull(id); Objects.requireNonNull(owner); Objects.requireNonNull(dimension);
            Objects.requireNonNull(name); Objects.requireNonNull(type); Objects.requireNonNull(policy);
            boundary = List.copyOf(boundary);
            if (revision < 1) throw new IllegalArgumentException("revision must be positive");
            Geometry.validate(boundary);
        }
    }
    public record TerrainTile(String dimension, int chunkX, int chunkZ, Instant surveyedAt,
                              List<Integer> heights, BitSet water) {
        public TerrainTile {
            Objects.requireNonNull(dimension); Objects.requireNonNull(surveyedAt);
            heights = List.copyOf(heights); water = (BitSet)water.clone();
            if (heights.size()!=256 || water.length()>256) throw new IllegalArgumentException("16x16 surface tile required");
        }
        @Override public BitSet water() { return (BitSet)water.clone(); }
    }
    public record Note(UUID id, UUID author, String dimension, int x, int y, int z,
                       Instant createdAt, String text, Optional<UUID> areaId) {
        public Note { Objects.requireNonNull(id); Objects.requireNonNull(author); Objects.requireNonNull(dimension);
            Objects.requireNonNull(createdAt); Objects.requireNonNull(text); Objects.requireNonNull(areaId); }
    }
    public record Upload(UUID id, Instant createdAt, List<TerrainTile> terrain, List<Area> areas, List<Note> notes) {
        public Upload { Objects.requireNonNull(id); Objects.requireNonNull(createdAt);
            terrain=List.copyOf(terrain); areas=List.copyOf(areas); notes=List.copyOf(notes); }
    }
    public static boolean canEdit(Area area, UUID actor) { return area.owner().equals(actor); }
}
