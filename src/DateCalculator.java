/**
 * represents a date calculator
 */
public class DateCalculator
{
    /**
     * check if the year is a leap year
     * @param year a year number
     * @return true if the year is leap, false otherwise
     */
    public static boolean isLeapYear(int year)
    {
        if((year%400 == 0) || ((year%4 == 0) && (year%100!=0)))
        {
            return  true;
        }
        return  false;
    }

    /**
     * check how many days there are in the date's month
     * @param date a date
     * @return the number of days in the date's month
     */
    public static int daysInMonth(Date date)
    {
        if(date.getMonth() == 1|| date.getMonth() == 3||date.getMonth() == 5||date.getMonth() == 7||
                date.getMonth() == 8||date.getMonth() == 10||date.getMonth() == 12)
        {
            return 31;
        }
        else if (date.getMonth() == 4|| date.getMonth() == 6||date.getMonth() == 9||date.getMonth() == 11)
        {
            return 30;
        }
        else if (isLeapYear(date.getYear()))
        {
            return 29; //february in leap year
        }
        else return 28; //february in regular year
    }

    /**
     * calculates the date that comes a certain amount of days after the given date
     * @param date a given date
     * @param num an amount of days
     * @return the date that comes "num" days after date
     */
    public static Date addToDate(Date date, int num)
    {
        if (num == 0)
        {
            return date;
        }
        else if (num >0)
        {
            int daysInYear;
            //calculates the number of days until the same date next year
            if(isLeapYear(date.getYear()) &&  ((date.getMonth() == 1)||(date.getMonth()==2)&&(date.getDay() <= 28)) ||
                    isLeapYear(date.getYear()+1)&&(date.getMonth()>2))
            {
                daysInYear = 366;
            }
            else daysInYear = 365;
            if (num >= daysInYear)
            { //gets to the same date next year
                return addToDate(new Date(date.getDay(), date.getMonth(), date.getYear()+1), num-daysInYear);
            }
            else //if we need to move only some months
            {
                if(date.getMonth()==12)
                {
                    if(date.getDay()+ num > 31) //if we need to get to the next month
                    { //add a year in december
                        return addToDate(new Date(1, 1, date.getYear() + 1), num - 31 + date.getDay()-1); //to see about -1
                    }
                    else
                    {//add the remaining days
                        return (new Date(date.getDay()+num,12, date.getYear()));
                    }
                }
                else
                {
                    if(date.getDay()+num > daysInMonth(date))
                    { //if we need to get to the next month, takes to the first day of next month
                        return addToDate(new Date(1, date.getMonth()+1, date.getYear()),
                                num - daysInMonth(date) + date.getDay()-1);
                    }
                    else
                    {//add the remaining days
                        return new Date(date.getDay()+num, date.getMonth(), date.getYear());
                    }
                }
            }
        }
        else
        {
            int daysInYear;
            //calculates the number of days from the same date last year
            if(isLeapYear(date.getYear()-1) && (date.getMonth()<3) ||
                    isLeapYear(date.getYear()) && (date.getMonth()>2 || (date.getMonth()==2 &&date.getDay()==29)))
            {
                daysInYear = -366;
            }
            else daysInYear = -365;
            if (num <= daysInYear)
            { //gets to the same date next year
                return addToDate(new Date(date.getDay(), date.getMonth(), date.getYear()-1), num-daysInYear);
            }
            else //if we need to move only some months
            {
                if(date.getMonth()==1)
                {
                    if(date.getDay() + num < 1) //if we need to get to the last month
                    {//subtract a year in december
                        return addToDate(new Date(31, 12, date.getYear() - 1), num + date.getDay());
                    }
                    else
                    {//subtract the remaining days
                        return (new Date(date.getDay()+num,1, date.getYear()));
                    }
                }
                else
                {
                    if(date.getDay()+num < 1)
                    { //if we need to get to the last month, takes to the last day of last month
                        return addToDate(new Date(daysInMonth(new Date(date.getDay(), date.getMonth()-1,
                                date.getYear())), date.getMonth()-1, date.getYear()), num + date.getDay());
                    }
                    else
                    {//subtract the remaining days
                        return new Date(date.getDay()+num, date.getMonth(), date.getYear());
                    }
                }
            }
        }

    }
}

