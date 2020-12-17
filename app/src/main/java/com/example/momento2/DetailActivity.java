package com.example.momento2;

import android.os.Bundle;

import com.example.momento2.models.ShoesModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends BaseActivity {

    private FloatingActionButton fab_detail_list;
    private FloatingActionButton fab_detail_edit;
    private FloatingActionButton fab_detail_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        model = (ShoesModels) getIntent().getSerializableExtra("model");
        if (model != null){
            makeSimpleAlertDialog("Success", "Brand: " + model.getBrand());
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", model);
            DataDetailFragment.receiveData(bundle);
        }else{
            makeSimpleAlertDialog("Error", "Empty model");
        }

        fab_detail_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

        fab_detail_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEdit();
            }
        });

        fab_detail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(model);
            }
        });

    }

    private void delete(ShoesModels model) {

    }

    protected void init(){
        fab_detail_list = findViewById(R.id.fab_detail_list);
        fab_detail_edit = findViewById(R.id.fab_detail_edit);
        fab_detail_delete = findViewById(R.id.fab_detail_delete);
    }
}