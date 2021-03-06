package cn.zhoujia.haowanapp.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import cn.zhoujia.haowanapp.greendao.Notepad;

import cn.zhoujia.haowanapp.greendao.NotepadDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig notepadDaoConfig;

    private final NotepadDao notepadDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        notepadDaoConfig = daoConfigMap.get(NotepadDao.class).clone();
        notepadDaoConfig.initIdentityScope(type);

        notepadDao = new NotepadDao(notepadDaoConfig, this);

        registerDao(Notepad.class, notepadDao);
    }
    
    public void clear() {
        notepadDaoConfig.getIdentityScope().clear();
    }

    public NotepadDao getNotepadDao() {
        return notepadDao;
    }

}
