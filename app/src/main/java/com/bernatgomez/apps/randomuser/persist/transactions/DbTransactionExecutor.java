package com.bernatgomez.apps.randomuser.persist.transactions;

import android.os.AsyncTask;

import com.bernatgomez.apps.randomuser.DbTransactionResult;
import com.bernatgomez.apps.randomuser.holder.DbHolder;
import com.bernatgomez.apps.randomuser.models.BaseModel;
import com.bernatgomez.apps.randomuser.models.UserModel;
import com.bernatgomez.apps.randomuser.persist.RandomUserDatabase;
import com.bernatgomez.apps.randomuser.persist.User;
import com.bernatgomez.apps.randomuser.utils.AndroidLogger;
import com.squareup.otto.Bus;

import javax.inject.Inject;


/**
 * DB transaction executor based on the command pattern
 *
 * Created by bernatgomez on 09/09/2017.
 */
public class DbTransactionExecutor implements IExecutor {

    private static final String TAG = DbTransactionExecutor.class.getSimpleName();

    private Bus bus;


    @Inject
    public DbTransactionExecutor(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void execute(CmdType cmd, BaseModel model) {
        AndroidLogger.logMsg(TAG, "execute(): " + cmd + " " + model);

        switch (cmd) {

            case DISABLE_USER:
                new DbTransaction(cmd).execute((UserModel) model);
                break;

            default:
                break;
        }
    }

    /**
     * Async task used to perform db transaction on background
     */
    final class DbTransaction extends AsyncTask<UserModel, Void, Boolean> {

        private CmdType type;
        private UserModel target;


        public DbTransaction(CmdType type) {
            this.type = type;
        }

        /**
         * Get data target used in db transactions
         *
         * @param target
         * @return
         */
        private User getUserDbModelFrom(UserModel target) {
            User userDb = new User();

            userDb.setFirstName(target.getName().getFirst());
            userDb.setLastName(target.getName().getLast());
            userDb.setEnabled(false);

            return userDb;
        }

        @Override
        protected Boolean doInBackground(UserModel... userModels) {

            try {
                this.target = userModels[0];

                RandomUserDatabase db = DbHolder.getInstance().getDb();

                db.userDao().insertUser(this.getUserDbModelFrom(this.target));

                return true;

            } catch (Exception e) {
                AndroidLogger.logError(TAG, "doInBackground()", e);

                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            AndroidLogger.logMsg(TAG, "result: "+ result);

            super.onPostExecute(result);

            bus.post(new DbTransactionResult(CmdType.DISABLE_USER.name(), result));
        }
    }
}
