// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 26.03.2006 20:32:38
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   roc.java

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class SIDEPANEL extends Panel
    implements Observer
{

    public SIDEPANEL(int compass, ROCGRAPH mygraph, String titl, boolean visib)
    {
        largefont = new Font("Ariel", 1, 17);
        mediumfont = new Font("Ariel", 1, 13);
        theGraph = mygraph;
        whichSide = compass;
        setLayout(new BorderLayout(5, 10));
        if(whichSide == 2)
        {
            noteme = new Watcher();
            noteme.addObserver(this);
            myScroll = new Slider(noteme, 0, 50, 10, 0, 100);
            add("Center", myScroll);
            eastLabel = new Label(" Test threshold                            ");
            eastLabel.setFont(mediumfont);
            eastLabel.setForeground(Color.green);
            add("East", eastLabel);
            westLabel = new Label("      ");
            add("West", westLabel);
            northLabel = new Label("          Test value>");
            northLabel.setFont(mediumfont);
            add("North", northLabel);
        }
        if(whichSide == 0)
        {
            northLabel = new Label(titl);
            northLabel.setFont(largefont);
            add("North", northLabel);
            noteme = new Watcher();
            noteme.addObserver(this);
            myScroll = new Slider(noteme, 0, 50, 10, 0, 100);
            add("Center", myScroll);
            eastLabel = new Label("  Curve Separation                                  ");
            eastLabel.setFont(mediumfont);
            add("East", eastLabel);
            eastLabel.setVisible(visib);
            westLabel = new Label("      ");
            add("West", westLabel);
            myScroll.setVisible(visib);
        }
        if(whichSide == 1 || whichSide == 3)
        {
            northLabel = new Label("     ");
            add("North", northLabel);
            southLabel = new Label("     ");
            add("South", southLabel);
            westLabel = new Label("      ");
            add("West", westLabel);
        }
    }

    public void SetMe(int valu)
    {
        noteme.setValue(valu);
    }

    public void update(Observable obs, Object arg)
    {
        if(whichSide == 2)
            theGraph.MoveCutoff(noteme.getValue());
        if(whichSide == 0)
            theGraph.MoveSeparation(noteme.getValue());
    }

    Font largefont;
    Font mediumfont;
    int whichSide;
    Label northLabel;
    Label southLabel;
    Label eastLabel;
    Label westLabel;
    Slider myScroll;
    Watcher noteme;
    ROCGRAPH theGraph;
    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;
}