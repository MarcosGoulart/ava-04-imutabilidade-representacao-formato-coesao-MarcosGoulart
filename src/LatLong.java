import java.io.UnsupportedEncodingException;

public class LatLong {

    public final double latitude;
    public final double longitude;


    public LatLong(){
        this.latitude = 0.0;
        this.longitude = 0.0; 
    }

    public LatLong(double latitude, double longitude) {
        if(latitude >= -90.0 && latitude <= 90.0) this.latitude = latitude;
        else {
            this.latitude = 0;
        }
        if(longitude >= -180.0 && longitude <= 180.0) this.longitude = longitude;
        else {
            this.longitude = 0;
        }
    }

    public LatLong norte(double latitude) {

        return new LatLong(this.latitude + latitude, this.longitude);
    }

    public LatLong sul(double latitude) {

        return new LatLong(this.latitude - latitude, this.longitude);
    }

    public LatLong leste(double longitude) {

        return new LatLong(this.latitude, this.longitude + longitude);
    }
    public LatLong oeste(double longitude) {

        return new LatLong(this.latitude, this.longitude - longitude);
    }

    public boolean noEquador() {
        if(this.latitude == 0) return true;
        return false;
    }

    public boolean emGreenwich() {
        if(this.longitude == 0) return true;
        return false;
    }

    public boolean noNorte() {
        if(this.latitude > 0 ) return true;
        return false;
    }

    public boolean noSul() {
        if(this.latitude < 0 ) return true;
        return false;
    }

    public boolean noOriente() {
        if(this.longitude > 0 ) return true;
        return false;
    }

    public boolean noOcidente() {
        if(this.latitude < 0 ) return true;
        return false;
    }

    public String toGrausDecimais() {

        String ladoLatitude = "";
        String ladoLongitude = "";
        Double novoLatitude = this.latitude;
        Double novoLongitude = this.longitude;
        if(this.latitude > 0){
            ladoLatitude = "°N";
        } else if(this.latitude < 0){
            novoLatitude *= -1;
            ladoLatitude = "°S";
        }
        if (this.longitude > 0) {
            ladoLongitude = "°E";
        } else if (this.longitude < 0) {
            novoLongitude *= -1;
            ladoLongitude = "°W";
        }

        return novoLatitude + ladoLatitude + ", " + novoLongitude + ladoLongitude;
    }

    @Override
    public String toString() {

        return this.latitude + ", " + this.longitude;
    }

    public String toGrausMinutosSegundos() throws UnsupportedEncodingException {
        String result = "";
        String ladoLatitude = "";
        String ladoLongitude = "";

        if (this.latitude > 0) {
            ladoLatitude = "N";
        } else if (this.latitude < 0) {
            ladoLatitude = "S";
        }
        if (this.longitude > 0) {
            ladoLongitude = "E";
        } else if (this.longitude < 0) {
            ladoLongitude = "W";
        }

        int horaLatitude = (int) this.latitude;
        double valorDecimal = this.latitude - horaLatitude;
        double minutoLatitude = 60 * valorDecimal;
        double segundoLatitude = (minutoLatitude - (int) minutoLatitude) * 60;
        int minutoLatitudeInt = (int) minutoLatitude;
        segundoLatitude = Math.round(segundoLatitude * 1000.0) / 1000.0;
        
        if (horaLatitude < 0) {
            horaLatitude *=  -1;
            result += horaLatitude + "°";
        } else {
            result += horaLatitude + "°";
        }
        if(minutoLatitudeInt < 0){
            minutoLatitudeInt *=  -1;
            result += minutoLatitudeInt + "'";
        } else {
            result += minutoLatitudeInt + "'";
        }
        if(segundoLatitude < 0){
            segundoLatitude *=  -1;
        }
        
        String segundoLatitudeString = String.valueOf(segundoLatitude);
        int j = 10;
        double temp = segundoLatitude;
        int cont = 0;
        for(int i = 0; i < 3; i++){
           
            
            if(temp % 1 == 0) {
                segundoLatitudeString += "0";
                cont++;
            }
            if(cont == 2) break;
            temp = segundoLatitude * j;
            j *= 10;
        }

        result += segundoLatitudeString + "\"" + ladoLatitude + ", ";

        int horaLongitude = (int) this.longitude;
        valorDecimal = this.longitude - horaLongitude;
        valorDecimal = Math.round(valorDecimal * 10000.0) / 10000.0;
        double minutoLongitude = 60 * valorDecimal;
        double segundoLongitude = (minutoLongitude - (int) minutoLongitude) * 60;
        int minutoLongitudeInt = (int) minutoLongitude;
        segundoLongitude = Math.round(segundoLongitude * 1000.0) / 1000.0;

       
        if (horaLongitude < 0) {
            horaLongitude *=  -1;
            result += horaLongitude + "°";
        } else {
            result += horaLongitude + "°";
        }
        if(minutoLongitudeInt < 0){
            minutoLongitudeInt *=  -1;
            result += minutoLongitudeInt + "'";
        } else {
            result += minutoLongitudeInt + "'";
        }
        if(segundoLongitude < 0){
            segundoLongitude *=  -1;
        }

        String segundoLongitudeString = String.valueOf(segundoLongitude);
        j = 10;
        temp = segundoLongitude;
        cont = 0;
        for (int i = 0; i < 3; i++) {

            if (temp % 1 == 0) {
                segundoLongitudeString += "0";
                cont++;
            }
            if(cont == 2) break;
            temp = segundoLongitude * j;
            j *= 10;
        } 
        result +=  segundoLongitudeString + "\"" + ladoLongitude;

        return result;
    }

    public static LatLong fromString(String latLong) {
        String[] latLongSplit = latLong.split(",");
        String latitude = latLongSplit[0];
        String longitude = latLongSplit[1];
        if(latLong.indexOf("S") == -1 && latLong.indexOf("N") == -1 && latLong.indexOf("L") == -1 && latLong.indexOf("W") == -1){
            return new LatLong(Double.parseDouble(latitude), Double.parseDouble(longitude));
        } else {

            String[] horaSplit = latitude.split("°");
            String horaLatitde = horaSplit[0];
    
            String[] minutoSplit = horaSplit[1].split("'");
            String minutoLatitde = minutoSplit[0];
    
            String[] segundoSplit = minutoSplit[1].split("\"");
            String segundoLatitde = segundoSplit[0];
    
            Double segundos = Double.parseDouble(minutoLatitde) * 60;
            segundos += Double.parseDouble(segundoLatitde);
    
            int segundosTotal = 60 * 60;
    
            Double latitudeResult = segundos / segundosTotal;
            latitudeResult += Double.parseDouble(horaLatitde);
    
            if(latitude.indexOf("S") != -1){
                latitudeResult *= -1;
            }
    
            ////// Longitude
            
            horaSplit = longitude.split("°");
            String horaLongitude = horaSplit[0];
    
            minutoSplit = horaSplit[1].split("'");
            String minutoLongitude = minutoSplit[0];
    
            segundoSplit = minutoSplit[1].split("\"");
            String segundoLongitude = segundoSplit[0];
    
            segundos = Double.parseDouble(minutoLongitude) * 60;
            segundos += Double.parseDouble(segundoLongitude);
    
            Double longitudeResult = segundos / segundosTotal;
            longitudeResult += Double.parseDouble(horaLongitude);
    
            if(longitude.indexOf("W") != -1){
                longitudeResult *= -1;
            }
    
            return new LatLong(latitudeResult, longitudeResult);
        }
    }
}