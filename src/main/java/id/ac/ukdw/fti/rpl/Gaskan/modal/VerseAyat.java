package id.ac.ukdw.fti.rpl.Gaskan.modal;

public class VerseAyat {
    private String Ayat;
    private String Teks;
    
    public String getAyat() {
        if (this.Ayat==null) {
            return "unknown";
        }
        else{
            return Ayat;
        } 
        
    }

    public void setAyat(String ayat) {
        this.Ayat = ayat;
    }

    public void setTeks(String Teks) {
        this.Teks = Teks;
    }

    public String getTeks() {
        if (this.Teks==null) {
            return "unknown";
        }
        else{
            return Teks;
        } 
        
    }
}