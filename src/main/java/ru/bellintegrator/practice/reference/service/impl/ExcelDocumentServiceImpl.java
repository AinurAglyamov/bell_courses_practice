package ru.bellintegrator.practice.reference.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.service.ExcelDocumentService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelDocumentServiceImpl implements ExcelDocumentService {

    @Override
    public List<Country> getCountryList(String fileLocation) {

        List<Country> countries = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet) {
                Integer code = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                countries.add(new Country(code, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countries;
    }
}
