package com.example.momento2;

import android.os.Bundle;

import com.example.momento2.adapters.ShoeAdapter;
import com.example.momento2.models.ShoesModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_create;
    private ListView lv_list_shoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();

        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreate();
            }
        });

        lv_list_shoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                model = modelsArrayList.get(position);
                goToDetail(model);
            }
        });

    }

    protected void init(){
        fab_list_create = findViewById(R.id.fab_list_create);
        lv_list_shoes = findViewById(R.id.lv_list_shoes);
    }

    protected void getShoes(){
        if (collectionReference != null){
            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                if (task.getResult() != null){
                                    modelsArrayList = new ArrayList<>();
                                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                                        model = snapshot.toObject(ShoesModels.class);
                                        modelsArrayList.add(model);
                                    }
                                    if (modelsArrayList.size() > 0){
                                        paintShoes(modelsArrayList);
                                    }else{
                                        makeSimpleAlertDialog("Warning", "Shoes doesn´t found");
                                    }
                                }else{
                                    makeSimpleAlertDialog("Warning", "Shoes doesn´t found");
                                }
                            }else{
                                makeSimpleAlertDialog("Error", task.getException().getMessage());
                            }
                        }
                    });
        }else{
            makeSimpleToast("Database Error",1);
        }
    }

    protected void paintShoes(ArrayList<ShoesModels> modelsArrayList){
        adapter = new ShoeAdapter(this, modelsArrayList);
        lv_list_shoes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getShoes();
    }
}