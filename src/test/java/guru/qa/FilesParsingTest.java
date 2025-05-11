package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import guru.qa.model.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilesParsingTest{

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    @Test
    void pdfFileTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href='junit-user-guide-5.12.2.pdf']").download();

        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("", pdf.author);
    }

    @Test
    void xlsFileTest() throws Exception {
        open("https://excelvba.ru/programmes/Teachers?ysclid=lfcu77j9j9951587711");
        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();

        XLS xls = new XLS(downloaded);

        String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();

        Assertions.assertTrue(actualValue.contains("Суммарное количество часов планируемое на штатную по всем разделам"));
    }

    @Test
    void csvFileParsingTest() throws Exception{

        try (InputStream is = cl.getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))){
            List<String[]> data = csvReader.readAll();
            Assertions.assertArrayEquals(
                    new String[] {"Selenide", "https://selenide.org"}
                    , data.get(0)
            );

            Assertions.assertArrayEquals(
                    new String[] {"JUnit 5", "https://junit.org"}
                    , data.get(1)
            );
        }
    }

    @Test
    void zipFileParsingTest() throws Exception{
        try (ZipInputStream zp = new ZipInputStream(cl.getResourceAsStream("sample.zip")))
        {
            ZipEntry zipEntry;

            while ((zipEntry = zp.getNextEntry()) != null){
                System.out.println(zipEntry.getName());
            }
        }
    }

    @Test
    void jsonFileTest() throws Exception{

        try (Reader reader = new InputStreamReader
                (cl.getResourceAsStream("gloss.json"))) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(234234, actual.get("ID").getAsInt());
            Assertions.assertEquals(234234, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary").getAsJsonObject();
            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
        }

    }

    @Test
    void jsonFileImproovedTest() throws Exception{

        try (Reader reader = new InputStreamReader
                (cl.getResourceAsStream("gloss.json"))) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(234234, actual.getId());

            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
        }

    }
}
