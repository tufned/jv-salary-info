package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int totalIncome = 0;
            for (String record : data) {
                String[] recordValues = record.split(" ");
                if (name.equalsIgnoreCase(recordValues[1])
                        && checkDateWithinPeriod(recordValues[0], dateFrom, dateTo)) {
                    totalIncome +=
                            Integer.parseInt(recordValues[2]) * Integer.parseInt(recordValues[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(totalIncome);
        }
        return builder.toString();
    }

    private boolean checkDateWithinPeriod(String dateValue, String dateFrom, String dateTo) {
        LocalDate date = LocalDate.parse(dateValue, formatter);
        LocalDate dateFromPrep = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToPrep = LocalDate.parse(dateTo, formatter);
        return (date.isAfter(dateFromPrep) || date.isEqual(dateFromPrep))
                && (date.isBefore(dateToPrep) || date.isEqual(dateToPrep));
    }
}
