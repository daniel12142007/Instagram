package com.example.instagram.api;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/audio")
@Tag(name = "Audio", description = "Audio for group and direct")
public class AudioApi {
    //    download
    @GetMapping("/download-pdf")
    public ResponseEntity<byte[]> generatePdf() {
        Document document = new Document();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("содержимое для скачивание"));
            document.close();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "example.pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    //    look
    @GetMapping("/look-pdf")
    public ResponseEntity<byte[]> generatesPdf() {
        Document document = new Document();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("содержимое для вида"));
            document.close();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
//     <dependency>
//            <groupId>com.itextpdf</groupId>
//            <artifactId>itextpdf</artifactId>
//            <version>5.5.13</version> <!-- Или используйте более новую версию, если доступна -->
//        </dependency>
}