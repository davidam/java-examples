import java.awt.Point;
import java.awt.Polygon;

public class BasicSprite
{

    public BasicSprite()
    {
    }

    public static Polygon giRomSkip(Point pkt)
    {
        Polygon midl = new Polygon();
        midl.addPoint(pkt.x - 8, pkt.y);
        midl.addPoint(pkt.x, pkt.y - 25);
        midl.addPoint(pkt.x + 8, pkt.y);
        return midl;
    }

    public static Polygon giAngripKule(Point pkt)
    {
        Polygon midl = new Polygon();
        midl.addPoint(pkt.x - 3, pkt.y - 3);
        midl.addPoint(pkt.x, pkt.y + 3);
        midl.addPoint(pkt.x + 3, pkt.y - 3);
        return midl;
    }

    public static Polygon giKule(Point pkt)
    {
        Polygon midl = new Polygon();
        midl.addPoint(pkt.x, pkt.y - 2);
        midl.addPoint(pkt.x - 2, pkt.y + 2);
        midl.addPoint(pkt.x + 2, pkt.y + 2);
        return midl;
    }

    public static Polygon giAngriper(Point pkt)
    {
        Polygon midl = new Polygon();
        midl.addPoint(pkt.x - 6, pkt.y - 6);
        midl.addPoint(pkt.x - 6, pkt.y + 6);
        midl.addPoint(pkt.x, pkt.y + 9);
        midl.addPoint(pkt.x + 6, pkt.y + 6);
        midl.addPoint(pkt.x + 6, pkt.y - 6);
        return midl;
    }

    public static Polygon giExplo(Point pkt)
    {
        Polygon midl = new Polygon();
        midl.addPoint(pkt.x - 6, pkt.y - 6);
        midl.addPoint(pkt.x - 3, pkt.y);
        midl.addPoint(pkt.x - 6, pkt.y + 6);
        midl.addPoint(pkt.x - 3, pkt.y + 3);
        midl.addPoint(pkt.x, pkt.y + 5);
        midl.addPoint(pkt.x + 3, pkt.y + 3);
        midl.addPoint(pkt.x + 6, pkt.y + 6);
        midl.addPoint(pkt.x + 3, pkt.y);
        midl.addPoint(pkt.x + 6, pkt.y - 6);
        midl.addPoint(pkt.x + 3, pkt.y - 3);
        midl.addPoint(pkt.x, pkt.y - 5);
        midl.addPoint(pkt.x - 3, pkt.y - 3);
        return midl;
    }

    public static Polygon giUfo(Point pkt)
    {
        Polygon midl = new Polygon();
        midl.addPoint(pkt.x - 15, pkt.y);
        midl.addPoint(pkt.x - 10, pkt.y - 5);
        midl.addPoint(pkt.x - 5, pkt.y - 5);
        midl.addPoint(pkt.x + 5, pkt.y - 5);
        midl.addPoint(pkt.x + 10, pkt.y - 5);
        midl.addPoint(pkt.x + 15, pkt.y);
        midl.addPoint(pkt.x + 10, pkt.y + 5);
        midl.addPoint(pkt.x - 10, pkt.y + 5);
        return midl;
    }

    public static Polygon giMissil(Point pkt)
    {
        Polygon midl = new Polygon();
        midl.addPoint(pkt.x, pkt.y - 12);
        midl.addPoint(pkt.x - 3, pkt.y + 3);
        midl.addPoint(pkt.x + 3, pkt.y + 3);
        return midl;
    }
}