package com.eurochemix.webapp;

import com.eurochemix.webapp.model.ContactType;
import com.eurochemix.webapp.model.Resume;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Ilya on 28.02.2016.
 */
public class FileStorage extends AbstractStorage<File>{
    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if(!dir.isDirectory()||!dir.canWrite())
            throw new IllegalArgumentException( "'"+path+"' is not a directory or is not writable");


    }

    @Override
    protected void doClear() {
        File[] files =dir.listFiles();
        if(files == null)return;
        for (File file:files){
            doDelete(file);
        }
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected File getContext(String fileName) {
        return new File(fileName);
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(r.getFullName());
            dos.writeUTF(r.getLocation());
            dos.writeUTF(r.getHomePage());

            for(Map.Entry<ContactType, String> entry:r.getContacts().entrySet()){
                dos.writeInt(entry.getKey().ordinal());
                dos.writeUTF(entry.getValue());
            }

        } catch (IOException e){
            throw new WebAppException("Couldn't create file "+file.getAbsolutePath(),r,e);
        }

    }

    @Override
    protected void doUpdate(File file, Resume r) {

    }

    @Override
    protected Resume doLoad(File file) {
        return null;
    }

    @Override
    protected void doDelete(File file) {
        if(!file.delete()) throw new WebAppException("File "+file.getAbsolutePath()+ " can not be deleted");


    }

    @Override
    protected List<Resume> doGetAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
