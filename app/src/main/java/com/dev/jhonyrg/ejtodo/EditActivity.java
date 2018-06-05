package com.dev.jhonyrg.ejtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import myclass.ToDo;
import myclass.ToDoBase;

public class EditActivity extends AppCompatActivity {
    private ToDoBase objBaseNotes;

    @NotEmpty(messageId = R.string.error, order = 1)
    @MinLength(value = 3, messageId = R.string.error, order = 2)
    @BindView(R.id.etxtTitle) public MaterialEditText title;

    @NotEmpty(messageId = R.string.error, order = 1)
    @MinLength(value = 3, messageId = R.string.error, order = 2)
    @BindView(R.id.etxtDescription) public MaterialEditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        ButterKnife.bind(this);
        new ToDoBase(this);
    }

    @OnClick(R.id.btnSaved)
    public void ClickSave()
    {
        if(FormValidator.validate(this, new SimpleErrorPopupCallback(this)))
        {
            //Insertar registro en la base de datos
            if(MainActivity.objNote == null)
            {
                //Si no es actualización, inserción nueva. Crear objeto
                ToDo notaToDo = new ToDo();
                notaToDo.setTitulo(this.title.getText().toString());
                notaToDo.setDescripcion(this.description.getText().toString());
                notaToDo.setFecha(notaToDo.getFecha());

                /*notaToDo.setTitulo("Esperar");
                notaToDo.setDescripcion("Esperar un momento");
                notaToDo.setFecha(notaToDo.getFecha());*/

                //Abrir Base de datos
                //objBaseNotes.OpenDatabase();
                ToDoBase.OpenDatabase();
                if(ToDoBase.Insert(notaToDo))
                {
                    Toast.makeText(this, "Inserción", Toast.LENGTH_SHORT).show();
                    ToDoBase.CloseDatabase();
                }
                else
                {
                    Toast.makeText(this, "Falló Inserción", Toast.LENGTH_SHORT).show();
                    ToDoBase.CloseDatabase();
                }
            }
        }
    }

    @OnClick(R.id.btnCancel)
    public void ClickCancel()
    {
        this.finish();
    }
}
