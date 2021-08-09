

public class Time  {
    public static final Time MIDDAY = new Time(12, 0, 0);
    public static final Time MIDNIGHT = new Time(0, 0, 0);;
    private final int hours;
    private final int minutes;
    private final int seconds;

    public Time(int hours, int minutes, int seconds) {

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

    }

    public Time() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public Time(int hours, int minutes) throws Exception {
        if(!this.hoursValid(hours)) throw new IllegalArgumentException("Hora inválida");
        this.hours = hours;
        if(!this.minutesAndSecondsValid(minutes)) throw new IllegalArgumentException("Minuto inválida");
        this.minutes = minutes;
        this.seconds = 0;
    }

    public Boolean hoursValid(int hours){
        if(hours >= 0 && hours < 24) return true;
        return false;
    }

    public Boolean minutesAndSecondsValid(int value){
        if(value >= 0 && value < 60) return true;
        return false;
    }

    public Time plus(Time time) throws Exception {

        int seconds = time.seconds + this.seconds;
        int minutes = time.minutes + this.minutes;
        int hours = time.hours + this.hours;

        if (seconds > 59) {
            minutes += seconds / 60;
            seconds %= 60;
        }
        if (minutes > 59) {
            hours += minutes / 60;
            minutes %= 60;
        }
        if (hours > 23) {
            hours %= 24;
        }
        
        return new Time(hours, minutes, seconds); 
    }

    public int hours() {
        return this.hours;
    }

    public int minutes() {
        return this.minutes;
    }

    public int seconds() {
        return this.seconds;
    }

    public Time plusHours(int hours) throws Exception {
        hours += this.hours;
        
        if(hours > 23){
            hours %= 24;
        }
        return new Time(hours, this.minutes, this.seconds);
    }

    public Time plusMinutes(int minutes) throws Exception {
        minutes += this.minutes;
        int hours = 0;
        if(minutes > 59){
            hours = minutes / 60;
            minutes %= 60;
        }
        if(hours > 23){
            hours %= 24;
        }
        return new Time(this.hours + hours, minutes, this.seconds);
    }

    public Time plusSeconds(int seconds) throws Exception {
        seconds += this.seconds;
        int minutes = 0;
        int hours = 0;
        if(seconds > 59){
            minutes = seconds / 60;
            seconds %= 60;
        }
        if(minutes > 59){
            hours = minutes / 60;
            minutes %= 60;
        }
        if(hours > 23){
            hours %= 24;
        }
        
        return new Time(this.hours + hours, this.minutes + minutes, seconds);
    }

    public Time minusHours(int hours) throws Exception{
        hours = this.hours - hours;
        if(hours < 0){
            hours = 0;
        }
        return new Time(hours, this.minutes, this.seconds);
    }

    public Time minusMinutes(int minutes) throws Exception {
        minutes = this.minutes - minutes;
        int hours = this.hours;
        if(minutes < 0){
            while(minutes < 0){
                minutes += 60;
                hours -= 1;
                if(hours < 0){
                    hours = 0;
                } 
            }
        }
        return new Time(hours, minutes, this.seconds);
    }

    public Time minusSeconds(int seconds) throws Exception {
        seconds = this.seconds - seconds;
        int minutes = this.minutes;
        int hours = this.hours;
        if(seconds < 0){
            while(seconds < 0){
                seconds += 60;
                minutes -= 1;
                if(minutes < 0){
                    minutes = 0;
                } 
            }
        }
        if(minutes < 0){
            while(minutes < 0){
                minutes += 60;
                hours -= 1;
            }
        }
        if(hours < 0){
            hours += 24;
        } 
        return new Time(hours, minutes, seconds);
    }

    public Time minus(Time time) throws Exception {
        int seconds = this.seconds - time.seconds;
        int minutes = this.minutes - time.minutes;
        int hours = this.hours - time.hours;
        if(seconds < 0){
            while(seconds < 0){
                seconds += 60;
                minutes -= 1;
                if(minutes < 0){
                    minutes = 0;
                } 
            }
        }
        if(minutes < 0){
            while(minutes < 0){
                minutes += 60;
                hours -= 1;
            }
        }
        if(hours < 0){
            hours += 24;
        }
        return new Time(hours, minutes, seconds);
    }

