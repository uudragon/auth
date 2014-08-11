package com.uud.commons.office;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class Outlining
{
  public static void main(String[] args)
    throws Exception
  {
    Outlining o = new Outlining();
    o.collapseRow();
  }

  private void collapseRow() throws Exception {
    SXSSFWorkbook wb2 = new SXSSFWorkbook(100);
    SXSSFSheet sheet2 = (SXSSFSheet)wb2.createSheet("new sheet");

    int rowCount = 20;
    for (int i = 0; i < rowCount; ++i) {
      sheet2.createRow(i);
    }

    sheet2.groupRow(4, 9);
    sheet2.groupRow(11, 19);

    sheet2.setRowGroupCollapsed(4, true);
    File f = new File("D:/outlining_collapsed.xlsx");
    FileOutputStream fileOut = new FileOutputStream(f);
    wb2.write(fileOut);
    fileOut.close();
    wb2.dispose();
  }
}