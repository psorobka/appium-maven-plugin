/*
 * Copyright © 2014 Piotr Soróbka <psorobka@gmail.com>.
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
package com.github.psorobka.appium;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @author Piotr Soróbka <psorobka@gmail.com>
 */
public class AppiumExampleActivity extends Activity {

    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appium_example);
        nameEditText = (EditText) findViewById(R.id.name);
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.hello, nameEditText.getText()))
                .setPositiveButton(R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