    public boolean isMidDay() {
        if(this.hours == 12 && this.minutes == 0 && this.seconds == 0) return true;
        return false;
    }

    public boolean isMidNight() {
        if(this.hours == 0 && this.minutes == 0 && this.seconds == 0) return true;
        return false;
    }

    public Time shift() throws Exception {
        Time time = this.plusHours(12);
        return time;
    }

    public Time tick() throws Exception {
        Time time = this.plusSeconds(1);
        return time;
    }

    @Override
    public String toString(){
        String hours = "";
        String minutes = "";
        String seconds = "";
        
        if(this.hours < 10)hours = "0" + this.hours;
        else if(this.hours == 0) hours = "00";
        else hours = String.valueOf(this.hours);

        if(this.minutes < 10)minutes = "0" + this.minutes;
        else if(this.minutes == 0) minutes = "00";
        else minutes = String.valueOf(this.minutes);

        if(this.seconds < 10)seconds = "0" + this.seconds;
        else if(this.seconds == 0) seconds = "00";
        else seconds = String.valueOf(this.seconds);
        
        return hours +":"+ minutes +":"+ seconds;
    }

    public String toLongString() {
        String result = "";
        if(this.hours > 1) result += this.hours + " horas";
        if(this.hours == 1) result += this.hours + " hora";
        if(this.seconds == 0 && this.minutes == 1) result += " e " + this.minutes + " minuto";
        if(this.seconds == 0 && this.minutes > 1) result += " e " + this.minutes + " minutos";
        if(this.seconds > 1 && this.minutes == 1) result += " " + this.minutes + " minuto e " + this.seconds + " segundos";
        if(this.seconds > 1 && this.minutes > 1) result += " " + this.minutes + " minutos e " + this.seconds + " segundos";
        if(this.seconds == 1 && this.minutes == 1) result += " " + this.minutes + " minuto e " + this.seconds + " segundo";
        if(this.seconds == 1 && this.minutes > 1) result += " " + this.minutes + " minutos e " + this.seconds + " segundo";
        return result;
    }
    
    public static Time fromString(String time) throws Exception {
        String[] timeSplit = time.split(":");
        int hours = Integer.parseInt(timeSplit[0]);
        int minutos = Integer.parseInt(timeSplit[1]);
        int seconds = Integer.parseInt(timeSplit[2]);
        return new Time(hours, minutos, seconds);
    }

    public static Time fromDouble(double d) throws Exception {
        int totalSegundosHora = 60 * 60;
        double valorDecimal = d - (int)(d);
        double x = totalSegundosHora * (valorDecimal * 100);
        x /= 100;
        int xInt = (int) x;
        int hours = (int) d;
        int minutes = xInt / 60;
        int seconds = xInt % 60;
        return new Time(hours, minutes, seconds);
    }

    public static Time fromSeconds(int seconds) throws Exception {

        int oneHours = 60 * 60;
        int oneMinutes = 60;

        int hours = seconds / oneHours;
        seconds %= oneHours;
        
        int minutes = seconds / oneMinutes;
        seconds %= oneMinutes;
        
        return new Time(hours, minutes, seconds);

    }

    public double toDouble() {
        double number = this.hours;
        double segunds = (this.minutes * 60) + this.seconds;
        double decimal = segunds / (60 * 60);
        decimal = Math.round(decimal * 100000000.0)/100000000.0;
        return number + decimal;
    }

    public static Time from(Time tr5) {
        return tr5;
    }

    public String toShortString() {
        String result = "";
        
        if (this.hours < 10) result += "0"+ this.hours +"h";
        else result += this.hours +"h";
        
        if (this.minutes < 10 && !(this.minutes == 0 && this.seconds == 0)) result += "0"+ this.minutes +"m";
        else if(!(this.minutes == 0 && this.seconds == 0)) result += this.minutes +"m";

        if (this.seconds < 10 && this.seconds > 0) result += "0"+ this.seconds +"s";
        else if(this.seconds > 0) result += this.seconds +"s";
        return result;
    }

    public int toInt() {

        return (this.hours * (60 * 60) + this.minutes * 60 + this.seconds); 
    } 

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(obj instanceof Time){
            Time time = (Time) obj;
            return this.hours == time.hours && this.minutes == time.minutes && this.seconds == time.seconds;
        }
        return false;
    }   

}
