/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.girish.sqlitedemo.data;

import android.provider.BaseColumns;

/**
 * API Contract for the  app.
 */
public final class HabbitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabbitContract() {
    }

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single person.
     */
    public static final class HabbitEntry implements BaseColumns {

        /**
         * Name of database table for person
         */
        public final static String TABLE_NAME = "personName";

        /**
         * Unique ID number for the person (only for use in the database table).
         * <p/>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the person.
         * <p/>
         * Type: TEXT
         */
        public final static String COLUMN_PERSON_NAME = "name";


        /**
         * Gender of the person.
         * <p/>
         * The only possible values are
         * <p/>
         * Type: INTEGER
         */
        public final static String COLUMN_PERSON_GENDER = "gender";



    }

}

