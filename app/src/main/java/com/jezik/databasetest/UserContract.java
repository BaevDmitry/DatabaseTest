package com.jezik.databasetest;

import android.provider.BaseColumns;

/**
 * Created by Дмитрий on 29.06.2016.
 */
public final class UserContract {

    public UserContract() {
    }

    public static abstract class UserEntry implements BaseColumns {
        //Table Name
        public static final String TABLE_USERdETAIL = "userdetail";

        //userdetail Table Columns
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String COLLEGE = "college";
        public static final String PLACE = "place";
        public static final String USER_ID = "userId";
        public static final String NUMBER = "number";
    }
}
