package com.example.rodri.filmesseries.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.rodri.filmesseries.DAO.VideosContract;
import com.example.rodri.filmesseries.R;

public class FilmeAssistidoAdapter extends RecyclerView.Adapter<FilmeAssistidoAdapter.ViewHolder> {
    private Cursor cursor;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitulo;
        public TextView txtData;
        public TextView txtDescricao;
        public ImageView imgVImagem;

        public ViewHolder (View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.filme_titulo);
            txtData = (TextView) itemView.findViewById(R.id.filme_data);
            txtDescricao = (TextView) itemView.findViewById(R.id.filme_pop);
            imgVImagem  = (ImageView) itemView.findViewById(R.id.filme_imagem);


        }

    }

    @NonNull
    @Override
    public FilmeAssistidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaFilmeAssistido = inflater.inflate(R.layout.list_filme, parent, false);
        FilmeAssistidoAdapter.ViewHolder viewHolder = new FilmeAssistidoAdapter.ViewHolder(linhaFilmeAssistido);

        return viewHolder;
    }


    public FilmeAssistidoAdapter(Cursor c) {
        this.cursor = c;
    }

    public void SetCursor(Cursor c){
        this.cursor = c;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeAssistidoAdapter.ViewHolder holder, int position) {
        int idxTitulo = cursor.getColumnIndexOrThrow(VideosContract.Filme.COLUMN_NAME_TITULO);
        int idxData = cursor.getColumnIndexOrThrow(VideosContract.Filme.COLUMN_NAME_DATA);
        int idxDescricao = cursor.getColumnIndexOrThrow(VideosContract.Filme.COLUMN_NAME_DESCRICAO);
        int idxImagem = cursor.getColumnIndexOrThrow(VideosContract.Filme.COLUMN_NAME_IMAGEM);
        cursor.moveToPosition(position);
        holder.txtTitulo.setText(String.valueOf(cursor.getString(idxTitulo)));
        holder.txtData.setText(String.valueOf(cursor.getString(idxData)));
        holder.txtDescricao.setText(String.valueOf(cursor.getString(idxDescricao)));
        final ViewHolder imageViewHolder = (ViewHolder) holder;
        final Context glideContext = holder.imgVImagem.getContext();
        Glide.with(glideContext).asBitmap().load(String.valueOf(cursor.getString(idxImagem))).into(new BitmapImageViewTarget(imageViewHolder.imgVImagem) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(glideContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageViewHolder.imgVImagem.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    @Override
    public long getItemId(int position) {
        int idxID = cursor.getColumnIndexOrThrow(VideosContract.Filme._ID);
        cursor.moveToPosition(position);
        return cursor.getLong(idxID);
    }
}
