package ai.haibara.measurement.core;
import java.util.*;
import ai.haibara.measurement.core.SurveyData.Point;
public final class Geometry {
    private Geometry() {}
    private static double cross(Point a, Point b, Point c) {
        return ((double)b.x()-a.x())*((double)c.z()-a.z())-((double)b.z()-a.z())*((double)c.x()-a.x());
    }
    private static boolean on(Point a,Point b,Point p) {
        return cross(a,b,p)==0 && p.x()>=Math.min(a.x(),b.x()) && p.x()<=Math.max(a.x(),b.x())
            && p.z()>=Math.min(a.z(),b.z()) && p.z()<=Math.max(a.z(),b.z());
    }
    private static boolean intersects(Point a,Point b,Point c,Point d) {
        double u=cross(a,b,c),v=cross(a,b,d),w=cross(c,d,a),t=cross(c,d,b);
        return (Math.signum(u)*Math.signum(v)<0 && Math.signum(w)*Math.signum(t)<0)
            || on(a,b,c)||on(a,b,d)||on(c,d,a)||on(c,d,b);
    }
    public static void validate(List<Point> points) {
        int n=points.size();
        if(n<3 || n>4096 || new HashSet<>(points).size()!=n) throw new IllegalArgumentException("invalid vertex count or duplicate");
        double area=0;
        for(int i=0;i<n;i++) {
            Point a=points.get(i),b=points.get((i+1)%n),prev=points.get((i+n-1)%n);
            if(cross(prev,a,b)==0 && (on(prev,a,b)||on(a,b,prev))) throw new IllegalArgumentException("backtracking edge");
            area+=(double)a.x()*b.z()-(double)b.x()*a.z();
            for(int j=i+1;j<n;j++) {
                if(j==i+1 || (i==0 && j==n-1)) continue;
                if(intersects(a,b,points.get(j),points.get((j+1)%n))) throw new IllegalArgumentException("self-intersection");
            }
        }
        if(area==0) throw new IllegalArgumentException("zero area");
    }
}
