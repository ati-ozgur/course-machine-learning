// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 26.03.2006 20:33:25
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   roc.java

import java.awt.*;

class ROCGRAPH extends Canvas
{

    public ROCGRAPH()
    {
        pinkish = new Color(255, 204, 204);
        bluish = new Color(204, 204, 255);
        CUTOFF = 0.40000000000000002D;
        Aoffset = 0.29999999999999999D;
        Boffset = 0.45000000000000001D;
        Yoffset = 115;
        ROCxcut = 0;
        smallfont = new Font("Ariel", 1, 9);
        mediumfont = new Font("Ariel", 1, 13);
        largefont = new Font("Ariel", 1, 17);
        reciproot2pi = 1.0D / Math.sqrt(6.2831853070000001D);
        NORMALCURVE = new double[111];
        ROCx = new int[300];
        ROCy = new int[300];
        AUCdata = new double[111];
        AUCnormal = 0.0D;
        for(int i = -1; i < 299;)
        {
            i++;
            ROCx[i] = -1;
            ROCy[i] = -1;
        }

        for(int i = 111; i > 0;)
        {
            double xval = (double)(--i) / 37D;
            xval = -(xval * xval) / 2D;
            NORMALCURVE[i] = Math.exp(xval) * reciproot2pi;
            AUCnormal += NORMALCURVE[i];
            AUCdata[110 - i] = AUCnormal;
        }

        AUCnormal *= 2D;
    }

    public void CanAUC(boolean na)
    {
        isAUC = na;
    }

    public void CanRoc(boolean nr)
    {
        noroc = nr;
    }

    public void CurveOutline(Graphics g, int wCanvas, int hCanvas, double jOffset)
    {
        int cWidth = 400;
        int cHeight = 100;
        int ijOffset = (int)(jOffset * (double)cWidth);
        int i = 0;
        int yJ;
        for(int oldyJ = Yoffset + cHeight; i < cWidth; oldyJ = yJ)
        {
            i++;
            int idx = ijOffset - i;
            idx = Math.abs(idx);
            double Jval;
            if(idx > 110)
                Jval = 0.0D;
            else
                Jval = NORMALCURVE[idx];
            yJ = (int)((double)cHeight * (Jval / 0.39900000000000002D));
            yJ = (Yoffset + cHeight) - yJ;
            g.setColor(Color.black);
            g.drawLine(i - 1, oldyJ, i, yJ);
        }

    }

    public void MoveCutoff(int cOff)
    {
        CUTOFF = (double)cOff / 124D;
        repaint();
    }

    public void MoveSeparation(int cOff)
    {
        if(cOff < 1)
            cOff = 1;
        if(cOff > 100)
            cOff = 100;
        Boffset = Aoffset + (double)cOff / 300D;
        repaint();
    }

    public void PaintRectangle(Graphics g, int x, int y, int w, int h, Color clr)
    {
        int count = h;
        int yco = y;
        g.setColor(clr);
        for(; count > 0; count--)
        {
            g.drawLine(x, yco, x + w, yco);
            yco++;
        }

    }

