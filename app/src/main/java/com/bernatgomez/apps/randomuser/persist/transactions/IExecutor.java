package com.bernatgomez.apps.randomuser.persist.transactions;

import com.bernatgomez.apps.randomuser.models.BaseModel;


/**
 * Common interface for operation execution
 *
 * Created by bernatgomez on 09/09/2017.
 */
public interface IExecutor {

    //TODO: add more transactions (INSERT_USER, REMOVE_USER, DELETE_USER), DB related or not
    enum CmdType {NONE, DISABLE_USER};

    /**
     * Apply given transaction over given model
     * @param cmd
     * @param model
     */
    public void execute(CmdType cmd, BaseModel model);
}
