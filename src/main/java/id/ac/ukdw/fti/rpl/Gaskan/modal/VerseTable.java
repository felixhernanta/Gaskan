package id.ac.ukdw.fti.rpl.Gaskan.modal;

public class VerseTable {
    private String verseEvent1;
    private String verseDate1;
    private String verseDuration1;
    private String AyatEvents1;

    public void setAyatEvents1(String AyatEvents1){
        this.AyatEvents1=AyatEvents1;
    }
    
    public void setVerseEvent1(String verseEvent1){
        this.verseEvent1=verseEvent1;
    }

    public void setVerseDate1(String verseDate1){
        this.verseDate1=verseDate1;
    }

    public void setVerseDuration1(String verseDuration1){
        this.verseDuration1=verseDuration1;
    }

    public String getAyatEvents1(){
        if (this.AyatEvents1==null) {
            return "unknown";
        }
        else{
            return this.AyatEvents1;
        } 
    }

    public String getVerseEvent1(){
        if (this.verseEvent1==null) {
            return "unknown";
        }
        else{
            return this.verseEvent1;
        } 
    }

    public String getVerseDate1(){
        if (this.verseDate1==null) {
            return "unknown";
        }
        else if(this.verseDate1.contains("-")){
            return this.verseDate1.substring(1,this.verseDate1.length())+" BC";
        }
        else{
            return "AD "+this.verseDate1;
        }   
    }

    public String getVerseDuration1(){
        if (this.verseDuration1==null) {
            return "unknown";
        }
        else{
            return this.verseDuration1;
        }   
    }

}
