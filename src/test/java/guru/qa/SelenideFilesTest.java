package guru.qa;

import org.apache.commons.exec.ExecuteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideFilesTest {


    @Test
    void downloadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File download = $("[href='https://github.com/junit-team/junit5/raw/refs/heads/main/README.md']").download();


        try(InputStream is = new FileInputStream(download)) {

            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Contributions to JUnit 5 are both welcomed and appreciated."));

            System.out.println();
        }
    }

    @Test
    void uploadFileTest(){
        open("https://fineuploader.com/demos.html");
        $("[input='file']").uploadFromClasspath("folder/cat.jpg");

    }
}
