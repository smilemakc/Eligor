/*
 * Copyright (C) 2014 nohana, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.eligor.sample;

import com.eligor.Eligor;
import com.eligor.FallbackRunnable;
import com.eligor.SimplePeriodicSyncManager;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    private final Eligor mEligor = Eligor.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEligor.registerPeriodicSyncManager(new SimplePeriodicSyncManager(new Account("account_name", "account_type"), "authority", new FallbackRunnable() {

            @Override
            public void onPerformSync(Bundle args) {
                Log.v("sample", "dispatched");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    Log.v("sample", "interrupted");
                }
            }
        }));
    }

    public void onRequestSync(View view) {
        mEligor.requestSync(true);
    }
}
