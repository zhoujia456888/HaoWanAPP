package cn.zhoujia.haowanapp.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "NOTEPAD".
 */
public class Notepad {

    private Long id;
    private String notepadcontent;
    private String notepaddate;

    public Notepad() {
    }

    public Notepad(Long id) {
        this.id = id;
    }

    public Notepad(Long id, String notepadcontent, String notepaddate) {
        this.id = id;
        this.notepadcontent = notepadcontent;
        this.notepaddate = notepaddate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotepadcontent() {
        return notepadcontent;
    }

    public void setNotepadcontent(String notepadcontent) {
        this.notepadcontent = notepadcontent;
    }

    public String getNotepaddate() {
        return notepaddate;
    }

    public void setNotepaddate(String notepaddate) {
        this.notepaddate = notepaddate;
    }

}
