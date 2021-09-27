package terminal;

import java.util.LinkedList;

public class NoteAccount {
    private  int noteDenomination;
    private  LinkedList<Note> list=new LinkedList<>();

    public NoteAccount(int noteDenomination){
        this.noteDenomination=noteDenomination;
        for (int i = 0; i <1000 ; i++) {
            list.add(new Note(noteDenomination));
        }
    }

    public LinkedList<Note> getList() {
        return list;
    }

    public int getNoteDenomination() {
        return noteDenomination;
    }
}
