package edu.sjtu.stap.diff.diff;

import java.util.List;

/**
 * Created by weizhaoy on 16/7/18.
 */
public interface IDifferResult {

    List<String > getModifiedFunctions();
    Boolean ifChooseAll();
}
