import org.apache.poi.ss.usermodel.Cell;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ScrapperToExcel{
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("URL_and_Ptags");
        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
        data.put(0, new Object[]{ "Serial Number","Title/Tags", "URLs/Text"});
        final Document doc = Jsoup.connect("https://pec.ac.in/").get();
        int count=1;
        for(Element selectResult:doc.select("a[href]")) {
            final String title = selectResult.text();
            final String url = selectResult.attr("abs:href");
            data.put(count, new Object[]{ count, title, url});
            ++count;
        }
        for(Element selectResult:doc.select("p")){
            final String title = selectResult.text();
            data.put(count, new Object[]{ count, "p", title});
            ++count;
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
                    cell.setCellValue((String)obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("URLandPtag.csv"));
            workbook.write(out);
            out.close();
            System.out.println("URLandPtag.xlsx written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
