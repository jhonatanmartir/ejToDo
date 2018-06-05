package com.dev.jhonyrg.ejtodo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myclass.RecyclerViewAdapter;
import myclass.ToDo;
import myclass.ToDoBase;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvToDo) private RecyclerView viewToDo;

    private RecyclerViewAdapter adapter;
    private List<ToDo> listToDo;
    public static ToDo objNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        this.viewToDo.setHasFixedSize(true);

        new ToDoBase(this);
        this.listToDo = new ArrayList<ToDo>();
        this.adapter = new RecyclerViewAdapter(listToDo, R.layout.item_view, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ToDo note, int position) {
                Toast.makeText(MainActivity.this, "Item "+ position, Toast.LENGTH_SHORT).show();
            }
        });

        this.viewToDo.setLayoutManager(new LinearLayoutManager(this));
        this.viewToDo.setAdapter(this.adapter);
        this.viewToDo.setItemAnimator(new DefaultItemAnimator());
        //this.fillList();
    }

    private void fillList() {
        ToDoBase.OpenDatabase();
        Cursor cursor = ToDoBase.getNotesToDo();

        //Validar, cursor !vacio
        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            this.listToDo.clear();

            do {
                //Crear instancia nota por fila
                ToDo rowToDo = new ToDo();
                rowToDo.setTitulo(cursor.getColumnName(1));
                rowToDo.setDescripcion(cursor.getColumnName(2));
                rowToDo.setFecha(cursor.getColumnName(3));

                //agregar a la lista ToDo
                this.listToDo.add(rowToDo);
            }
            while (cursor.moveToNext());
        }
        ToDoBase.CloseDatabase();
    }

    @OnClick(R.id.fab)
    public void ClickAction()
    {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.fillList();
        this.adapter.notifyDataSetChanged();
    }
}
