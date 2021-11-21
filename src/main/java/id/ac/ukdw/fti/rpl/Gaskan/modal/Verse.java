package id.ac.ukdw.fti.rpl.Gaskan.modal;

public class Verse {
    private String verseEvent1;
    private int verseDate1;
    private String duration1;
    
    public void setVerseEvent1(String verseEvent1){
        this.verseEvent1=verseEvent1;
    }

    public void setVerseDate1(int verseDate1){
        this.verseDate1=verseDate1;
    }

    public void setDuration1(String duration1){
        this.duration1=duration1;
    }

    public String getVerseEvent1(){
        if (this.verseEvent1==null) {
            return "unknown";
        }
        else{
            return this.verseEvent1;
        } 
    }

    public int getVerseDate1(){
        if (Integer.toString(this.verseDate1)==null) {
            return 0;
        }
        else{
            return this.verseDate1;
        }
    }

    public String getDuration1(){
        if (this.duration1==null) {
            return "-";
        }
        else{
            return this.duration1;
        }
    }
}