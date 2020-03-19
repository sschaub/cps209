// See https://en.wikipedia.org/wiki/Date_format_by_country for date formats

import java.util.*;

abstract class DateFormatter { 
    public abstract String format(Date date);
}
 
class BritishDateFormatter extends DateFormatter {
    public String format(Date date)
    {
        return String.format("%d-%d-%d", date.Day, date.Month, date.Year);
    }
}

class USDateFormatter extends DateFormatter {
    public String format(Date date)
    {
        return String.format("%d-%d-%d", date.Month, date.Day, date.Year);
    }
}


class DateFormatterFactory {
    protected String locale;

    public DateFormatterFactory(String locale)
    {
        this.locale = locale;
    }

    public DateFormatter MakeDateFormatter()
    {
        switch (locale)
        {
            case "gb": 
                return new BritishDateFormatter();
            case "us": 
                return new USDateFormatter();
            default:
                throw new IllegalArgumentException("Unsupported locale: " + locale);
        }
    }
}
 
class FormatDates {
    public static void main(String[] args)
    {
        String locale = args[0];

        DateFormatter formatter = new USDateFormatter();

        System.out.println("Today is: " + formatter.format(new Date()));
    }
}