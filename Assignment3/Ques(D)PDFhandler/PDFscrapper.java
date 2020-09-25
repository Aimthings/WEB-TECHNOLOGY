import org.apache.poi.ss.usermodel.Cell;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.FileOutputStream;
import java.io.File;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.select.Elements;

public class PDFscrapper {
    public static void main(String[] args) {
        try {
            ArrayList<String> urlList = new ArrayList<String>();
            Set<String> urlSet = new HashSet<String>(), pdfSet = new HashSet<String>();
            int urlCount = 0, pdf = 0;
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Pdf_Urls");
            Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
            data.put(0, new Object[]{"Serial Number", "Title", "URLs"});
            String mainURL = "https://pec.ac.in/";
            urlList.add(mainURL);
            urlSet.add(mainURL);
            Document seed;
            Elements links;
            Elements iframe;
            for (int i = 0; i < urlList.size(); i++) {
                if (i >= 200) {
                    break;
                }
            try {
                String currUrl = urlList.get(i);
                seed = Jsoup.connect(currUrl).get();
                links = seed.select("a[href]");
                for (Element link : links) {
                    String Url;
                    Url = link.absUrl("href");
                    if (!urlSet.contains(Url) && Url.contains("https://pec.ac.in/")) {
                        if (Url.endsWith(".pdf") && !pdfSet.contains(Url)) {
                            ++pdf;
                            data.put(pdf, new Object[]{pdf,"pdf",Url});
                            pdfSet.add(Url);
                        } else {
                            urlList.add(Url);
                            urlSet.add(Url);
                            urlCount++;
                        }
                    }
                }
                iframe = seed.select("iframe");
                for (Element frame : iframe) {
                    String Url;
                    Url = frame.attr("data-src");
                    if (!urlSet.contains(Url) && Url.contains("https://pec.ac.in/") && Url.endsWith(".pdf") && !pdfSet.contains(Url)) {
                        ++pdf;
                        data.put(pdf, new Object[]{pdf,"pdf",Url});
                        pdfSet.add(Url);
                    }
                }
                Set<Integer> keyset = data.keySet();
                int rownum = 0;
                for (Integer key : keyset) {
                    Row row = sheet.createRow(rownum++);
                    Object[] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr) {
                      Cell cell = row.createCell(cellnum++);
                      if (obj instanceof String)
                        cell.setCellValue((String) obj);
                      else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                    }
                }
        }catch (Exception e) {
            e.printStackTrace();
        }
            try{
                System.out.println("Still Running");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }
            try {
                FileOutputStream out = new FileOutputStream(new File("Pdf_Urls.xlsx"));
                workbook.write(out);
                out.close();
                System.out.println("Total PDF found:"+pdf);
                System.out.println("Total URLs searched:"+urlCount);
                System.out.println("Pdf_Urls.xlsx written successfully on disk.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }    catch (Exception e) {
            e.printStackTrace();
      }
    }
}