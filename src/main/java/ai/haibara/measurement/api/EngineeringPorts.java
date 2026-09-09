package ai.haibara.measurement.api;
import ai.haibara.measurement.core.SurveyData.*;
import java.util.*;
import java.util.concurrent.CompletionStage;
/** Extension boundaries, not working AI/route/schematic implementations. */
public final class EngineeringPorts {
    private EngineeringPorts() {}
    public record Position(int x, int y, int z) {}
    public record Constraints(double minRadiusBlocks, double maxGradeRatio, Set<UUID> avoidAreas) {
        public Constraints {
            if(!Double.isFinite(minRadiusBlocks)||minRadiusBlocks<=0 || !Double.isFinite(maxGradeRatio)||maxGradeRatio<0)
                throw new IllegalArgumentException("invalid route constraints");
            avoidAreas=Set.copyOf(avoidAreas);
        }
    }
    public record Request(UUID archiveId, String dimension, Position start, Position end, Constraints constraints) {}
    public record Route(UUID id, List<Position> nodes, String explanation) { public Route { nodes=List.copyOf(nodes); } }
    public record PlanResult(List<Route> routes, List<String> diagnostics) { public PlanResult { routes=List.copyOf(routes); diagnostics=List.copyOf(diagnostics); } }
    public record Validation(boolean valid, List<String> problems) { public Validation { problems=List.copyOf(problems); } }
    public record Segment(UUID id, Position origin, String contentHash) {}
    public record BlueprintQuote(UUID jobId, UUID routeId, long routeRevision, List<Segment> segments, Map<String,Long> materials) {
        public BlueprintQuote { segments=List.copyOf(segments); materials=Map.copyOf(materials); }
        public int requiredSheets() { return segments.size(); }
    }
    public record BlueprintArtifact(UUID segmentId, String format, String storageKey) {}
    // Implementations need timeouts/cancellation and must never access live world off the server thread.
    public interface IntentInterpreter { CompletionStage<Request> interpret(String text, Upload context); }
    public interface RoutePlanner { CompletionStage<PlanResult> plan(Request request, Upload terrainSnapshot); }
    public interface PlanValidator { Validation validate(Request request, Route route, List<Area> currentPublicAreas); }
    public interface BlueprintCompiler { BlueprintQuote quote(Route route, long revision); }
    public interface BlueprintExporter { String format(); CompletionStage<List<BlueprintArtifact>> export(BlueprintQuote quote); }
    public interface SurveyRepository {
        Optional<Area> findArea(UUID id);
        // Server must atomically check owner, revision, overlap and then persist; failed save must not consume stakes.
        boolean updateArea(UUID actor, long expectedRevision, Area replacement);
        void appendArchive(Upload immutableSnapshot);
    }
}
