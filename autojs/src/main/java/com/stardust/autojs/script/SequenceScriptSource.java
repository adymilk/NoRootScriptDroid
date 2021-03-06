package com.stardust.autojs.script;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.stardust.io.ConcatReader;

import java.io.Reader;
import java.io.StringReader;

/**
 * Created by Stardust on 2017/4/2.
 */

public class SequenceScriptSource extends ScriptSource {

    private String mScript;
    private ScriptSource mSecondScriptSource;
    private ScriptSource mFirstScriptSource;


    public SequenceScriptSource(String name, ScriptSource firstScriptSource, ScriptSource secondScriptSource) {
        super(name);
        mSecondScriptSource = secondScriptSource;
        mFirstScriptSource = firstScriptSource;
    }

    @NonNull
    @Override
    public String getScript() {
        concatScriptsIfNeeded();
        return mScript;
    }

    private void concatScriptsIfNeeded() {
        if (mScript != null)
            return;
        mScript = mFirstScriptSource.getScript() + mSecondScriptSource.getScript();
    }

    @Nullable
    @Override
    public Reader getScriptReader() {
        if (mScript != null)
            return new StringReader(mScript);
        return new ConcatReader(mFirstScriptSource.getNonNullScriptReader(), mSecondScriptSource.getNonNullScriptReader());
    }

    @Override
    public String toString() {
        return mSecondScriptSource.toString();
    }

}
