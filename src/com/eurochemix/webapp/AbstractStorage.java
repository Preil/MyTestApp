package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ilya on 21.02.2016.
 */
abstract public class AbstractStorage implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Delete all resumes.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract boolean exist(String uuid);

    @Override
    public void save(Resume r) {
        logger.info("Save resume with uuid = " + r.getUuid());
        if (exist(r.getUuid()))
            throw new WebAppException("Resume with " + r.getUuid() + " already exist");
        doSave(r);
    }

    protected abstract void doSave(Resume r);

    @Override
    public void update(Resume r) {
        logger.info("Update resume with " + r.getUuid());
        if (!exist(r.getUuid()))
            throw new WebAppException("Resume with " + r.getUuid() + "doesn't exist");
        doUpdate(r);
    }

    protected abstract void doUpdate(Resume r);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with " + uuid);
        if (!exist(uuid))
            throw new WebAppException("Resume with " + uuid + "doesn't exist");
        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with " + uuid);
        if (!exist(uuid))
            throw new WebAppException("Resume with " + uuid + "doesn't exist");
        doDelete(uuid);
    }
    protected abstract void doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllsorted");
        List<Resume> list = doGetAll();
        Collections.sort(list, new Comparator<Resume>(){
            @Override
            public int compare(Resume o1, Resume o2){
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if(cmp!=0) return cmp;
                return o1.getUuid().compareTo(o2.getUuid());
            }

        });
        return list;
    }

    protected abstract List<Resume> doGetAll();

    @Override
    public abstract int size();
}