    public void PlotNormalCurves(Graphics g, int wCanvas, int hCanvas, boolean canshade)
    {
        int cWidth = 400;
        int cHeight = 100;
        int iAoffset = (int)(Aoffset * (double)cWidth);
        int iBoffset = (int)(Boffset * (double)cWidth);
        int xcut = (int)(CUTOFF * (double)cWidth);
        int i = -1;
        double TNF = 0.0D;
        double FNF = 0.0D;
        ROCx[0] = 0;
        ROCy[0] = 0;
        int iXY = 1;
        boolean toggle = true;
        while(i < cWidth) 
        {
            i++;
            if(canshade)
                toggle ^= true;
            int idx = iAoffset - i;
            if(idx < 0)
                idx++;
            idx = Math.abs(idx);
            double Aval;
            if(idx > 110)
                Aval = 0.0D;
            else
                Aval = NORMALCURVE[idx];
            int yA = (int)((double)cHeight * (Aval / 0.39900000000000002D));
            yA = (Yoffset + cHeight) - yA;
            idx = iBoffset - i;
            if(idx < 0)
                idx++;
            idx = Math.abs(idx);
            double Bval;
            if(idx > 110)
                Bval = 0.0D;
            else
                Bval = NORMALCURVE[idx];
            int yB = (int)((double)cHeight * (Bval / 0.39900000000000002D));
            yB = (Yoffset + cHeight) - yB;
            if(i < xcut)
            {
                g.setColor(Color.blue);
                g.drawLine(i, cHeight + Yoffset, i, yA);
                if(toggle)
                {
                    g.setColor(pinkish);
                    g.drawLine(i, cHeight + Yoffset, i, yB);
                }
            } else
            {
                g.setColor(Color.red);
                g.drawLine(i, cHeight + Yoffset, i, yB);
                if(toggle)
                {
                    g.setColor(bluish);
                    g.drawLine(i, cHeight + Yoffset, i, yA);
                }
            }
            TNF += Aval;
            FNF += Bval;
            double FPF = 1.0D - TNF / AUCnormal;
            double TPF = 1.0D - FNF / AUCnormal;
            int iFPF = (int)(FPF * 150D);
            int iTPF = (int)(TPF * 150D);
            if(iFPF != ROCx[-1 + iXY] || iTPF != ROCy[-1 + iXY])
            {
                ROCx[iXY] = iFPF;
                ROCy[iXY] = iTPF;
                iXY++;
            }
            if(i == xcut)
            {
                ROCxcut = -1 + iXY;
                rTPF = TPF;
                rFPF = FPF;
            }
        }
        ROCdatapoints = iXY;
    }

    public void ROCbox(Graphics g, int wCanvas, int hCanvas)
    {
        int spacing = 15;
        double AUC = 0.0D;
        int y0roc = spacing;
        int wRoc = 150;
        int hRoc = 150;
        int x0roc = wCanvas - spacing - wRoc;
        int i = -1;
        int oldFPF = x0roc;
        int oldTPF = hRoc + y0roc;
        g.setColor(Color.black);
        if(!noroc)
        {
            int iTPF;
            int iFPF;
            while(i < ROCdatapoints) 
            {
                i++;
                iTPF = -ROCy[i];
                iFPF = ROCx[i];
                iFPF += x0roc;
                iTPF += hRoc + y0roc;
                g.drawLine(oldFPF, oldTPF, iFPF, iTPF);
                if(i > 1)
                    AUC += (oldFPF - iFPF) * ((oldTPF - y0roc) + (iTPF - oldTPF) / 2);
                oldFPF = iFPF;
                oldTPF = iTPF;
            }
            iTPF = -ROCy[ROCxcut];
            iFPF = ROCx[ROCxcut];
            iFPF += x0roc;
            iTPF += hRoc + y0roc;
            g.setColor(Color.red);
            g.drawLine(iFPF, y0roc + 149, iFPF, 1 + iTPF);
            g.setColor(pinkish);
            g.drawLine(iFPF, 1 + y0roc, iFPF, -1 + iTPF);
            g.setColor(bluish);
            g.drawLine(1 + x0roc, iTPF, -1 + iFPF, iTPF);
            g.setColor(Color.blue);
            g.drawLine(x0roc + 149, iTPF, 1 + iFPF, iTPF);
            g.setColor(Color.black);
            g.setFont(largefont);
            g.drawString("ROC curve", x0roc + 30, y0roc + 188);
            g.setFont(mediumfont);
            g.drawString("1", x0roc - 10, y0roc);
            g.drawString("TPF", x0roc - 30, y0roc + 75);
            g.drawString("0", x0roc - 10, y0roc + 165);
            g.drawString("FPF", x0roc + 60, y0roc + 165);
            g.drawString("1", x0roc + 140, y0roc + 165);
            AUC = 1.0D - AUC / 22500D;
            String sAUC = Double.toString(AUC);
            sAUC = prettyUp(sAUC);
            if(isAUC)
            {
                g.drawString("A.U.C ~ ", x0roc + 55, y0roc + 130);
                g.drawString(sAUC, x0roc + 105, y0roc + 130);
            }
            g.setColor(Color.black);
            g.drawLine(x0roc, y0roc, x0roc + wRoc, y0roc);
            g.drawLine(x0roc, y0roc, x0roc, y0roc + hRoc);
            g.drawLine(x0roc, -1 + y0roc + hRoc, x0roc + wRoc, -1 + y0roc + hRoc);
            g.drawLine(-1 + x0roc + wRoc, y0roc, -1 + x0roc + wRoc, y0roc + hRoc);
        }
        PaintRectangle(g, x0roc - 129, y0roc + 1, 39, 39, Color.red);
        PaintRectangle(g, x0roc - 89, y0roc + 1, 39, 39, bluish);
        PaintRectangle(g, x0roc - 129, y0roc + 41, 39, 39, pinkish);
        PaintRectangle(g, x0roc - 89, y0roc + 41, 39, 39, Color.blue);
        g.setColor(Color.black);
        g.drawLine(x0roc - 130, y0roc, x0roc - 50, y0roc);
        g.drawLine(x0roc - 130, y0roc, x0roc - 130, y0roc + 80);
        g.drawLine(x0roc - 130, y0roc + 80, x0roc - 50, y0roc + 80);
        g.drawLine(x0roc - 50, y0roc, x0roc - 50, y0roc + 80);
        g.drawLine(x0roc - 90, y0roc, x0roc - 90, y0roc + 80);
        g.drawLine(x0roc - 130, y0roc + 40, x0roc - 50, y0roc + 40);
        g.drawString("FNF", x0roc - 120, y0roc + 55);
        g.drawString("FPF", x0roc - 80, y0roc + 15);
        g.setColor(Color.white);
        g.drawString("TPF", x0roc - 120, y0roc + 15);
        g.drawString("TNF", x0roc - 80, y0roc + 55);
        g.setFont(smallfont);
        String Fval = Double.toString(rTPF);
        Fval = prettyUp(Fval);
        g.drawString(Fval, x0roc - 120, y0roc + 35);
        Fval = Double.toString(1.0D - rFPF);
        Fval = prettyUp(Fval);
        g.drawString(Fval, x0roc - 80, y0roc + 75);
        g.setColor(Color.black);
        Fval = Double.toString(1.0D - rTPF);
        Fval = prettyUp(Fval);
        g.drawString(Fval, x0roc - 120, y0roc + 75);
        Fval = Double.toString(rFPF);
        Fval = prettyUp(Fval);
        g.drawString(Fval, x0roc - 80, y0roc + 35);
    }

