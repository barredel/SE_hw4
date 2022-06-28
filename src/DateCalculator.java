public class DateCalculator
{

    public static boolean isLeapYear(int year)
    {
        if((year%400 == 0) || ((year%4 == 0) && (year%100!=0)))
        {
            return  true;
        }
        return  false;
    }


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
            return 29;
        }
        else return 28;
    }

    public static Date addToDate(Date date, int num)
    {// TODO: Add your code here...
        if (num == 0)
        {
            return date;
        }
        else if (num >0)
        {
            int daysInYear;
            if(isLeapYear(date.getYear()) &&  ((date.getMonth() == 1)||(date.getMonth()==2)&&(date.getDay() <= 28)) ||
                    isLeapYear(date.getYear()+1)&&(date.getMonth()>2))
            {
                daysInYear = 366;
            }
            else daysInYear = 365;
            if (num >= daysInYear)
            {
                return addToDate(new Date(date.getDay(), date.getMonth(), date.getYear()+1), num-daysInYear);
            }
            else
            {
                if(date.getMonth()==12)
                {
                    if(date.getDay()+ num > 31)
                    {
                        return addToDate(new Date(1, 1, date.getYear() + 1), num - 31 + date.getDay()-1); //to see about -1
                    }
                    else
                    {
                        return (new Date(date.getDay()+num,12, date.getYear()));
                    }
                }
                else
                {
                    if(date.getDay()+num > daysInMonth(date))
                    {
                        return addToDate(new Date(1, date.getMonth()+1, date.getYear()),
                                num - daysInMonth(date) + date.getDay()-1);
                    }
                    else
                    {
                        return new Date(date.getDay()+num, date.getMonth(), date.getYear());
                    }
                }
            }
        }
        else
        {
            int daysInYear;
            if(isLeapYear(date.getYear()-1) && (date.getMonth()<3) ||
                    isLeapYear(date.getYear()) && (date.getMonth()>2 || (date.getMonth()==2 &&date.getDay()==29)))
            {
                daysInYear = -366;
            }
            else daysInYear = -365;
            if (num <= daysInYear)
            {
                return addToDate(new Date(date.getDay(), date.getMonth(), date.getYear()-1), num-daysInYear);
            }
            else
            {
                if(date.getMonth()==1)
                {
                    if(date.getDay() + num < 1)
                    {
                        return addToDate(new Date(31, 12, date.getYear() - 1), num + date.getDay());
                    }
                    else
                    {
                        return (new Date(date.getDay()+num,1, date.getYear()));
                    }
                }
                else
                {
                    if(date.getDay()+num < 1)
                    {
                        return addToDate(new Date(daysInMonth(new Date(date.getDay(), date.getMonth()-1,
                                                       date.getYear())), date.getMonth()-1, date.getYear()),
                                                                        num + date.getDay());
                    }
                    else
                    {
                        return new Date(date.getDay()+num, date.getMonth(), date.getYear());
                    }
                }
            }
        }

    }
}

