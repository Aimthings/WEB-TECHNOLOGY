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
import org.jsoup.select.Elements;

public class FocusedCrawlerFaculty{
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Faculty_info");
        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
        data.put(0, new Object[]{ "Serial Number","Pic_image_src","Name_URL","Name_text","Department","Designation","Qualification","Interests"});

        final Document doc = Jsoup.connect("https://pec.ac.in/faculty-index").get();
        Elements body=doc.select("tbody");
        int count=1;
        for(Element each:body.select("tr"))
        {
            String img=each.select("td.views-field.views-field-field-profile-image img").attr("src");
            String name_url=each.select("td.views-field.views-field-title a").attr("abs:href");
            String name_text=each.select("td.views-field.views-field-title a").text();
            String depart=each.select("td.views-field.views-field-field-department").text();
            String desig=each.select("td.views-field.views-field-field-designation").text();
            String qual=each.select("td.views-field.views-field-field-qualification").text();
            String interest=each.select("td.views-field.views-field-field-research-interests").text();
            data.put(count, new Object[]{ count,img,name_url,name_text,depart,desig,qual,interest});
            ++count;
        }

        Set<Integer> keyset = data.keySet();
        int rowkanum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rowkanum++);   // Creating a new row in the sheet
            Object[] objArr = data.get(key);
            int cellkanumber = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellkanumber++);  // Creates a cell in the next column of the row
                if (obj instanceof String)
                    cell.setCellValue((String)obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("FocusedCrawl.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("FocusedCrawl.xlsx written successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