    public void SetShade(boolean cs)
    {
        canshade = cs;
    }

    public void drawMe()
    {
        repaint();
    }

    public void paint(Graphics g)
    {
        Rectangle myRect = getBounds();
        int wCanvas = myRect.width;
        int hCanvas = myRect.height;
        Yoffset = (-1 + hCanvas) - 100;
        int cWidth = 400;
        int xcut = (int)(CUTOFF * (double)cWidth);
        PlotNormalCurves(g, wCanvas, hCanvas, canshade);
        CurveOutline(g, wCanvas, hCanvas, Aoffset);
        CurveOutline(g, wCanvas, hCanvas, Boffset);
        g.setColor(Color.green);
        if(xcut < 3)
            xcut = 3;
        g.drawLine(xcut, 1, xcut, -2 + hCanvas);
        ROCbox(g, wCanvas, hCanvas);
        g.setFont(mediumfont);
        g.setColor(Color.black);
        g.drawString("relative", 8, 80);
        g.drawString("frequency", 8, 95);
        g.drawLine(0, 0, wCanvas, 0);
        g.drawLine(0, 0, 0, hCanvas);
        g.drawLine(0, -1 + hCanvas, wCanvas, -1 + hCanvas);
        g.drawLine(-1 + wCanvas, 0, -1 + wCanvas, hCanvas);
    }

    public String prettyUp(String str)
    {
        String expt = "";
        int epos = str.indexOf('e');
        if(epos < 1)
            epos = str.indexOf('E');
        if(epos > 0)
        {
            expt = str.substring(1 + epos, str.length());
            int expval = Integer.valueOf(expt).intValue();
            return "0.000";
        }
        if(str.length() > 5)
            str = str.substring(0, 5);
        return str;
    }

    static final double pi = 3.1415926535000001D;
    double CUTOFF;
    double Aoffset;
    double Boffset;
    double reciproot2pi;
    boolean canshade;
    boolean noroc;
    boolean isAUC;
    int Yoffset;
    Color pinkish;
    Color bluish;
    double NORMALCURVE[];
    int ROCx[];
    int ROCy[];
    int ROCdatapoints;
    double AUCdata[];
    double AUCnormal;
    int ROCxcut;
    double rTPF;
    double rFPF;
    Font smallfont;
    Font mediumfont;
    Font largefont;
}