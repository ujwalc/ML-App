package com.example.langtranslation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // source_txt variable defined to get input value from user.
    private TextInputEditText source_txt;
    // target_txt variable for translated final output.
    private TextView target_txt;
    // variables for languages used in translation.
    private String engish,french;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Values from resource xml files are stored in below variables.
        source_txt=findViewById(R.id.source_txt);
        target_txt=findViewById(R.id.target_txt);
        engish=getString(R.string.en);
        french=getString(R.string.fr);
        final TranslateViewModel viewModel= ViewModelProviders.of(this).get(TranslateViewModel.class);

        viewModel.sourceLang.setValue(new Language(engish));
        viewModel.targetLang.setValue(new Language(french));


        source_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setProgressText(target_txt);
                viewModel.sourceText.postValue(s.toString());
            }
        });

        viewModel.translatedText.observe(this, new Observer<ResultOrError>(){
            @Override
           public void onChanged(ResultOrError resultOrError){

                if (resultOrError.error != null){
                    source_txt.setError(resultOrError.error.getLocalizedMessage());
                } else{
                    //Final output is print if no errors
                    target_txt.setText(resultOrError.result);
                }

            }
        });
    }
    private void setProgressText(TextView tv){
        tv.setText(this.getString(R.string.translated_progress));
    }
}
