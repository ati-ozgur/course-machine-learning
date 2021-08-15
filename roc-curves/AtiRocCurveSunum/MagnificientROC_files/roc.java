// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 26.03.2006 20:30:35
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   roc.java

import java.applet.Applet;
import java.awt.*;

public class roc extends Applet
{

    public roc()
    {
        myBackground = new Color(204, 204, 255);
    }

    int Hex1(int i)
    {
        if(i > 57)
            i -= 7;
        return i -= 48;
    }

    Color ReadColour(String str)
    {
        if(str.charAt(0) != '#')
            return Color.white;
        if(str.length() < 7)
        {
            return Color.gray;
        } else
        {
            int r = ReadHex(str.substring(1, 3));
            int g = 0;
            int b = 0;
            g = ReadHex(str.substring(3, 5));
            b = ReadHex(str.substring(5, 7));
            return new Color(r, g, b);
        }
    }

    int ReadHex(String str)
    {
        int i = Hex1(str.charAt(0));
        int j = Hex1(str.charAt(1));
        return 16 * i + j;
    }

    public int atStartup(String mode, String separation, String demarcation, String titl, String background)
    {
        int rval = 0;
        String parmVal = getParameter(separation);
        if(parmVal != null)
        {
            int pv = Integer.valueOf(parmVal).intValue();
            Qseparation = pv;
        }
        parmVal = getParameter(demarcation);
        if(parmVal != null)
        {
            int pv = Integer.valueOf(parmVal).intValue();
            Qdemarcation = pv;
        }
        parmVal = getParameter(mode);
        if(parmVal != null)
        {
            if(parmVal.indexOf("noSeparation") > -1)
                rval |= 1;
            if(parmVal.indexOf("shading") > -1)
                rval |= 2;
            if(parmVal.indexOf("noROC") > -1)
                rval |= 4;
        }
        parmVal = getParameter(titl);
        if(parmVal != null)
            MainTitle = parmVal;
        parmVal = getParameter(background);
        if(parmVal != null)
            myBackground = ReadColour(parmVal);
        return rval;
    }

    public void init()
    {
        isseparation = true;
        canshade = false;
        noroc = false;
        MainTitle = "                                   ROC CURVE DEMONSTRATION";
        int ok = atStartup("mode", "separation", "demarcation", "title", "background");
        if((ok & 1) != 0)
            isseparation = false;
        setBackground(myBackground);
        theGraph = new ROCGRAPH();
        CurveSeparation = new SIDEPANEL(0, theGraph, MainTitle, isseparation);
        DemarcationControl = new SIDEPANEL(2, theGraph, "", true);
        LskewKurtosis = new SIDEPANEL(3, theGraph, "", true);
        RskewKurtosis = new SIDEPANEL(1, theGraph, "", true);
        CurveSeparation.SetMe(Qseparation);
        DemarcationControl.SetMe(Qdemarcation);
        if((ok & 2) != 0)
            canshade = true;
        if((ok & 4) != 0)
            noroc = true;
        theGraph.SetShade(canshade);
        theGraph.CanRoc(noroc);
        theGraph.CanAUC(isseparation);
        setLayout(new BorderLayout(2, 5));
        add("Center", theGraph);
        add("North", CurveSeparation);
        add("South", DemarcationControl);
        add("West", LskewKurtosis);
        add("East", RskewKurtosis);
        theGraph.repaint();
    }

    ROCGRAPH theGraph;
    SIDEPANEL CurveSeparation;
    SIDEPANEL DemarcationControl;
    SIDEPANEL LskewKurtosis;
    SIDEPANEL RskewKurtosis;
    boolean canshade;
    boolean noroc;
    boolean isseparation;
    int Qdemarcation;
    int Qseparation;
    String MainTitle;
    Color myBackground;
    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;
}