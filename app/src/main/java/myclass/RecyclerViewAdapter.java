package myclass;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.jhonyrg.ejtodo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<ToDo> toDo;
    private int idLayout;
    private OnItemClickListener itemClickListener;

    public RecyclerViewAdapter(List<ToDo> toDo, int idLayout, OnItemClickListener listener) {
        this.toDo = toDo;
        this.idLayout = idLayout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflar layout item
        View view = LayoutInflater.from(parent.getContext()).inflate(idLayout, parent, false);

        //Crear view Holder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(toDo.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.toDo.size();
    }

    //Clase ViewHolder para el adaptador
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtvTitulo) public TextView titulo;
        @BindView(R.id.txtvDescripcion) public TextView descripcion;
        @BindView(R.id.txtvFecha) public TextView fecha;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        public void bind(final ToDo note, final OnItemClickListener listener)
        {
            this.titulo.setText(note.getTitulo());
            this.descripcion.setText(note.getDescripcion());
            this.fecha.setText(note.getFecha());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(note, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(ToDo note, int position);
    }
}
