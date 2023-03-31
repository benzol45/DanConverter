package com.example.convertor.service;

import com.example.convertor.entity.LearnProgram;
import com.example.convertor.entity.PersonResult;
import com.example.convertor.entity.ResultsHolder;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReportConverter {
    private final ResultsHolder resultsHolder;

    @Autowired
    public ReportConverter(ResultsHolder resultsHolder) {
        this.resultsHolder = resultsHolder;
    }

    public Map<String,String> convert(MultipartFile multipartFile, String organizationName, String organizationInn) {
        Map<String,String> results = new HashMap<>();
        try {
            List<PersonResult> personResultList = readData(multipartFile);
            Map<String,String> result = generateXML(personResultList, organizationName, organizationInn);
            results.putAll(result);
        } catch (IOException | NotOfficeXmlFileException e) {
            results.put("error",e.getMessage());
        }

        String filename = multipartFile.getOriginalFilename().replaceAll("\\.xlsx","");
        resultsHolder.put(filename, results);

        return results;
    }

    public List<PersonResult> readData(MultipartFile multipartFile) throws IOException {
        List<PersonResult> personResultList= new ArrayList();

        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        int rowCounter = 0;
        for (Row row : sheet) {
            //for (Cell cell : row) {  }
            Cell cell = row.getCell(1);
            if (cell==null) {
                //Дальше пустые строки ?
                break;
            }
            rowCounter++;
            if (rowCounter==1) {
                //Заголовок
                //TODO Проверить порядок столбцов и если нет - отпадаем
                continue;
            }

            personResultList.add(mapRowToPersonResult(row,rowCounter));
        }
        workbook.close();

        return personResultList;
    }

    private PersonResult mapRowToPersonResult(Row row, int rowNumber) {
        return new PersonResult(
                rowNumber,
                row.getCell(1).getStringCellValue().trim(),
                row.getCell(2).getStringCellValue().trim(),
                row.getCell(3).getStringCellValue().trim(),
                row.getCell(4).getStringCellValue().trim(),
                row.getCell(5).getStringCellValue().trim(),
                row.getCell(6).getStringCellValue().trim(),
                row.getCell(7).getNumericCellValue(),
                row.getCell(8).getStringCellValue().trim(),
                row.getCell(9).getDateCellValue(),
                getProtocolNumber(row.getCell(10)));
    }

    private String getProtocolNumber(Cell cell) {
        if (cell.getCellType()== CellType.NUMERIC) {
            return Integer.toString(Double.valueOf(cell.getNumericCellValue()).intValue());
        } else {
            return cell.getStringCellValue().trim();
        }
    }

    public Map<String,String> generateXML(List<PersonResult> personResultList, String organizationName, String organizationInn) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<RegistrySet xsi:noNamespaceSchemaLocation=\"schema.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n");

        StringBuilder problems = new StringBuilder();
        for (PersonResult personResult: personResultList) {
            if (findLearnProgramId(personResult.getStudyingTitle())==23) {
                problems.append("row " + personResult.getNumber() + " : " + personResult.getSurname() + " " + personResult.getName() + " " + personResult.getFatherName() + "\n");
                continue;
            }

            sb.append("  <RegistryRecord>\n");
                sb.append("    <Worker>\n");
                    sb.append("      <LastName>" + personResult.getSurname() + "</LastName>\n");
                    sb.append("      <FirstName>" + personResult.getName() + "</FirstName>\n");
                    sb.append("      <MiddleName>" + personResult.getFatherName() + "</MiddleName>\n");
                    sb.append("      <Snils>" + personResult.getSNILS() + "</Snils>\n");
                    sb.append("      <Position>" + personResult.getPosition() + "</Position>\n");
                    sb.append("      <EmployerInn>" + BigDecimal.valueOf(personResult.getCompanyINN()).toPlainString() + "</EmployerInn>\n");
                    sb.append("      <EmployerTitle>" + personResult.getCompany() + "</EmployerTitle>\n");
                sb.append("    </Worker>\n");
                sb.append("    <Organization>\n");
                    sb.append("      <Inn>" + organizationInn + "</Inn>\n");
                    sb.append("      <Title>" + organizationName + "</Title>\n");
                sb.append("    </Organization>\n");
                sb.append("    <Test isPassed=\"true\" learnProgramId=\"" + findLearnProgramId(personResult.getStudyingTitle()) + "\">\n");
                    sb.append("      <Date>" + dateFormat.format(personResult.getCheckDate())+"T01:00:00" + "</Date>\n");
                    sb.append("      <ProtocolNumber>" + personResult.getProtocolNumber() + "</ProtocolNumber>\n");
                    sb.append("      <LearnProgramTitle>" + personResult.getStudyingTitle() + "</LearnProgramTitle>\n");
                sb.append("    </Test>\n");
            sb.append("  </RegistryRecord>\n");
        }
        sb.append("</RegistrySet>");

        return Map.of("result",sb.toString(),"problem",problems.toString());
    }

    private Integer findLearnProgramId(String title) {
        return Arrays.stream(LearnProgram.values())
                .filter(e->title.toLowerCase().contains(e.getPatternWord().toLowerCase()))
                .map(e->e.getId())
                .findAny()
                .orElseThrow(()->new IllegalStateException("Can't find Learn Program by title " + title));
    }




//    String filePath = "C:\\den\\210949_Елисеева — копия.xlsx";
//    ReportConverter reportConverter = new ReportConverter(filePath,
//            "Общество с ограниченной ответственностью «ЭсАрДжи. Учебный центр»",
//            "3444171684");
//
//        try {
//        reportConverter.readData();
//    } catch (IOException e) {
//        throw new IllegalStateException(e);
//    }
//
//    String report = reportConverter.generateXML();
//        try {
//        Files.writeString(Path.of(filePath.replaceAll("\\.xlsx",".xml")),report);
//    } catch (IOException e) {
//        throw new IllegalStateException(e);
//    }
}
