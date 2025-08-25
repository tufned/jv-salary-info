package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final byte INDEX_DATE = 0;
    static final byte INDEX_NAME = 1;
    static final byte INDEX_HOURS = 2;
    static final byte INDEX_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int totalIncome = 0;
            for (String record : data) {
                String[] recordValues = record.split(" ");
                if (name.equalsIgnoreCase(recordValues[INDEX_NAME])
                        && checkDateWithinPeriod(recordValues[INDEX_DATE], dateFrom, dateTo)) {
                    totalIncome += Integer.parseInt(recordValues[INDEX_HOURS])
                                    * Integer.parseInt(recordValues[INDEX_RATE]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalIncome);
        }
        return builder.toString();
    }

    private boolean checkDateWithinPeriod(String dateValue, String dateFrom, String dateTo) {
        LocalDate date = LocalDate.parse(dateValue, DATE_FORMATTER);
        LocalDate dateFromPrep = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToPrep = LocalDate.parse(dateTo, DATE_FORMATTER);
        return (date.isAfter(dateFromPrep) || date.isEqual(dateFromPrep))
                && (date.isBefore(dateToPrep) || date.isEqual(dateToPrep));
    }
}
