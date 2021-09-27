package terminal;

import java.io.*;

public class AccountWriterReader {
    public String getFilename() {
        String src = "./accountList.txt";
        return src;
    }

    public void saveMapToFile() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(getFilename()))) {
            out.write(Account.accountList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMapFromFile() {
        File file = new File(getFilename());
        if (file.exists()) {
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                String line;
                Account.accountList.clear();
                while ((line = in.readLine()) != null) {
                    parsSave(line);
                }
                in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parsSave(String str){
        String[] part = str.split("[=]");
        NoteAccount noteAccount=new NoteAccount(Integer.valueOf(part[0]));
        noteAccount.getList().clear();
        for (int i = 0; i <Integer.valueOf(part[1]) ; i++) {
            noteAccount.getList().add(new Note(Integer.valueOf(part[0])));
        }
        Account.accountList.add(noteAccount);
    }
}

