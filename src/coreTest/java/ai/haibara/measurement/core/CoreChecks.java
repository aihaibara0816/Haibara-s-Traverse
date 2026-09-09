package ai.haibara.measurement.core;
import java.util.*;
import ai.haibara.measurement.core.SurveyData.*;
public final class CoreChecks {
    private static void reject(List<Point> p) { try { Geometry.validate(p); } catch(IllegalArgumentException e) { return; } throw new AssertionError("accepted invalid polygon"); }
    public static void main(String[] args) {
        var points=new ArrayList<>(List.of(new Point(0,0),new Point(8,0),new Point(8,8),new Point(0,8)));
        UUID owner=UUID.randomUUID();
        var a=new Area(UUID.randomUUID(),owner,"minecraft:overworld",1,"Village","settlement",Policy.PROTECTED,false,false,points);
        points.clear();
        if(a.boundary().size()!=4 || !SurveyData.canEdit(a,owner) || SurveyData.canEdit(a,UUID.randomUUID())) throw new AssertionError("ownership/snapshot failure");
        reject(List.of(new Point(0,0),new Point(8,8),new Point(0,8),new Point(8,0)));
        reject(List.of(new Point(0,0),new Point(1,1),new Point(2,2)));
        reject(List.of(new Point(0,0),new Point(4,0),new Point(2,0),new Point(2,4)));
        var selection=new SurveySelection();
        var s0=new SurveySelection.Stake("minecraft:overworld",0,64,0);
        if(selection.add(s0)!=SurveySelection.AddResult.ADDED) throw new AssertionError("first point");
        if(selection.add(new SurveySelection.Stake("minecraft:the_nether",4,64,0))!=SurveySelection.AddResult.WRONG_DIMENSION) throw new AssertionError("dimension isolation");
        if(selection.add(new SurveySelection.Stake("minecraft:overworld",0,80,0))!=SurveySelection.AddResult.DUPLICATE) throw new AssertionError("duplicate XZ");
        selection.add(new SurveySelection.Stake("minecraft:overworld",8,64,0));
        selection.add(new SurveySelection.Stake("minecraft:overworld",8,64,8));
        if(selection.previewBoundary().size()!=3 || selection.selectedStakes().size()!=3) throw new AssertionError("destructive preview");
        selection.undo();
        boolean invalid=false;
        try { selection.previewBoundary(); } catch(IllegalArgumentException expected) { invalid=true; }
        if(!invalid || selection.selectedStakes().size()!=2) throw new AssertionError("invalid preview lost selection");
        selection.cancel();
        if(!selection.selectedStakes().isEmpty() || selection.undo().isPresent()) throw new AssertionError("cancel/undo");
        System.out.println("PASS: polygon validation, immutable snapshots, ownership rules, ordered pen selection");
    }
}
