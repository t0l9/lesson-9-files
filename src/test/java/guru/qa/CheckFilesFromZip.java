package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



public class CheckFilesFromZip {

    private ClassLoader cl = CheckFilesFromZip.class.getClassLoader();



    @Test
    void zipFileParsingTest() throws Exception{

        try (ZipInputStream zp = new ZipInputStream(cl.getResourceAsStream("zip.zip")))
        {
            ZipEntry zipEntry;

            while ((zipEntry = zp.getNextEntry()) != null){

                String FileName = zipEntry.getName();
                byte[] buffer = zp.readAllBytes();

                try (InputStream entryStream = new ByteArrayInputStream(buffer)) {
                    switch (FileName) {
                        case "4eae10bf98dde4f7356ebef161d365d5.pdf":
                            System.out.println("INFO... Провера PDF Файла");
                            checkPdf(entryStream);
                            System.out.println("INFO... Провера PDF Файла Выполнена");
                            break;
                        case "import_ou_csv.csv":
                            System.out.println("INFO... Провера CSV Файла");
                            checkCsv(entryStream);
                            System.out.println("INFO... Провера CSV Файла Выполнена");
                            break;
                        case "import_ou_xls.xls":
                            System.out.println("INFO... Провера XLS Файла");
                            checkXls(entryStream);
                            System.out.println("INFO... Провера XLS Файла Выполнена");
                            break;
                    }
                }


            }
        }
    }

    void checkXls(InputStream zipStream) throws Exception {
        XLS xls = new XLS(zipStream);

        String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(2).getStringCellValue();
        String actualSecondValue = xls.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue();

        Assertions.assertTrue(actualValue.contains("Название"));
        Assertions.assertTrue(actualSecondValue.contains("Коммерческий департамент"));
    }



    void checkPdf(InputStream zipStream) throws Exception {
        PDF pdf = new PDF(zipStream);
        Assertions.assertTrue(pdf.text.contains("Пример pdf"));
    }


    void checkCsv(InputStream zipStream) throws Exception {

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(zipStream))){
            List<String[]> data = csvReader.readAll();
            Assertions.assertArrayEquals(
                    new String[] {"QuotaAmount,StartDate,OwnerName,Username"}
                    , data.get(0)
            );

            Assertions.assertArrayEquals(
                    new String[] {"150000,2016-01-01,Chris Riley,trailhead9.ub20k5i9t8ou@example.com"}
                    , data.get(1)
            );
        }
    }


}
