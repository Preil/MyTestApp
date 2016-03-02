package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ilya on 21.02.2016.
 */
abstract public class AbstractStorage<C> implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Delete all resumes.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract boolean exist(C ctx);

    protected abstract C getContext(String uuid);

    @Override
    public void save(Resume r) throws IOException {
        logger.info("Save resume with uuid = " + r.getUuid());
        C ctx = getContext(r);
        if (exist(ctx))
            throw new WebAppException("Resume with " + r.getUuid() + " already exist");
        doSave(ctx, r);
    }

    protected abstract void doSave(C ctx, Resume r) throws IOException;

    @Override
    public void update(Resume r) {
        logger.info("Update resume with " + r.getUuid());
        C ctx = getContext(r);
        if (!exist(ctx))
            throw new WebAppException("Resume with " + r.getUuid() + "doesn't exist");
        doUpdate(ctx, r);
    }

    protected abstract void doUpdate(C ctx, Resume r);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with " + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx))
            throw new WebAppException("Resume with " + uuid + "doesn't exist");
        return doLoad(ctx);
    }

    protected abstract Resume doLoad(C ctx);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with " + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx))
            throw new WebAppException("Resume with " + uuid + "doesn't exist");
        doDelete(ctx);
    }

    protected abstract void doDelete(C ctx);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllsorted");
        List<Resume> list = doGetAll();
        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if (cmp != 0) return cmp;
                return o1.getUuid().compareTo(o2.getUuid());
            }

        });
        return list;
    }

    protected abstract List<Resume> doGetAll();

    @Override
    public abstract int size();

    private C getContext(Resume r) {
        return getContext(r.getUuid());
    }
}
